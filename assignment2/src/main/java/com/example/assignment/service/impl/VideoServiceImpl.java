package com.example.assignment.service.impl;

//import com.example.assignment.JPAUtil;
import com.example.assignment.entity.User;
import com.example.assignment.entity.Video;
import com.example.assignment.hibernate.HibernateUtil;
import com.example.assignment.service.VideoService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.util.List;

public class VideoServiceImpl implements VideoService {
//    EntityManager em = JPAUtil.getEntityManager();
    private EntityManager entityManager = HibernateUtil.createEntityManager();

    @Override
    public Video findById(String id) {
        EntityManager em = HibernateUtil.createEntityManager();
        Video u = em.find(Video.class, id);
        em.close();
        return u;
    }

    @Override
    public void add(Video u) {
        EntityManager em = HibernateUtil.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(u);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Override
    public void update(Video u) {
        EntityManager em = HibernateUtil.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(u);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Override
    public void deleteById(String id) {
        EntityManager em = HibernateUtil.createEntityManager();
        try {
            em.getTransaction().begin();
            Video u = findById(id);
            em.remove(u);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Video> findAll() {
        EntityManager em = HibernateUtil.createEntityManager();
        TypedQuery<Video> query = em.createQuery("SELECT u FROM Video u", Video.class);
        List<Video> list = query.getResultList();
        em.close();
        return list;
    }

    @Override
    public List<Video> search(String keyword) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Video> criteriaQuery = criteriaBuilder.createQuery(Video.class);
        Root<Video> root = criteriaQuery.from(Video.class);

        // Tạo điều kiện cho việc tìm kiếm dựa trên tiêu đề (title)
        Predicate titlePredicate = criteriaBuilder.like(
                criteriaBuilder.lower(root.get("title")),
                "%" + keyword.toLowerCase() + "%"
        );
        criteriaQuery.where(titlePredicate);

        TypedQuery<Video> typedQuery = entityManager.createQuery(criteriaQuery);
        return typedQuery.getResultList();
    }


    @Override
    public List<Video> searchFavorite(String userId, String keyword) {
        //TODO
        return null;
    }
}

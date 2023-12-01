package com.example.assignment.service.impl;

import com.example.assignment.entity.User;
import com.example.assignment.hibernate.HibernateUtil;
import com.example.assignment.service.UserService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class UserServiceImpl implements UserService {
    // Đoạn mã để lấy EntityManager
    private EntityManager entityManager = HibernateUtil.createEntityManager();

    @Override
    public boolean checkUser(String username, String password) {
            // Sử dụng EntityManager để truy vấn cơ sở dữ liệu và kiểm tra người dùng
            try {
                entityManager.getTransaction().begin();

                // Thực hiện truy vấn để lấy thông tin người dùng dựa trên username (id)
                User user = entityManager.createQuery("SELECT u FROM User u WHERE u.id = :username", User.class)
                        .setParameter("username", username)
                        .getSingleResult();

                entityManager.getTransaction().commit();

                // Kiểm tra xem thông tin đăng nhập có khớp không
                if (user != null && user.getPassword().equals(password)) {
                    return true; // Người dùng tồn tại và mật khẩu khớp
                }
            } catch (NoResultException e) {
                // Người dùng không tồn tại trong cơ sở dữ liệu
                System.out.println("User not found: " + e.getMessage());
            } finally {
//                entityManager.close();
            }
            return false; // Người dùng không tồn tại hoặc mật khẩu không khớp

    }

    @Override
    public List<User> getAll() {
        EntityManager em = HibernateUtil.createEntityManager();
        TypedQuery<User> query = em.createQuery("SELECT u FROM User u", User.class);
        List<User> list = query.getResultList();
        em.close();
        return list;
    }

    @Override
    public void create(User u) {
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
    public void update(User u) {
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
            User u = getById(id);
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
    public User getById(String id) {
        EntityManager em = HibernateUtil.createEntityManager();
        User u = em.find(User.class, id);
        em.close();
        return u;
    }
    @Override
    public boolean isAdmin(String username) {
        EntityManager em = HibernateUtil.createEntityManager();
        try {
            em.getTransaction().begin();

            // Thực hiện truy vấn để lấy thông tin người dùng dựa trên username
            User user = em.createQuery("SELECT u FROM User u WHERE u.id = :username", User.class)
                    .setParameter("username", username)
                    .getSingleResult();

            em.getTransaction().commit();

            // Kiểm tra xem user có vai trò admin không
            return user != null && Boolean.TRUE.equals(user.getAdmin());
        } catch (NoResultException e) {
            // Người dùng không tồn tại trong cơ sở dữ liệu
            System.out.println("User not found: " + e.getMessage());
        } finally {
            em.close();
        }
        return false; // Nếu không tìm thấy hoặc không có vai trò admin
    }

}

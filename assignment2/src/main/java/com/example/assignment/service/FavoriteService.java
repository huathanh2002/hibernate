package com.example.assignment.service;

import com.example.assignment.entity.Favorite;
import com.example.assignment.entity.User;
import com.example.assignment.entity.Video;
import com.example.assignment.hibernate.HibernateUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.Date;
import java.util.List;


public class FavoriteService {
    private EntityManager entityManager = HibernateUtil.createEntityManager();

    public List<Favorite> getAll(String userId) {
        String queryString = "SELECT f FROM Favorite f " +
                "JOIN FETCH f.video v " +
                "WHERE f.user.id = :userId";

        TypedQuery<Favorite> query = entityManager.createQuery(queryString, Favorite.class);
        query.setParameter("userId", userId);

        return query.getResultList();
    }
    public void create(Favorite favorite) {
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            // Lấy thông tin user từ Favorite object
            User user = favorite.getUser();
            // Kiểm tra xem User đã tồn tại trong database chưa
            User existingUser = entityManager.find(User.class, user.getId());
            if (existingUser == null) {
                // Nếu không tồn tại, persist user mới
                entityManager.persist(user);
            }

            // Lấy thông tin video từ Favorite object
            Video video = favorite.getVideo();
            // Kiểm tra xem Video đã tồn tại trong database chưa
            Video existingVideo = entityManager.find(Video.class, video.getId());
            if (existingVideo == null) {
                // Nếu không tồn tại, persist video mới
                entityManager.persist(video);
            }

            // Set likedate cho Favorite object
            favorite.setLikedate(new Date());

            // Thêm Favorite vào database
            entityManager.persist(favorite);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
//            entityManager.close();
        }
    }
    // Tìm Favorite bằng id của Video
    public Favorite getByVideoId(String videoId) {
        EntityManager em = HibernateUtil.createEntityManager();
        Favorite favorite = null;
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Favorite> cq = cb.createQuery(Favorite.class);
            Root<Favorite> root = cq.from(Favorite.class);
            cq.select(root).where(cb.equal(root.get("video").get("id"), videoId));
            favorite = em.createQuery(cq).getSingleResult();
        } catch (NoResultException e) {
            // Xử lý khi không tìm thấy Favorite
            e.printStackTrace();
        } finally {
            em.close();
        }
        return favorite;
    }

    // Xóa Favorite bằng id của Video
    public void deleteByVideoId(String videoId) {
        EntityManager em = HibernateUtil.createEntityManager();
        try {
            em.getTransaction().begin();

            // Tìm Favorite theo id của Video và xóa nó
            Favorite favorite = getByVideoId(videoId);
            if (favorite != null) {
                em.remove(favorite);
            }

            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public boolean isFavorite(String userId, String videoId) {
        try {
            // Tạo truy vấn kiểm tra sự tồn tại của cặp (userId, videoId)
            long count = (long) entityManager.createQuery(
                    "SELECT COUNT(f) FROM Favorite f WHERE f.user.id = :userId AND f.video.id = :videoId")
                    .setParameter("userId", userId)
                    .setParameter("videoId", videoId)
                    .getSingleResult();

            // Nếu count > 0 tức là cặp (userId, videoId) tồn tại trong bảng Favorite, trả về true
            return count > 0;
        } catch (Exception e) {
            e.printStackTrace();
            // Trong trường hợp xảy ra lỗi hoặc ngoại lệ, trả về false (có thể xử lý theo cách riêng của bạn)
            return false;
        }
    }
    public List<Video> searchFavorite(String userId, String keyword) {
        String queryString = "SELECT v FROM Video v " +
                "JOIN v.favorites f " +
                "WHERE f.user.id = :userId " +
                "AND (LOWER(v.title) LIKE LOWER(:keyword) OR LOWER(v.description) LIKE LOWER(:keyword))";

        TypedQuery<Video> query = entityManager.createQuery(queryString, Video.class);
        query.setParameter("userId", userId);
        query.setParameter("keyword", "%" + keyword + "%");

        return query.getResultList();
    }
}

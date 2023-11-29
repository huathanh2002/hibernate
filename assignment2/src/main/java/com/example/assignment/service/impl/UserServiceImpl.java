package com.example.assignment.service.impl;

import com.example.assignment.entity.User;
import com.example.assignment.hibernate.HibernateUtil;
import com.example.assignment.service.UserService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;

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

}

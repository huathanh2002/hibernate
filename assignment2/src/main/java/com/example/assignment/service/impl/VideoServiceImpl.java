package com.example.assignment.service.impl;

import com.example.assignment.JPAUtil;
import com.example.assignment.entity.Video;
import com.example.assignment.hibernate.HibernateUtil;
import com.example.assignment.service.VideoService;
import jakarta.persistence.EntityManager;

import java.util.List;

public class VideoServiceImpl implements VideoService {
//    EntityManager em = JPAUtil.getEntityManager();
    private EntityManager entityManager = HibernateUtil.createEntityManager();

    @Override
    public Video findById(String id) {
        //TODO
        return null;
    }

    @Override
    public Video add(Video m) {
        //TODO
        return null;
    }

    @Override
    public Video update(Video m) {
        //TODO
        return null;
    }

    @Override
    public void deleteById(String id) {
        //TODO
    }

    @Override
    public List<Video> findAll() {
        //TODO
        return null;
    }

    @Override
    public List<Video> search(String keyword) {
        //TODO
        return null;
    }

    @Override
    public List<Video> searchFavorite(String userId, String keyword) {
        //TODO
        return null;
    }
}

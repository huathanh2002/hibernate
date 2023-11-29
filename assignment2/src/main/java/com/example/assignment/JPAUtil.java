package com.example.assignment;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAUtil {
    static EntityManagerFactory factory = Persistence.createEntityManagerFactory("PolyOE");
    static EntityManager em = factory.createEntityManager();

    public static EntityManager getEntityManager(){
        return em;
    }
}

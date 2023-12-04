package com.example.assignment.hibernate;


import com.example.assignment.entity.User;
import com.example.assignment.entity.Video;
import com.example.assignment.entity.Favorite;
import jakarta.persistence.EntityManager;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

public class HibernateUtil {
    private static final SessionFactory FACTORY;

    static {
        Configuration conf = new Configuration();

        Properties properties = new Properties();
        properties.put(Environment.DIALECT, "org.hibernate.dialect.SQLServerDialect");
        properties.put(Environment.DRIVER, "com.microsoft.sqlserver.jdbc.SQLServerDriver");
//        properties.put(Environment.URL, "jdbc:sqlserver://192.168.13.24:1433;databaseName=asmgd2java4;encrypt=true;trustServerCertificate=true");
        properties.put(Environment.URL, "jdbc:sqlserver://localhost:1433;databaseName=asmgd2java4;encrypt=true;trustServerCertificate=true");
        properties.put(Environment.USER, "sa");
        properties.put(Environment.PASS, "123");
        properties.put(Environment.SHOW_SQL, "true");

        conf.setProperties(properties);
        conf.addAnnotatedClass(Favorite.class);
        conf.addAnnotatedClass(Video.class);
        conf.addAnnotatedClass(User.class);
        ServiceRegistry registry = new StandardServiceRegistryBuilder()
                .applySettings(conf.getProperties()).build();
        FACTORY = conf.buildSessionFactory(registry);

    }

    public static EntityManager createEntityManager() {
        return FACTORY.createEntityManager();
    }

    public static void main(String[] args) {
        createEntityManager();
    }
}

package com.example.models.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Datamanager {

    private static Datamanager datamanager;
    public EntityManager manager;

    public Datamanager() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("quiz_unit");
        this.manager = entityManagerFactory.createEntityManager();
    }

    public static Datamanager getInstanceManager() {
        if (datamanager == null) {
            datamanager = new Datamanager();
        }
        return datamanager;
    }
}

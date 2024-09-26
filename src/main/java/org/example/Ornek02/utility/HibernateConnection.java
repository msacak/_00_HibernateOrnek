package org.example.Ornek02.utility;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class HibernateConnection {
    public static EntityManagerFactory emf = Persistence.createEntityManagerFactory("URUN");
    public static EntityManager em = emf.createEntityManager();
}

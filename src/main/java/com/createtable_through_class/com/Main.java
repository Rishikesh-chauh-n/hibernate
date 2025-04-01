package com.createtable_through_class.com;

import jakarta.persistence.*;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ecommerce");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        try {
            User user = new User("John Doe", "john@example.com");
            em.persist(user); // Attempt to insert
            em.getTransaction().commit();
            System.out.println("User saved successfully!");
        } catch (PersistenceException e) {
            em.getTransaction().rollback(); // Rollback if error occurs
            System.out.println("Error: This email is already registered. Please use a different email.");
        } finally {
            em.close();
            emf.close();
        }
    }
}

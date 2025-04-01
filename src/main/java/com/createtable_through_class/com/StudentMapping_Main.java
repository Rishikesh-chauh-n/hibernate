package com.createtable_through_class.com;

import jakarta.persistence.*;
import java.util.*;

public class StudentMapping_Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ecommerce");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        saveStudent(em, "Alice", Arrays.asList("Java", "Spring Boot", "Hibernate"));
        saveStudent(em, "Bob", Arrays.asList("Python", "Django"));
        saveStudent(em, "Rishi", Arrays.asList("Python", "Django"));
        saveStudent(em, "Alice", Arrays.asList("Java", "Spring Boot", "Hibernate")); // Allowed, since ID will be different

        em.getTransaction().commit();
        em.close();
        emf.close();
    }

    private static void saveStudent(EntityManager em, String name, List<String> skills) {
        // Directly insert a new student since ID is auto-generated and must be unique
        StudentMapping student = new StudentMapping();
        student.setName(name);
        student.setSkills(skills);
        em.persist(student);
        System.out.println("Inserted new student: " + name + " with skills: " + skills);
    }
}

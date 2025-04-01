package com.onetoonemapping;

import jakarta.persistence.EntityManager;

public class UserService {

    // ✅ Dynamic User & Passport Add Karna
    public static void addUserWithPassport(String userName, String passportNumber) {
        EntityManager entityManager = JPAUtil.getEntityManager();
        entityManager.getTransaction().begin();

        Passport passport = new Passport();
        passport.setPassportNumber(passportNumber);

        User user = new User();
        user.setName(userName);
        user.setPassport(passport);

        passport.setUser(user); // (Only if Bidirectional Mapping)

        entityManager.persist(passport); // Pehle passport save hoga
        entityManager.persist(user);    // Fir user save hoga

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    // ✅ Passport Number Se User Ka Naam Fetch Karna
    public static String getUserByPassportNumber(String passportNumber) {
        EntityManager entityManager = JPAUtil.getEntityManager();
        String query = "SELECT u FROM User u WHERE u.passport.passportNumber = :passportNumber";
        User user = entityManager.createQuery(query, User.class)
                .setParameter("passportNumber", passportNumber)
                .getSingleResult();
        entityManager.close();
        return user.getName();
    }

    // ✅ User Name Se Passport Number Fetch Karna
    public static String getPassportByUserName(String userName) {
        EntityManager entityManager = JPAUtil.getEntityManager();
        String query = "SELECT p FROM Passport p WHERE p.user.name = :name";
        Passport passport = entityManager.createQuery(query, Passport.class)
                .setParameter("name", userName)
                .getSingleResult();
        entityManager.close();
        return passport.getPassportNumber();
    }
}

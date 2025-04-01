package com.LearningJPQL;

import jakarta.persistence.*;
import java.util.List;

public class BankingApp {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("bankingPU");

    @Entity
    @Table(name = "banks")
    static class Bank {
        @Id
        private Long id = 1L; // Fixed ID for Singleton
        private static final String NAME = "XYZ Bank";
        private static final String DETAILS = "Leading bank with top services";

        private Bank() {}

        public Long getId() { return id; }
        public String getName() { return NAME; }
        public String getDetails() { return DETAILS; }

        public static Bank getInstance(EntityManager em) {
            Bank bank = em.find(Bank.class, 1L);
            if (bank == null) {
                bank = new Bank();
                em.persist(bank);
            }
            return bank;
        }
    }

    @Entity
    @Table(name = "accounts")
    static class Account {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String owner;
        private double balance;

        @ManyToOne
        private Bank bank;

        public Account() {}

        public Account(String owner, double balance, Bank bank) {
            this.owner = owner;
            this.balance = balance;
            this.bank = bank;
        }

        public Long getId() { return id; }
        public String getOwner() { return owner; }
        public double getBalance() { return balance; }
        public Bank getBank() { return bank; }

        private void updateBalance(double amount) {
            this.balance += amount;
        }

        public void deposit(double amount, EntityManager em) {
            updateBalance(amount);
            em.persist(new Transaction(this, amount, "DEPOSIT"));
            em.merge(this);
        }

        public boolean withdraw(double amount, EntityManager em) {
            if (this.balance >= amount) {
                updateBalance(-amount);
                em.persist(new Transaction(this, amount, "WITHDRAWAL"));
                em.merge(this);
                return true;
            }
            return false;
        }
    }

    @Entity
    @Table(name = "transactions")
    static class Transaction {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToOne
        private Account account;

        private double amount;
        private String type; // "DEPOSIT" or "WITHDRAWAL"

        public Transaction() {}

        public Transaction(Account account, double amount, String type) {
            this.account = account;
            this.amount = amount;
            this.type = type;
        }
    }

    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        // Get singleton bank instance
        Bank bank = Bank.getInstance(em);

        // Creating an account
        Account account = new Account("John Doe", 5000.0, bank);
        em.persist(account);

        // Deposit
        account.deposit(2000.0, em);

        // Withdraw
        boolean withdrawalSuccess = account.withdraw(1500.0, em);
        if (!withdrawalSuccess) {
            System.out.println("Insufficient balance for withdrawal!");
        }

        em.getTransaction().commit();

        // Fetch account details using JPQL
        TypedQuery<Account> query = em.createQuery("SELECT a FROM Account a WHERE a.owner = :owner", Account.class);
        query.setParameter("owner", "John Doe");
        List<Account> accounts = query.getResultList();

        for (Account acc : accounts) {
            System.out.println("Account Owner: " + acc.getOwner() + ", Balance: " + acc.getBalance() + ", Bank: " + acc.getBank().getName());
        }

        em.close();
    }
}

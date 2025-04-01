package com.onetoonemapping;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Naya User Add Karein");
            System.out.println("2. Passport Number Se User Ka Naam Find Karein");
            System.out.println("3. User Name Se Passport Number Find Karein");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (choice == 1) {
                System.out.print("Enter User Name: ");
                String name = scanner.nextLine();
                System.out.print("Enter Passport Number: ");
                String passportNumber = scanner.nextLine();

                UserService.addUserWithPassport(name, passportNumber);
                System.out.println("✅ User & Passport Successfully Added!");

            } else if (choice == 2) {
                System.out.print("Enter Passport Number: ");
                String passportNumber = scanner.nextLine();

                String userName = UserService.getUserByPassportNumber(passportNumber);
                System.out.println("User Name: " + userName);

            } else if (choice == 3) {
                System.out.print("Enter User Name: ");
                String name = scanner.nextLine();

                String passportNumber = UserService.getPassportByUserName(name);
                System.out.println("Passport Number: " + passportNumber);

            } else if (choice == 4) {
                System.out.println("Exiting...");
                break;
            } else {
                System.out.println("❌ Invalid Choice! Try Again.");
            }
        }

        scanner.close();
        JPAUtil.close();
    }
}


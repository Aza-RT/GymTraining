package com.example.gymtraining.Menu;
import com.example.gymtraining.Database.*;
import com.example.gymtraining.Exception.InvalidInputException;
import com.example.gymtraining.Model.*;

import java.util.Scanner;

public class GymMenu implements Menu {
    private static final Scanner input = new Scanner(System.in);
    private static final String indent = "    ";
    private static final StaffDAO dao = new StaffDAO();

    @Override
    public void run() {
        int choice;

        do {
            displayMenu();
            choice = readInt("Choose option: ");

            switch (choice) {
                case 1 -> dao.getAllMembers();
                case 2 -> dao.getAllTrainers();
                case 3 -> dao.insertMember(readMember("Enter member: ", 0));
                case 4 -> dao.insertTrainer(readTrainer("Enter trainer: ", 0));
                case 5 -> dao.updateMember(readMember("Enter member: ", 0));
                case 6 -> dao.updateTrainer(readTrainer("Enter trainer: ", 0));
                case 7 -> dao.deleteMember(readInt("Enter member's ID: "));
                case 8 -> dao.deleteTrainer(readInt("Enter trainer's ID: "));
                case 9 -> dao.searchMemberByName(readString("Enter name: "));
                case 10 -> dao.searchTrainerByName(readString("Enter name: "));
                case 11 -> dao.searchMemberByIDs(readInt("Enter lower ID: "), readInt("Enter upper ID: "));
                case 12 -> dao.searchTrainerByIDs(readInt("Enter lower ID: "), readInt("Enter upper ID: "));
                case 0 -> System.exit(0);
                default -> System.out.println("Invalid option. Try again.");
            }

        } while (true);
    }

    @Override
    public void displayMenu() {
        System.out.println("""
                =================================
                     GYM MANAGEMENT SYSTEM
                =================================
                1. View all members
                2. View all trainers
                3. Add new member
                4. Add new trainer
                5. Update member
                6. Update trainer
                7. Delete member
                8. Delete trainer
                =================================
                9. Search member by name
                10. Search trainer by name
                11. Search members by IDs
                12. Search trainers by IDs
                0. Exit
                =================================
                """);
    }

    public static int readInt(String message) {
        System.out.print(message);
        while (!input.hasNextInt()) {
            System.out.print("Enter a valid number: ");
            input.next();
        }
        int value = input.nextInt();
        input.nextLine();
        return value;
    }

    public static String readString(String message) {
        System.out.print(message);
        return input.nextLine();
    }

    public static Member readMember(String message, int indentLevel) {
        System.out.println(message);
        String info = readString(indent.repeat(indentLevel) + "Select a member type (Child/Premium): ");

        int ID = readInt(indent.repeat(indentLevel) + "ID: ");
        String fullName = readString(indent.repeat(indentLevel) + "Full name: ");
        int age = readInt(indent.repeat(indentLevel) + "Age: ");
        String subscriptionDate = readString(indent.repeat(indentLevel) + "Subscription date: ");

        if (info.equalsIgnoreCase("child")) {
            String parentName = readString(indent.repeat(indentLevel) + "Parent name: ");

            return new ChildMember(ID, fullName, age, subscriptionDate, parentName);
        } else if (info.equalsIgnoreCase("premium")) {
            int vipLevel = readInt(indent.repeat(indentLevel) + "VIP level: ");
            int count = readInt(indent.repeat(indentLevel) + "How many trainers? ");
            Trainer[] trainers = new Trainer[count];

            for (int i = 0; i < count; i++) {
                int trainerID = readInt(String.format("Trainer %d's ID: ", i + 1));
                Trainer trainer = dao.getTrainer(trainerID);
                trainers[i] = trainer;
            }

            return new PremiumMember(ID, fullName, age, subscriptionDate, trainers, vipLevel);
        } else {
            throw new InvalidInputException("Wrong member choice.");
        }
    }

    public static Trainer readTrainer(String message, int indentLevel) {
        System.out.println(message);
        String info = readString(indent.repeat(indentLevel) + "Select a trainer type (Personal/Group): ");

        int ID = readInt(indent.repeat(indentLevel) + "ID: ");
        String fullName = readString(indent.repeat(indentLevel) + "Full name: ");
        int age = readInt(indent.repeat(indentLevel) + "Age: ");
        String specialization = readString(indent.repeat(indentLevel) + "Specialization: ");

        if (info.equalsIgnoreCase("personal")) {
            int memberID = readInt("Member's ID: ");
            Member member = dao.getMember(memberID);

            return new PersonalTrainer(ID, fullName, age, specialization, member);
        } else if (info.equalsIgnoreCase("group")) {
            int count = readInt(indent.repeat(indentLevel) + "How many members? ");
            Member[] members = new Member[count];

            for (int i = 0; i < count; i++) {
                int memberID = readInt(String.format("Member %d's ID: ", i + 1));
                Member member = dao.getMember(memberID);
                members[i] = member;
            }

            return new GroupTrainer(ID, fullName, age, specialization, members);
        } else {
            throw new InvalidInputException("Invalid trainer type.");
        }
    }
}

package com.example.gymtraining;

import java.util.*;

public class Main {
    private static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        ArrayList<Member> members = new ArrayList<>();
        ArrayList<Trainer> trainers = new ArrayList<>();

        members.add(new ChildMember(1, "Aruzhan Sadykqyzy", 14, "01.01.2025", "Sadyk A."));
        members.add(new PremiumMember(2, "Dias Nur", 25, "05.01.2025", trainers.toArray(new Trainer[0]), 3));

        trainers.add(new PersonalTrainer(1, "Almas Toleu", 30, "Fitness", new ChildMember(1, "Aruzhan Sadykqyzy", 14, "01.01.2025", "Sadyk A.")));
        trainers.add(new GroupTrainer(2, "Nursultan Bek", 28, "Yoga", members.toArray(new Member[0])));

        int choice;

        do {
            printMenu();
            choice = readInt("Choose option: ");

            switch (choice) {
                case 1 -> viewMembers(members);
                case 2 -> viewTrainers(trainers);
                case 3 -> addMember(members);
                case 4 -> addTrainer(trainers);
                case 0 -> System.exit(0);
                default -> System.out.println("Invalid option. Try again.");
            }

        } while (true);
    }

    private static void printMenu() {
        System.out.println("""
                =================================
                     GYM MANAGEMENT SYSTEM
                =================================
                1. View all members
                2. View all trainers
                3. Add new member
                4. Add new trainer
                0. Exit
                =================================
                """);
    }

    private static void viewMembers(ArrayList<Member> members) {
        if (!members.isEmpty()) {
            System.out.println("\n--------- MEMBERS LIST ---------");

            for (Member m : members) {
                System.out.println(m);
                System.out.println("Subscription: " + m.subscriptionDuration());

                if (m instanceof PremiumMember) {
                    PremiumMember pm = (PremiumMember) m;
                    System.out.println("VIP Subscription Duration: " + pm.subscriptionDuration());
                }
                if (m instanceof ChildMember) {
                    ChildMember cm = (ChildMember) m;
                    System.out.println("Parent: " + cm.getParentName());
                }
                System.out.println("--------------------------------");
            }
        }
    }

    private static void viewTrainers(ArrayList<Trainer> trainers) {
        if (!trainers.isEmpty()) {
            System.out.println("\n--------- TRAINERS LIST ---------");

            for (Trainer t : trainers) {
                System.out.println(t);
                System.out.println(t.scheduleOnDay("Monday"));

                if (t instanceof PersonalTrainer) {
                    PersonalTrainer pt = (PersonalTrainer) t;
                    System.out.println("Session duration: " + pt.durationOfSession());
                }
                if (t instanceof GroupTrainer) {
                    GroupTrainer gt = (GroupTrainer) t;
                    System.out.println(String.format("%s available spots.", gt.hasAvailableSpots() ? "Has" : "Doesn't have"));
                }
                System.out.println("--------------------------------");
            }
        }
    }

    private static void addMember(ArrayList<Member> members) {
        System.out.println("\nAdd Member:");
        System.out.println("1. Child Member");
        System.out.println("2. Premium Member");

        int type = readInt("Choose type: ");

        try {
            if (type == 1) {
                members.add(new ChildMember(
                        readInt("ID: "),
                        readString("Full name: "),
                        readInt("Age: "),
                        readString("Join date: "),
                        readString("Parent name: ")
                ));
            } else if (type == 2) {
                members.add(new PremiumMember(
                        readInt("ID: "),
                        readString("Full name: "),
                        readInt("Age: "),
                        readString("Join date: "),
                        new Trainer[]{readTrainer("Trainer 1: "), readTrainer("Trainer 2: ")},
                        readInt("VIP level (1-5): ")
                ));
            } else {
                System.out.println("Invalid member type.");
                return;
            }
            System.out.println("Member added successfully.");
        } catch (Exception e) {
            return;
        }
    }

    private static void addTrainer(ArrayList<Trainer> trainers) {
        System.out.println("\nAdd Trainer:");
        System.out.println("1. Personal Trainer");
        System.out.println("2. Group Trainer");

        int type = readInt("Choose type: ");

        try {
            if (type == 1) {
                trainers.add(new PersonalTrainer(
                        readInt("ID: "),
                        readString("Full name: "),
                        readInt("Age: "),
                        readString("Specialization: "),
                        readMember("Member: ")
                ));
            } else if (type == 2) {
                trainers.add(new GroupTrainer(
                        readInt("ID: "),
                        readString("Full name: "),
                        readInt("Age: "),
                        readString("Specialization: "),
                        new Member[]{readMember("Member 1: "), readMember("Member 2: ")}
                ));
            } else {
                System.out.println("Invalid trainer type.");
                return;
            }
        } catch (Exception e) {
            return;
        }
    }

    private static int readInt(String message) {
        System.out.print(message);
        while (!input.hasNextInt()) {
            System.out.print("Enter a valid number: ");
            input.next();
        }
        int value = input.nextInt();
        input.nextLine();
        return value;
    }

    private static String readString(String message) {
        System.out.print(message);
        return input.nextLine();
    }

    private static Member readMember(String message) {
        System.out.println(message);
        System.out.print("    ID: ");
        int ID = input.nextInt();
        input.nextLine();
        System.out.print("    Full name: ");
        String fullName = input.nextLine();
        input.nextLine();
        System.out.print("    Age: ");
        int age = input.nextInt();
        input.nextLine();
        System.out.print("    Subscription date: ");
        String subscriptionDate = input.nextLine();

        return new Member(ID, fullName, age, subscriptionDate);
    }

    private static Trainer readTrainer(String message) {
        System.out.println(message);
        System.out.print("    ID: ");
        int ID = input.nextInt();
        input.nextLine();
        System.out.print("    Full name: ");
        String fullName = input.nextLine();
        input.nextLine();
        System.out.print("    Age: ");
        int age = input.nextInt();
        input.nextLine();
        System.out.print("    Specialization: ");
        String specialization = input.nextLine();

        return new Trainer(ID, fullName, age, specialization);
    }
}

package com.example.gymtraining.Menu;
import com.example.gymtraining.Exception.InvalidInputException;
import com.example.gymtraining.Model.*;

import java.util.ArrayList;
import java.util.Scanner;

public class GymMenu implements Menu {
    private static final Scanner input = new Scanner(System.in);
    private final Member member1 = new ChildMember(1, "Aruzhan Sadykqyzy", 14, "01.01.2026", "Sadyk A.");
    private final Member member2 = new PremiumMember(2, "Dias Nur", 25, "05.01.2026", new Trainer[]{new GroupTrainer(1, "Almas Toleu", 30, "Fitness", new Member[]{})}, 3);
    private final Member member3 = new ChildMember(3, "Ilyas Amangeldiyev", 17, "12.01.2026", "Ermek A.");
    private final Trainer trainer1 = new PersonalTrainer(1, "Almas Toleu", 30, "Fitness", member1);
    private final Trainer trainer2 = new GroupTrainer(2, "Nursultan Bek", 28, "Yoga", new Member[]{member3});
    private static final String indent = "    ";

    @Override
    public void run() {
        ArrayList<Member>  members = new ArrayList<>();
        ArrayList<Trainer> trainers = new ArrayList<>();

        members.add(member1);
        members.add(member2);

        trainers.add(trainer1);
        trainers.add(trainer2);

        int choice;

        do {
            displayMenu();
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

        int type;

        while (true) {
            type = readInt("Choose type: ");

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
                            new Trainer[]{readTrainer("Trainer 1: ", 1), readTrainer("Trainer 2: ", 1)},
                            readInt("VIP level (1-5): ")
                    ));
                } else {
                    System.out.println("\nInvalid member type. Type your choice again.\n");
                    continue;
                }
                System.out.println("Member added successfully.");
                break;
            } catch (Exception e) {
                System.out.println("\nError: " + e.getMessage() + "\n");
            }
        }
    }

    private static void addTrainer(ArrayList<Trainer> trainers) {
        System.out.println("\nAdd Trainer:");
        System.out.println("1. Personal Trainer");
        System.out.println("2. Group Trainer");

        int type;

        while (true) {
            try {
                type = readInt("Choose type: ");

                if (type == 1) {
                    trainers.add(new PersonalTrainer(
                            readInt("ID: "),
                            readString("Full name: "),
                            readInt("Age: "),
                            readString("Specialization: "),
                            readMember("Member: ", 1)
                    ));
                } else if (type == 2) {
                    trainers.add(new GroupTrainer(
                            readInt("ID: "),
                            readString("Full name: "),
                            readInt("Age: "),
                            readString("Specialization: "),
                            new Member[]{readMember("Member 1: ", 1), readMember("Member 2: ", 1)}
                    ));
                } else {
                    System.out.println("\nInvalid trainer type. Type your choice again.\n");
                    continue;
                }
                System.out.println("Trainer added successfully.");
                break;
            } catch (Exception e) {
                System.out.println("\nError: " + e.getMessage() + "\n");
            }
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

    private static Member readMember(String message, int indentLevel) {
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
                trainers[i] = readTrainer(String.format("%sTrainer %d: ", indent.repeat(indentLevel - 1), i + 1), indentLevel + 1);
            }

            return new PremiumMember(ID, fullName, age, subscriptionDate, trainers, vipLevel);
        } else {
            throw new InvalidInputException("Wrong member choice.");
        }
    }

    private static Trainer readTrainer(String message, int indentLevel) {
        System.out.println(message);
        String info = readString(indent.repeat(indentLevel) + "Select a trainer type (Personal/Group): ");

        int ID = readInt(indent.repeat(indentLevel) + "ID: ");
        String fullName = readString(indent.repeat(indentLevel) + "Full name: ");
        int age = readInt(indent.repeat(indentLevel) + "Age: ");
        String specialization = readString(indent.repeat(indentLevel) + "Specialization: ");

        if (info.equalsIgnoreCase("personal")) {
            Member member = readMember("Member: ", indentLevel + 1);

            return new PersonalTrainer(ID, fullName, age, specialization, member);
        } else if (info.equalsIgnoreCase("group")) {
            int count = readInt("How many members? ");
            Member[] members = new Member[count];

            for (int i = 0; i < count; i++) {
                members[i] = readMember(String.format("%sMember %d: ", indent.repeat(indentLevel - 1), i + 1), indentLevel + 1);
            }

            return new GroupTrainer(ID, fullName, age, specialization, members);

        } else {
            throw new InvalidInputException("Wrong trainer choice.");
        }
    }
}

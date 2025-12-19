package com.example.gymtraining;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Gym
        System.out.println("------------GYM-------------");
        System.out.print("\nInput a name for a gym: ");
        String name = input.nextLine();

        System.out.print("Input an address for a gym: ");
        String address = input.nextLine();

        Gym gym = new Gym(name, address, new Trainer[]{
                new Trainer(1, "Abenov Maksat", 30, "Weight loss"),
                new Trainer(2, "Malikov Dastan", 40, "Bodybuilding"),
                new Trainer(3, "Aidynuly Makhanbet", 20, "Bodybuilding")
        }, new Member[]{
                new Member(1, "Amangeldiyev Ilyas", 18, "10.12.2025"),
                new Member(2, "Dautov Fariddin", 17, "07.12.2025"),
                new Member(3, "Yeskendirov Aidyn", 18, "12.12.2025")
        });
        System.out.println(gym);

        System.out.println("----------GETTERS-----------\n");

        System.out.println("Name: " + gym.getName());
        System.out.println("Address: " + gym.getAddress());
        System.out.println(gym.getTrainers());
        System.out.println(gym.getMembers());

        System.out.println("----------SETTERS----------");

        gym.setName("Champion Fitness");
        gym.setAddress("Al-Farabi St., 10");
        System.out.println(gym);
        System.out.println("-----------------------------");

        System.out.println("-----------METHODS----------");

        gym.addMember(new Member(4, "Yerzhanov Shakriyar", 18, "05.12.2025"));
        System.out.println(gym.getMembers());

        gym.employTrainer(new Trainer(4, "Aben Aibar", 25, "Weight loss"));
        System.out.println(gym.getTrainers());

        // Trainer
        System.out.println("----------TRAINER-----------");
        System.out.print("\nTrainer ID: ");
        int trainerID = input.nextInt();
        input.nextLine();

        System.out.print("Trainer's full name: ");
        String trainerFullName = input.nextLine();

        System.out.print("Trainer's age: ");
        int trainerAge = input.nextInt();
        input.nextLine();

        System.out.print("Trainer's specialization: ");
        String specialization = input.nextLine();

        Trainer trainer = new Trainer(trainerID, trainerFullName, trainerAge, specialization);
        System.out.println(trainer);
        System.out.println("------------GETTERS----------\n");

        System.out.println("ID = " + trainer.getID());
        System.out.println("fullName = " + trainer.getFullName());
        System.out.println("age = " + trainer.getAge());
        System.out.println("specialization = " + trainer.getSpecialization());
        System.out.println();
        System.out.println("------------SETTERS----------");

        trainer.setID(10);
        trainer.setFullName("Malikov Dastan");
        trainer.setAge(40);
        trainer.setSpecialization("Bodybuilding");
        System.out.println(trainer);

        System.out.println("-----------METHODS----------");
        System.out.println();
        System.out.println(trainer.isProfessional("Weight loss"));
        System.out.println(trainer.scheduleOnDay("Monday"));
        System.out.println(trainer.scheduleOnDay("March 14th"));
        System.out.println();

        // Member
        System.out.println("-----------MEMBER-----------");
        System.out.print("\nMember ID: ");
        int memberID = input.nextInt();
        input.nextLine();

        System.out.print("Member's full name: ");
        String memberFullName = input.nextLine();

        System.out.print("Member's age: ");
        int memberAge = input.nextInt();
        input.nextLine();

        System.out.print("Member's subscription date: ");
        String subscriptionDate = input.nextLine();

        Member member = new Member(memberID, memberFullName, memberAge, subscriptionDate);
        System.out.println(member);

        System.out.println("------------GETTERS----------\n");
        System.out.println("ID = " + member.getID());
        System.out.println("fullName = " + member.getFullName());
        System.out.println("age = " + member.getAge());
        System.out.println("subscriptionDate = " + member.getSubscriptionDate());
        System.out.println();

        System.out.println("------------SETTERS----------");
        member.setID(10);
        member.setFullName("Dautov Fariddin");
        member.setAge(17);
        member.setSubscriptionDate("10.12.2025");
        System.out.println(member);

        System.out.println("------------METHODS----------\n");
        System.out.println(member.getDiscount("Q4TN9CAYP6"));
        System.out.println(member.getDiscount("FDA5AKD0SD"));
        System.out.println(member.subscriptionDuration());
        System.out.println(member.hasPromocode());

        input.close();
    }
}

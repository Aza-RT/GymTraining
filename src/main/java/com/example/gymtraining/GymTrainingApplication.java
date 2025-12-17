package com.example.gymtraining;


public class GymTrainingApplication {

    public static void main(String[] args) {
        // Gym
        System.out.println("------------GYM-------------");
        Gym gym = new Gym("1FIT", "Baitursynuly St., 75", new Trainer[]{
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
        System.out.println(gym);
        System.out.println("-----------------------------");

        gym.setAddress("Al-Farabi St., 10");
        System.out.println(gym);

        System.out.println("-----------METHODS----------");

        gym.addMember(new Member(4, "Yerzhanov Shakriyar", 18, "05.12.2025"));
        System.out.println(gym.getMembers());

        gym.employTrainer(new Trainer(4, "Aben Aibar", 25, "Weight loss"));
        System.out.println(gym.getTrainers());

        // Trainer
        System.out.println("----------TRAINER-----------");
        Trainer trainer = new Trainer(1, "Abenov Maksat", 30, "Weight loss");
        System.out.println(trainer);
        System.out.println("------------GETTERS----------\n");

        System.out.println("ID = " + trainer.getID());
        System.out.println("fullName = " + trainer.getFullName());
        System.out.println("age = " + trainer.getAge());
        System.out.println("specialization = " + trainer.getSpecialization());
        System.out.println();
        System.out.println("------------SETTERS----------");

        trainer.setID(10);
        System.out.println(trainer);
        System.out.println("-----------------------------");

        trainer.setFullName("Malikov Dastan");
        System.out.println(trainer);
        System.out.println("-----------------------------");

        trainer.setAge(40);
        System.out.println(trainer);
        System.out.println("-----------------------------");

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
        Member member = new Member(1, "Amangeldiyev Ilyas", 18, "10.10.2025");
        System.out.println(member);

        System.out.println("------------GETTERS----------\n");
        System.out.println("ID = " + member.getID());
        System.out.println("fullName = " + member.getFullName());
        System.out.println("age = " + member.getAge());
        System.out.println("subscriptionDate = " + member.getSubscriptionDate());
        System.out.println();

        System.out.println("------------SETTERS----------\n");
        member.setID(10);
        System.out.println(member);
        System.out.println("-----------------------------");

        member.setFullName("Dautov Fariddin");
        System.out.println(member);
        System.out.println("-----------------------------");

        member.setAge(17);
        System.out.println(member);
        System.out.println("-----------------------------");

        member.setSubscriptionDate("10.12.2025");
        System.out.println(member);

        System.out.println("------------METHODS----------\n");
        System.out.println(member.getDiscount("Q4TN9CAYP6"));
        System.out.println(member.getDiscount("FDA5AKD0SD"));
        System.out.println(member.subscriptionDuration());
        System.out.println(member.hasPromocode());
    }
}

package com.example.gymtraining.Model;

import com.example.gymtraining.Exception.InvalidInputException;

import java.util.Random;
import java.util.Scanner;

public class PersonalTrainer extends Trainer {
    private Member client;
    private final Random random = new Random();

    public PersonalTrainer(int id, String name, int age, String specialization, Member client) {
        super(id, name, age, specialization);
        setClient(client);
    }

    public Member getClient() {
        return client;
    }

    public void setClient(Member client) {
        if (client.isValid()) {
            this.client = client;
        }
        else {
            throw new InvalidInputException("Invalid client inputted.");
        }
    }

    @Override
    public boolean isProfessional(String field) {
        return field.equalsIgnoreCase(super.specialization) && client.isValid();
    }

    public int durationOfSession() {
        switch (specialization.toLowerCase()) {
            case "weight loss" -> {return 60;}
            case "cardio" -> {return 45;}
            case "bodybuilding" -> {return 90;}
            case "fitness" -> {return 75;}
            case "yoga" -> {return 60;}
            default -> {return 40;}
        }
    }

    @Override
    public String scheduleOnDay(String day) {
        String[] hours = {"08", "09", "10", "11", "12", "13", "14", "15",
                          "16", "17", "18", "19", "20", "21"};
        String[] minutes = {"00", "15", "30", "45"};

        switch (day) {
            case "Monday" -> {return String.format("%s is free at %s:%s on %s.", fullName, hours[random.nextInt(0, 14)], minutes[random.nextInt(0,4)], day);}
            case "Tuesday" -> {return String.format("%s is free at %s:%s on %s.", fullName, hours[random.nextInt(0, 14)], minutes[random.nextInt(0,4)], day);}
            case "Wednesday" -> {return String.format("%s is free at %s:%s on %s.", fullName, hours[random.nextInt(0, 14)], minutes[random.nextInt(0,4)], day);}
            case "Thursday" -> {return String.format("%s is free at %s:%s on %s.", fullName, hours[random.nextInt(0, 14)], minutes[random.nextInt(0,4)], day);}
            case "Friday" -> {return String.format("%s is free at %s:%s on %s.", fullName, hours[random.nextInt(0, 14)], minutes[random.nextInt(0,4)], day);}
            case "Saturday" -> {return String.format("%s is free at %s:%s on %s.", fullName, hours[random.nextInt(0, 14)], minutes[random.nextInt(0,4)], day);}
            case "Sunday" -> {return String.format("%s is free at %s:%s on %s.", fullName, hours[random.nextInt(0, 14)], minutes[random.nextInt(0,4)], day);}
            default -> {return "Invalid day.";}
        }
    }

    @Override
    public String toString() {
        if (trainerID > 0 && !fullName.isEmpty() && age > 0) {
            return String.format("\nPersonalTrainer{%d, %s, %s, %s, %s}\n", trainerID, fullName, age, specialization, client.toString());
        } else {
            return "Invalid object.";
        }
    }
}

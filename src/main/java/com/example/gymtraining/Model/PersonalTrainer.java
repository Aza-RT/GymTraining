package com.example.gymtraining.Model;

import com.example.gymtraining.Exception.InvalidInputException;

public class PersonalTrainer extends Trainer {
    private Member client;

    public PersonalTrainer(int id, String name, int age, String specialization, Member client) {
        super(id, name, age, specialization);
        setClient(client);
    }

    @Override
    public boolean isProfessional(String field) {
        return super.isProfessional(field) && client.isValid();
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
    public String toString() {
        if (trainerID > 0 && !fullName.isEmpty() && age > 0) {
            return String.format("\nPersonalTrainer{%d, %s, %s, %s, %s}\n", trainerID, fullName, age, specialization, client.toString());
        } else {
            return "Invalid object.";
        }
    }
}

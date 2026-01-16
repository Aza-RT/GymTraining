package com.example.gymtraining.Model;

import com.example.gymtraining.Exception.InvalidInputException;

import java.util.Random;

public abstract class Trainer {
    protected int trainerID;
    protected String fullName;
    protected int age;
    protected String specialization;

    public Trainer(int trainerID, String fullName, int age, String specialization) {
        setID(trainerID);
        setFullName(fullName);
        setAge(age);
        this.specialization = specialization;
    }

    public int getID() {
        return trainerID;
    }

    public String getFullName() {
        return fullName;
    }

    public int getAge() {
        return age;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setID(int trainerID) {
        if (trainerID > 0) {
            this.trainerID = trainerID;
        } else {
            throw new InvalidInputException("Invalid ID inputted.");
        }
    }

    public void setFullName(String fullName) {
        if (!fullName.trim().isEmpty()) {
            this.fullName = fullName;
        } else {
            throw new InvalidInputException("Invalid name inputted.");
        }
    }

    public void setAge(int age) {
        if (age > 0) {
            this.age = age;
        } else {
            throw new InvalidInputException("Invalid age inputted.");
        }
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public abstract String scheduleOnDay(String day);
    public abstract boolean isProfessional(String field);

    public boolean isValid() {
        return trainerID != 0 && !fullName.isEmpty() && age > 0;
    }

    @Override
    public String toString() {
        if (trainerID > 0 && !fullName.isEmpty() && age > 0) {
            return String.format("\nTrainer{%d, %s, %s, %s}\n", trainerID, fullName, age, specialization);
        } else {
            return "Invalid object.";
        }
    }
}

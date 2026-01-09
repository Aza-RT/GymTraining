package com.example.gymtraining;

import java.util.Random;

public class Trainer {
    protected int trainerID;
    protected String fullName;
    protected int age;
    protected String specialization;
    private final Random random = new Random();

    Trainer(int trainerID, String fullName, int age, String specialization) {
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
            this.trainerID = 0;
        }
    }

    public void setFullName(String fullName) {
        if (!fullName.trim().isEmpty()) {
            this.fullName = fullName;
        } else {
            this.fullName = "";
        }
    }

    public void setAge(int age) {
        if (age > 0) {
            this.age = age;
        } else {
            this.age = 0;
        }
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String scheduleOnDay(String day) {
        String[] hours = {"08", "09", "10", "11", "12", "13", "14", "15",
                          "16", "17", "18", "19", "20", "21"};
        String[] minutes = {"00", "15", "30", "45"};

        switch (day) {
            case "Monday" -> {return String.format("%s is free at %s:%s on %s.", fullName, hours[random.nextInt(0, 24)], minutes[random.nextInt(0,4)], day);}
            case "Tuesday" -> {return String.format("%s is free at %s:%s on %s.", fullName, hours[random.nextInt(0, 24)], minutes[random.nextInt(0,4)], day);}
            case "Wednesday" -> {return String.format("%s is free at %s:%s on %s.", fullName, hours[random.nextInt(0, 24)], minutes[random.nextInt(0,4)], day);}
            case "Thursday" -> {return String.format("%s is free at %s:%s on %s.", fullName, hours[random.nextInt(0, 24)], minutes[random.nextInt(0,4)], day);}
            case "Friday" -> {return String.format("%s is free at %s:%s on %s.", fullName, hours[random.nextInt(0, 24)], minutes[random.nextInt(0,4)], day);}
            case "Saturday" -> {return String.format("%s is free at %s:%s on %s.", fullName, hours[random.nextInt(0, 24)], minutes[random.nextInt(0,4)], day);}
            case "Sunday" -> {return String.format("%s is free at %s:%s on %s.", fullName, hours[random.nextInt(0, 24)], minutes[random.nextInt(0,4)], day);}
            default -> {return "Invalid day.";}
        }
    }

    public boolean isProfessional(String field) {
        return field.equalsIgnoreCase(specialization);
    }

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

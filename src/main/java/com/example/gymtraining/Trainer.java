package com.example.gymtraining;

public class Trainer {
    private int trainerID;
    private String fullName;
    private int age;
    private String specialization;

    Trainer(int ID, String fullName, int age, String specialization) {
        setID(ID);
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

    public void setID(int ID) {
        if (ID > 0) {
            this.trainerID = ID;
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
        switch (day) {
            case "Monday" -> {return String.format("%s is free on 13:00 and 16:00 on %s.", fullName, day);}
            case "Tuesday" -> {return String.format("%s is free on 11:30 and 15:00 on %s.", fullName, day);}
            case "Wednesday" -> {return String.format("%s is free on 12:00 and 17:30 on %s.", fullName, day);}
            case "Thursday" -> {return String.format("%s is free on 12:30 and 17:00 on %s.", fullName, day);}
            case "Friday" -> {return String.format("%s is free on 9:30 and 15:00 on %s.", fullName, day);}
            case "Saturday" -> {return String.format("%s is free on 10:00 and 18:30 on %s.", fullName, day);}
            case "Sunday" -> {return String.format("%s is free on 11:00 and 19:00 on %s.", fullName, day);}
            default -> {return "Invalid day.";}
        }
    }

    public boolean isProfessional(String field) {
        return field.equalsIgnoreCase(specialization);
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

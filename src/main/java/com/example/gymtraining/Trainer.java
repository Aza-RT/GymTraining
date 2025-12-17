package com.example.gymtraining;

public class Trainer {
    private int ID;
    private String fullName;
    private int age;
    private String specialization;

    Trainer(int ID, String fullName, int age, String specialization) {
        this.ID = ID;
        this.fullName = fullName;
        this.age = age;
        this.specialization = specialization;
    }

    public int getID() {
        return ID;
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
        this.ID = ID;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setAge(int age) {
        this.age = age;
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
        return String.format("\nTrainer ID: %d\nTrainer's full name: %s\nTrainer's age: %s\nTrainer's specialization: %s\n", ID, fullName, age, specialization);
    }
}

package com.example.gymtraining.Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class GroupTrainer extends Trainer {
    private ArrayList<Member> clients = new ArrayList<>();
    private final Random random = new Random();

    public GroupTrainer(int id, String name, int age, String specialization, Member[] clients) {
        super(id, name, age, specialization);
        setClients(clients);
    }

    public void setClients(Member[] clients) {
        for (Member client: clients) {
            if (client.isValid()) {
                this.clients.add(client);
            }
        }
    }

    public ArrayList<Member> getClients() {
        return clients;
    }

    @Override
    public boolean isProfessional(String field) {
        return field.equalsIgnoreCase(super.specialization) && clients.size() > 5;
    }

    @Override
    public String scheduleOnDay(String day) {
        String[] hours = {"08", "09", "10", "11", "12", "13", "14", "15",
                          "16", "17", "18", "19", "20", "21"};
        String[] minutes = {"00", "15", "30", "45"};

        switch (day) {
            case "Monday" -> {return String.format("%s has a group session at %s:%s on %s.", fullName, hours[random.nextInt(0, 14)], minutes[random.nextInt(0,4)], day);}
            case "Tuesday" -> {return String.format("%s has a group session at %s:%s on %s.", fullName, hours[random.nextInt(0, 14)], minutes[random.nextInt(0,4)], day);}
            case "Wednesday" -> {return String.format("%s is not free on %s.", fullName, day);}
            case "Thursday" -> {return String.format("%s has a group session at %s:%s on %s.", fullName, hours[random.nextInt(0, 14)], minutes[random.nextInt(0,4)], day);}
            case "Friday" -> {return String.format("%s is not free on %s.", fullName, day);}
            case "Saturday" -> {return String.format("%s has a group session at %s:%s on %s.", fullName, hours[random.nextInt(0, 14)], minutes[random.nextInt(0,4)], day);}
            case "Sunday" -> {return String.format("%s has a group session at %s:%s on %s.", fullName, hours[random.nextInt(0, 14)], minutes[random.nextInt(0,4)], day);}
            default -> {return "Invalid day.";}
        }
    }

    public boolean hasAvailableSpots() {
        return clients.size() <= 20;
    }

    @Override
    public String toString() {
        if (trainerID > 0 && !fullName.isEmpty() && age > 0) {
            ArrayList<String> clientsString = new ArrayList<>();
            for(Member member: clients) {
                clientsString.add(member.toString());
            }

            return String.format("\nGroupTrainer{%d, %s, %s, %s, %s}\n", trainerID, fullName, age, specialization, Arrays.toString(clientsString.toArray(new String[0])));
        } else {
            return "Invalid object.";
        }
    }
}

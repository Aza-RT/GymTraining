package com.example.gymtraining.Model;

import java.util.ArrayList;
import java.util.Arrays;

public class GroupTrainer extends Trainer {
    private ArrayList<Member> clients = new ArrayList<>();

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
        return super.isProfessional(field) && clients.size() > 5;
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

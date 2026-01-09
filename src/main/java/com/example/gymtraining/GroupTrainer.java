package com.example.gymtraining;

import java.util.ArrayList;

public class GroupTrainer extends Trainer {
    private ArrayList<Member> clients;

    GroupTrainer(int id, String name, int age, String specialization, Member[] clients) {
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
}

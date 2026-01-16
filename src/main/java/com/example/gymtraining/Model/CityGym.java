package com.example.gymtraining.Model;

import com.example.gymtraining.Exception.InvalidInputException;

import java.util.ArrayList;
import java.util.Arrays;

public class CityGym extends Gym {

    private int branches;
    private boolean parking;
    private final ArrayList<String> specializations = new ArrayList<>(Arrays.stream(new String[]{"Weightlifting", "Bodybuilding", "Fitness", "Yoga"}).toList());

    public CityGym(String name, String address, int branches, boolean parking) {
        super(name, address, new Trainer[]{}, new Member[]{});
        setBranches(branches);
        setParking(parking);
    }

    public int getBranches() {
        return branches;
    }

    public void setBranches(int branches) {
        if (branches > 0) {
            this.branches = branches;
        } else {
            throw new InvalidInputException("Invalid branch inputted.");
        }
    }

    public void setParking(boolean parking) {
        this.parking = parking;
    }

    public boolean getParking() {
        return parking;
    }

    @Override
    public void addMember(Member member) {
        if (member.isValid()) {
            this.members.add(member);
        }
    }

    @Override
    public void employTrainer(Trainer trainer) {
        if (trainer.isValid() && specializations.contains(trainer.getSpecialization())) {
            this.trainers.add(trainer);
        }
    }

    @Override
    public String toString() {
        if (!name.trim().isEmpty() && !address.trim().isEmpty()) {
            String trainersString = Arrays.toString(trainers.toArray(new Trainer[0]));
            String membersString = Arrays.toString(members.toArray(new Member[0]));
            return String.format("\nCityGym{%s, %s, %s, %s, %b, %d}\n", name, address, trainersString, membersString, parking, branches);
        } else {
            return "Invalid object.";
        }
    }
}

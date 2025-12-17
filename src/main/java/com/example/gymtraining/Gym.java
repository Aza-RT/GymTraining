package com.example.gymtraining;

import java.util.Arrays;
import java.util.ArrayList;

public class Gym {
    private String name;
    private String address;
    private ArrayList<Trainer> trainers;
    private ArrayList<Member> members;

    Gym(String name, String address, Trainer[] trainers, Member[] members) {
        this.name = name;
        this.address = address;
        this.trainers = new ArrayList<>(Arrays.stream(trainers).toList());
        this.members = new ArrayList<>(Arrays.stream(members).toList());
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getTrainers() {
        String trainersString = Arrays.toString(trainers.toArray(new Trainer[0]));
        trainersString = trainersString.substring(1, trainersString.length() - 1);
        return String.format("\nTrainers of %s:\n", name) + trainersString;
    }

    public String getMembers() {
        String membersString = Arrays.toString(members.toArray(new Member[0]));
        membersString = membersString.substring(1, membersString.length() - 1);
        return String.format("\nMembers of %s:\n", name) + membersString;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setTrainers(Trainer[] trainers) {
        this.trainers = new ArrayList<>(Arrays.stream(trainers).toList());
    }

    public void setMembers(Member[] members) {
        this.members = new ArrayList<>(Arrays.stream(members).toList());
    }

    public void addMember(Member member) {
        this.members.add(member);
    }

    public void employTrainer(Trainer trainer) {
        this.trainers.add(trainer);
    }

    @Override
    public String toString() {
        String trainersString = Arrays.toString(trainers.toArray(new Trainer[0]));
        String membersString = Arrays.toString(members.toArray(new Member[0]));
        return String.format("\nThe gym center %s is located on %s.\n", name, address, trainersString, membersString);
    }
}

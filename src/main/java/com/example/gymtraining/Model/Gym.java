package com.example.gymtraining.Model;

import com.example.gymtraining.Exception.InvalidInputException;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;

public class Gym {
    protected String name;
    protected String address;
    protected ArrayList<Trainer> trainers;
    protected ArrayList<Member> members;

    public Gym(String name, String address, Trainer[] trainers, Member[] members) {
        setName(name);
        setAddress(address);
        setTrainers(trainers);
        setMembers(members);
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
        if (!name.trim().isEmpty()) {
            this.name = name;
        } else {
            throw new InvalidInputException("Invalid name inputted.");
        }
    }

    public void setAddress(String address) {
        if (!address.trim().isEmpty()) {
            this.address = address;
        } else {
            throw new InvalidInputException("Invalid address inputted.");
        }
    }

    public void setTrainers(Trainer[] trainers) {
        this.trainers = new ArrayList<>(Arrays.stream(trainers).toList());

        ArrayList<Integer> invalidTrainersIndices = new ArrayList<>();
        for (int i = 0; i < trainers.length; i++) {
            Trainer trainer = trainers[i];

            if (trainer.getID() == 0 || trainer.getFullName().isEmpty() || trainer.getAge() == 0) {
                invalidTrainersIndices.add(i);
            }
        }

        Collections.reverse(invalidTrainersIndices);

        for (int idx: invalidTrainersIndices) {
            this.trainers.remove(idx);
        }
    }

    public void setMembers(Member[] members) {
        this.members = new ArrayList<>(Arrays.stream(members).toList());

        ArrayList<Integer> invalidMembersIndices = new ArrayList<>();
        for (int i = 0; i < members.length; i++) {
            Member member = members[i];

            if (member.getID() == 0 || member.getFullName().isEmpty() || member.getAge() == 0) {
                invalidMembersIndices.add(i);
            }
        }

        Collections.reverse(invalidMembersIndices);

        for (int idx: invalidMembersIndices) {
            this.trainers.remove(idx);
        }
    }

    public void addMember(Member member) {
        if (member.isValid()) {
            this.members.add(member);
        }
    }

    public void employTrainer(Trainer trainer) {
        if (trainer.isValid()) {
            this.trainers.add(trainer);
        }
    }

    @Override
    public String toString() {
        if (!name.trim().isEmpty() && !address.trim().isEmpty()) {
            String trainersString = Arrays.toString(trainers.toArray(new Trainer[0]));
            String membersString = Arrays.toString(members.toArray(new Member[0]));
            return String.format("\nGym{%s, %s, %s, %s}\n", name, address, trainersString, membersString);
        } else {
            return "Invalid object.";
        }
    }
}

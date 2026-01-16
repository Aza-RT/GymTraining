package com.example.gymtraining.Model;

import com.example.gymtraining.Exception.InvalidInputException;

import java.util.ArrayList;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class PremiumMember extends Member {
    private ArrayList<Trainer> availableTrainers;
    private int vipLevel;

    public PremiumMember(int id, String fullName, int age, String joinDate, Trainer[] availableTrainers, int vipLevel) {
        super(id, fullName, age, joinDate);
        setAvailableTrainers(availableTrainers);
        setVipLevel(vipLevel);
    }

    public int getVipLevel() {
        return vipLevel;
    }

    public void setVipLevel(int vipLevel) {
        if (vipLevel >= 1 && vipLevel <= 5) {
            this.vipLevel = vipLevel;
        }
        else {
            throw new InvalidInputException("Invalid level inputted.");
        }
    }

    public ArrayList<Trainer> getAvailableTrainers() {
        return availableTrainers;
    }

    public void setAvailableTrainers(Trainer[] trainers) {
        for(Trainer trainer: trainers) {
            if (trainer.isValid()) {
                this.availableTrainers.add(trainer);
            }
        }
    }

    @Override
    public String subscriptionDuration() {
        LocalDate today = LocalDate.now();
        LocalDate endOfSubscription = LocalDate.parse("01.01.2027", dateFormat);
        int days = (int)(ChronoUnit.DAYS.between(today, endOfSubscription));
        return String.format("You still have %d days left till the end of your subscription.", days);
    }
}

package com.example.gymtraining.Model;

import com.example.gymtraining.Exception.InvalidInputException;

public class TownGym extends Gym {

    private boolean familyDiscount;
    private int capacity;

    public TownGym(String name, String address, boolean familyDiscount, int capacity) {
        super(name, address, new Trainer[]{}, new Member[]{});
        setCapacity(capacity);
        this.familyDiscount = familyDiscount;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        if (capacity > 0) {
            this.capacity = capacity;
        } else {
            throw new InvalidInputException("Invalid capacity inputted.");
        }
    }

    public boolean getFamilyDiscount() {
        return familyDiscount;
    }

    public void setFamilyDiscount(boolean familyDiscount) {
        this.familyDiscount = familyDiscount;
    }


    @Override
    public void employTrainer(Trainer trainer) {
        super.employTrainer(trainer);
        System.out.println("TownGym local trainer employed");
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" TownGym{familyDiscount=&b, capacity=%d}", familyDiscount, capacity);
    }
}

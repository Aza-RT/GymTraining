package com.example.gymtraining;

public class CityGym extends Gym {

    private int branches;
    private boolean parking;

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
            this.branches = 0;
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
        if (member instanceof PremiumMember) {
            super.addMember(member);
        }
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" CityGym{branches=%d, parking=%b}", branches, parking);
    }
}

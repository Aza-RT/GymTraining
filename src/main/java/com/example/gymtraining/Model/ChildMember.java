package com.example.gymtraining.Model;

import com.example.gymtraining.Exception.InvalidInputException;

public class ChildMember extends Member {
    private String parentName;

    public ChildMember(int id, String fullName, int age, String joinDate, String parentName) {
        super(id, fullName, age, joinDate);
        setParentName(parentName);
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        if (parentName != null && !parentName.isEmpty()) {
            this.parentName = parentName;
        }
        else {
            throw new InvalidInputException("Invalid parent's name inputted.");
        }
    }

    @Override
    public String subscriptionDuration() {
        return "Call a parent to get info.";
    }

    public void activateChildDiscount() {
        super.getDiscount("FGS4SK1M2D");
    }

    @Override
    public String toString() {
        if (memberID > 0 && !fullName.isEmpty() && age > 0) {
            return String.format("\nChildMember{%d, %s, %s, %s, %s}\n", memberID, fullName, age, subscriptionDate.format(dateFormat), parentName);
        } else {
            return "Invalid object.";
        }
    }
}

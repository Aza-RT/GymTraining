package com.example.gymtraining;

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
            this.parentName = "";
        }
    }

    @Override
    public String subscriptionDuration() {
        return "Call a parent to get info.";
    }

    public void activateChildDiscount() {
        super.getDiscount("FGS4SK1M2D");
    }
}

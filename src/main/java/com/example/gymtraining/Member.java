package com.example.gymtraining;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Member {
    private int ID;
    private String fullName;
    private int age;
    private LocalDate subscriptionDate;
    private int pricePerYear = 15_000;
    private int promocodePercent = 0;
    private final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    Member(int ID, String fullName, int age, String subscriptionDate) {
        this.ID = ID;
        this.fullName = fullName;
        this.age = age;
        this.subscriptionDate = LocalDate.parse(subscriptionDate, dateFormat);
    }

    public int getID() {
        return ID;
    }

    public String getFullName() {
        return fullName;
    }

    public int getAge() {
        return age;
    }

    public String getSubscriptionDate() {
        return subscriptionDate.format(dateFormat);
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setSubscriptionDate(String subscriptionDate) {
        this.subscriptionDate = LocalDate.parse(subscriptionDate, dateFormat);
    }

    public String calculateDiscount(int userPromocodePercent) {
        if(userPromocodePercent > promocodePercent) {
            this.pricePerYear *= (1 - userPromocodePercent * 0.01);
            promocodePercent = userPromocodePercent;
            return String.format("Promocode active. The payment has been decreased by %d%%.", userPromocodePercent);
        } else {
            return "Promocode with higher discount already active.";
        }
    }

    public String getDiscount(String promocode) {
        String promocode20Percent = "FGS4SK1M2D";
        String promocode30Percent = "Q4TN9CAYP6";
        String promocode50Percent = "FDA5AKD0SD";

        if(promocode.equals(promocode20Percent)) {
            return calculateDiscount(20);
        } else if(promocode.equals(promocode30Percent)) {
            return calculateDiscount(30);
        } else if(promocode.equals(promocode50Percent)) {
            return calculateDiscount(50);
        } else {
            return "Invalid promocode.";
        }
    }

    public String subscriptionDuration() {
        LocalDate today = LocalDate.now();
        LocalDate endOfSubscription = subscriptionDate.plusMonths(1);

        if(today.isBefore(endOfSubscription)) {
            int days = (int)(ChronoUnit.DAYS.between(today, endOfSubscription));
            return String.format("You still have %d days left till the end of your subscription.", days);
        } else {
            return "Your subscription has been deprecated.";
        }
    }

    public String hasPromocode() {
        if(promocodePercent != 0) {
            return String.format("%s has %d%% discount promocode.", fullName, promocodePercent);
        } else {
            return String.format("%s does not have a discount promocode.", fullName);
        }
    }

    @Override
    public String toString() {
        return String.format("\nMember ID: %d\nMember's full name: %s\nMember's age: %s\nWhen did the member buy the subscription: %s\n", ID, fullName, age, subscriptionDate.format(dateFormat));
    }
}

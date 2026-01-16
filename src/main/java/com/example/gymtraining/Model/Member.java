package com.example.gymtraining.Model;

import com.example.gymtraining.Exception.InvalidInputException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Member {
    protected int memberID;
    protected String fullName;
    protected int age;
    protected LocalDate subscriptionDate;
    protected int pricePerYear = 15_000;
    protected int promocodePercent = 0;
    protected final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public Member(int ID, String fullName, int age, String subscriptionDate) {
        setID(ID);
        setFullName(fullName);
        setAge(age);
        setSubscriptionDate(subscriptionDate);
    }

    public int getID() {
        return memberID;
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

    public void setID(int memberID) {
        if (memberID > 0) {
            this.memberID = memberID;
        }
        else {
            throw new InvalidInputException("Invalid ID inputted.");
        }
    }

    public void setFullName(String fullName) {
        if (!fullName.trim().isEmpty()) {
            this.fullName = fullName;
        } else {
            throw new InvalidInputException("Invalid name inputted.");
        }
    }

    public void setAge(int age) {
        if (age > 0) {
            this.age = age;
        } else {
            throw new InvalidInputException("Invalid age inputted.");
        }
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

    public boolean isValid() {
        return memberID != 0 && !fullName.isEmpty() && age > 0;
    }

    @Override
    public String toString() {
        if (memberID > 0 && !fullName.isEmpty() && age > 0) {
            return String.format("\nMember{%d, %s, %s, %s}\n", memberID, fullName, age, subscriptionDate.format(dateFormat));
        } else {
            return "Invalid object.";
        }
    }
}

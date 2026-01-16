package com.example.gymtraining.Model;

public interface Discount {
    public String getDiscount(String promocode);
    public String calculateDiscount(int userPromocodePercent);
}

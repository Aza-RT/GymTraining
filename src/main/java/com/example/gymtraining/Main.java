package com.example.gymtraining;
import com.example.gymtraining.Menu.*;
import com.example.gymtraining.Database.*;
import com.example.gymtraining.Model.*;

public class Main {
    public static void main(String[] args) {
        Menu menu = new GymMenu();

        menu.run();
    }
}
package ru.mts.hw_3.animal.impl;

import java.math.BigDecimal;

public class Wolf extends Predator {
    public Wolf(String breed, BigDecimal cost) {
        this.name = "wolf";
        this.breed = breed;
        this.cost = cost;
    }
}

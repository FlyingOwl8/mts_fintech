package ru.mts.hw_3.animal.impl;

import java.math.BigDecimal;

public class Shark extends Predator {
    public Shark(String breed, BigDecimal cost) {
        this.name = "shark";
        this.breed = breed;
        this.cost = cost;
    }
}

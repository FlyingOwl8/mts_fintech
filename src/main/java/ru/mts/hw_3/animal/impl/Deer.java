package ru.mts.hw_3.animal.impl;

import java.math.BigDecimal;

public class Deer extends Prey {
    public Deer(String breed, BigDecimal cost) {
        this.name = "deer";
        this.breed = breed;
        this.cost = cost;
    }
}

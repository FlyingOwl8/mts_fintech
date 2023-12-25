package ru.mts.hw_3.animal.impl;

import java.math.BigDecimal;

public class Rabbit extends Prey {
    public Rabbit(String breed, BigDecimal cost) {
        this.name = "rabbit";
        this.breed = breed;
        this.cost = cost;
    }
}

package ru.mts.hw_6.animal.impl;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Wolf extends Predator {
    public Wolf(String breed, BigDecimal cost, LocalDate birthDate) {
        validateArguments(breed, cost, birthDate);
        this.name = "wolf";
        this.breed = breed;
        this.cost = cost;
        this.birthDate = birthDate;
    }
}

package ru.mts.hw.animal.impl;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Wolf extends Predator {
    public Wolf(String name, String breed, BigDecimal cost, LocalDate birthDate) {
        validateArguments(breed, cost, birthDate);
        this.name = name;
        this.breed = breed;
        this.cost = cost;
        this.birthDate = birthDate;
    }
}

package ru.mts.hw_7.animal.impl;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Deer extends Prey {
    public Deer(String breed, BigDecimal cost, LocalDate birthDate) {
        validateArguments(breed, cost, birthDate);
        this.name = "deer";
        this.breed = breed;
        this.cost = cost;
        this.birthDate = birthDate;
    }
}

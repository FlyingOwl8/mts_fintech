package ru.mts.hw_7.animal.impl;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Shark extends Predator {
    public Shark(String breed, BigDecimal cost, LocalDate birthDate) {
        validateArguments(breed, cost, birthDate);
        this.name = "shark";
        this.breed = breed;
        this.cost = cost;
        this.birthDate = birthDate;
    }
}

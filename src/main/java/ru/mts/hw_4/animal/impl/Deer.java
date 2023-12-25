package ru.mts.hw_4.animal.impl;

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

    private void validateArguments(String breed, BigDecimal cost, LocalDate birthDate) {
        if (breed == null || cost == null || birthDate == null) {
            throw new IllegalArgumentException("Any field can't be null");
        }
        if (breed.isBlank()) {
            throw new IllegalArgumentException("Breed can't be an empty string");
        }
        if (cost.compareTo(BigDecimal.valueOf(0)) < 0) {
            throw new IllegalArgumentException("Cost can't be negative");
        }
    }
}

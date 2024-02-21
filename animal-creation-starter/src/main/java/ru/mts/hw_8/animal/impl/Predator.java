package ru.mts.hw_8.animal.impl;

import java.time.format.DateTimeFormatter;

import static java.math.RoundingMode.HALF_UP;

public abstract class Predator extends AbstractAnimal {

    public Predator() {
        character = "aggressive";
    }

    @Override
    public String toString() {
        return "Predator name: " + getName() + ", Predator breed: " + getBreed() + ", " + "Cost: " + getCost().setScale(2, HALF_UP) + ", Predator character: " + getCharacter() + ", Predator birthdate: " + getBirthDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }
}

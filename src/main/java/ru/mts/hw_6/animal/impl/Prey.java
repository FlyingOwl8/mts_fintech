package ru.mts.hw_6.animal.impl;

import java.time.format.DateTimeFormatter;

import static java.math.RoundingMode.HALF_UP;

public abstract class Prey extends AbstractAnimal {

    public Prey() {
        character = "peaceful";
    }

    @Override
    public String toString() {
        return "Prey name: " + getName() + ", Prey breed: " + getBreed() + ", " + "Cost: " + getCost().setScale(2, HALF_UP) + ", Prey character: " + getCharacter() + ", Prey birthdate: " + getBirthDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }
}

package ru.mts.hw_3;

import java.math.BigDecimal;

import static java.math.RoundingMode.HALF_UP;

public class Prey extends AbstractAnimal {

    public Prey() {
        character = "peaceful";
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getBreed() {
        return breed;
    }

    @Override
    public BigDecimal getCost() {
        return cost;
    }

    @Override
    public String getCharacter() {
        return character;
    }

    @Override
    public String toString() {
        return "Prey name: " + getName() + ", Prey breed: " + getBreed() + ", " + "Cost: " + getCost().setScale(2, HALF_UP) + ", Prey character: " + getCharacter();
    }
}

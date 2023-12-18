package ru.mts.hw_3.animal.impl;

import static java.math.RoundingMode.HALF_UP;

public abstract class Predator extends AbstractAnimal {

    public Predator() {
        character = "aggressive";
    }

    @Override
    public String toString() {
        return "Predator name: " + getName() + ", Predator breed: " + getBreed() + ", " + "Cost: " + getCost().setScale(2, HALF_UP) + ", Predator character: " + getCharacter();
    }
}

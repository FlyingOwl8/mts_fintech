package ru.mts.hw_3.animal.impl;

import ru.mts.hw_3.animal.Animal;

import java.math.BigDecimal;

public abstract class AbstractAnimal implements Animal {
    protected String name;
    protected String breed;
    protected String character;
    protected BigDecimal cost;

    public String getName() {
        return name;
    }

    public String getBreed() {
        return breed;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public String getCharacter() {
        return character;
    }
}

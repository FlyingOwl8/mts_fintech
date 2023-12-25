package ru.mts;

import ru.mts.hw_3.service.impl.CreateAnimalServiceImpl;

public class Main {
    public static void main(String[] args) {
        CreateAnimalServiceImpl service = new CreateAnimalServiceImpl();
        service.createAnimals();
        service.createAnimals(5);
    }
}
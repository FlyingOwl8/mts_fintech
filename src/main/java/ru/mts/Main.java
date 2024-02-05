package ru.mts;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.mts.hw_6.animal.Animal;
import ru.mts.hw_6.config.Config;
import ru.mts.hw_6.repository.AnimalsRepository;
import ru.mts.hw_6.service.CreateAnimalService;
import ru.mts.hw_6.service.impl.CreateAnimalServiceImpl;


public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        AnimalsRepository animalsRepository = context.getBean(AnimalsRepository.class);

        String[] animalNamesArray = animalsRepository.findLeapYearNames();
        System.out.println("Животные, родившиеся в високосный год:");
        for (String animalName : animalNamesArray) {
            System.out.println(animalName);
        }
        System.out.println("-----");
        Animal[] olderAnimalsArray = animalsRepository.findOlderAnimals(1);
        System.out.println("Животные старше 1 года:");
        for (Animal animal : olderAnimalsArray) {
            System.out.println(animal);
        }
        System.out.println("-----");
        System.out.println("Поиск одинаковых животных:");
        animalsRepository.printDuplicates();

        System.out.println("Bean прототип");
        CreateAnimalService createService = context.getBean(CreateAnimalServiceImpl.class);
        CreateAnimalService anotherCreateService = context.getBean(CreateAnimalServiceImpl.class);
        System.out.println(createService.getAnimal());
        System.out.println(anotherCreateService.getAnimal());
        System.out.println("-----");
    }
}
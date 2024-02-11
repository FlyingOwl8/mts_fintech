package ru.mts.hw_7.sheduled;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.mts.hw_7.animal.Animal;
import ru.mts.hw_7.repository.AnimalsRepository;


@Component
public class SheduledTask {
    AnimalsRepository animalsRepository;

    public SheduledTask(AnimalsRepository animalsRepository) {
        this.animalsRepository = animalsRepository;
    }

    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
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
    }
}

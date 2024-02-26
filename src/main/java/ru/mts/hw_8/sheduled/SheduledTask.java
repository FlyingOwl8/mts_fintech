package ru.mts.hw_8.sheduled;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.mts.hw_8.animal.Animal;
import ru.mts.hw_8.repository.AnimalsRepository;

import java.time.LocalDate;
import java.util.Map;


@Component
@RequiredArgsConstructor
public class SheduledTask {
    private final AnimalsRepository animalsRepository;

    @Scheduled(fixedRate = 10000)
    public void reportCurrentTime() {
        Map<String, LocalDate> animalNamesArray = animalsRepository.findLeapYearNames();
        System.out.println("Животные, родившиеся в високосный год:");
        for (String animalName : animalNamesArray.keySet()) {
            System.out.println(animalName);
        }
        System.out.println("-----");
        Map<Animal, Integer> olderAnimalsArray = animalsRepository.findOlderAnimals(1);
        System.out.println("Животные старше 1 года:");
        for (Animal animal : olderAnimalsArray.keySet()) {
            System.out.println(animal);
        }
        System.out.println("-----");
        System.out.println("Поиск одинаковых животных:");
        animalsRepository.printDuplicates();
    }
}

package ru.mts.hw.sheduled;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.mts.hw.animal.Animal;
import ru.mts.hw.animal.impl.Wolf;
import ru.mts.hw.exception.MyCheckedException;
import ru.mts.hw.exception.MyUncheckedException;
import ru.mts.hw.repository.AnimalsRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;


@Component
@RequiredArgsConstructor
@Slf4j
public class SheduledTask {
    private final AnimalsRepository animalsRepository;

    @Scheduled(fixedRate = 10000)
    public void reportCurrentTime() {
        try {
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
            System.out.println("-----");

            Animal wolf1 = new Wolf("wolf2", "gray wolf", BigDecimal.valueOf(10),
                    LocalDate.of(LocalDate.now().getYear() - 9, 1, 1));
            Animal wolf2 = new Wolf("wolf3", "gray wolf", BigDecimal.valueOf(15),
                    LocalDate.of(LocalDate.now().getYear() - 5, 1, 1));
            List<Animal> wolfList = List.of(wolf1, wolf2);
            List<Animal> foundAnimals = animalsRepository.findMinConstAnimals(wolfList);
            for (Animal animal : foundAnimals) {
                System.out.println(animal);
            }
            System.out.println("-----");
        } catch (MyUncheckedException e) {
            log.error("My unchecked exception: " + e.getMessage());
        } catch (MyCheckedException e) {
            log.error("My checked exception: " + e.getMessage());
        } catch (Exception e) {
            log.error("Exception: " + e.getMessage());
        }
    }
}

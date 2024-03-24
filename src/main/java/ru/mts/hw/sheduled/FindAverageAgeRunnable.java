package ru.mts.hw.sheduled;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.mts.hw.animal.Animal;
import ru.mts.hw.animal.impl.Wolf;
import ru.mts.hw.repository.AnimalsRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class FindAverageAgeRunnable implements Runnable {
    private final AnimalsRepository animalsRepository;
    private static final List<Animal> wolfList = List.of(
            new Wolf("wolf1", "gray wolf", BigDecimal.valueOf(15),
                    LocalDate.of(LocalDate.now().getYear() - 9, 1, 1)),
            new Wolf("wolf1", "gray wolf", BigDecimal.valueOf(15),
                    LocalDate.of(LocalDate.now().getYear() - 5, 1, 1)),
            new Wolf("wolf1", "gray wolf", BigDecimal.valueOf(15),
                    LocalDate.of(LocalDate.now().getYear() - 7, 1, 1))
    );

    @Override
    public void run() {
        log.info(Thread.currentThread().getName() + " |-->\n" + animalsRepository.countAverageAge(wolfList));
    }
}

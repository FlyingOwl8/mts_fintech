package ru.mts.hw.sheduled;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.mts.hw.repository.AnimalsRepository;

@Slf4j
@Component
@RequiredArgsConstructor
public class PrintDuplicatesRunnable implements Runnable {
    private final AnimalsRepository animalsRepository;

    @Override
    public void run() {
        log.info(Thread.currentThread().getName() + " |-->\n" + animalsRepository.generateDuplicatesText());
    }
}

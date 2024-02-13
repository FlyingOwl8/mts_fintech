package ru.mts.hw_7;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.mts.hw_7.animal.Animal;
import ru.mts.hw_7.animal.impl.Deer;
import ru.mts.hw_7.animal.impl.Shark;
import ru.mts.hw_7.animal.impl.Wolf;

import java.math.BigDecimal;
import java.time.LocalDate;

public class AbstractAnimalTest {
    Animal wolf = new Wolf("default_wolf", "gray wolf", BigDecimal.valueOf(15), LocalDate.of(2023, 12, 31));
    Animal shark = new Shark("default_shark", "tiger shark", BigDecimal.valueOf(15), LocalDate.of(2023, 12, 31));
    Animal deer = new Deer("default_deer", "european deer", BigDecimal.valueOf(15), LocalDate.of(2023, 12, 31));
    Animal wolfDuplicate = new Wolf("default_wolf", "gray wolf", BigDecimal.valueOf(15), LocalDate.of(2023, 12, 31));
    Animal wolfDifferent = new Wolf("default_wolf", "gray wolf", BigDecimal.valueOf(25), LocalDate.of(2020, 12, 31));
    Animal nullAnimal = null;
    BigDecimal anotherType = BigDecimal.valueOf(15);

    @Test
    @DisplayName("Тестирование метода сравнения животных")
    public void equalsTest() {
        Assertions.assertEquals(wolf, wolfDuplicate);
        Assertions.assertNotEquals(wolf, shark);
        Assertions.assertNotEquals(wolf, deer);
        Assertions.assertNotEquals(wolf, wolfDifferent);
        Assertions.assertNotEquals(wolf, nullAnimal);
        Assertions.assertNotEquals(wolf, anotherType);
    }
}

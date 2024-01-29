package ru.mts.hw_4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.mts.hw_4.animal.Animal;
import ru.mts.hw_4.animal.impl.Deer;
import ru.mts.hw_4.animal.impl.Shark;
import ru.mts.hw_4.animal.impl.Wolf;

import java.math.BigDecimal;
import java.time.LocalDate;

public class AbstractAnimalTest {
    Animal wolf = new Wolf("gray", BigDecimal.valueOf(15), LocalDate.of(2023, 12, 31));
    Animal shark = new Shark("tiger", BigDecimal.valueOf(15), LocalDate.of(2023, 12, 31));
    Animal deer = new Deer("european", BigDecimal.valueOf(15), LocalDate.of(2023, 12, 31));
    Animal wolfReference = wolf;
    Animal wolfDuplicate = new Wolf("gray", BigDecimal.valueOf(15), LocalDate.of(2023, 12, 31));
    Animal wolfDifferent = new Wolf("gray", BigDecimal.valueOf(25), LocalDate.of(2020, 12, 31));
    Animal nullAnimal = null;
    BigDecimal anotherType = BigDecimal.valueOf(15);

    @Test
    @DisplayName("Тестирование метода сравнения животных")
    public void equalsTest() {
        Assertions.assertEquals(wolf, wolfReference);
        Assertions.assertEquals(wolf, wolfDuplicate);
        Assertions.assertNotEquals(wolf, shark);
        Assertions.assertNotEquals(wolf, deer);
        Assertions.assertNotEquals(wolf, wolfDifferent);
        Assertions.assertNotEquals(wolf, nullAnimal);
        Assertions.assertNotEquals(wolf, anotherType);

        //Проверка рефлексивности equals (Объект должен быть равен самому себе)
        Assertions.assertEquals(wolf, wolf);
        //Проверка симметричности equals (Если a.equals(b) возвращает true, то и b.equals(a) должен возвращать true)
        Assertions.assertEquals(wolf, wolfDuplicate);
        Assertions.assertEquals(wolfDuplicate, wolf);
        //Проверка транзитивности equals (Если a.equals(b) и b.equals(c) возвращают true, то и a.equals(c) должен возвращать true)
        Assertions.assertEquals(wolfDuplicate, wolf);
        Assertions.assertEquals(wolf, wolfReference);
        Assertions.assertEquals(wolfDuplicate, wolfReference);
        //Проверка консистентности equals (Повторные вызовы equals для одного и того же объекта должны всегда возвращать один и тот же результат)
        Assertions.assertEquals(wolf, wolfDuplicate);
        Assertions.assertEquals(wolf, wolfDuplicate);
        Assertions.assertEquals(wolf, wolfDuplicate);
        Assertions.assertNotEquals(wolf, wolfDifferent);
        Assertions.assertNotEquals(wolf, wolfDifferent);
        Assertions.assertNotEquals(wolf, wolfDifferent);
    }
}

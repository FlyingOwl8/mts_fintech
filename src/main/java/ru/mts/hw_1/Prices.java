package ru.mts.hw_1;

import java.math.BigDecimal;

/**
 * Класс Prices для хранения рассчитанных стоимостей.
 * Содержит конструктор для создания объектов с заданными значениями
 * и геттеры.
 * */

public class Prices {
    //cтоимость без скидки
    private BigDecimal priceWithoutDiscount;
    //стоимость со скидкой
    private BigDecimal priceWithDiscount;

    public Prices(BigDecimal priceWithoutDiscount, BigDecimal priceWithDiscount) {
        this.priceWithDiscount = priceWithDiscount;
        this.priceWithoutDiscount = priceWithoutDiscount;
    }

    public BigDecimal getPriceWithoutDiscount() {
        return priceWithoutDiscount;
    }
    public BigDecimal getPriceWithDiscount() {
        return priceWithDiscount;
    }
}

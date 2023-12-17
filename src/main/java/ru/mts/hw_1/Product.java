package ru.mts.hw_1;

import java.math.BigDecimal;

/**
 * Класс Product для представления информации о товаре.
 * Содержит конструктор для создания объектов с заданными значениями,
 * имеющий проверку на валидность предоставленных значений
 * (все значения должны быть неотрицательными).
 * */
public class Product {
    //количество единиц товара
    public int count;
    //цена единицы товара
    public BigDecimal cost;
    //скидка на этот товар
    public double discount;

    //конструктор для создания объектов с заданными значениями
    public Product(int count, BigDecimal cost, double discount) {
        //проверка количества единиц товара на валидность
        if (count < 0) {
            throw new IllegalArgumentException("Count is not valid!");
        }
        //проверка цены единицы товара на валидность
        if (BigDecimal.valueOf(0).compareTo(cost) > 0) {
            throw new IllegalArgumentException("Cost is not valid!");
        }
        //проверка скидки на товар на валидность
        if (discount < 0) {
            throw new IllegalArgumentException("Discount is not valid!");
        }
        //присвоение полям валидных значений
        this.count = count;
        this.cost = cost;
        this.discount = discount;
    }
}

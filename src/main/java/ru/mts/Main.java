package ru.mts;


import java.math.BigDecimal;

import static java.math.RoundingMode.HALF_UP;


/**
 * Основной класс, создающий 3 разных объекта класса Product со своими значениями полей
 * и вызывающий для каждого из них метод расчёта общих стоимостей
 * */
public class Main {
    public static void main(String[] args) {
        //создание первого объекта класса и вызов метода
        Product product1 = new Product(1, BigDecimal.valueOf(40.0), 0.0075);
        computePrices(product1);

        //создание второго объекта класса и вызов метода
        Product product2 = new Product(52, BigDecimal.valueOf(100.0), 0.42575);
        computePrices(product2);

        //создание третьего объекта класса и вызов метода
        Product product3 = new Product(200, BigDecimal.valueOf(139.0), 0.591);
        computePrices(product3);
    }

    /**
     * Метод для расчётов стоимостей.
     * Принимает в качестве аргумента объект класса Product.
     * Вычисляет общую стоимость за все единицы товара без учёта скидки
     * и общую стоимость с учётом скидки
     * и выводит полученные значения с округлением до 2 знаков после запятой.
     * */
    public static void computePrices(Product product) {
        BigDecimal priceWithoutDiscount = product.cost.multiply(BigDecimal.valueOf(product.count));
        System.out.println("Price without discount: " + priceWithoutDiscount.setScale(2, HALF_UP));
        BigDecimal priceWithDiscount = BigDecimal.valueOf(1).subtract(BigDecimal.valueOf(product.discount)).multiply(product.cost).multiply(BigDecimal.valueOf(product.count));
        System.out.println("Price with discount: " + priceWithDiscount.setScale(2, HALF_UP));
    }
}
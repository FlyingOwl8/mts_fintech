package ru.mts;


import ru.mts.hw_1.Prices;
import ru.mts.hw_1.Product;

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
        Prices prices1 = computePrices(product1);
        printProductInformation(product1, prices1);

        //создание второго объекта класса и вызов метода
        Product product2 = new Product(52, BigDecimal.valueOf(100.0), 0.42575);
        Prices prices2 = computePrices(product2);
        printProductInformation(product2, prices2);

        //создание третьего объекта класса и вызов метода
        Product product3 = new Product(200, BigDecimal.valueOf(139.0), 0.591);
        Prices prices3 = computePrices(product3);
        printProductInformation(product3, prices3);
    }

    /**
     * Метод для расчётов стоимостей.
     * Принимает в качестве аргумента объект класса Product.
     * Вычисляет общую стоимость за все единицы товара без учёта скидки
     * и общую стоимость с учётом скидки
     * */
    private static Prices computePrices(Product product) {
        BigDecimal priceWithoutDiscount = product.cost.multiply(BigDecimal.valueOf(product.count));
        BigDecimal priceWithDiscount = BigDecimal.valueOf(1).subtract(BigDecimal.valueOf(product.discount)).multiply(product.cost).multiply(BigDecimal.valueOf(product.count));
        return new Prices(priceWithoutDiscount, priceWithDiscount);
    }

    /**
     * Вывод всей информации о товаре - исходных данных и рассчитанных стомостей
     * */
    private static void printProductInformation(Product product, Prices prices) {
        printProductProperties(product);
        printComputedPrices(prices);
        System.out.println("-------");
    }
    /**
     * Вывод исходных данных товара -
     * количества единц товара, его цены и скидки на него
     * */
    private static void printProductProperties(Product product) {
        System.out.println("Product count: " + product.count);
        System.out.println("Product cost: " + product.cost);
        System.out.println("Product discount: " + product.discount);
    }
    /**
     * Вывод расчитанных стоимостей с и без скидки с округлением до 2 знаков после запятой
     * */
    private static void printComputedPrices(Prices prices) {
        System.out.println("Price without discount: " + prices.getPriceWithoutDiscount().setScale(2, HALF_UP));
        System.out.println("Price with discount: " + prices.getPriceWithDiscount().setScale(2, HALF_UP));
    }
}
package com.leysoft.meli;

import java.util.StringJoiner;

public class Product {

    private final String name;

    private final Double price;

    public Product(String name, Double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Product.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .add("price=" + price)
                .toString();
    }
}

package com.leysoft.meli;

import java.security.SecureRandom;
import java.util.Random;
import java.util.StringJoiner;

public class Gen {

    private static final Integer BINARY = 2;

    private static final Random RANDOM = new SecureRandom();

    private final Integer gen;

    private final Product item;

    private Gen(Integer gen, Product item) {
        this.gen = gen;
        this.item = item;
    }

    public static Gen generate(Product item) {
        return new Gen(RANDOM.nextInt(BINARY), item);
    }

    public Integer getGen() {
        return gen;
    }

    public Product getItem() {
        return item;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Gen.class.getSimpleName() + "[", "]")
                .add("gen=" + gen)
                .add("item=" + item)
                .toString();
    }
}

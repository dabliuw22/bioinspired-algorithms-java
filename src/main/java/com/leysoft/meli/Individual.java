package com.leysoft.meli;

import java.util.Arrays;
import java.util.StringJoiner;

public class Individual {

    private final Integer size;

    private final Gen[] genes;

    private Individual(Integer size) {
        this.size = size;
        this.genes = new Gen[size];
    }

    public static Individual empty(Integer size) {
        return new Individual(size);
    }

    public static Individual generate(Product[] products) {
        var newIndividual = new Individual(products.length);
        for (int i = 0; i < products.length; i++) {
            var gen = Gen.generate(products[i]);
            newIndividual.setGen(i, gen);
        }
        return newIndividual;
    }

    public Double f() {
        var sum = 0.D;
        for (int i = 0; i < size; i++) {
            var gen = genes[i];
            sum += (gen.getGen() > 0 ? genes[i].getItem().getPrice(): 0);
        }
        return sum;
    }

    public Gen getGen(int index) {
        return this.genes[index];
    }

    public void setGen(int index, Gen gen) {
        this.genes[index] = gen;
    }

    public Integer getSize() {
        return this.size;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Individual.class.getSimpleName() + "[", "]")
                .add("size=" + size)
                .add("genes=" + Arrays.toString(genes))
                .toString();
    }
}

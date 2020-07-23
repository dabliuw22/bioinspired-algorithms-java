package com.leysoft.genetic;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Random;
import java.util.StringJoiner;
import static java.lang.Math.*;

public class Individual {

    private final int[] genes;

    private final Random random;

    public Individual() {
        this.genes = new int[Config.CHROMOSOME_LENGTH];
        this.random = new SecureRandom();
    }

    public int getGen(int index) {
        return this.genes[index];
    }

    public void setGen(int index, int gen) {
        this.genes[index] = gen;
    }

    public void generateIndividual() {
        for(int i = 0; i < Config.CHROMOSOME_LENGTH; i++) {
            int gen = random.nextInt(Config.BINARY);
            this.genes[i] = gen;
        }
    }

    private double function(double x) {
        return (sin(x) * pow(x - 2, 2)) + 3;
    }

    private double genesToNumber() {
        var number = 0.0;
        var base = 1.0;

        /**
         * [
         *  (0) signo,
         *  (> 0 and <= GEN_LENGTH) entero,
         *  (> GEN_LENGTH and < CHROMOSOME_LENGTH) decimas
         * ]
         **/
        // entero
        for(int i = Config.GEN_LENGTH; i > 0; i--) {
            if(getGen(i) == 1) {
                number += base;
            }
            base *= 2;
        }
        base = 2.0;
        // decimas
        for(int i = Config.GEN_LENGTH + 1; i < Config.CHROMOSOME_LENGTH; i++) {
            if(getGen(i) == 1) {
                number += 1/base;
            }
            base *= 2;
        }
        // signo
        if(getGen(0) == 1) {
            number *= -1;
        }
        return number > 0 ? number * Config.LIMIT_SUP / Math.pow(2, Config.GEN_LENGTH):
                -1 * number * Config.LIMIT_INF / Math.pow(2, Config.GEN_LENGTH);
    }

    public double fitness() {
        return function(genesToNumber());
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Individual.class.getSimpleName() + "[", "]")
                .add("genes=" + Arrays.toString(genes))
                .add("value=" + genesToNumber())
                .add("fitness=" + fitness())
                .toString();
    }
}

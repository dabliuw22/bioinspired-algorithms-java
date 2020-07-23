package com.leysoft.genetic;

import java.security.SecureRandom;
import java.util.Random;

public class DefaultGenetic implements Genetic {

    private final int size;

    private final Random random;

    public DefaultGenetic(int size) {
        this.size = size;
        this.random = new SecureRandom();
    }

    @Override
    public Individual best() {
        var population = new Population(size);
        population.initializer();
        int generation = 1;
        while(generation <= Config.SIMULATION_LENGTH) {
            System.out.println("Gen " + generation + ": " + population.best());
            population = evolve(population);
            generation++;
        }
        System.out.println("Best: " + population.best());

        return population.best();
    }

    public Population evolve(Population population) {
        var newPopulation = new Population(population.size());
        for(int i = 0; i < population.size(); i++) {
            Individual individualOne = selection(population);
            Individual individualTwo = selection(population);
            Individual newIndividual = crossover(individualOne, individualTwo);
            newPopulation.save(i, newIndividual);
        }
        for(int i = 0; i < newPopulation.size(); i++) {
            mutate(newPopulation.getIndividual(i));
        }
        return newPopulation;
    }

    public Individual crossover(Individual individualOne, Individual individualTwo) {
        var newSolution = new Individual();
        for(int i = 0; i < Config.CHROMOSOME_LENGTH; i++) {
            if(Math.random() <= Config.CROSSOVER) {
                newSolution.setGen(i, individualOne.getGen(i));
            } else {
                newSolution.setGen(i, individualTwo.getGen(i));
            }
        }
        return newSolution;
    }

    public void mutate(Individual individual) {
        for(int i = 0; i < Config.CHROMOSOME_LENGTH; i++) {
            if(Math.random() <= Config.MUTATION) {
                int gen = random.nextInt(Config.BINARY);
                individual.setGen(i, gen);
            }
        }
    }

    public Individual selection(Population population) {
        var newPopulation = new Population(Config.TOURNAMENT_SIZE);
        for(int i = 0; i < Config.TOURNAMENT_SIZE; i++) {
            int index = (int) (Math.random()*population.size());
            newPopulation.save(i, population.getIndividual(index));
        }
        return newPopulation.best();
    }
}

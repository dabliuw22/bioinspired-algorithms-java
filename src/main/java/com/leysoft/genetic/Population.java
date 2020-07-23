package com.leysoft.genetic;

public class Population {

    private final Individual[] individuals;

    public Population(int size) {
        this.individuals = new Individual[size];
    }

    public Individual getIndividual(int index) {
        return this.individuals[index];
    }

    public void initializer() {
        for(int i = 0; i < individuals.length; i++) {
            Individual newIndividual = new Individual();
            newIndividual.generateIndividual();
            save(i, newIndividual);
        }
    }

    public Individual best() {
        Individual best = this.individuals[0];
        for(int i = 1; i < size(); i++) {
            if(getIndividual(i).fitness() < best.fitness()) {
                best = getIndividual(i);
            }
        }
        return best;
    }

    public void save(int index, Individual newIndividual) {
        this.individuals[index] = newIndividual;
    }

    public int size() {
        return this.individuals.length;
    }
}

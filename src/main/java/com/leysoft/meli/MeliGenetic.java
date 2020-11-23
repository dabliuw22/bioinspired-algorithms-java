package com.leysoft.meli;

public class MeliGenetic {

    private static final Double CROSSOVER = 0.3;

    private static final Double MUTATION = 0.10;

    private static final Double TOURNAMENT = 0.15;

    private static final Integer SIMULATION_LENGTH = 500;

    private static final Integer TERMINATION = 5;

    private final Integer size;

    private final Integer tournamentSize;

    public MeliGenetic(Integer size) {
        this.size = size;
        this.tournamentSize = Double.valueOf(size * TOURNAMENT).intValue();
    }

    public Individual best(Product[] products, Double ticket) {
        var population = new Population(products, size, ticket);
        population.initializer();
        var best = population.best();
        var generation = 1;
        var termination = 0;
        while (generation <= SIMULATION_LENGTH) {
            System.out.println("Gen: " + generation + ", Best: " + best + ", f(): " + best.f());
            population = evolve(population, products, ticket);
            var temp = population.best();
            termination = temp.f().equals(best.f()) ? termination + 1: 0;
            best = temp;
            generation++;
            if (termination >= TERMINATION) break;
        }
        System.out.println("Best: " + best + ", f(): " + best.f());
        return best;
    }

    public Population evolve(Population population, Product[] products, Double ticket) {
        var newPopulation = new Population(products, population.size(), ticket);
        for (int i = 0; i < population.size(); i++) {
            var individual1 = selection(population, products, ticket);
            var individual2 = selection(population, products, ticket);
            var individual = crossover(individual1, individual2, products);
            newPopulation.save(i, individual);
        }
        for (int i = 0; i < newPopulation.size(); i++) {
            mutate(newPopulation.get(i), products);
        }
        return newPopulation;
    }

    public Individual crossover(Individual individualOne, Individual individualTwo, Product[] products) {
        var individual = Individual.empty(products.length);
        var limit = (Integer) products.length * CROSSOVER;
        for(int i = 0; i < products.length; i++) {
            if (i <= limit) {
                individual.setGen(i, individualOne.getGen(i));
            } else {
                individual.setGen(i, individualTwo.getGen(i));
            }
        }
        return individual;
    }

    public void mutate(Individual individual, Product[] products) {
        for(int i = 0; i < products.length; i++) {
            if(Math.random() <= MUTATION) {
                var gen = Gen.generate(products[i]);
                individual.setGen(i, gen);
            }
        }
    }

    public Individual selection(Population population, Product[] products, Double ticket) {
        var tournament = new Population(products, tournamentSize, ticket);
        for (int i = 0; i < tournamentSize; i++) {
            int index = (int) (Math.random() * population.size());
            tournament.save(i, population.get(index));
        }
        return tournament.best();
    }
}

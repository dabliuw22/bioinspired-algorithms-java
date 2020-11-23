package com.leysoft.meli;

public class Population {

    private final Product[] products;

    private final Individual[] individuals;

    private final Double ticket;

    Population(Product[] products, Integer size, Double ticket) {
        this.products = products;
        this.individuals = new Individual[size];
        this.ticket = ticket;
    }

    public Individual get(int index) {
        return this.individuals[index];
    }

    public void initializer() {
        for(int i = 0; i < individuals.length; i++) {
            var individual = Individual.generate(products);
            save(i, individual);
        }
    }

    public Individual best() {
        var best = get(0);
        for(int i = 1; i < size(); i++) {
            var temp = get(i);
            if(temp.f() > best.f() && temp.f() <= this.ticket) {
                best = temp;
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

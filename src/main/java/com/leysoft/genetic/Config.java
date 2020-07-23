package com.leysoft.genetic;

public class Config {

    public static final double CROSSOVER = 0.5;

    public static final  double MUTATION = 0.15;

    public static final int CHROMOSOME_LENGTH = 16;

    public static final int GEN_LENGTH = CHROMOSOME_LENGTH - 1 - 5;

    public static final int BINARY = 2;

    public static final int TOURNAMENT_SIZE = 5;

    public static final int SIMULATION_LENGTH = 200;

    public static final double LIMIT_INF = -20;

    public static final double LIMIT_SUP = 20;

    private Config() {}
}

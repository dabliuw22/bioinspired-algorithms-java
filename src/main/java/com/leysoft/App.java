package com.leysoft;

import com.leysoft.genetic.DefaultGenetic;

public class App {

    public static void main(String[] args) {
        var genetic = new DefaultGenetic(100);
        genetic.best();
    }
}

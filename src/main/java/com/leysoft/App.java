package com.leysoft;

import com.leysoft.meli.MeliGenetic;
import com.leysoft.meli.Product;

public class App {

    public static void main(String[] args) {

        var meli = new MeliGenetic(500);
        var products = new Product[] {
                new Product("MLA1", 100D),
                new Product("MLA2", 210D),
                new Product("MLA3", 260D),
                new Product("MLA4", 80D),
                new Product("MLA5", 90D)
        };
        var best = meli.best(products, 400D);
    }
}

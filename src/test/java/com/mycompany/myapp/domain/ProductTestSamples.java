package com.mycompany.myapp.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class ProductTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static Product getProductSample1() {
        Product product = new Product();
        product.id = 1L;
        product.name = "name1";
        product.description = "description1";
        return product;
    }

    public static Product getProductSample2() {
        Product product = new Product();
        product.id = 2L;
        product.name = "name2";
        product.description = "description2";
        return product;
    }

    public static Product getProductRandomSampleGenerator() {
        Product product = new Product();
        product.id = longCount.incrementAndGet();
        product.name = UUID.randomUUID().toString();
        product.description = UUID.randomUUID().toString();
        return product;
    }
}

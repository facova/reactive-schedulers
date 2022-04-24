package com.example.schedulers;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

class FluxServiceTest {

    FluxService fluxService = new FluxService();

    @Test
    void nameFluxFlatMap() {

        var nameFlux = fluxService.nameFluxFlatMap(4);

        StepVerifier.create(nameFlux)
                .expectNext("D","A","N","I")
                .verifyComplete();
    }
    @Test
    void nameFluxFlatMapAsync() {

        var nameFlux = fluxService.nameFluxFlatMapAsync(6);

        StepVerifier.create(nameFlux)
//                .expectNext("F","E","L","I","P","E","R","E","N","A","T","O")
                .expectNextCount(12)
                .verifyComplete();
    }

}
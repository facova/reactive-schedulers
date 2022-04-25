package com.example.schedulers.service;

import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;

public class FluxService {

    Flux<String> nameFluxFlatMap(int stringLength) {
        return Flux.fromIterable(List.of("Felipe", "Dani", "Renato", "Luis"))
                .map(String::toUpperCase)
                .filter(s -> s.length() == stringLength)
                .flatMap(this::splitString)
                .log();
    }

    Flux<String> nameFluxFlatMapAsync(int stringLength) {
        return Flux.fromIterable(List.of("Felipe", "Dani", "Renato"))
                .map(String::toUpperCase)
                .filter(s -> s.length() == stringLength)
                .flatMap(this::splitStringWithDelay)
                .log();
    }

    public Flux<String> splitString(String name) {
        var charArray = name.split("");
        return Flux.fromArray(charArray);
    }

    public Flux<String> splitStringWithDelay(String name) {
        var charArray = name.split("");
        var delay = 1000;
        return Flux.fromArray(charArray)
                .delayElements(Duration.ofMillis(delay));
    }
}

package com.example.schedulers.service;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.List;

import static com.example.schedulers.util.CommonUtil.delay;

@Slf4j
public class SchedulersPublishOnFromCallableService {

    static List<String> namesList2 = List.of("Victor", "Luis", "Ewandro");

    public Flux<String> namesPublishOn() {

        return Flux.fromIterable(namesList2)
                .flatMap(this::upperCaseReactive)
                .publishOn(Schedulers.boundedElastic())
                .map(name -> {
                    log.info("Map feito na thread {} com o nome : {}",
                            Thread.currentThread().getName(), name);
                    return name;
                })
                .log();
    }


    // Método de uma lib não reativa e blocante
    private String upperCase(String name) {
        log.info("upperCase chamado na thread: {}",
                Thread.currentThread().getName());
        delay(1000);
        return name.toUpperCase();
    }

    private Mono<String> upperCaseReactive(String name) {
        return Mono.fromCallable(() -> upperCase(name))
                .subscribeOn(Schedulers.boundedElastic());
    }
}

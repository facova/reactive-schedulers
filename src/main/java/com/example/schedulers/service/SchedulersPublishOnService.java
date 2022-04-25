package com.example.schedulers.service;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.List;

import static com.example.schedulers.util.CommonUtil.delay;

@Slf4j
public class SchedulersPublishOnService {

    static List<String> namesList1 = List.of("Felipe", "Dani", "Renato");
    static List<String> namesList2 = List.of("Victor", "Luis", "Ewandro");

    public Flux<String> namesPublishOn(){

        var namesFlux1 = Flux.fromIterable(namesList1)
                .publishOn(Schedulers.parallel())
                .map(this::upperCase)
                .log();

        var namesFlux2 = Flux.fromIterable(namesList2)
                .map(this::upperCase)
                .publishOn(Schedulers.boundedElastic())
                .map(name -> {
                    log.info("Map feito na thread {} com o nome : {}",
                            Thread.currentThread().getName(), name);
                    return name;
                })
                .log();

        return  namesFlux1.mergeWith(namesFlux2);
    }

    // Método de uma lib não reativa e blocante
    private String upperCase(String name) {
        log.info("upperCase chamado na thread: {}",
                Thread.currentThread().getName());
        delay(1000);
        return name.toUpperCase();
    }

}

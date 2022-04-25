package com.example.schedulers.service;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.List;

import static com.example.schedulers.util.CommonUtil.delay;

@Slf4j
public class SchedulersSubscribeOnService {

    static List<String> namesList1 = List.of("Felipe", "Dani", "Renato");
    static List<String> namesList2 = List.of("Victor", "Luis", "Ewandro");

    public Flux<String> namesSubscribeOn(){

        var namesFlux = flux1(namesList1)
                .subscribeOn(Schedulers.boundedElastic())
                .log();

        var namesFlux1 = flux1(namesList2)
                .map(s -> {
                    log.info("Nome é : {}", s);
                    return s;
                })
                .subscribeOn(Schedulers.boundedElastic())
                .log();

        return  namesFlux.mergeWith(namesFlux1);
    }

    private Flux<String> flux1(List<String> namesList) {
        return Flux.fromIterable(namesList)
                .map(this::upperCase);
    }

    // Método de uma lib não reativa e blocante
    private String upperCase(String name) {
        log.info("upperCase chamado na thread: {}",
                Thread.currentThread().getName());
        delay(1000);
        return name.toUpperCase();
    }

}

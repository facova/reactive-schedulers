package com.example.schedulers.service;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

class SchedulersSubscribeOnServiceTest {

    SchedulersSubscribeOnService schedulersSubscribeOnService =
            new SchedulersSubscribeOnService();

    @Test
    void namesSubscribeOn() {

        var flux = schedulersSubscribeOnService.namesSubscribeOn();

        StepVerifier.create(flux)
                .expectNextCount(6)
                .verifyComplete();

    }

}
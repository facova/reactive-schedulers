package com.example.schedulers.service;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

class SchedulersPublishOnFromCallableServiceTest {

    SchedulersPublishOnFromCallableService fromCallableService =
            new SchedulersPublishOnFromCallableService();

    @Test
    void namesPublishOn() {

        var flux = fromCallableService.namesPublishOn();

        StepVerifier.create(flux)
                .expectNextCount(3)
                .verifyComplete();

    }

}

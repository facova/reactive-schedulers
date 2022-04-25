package com.example.schedulers.service;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

class SchedulersPublishOnServiceTest {

    SchedulersPublishOnService schedulersPublishOnService =
            new SchedulersPublishOnService();

    @Test
    void namesPublishOn() {

        var flux = schedulersPublishOnService.namesPublishOn();

        StepVerifier.create(flux)
                .expectNextCount(6)
                .verifyComplete();

    }

}
package com.javatechie;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class MonoFluxTest {

    @Test
    void monoPublisherHappyPathTest() {

        /*
         Mono is publisher for one element. Its package is reactor.core.publisher.Mono
         By looking at package name you can understand that it is publisher.
         Following monoString is publisher event stream for String.
         Calling log() method in method change is optional. If it
         is called then it prints internal method call chains on
         console so that we see workflow
        */
        Mono<String> monoString = Mono.just("JavaTechie").log();


        /*
            1.Step:- Our Unit Test method Subscribe as subscriber by
              calling subscriber method to publisher

            To access any publisher, we have to
            subscribe to that publisher as subscriber.
            For that we called following subscribe
            method.

            Here we passed method reference in place
            of lambda. When event stream provide us
            data, then our method reference code
            execute. and our current thread in side
            of testMono will not be blocked.
        */
        monoString.subscribe(System.out::println);

    }

    @Test
    void monoPublisherErrorPathTest() {

        Mono<?> monoString = Mono.just("JavaTechie")
                .then(Mono.error(new RuntimeException("Some Stupid Exception")))
                .log();
        monoString.subscribe(System.out::println, e->System.out.println(e.getMessage()));
    }

    @Test
    void fluxPublisherHappyPathTest() {

        Flux<String> fluxString = Flux.just("Spring", "Springboot",
                "Hibernate", "Microservice");
        fluxString.subscribe(System.out::println);
    }

    @Test
    void fluxPublisherConcatInMiddleHappyPathTest() {

        Flux<String> fluxString = Flux.just("Spring", "Springboot",
                        "Hibernate", "Microservice")
                .concatWithValues("AWS", "Google Cloud");
        fluxString.subscribe(System.out::println);
    }

    @Test
    void fluxPublisherShowingCallChainsTest() {

        Flux<String> fluxString = Flux.just("Spring", "Springboot",
                        "Hibernate", "Microservice")
                .concatWithValues("AWS", "Google Cloud")
                .log();

        fluxString.subscribe(System.out::println);
    }

    @Test
    void fluxPublisherErrorPathTest() {

        Flux<String> fluxString = Flux.just("Spring", "Springboot",
                        "Hibernate", "Microservice")
                .concatWithValues("AWS", "Google Cloud")
                .concatWith(Flux.error(new RuntimeException("Stupid Flux Error")))
                .concatWithValues("Apple", "Grapes")
                .log();
        fluxString.subscribe(System.out::println, e->System.out.println(e.getMessage()));
    }
}

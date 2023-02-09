package com.javatechie.daos;

import com.javatechie.dtos.Customer;
import com.javatechie.utils.UtilBox;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class CustomerDao {

    public List<Customer> getAllCustomersTradWay() {

        return IntStream.rangeClosed(1, 60)
                .peek(index -> UtilBox.delay(1))
                .peek(i-> System.out.println(
                        "Processing Count : ".concat(String.valueOf(i))))
                .mapToObj(count->Customer.builder()
                        .id(count)
                        .name("Customer - ".concat(String.valueOf(count))).build())
                .collect(Collectors.toList());
    }

    public Flux<Customer> getAllCustomersNewWay() {

        return Flux.range(1, 60)
                .delayElements(Duration.ofSeconds(1l))
                .doOnNext(i-> System.out.println(
                        "Processing Count : ".concat(String.valueOf(i))))
                .map(count -> Customer.builder()
                        .id(count)
                        .name("Customer - ".concat(String.valueOf(count)))
                        .build());
    }

    public Flux<Customer> getAllCustomersList() {

        return Flux.range(1, 60)
                .map(count -> Customer.builder()
                        .id(count)
                        .name("Customer - ".concat(String.valueOf(count)))
                        .build());
    }
}
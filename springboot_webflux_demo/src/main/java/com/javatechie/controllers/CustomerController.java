package com.javatechie.controllers;

import com.javatechie.dtos.Customer;
import com.javatechie.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/tradWay")
    public List<Customer> getAllCustomersTradWay() {
        return  customerService.getAllCustomersTradWay();
    }

    @GetMapping(value = "/newWay", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Customer> getAllCustomersNewWay() {
        return  customerService.getAllCustomersNewWay();
    }
}
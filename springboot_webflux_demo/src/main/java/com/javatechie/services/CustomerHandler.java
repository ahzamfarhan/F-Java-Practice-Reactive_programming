package com.javatechie.services;

import com.javatechie.daos.CustomerDao;
import com.javatechie.dtos.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerHandler {

    private CustomerDao customerDao;

    @Autowired
    CustomerHandler(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public Mono<ServerResponse> getCustomers(ServerRequest serverRequest) {
        Flux<Customer> customersFlux = customerDao.getAllCustomersList();
        return ServerResponse.ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(customersFlux, Customer.class);
    }

    public Mono<ServerResponse> getCustomersEventStream(ServerRequest serverRequest) {

        Flux<Customer> customerFlux = customerDao.getAllCustomersNewWay();

        return ServerResponse.ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(customerFlux, Customer.class);
    }


    public Mono<ServerResponse> getCustomerById(ServerRequest serverRequest) {

        int customerId = Integer.parseInt(serverRequest.pathVariable("customerId"));
        Mono<Customer> customerMono = customerDao.getAllCustomersList()
                .filter(customer -> customer.getId() == customerId)
                //.take(1).single();
                    //OR
                .next();

        return ServerResponse.ok()
                .body(customerMono, Customer.class);
    }

    public Mono<ServerResponse> saveCustomer(ServerRequest serverRequest) {

        Mono<Customer> customerMono = serverRequest.bodyToMono(Customer.class);

        Mono<String> savedResponseStr = customerMono
                .map(customer -> customer.getId() + " - " + customer.getName());

        System.out.println("Customer is saved successfully...");
        return ServerResponse.ok().body(savedResponseStr, String.class);
    }

}

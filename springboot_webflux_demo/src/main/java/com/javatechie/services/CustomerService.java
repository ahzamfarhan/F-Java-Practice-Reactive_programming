package com.javatechie.services;

import com.javatechie.daos.CustomerDao;
import com.javatechie.dtos.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerDao customerDao;

    private final StopWatch stopWatch;

    @Autowired
    public CustomerService(CustomerDao customerDao, StopWatch stopWatch) {
        this.customerDao = customerDao;
        this.stopWatch = stopWatch;
    }

    public List<Customer> getAllCustomersTradWay() {

        stopWatch.start();
        List<Customer> customersList = customerDao.getAllCustomersTradWay();
        stopWatch.stop();
        System.out.println(String.format("Total Execution time in Seconds %.2f",
                stopWatch.getTotalTimeSeconds()));

        return customersList;
    }

    public Flux<Customer> getAllCustomersNewWay() {

        stopWatch.start();
        Flux<Customer> customersFlux = customerDao.getAllCustomersNewWay();
        stopWatch.stop();
        System.out.println(String.format("Total Execution time in Seconds %.2f",
                stopWatch.getTotalTimeSeconds()));

        return customersFlux;
    }

}

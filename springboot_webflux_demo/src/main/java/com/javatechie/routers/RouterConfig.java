package com.javatechie.routers;

import com.javatechie.services.CustomerHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class RouterConfig {

    private CustomerHandler customerHandler;

    @Autowired
    public RouterConfig(CustomerHandler customerHandler) {
        this.customerHandler = customerHandler;
    }

    @Bean
    public RouterFunction<ServerResponse> routerFunction() {

        return  RouterFunctions.route()
                .GET("/router/customersList",
                        customerHandler::getCustomers)
                .GET("/router/customersEventStream",
                        customerHandler::getCustomersEventStream)
                .build();

    }
}

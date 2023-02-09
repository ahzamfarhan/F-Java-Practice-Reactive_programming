package com.javatechie.repository;

import com.javatechie.dtos.ProductDto;
import com.javatechie.pojos.Product;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface ProductRepo extends R2dbcRepository<Product, Long> {
    Mono<Product> findByName(String name);

    public  Flux<Product> getByPriceBetween(Double minPrice, Double maxPrice);
}

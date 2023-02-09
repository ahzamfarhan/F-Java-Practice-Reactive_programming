package com.javatechie.services;

import com.javatechie.dtos.ProductDto;
import com.javatechie.pojos.Product;
import com.javatechie.repository.ProductRepo;
import com.javatechie.utils.UtilBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Service
public class ProductService {

    ProductRepo productRepo;

    @Autowired
    public ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public Flux<ProductDto> getAllProducts() {
        return productRepo.findAll()
                .map(UtilBox::mapToDto);
    }

    public Flux<ProductDto> getAllProductsSlow() {
        return productRepo.findAll()
                .delayElements(Duration.ofSeconds(1))
                .map(UtilBox::mapToDto);
    }

    public Mono<ProductDto> getProductByName(String name) {
        return productRepo.findByName(name)
                .map(UtilBox::mapToDto);
    }

    public  Flux<ProductDto> getProductByPriceBetween(Double minPrice, Double maxPrice) {

        return  productRepo.getByPriceBetween(minPrice, maxPrice)
                .map(UtilBox::mapToDto);
    }

    public Mono<ProductDto> getProductById(Long productId) {
        return productRepo.findById(productId).map(UtilBox::mapToDto);
    }


    public  Mono<ProductDto>  saveProduct(Mono<ProductDto> productDtoMono) {
       return productDtoMono.map(UtilBox::mapToEntity)
               .flatMap(productRepo::save)
               .map(UtilBox::mapToDto);
    }

    public  Mono<ProductDto>  updateProduct(Mono<ProductDto> productDtoMono, Long productId) {

       return productRepo.findById(productId)
                .flatMap(product -> productDtoMono.map(UtilBox::mapToEntity))
                .doOnNext(product -> product.setProductId(productId))
                .flatMap(productRepo::save)
                .map(UtilBox::mapToDto);
    }

    public  Mono<Void> deleteProduct(Long productId) {
        return productRepo.deleteById(productId);
    }

}

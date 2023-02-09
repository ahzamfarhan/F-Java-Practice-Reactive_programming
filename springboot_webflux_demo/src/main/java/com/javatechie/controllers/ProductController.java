package com.javatechie.controllers;

import com.javatechie.dtos.ProductDto;
import com.javatechie.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = "/all", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ProductDto> getAllProducts() {
        return  productService.getAllProducts();

    }

    @GetMapping(value = "/all/slow", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ProductDto> getAllProductsSlow() {
        return  productService.getAllProductsSlow();

    }

    @GetMapping(value = "/all/slow2", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<ProductDto> getAllProductsSlowTrad() {
        return  productService.getAllProductsSlow();

    }

    @GetMapping("/getProductByPriceRange/{min}/{max}")
    public Flux<ProductDto> getProductByPriceRange(@PathVariable("min") double min,
                                                   @PathVariable("max") double max) {
        return  productService.getProductByPriceBetween(min, max);
    }

    @GetMapping("/getProductByName")
    public  Mono<ProductDto> getProductByName(@RequestParam("name") String name) {
        return  productService.getProductByName(name);
    }

    @GetMapping("/productById/{productId}")
    public Mono<ProductDto> getProductById(@PathVariable Long productId) {
        return productService.getProductById(productId);
    }

    @DeleteMapping("/deleteProduct/{productId}")
    public Mono<Void> deleteProduct(@PathVariable Long productId) {
        return productService.deleteProduct(productId);
    }

    @PostMapping("/saveProduct")
    public Mono<ProductDto> saveProduct(@RequestBody Mono<ProductDto> productDto) {
        return productService.saveProduct(productDto);
    }

    @PutMapping("/updateProduct/{productId}")
    public Mono<ProductDto> updateProduct(@RequestBody Mono<ProductDto> productDto,
                                        @PathVariable Long productId) {
        return productService.updateProduct(productDto, productId);
    }

}

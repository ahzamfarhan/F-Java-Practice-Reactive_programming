package com.javatechie.utils;

import com.javatechie.dtos.ProductDto;
import com.javatechie.pojos.Product;
import org.modelmapper.ModelMapper;
import org.springframework.ui.ModelMap;

import java.util.concurrent.TimeUnit;

public class UtilBox {

    public static void delay( final int delayInSeconds) {
        try {
            TimeUnit.SECONDS.sleep(delayInSeconds);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    public static ProductDto mapToDto(Product product) {
        return new ModelMapper().map(product, ProductDto.class);
    }

    public static Product mapToEntity(ProductDto productDto) {
        return new ModelMapper().map(productDto, Product.class);
    }
}

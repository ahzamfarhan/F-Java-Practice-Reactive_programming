package com.javatechie.dtos;


import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Customer {

    private int id;
    private String name;
}

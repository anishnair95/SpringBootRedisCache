package com.javatechie.crud.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PRODUCT_TBL")
public class Product implements Serializable {

    private static final long SERIALIZABLE=1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE )
    private int id;
    private String name;
    private int quantity;
    private double price;
}

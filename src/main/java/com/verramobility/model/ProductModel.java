package com.verramobility.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "product", schema ="discountsale")
public class ProductModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private Double discount;
}

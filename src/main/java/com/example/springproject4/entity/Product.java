package com.example.springproject4.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private Double price;
    private Integer quantity;

    @ManyToOne(fetch = FetchType.EAGER) // fetchType EAGER este default
    @JoinColumn(name = "user_id") //primary key devine foreign key in tabela products
    private User user;
}

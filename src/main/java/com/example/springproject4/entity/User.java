package com.example.springproject4.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String email;
    private String mobile;
    private Integer age;
    private String password;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER) //fetchType LAZY este DEFAULT
    @JoinTable(name = "users_roles",
            joinColumns = {@JoinColumn (name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private List<Role> roles = new ArrayList<>();

//    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, orphanRemoval = true)
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Product> products = new ArrayList<>();

    public void addProduct(Product product)
    {
        this.products.add(product);
        product.setUser(this);  //legam cele doua entitati
    }

    public void addProducts(List<Product> products)
    {
        this.products.addAll(products);
        products.forEach(product -> product.setUser(this));
    }

    public void deleteProducts(List<Product> products)
    {
//        products.forEach(product -> product.setUser(null));
        this.products.removeAll(products);
    }

    public void addRole(Role role) // new Role() -> obiect se duce in memoria HEAP
    {                                        // daca am un program cu o bucla infinita ce genereaza OutOfMemoryError!
        this.roles.add(role);                //referintele obiectelor, parametrii unei metode si variabilele intermediare
        role.getUsers().add(this);                                      //din corpun unei metode sunt salvate in STACK
    }

}

//Dupa ce o adnotam cu @Entity, ea devive tabela(users) in SQL db
        // ea este pusa in context de persistenta
        // ea devine si un Bean in context de Spring
//O entitate inainte sa fie mapata la o tabela, se afla in starea TRANSIENT
        // Dupa ce clasa corespunzatoare entitatii este mapata la o tabela, este pusa in context de persistenta
        //adica se afla in starea PERSISTENT
//Daca o scoatem din contextul de persistenta(cu ajutorul unui evict, sau remove), ea se va afla in starea DETACHED

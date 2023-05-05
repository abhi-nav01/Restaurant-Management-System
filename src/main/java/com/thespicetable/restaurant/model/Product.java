package com.thespicetable.restaurant.model;

import lombok.Data;

import javax.persistence.*;

@Entity  //for db creation
@Data    //resolves all requests such as getter,setter,tostring, etc.
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    private Category category;
    //Since one category can have many products

    private double price;
    private double quantity;
    private String description;
    private String imageName;
    //2-methods for image insertion 1st is through name and 2nd is through image insertion in database
    //but 2nd method is not preferred since it overload the db
}

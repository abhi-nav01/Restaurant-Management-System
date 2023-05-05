package com.thespicetable.restaurant.dto;

import com.thespicetable.restaurant.model.Category;
import lombok.Data;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;

//this is used to handle category object; act as data holder
@Data
public class ProductDTO {
    private Long id;
    private String name;
    private int categoryId;
    private double price;
    private double quantity;
    private String description;
    private String imageName;
}

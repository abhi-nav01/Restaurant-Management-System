package com.thespicetable.restaurant.service;

import com.thespicetable.restaurant.model.Category;
import com.thespicetable.restaurant.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;
    public void addCategory(Category category){
        categoryRepository.save(category);
        //to save this object into the database using obj of category
    }
    public List<Category> getAllCategory(){
        return categoryRepository.findAll();
        //to return all the categories present in the table
    }

    public void removeCategoryById(int id){
        categoryRepository.deleteById(id);
        //deletes row by matching id
    }

    public Optional<Category> getCategoryById(int id){
        return categoryRepository.findById(id);
        //optional return type is provided since find by id returns optional so..
    }
}

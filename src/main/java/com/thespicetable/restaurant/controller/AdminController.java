package com.thespicetable.restaurant.controller;

import com.thespicetable.restaurant.dto.ProductDTO;
import com.thespicetable.restaurant.model.Category;
import com.thespicetable.restaurant.model.Product;
import com.thespicetable.restaurant.service.CategoryService;
import com.thespicetable.restaurant.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Controller
public class AdminController {
    public static String uploadDir=System.getProperty("user.dir") + "/src/main/resources/static/productImages";
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;

    @GetMapping("/admin")           //to connect to admin page
    public String adminHome(){
        return "adminHome";
    }

    @GetMapping("/admin/categories")
    public String toCat(Model model){
        model.addAttribute("categories", categoryService.getAllCategory());
        //returns list of all categories present
        return "categories";
    }
    @GetMapping("/admin/categories/add")
    public String toCatAdd(Model model){
        model.addAttribute("category",new Category());
        return "categoriesAdd";
    }
    @PostMapping("/admin/categories/add")
    public String postCatAdd(@ModelAttribute("category") Category category){
        categoryService.addCategory(category);
        return "redirect:/admin/categories";
    }

    @GetMapping("/admin/categories/delete/{id}")
    public String deleteCat(@PathVariable int id){
        // pathvar is used to automatically match id with categories id since data is deleted using id column
        categoryService.removeCategoryById(id);
        return "redirect:/admin/categories";
    }

    @GetMapping("/admin/categories/update/{id}")
    public String updateCat(@PathVariable int id, Model model){
        Optional<Category> category= categoryService.getCategoryById(id);
        if(category.isPresent()){
            model.addAttribute("category",category.get());
            // to take data from optional we use .get() method
            return "categoriesAdd";
        }else
            return "404";
    }

    //----------Product Section-----------//
    @GetMapping("/admin/products")
    public String toProd(Model model){
        model.addAttribute("products",productService.getAllProduct());
        return "products";
    }

    @GetMapping("/admin/products/add")
    public String addProd(Model model){
        model.addAttribute("productDTO", new ProductDTO());
        model.addAttribute("categories", categoryService.getAllCategory());
        return "productsAdd";
    }

    @PostMapping("/admin/products/add")
    public String productAddPost(@ModelAttribute("productDTO")ProductDTO productDTO,
                                 @RequestParam("productImage")MultipartFile file,
                                 @RequestParam("imgName")String imgname) throws IOException {
        Product product=new Product();
        product.setId(productDTO.getId());
        product.setName(productDTO.getName());
        product.setCategory(categoryService.getCategoryById(productDTO.getCategoryId()).get());
        //getting category id from dropdown menu of dto and to pass that categoryid to service
        product.setPrice(productDTO.getPrice());
        product.setQuantity(productDTO.getQuantity());
        product.setDescription(productDTO.getDescription());
        String imageUUID;
        if(!file.isEmpty()){
            //checks if image is not present
            imageUUID= file.getOriginalFilename();
            Path fileNameAndPath = Paths.get(uploadDir, imageUUID);
            //to store file name and path into upload directory and then pass it to write if user chooses any image
            Files.write(fileNameAndPath, file.getBytes());
        } else{
            imageUUID=imgname;
        }
        product.setImageName(imageUUID);
        productService.addProduct(product);

        return "redirect:/admin/products";
    }

    @GetMapping("/admin/product/delete/{id}")
    public String deleteProd(@PathVariable long id){
        // pathvar is used to automatically match id with categories id since data is deleted using id column
        productService.removeProductById(id);
        return "redirect:/admin/products";
    }

    @GetMapping("/admin/product/update/{id}")
    public String updateProd(@PathVariable long id, Model model){
        Product product= productService.getProductById(id).get();
        ProductDTO productDTO=new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setCategoryId(product.getCategory().getId());
        productDTO.setPrice(product.getPrice());
        productDTO.setQuantity(product.getQuantity());
        productDTO.setDescription(product.getDescription());
        productDTO.setImageName(product.getImageName());

        model.addAttribute("categories",categoryService.getAllCategory());
        model.addAttribute("productDTO", productDTO);
        return "productsAdd";
    }
}

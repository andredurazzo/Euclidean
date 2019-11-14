package com.amaro.Euclidean.controller;


import com.amaro.Euclidean.model.Product;
import com.amaro.Euclidean.model.ProductRequest;
import com.amaro.Euclidean.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
public class ProductsController {

    @Autowired
    private ProductService service;

    @PostMapping("/product")
    public List<Product> saveProduct(@RequestBody ProductRequest response){

        return service.saveProducts(response.getProducts());
    }


    @GetMapping("/product")
    public List<Product> products() throws Exception{
        return service.getAllProducts();
    }

    @GetMapping("/product/{id}")
    public Product product(@PathVariable("id") Long id )throws Exception {
        return service.findById(id);
    }

    @GetMapping("tags")
    public Set<String> getTags(){
        return service.getAllTags();

    }
}

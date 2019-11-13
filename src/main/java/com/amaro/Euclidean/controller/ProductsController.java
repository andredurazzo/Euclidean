package com.amaro.Euclidean.controller;


import com.amaro.Euclidean.model.Product;
import com.amaro.Euclidean.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
public class ProductsController {

    @Autowired
    private ProductService service;

    @PostMapping("/product")
    public void saveProduct(@RequestBody List<Product> products){
        service.saveProducts(products);
    }


    @GetMapping("/product")
    public List<Product> products() throws Exception{
        return service.getAllProducts();
    }

    @GetMapping("tags")
    public Set<String> getTags(){
        return service.getAllTags();

    }
}

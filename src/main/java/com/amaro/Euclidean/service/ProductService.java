package com.amaro.Euclidean.service;

import com.amaro.Euclidean.model.Product;

import java.util.List;
import java.util.Set;

public interface ProductService {
    List<Product> saveProducts(List<Product> products);
    Product findById(Long id) throws Exception;
    List<Product> getAllProducts() throws Exception;
    Set<String> getAllTags();
}

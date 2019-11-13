package com.amaro.Euclidean.service;

import com.amaro.Euclidean.config.AmaroProperties;
import com.amaro.Euclidean.model.Product;
import com.amaro.Euclidean.model.ProductResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private AmaroProperties properties;
    @Override
    public List<Product> getAllProducts() throws Exception {

        final BufferedReader br = new BufferedReader(new InputStreamReader(
                this.getClass().getResourceAsStream("/static/products.json")));

        StringBuffer sb = new StringBuffer();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }

        ProductResponse productResponse = new ObjectMapper().readValue(sb.toString(), ProductResponse.class);

        productResponse
                .getProducts()
                .forEach(
                        product ->
                        {
                            Set<String> tags = properties.getTags();
                            Integer[] arrayTags = new Integer[tags.size()];
                            Arrays.fill(arrayTags, 0);
                            product.getTags().forEach(
                                    s -> {
                                        try {
                                            int index = tags.stream().collect(Collectors.toList()).indexOf(s);
                                            arrayTags[index] = 1;
                                        } catch (Exception e){
                                            System.out.println("Tag not found:" + s);
                                        }

                                    }
                            );
                            product.setTagsVector( Arrays.asList(arrayTags));
                        }
                );


        return productResponse.getProducts();

    }

    @Override
    public List<Product> saveProducts(List<Product> products) {
        return null;
    }

    @Override
    public Set<String> getAllTags() {
        return properties.getTags();
    }

}

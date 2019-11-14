package com.amaro.Euclidean.service;

import com.amaro.Euclidean.config.AmaroProperties;
import com.amaro.Euclidean.model.Product;
import com.amaro.Euclidean.model.ProductRequest;
import com.amaro.Euclidean.repository.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private AmaroProperties properties;

    @Autowired
    private ProductRepository repository;


    @Override
    public List<Product> getAllProducts() throws Exception {
        return repository.findAll()
                .stream()
                .map(product ->
                        Product.builder()
                                .id(product.getId())
                                .name(product.getName())
                                .tags(product.getTags())
                                .tagsVector(product.getTagsVector()).build()
                ).collect(Collectors.toList());

    }

    //        final BufferedReader br = new BufferedReader(new InputStreamReader(
//                this.getClass().getResourceAsStream("/static/products.json")));
//
//        StringBuffer sb = new StringBuffer();
//        String line;
//        while ((line = br.readLine()) != null) {
//            sb.append(line);
//        }
//
//        ProductRequest productRequest = new ObjectMapper().readValue(sb.toString(), ProductRequest.class);
//
//        productRequest
//                .getProducts()
//                .forEach(
//                        product ->
//                        {
//                            Integer[] arrayTags = toVectorTag(product);
//                            product.setTagsVector( Arrays.asList(arrayTags));
//                        }
//                );
//
//
//        return productRequest.getProducts();
    @Override
    public Product findById(Long id) throws Exception {

        com.amaro.Euclidean.document.Product product = repository.findById(id).orElseThrow(Exception::new);
        return Product.builder()
                .id(product.getId())
                .name(product.getName())
                .tags(product.getTags())
                .tagsVector(product.getTagsVector()).build();
    }

    @Override
    public List<Product> saveProducts(List<Product> products) {
        List<com.amaro.Euclidean.document.Product> psEntity = products.parallelStream()
                .map(
                        product ->
                        {
                            product.setTagsVector(Arrays.asList(toVectorTag(product)));
                            return product.toEntity();
                        }
                ).collect(Collectors.toList());
        repository.saveAll(psEntity);
        return products;
    }

    private Integer[] toVectorTag(Product product) {
        Set<String> tags = properties.getTags();
        Integer[] arrayTags = new Integer[tags.size()];
        Arrays.fill(arrayTags, 0);
        product.getTags().forEach(
                s -> {
                    try {
                        int index = tags.stream().collect(Collectors.toList()).indexOf(s);
                        arrayTags[index] = 1;
                    } catch (Exception e) {
                        System.out.println("Tag not found:" + s);
                    }

                }
        );
        return arrayTags;
    }

    @Override
    public Set<String> getAllTags() {
        return properties.getTags();
    }

}

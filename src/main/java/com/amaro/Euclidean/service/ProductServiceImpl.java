package com.amaro.Euclidean.service;

import com.amaro.Euclidean.config.AmaroProperties;
import com.amaro.Euclidean.model.Product;
import com.amaro.Euclidean.model.ProductSimilarity;
import com.amaro.Euclidean.repository.ProductRepository;
import static com.amaro.Euclidean.utils.VectorUtils.toVectorTag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Comparator;
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
                            product.setTagsVector(Arrays.asList(toVectorTag(product, properties.getTags())));
                            return product.toEntity();
                        }
                ).collect(Collectors.toList());
        repository.saveAll(psEntity);
        return products;
    }



    @Override
    public List<ProductSimilarity> getSimilarityProducts(Long id) throws Exception {
        com.amaro.Euclidean.document.Product product = repository.findById(id).orElseThrow(Exception::new);
        List<com.amaro.Euclidean.document.Product> allProducts = repository.findAll().parallelStream().filter(p -> !p.equals(product)).collect(Collectors.toList());


        List<ProductSimilarity> productSimilarities = allProducts.stream()
                .map(
                        p -> ProductSimilarity
                                .builder()
                                .id(p.getId())
                                .name(p.getName())
                                .similarity(calculateSimilarity(product.getTagsVector(), p.getTagsVector())).build()
                )
                .sorted(Comparator.comparing(ProductSimilarity::getSimilarity).reversed())
                .limit(3)
                .collect(Collectors.toList());

        return productSimilarities;

    }

    private Float calculateSimilarity(List<Integer> p1, List<Integer> p2 ){
        Float d = 0.0F;

        Float calc = 0.0F;

        for(int i=0; i < p1.size(); i++){
            calc += (float) Math.pow( (p1.get(i) - p2.get(i)),2);
        }

        d +=  (float)Math.sqrt(calc);

        Float s = 1 /(1.0F + d);

        return s;
    }

    @Override
    public Set<String> getAllTags() {
        return properties.getTags();
    }

}

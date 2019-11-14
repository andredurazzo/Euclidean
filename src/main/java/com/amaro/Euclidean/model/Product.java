package com.amaro.Euclidean.model;

import lombok.*;

import java.util.List;
import java.util.Set;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    private Long id;
    private String name;
    private Set<String> tags;
    private List<Integer> tagsVector;

    public com.amaro.Euclidean.document.Product toEntity(){
        return com.amaro.Euclidean.document.Product.builder().id(this.id)
                .name(this.name)
                .tags(this.tags)
                .tagsVector(this.tagsVector).build();
    }
}

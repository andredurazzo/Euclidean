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

}

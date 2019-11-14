package com.amaro.Euclidean.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class ProductSimilarity {

    private Long id;
    private String name;
    private Float similarity;
}

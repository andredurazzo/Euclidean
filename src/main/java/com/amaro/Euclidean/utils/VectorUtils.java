package com.amaro.Euclidean.utils;

import com.amaro.Euclidean.model.Product;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class VectorUtils {

    public static Integer[] toVectorTag(Product product, Set<String> tags) {
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
}

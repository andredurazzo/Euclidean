package com.amaro.Euclidean.config;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ConfigurationProperties(prefix = "amaro" )
@Component
public class AmaroProperties {
   private Set<String> tags;
}

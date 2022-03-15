package com.example.productlistcsv.to;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NamePriceTo {
  private String name;
  private Integer price;
}

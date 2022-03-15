package com.example.productlistcsv.to;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NameFrequencyTo {
  private String name;
  private Integer frequency;
}

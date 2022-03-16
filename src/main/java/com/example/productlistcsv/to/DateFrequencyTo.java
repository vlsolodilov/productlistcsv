package com.example.productlistcsv.to;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class DateFrequencyTo {
  private LocalDate date;
  private Integer frequency;
}

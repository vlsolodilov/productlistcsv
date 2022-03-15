package com.example.productlistcsv.util;

import com.example.productlistcsv.model.Product;
import com.example.productlistcsv.model.ProductPrice;
import com.example.productlistcsv.to.DateFrequencyTo;
import com.example.productlistcsv.to.NameFrequencyTo;
import com.example.productlistcsv.to.NamePriceTo;
import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@UtilityClass
public class ToUtil {

  public static List<NamePriceTo> getListNamePriceTo(List<ProductPrice> productPriceList) {
    Map<Product, List<ProductPrice>> map = productPriceList.stream().collect(Collectors.groupingBy(ProductPrice::getProduct));
    List<NamePriceTo> list = new ArrayList<>();
    for (Product p : map.keySet()) {
      ProductPrice pp = Collections.max(map.get(p), Comparator.comparing(ProductPrice::getDatetime));
      list.add(new NamePriceTo(p.getName(), pp.getPrice()));
    }
    return list;
  }

  public static List<NameFrequencyTo> getListNameFrequencyTo(List<ProductPrice> productPriceList) {
    Map<String, Integer> nameFrequencyMap = new HashMap<>();
    productPriceList.forEach(productPrice -> nameFrequencyMap.merge(productPrice.getProduct().getName(),1, Integer::sum));
    return nameFrequencyMap.entrySet().stream()
            .map(entry -> new NameFrequencyTo(entry.getKey(), entry.getValue()))
            .collect(Collectors.toList());
  }

  public static List<DateFrequencyTo> getListDateFrequencyTo(List<ProductPrice> productPriceList) {
    Map<LocalDate, Integer> dateFrequencyMap = new HashMap<>();
    productPriceList.forEach(productPrice -> dateFrequencyMap.merge(productPrice.getDatetime(),1, Integer::sum));
    return dateFrequencyMap.entrySet().stream()
            .map(entry -> new DateFrequencyTo(entry.getKey(), entry.getValue()))
            .collect(Collectors.toList());
  }
}

package com.example.productlistcsv.web;

import com.example.productlistcsv.repository.ProductPriceRepository;
import com.example.productlistcsv.repository.ProductRepository;
import com.example.productlistcsv.to.DateFrequencyTo;
import com.example.productlistcsv.to.NameFrequencyTo;
import com.example.productlistcsv.to.NamePriceTo;
import com.example.productlistcsv.util.ToUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = MainController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class MainController {
  static final String REST_URL = "/api/products";

  private final ProductRepository productRepository;
  private final ProductPriceRepository productPriceRepository;

  @Autowired
  public MainController(
      ProductRepository productRepository,
      ProductPriceRepository productPriceRepository) {
    this.productRepository = productRepository;
    this.productPriceRepository = productPriceRepository;
  }

  @GetMapping
  public List<NamePriceTo> getListNamePrice(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
    log.info("getListNamePrice {}", date);
    return ToUtil.getListNamePriceTo(productPriceRepository.getAllByDate(date));
  }

  @GetMapping("/statistic/amount")
  public int getAmountProduct() {
    log.info("getAmountProduct");
    return productRepository.getAmountProduct();
  }

  @GetMapping("/statistic/names")
  public List<NameFrequencyTo> getListNameFrequency() {
    log.info("getListNameFrequency");
    return ToUtil.getListNameFrequencyTo(productPriceRepository.findAll());
  }

  @GetMapping("/statistic/dates")
  public List<DateFrequencyTo> getListDateFrequency() {
    log.info("getListDateFrequency");
    return ToUtil.getListDateFrequencyTo(productPriceRepository.findAll());
  }
}

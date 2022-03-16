package com.example.productlistcsv.util;

import com.example.productlistcsv.repository.ProductPriceRepository;
import com.example.productlistcsv.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
public class ScheduledTasks {

    private final ProductRepository productRepository;
    private final ProductPriceRepository productPriceRepository;

    @Value("${dir}")
    private String dir;

    @Autowired
    public ScheduledTasks(
            ProductRepository productRepository,
            ProductPriceRepository productPriceRepository) {
        this.productRepository = productRepository;
        this.productPriceRepository = productPriceRepository;
    }

    @Scheduled(cron = "${cron}")
    public void importCsv() {
        log.info("Import csv files:");
        try {
            CsvUtil.csvToDB(dir, productRepository, productPriceRepository);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

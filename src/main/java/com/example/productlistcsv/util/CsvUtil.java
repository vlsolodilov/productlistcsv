package com.example.productlistcsv.util;

import com.example.productlistcsv.model.Product;
import com.example.productlistcsv.model.ProductPrice;
import com.example.productlistcsv.repository.ProductPriceRepository;
import com.example.productlistcsv.repository.ProductRepository;
import lombok.experimental.UtilityClass;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class CsvUtil {

    public static void csvToDB(String dir, ProductRepository productRepository, ProductPriceRepository productPriceRepository) throws IOException {
        Path directory = Paths.get(dir);
        Files.walkFileTree(directory, new SimpleFileVisitor<Path>(){
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                try (BufferedReader fileReader = new BufferedReader(new FileReader(file.toFile()));
                     CSVParser csvParser = new CSVParser(fileReader,
                             CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {
                    List<Product> productList = new ArrayList<>();
                    List<ProductPrice> productPriceList = new ArrayList<>();
                    Iterable<CSVRecord> csvRecords = csvParser.getRecords();
                    for (CSVRecord csvRecord : csvRecords) {
                        Product product = new Product(Integer.parseInt(csvRecord.get("product_id")), csvRecord.get("product_name"));
                        ProductPrice productPrice = new ProductPrice(
                                Integer.parseInt(csvRecord.get("price_id")),
                                Integer.parseInt(csvRecord.get("price")),
                                LocalDate.parse(csvRecord.get("price_datetime"), DateTimeFormatter.ISO_LOCAL_DATE),
                                product
                        );
                        productList.add(product);
                        productPriceList.add(productPrice);
                    }
                    productRepository.saveAll(productList);
                    productPriceRepository.saveAll(productPriceList);
                } catch (IOException e) {
                    throw new RuntimeException("fail to import CSV file: " + e.getMessage());
                }
                Files.delete(file);
                return FileVisitResult.CONTINUE;
            }
        });
    }
}

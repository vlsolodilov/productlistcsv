package com.example.productlistcsv.repository;

import com.example.productlistcsv.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query("SELECT COUNT(*) FROM Product")
    int getAmountProduct();
}

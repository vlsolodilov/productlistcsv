package com.example.productlistcsv.repository;

import com.example.productlistcsv.model.ProductPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface ProductPriceRepository extends JpaRepository<ProductPrice, Integer> {

  @Query("SELECT pp FROM ProductPrice pp WHERE pp.datetime<=:datetime")
  List<ProductPrice>getAllByDate(@Param("datetime") LocalDate datetime);
}

package com.example.productlistcsv.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "product")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Product implements Serializable {
  @Id
  private Integer id;

  @Column(name = "name", nullable = false)
  private String name;

}

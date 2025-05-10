package org.example.productdocker.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "Product")
@Entity
public class Product {
  @Id
  @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
  private Integer id; // max length 36
  @Column(nullable = false)
  private String name; // max length 100
  private String description; // max length 255
  @Column(nullable = false)
  private BigDecimal price; // precision 10, scale 2

  private Timestamp createdAt;

  @PrePersist
  protected void onCreate() {
    if (this.createdAt != null) {
      throw new IllegalArgumentException("createdAt should not be manually set. It is auto-generated.");
    }
    this.createdAt = Timestamp.from(Instant.now());
  }
}

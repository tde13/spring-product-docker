package org.example.productdocker.repo;

import org.example.productdocker.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product, Integer> {

    // Custom query methods can be defined here if needed
    // For example: List<Product> findByName(String name);

}

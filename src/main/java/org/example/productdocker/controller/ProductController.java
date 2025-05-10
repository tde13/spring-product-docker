package org.example.productdocker.controller;

import org.example.productdocker.entity.Product;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.example.productdocker.repo.ProductRepo;

@RestController
@RequestMapping("/api/products")
public class ProductController {

  @Autowired
  private ProductRepo productRepo;

  @GetMapping("/findAll")
  public List<Product> findAll() {
    return productRepo.findAll();
  }

  @PostMapping("/save")
  public Product save(@RequestBody Product product) {
    return productRepo.save(product);
  }

  @GetMapping("/findById")
  public Product findById(@RequestParam Integer id) {
    return productRepo.findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found with id: " + id));
  }

  @DeleteMapping("/deleteById")
  public void deleteById(@RequestParam Integer id) {
    if (!productRepo.existsById(id)) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found with id: " + id);
    }
    productRepo.deleteById(id);
  }

  @PutMapping ("/update")
  public Product updateProduct(@RequestBody Product updatedProduct) {
    Product existingProduct = productRepo.findById(updatedProduct.getId())
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found with id: " + updatedProduct.getId()));

    if (updatedProduct.getName() != null) {
      existingProduct.setName(updatedProduct.getName());
    }
    if (updatedProduct.getDescription() != null) {
      existingProduct.setDescription(updatedProduct.getDescription());
    }
    if (updatedProduct.getPrice() != null) {
      existingProduct.setPrice(updatedProduct.getPrice());
    }
    // createdAt is not updated on purpose to preserve original creation time

    return productRepo.save(existingProduct);
  }




}

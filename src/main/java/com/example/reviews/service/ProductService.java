package com.example.reviews.service;

import com.example.reviews.entity.mysql.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
   Product persist(Product product);
   Optional<Product> getProductById(Integer id);
   List<Product> findAllProducts();
}

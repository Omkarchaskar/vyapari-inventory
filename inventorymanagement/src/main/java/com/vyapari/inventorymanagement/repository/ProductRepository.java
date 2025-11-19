package com.vyapari.inventorymanagement.repository;

import com.vyapari.inventorymanagement.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    // 🔍 Search by name
    List<Product> findByNameContainingIgnoreCase(String name);
}

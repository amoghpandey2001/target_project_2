package com.targetready.InventoryService.repository;

import com.targetready.InventoryService.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {
}

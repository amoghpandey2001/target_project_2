package com.targetready.InventoryService.repository;

import com.targetready.InventoryService.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;



public interface InventoryRepository extends JpaRepository<Inventory, String> {
    Inventory findByProductId(String productId);
}


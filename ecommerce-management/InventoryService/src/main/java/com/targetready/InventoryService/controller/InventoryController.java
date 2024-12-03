package com.targetready.InventoryService.controller;

import com.targetready.InventoryService.dto.InventoryDTO;
import com.targetready.InventoryService.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @GetMapping
    public List<InventoryDTO> getAllInventory() {
        return inventoryService.getAllInventory();
    }

    @PostMapping
    public InventoryDTO addInventory(@RequestBody InventoryDTO inventoryDTO) {
        return inventoryService.addInventory(inventoryDTO);
    }

    @PutMapping("/{productId}")
    public InventoryDTO updateInventory(@PathVariable String productId, @RequestBody InventoryDTO inventoryDto) {
        return inventoryService.updateInventory(productId, inventoryDto.getQuantity());
    }
}


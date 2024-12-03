

package com.targetready.InventoryService.service;

import com.targetready.InventoryService.dto.InventoryDTO;
import com.targetready.InventoryService.mapper.InventoryMapper;
import com.targetready.InventoryService.model.Inventory;
import com.targetready.InventoryService.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private InventoryMapper inventoryMapper;

    public List<InventoryDTO> getAllInventory() {
        return inventoryRepository.findAll().stream()
                .map(inventoryMapper::toInventoryDTO)
                .collect(Collectors.toList());
    }

    public InventoryDTO addInventory(InventoryDTO inventoryDTO) {
        Inventory inventory = inventoryMapper.toInventory(inventoryDTO);
        Inventory savedInventory = inventoryRepository.save(inventory);
        return inventoryMapper.toInventoryDTO(savedInventory);
    }

    public InventoryDTO updateInventory(String productId, int quantity_ordered) {
        Inventory inventory = inventoryRepository.findByProductId(productId);

        if (inventory != null) {
            int quantity_stored=inventory.getQuantity();
            if (quantity_stored < quantity_ordered) {
                throw new IllegalArgumentException("Insufficient quantity in inventory");
            }
            inventory.setQuantity(quantity_stored-quantity_ordered);
            Inventory updatedInventory = inventoryRepository.save(inventory);
            return inventoryMapper.toInventoryDTO(updatedInventory);
        }
        return null;
    }
}




package com.targetready.InventoryService.mapper;

import com.targetready.InventoryService.dto.InventoryDTO;
import com.targetready.InventoryService.model.Inventory;
import org.springframework.stereotype.Component;

@Component
public class InventoryMapper {
    public InventoryDTO toInventoryDTO(Inventory inventory) {
        InventoryDTO inventoryDTO = new InventoryDTO();
        inventoryDTO.setProductId(inventory.getProductId());
        inventoryDTO.setQuantity(inventory.getQuantity());
        return inventoryDTO;
    }

    public Inventory toInventory(InventoryDTO inventoryDTO) {
        Inventory inventory = new Inventory();
        inventory.setProductId(inventoryDTO.getProductId());
        inventory.setQuantity(inventoryDTO.getQuantity());
        return inventory;
    }
}

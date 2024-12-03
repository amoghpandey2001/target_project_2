package com.targetready.InventoryService.mapper;

import com.targetready.InventoryService.dto.OrderDTO;
import com.targetready.InventoryService.model.Inventory;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    public Inventory toInventory(OrderDTO orderDTO) {
        Inventory inventory = new Inventory();
        inventory.setProductId(orderDTO.getProductId());
        inventory.setQuantity(orderDTO.getQuantity());
        return inventory;
    }
}


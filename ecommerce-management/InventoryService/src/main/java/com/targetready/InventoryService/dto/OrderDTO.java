package com.targetready.InventoryService.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDTO {
    private String orderId;
    private double amount;
    private String bank;
    private int quantity;
    private String productId;
}

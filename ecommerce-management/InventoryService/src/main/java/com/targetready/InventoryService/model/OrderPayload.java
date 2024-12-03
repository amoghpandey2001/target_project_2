package com.targetready.InventoryService.model;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderPayload {
    private String order_id;
    private double amount;
    private String bank;
    private String product_id;
    private int quantity;
    private boolean status;
    private String transaction_id;

}

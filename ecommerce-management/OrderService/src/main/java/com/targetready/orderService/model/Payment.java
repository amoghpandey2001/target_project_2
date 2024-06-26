package com.targetready.orderService.model;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Payment {
    private String orderId;

    private double amount;

    private String transactionId;

    private String bank;

    private String productId;

    private Long  quantity;
}

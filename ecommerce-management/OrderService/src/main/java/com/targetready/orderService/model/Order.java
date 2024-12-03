package com.targetready.orderService.model;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Order {
    private String orderId;
    private double amount;
    private String bank;
    private String productId;
    private Long  quantity;
}

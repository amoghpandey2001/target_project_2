package com.targetready.orderService.helper;

import java.util.UUID;

public class OrderIdGenerator {
    public static String generateTransactionId() {
        return UUID.randomUUID().toString();
    }
}

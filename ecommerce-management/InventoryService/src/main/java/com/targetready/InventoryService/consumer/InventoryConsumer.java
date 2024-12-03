package com.targetready.InventoryService.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.targetready.InventoryService.service.InventoryService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.JsonNode;

@Service
public class InventoryConsumer {
    private final ObjectMapper objectMapper;
    private final InventoryService inventoryService;

    @Autowired
    public InventoryConsumer(ObjectMapper objectMapper, InventoryService inventoryService) {
        this.objectMapper = objectMapper;
        this.inventoryService = inventoryService;
    }

    @KafkaListener(topics = "changes.public.orders", groupId = "changes-group")
    public void consume(ConsumerRecord<String,String> record) {
        try {

            JsonNode jsonNode = objectMapper.readTree(record.value());
            String productId = jsonNode.get("product_id").asText();
            int quantity_ordered = jsonNode.get("quantity").asInt();
            System.out.println(jsonNode.toPrettyString());
            System.out.println(productId + quantity_ordered);
            inventoryService.updateInventory(productId,quantity_ordered);


        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}

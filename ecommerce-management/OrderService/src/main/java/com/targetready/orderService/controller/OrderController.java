package com.targetready.orderService.controller;


import com.targetready.orderService.dto.InvoiceDTO;
import com.targetready.orderService.dto.OrderDTO;
import com.targetready.orderService.service.KafkaProducerService;
import com.targetready.orderService.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/target")
public class OrderController {

    @Autowired
    private KafkaProducerService kafkaProducerService;


    @Autowired
    private OrderService orderService;



    @PostMapping("/orders")
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO orderDTO) {
        OrderDTO createdOrder = orderService.createOrder(orderDTO);
        return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
    }
    @GetMapping("/all-orders")
    public ResponseEntity<List<InvoiceDTO>> getAllOrders() {
        System.out.println("Getting Order Invoices");
        List<InvoiceDTO> invoices = orderService.getAllOrders();
        return new ResponseEntity<>(invoices, HttpStatus.OK);

    }










}


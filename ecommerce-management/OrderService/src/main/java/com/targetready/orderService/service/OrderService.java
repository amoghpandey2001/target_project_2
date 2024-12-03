package com.targetready.orderService.service;

import brave.Span;
import brave.Tracing;
import brave.kafka.clients.KafkaTracing;
import com.targetready.orderService.dto.InvoiceDTO;
import com.targetready.orderService.dto.OrderDTO;
import com.targetready.orderService.helper.OrderIdGenerator;
import com.targetready.orderService.mapper.InvoiceMapper;
import com.targetready.orderService.mapper.OrderMapper;
import com.targetready.orderService.model.Order;
import com.targetready.orderService.repository.InvoiceRepository;
import com.targetready.orderService.model.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.targetready.orderService.helper.OrderIdGenerator.generateTransactionId;

@Service
public class OrderService {

    private final InvoiceRepository invoiceRepository;

    @Autowired
    public OrderService(InvoiceRepository invoiceRepository, KafkaTemplate<String, Object> kafkaTemplate){

        this.invoiceRepository = invoiceRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void saveInvoice(Invoice invoice) {
        invoiceRepository.save(invoice);
    }


    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private InvoiceMapper invoiceMapper;

    @Autowired
    private KafkaTracing kafkaTracing;

    @Autowired
    private Tracing tracing;

    private final KafkaTemplate<String, Object> kafkaTemplate;


    public OrderDTO createOrder(OrderDTO orderDTO){
        orderDTO.setOrderId(generateTransactionId());
        Order order = orderMapper.toEntity(orderDTO);
        sendOrder(order);
        return orderMapper.toDto(order);

    }


    public List<InvoiceDTO> getAllOrders() {
        List<Invoice> invoices = invoiceRepository.findAll();
        return invoices.stream()
                .map(invoiceMapper::toDto)
                .collect(Collectors.toList());
    }





    public void sendOrder(Order order) {
        Span span = tracing.tracer().nextSpan().name("order").start();

        try{
            validateOrder(order);
            span.tag("success", "Valid order");
            kafkaTemplate.send("orders", order);
        }
        catch (IllegalArgumentException e) {
            span.tag("error",e.getMessage());
        }
        catch(Exception e) {
            span.tag("error", "An error occurred while processing the order: " + e.getMessage());

        }
        finally {
            span.finish();
        }
    }

    private void validateOrder(Order order) {
        if(order==null || order.getOrderId()==null) {
            throw new IllegalArgumentException("Invalid order data");
        }
        if(order.getAmount()<0) {
            throw new IllegalArgumentException("Price should be greater than 0");
        }

    }
}

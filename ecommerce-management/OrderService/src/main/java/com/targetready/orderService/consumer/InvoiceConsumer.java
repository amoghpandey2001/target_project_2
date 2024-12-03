package com.targetready.orderService.consumer;


import com.targetready.orderService.service.OrderService;
import com.targetready.orderService.model.Invoice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class InvoiceConsumer {
    private static final Logger logger = LoggerFactory.getLogger(InvoiceConsumer.class);
   private  Invoice invoice;
    @Autowired

    private final OrderService orderService;

    public InvoiceConsumer(OrderService orderService) {
        this.orderService = orderService;
    }


    @KafkaListener(topics = "invoices", groupId = "invoice-group")
    public void consume(Invoice invoice) {
        logger.info("Received Invoices: {}", invoice);
        try {
            orderService.saveInvoice(invoice);
        }catch (Exception e) {
            logger.error("Error processing payment: {}",invoice, e);
        }


    }

}

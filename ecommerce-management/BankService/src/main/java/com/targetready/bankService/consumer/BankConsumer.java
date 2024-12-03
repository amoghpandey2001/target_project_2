package com.targetready.bankService.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.targetready.orderService.model.Invoice;
import com.targetready.orderService.model.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.targetready.bankService.producer.BankProducer;
@Service
public class BankConsumer {
    private static final Logger logger = LoggerFactory.getLogger(BankConsumer.class);
   private  Invoice invoice;
    @Autowired
    private  BankProducer bankProducer;
//    private final InvoiceService invoiceService;

//    public BankConsumer( InvoiceService invoiceService) {
//        this.invoiceService = invoiceService;
//    }


    @KafkaListener(topics = "payments", groupId = "payment-group")
    public void consume(Payment payment) {
        logger.info("Received payment: {}", payment);
        try {
            invoice = new Invoice();
            invoice.setOrderId(payment.getOrderId());
            invoice.setAmount(payment.getAmount());
            invoice.setTransactionId(payment.getTransactionId());
            invoice.setStatus(true);
            invoice.setBank(payment.getBank());
            invoice.setProductId(payment.getProductId());
            invoice.setQuantity(payment.getQuantity());


            bankProducer.sendInvoice(invoice);
            logger.info("Invoice sent to producer: {}", invoice);


        }catch (Exception e) {
            logger.error("Error processing payment: {}", payment, e);
        }


    }

}

package com.jeni.notification.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;


@Service
@Slf4j
@RequiredArgsConstructor
public class NotificationService {

    @Autowired
    private final JavaMailSender javaMailSender;

    @KafkaListener(topics = "order-placed", groupId = "group_id")
//    public void consume(String value) {
//        System.out.println("Consumed message: " + value);
       public void consume(com.jeni.order.dto.OrderRequest orderRequest) {
        log.info("Got Message from order-placed topic {}", orderRequest);
       MimeMessagePreparator messagePreparator = mimeMessage -> {
             MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
             messageHelper.setFrom("jeniarulcse@gmail.com");
                messageHelper.setTo(orderRequest.userDetails().email());
                messageHelper.setSubject(String.format("Your Order with OrderNumber %s is placed successfully", orderRequest.id()));
                messageHelper.setText(String.format("""
                        Hi %s %s,
                        
                        Your order with order number %s is now placed successfully.
                        
                        Best Regards,
                        """, orderRequest.userDetails().firstName(),
                        orderRequest.userDetails().lastName(),
                        orderRequest.id()));
        };
        try {
            javaMailSender.send(messagePreparator);
            log.info("Order Notification email sent!!");
        } catch (MailException e) {
            log.error("Exception occurred when sending mail", e);
            throw new RuntimeException("Exception occurred when sending mail to "+orderRequest.userDetails().email(), e);
        }
    }
}

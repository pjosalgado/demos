package com.example.demo1;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EventListener {

    @KafkaListener(topics = "${topics.groupid-test}", groupId = "${spring.kafka.consumer.group-id}")
    public void consumer(String order) {
        log.info("Message received: " + order);
    }

}

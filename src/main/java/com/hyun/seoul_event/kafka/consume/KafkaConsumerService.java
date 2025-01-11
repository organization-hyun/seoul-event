package com.hyun.seoul_event.kafka.consume;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaConsumerService {

    @KafkaListener(topics = "seoul-event-topic", groupId = "example-group")
    public void listen(String message) {
        log.info("Consumed message: {}", message);
    }
}

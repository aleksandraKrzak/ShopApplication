package com.aleksandrakrzak.shop.kafka;

import lombok.extern.slf4j.Slf4j;
//import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Receiver {

//    @KafkaListener(topics = "andrzej")
    public void receive(String text) {
        log.info(text);
    }

}

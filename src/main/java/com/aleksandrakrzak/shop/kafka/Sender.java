package com.aleksandrakrzak.shop.kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Sender {

//    private final KafkaTemplate<String, String> template;

    public void send(String message) {
//        template.send("andrzej", message);
    }

}

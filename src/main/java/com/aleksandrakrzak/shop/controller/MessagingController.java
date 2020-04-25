package com.aleksandrakrzak.shop.controller;

import com.aleksandrakrzak.shop.kafka.Sender;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("api/messaging")
@RequiredArgsConstructor
public class MessagingController {

    private final Sender sender;

    @GetMapping
    public void send(@RequestParam String message) {
        sender.send(message);
    }

}

package com.produce.producer.controller;

import com.produce.producer.model.WebEvent;
import com.produce.producer.producers.WebEventProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/data")
public class WebEventController {

    @Autowired
    private WebEventProducer producer;

    @PostMapping
    public ResponseEntity collectData(@RequestBody WebEvent event) {
        producer.produceWebEvent(event);
        return new ResponseEntity(HttpStatus.OK);
    }
}

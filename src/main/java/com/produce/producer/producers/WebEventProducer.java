package com.produce.producer.producers;


import com.produce.producer.model.WebEvent;
import com.produce.producer.util.ApplicationProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class WebEventProducer {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Autowired
    private ApplicationProperties properties;

    public void produceWebEvent(WebEvent event) {
        List<Header> recordHeaders = new ArrayList<>();
        recordHeaders.add(new RecordHeader("data_version", event.getDataVersion().getBytes()));
        ProducerRecord<String, String> record = new ProducerRecord(properties.topics.get(event.getEventType())
                , null, constructMessage(event));
        ListenableFuture<SendResult<String, String>> listenableFuture = kafkaTemplate.send(record);
        listenableFuture.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onFailure(Throwable ex) {
            handleFailure(ex, record.value());
            }

            @Override
            public void onSuccess(SendResult<String, String> result) {
                log.info("message published successFully");
            }
        });

    }
    private String constructMessage(WebEvent event){
        return event.getDataVersion()+";"+event.getData();
    }

    private void handleFailure(Throwable ex, String value) {
        log.error("Error sending the message, exception is {}", ex.getMessage());
        log.info(value);
    }
}

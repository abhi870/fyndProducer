package com.produce.producer.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ApplicationProperties {

    @Value("${events.topicone}")
    public String topicOne;

    @Value("${events.topictwo}")
    public String topicTwo;

    public Map<String, String> topics;

    public ApplicationProperties(){
        topics = new HashMap<>();
        topics.put("topicone", "topic1");
        topics.put("topictwo", "topic2");
    }
}

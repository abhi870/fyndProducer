package com.produce.producer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WebEvent {
    private Integer key;
    private String eventType;
    private String data;
    private String dataVersion;
}

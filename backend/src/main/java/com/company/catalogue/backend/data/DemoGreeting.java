package com.company.catalogue.backend.data;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DemoGreeting {
    private long id;
    private String content;
}

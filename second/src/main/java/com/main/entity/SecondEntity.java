package com.main.entity;

import lombok.*;

@Builder
@Data
public class SecondEntity {
    private Long id;
    private String name;
    private Boolean isAvailable;
}

package com.hvn.springkafka.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String name;
    private String dept;
    private Long salary;
}

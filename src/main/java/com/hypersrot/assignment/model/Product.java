package com.hypersrot.assignment.model;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Product {
    @Id
    private String id;
    private Long ordered;
    private Long price;
    private Long available;
}

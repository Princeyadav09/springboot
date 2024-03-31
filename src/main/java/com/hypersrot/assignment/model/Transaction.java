package com.hypersrot.assignment.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Transaction {

    private String id;
    private String orderId;
    private String userId;
    private String transactionId;
    private String status;
}

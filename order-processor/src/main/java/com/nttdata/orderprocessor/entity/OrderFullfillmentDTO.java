package com.nttdata.orderprocessor.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OrderFullfillmentDTO {
    Long customerId;
    Long productId;
}

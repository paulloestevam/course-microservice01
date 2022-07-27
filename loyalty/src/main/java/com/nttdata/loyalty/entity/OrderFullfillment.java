package com.nttdata.loyalty.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OrderFullfillment {
    Long customerId;
    Long productId;
}

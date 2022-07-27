package com.nttdata.orderprocessor.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@ToString
@Entity
@Table(name = "order_fullfillment")
public class OrderFullfillment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    Long customerId;
    Long productId;
    Date dateOrder;

    public OrderFullfillment() {
        super();
    }

    public OrderFullfillment(Long customerId, Long productId) {
        this.customerId = customerId;
        this.productId = productId;
    }










}

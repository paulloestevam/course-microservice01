package com.nttdata.loyalty.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@ToString
@Entity
@Table(name = "loyalty_customer_info")
public class LoyaltyCustomerInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    Long idCustomer;
    int points;

    public LoyaltyCustomerInfo() {
        super();
    }

    public LoyaltyCustomerInfo(Long idCustomer, int points) {
        this.idCustomer = idCustomer;
        this.points = points;
    }
}

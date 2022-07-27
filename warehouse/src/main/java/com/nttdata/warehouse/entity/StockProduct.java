package com.nttdata.warehouse.entity;

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
@Table(name = "stock_product")
public class StockProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    Long productId;
    String status;

    public StockProduct() {
        super();
    }

    public StockProduct(Long productId, String status) {
        this.productId = productId;
        this.status = status;
    }
}

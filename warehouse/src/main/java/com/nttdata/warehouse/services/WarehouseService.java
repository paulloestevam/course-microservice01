package com.nttdata.warehouse.services;

import com.nttdata.warehouse.entity.OrderFullfillment;
import com.nttdata.warehouse.entity.StockProduct;
import com.nttdata.warehouse.jpa.StockProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WarehouseService {

    @Autowired
    StockProductRepository stockProductRepository;

    public String checkStockAndReserve(OrderFullfillment order) {

        StockProduct stockProduct = stockProductRepository.findByProductId(order.getProductId());

        if (stockProduct.getStatus().equals("AVAILABLE")) {
            stockProduct.setStatus("RESERVED");
            stockProductRepository.save(stockProduct);
            return "ok";
        } else {
            return "not ok";
        }

    }

    public String packageAndSendOrder(OrderFullfillment order) {
        StockProduct stockProduct = stockProductRepository.findByProductId(order.getProductId());

        if (stockProduct.getStatus().equals("RESERVED")) {
            stockProduct.setStatus("IN DELIVERY");
            stockProductRepository.save(stockProduct);
            return "ok";
        } else {
            return "not ok";
        }
    }

    public List getAll() {
        return stockProductRepository.findAll();
    }
}

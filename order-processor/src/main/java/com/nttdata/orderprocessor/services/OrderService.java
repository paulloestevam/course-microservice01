package com.nttdata.orderprocessor.services;

import com.nttdata.orderprocessor.entity.OrderFullfillment;
import com.nttdata.orderprocessor.jpa.OrderFullfillmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    OrderFullfillmentRepository orderFullfillmentRepository;

    public String saveOrder(OrderFullfillment orderFullfillment) {
        orderFullfillmentRepository.save(orderFullfillment);
        return "ok saveOrder";
    }

    public List getAll() {
        return orderFullfillmentRepository.findAll();
    }
}

package com.nttdata.orderprocessor.controller;


import com.nttdata.orderprocessor.entity.OrderFullfillment;
import com.nttdata.orderprocessor.entity.OrderFullfillmentDTO;
import com.nttdata.orderprocessor.services.LoyaltyService;
import com.nttdata.orderprocessor.services.OrderService;
import com.nttdata.orderprocessor.services.PaymentService;
import com.nttdata.orderprocessor.services.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/orderprocessor")
public class OrderController {
    @Autowired
    WarehouseService warehouseService;
    @Autowired
    PaymentService paymentService;
    @Autowired
    LoyaltyService loyaltyService;
    @Autowired
    OrderService orderService;


    @PostMapping("/orderFullfillment")
    public String orderFullfillment(@RequestBody OrderFullfillmentDTO orderFullfillmentDTO) {
        System.out.println("-> POST /api/v1/orderprocessor/orderFullfillment " + new Date());

        String returnWarehouse = warehouseService.checkStockAndReserve(orderFullfillmentDTO);
        String returnPayment = paymentService.takeMoneyFromCustomer(orderFullfillmentDTO);
        String returnLoyalty = loyaltyService.awardPointsToCustomer(orderFullfillmentDTO);
        String returnWarehouseSendOrder = warehouseService.packageAndSendOrder(orderFullfillmentDTO);


        OrderFullfillment orderFullfillment = new OrderFullfillment();
        orderFullfillment.setCustomerId(orderFullfillmentDTO.getCustomerId());
        orderFullfillment.setProductId(orderFullfillmentDTO.getProductId());
        orderFullfillment.setDateOrder(new Date());

        String returnOrder = orderService.saveOrder(orderFullfillment);

        return new Date() +
                " Pedido realizado - ".
                        concat(returnWarehouse).
                        concat(" - ").
                        concat(returnPayment).
                        concat(" - ").
                        concat(returnLoyalty).
                        concat(" - ").
                        concat(returnWarehouseSendOrder).
                        concat(" - ").
                        concat(returnOrder);
    }

    @GetMapping("")
    public String getAll() {
        System.out.println("-> GET /api/v1/orderprocessor " + new Date());
        List listOrder = orderService.getAll();
        return "->>  listOrder:" + listOrder;
    }

}

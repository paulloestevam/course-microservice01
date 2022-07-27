package com.nttdata.warehouse.controller;


import com.nttdata.warehouse.entity.OrderFullfillment;
import com.nttdata.warehouse.services.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/warehouse")
public class WarehouseController {
    @Autowired
    WarehouseService warehouseService;

    @PostMapping("/checkStoreAndReserve")
    public ResponseEntity<String> checkStoreAndReserve(@RequestBody OrderFullfillment order) {
        System.out.println("-> POST /api/v1/warehouse/checkStoreAndReserve " + new Date());

        warehouseService.checkStockAndReserve(order);

        return new ResponseEntity<>("ok checkStoreAndReserve", HttpStatus.OK);
    }

    @PostMapping("/packageAndSendOrder")
    public String packageAndSendOrder(@RequestBody OrderFullfillment order) {
        System.out.println("-> POST /api/v1/warehouse/packageAndSendOrder " + new Date());

        warehouseService.packageAndSendOrder(order);

        return "ok checkStoreAndReserve";
    }

    @GetMapping("")
    public String getAll() {
        System.out.println("-> GET /api/v1/warehouse " + new Date());

        List listStockProduct = warehouseService.getAll();

        return "->>  listStockProduct:" + listStockProduct;
    }


}

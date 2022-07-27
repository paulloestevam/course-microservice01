package com.nttdata.loyalty.controller;


import com.nttdata.loyalty.entity.OrderFullfillment;
import com.nttdata.loyalty.services.LoyaltyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/loyalty")
public class LoyaltyController {
    @Autowired
    LoyaltyService loyaltyService;

    @PostMapping("/awardPointsToCustomer")
    public String awardPointsToCustomer(@RequestBody OrderFullfillment order) {
        System.out.println("-> POST /api/v1/loyalty/awardPointsToCustomer " + new Date());

        loyaltyService.awardPointsToCustomer(order);

        return "ok awardPointsToCustomer";
    }

    @GetMapping("")
    public String getAll() {
        System.out.println("-> GET /api/v1/loyalty " + new Date());

        List listLoyalty = loyaltyService.getAll();

        return "->>  listLoyalty:" + listLoyalty;
    }


}

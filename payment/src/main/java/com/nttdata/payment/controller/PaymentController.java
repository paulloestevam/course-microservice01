package com.nttdata.payment.controller;


import com.nttdata.payment.entity.OrderFullfillment;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/v1/payment")
public class PaymentController {

    @PostMapping("/takeMoneyFromCustomer")
    public String takeMoneyFromCustomer(@RequestBody OrderFullfillment order) {
        System.out.println("-> POST /api/v1/payment/takeMoneyFromCustomer " + new Date());

        return "ok takeMoneyFromCustomer";
    }

    @GetMapping("")
    public String ok() {
        return "ok";
    }

    @GetMapping("/teste")
    public String teste() {
        return "ok teste";
    }


}

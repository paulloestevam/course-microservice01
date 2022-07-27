package com.nttdata.orderprocessor.services;

import com.nttdata.orderprocessor.entity.OrderFullfillmentDTO;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PaymentService {
    public String takeMoneyFromCustomer(OrderFullfillmentDTO order) {
        String url = "http://payment:8092/api/v1/payment/takeMoneyFromCustomer";
//        String url = "http://localhost:8092/api/v1/payment/takeMoneyFromCustomer";
        return new RestTemplate().exchange(url,
                HttpMethod.POST,
                new HttpEntity<>(order),
                String.class).getBody();
    }
}

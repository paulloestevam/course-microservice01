package com.nttdata.orderprocessor.services;

import com.nttdata.orderprocessor.entity.OrderFullfillmentDTO;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LoyaltyService {
    public String awardPointsToCustomer(OrderFullfillmentDTO order) {
        String url = "http://loyalty:8093/api/v1/loyalty/awardPointsToCustomer";
//        String url = "http://localhost:8093/api/v1/loyalty/awardPointsToCustomer";
        return new RestTemplate().exchange(url,
                        HttpMethod.POST,
                        new HttpEntity<>(order),
                        String.class).
                getBody();
    }
}

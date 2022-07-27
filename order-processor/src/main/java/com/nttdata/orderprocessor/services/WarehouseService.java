package com.nttdata.orderprocessor.services;

import com.nttdata.orderprocessor.entity.OrderFullfillmentDTO;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WarehouseService {
    public String checkStockAndReserve(OrderFullfillmentDTO order) {
        String url = "http://warehouse:8091/api/v1/warehouse/checkStoreAndReserve";
//        String url = "http://localhost:8091/api/v1/warehouse/checkStoreAndReserve";
        return new RestTemplate().exchange(url,
                        HttpMethod.POST,
                        new HttpEntity<>(order),
                        String.class).
                getBody();

    }

    public String packageAndSendOrder(OrderFullfillmentDTO order) {
        String url = "http://warehouse:8091/api/v1/warehouse/packageAndSendOrder";
//        String url = "http://localhost:8091/api/v1/warehouse/packageAndSendOrder";
        return new RestTemplate().exchange(url,
                        HttpMethod.POST,
                        new HttpEntity<>(order),
                        String.class).
                getBody();
    }
}

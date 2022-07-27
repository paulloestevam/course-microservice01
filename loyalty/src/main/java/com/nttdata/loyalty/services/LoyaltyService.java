package com.nttdata.loyalty.services;

import com.nttdata.loyalty.entity.LoyaltyCustomerInfo;
import com.nttdata.loyalty.entity.OrderFullfillment;
import com.nttdata.loyalty.jpa.LoyaltyCustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoyaltyService {

    @Autowired
    LoyaltyCustomerRepository loyaltyCustomerRepository;

    public String awardPointsToCustomer(OrderFullfillment order) {
        LoyaltyCustomerInfo loyaltyCustomerInfo = loyaltyCustomerRepository.findByIdCustomer(order.getCustomerId());

        loyaltyCustomerInfo.setPoints(loyaltyCustomerInfo.getPoints() + 10);
        loyaltyCustomerRepository.save(loyaltyCustomerInfo);

        return "ok";
    }


    public List getAll() {
        return loyaltyCustomerRepository.findAll();
    }
}

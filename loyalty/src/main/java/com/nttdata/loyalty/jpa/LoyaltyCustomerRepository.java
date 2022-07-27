package com.nttdata.loyalty.jpa;


import com.nttdata.loyalty.entity.LoyaltyCustomerInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoyaltyCustomerRepository extends JpaRepository<LoyaltyCustomerInfo, Long> {
    LoyaltyCustomerInfo findByIdCustomer(Long idCustomer);


}

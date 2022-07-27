package com.nttdata.orderprocessor.jpa;


import com.nttdata.orderprocessor.entity.OrderFullfillment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderFullfillmentRepository extends JpaRepository<OrderFullfillment, Long> {

}

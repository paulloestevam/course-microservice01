package com.nttdata.warehouse.jpa;


import com.nttdata.warehouse.entity.StockProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockProductRepository extends JpaRepository<StockProduct, Long> {

    StockProduct findByProductId(Long productoId);


}

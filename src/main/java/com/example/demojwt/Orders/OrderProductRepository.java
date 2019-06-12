package com.example.demojwt.Orders;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderProductRepository  extends JpaRepository<OrderProduct,OrderProductPK> {
}

package com.example.demojwt.Orders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Service
@Transactional
public class OrderProductServiceImpl implements OrderProductService{

    @Autowired
    private OrderProductRepository orderProductRepository;

    @Override
    public OrderProduct create(@NotNull(message = "The products for order cannot be null.") @Valid OrderProduct orderProduct) {
        return orderProductRepository.save(orderProduct);
    }
}

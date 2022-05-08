package com.example.orderservice.jpa;

import com.example.orderservice.jpa.OrderEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public interface OrderRepository extends CrudRepository<OrderEntity,Long> {
    OrderEntity findByOrderId(String orderId);
    Iterable<OrderEntity> findByUserId(String userId);
}

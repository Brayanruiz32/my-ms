package com.marreros.ms_order.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marreros.ms_order.models.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}

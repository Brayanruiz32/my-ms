package com.marreros.ms_order.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marreros.ms_order.dto.OrderDTO;
import com.marreros.ms_order.models.Order;
import com.marreros.ms_order.services.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;
    
    @GetMapping("/all")
    public ResponseEntity<List<Order>> findAll(){
        return new ResponseEntity<>(orderService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> findById(@PathVariable Long id){
        return new ResponseEntity<>(orderService.findById(id), HttpStatus.OK);
    }
    
    @PostMapping("/create")
    public ResponseEntity<Order> create(@RequestBody OrderDTO orderDTO){
        return new ResponseEntity<>(orderService.create(orderDTO), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Order> update(@PathVariable Long id, @RequestBody OrderDTO orderDTO){
        return new ResponseEntity<>(orderService.update(id, orderDTO), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        orderService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}

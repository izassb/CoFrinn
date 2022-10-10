package com.example.course.services;

import com.example.course.entities.Order;
import com.example.course.entities.UserEntity;
import com.example.course.repositories.OrderRepository;
import com.example.course.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public List<Order> findAll(){
        return orderRepository.findAll();
    }

    public Order findById(Long id){
        Optional<Order> order = this.orderRepository.findById(id);
        return order.get();
    }
}

package com.example.course.config;

import com.example.course.entities.Order;
import com.example.course.entities.UserEntity;
import com.example.course.repositories.OrderRepository;
import com.example.course.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.Instant;
import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;


    @Override
    public void run(String... args) throws Exception {
        UserEntity u1 = new UserEntity(null,"caio","caio@123.com","1212312312","1234123");
        UserEntity u2 = new UserEntity(null,"caio","caio@123.com","1212312312","1234123");

        Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), u1);
        Order o2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"), u2);
        Order o3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"), u1);

        this.userRepository.saveAll(Arrays.asList(u1,u2));
        this.orderRepository.saveAll(Arrays.asList(o1,o2,o3));
    }
}

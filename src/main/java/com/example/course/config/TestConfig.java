package com.example.course.config;

import com.example.course.entities.UserEntity;
import com.example.course.repositories.UserRepository;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
  @Autowired private UserRepository userRepository;

  @Override
  public void run(String... args) throws Exception {
    UserEntity u1 = new UserEntity(null, "caio", "caio@123.com", "1212312312", "1234123");
    UserEntity u2 = new UserEntity(null, "izabelly", "iza@123.com", "222333444", "12345");

    this.userRepository.saveAll(Arrays.asList(u1, u2));
  }
}

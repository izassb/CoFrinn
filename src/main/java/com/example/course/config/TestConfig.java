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



  }
}

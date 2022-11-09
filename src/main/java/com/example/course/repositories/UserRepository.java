package com.example.course.repositories;

import com.example.course.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface UserRepository extends JpaRepository<UserEntity, Long> {
        @Query("SELECT  i from UserEntity  i where  i.email =:email")
        public UserEntity findAllBy(String email);
        @Query("SELECT  j from UserEntity  j where  j.name=:name and j.password =:password")
        public UserEntity buscarLogin(String name, String password);
    }



package com.example.course.repositories;


import com.example.course.entities.ExitEntity;
import com.example.course.services.ExitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExitRepository extends JpaRepository<ExitEntity, Integer> {

}



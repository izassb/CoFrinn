package com.example.course.services;

import com.example.course.entities.LauchEntity;
import com.example.course.repositories.LauchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LauchService {

    @Autowired
    private LauchRepository lauchRepository;

    public List<LauchEntity> findAll() {

        return lauchRepository.findAll();
    }

    public LauchEntity findById(Integer id) {
        Optional<LauchEntity> lauch = lauchRepository.findById(id);
        return lauch.get();
    }

    public void delete(Integer id) {
        this.lauchRepository.deleteById(id);

    }

    public LauchEntity insert(LauchEntity object) {
        return lauchRepository.save(object);
    }
}

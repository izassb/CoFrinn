package com.example.course.services;

import com.example.course.entities.ExitEntity;
import com.example.course.repositories.ExitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import java.util.Optional;


@Service
public class ExitService {

    @Autowired
    private ExitRepository exitRepository;

    public List<ExitEntity> findAll() {

        return exitRepository.findAll();
    }

    public ExitEntity findById(Integer id) {
        Optional<ExitRepository> exit = exitRepository.findById(id);
        return (ExitEntity) exit.get();
    }

    public void delete(Integer id) {
        this.exitRepository.deleteById(id);
    }

    public ExitEntity insert(ExitEntity object) {

        return exitRepository.save(object);
    }
}

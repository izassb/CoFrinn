package com.example.course.services;

import com.example.course.entities.UserEntity;
import com.example.course.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.transaction.Transactional;

@Service
@CrossOrigin("*")
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

    public UserEntity findById(Long id) {
        Optional<UserEntity> user = userRepository.findById(id);
        return user.get();
    }

    public void delete(Long id) {
        this.userRepository.deleteById(id);
    }


    public UserEntity insert(UserEntity object) {
        return userRepository.save(object);
    }

    public UserEntity loginUser(String user, String password) throws ServiceExc {

        UserEntity Userlogin = userRepository.buscarLogin(user, password);
        return Userlogin;
    }
}

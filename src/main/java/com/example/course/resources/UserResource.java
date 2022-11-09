package com.example.course.resources;

import java.net.URI;

import com.example.course.entities.UserEntity;
import com.example.course.services.ServiceExc;
import com.example.course.services.UserService;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpSession;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/users")
public class UserResource {
    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity<List<UserEntity>> findAll() {
        List<UserEntity> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserEntity> findById(@PathVariable Long id) {
        UserEntity user = service.findById(id);
        return ResponseEntity.ok().body(user);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Void> deleteOne(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<UserEntity> insertUser(@RequestBody UserEntity user) {
        user = service.insert(user);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(user);
    }
    @PostMapping("/login")
    public ModelAndView login(UserEntity user, BindingResult br, HttpSession session) throws NoSuchAlgorithmException, ServiceExc {
        ModelAndView mv = new ModelAndView();
        mv.addObject("userEntity", new UserEntity());

        UserEntity userLogin = service.loginUser(user.getName(),user.getPassword());
        if (userLogin==null){
            mv.addObject("msg","usuario nao encontrado");
        }else {
            session.setAttribute("usuarioLogado", userLogin);

        }
        return mv;
    }
}

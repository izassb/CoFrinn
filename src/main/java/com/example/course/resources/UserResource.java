package com.example.course.resources;

import com.example.course.entities.UserEntity;
import com.example.course.services.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users")
public class UserResource {
  @Autowired private UserService service;

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
}

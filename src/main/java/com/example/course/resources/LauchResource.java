package com.example.course.resources;

import com.example.course.entities.LauchEntity;
import com.example.course.services.LauchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/lauches")
public class LauchResource {
    @Autowired
    private LauchService lauchService;

    @GetMapping
    public ResponseEntity<List<LauchEntity>> findAll() {
        List<LauchEntity> list = lauchService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<LauchEntity> findById(@PathVariable Integer id) {
        LauchEntity lauch = lauchService.findById(id);
        return ResponseEntity.ok().body(lauch);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        lauchService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<LauchEntity> insertLauch(@RequestBody LauchEntity lauch) {
        lauch = lauchService.insert(lauch);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}").buildAndExpand(lauch.getId()).toUri();
        return ResponseEntity.created(uri).body(lauch);
    }
}

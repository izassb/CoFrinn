package com.example.course.resources;

import com.example.course.entities.ExitEntity;
import com.example.course.services.ExitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/exits")
public class ExitResource {

    @Autowired
    private ExitService exitService;

    @GetMapping
    public ResponseEntity<List<ExitEntity>> findAll() {
        List<ExitEntity> list = exitService.findAll();
        return ResponseEntity.ok().body(list);

    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ExitEntity> findById(@PathVariable Integer id) {
        ExitEntity exit = exitService.findById(id);
        return ResponseEntity.ok().body(exit);
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        exitService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<ExitEntity> insertExit(@RequestBody ExitEntity exit) {

        exit = exitService.insert(exit);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}").buildAndExpand(exit.getId()).toUri();
        return ResponseEntity.created(uri).body(exit);
    }


}

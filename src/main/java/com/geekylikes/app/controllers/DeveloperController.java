package com.geekylikes.app.controllers;

import com.geekylikes.app.models.Developer;
import com.geekylikes.app.repositories.DeveloperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/devs")
public class DeveloperController {
    @Autowired
    private DeveloperRepository repository;

    @GetMapping
    public @ResponseBody List<Developer> getDevelopers(){
        return repository.findAll();
    }

    @PostMapping
    public ResponseEntity<
    Developer> createDeveloper(@RequestBody Developer newDeveloper){
        return new ResponseEntity<>(repository.save(newDeveloper),HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public @ResponseBody Developer getOneDevloper(@PathVariable Long id){
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/id")
    public @ResponseBody Developer updateDeveloper(@PathVariable Long id, @RequestBody Developer updates){
            Developer developer = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

            if(updates.getName() != null) developer.setName(updates.getName());
            if(updates.getCohort()!=null) developer.setCohort(updates.getCohort());
            if(updates.getEmail() != null) developer.setEmail(updates.getEmail());
            if(updates.getLanguages() != null) developer.setLanguages(updates.getLanguages());

            return repository.save(developer);
    }

    @DeleteMapping("/id")
    public ResponseEntity<String> destroyDeveloper(@PathVariable Long id){
//        repository.deleteById(id);
        return new ResponseEntity<>(repository.deleteById(id),HttpStatus.)
    }
}

package com.geekylikes.app.controllers;

import com.geekylikes.app.models.Developer;
import com.geekylikes.app.repositories.DeveloperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public @ResponseBody
    Developer createDeveloper(@RequestBody Developer newDeveloper){
        return (Developer) repository.save(newDeveloper);
    }

    @GetMapping("/{id}")
    public @ResponseBody Developer getOneDevloper(@PathVariable Long id){
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}

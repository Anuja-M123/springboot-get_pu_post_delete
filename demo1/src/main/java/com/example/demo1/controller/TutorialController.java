package com.example.demo1.controller;

import java.util.List;
import java.util.Optional;

//import org.apache.el.stream.Optional;
//import org.apache.el.stream.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo1.model.TutorialModel;
import com.example.demo1.repository.TutorialRepository;

    @RestController
@RequestMapping("/api")
public class TutorialController {

    @Autowired
    TutorialRepository tutorialRepository;

    @GetMapping("show_all")

    public List<TutorialModel> getAllTutorials() {
        return (List<TutorialModel>) tutorialRepository.findAll();
    }

    @PostMapping("/create")
    public ResponseEntity<TutorialModel> createTutorial(@RequestBody TutorialModel tutorial) {
        TutorialModel _tutorial = tutorialRepository
                .save(new TutorialModel(tutorial.getFirstname(), tutorial.getLastname(), tutorial.getPassword()));
        return new ResponseEntity<>(_tutorial, HttpStatus.OK);
    }

    @DeleteMapping("/delete_all")
    public ResponseEntity<HttpStatus> deleteAllTutorial() {
        tutorialRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<TutorialModel> updateTutorial(@PathVariable Long id, @RequestBody TutorialModel updatedTutorial) {
        Optional<TutorialModel> tutorial = tutorialRepository.findById(id);

        if (tutorial.isPresent()) {
            TutorialModel existingTutorial = tutorial.get();
            existingTutorial.setTitle(updatedTutorial.getTitle());
            existingTutorial.setDescription(updatedTutorial.getDescription());
            // Update other properties as needed

            TutorialModel savedTutorial = tutorialRepository.save(existingTutorial);
            return ResponseEntity.ok(savedTutorial);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
}

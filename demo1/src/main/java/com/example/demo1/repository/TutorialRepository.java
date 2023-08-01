package com.example.demo1.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo1.model.TutorialModel;

public interface TutorialRepository extends  CrudRepository<TutorialModel,Long>{
    
}

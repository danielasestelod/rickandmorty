package com.cebem.rickandmorty.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cebem.rickandmorty.models.VGModel;

@Repository
public interface VGRepository extends CrudRepository<VGModel,Long> {
    
}
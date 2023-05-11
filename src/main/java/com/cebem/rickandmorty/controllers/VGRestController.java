package com.cebem.rickandmorty.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.cebem.rickandmorty.services.VGService;

@RestController
public class VGRestController {
    @Autowired
    VGService vgService;
    
    // DELETE   http://localhost:8080/memes/:id
    @DeleteMapping("/vg/{id}")
    public String memesDelete(@PathVariable String id){
        
        boolean result = vgService.deleteVG(Long.parseLong(id));
        if(result){
            return "OK borrado correcto";
        }else{
            return "ERROR al borrar";
        }
    }
}


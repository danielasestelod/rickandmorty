package com.cebem.rickandmorty.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cebem.rickandmorty.models.CharactersModel;
import com.cebem.rickandmorty.models.numberCharactersModel;
import com.cebem.rickandmorty.services.RickAndMortyServices;

@Controller
public class webController {
    @Autowired
    RickAndMortyServices rickAndMortyService;

    @RequestMapping("/rickandmorty/allTemplate")
    public String charactersTemplate(Model modelo){
        CharactersModel charactersModel= rickAndMortyService.getAllCharacters();

       modelo.addAttribute("creator","Creado por Angel");
       modelo.addAttribute("characters", charactersModel.results);
       //nombre del html al que se CONECTA
        return "rickandmortyall";
    }


    @RequestMapping("/rickandmorty/numbercharacters")
    public String numberCharacters(Model modelo){
        numberCharactersModel numbercharactersModel= rickAndMortyService.getNumberCharacters();

        modelo.addAttribute("count",numbercharactersModel.info.count );
        return "numberCharacters";

    } 
}

package com.cebem.rickandmorty.controllers;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cebem.rickandmorty.models.VGModel;
import com.cebem.rickandmorty.services.VGService;
@Controller
public class VideoGameController {
    @Autowired
    VGService vgService;
    
    @RequestMapping("/vg")
    public String videogames(Model model){
        
        ArrayList<VGModel> vg = vgService.getAllVideoGames();
        model.addAttribute("videojuegos",vg);
        return "VGList";
    }

    @PostMapping("/vg")
    public String vgAdd(@RequestParam Map<String, String> body){
        String name = body.get("name");
        String description = body.get("description");
        String category = body.get("category");
        String author = body.get("author");

        VGModel newVG = new VGModel();
        newVG.setDescription(description);
        newVG.setName(name);
        newVG.setCategory(category);
        newVG.setAuthor(author);

        vgService.createVG(newVG);

        return "VGOK";
    }

    @RequestMapping("/vg/addForm")
    public String vgAddForm(){
        return "vgAddForm";
    }

}

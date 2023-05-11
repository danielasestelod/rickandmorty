package com.cebem.rickandmorty.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cebem.rickandmorty.models.VGModel;
import com.cebem.rickandmorty.repositories.VGRepository;

@Service
public class VGService {
    @Autowired
    VGRepository vgRepository;
    public ArrayList<VGModel> getAllVideoGames(){
        return (ArrayList<VGModel>)vgRepository.findAll();
        
    }

    public VGModel createVG(VGModel vg){
        return vgRepository.save(vg);
    }

    public boolean deleteVG(long id){
        try{
            vgRepository.deleteById(id);
            return true;
        }catch(IllegalArgumentException ex){
            return false;
        }
        
    }

}

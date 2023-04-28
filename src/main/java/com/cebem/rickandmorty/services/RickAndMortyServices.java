package com.cebem.rickandmorty.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cebem.rickandmorty.models.CharacterModel;
import com.cebem.rickandmorty.models.CharactersModel;
import com.cebem.rickandmorty.models.numberCharactersModel;
import com.cebem.rickandmorty.utils.Utils;

@Service
public class RickAndMortyServices {
    //Le pides a springboot que te inyecte esa libreria  (---abajo) //LIBRERIA PARA HACER UNA PETICION A UN API
    @Autowired
    RestTemplate restTemplate;
    final static String BASE_API="https://rickandmortyapi.com/api";
    public CharacterModel getCharacterRandom(){
        final int TOTAL_CHARACTERS=826;
        int random = Utils.getRandomValue(826 -1)+1;
        String url = "https://rickandmortyapi.com/api/character/"+random;
       
        CharacterModel datos=restTemplate.getForObject(url,CharacterModel.class);
        //respuestaJson recoge un string con el docjson
        return datos;
    }

    public CharactersModel getAllCharacters(){

        String url = "/character/";
        CharactersModel datos=restTemplate.getForObject(BASE_API+url,CharactersModel.class);
        return datos;
    }

    public numberCharactersModel getNumberCharacters(){

        String url = "/character/";
        numberCharactersModel datos=restTemplate.getForObject(BASE_API+url,numberCharactersModel.class);
        return datos;//puso return datos.info.count
    }
    
}

package com.cebem.rickandmorty.controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cebem.rickandmorty.models.CharacterModel;
import com.cebem.rickandmorty.models.CharactersModel;
import com.cebem.rickandmorty.models.numberCharactersModel;
import com.cebem.rickandmorty.services.RickAndMortyServices;
import com.cebem.rickandmorty.utils.Utils;

@RestController 
@CrossOrigin(origins="*")
public class rickController {
   @Autowired

   RickAndMortyServices rickAndMortyService;
    //localhost:8080/saluda
    @GetMapping("/")//LE PEDIMOS ALGO AL SERVIDOR
    public String saluda(){
        return "Bienvenid@ a mi api rest de rickAndMorty";
    }
    //Siempre hay que devolver un String
    @GetMapping("/random")
    public String random(){
        return Math.round(Math.random()*10)+"";
    }

    //http://localhost:8080/palindromo/{parametro} las llaves le indican que la ruta va a variar(el parametro cambia)
    @GetMapping("/palindromo/{word}")
    public String palindromo(@PathVariable String word){
        return Utils.isPalindrome(word)?
        "Si":"No";
    }

    //http://localhost:8080/add/1/2 <--con parametros
    //queryes -->//http://localhost:8080/add?n1=2&n2=1
    //SIEMPRE SE PASAN STRING
    //Devolver un mensage ??
    @GetMapping("/add")
    public String add(@RequestParam String n1, @RequestParam String n2){
        float resultado= Float.parseFloat(n1)+Float.parseFloat(n2);
        Object params[]= {n1,n2, resultado};
        return MessageFormat.format("La suma de {0} mas {1} es igual a {2}",params);
    }

    //Guardar en disco duro -->POST
    @PostMapping("/saveOnDisk")
    public String saveOnDisk(@RequestParam Map <String,String> body){
        String name= body.get("name");
        String price= body.get("price");
        String info=name+" "+price+"\n";
        try{
            Utils.writeOnDisk("datos.txt",info);
        }catch(IOException e){
            return "Error al intentar escribir en el fichero";
        }
        
        return "Gracias por enviar el formulario";
    }
    
    @DeleteMapping("/delFromDisk")
    public String delFromDisk(){
        boolean resultado=Utils.deleteFile("datos.txt");
        return resultado?"Borrado correctamente":"No se pudo borrar";
    }

   @GetMapping("/cuadrado")
    public String squareNumber(@RequestParam String n1){
        double n=Double.parseDouble(n1);
        double resultado=(int)Math.pow(n, 2);
        Object params[]= {n1,resultado};
        return MessageFormat.format("El cuadrado de {0} es igual a {1}",params);
    }

    @DeleteMapping("/vaciar")
    public String deleteContent(){
       String info=" ";
        try{
            Utils.delContent("datos.txt",info);
        }catch(IOException ex){
            return "Error al intentar borrar el contenido"+ex.getMessage();//ex.getMessage()-> devuelve el error
        }
        
        return "Gracias";
    }

    @GetMapping("/show")
    public String showContent(){
        
        try{
            return Utils.showContent("datos.txt");
        }catch(IOException e){
            return "Error al intentar mostrar el contenido";
        }
        
    }
    //GET http://localhost:8080/mayor?n1= &n2=...
    @GetMapping("/mayor")
    public String highestNumber(@RequestParam String n1, @RequestParam String n2,@RequestParam String n3){
        float num1=Float.parseFloat(n1);
        float num2=Float.parseFloat(n2);
        float num3=Float.parseFloat(n3);
        
        return Utils.maxOfElements(num1,num2,num3)+"";
    }

    @GetMapping("/capitalice/{text}")
    public static String capitalizeFirstLetter(@PathVariable String text){
        return Utils.capitalize(text);
    }
    //De un array de colores selecciona 3 de forma aleatoria
    @GetMapping("/colors")
    public static String randomColor(){
        final int COLOR_COUNT=3;
        final String[] Colors= new String[] {"negro","rojo","verde","amarillo","marron","naranja","azul"};
        if(COLOR_COUNT>Colors.length) throw new RuntimeException("Limite de colores superado");
        ArrayList <String> colores= new ArrayList<String>(Arrays.asList(Colors));
        String finalColors="";
        for(int i=0;i<COLOR_COUNT;i++){
            int random=Utils.getRandomValue(colores.size());
            finalColors+=colores.remove(random)+" ";
        }
        return finalColors;
    }
    
    @GetMapping("/rickandmorty/random")
    public String randomCharacter(){
        //RickAndMortyServices rickAndMortyService = new RickAndMortyServices(); //<--@autoWhired
        CharacterModel characterModel= rickAndMortyService.getCharacterRandom();

        return characterModel.name +"<br/>" +"<img width='100' src='"+characterModel.image+"'/>";
    }

    @GetMapping("/rickandmorty/all")
    public String allCharacters(){
        CharactersModel charactersModel= rickAndMortyService.getAllCharacters();
        String personajes="";
        for(CharacterModel character:charactersModel.results) 
            personajes+= character.name +"<br/>" +"<img width='100' src='"+character.image+"'/>";
        return personajes;

    }

   

    


}

/*Crea un endPoint que dvuelva un personaje random de la serie de rick and morty*/


/*
./mvnw spring-boot:run
GET PIDO DATOS -- GET /alumnos/idmanuel
POST GUARDO DATOS -- 
PUT ACTUALIZO DATOS -- PUT /alumnos/idmanuel(CAMBIA LOS DATOS DE MANUEL)
DELETE BORRO DATOS -- DELETE /alumno/id (BORRO LOS DATOS DE ESE ID)
PATCH 
INFO
*/
//OPERACIONES CRUD

/*SERVER SIDE RENDER:
    conectada con una bbdd y se construye en tiempo real
    -dinamica
    x depende de la velocidad del cliente y del servidor
CLIENT SIDE RENDER:
    le pasa los datos d la bbdd al cliente dnd se genera la web
    -poca carga serv
    x el client tiene q tener un programa(react, angular...)
    x mas carga cliente
    x mal para el SEO
STATIC RENDER: 
    las pag web se generan con anterioridad cnd se hace el build d la aplicacion y s suve al servidor
    -ninguna carga para el cliente
    -la pagina ya esta hecha cuando se solicita
    -menor latencia ->rango mas alto google

    x no s actualiza->abria que desplegarla de nuevo en un serv
    x no dinamica
    

*/ 


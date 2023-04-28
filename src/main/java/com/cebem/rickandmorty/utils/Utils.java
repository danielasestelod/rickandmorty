package com.cebem.rickandmorty.utils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Utils {
    public static boolean isPalindrome(String word){
        String inverseWord= new StringBuilder(word).reverse().toString();
        return inverseWord.equalsIgnoreCase(word);
    }

    //QUERY
    
    public static void writeOnDisk(String fileName,String info) throws IOException{
        FileWriter fw= null;
        try {
            fw=new FileWriter(fileName,true);//true: escribe al final del archivo
            fw.write(info);
            
         } finally{
            if(fw != null) fw.close();
        }
    }

    public static boolean deleteFile(String fichero){
        File f= new File("datos.txt");
        //boolean resultado=f.delete();
        return f.delete();
    }



    public static String showContent(String fileName) throws IOException{
        String cadena;
        FileReader fr= null;
        BufferedReader br= null;
        String resultado="<ul>";
        
        try {
            
            fr=new FileReader(fileName);
            br=new BufferedReader(fr);
            while((cadena=br.readLine()) !=null) resultado+="<li>"+cadena+"</li>";
            resultado+="</ul>";
            return resultado;
         }finally{
            if(fr != null) fr.close();
            if(br!=null) br.close();
        }
        
    }
   
    public static void delContent(String fileName,String info) throws IOException{
        FileWriter fw= null;
        try {
            fw=new FileWriter(fileName,false);//al ser false o nada, escribe desde el principio sin importar que haya cosas
            fw.write(info);
            
         } finally{
            if(fw != null) fw.close();
        }
    }

    public static float maxOfElements(float ...numeros) throws NumberFormatException{
        if(numeros==null || numeros.length==0) 
            throw new  NumberFormatException();

        float mayor=numeros[0];
        for(int i=1;i<numeros.length;i++){
            if(numeros[i]>mayor) mayor=numeros[i];
        }
        return mayor;
    }

    public static String capitalize(String text){
        String[] words = text.split(" ");
        String capitalizeTx="";
        for(String word:words){
            Character letra=Character.toUpperCase(word.charAt(0)); 
            String resto= word.substring(1).toLowerCase();
            capitalizeTx+=letra+resto+" ";
        }
        return capitalizeTx;
    }

    public static int getRandomValue(int max){
        return (int)Math.floor(Math.random()*max);
    }

   
}


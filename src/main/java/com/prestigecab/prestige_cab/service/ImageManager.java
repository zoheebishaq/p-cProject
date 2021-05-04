package com.prestigecab.prestige_cab.service;

import com.prestigecab.prestige_cab.model.Items;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import java.io.*;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ImageManager {
    @Value("${prestige-cab.img.path}")
    String path;

    public void savePhoto(Items items, InputStream image){
        items.setImage(save(image));

    }

    private String save( InputStream fi){
        String fileName = "";
        try(DirectoryStream<Path> dir = Files.newDirectoryStream(Paths.get(path+"/"),"*")){





            fileName = System.currentTimeMillis()+".jpg";

            String filePath = path+"/"+fileName;

            Files.copy(fi, new File(filePath).toPath());

        }catch (IOException ioe){
            System.out.println("Erreur sur nom d'image : "+ioe.getMessage());
        }


        return fileName;
    }

    public InputStream retreivePhoto(String fileName){
        return retreiveImage(fileName);
    }



    private InputStream retreiveImage(String fileName){
        InputStream is = null;
        try {
            is = new FileInputStream(path+"/"+fileName);
        } catch (FileNotFoundException fnfe) {
            System.out.println("Erreur récupération de l'image "+fileName+" : "+fnfe.getMessage());
        }
        return is;
    }
}

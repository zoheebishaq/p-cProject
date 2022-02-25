package com.coin.zoheeb_coin.controller;

import com.coin.zoheeb_coin.dao.ItemRepository;
import com.coin.zoheeb_coin.service.ImageManager;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import java.io.IOException;
import java.io.InputStream;

@Controller()
@RequestMapping(value = "/img")
public class ImageController {

    ItemRepository itemRepository;

    ImageManager imageManager;

    @Autowired
    ImageController(ItemRepository itemRepository,
                    ImageManager imageManager){
        this.itemRepository = itemRepository;
        this.imageManager = imageManager;
    }

    @GetMapping(value = "/item/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody
    byte[]
    films(@PathVariable("id") Long id) {
        String posterName = itemRepository.findById(id).get().getImage();
        InputStream is = imageManager.retreivePhoto(posterName);
        byte[] image = null;
        try {
            image = IOUtils.toByteArray(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

}

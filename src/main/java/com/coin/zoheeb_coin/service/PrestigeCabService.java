package com.coin.zoheeb_coin.service;

import com.coin.zoheeb_coin.dao.CategorieRepository;
import com.coin.zoheeb_coin.dao.ItemRepository;
import com.coin.zoheeb_coin.formdata.CategorieFormDTO;
import com.coin.zoheeb_coin.formdata.ItemFormDTO;
import com.coin.zoheeb_coin.model.Categories;
import com.coin.zoheeb_coin.model.Items;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class PrestigeCabService {
    CategorieRepository categorieRepository;
    ItemRepository itemRepository;
    ImageManager imageManager;

    @Autowired
    public PrestigeCabService(CategorieRepository categorieRepository,
                              ItemRepository itemRepository,
                              ImageManager imageManager) {
        this.categorieRepository = categorieRepository;
        this.itemRepository = itemRepository;
        this.imageManager = imageManager;
    }

    //Item--------------------------------------------------------------------------------------------------------------
    public List<Items> getItems() {
        return Lists.newArrayList(itemRepository.findAll());
    }
    public Items getItem(Long id){ return itemRepository.findById(id).orElse(new Items()); }
    public void deleteItems(Long  id){ itemRepository.deleteById(id); }

//    barre de recherche
    public List<Items> itemRequete(String keyword){
        return
        itemRepository.requeteItem(keyword);}



    public void saveItems(ItemFormDTO itemFormDTO){
//        try catch or else et =
        Items itemsDB = itemRepository.findById(itemFormDTO.getId()).orElse(new Items());
//        System.out.println(itemsDB);
        itemsDB.setName(itemFormDTO.getNomVoiture());
        itemsDB.setDescription(itemFormDTO.getDescriptionVoiture());
        itemsDB.setPrice(itemFormDTO.getPrix());
        Categories categories = categorieRepository.findById(itemFormDTO.getCategoriesId()).orElse(new Categories());
//        System.out.println(categories);
        itemsDB.setCategories(categories);

        if(!itemFormDTO.getImage().isEmpty()){
            try{
                imageManager.savePhoto(itemsDB, itemFormDTO.getImage().getInputStream());
            } catch (IOException ioe){
                System.out.println("Erreur : "+ioe.getMessage());
            }
        }

        itemRepository.save(itemsDB);

    }




    // Categorie--------------------------------------------------------------------------------------------------------
    public List<Categories> getCategories() {
        return Lists.newArrayList(categorieRepository.findAll());
    }
    public Categories getCategorie(Long id){ return categorieRepository.findById(id).orElse(new Categories()); }

    public void deleteCategories(Long  id){
        List<Items> items = itemRepository.findItemsByCategories_Id(id);
        for(Items item:items){
            deleteItems(item.getId());
        }
        categorieRepository.deleteById(id);
    }

    public void saveCategories(CategorieFormDTO categorieFormDTO)
    {
        Categories categoriesDB = categorieRepository.findById(categorieFormDTO.getId()).orElse(new Categories());
        categoriesDB.setName(categorieFormDTO.getName());
        categorieRepository.save(categoriesDB);
    }

//    public List<Items> getItemsByCategories(Long id){
//        return itemRepository.findItemsByCategories_Id(id);
//    }

}


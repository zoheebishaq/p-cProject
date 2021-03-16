package com.prestigecab.prestige_cab.service;

import com.google.common.collect.Lists;
import com.prestigecab.prestige_cab.dao.CategorieRepository;
import com.prestigecab.prestige_cab.dao.ItemRepository;
import com.prestigecab.prestige_cab.formdata.CategorieFormDTO;
import com.prestigecab.prestige_cab.formdata.ItemFormDTO;
import com.prestigecab.prestige_cab.model.Categories;
import com.prestigecab.prestige_cab.model.Items;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrestigeCabService {
    CategorieRepository categorieRepository;
    ItemRepository itemRepository;

    @Autowired
    public PrestigeCabService(CategorieRepository categorieRepository,
                              ItemRepository itemRepository) {
        this.categorieRepository = categorieRepository;
        this.itemRepository = itemRepository;
    }

    public List<Items> getItems() {
        return Lists.newArrayList(itemRepository.findAll());
    }
    public Items getItem(Long id){
        return itemRepository.findById(id).orElse(new Items());
    }
    public void deleteItems(Long  id){
        itemRepository.deleteById(id);
    }

    public void SaveCategories(CategorieFormDTO categorieFormDTO){
        Categories categoriesDB = categorieRepository.findById(categorieFormDTO.getId()).orElse(new Categories());
        categoriesDB.setName(categoriesDB.getName());
        categorieRepository.save(categoriesDB);
    }




    public List<Categories> getCategories() {
        return Lists.newArrayList(categorieRepository.findAll());
    }
    public Categories getCategorie(Long id){
        return categorieRepository.findById(id).orElse(new Categories());
    }
    public void deleteCategories(Long  id){
        itemRepository.deleteById(id);
    }

    public void saveItems(ItemFormDTO  itemFormDTO){
        Items itemsDB = itemRepository.findById(itemFormDTO.getId()).orElse(new Items());
        itemsDB.setName(itemFormDTO.getNomVoiture());
        itemsDB.setDescription(itemFormDTO.getDescriptionVoiture());
        itemsDB.setPrice(itemFormDTO.getPrix());
        Categories categories = categorieRepository.findById(itemFormDTO.getCategoriesId()).orElse(new Categories());
        itemsDB.setCategories(categories);

        itemRepository.save(itemsDB);

    }


}


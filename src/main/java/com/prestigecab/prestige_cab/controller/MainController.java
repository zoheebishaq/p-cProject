package com.prestigecab.prestige_cab.controller;

import com.prestigecab.prestige_cab.formdata.ItemFormDTO;
import com.prestigecab.prestige_cab.model.Items;
import com.prestigecab.prestige_cab.service.PrestigeCabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MainController {
    private PrestigeCabService prestigeCabService;

    @Autowired
    public MainController(PrestigeCabService prestigeCabService) {
        this.prestigeCabService = prestigeCabService;
    }

    @GetMapping("/")
    public String index() {
        return "accueil";
    }

    @GetMapping("/item")
    public String items(Model model) {
        model.addAttribute("item", prestigeCabService.getItems());
        return "itemlist";

    }

    @GetMapping("/item/{id}")
    public String items(Model model, @PathVariable(name = "id") Long id) {
        Items items = prestigeCabService.getItem(id);
        ItemFormDTO itemFormDTO = new ItemFormDTO();
        itemFormDTO.setId(items.getId());
        itemFormDTO.setNomVoiture(items.getName());
        itemFormDTO.setDescriptionVoiture(items.getDescription());
        itemFormDTO.setPrix(items.getPrice());
        itemFormDTO.setCategoriesId(items.getCategories().getId());
        model.addAttribute("items", itemFormDTO);
        return "itemform";
    }

    @GetMapping("/item/add")
    public String addItems(Model model) {
        model.addAttribute("items", new ItemFormDTO());
        return "itemform";
    }

}

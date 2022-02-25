package com.coin.zoheeb_coin.controller;

import com.coin.zoheeb_coin.formdata.CategorieFormDTO;
import com.coin.zoheeb_coin.formdata.ItemFormDTO;
import com.coin.zoheeb_coin.model.Categories;
import com.coin.zoheeb_coin.model.Items;
import com.coin.zoheeb_coin.service.PrestigeCabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MainController {
    private PrestigeCabService prestigeCabService;

    @Autowired
    public MainController(PrestigeCabService prestigeCabService) {
        this.prestigeCabService = prestigeCabService;

    }

    @GetMapping("/")
    public String index() {
        return "hello";
    }


    //Item--------------------------------------------------------------------------------------------------------------
    @GetMapping("/item")
    public String items(Model model, @Param("keyword") String keyword) {

        List<Items> itemsList = prestigeCabService.itemRequete(keyword);
//        model.addAttribute("item", prestigeCabService.getItems());
//        model.addAttribute("item", itemsList);
//        model.addAttribute("keyword", keyword);

        if (keyword == "" || keyword == null) {
            model.addAttribute("item", prestigeCabService.getItems());
        } else {
            model.addAttribute("item", itemsList);
        }

        return "itemlist";

    }

    @GetMapping("/admin/item/{id}")
    public String items(Model model, @PathVariable(name = "id") Long id) {
        Items items = prestigeCabService.getItem(id);
        ItemFormDTO itemFormDTO = new ItemFormDTO();
        itemFormDTO.setId(items.getId());
        itemFormDTO.setNomVoiture(items.getName());
        itemFormDTO.setDescriptionVoiture(items.getDescription());
        itemFormDTO.setPrix(items.getPrice());
        itemFormDTO.setCategoriesId(items.getCategories().getId());
        List<Categories> categories = prestigeCabService.getCategories();
        model.addAttribute("items", itemFormDTO);
        model.addAttribute("categories", categories);

        return "itemform";
    }

    @GetMapping("/item/add")
    public String addItems(Model model) {
        model.addAttribute("items", new ItemFormDTO());
        List<Categories> categories = prestigeCabService.getCategories();
        model.addAttribute("categories", categories);
        return "itemform";
    }


    @GetMapping("/admin/item/delete/{id}")
    public String deleteItems(@PathVariable(name = "id") Long id) {
        prestigeCabService.deleteItems(id);
        return "redirect:/item";
    }

    @PostMapping("/item")
    public String postItems(@ModelAttribute(name = "item") ItemFormDTO itemFormDTO) {

        prestigeCabService.saveItems(itemFormDTO);
        return "redirect:/item";
    }

    //Categorie---------------------------------------------------------------------------------------------------------
    @GetMapping("/categorie")
    public String categorie(Model model) {
        model.addAttribute("categorie", prestigeCabService.getCategories());
        return "categlist";
    }

    @GetMapping("/admin/categorie/{id}")
    public String categories(Model model, @PathVariable(name = "id") Long id) {
        Categories categories = prestigeCabService.getCategorie(id);
        CategorieFormDTO categorieFormDTO = new CategorieFormDTO();
        categorieFormDTO.setId(categories.getId());
        categorieFormDTO.setName(categories.getName());
        model.addAttribute("categories", categories);

        return "categform";
    }

    @GetMapping("/categorie/add")
    public String addCategories(Model model) {
        model.addAttribute("categories", new CategorieFormDTO());

        return "categform";
    }

    @GetMapping("/admin/categorie/delete/{id}")
    public String deleteCategories(@PathVariable(name = "id") Long id) {
        prestigeCabService.deleteCategories(id);
        return "redirect:/categorie";
    }

    @PostMapping("/categorie")
    public String postCategories(@ModelAttribute(name = "categorie") CategorieFormDTO categorieFormDTO) {
        prestigeCabService.saveCategories(categorieFormDTO);
        return "redirect:/categorie";
    }


}

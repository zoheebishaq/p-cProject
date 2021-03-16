package com.prestigecab.prestige_cab.formdata;

import org.springframework.web.multipart.MultipartFile;

public class ItemFormDTO {
    private long id;
    private String nomVoiture;
    private String descriptionVoiture;
    private int prix;
    private long categoriesId;
    private MultipartFile affiche;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNomVoiture() {
        return nomVoiture;
    }

    public void setNomVoiture(String nomVoiture) {
        this.nomVoiture = nomVoiture;
    }

    public String getDescriptionVoiture() {
        return descriptionVoiture;
    }

    public void setDescriptionVoiture(String descriptionVoiture) {
        this.descriptionVoiture = descriptionVoiture;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public long getCategoriesId() {
        return categoriesId;
    }

    public void setCategoriesId(long categoriesId) {
        this.categoriesId = categoriesId;
    }

    public MultipartFile getAffiche() {
        return affiche;
    }

    public void setAffiche(MultipartFile affiche) {
        this.affiche = affiche;
    }
}

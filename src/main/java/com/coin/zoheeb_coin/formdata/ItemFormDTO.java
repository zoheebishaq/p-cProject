package com.coin.zoheeb_coin.formdata;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class ItemFormDTO {
    private long id;
    private String nomVoiture;
    private String descriptionVoiture;
    private int prix;
    private long categoriesId;
    private MultipartFile image;


}

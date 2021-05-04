package com.prestigecab.prestige_cab.dao;

import com.prestigecab.prestige_cab.model.Items;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ItemRepository extends CrudRepository<Items, Long> {
    @Query("SELECT i FROM Items i WHERE "
    +"lower(CONCAT(i.name,i.categories,i.description)) "
            +" LIKE lower(concat('%',?1,'%'))")
    public List<Items> requeteItem(String keyword);

    public List<Items> findItemsByCategories_Id(Long id);
}

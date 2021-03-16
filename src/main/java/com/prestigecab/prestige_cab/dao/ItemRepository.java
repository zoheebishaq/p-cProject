package com.prestigecab.prestige_cab.dao;

import com.prestigecab.prestige_cab.model.Items;
import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository <Items,Long> {
}

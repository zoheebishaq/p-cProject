package com.coin.zoheeb_coin.dao;

import com.coin.zoheeb_coin.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Long> {
    User findByLogin(String name);
}

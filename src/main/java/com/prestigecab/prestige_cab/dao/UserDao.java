package com.prestigecab.prestige_cab.dao;

import com.prestigecab.prestige_cab.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Long> {
    User findByLogin(String name);
}

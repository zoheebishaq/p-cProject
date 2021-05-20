package com.prestigecab.prestige_cab.service;

import com.prestigecab.prestige_cab.dao.GroupDao;
import com.prestigecab.prestige_cab.dao.UserDao;
import com.prestigecab.prestige_cab.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashSet;

public class JpaUserService {
    private UserDao userDao;
    private GroupDao groupDao;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public JpaUserService(UserDao userDao,
                          GroupDao groupDao,
                          BCryptPasswordEncoder bCryptPasswordEncoder){
        this.userDao = userDao;
        this.groupDao = groupDao;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public void save(User user){
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setGroups(new HashSet<>(groupDao.findAll()));
        userDao.save(user);
    }

    public User findByUserName(String userName){
        return userDao.findByLogin(userName);
    }

}

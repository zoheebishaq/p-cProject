package com.coin.zoheeb_coin.service;

import com.coin.zoheeb_coin.dao.GroupDao;
import com.coin.zoheeb_coin.dao.UserDao;
import com.coin.zoheeb_coin.domain.User;
import com.coin.zoheeb_coin.domain.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
@Service
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
//        user.setGroups(user.getGroups());
        userDao.save(user);
    }

    public void addUser(String name ,String email, String mdp,Long idGroup){
        Group group = this.groupDao.findById(idGroup).get();
        User user = new User(name,mdp,email);
        Set<Group> groups = new HashSet<Group>();
        System.out.println(group.getRole());
        user.setGroups(groups);
        groups.add(group);
        this.save(user);
    }

    public User findByUserName(String userName){
        return userDao.findByLogin(userName);
    }

}

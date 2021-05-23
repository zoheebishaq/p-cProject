package com.prestigecab.prestige_cab.service;

import com.prestigecab.prestige_cab.dao.GroupDao;
import com.prestigecab.prestige_cab.dao.UserDao;
import com.prestigecab.prestige_cab.domain.Group;
import com.prestigecab.prestige_cab.domain.User;
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

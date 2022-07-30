package com.ufps.ingsistemas.pensumapp.services;

import com.ufps.ingsistemas.pensumapp.entities.RoleEntity;
import com.ufps.ingsistemas.pensumapp.entities.UserEntity;
import com.ufps.ingsistemas.pensumapp.repositories.RoleRepository;
import com.ufps.ingsistemas.pensumapp.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@Slf4j
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    public UserEntity saveUser(UserEntity user){
        return userRepository.save(user);
    }

    public RoleEntity saveRole(RoleEntity role){
        return roleRepository.save(role);
    }

    public void addRoleToUser(String username, String rolename){
        var user = userRepository.findByUsername(username);
        var role = roleRepository.findByName(rolename);
        user.getRoles().add(role);
    }

    public UserEntity getUser(String username){
        return userRepository.findByUsername(username);
    }

    public List<UserEntity> getUsers(){
        return (List<UserEntity>) userRepository.findAll();
    }
}

package com.ufps.ingsistemas.pensumapp.repositories;

import com.ufps.ingsistemas.pensumapp.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity,Long> {
    UserEntity findByUsername(String username);
}

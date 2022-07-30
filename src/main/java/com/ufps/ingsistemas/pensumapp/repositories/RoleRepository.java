package com.ufps.ingsistemas.pensumapp.repositories;

import com.ufps.ingsistemas.pensumapp.entities.RoleEntity;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<RoleEntity, Long> {
    RoleEntity findByName(String name);
}

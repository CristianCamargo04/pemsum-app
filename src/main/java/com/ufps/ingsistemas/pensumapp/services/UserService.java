package com.ufps.ingsistemas.pensumapp.services;

import com.ufps.ingsistemas.pensumapp.entities.RoleEntity;
import com.ufps.ingsistemas.pensumapp.entities.UserEntity;
import com.ufps.ingsistemas.pensumapp.exception.GenericException;
import com.ufps.ingsistemas.pensumapp.repositories.RoleRepository;
import com.ufps.ingsistemas.pensumapp.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Transactional
@Slf4j
public class UserService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(username);
        if(user==null){
            log.error("UserService: Usuario no encontrado en la base de datos");
            throw new GenericException(HttpStatus.NOT_FOUND,"El usuario no se encontro");
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        log.info("UserService: Usuario encontrado en la base de datos",username);
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),authorities);
    }

    @Transactional(readOnly = true)
    public List<UserEntity> findAllUser() {
        try {
            List<UserEntity> users = (List<UserEntity>) userRepository.findAll();
            log.info("UserService: Obtener a todo los usuarios");
            return users;
        }catch (HttpServerErrorException e){
            log.error("UserService: Error Obteniendo a todo los usuarios");
            throw  new GenericException(HttpStatus.INTERNAL_SERVER_ERROR,"Ha ocurrido un error interno");
        }
    }

    @Transactional
    public UserEntity saveUser(UserEntity userDto) {

        try {
            if(userRepository.findByUsername(userDto.getUsername())!=null){
                log.error("UserService: Error al guardar el usuario, el username ya existe");
                throw new GenericException(HttpStatus.BAD_REQUEST,"El username ya existe");
            }
            UserEntity user = userRepository.save(userDto);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            log.info("UserService: Guardando un usuario en la base de datos",userDto.getUsername());
            return user;
        }catch (HttpServerErrorException e){
            log.error("UserService: Error interno al guardar el usuario");
            throw  new GenericException(HttpStatus.INTERNAL_SERVER_ERROR,"Ha ocurrido un error interno");
        }
    }

    @Transactional(readOnly = true)
    public UserEntity findByIdUser(Long id) {
        try {
            UserEntity user = userRepository.findById(id).orElseThrow(()-> new GenericException(HttpStatus.NOT_FOUND,"No se encontro el usuario"));
            log.info("UserService: Obtener usuario por id: ",id);
            return user;
        }catch (HttpServerErrorException e){
            log.error("UserService: Error interno al buscar el usuario por id",id);
            throw  new GenericException(HttpStatus.INTERNAL_SERVER_ERROR,"Ha ocurrido un error interno");
        }
    }

    @Transactional(readOnly = true)
    public UserEntity findByUsername(String username) {
        try {
            log.info("UserService: buscar usuario por username ",username);
            return userRepository.findByUsername(username);
        }catch (HttpServerErrorException e){
            log.error("UserService: Error interno al buscar el usuario por username",username);
            throw  new GenericException(HttpStatus.INTERNAL_SERVER_ERROR,"Ha ocurrido un error interno");
        }
    }

    @Transactional
    public void deleteByIdUser(Long id) {
        log.info("UserService: Borrar el usuario por id ",id);
        findByIdUser(id);
        try {
            userRepository.deleteById(id);
        }catch (HttpServerErrorException e){
            log.error("UserService: Error interno al borrar el usuario por id",id);
            throw  new GenericException(HttpStatus.INTERNAL_SERVER_ERROR,"Ha ocurrido un error interno");
        }
    }

    @Transactional
    public UserEntity updateUser(Long id, UserEntity userDto) {
        log.info("UserService: Actualizado el usuario por id",id," Username ",userDto.getUsername());
        UserEntity userDtoResponse= findByIdUser(id);
        try {

            if(userDto.getUsername()!=userDtoResponse.getUsername()){
                if(userRepository.findByUsername(userDto.getUsername())!=null){
                    log.error("UserService: Error  al actualizar el usuario por id ya existe el username",id);
                    throw  new GenericException(HttpStatus.BAD_REQUEST,"El username ya existe");
                }
            }
            return userRepository.save(UserEntity.builder()
                            .id(userDtoResponse.getId())
                            .username(userDto.getUsername())
                            .password(userDto.getPassword())
                            .build());
        }catch (HttpServerErrorException e){
            log.error("UserService: Error interno al actualizar el usuario por id",id);
            throw  new GenericException(HttpStatus.INTERNAL_SERVER_ERROR,"Ha ocurrido un error interno");
        }

    }

    @Transactional
    public RoleEntity saveRole(RoleEntity role) {
        try {
            log.info("UserService: Guardando nuevo rol en la base de datos",role.getName());
            return roleRepository.save(role);
        }catch (HttpServerErrorException e){
            log.error("UserService: Error interno al guardar un rol",role.getName());
            throw  new GenericException(HttpStatus.INTERNAL_SERVER_ERROR,"Ha ocurrido un error interno");
        }
    }

    @org.springframework.transaction.annotation.Transactional
    public void addRoleToUser(String username, String roleName) {
        try {
            UserEntity  user = userRepository.findByUsername(username);
            RoleEntity  role = roleRepository.findByName(roleName);
            System.out.println(username.toString());
            System.out.println(role.toString());
            log.info("UserService: Añadiendo role al usuario",roleName,username);
            user.getRoles().add(role);
        }catch (HttpServerErrorException e){
            log.error("UserService: Error interno al añadir un rol al usuario",username," rol",roleName);
            throw  new GenericException(HttpStatus.INTERNAL_SERVER_ERROR,"Ha ocurrido un error interno");
        }
    }
}

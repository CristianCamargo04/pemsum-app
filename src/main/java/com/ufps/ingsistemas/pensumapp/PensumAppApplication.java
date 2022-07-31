package com.ufps.ingsistemas.pensumapp;

import com.ufps.ingsistemas.pensumapp.entities.RoleEntity;
import com.ufps.ingsistemas.pensumapp.entities.UserEntity;
import com.ufps.ingsistemas.pensumapp.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class PensumAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(PensumAppApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}



	@Bean
	CommandLineRunner run(UserService userService){
		return args -> {
			userService.saveRole(new RoleEntity(null,"ROLE_USER"));
			userService.saveRole(new RoleEntity(null,"ROLE_ADMIN"));

			userService.saveUser(new UserEntity(null,"admin","admin",null));
			userService.saveUser(new UserEntity(null,"gregorio","admin",null));
			userService.addRoleToUser("admin","ROLE_ADMIN");
			userService.addRoleToUser("admin","ROLE_USER");
			userService.addRoleToUser("gregorio","ROLE_USER");
		};
	}
}

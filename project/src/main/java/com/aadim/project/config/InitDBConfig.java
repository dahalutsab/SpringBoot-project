package com.aadim.project.config;

import com.aadim.project.entity.Role;
import com.aadim.project.entity.UserLogin;
import com.aadim.project.repository.RoleRepository;
import com.aadim.project.repository.UserLoginRepository;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collections;

@Configuration
@Slf4j
public class InitDBConfig {
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserLoginRepository userRepository;

    @PostConstruct
    public void insert(){
        if(roleRepository.findAll().isEmpty()) {
            Role admin = Role.builder()
                    .name("ADMIN")
                    .description("administrator")
                    .build();
            roleRepository.save(admin);
            log.info("ADMIN role saved");

            Role teacher = Role.builder()
                    .name("TEACHER")
                    .description("app user")
                    .build();
            roleRepository.save(teacher);
            log.info("TEACHER role saved");

            Role student = Role.builder()
                    .name("STUDENT")
                    .description("app user")
                    .build();
            roleRepository.save(student);
            log.info("STUDENT role saved");
        }
        else log.info("Roles already created");

        if(userRepository.findAll().isEmpty()) {
            UserLogin dbUser = UserLogin.builder()
                    .fullName("App Admin")
                    .password(new BCryptPasswordEncoder().encode("admin"))
                    .username("admin")
                    .roles(Collections.singletonList(roleRepository.findByName("ADMIN")))
                    .build();
            userRepository.save(dbUser);
            log.info("Admin user created");

            UserLogin teacher = UserLogin.builder()
                    .fullName("App Admin")
                    .password(new BCryptPasswordEncoder().encode("teacher"))
                    .username("user")
                    .roles(Collections.singletonList(roleRepository.findByName("TEACHER")))
                    .build();
            userRepository.save(teacher);
            log.info("User Teacher created");

            UserLogin student = UserLogin.builder()
                    .fullName("App Admin")
                    .password(new BCryptPasswordEncoder().encode("student"))
                    .username("student")
                    .roles(Collections.singletonList(roleRepository.findByName("STUDENT")))
                    .build();
            userRepository.save(student);
            log.info("User user created");
        }
        else log.info("Admin user already exists");
    }
}

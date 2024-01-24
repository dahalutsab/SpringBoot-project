package com.aadim.project.config;

import com.aadim.project.entity.Role;
import com.aadim.project.entity.User;
import com.aadim.project.entity.UserLogin;
import com.aadim.project.repository.RoleRepository;
import com.aadim.project.repository.UserLoginRepository;
import com.aadim.project.repository.UserRepository;
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
    private UserLoginRepository userLoginRepository;

    @Autowired
    private UserRepository userRepository;

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

            if(userLoginRepository.findAll().isEmpty()) {

                UserLogin dbUser = UserLogin.builder()
                        .password(new BCryptPasswordEncoder().encode("admin"))
                        .username("ADMIN")
                        .roles(Collections.singletonList(roleRepository.findByName("ADMIN")))
                        .build();
                userLoginRepository.save(dbUser);
                log.info("Admin user created in userLogin table");

                User user = User.builder()
                        .fullName("App Admin")
                        .email("dlutsab2120@gmail.com")
                        .contactNum("9847384736")
                        .build();
                userRepository.save(user);
                log.info("Admin user created in users table");
            }
            else log.info("Admin user already exists");
        }
        else log.info("Roles already created");


    }
}

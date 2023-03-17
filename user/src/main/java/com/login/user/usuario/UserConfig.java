package com.login.user.usuario;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfig {

    CommandLineRunner commandLineRunner(UserRepository repository){
        return args -> {

        };
    }
}

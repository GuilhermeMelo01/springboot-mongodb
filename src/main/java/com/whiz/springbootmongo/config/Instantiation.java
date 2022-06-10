package com.whiz.springbootmongo.config;

import com.whiz.springbootmongo.domain.User;
import com.whiz.springbootmongo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {

        userRepository.deleteAll();

        User gui = new User(null, "Guilherme", "GuilhermeZ01@email");
        User carol = new User(null, "Carol", "Carol@Gmail.com");
        User nicole = new User(null, "Nicole", "NicoleMaria@Gmail");

        userRepository.saveAll(List.of(gui, carol, nicole));
    }


}

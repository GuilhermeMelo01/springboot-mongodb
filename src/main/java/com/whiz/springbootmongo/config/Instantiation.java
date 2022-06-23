package com.whiz.springbootmongo.config;

import com.whiz.springbootmongo.domain.Post;
import com.whiz.springbootmongo.domain.User;
import com.whiz.springbootmongo.dto.AuthorDTO;
import com.whiz.springbootmongo.repository.PostRepository;
import com.whiz.springbootmongo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.TimeZone;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public void run(String... args) throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

        userRepository.deleteAll();
        postRepository.deleteAll();

        User gui = new User(null, "Guilherme", "GuilhermeZ01@email");
        User carol = new User(null, "Carol", "Carol@Gmail.com");
        User nicole = new User(null, "Nicole", "NicoleMaria@Gmail");
        userRepository.saveAll(List.of(gui, carol, nicole));

        Post post1 = new Post(null, sdf.parse("21/03/2018"), "Partiu viagem",
                "Vou viajar para São Paulo", new AuthorDTO(carol));
        Post post2 = new Post(null, sdf.parse("23/03/2018"), "Bom dia   ",
                "Acordei feliz hoje!", new AuthorDTO(carol));

        postRepository.saveAll(List.of(post1, post2));
    }
}

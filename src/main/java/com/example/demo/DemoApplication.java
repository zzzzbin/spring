package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.domain.lock.Article;
import com.example.demo.domain.lock.ArticleRepository;
import com.example.demo.domain.lock.CommentRepository;
import com.example.demo.dto.Droid;
import com.example.demo.repository.IngredientRepository;
import com.example.demo.repository.InstructorRepository;


@SpringBootApplication
@EnableAsync
@ConfigurationPropertiesScan
public class DemoApplication implements CommandLineRunner {

    @Bean
    @ConfigurationProperties(prefix = "droid")
    Droid createDroid() {
        return new Droid();
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Autowired
    IngredientRepository repo;

    @Autowired
    InstructorRepository instructorRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    ArticleRepository articleRepository;

    @Autowired
    CommentRepository commentRepository;

    @Override
    public void run(String... args) throws Exception {
        Article article = new Article();
        article.setId(1L);
        article.setCommentCount(0L);
        article.setTitle("XXXX");
        article.setVersion(1L);
        articleRepository.save(article);
        commentRepository.deleteAll();
    }
}

package com.example.demo.domain.lock;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.example.demo.DemoApplication;

import static org.springframework.boot.test.context.SpringBootTest.*;
@SpringBootTest(classes = DemoApplication.class,webEnvironment = WebEnvironment.RANDOM_PORT)
public class CommentControllerTest {
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void concurrentComment() {
        String url = "http://localhost:8080/comment";
        for (int i = 0; i < 100; i++) {
            int finalI = i;
            new Thread(() -> {
                MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
                params.add("articleId", "1");
                params.add("content", "Testcoment" + finalI);
                String result = testRestTemplate.postForObject(url, params, String.class);
                System.out.println("result = " + result);
            }).start();
        }
    }
}
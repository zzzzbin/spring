package com.example.demo.domain.lock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class TestComentController {
    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/testrest")
    public String concurrentComment() {
        String url = "http://localhost:8080/comment";
        for (int i = 0; i < 100; i++) {
            int finalI = i;
            new Thread(() -> {
                MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
                params.add("articleId", "1");
                params.add("content", "Testcoment" + finalI);
                String result = restTemplate.postForObject(url, params, String.class);
                System.out.println("result = " + result);
            }).start();
        }
        return "hello";
    }
}

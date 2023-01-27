package com.example.demo.controller;

import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.stream.Collectors;

@RestController
public class CookieController {
    @GetMapping("/cookie")
    public String readCookie(@CookieValue(value = "username", defaultValue = "Atta") String username) {
        return "Hey! My username is " + username;
    }

    @GetMapping("/cookie1")
    public String readCookie1(@CookieValue(value = "SESSION") String sessionId) {
        return "sessionId " + sessionId;
    }

    @GetMapping("/cookie2")
    public String readCookieV2(HttpServletRequest request) {
        Cookie username = WebUtils.getCookie(request, "username");
        if (username != null) {
            return "Hey! My username is " + username.getValue();
        }
        return "Not found";
    }

    @GetMapping("/change-username")
    public String setCookie(HttpServletResponse response) {

        // create a cookie
        Cookie cookie = new Cookie("username", "Jovan");
        cookie.setMaxAge(7 * 24 * 60 * 60); // 7 days
        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        cookie.setPath("/"); // global cookie accessible every where

        //add cookie to response
        response.addCookie(cookie);

        return "Username is changed!";
    }

    @GetMapping("/delete-username")
    public String deleteCookie(HttpServletResponse response) {

        // create a cookie
        Cookie cookie = new Cookie("username", null);
        cookie.setMaxAge(0);
        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        cookie.setPath("/");

        //add cookie to response
        response.addCookie(cookie);

        return "Username is deleted!";
    }

    @GetMapping("/all-cookies")
    public String readAllCookies(HttpServletRequest request) {

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            return Arrays.stream(cookies)
                    .map(c -> c.getName() + "=" + c.getValue()).collect(Collectors.joining(", "));
        }

        return "No cookies";
    }
}

package com.example.demo.controller;

import com.example.demo.domain.user.model.MUser;
import com.example.demo.domain.user.service.UserService;
import org.h2.engine.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
class UserListControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    @WithMockUser
    public void userCountTest() throws Exception {
        MUser user = new MUser();
        user.setUserName("TEST");
        List<MUser> userList = Arrays.asList(user);
        when(userService.getUsers(any())).thenReturn(userList);
        //check user List screen
        mockMvc.perform(get("/user/list"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("TEST")));
        
    }

}
package com.example.multiplace.web;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.ui.Model;


import static org.mockito.Mockito.lenient;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@ExtendWith(MockitoExtension.class)
class LoginControllerTest {

    @Mock
    private Model model;

    @InjectMocks
    private LoginController loginController;

    private MockMvc mockMvc;

    @Test
    public void testLogin() throws Exception {
        mockMvc = standaloneSetup(loginController).build();

        mockMvc.perform(MockMvcRequestBuilders.get("/users/login"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("auth-login"));
    }

    @Test
    public void testOnFailure() throws Exception {
        mockMvc = standaloneSetup(loginController).build();

        String email = "test@example.com";
        lenient().when(model.addAttribute("email", email)).thenReturn(model);

        mockMvc.perform(MockMvcRequestBuilders.post("/users/login-error")
                        .param("email", email))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("auth-login"))
                .andExpect(model().attribute("email", email))
                .andExpect(model().attribute("bad_credentials", "true"));
    }

}
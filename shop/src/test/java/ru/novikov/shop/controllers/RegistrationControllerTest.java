package ru.novikov.shop.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import ru.novikov.shop.model.User;
import ru.novikov.shop.service.UserService;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
public class RegistrationControllerTest {

    private MockMvc mockMvc;

    @Mock
    UserService userService;

    @InjectMocks
    RegistrationController registrationController;

    @BeforeEach
    public void setUp(){
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/src/main/resources/templates/");
        viewResolver.setSuffix(".html");
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(registrationController).setViewResolvers(viewResolver).build();
    }
    @Test
    public void addUserWhichIsNotExists() throws Exception{
        Mockito.when(userService.saveUser(any(User.class))).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders.post("/registration")
                .param("username", "username")
                .param("password", "password")
                .param("passwordConfirm", "password")
                .param("phoneNumber", "+79876543210"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    public void addUserWithWrongPasswordConfirmation() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/registration")
                        .param("username", "username")
                        .param("password", "password")
                        .param("passwordConfirm", "wrong_password")
                        .param("phoneNumber", "+79876543210"))
                .andExpect(status().isOk())
                .andExpect(view().name("registration"))
                .andExpect(model().attributeExists("passwordError"));
    }

}

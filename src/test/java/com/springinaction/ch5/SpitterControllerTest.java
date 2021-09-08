package com.springinaction.ch5;

import com.springinaction.common.Spitter;
import com.springinaction.data.SpitterRepository;
import com.springinaction.web.SpitterController;
import org.junit.Test;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
/**
 * Created by zjjfly on 2016/12/30.
 */
public class SpitterControllerTest{
    @Test
    public void shouldShowRegistration() throws Exception {
        SpitterController controller = new SpitterController();
        MockMvc mockMvc = standaloneSetup(controller).build();
        mockMvc.perform(get("/spitter/register")).andExpect(view().name("registerForm"));
    }

    @Test
    public void shouldProcessRegistration() throws Exception {
        SpitterRepository mockRepository = mock(SpitterRepository.class);
        Spitter unsaved = new Spitter("jbauer", "24hour", "Jack", "Bauer");
        Spitter saved = new Spitter(24L,"jbauer", "24hour", "Jack", "Bauer");
        when(mockRepository.save(unsaved)).thenReturn(saved);
        SpitterController controller = new SpitterController(mockRepository,new StandardPasswordEncoder());
        MockMvc mockMvc = standaloneSetup(controller).build();
        mockMvc.perform(post("/spitter/register")
        .param("firstName","Jack")
        .param("lastName","Bauer")
        .param("username","jbauer")
        .param("password","24hour"))
                .andExpect(redirectedUrl("/spitter/jbauer"));
        verify(mockRepository,atLeastOnce()).save(unsaved);
    }
}

package com.jutem.suggestion.controller;

import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;

import java.net.URI;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

public class SuggestionControllerTest {
    @Test
    public void testIndex() throws Exception {
        SuggestionController controller = new SuggestionController();
        MockMvc mockMvc = standaloneSetup(controller).build();
        mockMvc.perform(get(new URI("/"))).andExpect(view().name("index"));
    }
}

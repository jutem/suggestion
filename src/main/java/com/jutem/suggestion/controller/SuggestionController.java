package com.jutem.suggestion.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SuggestionController {

    @RequestMapping(value = "/")
    public String index(Model model) {
        return "index";
    }
}

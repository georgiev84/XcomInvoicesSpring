package com.petar.workspring.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    public ModelAndView modelandView(ModelAndView modelAndView){
        modelAndView.setViewName("index");

        return modelAndView;
    }
}

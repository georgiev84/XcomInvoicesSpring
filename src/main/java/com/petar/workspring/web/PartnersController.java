package com.petar.workspring.web;

import com.petar.workspring.viewmodels.PartnerModel;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

@Controller
public class PartnersController {

    @GetMapping("/login")
    public ModelAndView getDate(ModelAndView modelAndView){
        modelAndView.addObject("date", new Date());

        modelAndView.setViewName("login");
        return modelAndView;
    }
//    @PostMapping("/login")
//    public String loginPartner(@ModelAttribute PartnerModel partner, BindingResult bindingResult){
//        if(bindingResult.hasErrors()){
//            return "login";
//        }
//
//        int b=5;
//        return "/";
//    }
}

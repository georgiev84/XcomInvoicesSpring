package com.petar.workspring.web.controllers;

import com.petar.workspring.domain.models.binding.PartnerLoginBindingModel;
import com.petar.workspring.domain.models.service.PartnerServiceModel;
import com.petar.workspring.service.PartnerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class PartnersController {

    private final PartnerService partnerService;
    private final ModelMapper modelMapper;

    @Autowired
    public PartnersController(PartnerService partnerService, ModelMapper modelMapper) {
        this.partnerService = partnerService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/login")
    public ModelAndView login(ModelAndView modelAndView, HttpSession session){
        if(session.getAttribute("username") != null){

            modelAndView.setViewName("redirect:/invoices");
        } else {

            modelAndView.setViewName("login");
        }

        //modelAndView.setViewName("login");

        return modelAndView;
    }
    @PostMapping("/login")
    public ModelAndView loginConfirm(@ModelAttribute PartnerLoginBindingModel partnerLoginBindingModel, ModelAndView modelAndView, HttpSession session){
        PartnerServiceModel partnerServiceModel = this.partnerService.loginUser(this.modelMapper.map(partnerLoginBindingModel, PartnerServiceModel.class));
        if(partnerServiceModel == null){
            modelAndView.setViewName("redirect:/login");
            throw new IllegalArgumentException("User login failed!");
        }

        session.setAttribute("userId", partnerServiceModel.getId());
        session.setAttribute("username", partnerServiceModel.getUsername());
        session.setAttribute("company", partnerServiceModel.getCompany());

        modelAndView.setViewName("redirect:/invoices");
        return modelAndView;
    }

    // logout
    @GetMapping("/logout")
    public ModelAndView logout(ModelAndView modelAndView, HttpSession session){

        if(session.getAttribute("username") == null){
            modelAndView.setViewName("redirect:/login");
        } else {
            session.invalidate();
            modelAndView.setViewName("redirect:/");
        }
        return modelAndView;
    }
}

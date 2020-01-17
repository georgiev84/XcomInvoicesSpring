package com.petar.workspring.web.api.controller;

import com.petar.workspring.domain.models.binding.PartnerLoginBindingModel;
import com.petar.workspring.domain.models.service.PartnerServiceModel;
import com.petar.workspring.service.PartnerService;

import org.modelmapper.ModelMapper;
import org.springframework.http.*;
import org.springframework.mobile.device.Device;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@RestController
public class PartnersApiController {

    private final PartnerService partnerService;
    private final ModelMapper modelMapper;


    public PartnersApiController(PartnerService partnerService, ModelMapper modelMapper) {
        this.partnerService = partnerService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/api/login")
    public void login( HttpSession session, HttpServletResponse response) throws IOException {
        if(session.getAttribute("username") != null){

            response.sendRedirect("/api/login");
        } else {

            response.sendRedirect("/api/error");

        }

    }

    @GetMapping("/api/error")
    public String loginError() throws IOException {
        return "Wrong username or password";
    }


//    @RequestMapping(value = "/api/login", headers="Content-Type=application/json", method = RequestMethod.POST)
//    @ResponseStatus(HttpStatus.OK)
//    @ResponseBody
//    public String saveData(@RequestParam  String username,  @RequestParam String password, HttpServletResponse response, HttpSession session) throws IOException {
//
//        PartnerServiceModel inputModel = new PartnerServiceModel();
//        inputModel.setUsername(username);
//        inputModel.setPassword(password);
//
//
//        PartnerServiceModel partnerServiceModel = this.partnerService.loginUser(inputModel);
//        if(partnerServiceModel != null){
//
//            session.setAttribute("userId", partnerServiceModel.getId());
//            session.setAttribute("username", partnerServiceModel.getUsername());
//            session.setAttribute("company", partnerServiceModel.getCompany());
//
//            response.sendRedirect("/api/invoices");
//        }
//
//        int b=1;
//        return "ssss";
//    }

    @RequestMapping(value = "/api/login", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)

    public @ResponseBody
    String Submit(@RequestParam("username") String username, @RequestParam("password") String password, HttpServletResponse response, HttpSession session, @RequestHeader(value="User-Agent") String userAgent) throws IOException {

        PartnerServiceModel inputModel = new PartnerServiceModel();
        inputModel.setUsername(username);
        inputModel.setPassword(password);


        PartnerServiceModel partnerServiceModel = this.partnerService.loginUser(inputModel);
        if(partnerServiceModel != null){

            session.setAttribute("userId", partnerServiceModel.getId());
            session.setAttribute("username", partnerServiceModel.getUsername());
            session.setAttribute("company", partnerServiceModel.getCompany());


            // if client is mobile must redirect to response.sendRedirect("/api/invoices");
            String checkAgent = userAgent;
            int a=1;
           if(userAgent.equals("custom-agent")){
               // redirect to /api/invoices
               // must apply custom-user-agent in android app
               response.sendRedirect("/api/invoices");
           } else {
               response.sendRedirect("/invoices");
           }
        }

        int b=1;
        return "ssss";

    }





    }


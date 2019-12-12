package com.petar.workspring.web.controllers;

import com.petar.workspring.domain.data.Invoice;
import com.petar.workspring.domain.data.InvoiceProduct;
import com.petar.workspring.domain.models.binding.PartnerLoginBindingModel;
import com.petar.workspring.domain.models.service.PartnerServiceModel;
import com.petar.workspring.service.InvoiceService;
import com.petar.workspring.service.PartnerService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;

@Controller
public class InvoiceController {
    private final InvoiceService invoiceService;


    public InvoiceController(InvoiceService invoiceService, PartnerService partnerService, ModelMapper modelMapper, PartnerService partnerService1, ModelMapper modelMapper1) {
        this.invoiceService = invoiceService;

    }

    @GetMapping("/invoices")
    public ModelAndView invoices(ModelAndView modelAndView, HttpSession session){
        if(session.getAttribute("username") != null){

            modelAndView.setViewName("invoices");

            ArrayList<Invoice> invoices;


            Integer userid = (int) session.getAttribute("userId");

            if (userid == null){
                //kur tuka trqq varnesh neshto 4e ne eok da ne praish zaqvka naprazno
            } else {
                invoices = invoiceService.getInvoicesForUser(userid);
                modelAndView.addObject("company", session.getAttribute("company"));
                modelAndView.addObject("messages", invoices);
            }
        } else {
            modelAndView.setViewName("redirect:/");
        }





        return modelAndView;
    }

    @GetMapping("/invoices/{id}")
    public ModelAndView details(@PathVariable(name = "id") String id, ModelAndView modelAndView, HttpSession session){


        if(session.getAttribute("username") == null ){
            modelAndView.setViewName("redirect:/login");
        } else {

            ArrayList<InvoiceProduct> productList = invoiceService.getInvoiceDetails(id);


            modelAndView.addObject("invoiceDetails", productList);

            modelAndView.setViewName("invoice_details");
        }

        return modelAndView;

    }

//    @RequestMapping("/invoices/{id}")
//    public String getContent1(@PathVariable(name = "id") String id, Model model) {
//            ArrayList<InvoiceProduct> productList = invoiceService.getInvoiceDetails(id);
//
//            model.addAttribute("invoiceDetails", productList);
//
//        return "content :: resultList";
//    }

}

package com.petar.workspring.service;

import com.petar.workspring.domain.data.Invoice;
import com.petar.workspring.repository.PartnersRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class InvoiceServiceImplTests {
    ArrayList<Invoice> invoices;
    PartnersRepository invoiceRepository;


    @Test
    void getInvoicesForUser_when1Invoices_expect1Documents(){
        invoiceRepository = Mockito.mock(PartnersRepository.class);

       invoices = new ArrayList<Invoice>();
       invoices.add(new Invoice() {
           @Override
           public String getDocumentAcct() {
               return null;
           }

           @Override
           public String getInvoiceAcct() {
               return null;
           }

           @Override
           public Date getInvoiceDate() {
               return null;
           }

           @Override
           public BigDecimal getInvoiceSum() {
               return null;
           }
       });

        when(invoiceRepository.getInvoiceByPartnerId(101))
                .thenReturn(invoices);

        ModelMapper mapper = new ModelMapper();

        PartnerServiceImpl partnerService = new PartnerServiceImpl(invoiceRepository, mapper);
        InvoiceServiceImpl invoiceService = new InvoiceServiceImpl(invoiceRepository, mapper, partnerService);



        ArrayList<Invoice> testInvoices = invoiceService.getInvoicesForUser(101);

        assertEquals(invoices.size(), testInvoices.size());
    }
}

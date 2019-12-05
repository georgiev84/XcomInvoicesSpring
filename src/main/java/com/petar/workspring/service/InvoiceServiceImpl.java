package com.petar.workspring.service;

import com.petar.workspring.domain.data.Invoice;
import com.petar.workspring.domain.data.InvoiceProductList;
import com.petar.workspring.repository.PartnersRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class InvoiceServiceImpl implements InvoiceService {
    private final PartnersRepository invoiceRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public InvoiceServiceImpl(PartnersRepository invoiceRepository, ModelMapper modelMapper) {
        this.invoiceRepository = invoiceRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ArrayList<Invoice> getInvoicesForUser(String companyName) {
        ArrayList<Invoice> invoices = invoiceRepository.getInvoiceByPartnerName(companyName);
        int b=5;
        return invoices;
    }


    @Override
    public ArrayList<InvoiceProductList> getInvoiceDetails(String invoiceId) {
        ArrayList<InvoiceProductList> invoiceProductList = invoiceRepository.getInvoiceProducts(invoiceId);
        return invoiceProductList;
    }
}

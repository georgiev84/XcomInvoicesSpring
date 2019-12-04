package com.petar.workspring.service;

import com.petar.workspring.domain.data.Invoice;
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
    public ArrayList<String> getInvoicesForUser(int userId) {
        ArrayList<String> invoices = invoiceRepository.getInvoiceByPartnerId(userId);

        return invoices;
    }
}

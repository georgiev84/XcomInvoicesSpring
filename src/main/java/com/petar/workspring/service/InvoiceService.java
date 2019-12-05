package com.petar.workspring.service;


import com.petar.workspring.domain.data.Invoice;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public interface InvoiceService {
        ArrayList<Invoice> getInvoicesForUser(int userId);
}

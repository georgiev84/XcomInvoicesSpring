package com.petar.workspring.domain.data;


import org.springframework.beans.factory.annotation.Value;

import java.math.BigDecimal;
import java.util.Date;

public interface Invoice {
    String getDocumentAcct();
    String getInvoiceAcct();
    Date getInvoiceDate();
    BigDecimal getInvoiceSum();

}

package com.petar.workspring.domain.data;


import java.math.BigDecimal;
import java.util.Date;

public interface InvoiceBasicInfo {
    String getInvoiceAcct();
    Date getinvoiceDate();
    String getInvoicePartnerId();
    String getSumWithoutVat();
    String getVatOnly();
    BigDecimal getInvoiceSum();
    String getReason();
    String getDescription();
    String getPlace();
    String getRecipient();


}

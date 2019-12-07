package com.petar.workspring.domain.data;

import java.math.BigDecimal;
import java.util.Date;

public interface InvoiceProduct {
    String getCode();
    String getName();
    int getQtty();
    BigDecimal getPrice();

}

package com.petar.workspring.web.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceResponseModel {
    private String invoiceAcct;
    private String invoiceSum;
    private Date invoiceDate;
    private String documentAcct;


}

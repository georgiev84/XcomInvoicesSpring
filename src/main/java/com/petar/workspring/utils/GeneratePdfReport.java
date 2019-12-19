package com.petar.workspring.utils;


import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.petar.workspring.domain.data.InvoiceBasicInfo;
import com.petar.workspring.domain.data.InvoiceProduct;
import com.petar.workspring.domain.data.Owner;
import com.petar.workspring.domain.entities.Partner;

import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GeneratePdfReport {

    public static ByteArrayInputStream invoiceReport(List<InvoiceProduct> products, Partner partner, Owner owner, InvoiceBasicInfo invoiceBasicInfo) {

        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        final String FONT = "font/Helvetica.ttf";
        final String HEAD_FONT = "font/Verdana.ttf";

        try {

            Font commonFont = FontFactory.getFont(FONT, BaseFont.IDENTITY_H, true, 10);
            Font headFont = FontFactory.getFont(HEAD_FONT, BaseFont.IDENTITY_H, true, 10, Font.BOLD);
            Font titleFont = FontFactory.getFont(HEAD_FONT, BaseFont.IDENTITY_H, true, 20, Font.BOLD);

// TABLE OUTER  =========================================================================
            PdfPTable mainTable = new PdfPTable(2);
            mainTable.getDefaultCell().setBorder(0);
            mainTable.setWidthPercentage(100);

// TABLE BUYER  =========================================================================
            PdfPTable tableByer = new PdfPTable(1);
//            tableByer.getDefaultCell().setBorder(1);
            tableByer.getDefaultCell().setBorderWidthLeft(1);
            tableByer.setWidthPercentage(50);


            PdfPCell xCell;


            xCell = new PdfPCell(new Phrase("Получател", headFont));
//            xCell.setBorderColor(BaseColor.BLACK);
            xCell.setBackgroundColor(new BaseColor(242,242,240));
            xCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tableByer.addCell(xCell);

            // Add Partner Info
            xCell = new PdfPCell(new Phrase(partner.getCompany(), commonFont));
            xCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            xCell.setBorder(Rectangle.NO_BORDER);
            xCell.setBorderWidthLeft(1);
            xCell.setBorderWidthRight(1);
            tableByer.addCell(xCell);

            // Add Partner Address
            xCell = new PdfPCell(new Phrase("Адрес: " + partner.getCity() + ", " +partner.getAddress(), commonFont));
            xCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            xCell.setBorder(Rectangle.NO_BORDER);
            xCell.setBorderWidthLeft(1);
            xCell.setBorderWidthRight(1);
            tableByer.addCell(xCell);

            // Add Bulstat
            xCell = new PdfPCell(new Phrase("Булстат: " + partner.getBulstat(), commonFont));
            xCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            xCell.setBorder(Rectangle.NO_BORDER);
            xCell.setBorderWidthLeft(1);
            xCell.setBorderWidthRight(1);
            tableByer.addCell(xCell);


            // Add VAT
            xCell = new PdfPCell(new Phrase("ДДС Номер: " + partner.getTaxNo(), commonFont));
            xCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            xCell.setBorder(Rectangle.NO_BORDER);
            xCell.setBorderWidthLeft(1);
            xCell.setBorderWidthRight(1);
            tableByer.addCell(xCell);

            // Add MOL
            xCell = new PdfPCell(new Phrase("МОЛ: " + partner.getMol(), commonFont));
            xCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            xCell.setBorder(Rectangle.NO_BORDER);
            xCell.setBorderWidthLeft(1);
            xCell.setBorderWidthRight(1);
            xCell.setBorderWidthBottom(1);
            tableByer.addCell(xCell);

            mainTable.addCell(tableByer);


// TABLE SELLER  =========================================================================
            PdfPTable tableSeller = new PdfPTable(1);
            tableByer.setWidthPercentage(50);


            xCell = new PdfPCell(new Phrase("Доставчик", headFont));
//            xCell.setBorderColor(BaseColor.BLACK);
            xCell.setBackgroundColor(new BaseColor(242,242,240));
            xCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tableSeller.addCell(xCell);

            // Add Partner Info
            xCell = new PdfPCell(new Phrase("" + owner.getCompany(), commonFont));
            xCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            xCell.setBorder(Rectangle.NO_BORDER);
            xCell.setBorderWidthLeft(1);
            xCell.setBorderWidthRight(1);
            tableSeller.addCell(xCell);

            // Add Partner Address
            xCell = new PdfPCell(new Phrase("Адрес: " + owner.getCity() + ", " +partner.getAddress(), commonFont));
            xCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            xCell.setBorder(Rectangle.NO_BORDER);
            xCell.setBorderWidthLeft(1);
            xCell.setBorderWidthRight(1);
            tableSeller.addCell(xCell);

            // Add VAT
            xCell = new PdfPCell(new Phrase("ДДС Номер: " + owner.getTaxNo(), commonFont));
            xCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            xCell.setBorder(Rectangle.NO_BORDER);
            xCell.setBorderWidthLeft(1);
            xCell.setBorderWidthRight(1);
            tableSeller.addCell(xCell);

            // Add MOL
            xCell = new PdfPCell(new Phrase("МОЛ: " + owner.getMol(), commonFont));
            xCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            xCell.setBorder(Rectangle.NO_BORDER);
            xCell.setBorderWidthLeft(1);
            xCell.setBorderWidthRight(1);
            xCell.setBorderWidthBottom(1);
            tableSeller.addCell(xCell);

            mainTable.addCell(tableSeller);




// TABLE PRODUCTS =========================================================================
            PdfPTable table = new PdfPTable(5);
            table.setWidthPercentage(100);


            table.setWidths(new float[] { 2, 4, 20, 5, 5 });



            PdfPCell hcell;
            hcell = new PdfPCell(new Phrase("Ред", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            hcell.setBackgroundColor(new BaseColor(242,242,240));
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Код", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            hcell.setBackgroundColor(new BaseColor(242,242,240));
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Продукт", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            hcell.setBackgroundColor(new BaseColor(242,242,240));
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Количество", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            hcell.setBackgroundColor(new BaseColor(242,242,240));
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Цена без ДДС", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            hcell.setBackgroundColor(new BaseColor(242,242,240));
            table.addCell(hcell);

            DecimalFormat df = new DecimalFormat("0.0000");
//            df.setRoundingMode(RoundingMode.CEILING);

            int counterRow =0;

            for (InvoiceProduct product : products) {

                PdfPCell cell;
                counterRow++;
                cell = new PdfPCell(new Phrase(String.valueOf(counterRow),commonFont));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setPaddingRight(5);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(product.getCode(),commonFont));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setPaddingRight(5);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(product.getName(),commonFont));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setPaddingRight(5);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(String.valueOf(product.getQtty()),commonFont));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setPaddingRight(5);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(String.valueOf(df.format(product.getPrice())),commonFont));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setPaddingRight(5);
                table.addCell(cell);


            }


// TABLE REASON AND PAYMENT INFO  =========================================================================
            DecimalFormat dfTwo = new DecimalFormat("0.00");
            PdfPCell cellTest;

            PdfPTable tableReasons = new PdfPTable(2);
            tableReasons.setHorizontalAlignment(PdfPTable.ALIGN_LEFT);
            tableReasons.setWidthPercentage(100);
            tableReasons.setWidths(new float[] { 1, 4 });

            cellTest = new PdfPCell(new Phrase("Описание: ",commonFont));
            cellTest.setBorder(Rectangle.NO_BORDER);
            cellTest.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cellTest.setPaddingRight(5);
            tableReasons.addCell(cellTest);

            cellTest = new PdfPCell(new Phrase(String.valueOf(invoiceBasicInfo.getDescription()),commonFont));
            cellTest.setBorder(Rectangle.NO_BORDER);
            cellTest.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cellTest.setPaddingRight(5);
            tableReasons.addCell(cellTest);

            cellTest = new PdfPCell(new Phrase("Срок на плащане: ",commonFont));
            cellTest.setBorder(Rectangle.NO_BORDER);
            cellTest.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cellTest.setPaddingRight(5);
            tableReasons.addCell(cellTest);

            cellTest = new PdfPCell(new Phrase(String.valueOf(invoiceBasicInfo.getReason()),commonFont));
            cellTest.setBorder(Rectangle.NO_BORDER);
            cellTest.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cellTest.setPaddingRight(5);
            tableReasons.addCell(cellTest);


// TABLE INVOICE NUMBER AND DATE  =========================================================================
            PdfPTable tableInvoiceInfo = new PdfPTable(2);
//            tableInvoiceInfo.setHorizontalAlignment(PdfPTable.ALIGN_LEFT);
            tableInvoiceInfo.setWidthPercentage(50);

            PdfPCell cellInfo;

            cellInfo = new PdfPCell(new Phrase("Номер: ",commonFont));
            cellInfo.setBorder(Rectangle.NO_BORDER);
            cellInfo.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cellInfo.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cellInfo.setPaddingRight(5);
            tableInvoiceInfo.addCell(cellInfo);

            cellInfo = new PdfPCell(new Phrase(String.valueOf(invoiceBasicInfo.getInvoiceAcct()),commonFont));
            cellInfo.setBorder(Rectangle.NO_BORDER);
            cellInfo.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cellInfo.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellInfo.setPaddingRight(5);
            tableInvoiceInfo.addCell(cellInfo);

            cellInfo = new PdfPCell(new Phrase("Дата: ",commonFont));
            cellInfo.setBorder(Rectangle.NO_BORDER);
            cellInfo.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cellInfo.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cellInfo.setPaddingRight(5);
            tableInvoiceInfo.addCell(cellInfo);

            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyy");

            cellInfo = new PdfPCell(new Phrase(String.valueOf(formatter.format(invoiceBasicInfo.getinvoiceDate())),commonFont));
            cellInfo.setBorder(Rectangle.NO_BORDER);
            cellInfo.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cellInfo.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellInfo.setPaddingRight(5);
            tableInvoiceInfo.addCell(cellInfo);
            // END


// PARAGRAPHS  ==============================================================================
            Paragraph paragraphTotalSum = new Paragraph("");
            paragraphTotalSum.setFont(headFont);
            paragraphTotalSum.add("Сума с ДДС: " + String.valueOf(dfTwo.format(invoiceBasicInfo.getInvoiceSum())) + " лв.");
            paragraphTotalSum.setAlignment(Paragraph.ALIGN_RIGHT);

            Paragraph paragraph = new Paragraph(" ");

            Paragraph invoicePrint = new Paragraph("Фактура");
            invoicePrint.setAlignment(Element.ALIGN_CENTER);
            invoicePrint.setFont(titleFont);
            invoicePrint.add("ФАКТУРА");

            Paragraph paragraphPaymentType = new Paragraph("");
            paragraphPaymentType.setFont(commonFont);

            String payment = invoiceBasicInfo.getPaymentType() == 2 ? "Превод по сметка" : "в Брой";
            paragraphPaymentType.add("Плащане: " + payment);


// BUILD DOCUMENT  =========================================================================
            PdfWriter.getInstance(document, out);
            document.open();


            document.add(mainTable);
            document.add(paragraph);
            document.add(invoicePrint);
            document.add(paragraph);
            document.add(tableInvoiceInfo);
            document.add(paragraph);

            document.add(table);
            document.add(paragraph);
            document.add(paragraphTotalSum);
            document.add(paragraph);
            document.add(paragraphPaymentType);
            document.add(paragraph);
            document.add(tableReasons);


            document.close();

        } catch (DocumentException ex) {

            Logger.getLogger(GeneratePdfReport.class.getName()).log(Level.SEVERE, null, ex);
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
}
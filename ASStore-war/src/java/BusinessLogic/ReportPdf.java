/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.FontSelector;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 *
 * @author TRAN HO QUANG
 */
public class ReportPdf {

    Document document;

    public ReportPdf(File fileName) throws FileNotFoundException, DocumentException {
        document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(fileName));
    }

    public void writeContent(List<Others.StatisticProductCategory> listStatisticDetails, String fromDate, String toDate, String today) {
        try {
            document.open();
            writeHeader(fromDate, toDate);
            writeBody(listStatisticDetails);
            writeFooter(today);
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void writeBody(List<Others.StatisticProductCategory> listStatisticDetails) {
        try {
            PdfPTable table = new PdfPTable(4);
            //Khởi tạo 3 ô header
            PdfPCell header1 = new PdfPCell(new Paragraph("Category"));
            PdfPCell header2 = new PdfPCell(new Paragraph("Total Post Product"));
            PdfPCell header3 = new PdfPCell(new Paragraph("Current Quantity Product"));
            PdfPCell header4 = new PdfPCell(new Paragraph("Total Quantity Sold Product"));
            //Thêm 3 ô header vào table
            table.addCell(header1);
            table.addCell(header2);
            table.addCell(header3);
            table.addCell(header4);
            //Khởi tạo 3 ô data: ô số 1 là string, ô số 2 là ảnh, ô số 3 là table
            for (Others.StatisticProductCategory sta : listStatisticDetails) {
                PdfPCell data1 = new PdfPCell(new Paragraph(sta.getName()));
                PdfPCell data2 = new PdfPCell(new Paragraph(String.valueOf(sta.getTotalProduct())));
                PdfPCell data3 = new PdfPCell(new Paragraph(String.valueOf(sta.getCurrentQuantityProducts())));
                PdfPCell data4 = new PdfPCell(new Paragraph(String.valueOf(sta.getSoldQuantity())));
                table.addCell(data1);
                table.addCell(data2);
                table.addCell(data3);
                table.addCell(data4);
            }
            document.add(table);
        } catch (Exception e) {
        }
    }

    public void writeHeader(String fromDate, String toDate) {
        try {
            FontSelector selector1 = new FontSelector();
            selector1.addFont(FontFactory.getFont(FontFactory.TIMES_BOLD, 50));
            Phrase ph1 = selector1.process("ASStore");
            
            FontSelector selector2 = new FontSelector();
            selector2.addFont(FontFactory.getFont(FontFactory.TIMES_ROMAN, 20));
            Phrase ph2 = selector2.process("Statistical Total Post, Current Quantity, Total Quantity Sold Product Of Category");
            
            FontSelector selector3 = new FontSelector();
            selector3.addFont(FontFactory.getFont(FontFactory.TIMES_ROMAN, 16));
            Phrase ph3 = selector3.process("Day From: " + fromDate + " --- " + "Day To: " + toDate);
            
            Paragraph para1 = new Paragraph(ph1);
            Paragraph para2 = new Paragraph(ph2);
            Paragraph para3 = new Paragraph(ph3);

            
            para1.setIndentationLeft(80);
            para1.setIndentationRight(80);
            para1.setAlignment(Element.ALIGN_CENTER);
            para1.setSpacingAfter(15);
            
            
            
            para2.setIndentationLeft(80);
            para2.setIndentationRight(80);
            para2.setAlignment(Element.ALIGN_CENTER);
            para2.setSpacingAfter(15);

            para3.setIndentationLeft(80);
            para3.setIndentationRight(80);
            para3.setAlignment(Element.ALIGN_CENTER);
            para3.setSpacingAfter(15);
            
            document.add(para1);
            document.add(para2);
            document.add(para3);
            document.add(new Paragraph(""));
            
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    public void writeFooter(String today) {
        try {
            FontSelector selectorFt1 = new FontSelector();
            selectorFt1.addFont(FontFactory.getFont(FontFactory.TIMES_ROMAN, 14));
            Phrase ft1 = selectorFt1.process("Ho Chi Minh, Day: "+ today);
            
            FontSelector selectorFt2 = new FontSelector();
            selectorFt2.addFont(FontFactory.getFont(FontFactory.TIMES_ROMAN, 14));
            Phrase ft2 = selectorFt2.process("CA");
            
            FontSelector selectorFt3 = new FontSelector();
            selectorFt3.addFont(FontFactory.getFont(FontFactory.TIMES_ROMAN, 14));
            Phrase ft3 = selectorFt3.process("Sign");
            
            Paragraph para1 = new Paragraph(ft1);
            Paragraph para2 = new Paragraph(ft2);
            Paragraph para3 = new Paragraph(ft3);
            
            para1.setIndentationLeft(80);
            para1.setIndentationRight(50);
            para1.setSpacingBefore(15);
            para1.setAlignment(Element.ALIGN_RIGHT);
            
            
            para2.setIndentationLeft(80);
            para2.setIndentationRight(105);
            para2.setSpacingBefore(15);
            para2.setAlignment(Element.ALIGN_RIGHT);
            
            para3.setIndentationLeft(80);
            para3.setIndentationRight(100);
            para3.setSpacingBefore(15);
            para3.setAlignment(Element.ALIGN_RIGHT);
            
            document.add(para1);
            document.add(para2);
            document.add(para3);
            document.add(new Paragraph(""));
            document.add(new Paragraph(""));
            document.add(new Paragraph(""));
            
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

}

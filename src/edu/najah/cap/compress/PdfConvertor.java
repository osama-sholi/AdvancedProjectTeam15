package edu.najah.cap.compress;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;

public class PdfConvertor{
    public void ConvertTOPdf(String fileName) {
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(fileName));
            document.open();
            BufferedReader br = new BufferedReader(new FileReader("templeate.txt"));
            String line;
            while ((line = br.readLine()) != null) {
                document.add(new Paragraph(line));
            }
            br.close();
            document.close();
            System.out.println("Finished Convert to PDF");

        }
        catch (Exception e){
            System.err.println(e);
        }
    }
}


package edu.najah.cap.compress;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import edu.najah.cap.exceptions.FileDeletionException;
import edu.najah.cap.logs.MyLogging;

import java.io.*;
import java.util.logging.Level;

import static edu.najah.cap.logs.MyLogging.log;

public class PDFConvertor {
    public static String ConvertTOPdf(String path) throws IOException {
        log(Level.INFO, "Converting to PDF", "PDFConvertor", "ConvertTOPdf");
        String textFile = path;
        String pdfFile = path.substring(0, path.length() - 4) + ".pdf";
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(pdfFile));
            document.open();
            BufferedReader bufferedReader = new BufferedReader(new FileReader(textFile));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                document.add(new Paragraph(line));
            }
            bufferedReader.close();
            document.close();

            try {
                File file = new File(textFile);
                if (!file.delete()) {
                    throw new FileDeletionException("File Deletion Failed");
                }
            } catch (FileDeletionException e) {
                log(Level.SEVERE, e.getMessage(), "PDFConvertor", "ConvertTOPdf");
            }
            log(Level.INFO, "Converting Completed", "PDFConvertor", "ConvertTOPdf");
            return pdfFile;
        } catch(DocumentException e) {
            log(Level.SEVERE, e.getMessage(), "PDFConvertor", "ConvertTOPdf");
            return null;
        }
    }
}


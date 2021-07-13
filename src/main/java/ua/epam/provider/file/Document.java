package ua.epam.provider.file;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import ua.epam.provider.entity.Tariff;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class Document extends com.itextpdf.text.Document {
    public void createPDF(List<Tariff> list) throws DocumentException, IOException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("Tariffs.pdf"));
        BaseFont bfComic = BaseFont.createFont("c:\\windows\\fonts\\arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);

        Font font = new Font(bfComic, 16, Font.NORMAL);

        document.open();
        document.add(new Paragraph("Название тарифа         Цена", font));
        document.add(new Paragraph("_____________________________________________"));
        //Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
        for (Tariff tariff : list
        ) {
            document.add(new Paragraph(tariff.getTitle() + "    " + tariff.getPriceByDay(),font));
        }
        document.close();
    }
}


package com.aleksandrakrzak.shop.generator.strategy;

import com.aleksandrakrzak.shop.generator.model.FileType;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

@Slf4j
@Service
public class PdfGenerator extends GeneratorStrategy {

    public PdfGenerator() {
        super(FileType.PDF);
    }

    @Override
    public byte[] generateProductsFile() {
        Document document = new Document(PageSize.A4);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(); //wykorzytsywana do emitowania strumieni
        try {
            PdfWriter.getInstance(document, byteArrayOutputStream); //
            document.open();
            PdfPTable pdfPTable = new PdfPTable(5); // klasa ktora stworzy tabelke w pdf
            pdfPTable.addCell("Id");
            pdfPTable.addCell("Name");
            pdfPTable.addCell("Description");
            pdfPTable.addCell("Price");
            pdfPTable.addCell("Quantity");

            productRepository.findAll().forEach(product -> {

                pdfPTable.addCell(product.getId().toString());
                pdfPTable.addCell(product.getName());
                pdfPTable.addCell(product.getDescription());
                pdfPTable.addCell(product.getPrice().toString());
                pdfPTable.addCell(product.getQuantity().toString());
            });

            document.add(pdfPTable);
            document.close(); // musimy zamknac dokument przed zapisaniem do byte[]
            return byteArrayOutputStream.toByteArray();
        } catch (DocumentException e) {
            log.error(e.getMessage(), e);
        }
        return new byte[0];
    }
}

package com.aleksandrakrzak.shop.generator.strategy;

import com.aleksandrakrzak.shop.domain.dao.Product;
import com.aleksandrakrzak.shop.generator.model.FileType;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class XlsGenerator extends GeneratorStrategy {

    public XlsGenerator() {
        super(FileType.XLS);
    }

    @Override
    public byte[] generateProductsFile() {
        HSSFWorkbook workbook = new HSSFWorkbook();// klasa do exela - tworzyplik exelowy
        HSSFSheet sheet = workbook.createSheet("report");// tworzy sheeta exelowego

        int rowCount = 0;

        HSSFRow row = sheet.createRow(rowCount);
        row.createCell(0).setCellValue("ID");
        row.createCell(1).setCellValue("Name");
        row.createCell(2).setCellValue("Description");
        row.createCell(3).setCellValue("Price");
        row.createCell(4).setCellValue("Quantity");

        List<Product> allProducts = productRepository.findAll();

        for (int i = 0; i < allProducts.size(); i++) {
            row = sheet.createRow(++rowCount);
            row.createCell(0).setCellValue(allProducts.get(i).getId());
            row.createCell(1).setCellValue(allProducts.get(i).getName());
            row.createCell(2).setCellValue(allProducts.get(i).getDescription());
            row.createCell(3).setCellValue(allProducts.get(i).getPrice().toString());
            row.createCell(4).setCellValue(allProducts.get(i).getQuantity().toString());
        }

        sheet.setAutoFilter(new CellRangeAddress(0, rowCount, 0, 4)); //dodaje filtrowane dla wszystkich pol w exelu

        return workbook.getBytes();
    }
}

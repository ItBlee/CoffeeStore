package Utils;

import DAO.Mapper.Interfaces.IExcelRowMapper;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Font;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;

import static Utils.SystemConstant.*;

public class FileHandler {
    public static void exportConfig(Properties prop) {
        try {
            OutputStreamWriter output = new OutputStreamWriter(
                    new FileOutputStream(CONFIG_FILE_URL),
                    StandardCharsets.UTF_8);
            prop.store(output, "Coffee Store App Config File");
            output.close();
        } catch (Exception ignored) {}
    }

    public static Properties importConfig(String filePath) {
        Properties prop = new Properties();
        try {
            InputStreamReader input = new InputStreamReader(
                    new FileInputStream(filePath),
                    StandardCharsets.UTF_8);
            prop.load(input);
            input.close();
        } catch (Exception ignored) {}
        return prop;
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static <E> void exportExcel(String filePath, ArrayList<E> list, IExcelRowMapper<E> mapper) throws IOException {
        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("Sheet 1");

        int rowIndex = 0;
        // Create font
        Font font = sheet.getWorkbook().createFont();
        font.setFontName("Times New Roman");
        font.setBold(true);
        font.setFontHeightInPoints((short) 14);
        font.setColor(IndexedColors.WHITE.getIndex());

        // Create CellStyle
        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        cellStyle.setFont(font);
        cellStyle.setFillForegroundColor(IndexedColors.BLUE.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setBorderBottom(BorderStyle.THIN);

        // Create row
        Row row = sheet.createRow(rowIndex);

        mapper.mapExcelHeader(cellStyle, row);
        rowIndex++;
        for (E dto : list) {
            mapper.mapExcelBody(dto, row);
            rowIndex++;
        }
        int numberOfColumn = sheet.getRow(0).getPhysicalNumberOfCells();
        for (int columnIndex = 0; columnIndex < numberOfColumn; columnIndex++) {
            sheet.autoSizeColumn(columnIndex);
        }
        File file = new File(filePath);
        file.getParentFile().mkdirs();
        file.createNewFile();
        try (OutputStream os = new FileOutputStream(file)) {
            workbook.write(os);
            workbook.close();
        }
    }

    public static <E> ArrayList<E> importExcel(String filePath, IExcelRowMapper<E> mapper) throws IOException {
        ArrayList<E> list = new ArrayList<E>();
        InputStream inputStream = new FileInputStream(filePath);
        Workbook workbook = new HSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheetAt(0);

        DataFormatter dataFormatter = new DataFormatter();
        for (Row nextRow : sheet) {
            if (nextRow.getRowNum() == 0) {
                continue;
            }
            Iterator<Cell> cellIterator = nextRow.cellIterator();
            E dto = null;
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                String cellValue = dataFormatter.formatCellValue(cell);
                int columnIndex = cell.getColumnIndex();
                dto = mapper.mapExcelToDto(dto, columnIndex, cellValue);
            }
            if (dto != null)
                list.add(dto);
        }
        workbook.close();
        inputStream.close();
        return list;
    }

    public static void exportPDFReport(String path) {
        PDDocument document = new PDDocument();
        PDPage newPage = new PDPage();
        document.addPage(newPage);
        PDPage getPage = document.getPage(0);
        try {
            InputStream fontInput = new FileInputStream("bin/font/segoeui.ttf");
            InputStream fontBoldInput = new FileInputStream("bin/font/seguibl.ttf");
            PDFont font = PDType0Font.load(document, fontInput);
            PDFont fontBold = PDType0Font.load(document, fontBoldInput);
            PDPageContentStream content = new PDPageContentStream(document, getPage);

            content.beginText();
            content.setFont(fontBold, 20);
            content.newLineAtOffset(200, 750);
            content.showText("THE CROSSING COFFEE");
            content.endText();

            content.beginText();
            content.setFont(fontBold, 18);
            content.newLineAtOffset(270, 690);
            content.showText("Hóa đơn");
            content.endText();

            content.beginText();
            content.setFont(font, 14);
            content.setLeading(20f);
            content.newLineAtOffset(60, 610);
            content.showText("Customer Name: ");
            content.newLine();
            content.showText("Phone Number: ");
            content.endText();

            content.beginText();
            content.setFont(font, 14);
            content.setLeading(20f);
            content.newLineAtOffset(170, 610);
            content.showText("Trần Long Tuấn Vũ");
            content.newLine();
            content.showText("0397631223");
            content.endText();

            content.beginText();
            content.setFont(font, 14);
            content.newLineAtOffset(80, 540);
            content.showText("Product Name");
            content.endText();

            content.beginText();
            content.setFont(font, 14);
            content.newLineAtOffset(200, 540);
            content.showText("Unit Price");
            content.endText();

            content.beginText();
            content.setFont(font, 14);
            content.newLineAtOffset(310, 540);
            content.showText("Quantity");
            content.endText();

            content.beginText();
            content.setFont(font, 14);
            content.newLineAtOffset(410, 540);
            content.showText("Price");
            content.endText();

            content.beginText();
            content.setFont(font, 12);
            content.setLeading(20f);
            content.newLineAtOffset(80, 520);
            for(int i =0; i< 10; i++) {
                content.showText("" + i);
                content.newLine();
            }
            content.endText();

            content.beginText();
            content.setFont(font, 12);
            content.setLeading(20f);
            content.newLineAtOffset(200, 520);
            for(int i =0; i< 10; i++) {
                content.showText("" + i);
                content.newLine();
            }
            content.endText();

            content.beginText();
            content.setFont(font, 12);
            content.setLeading(20f);
            content.newLineAtOffset(310, 520);
            for(int i =0; i< 10; i++) {
                content.showText("" + i);
                content.newLine();
            }
            content.endText();

            content.beginText();
            content.setFont(font, 12);
            content.setLeading(20f);
            content.newLineAtOffset(410, 520);
            for(int i =0; i< 10; i++) {
                content.showText("" + i);
                content.newLine();
            }
            content.endText();

            content.beginText();
            content.setFont(font, 14);
            content.newLineAtOffset(310, (500-(20*10)));
            content.showText("Total: ");
            content.endText();

            content.beginText();
            content.setFont(font, 14);
            content.newLineAtOffset(410, (500-(20*10)));
            content.showText("999");
            content.endText();

            content.close();
            document.save(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ImageIcon createImageIcon(String path, int width, int height) {
        ImageIcon icon = new ImageIcon(path);
        if (icon.getIconHeight() != height || icon.getIconWidth() != width) {
            int scaleMode = Image.SCALE_SMOOTH;
            if (path.contains(".gif"))
                scaleMode = Image.SCALE_DEFAULT;
            Image scale = icon.getImage().getScaledInstance(width, height, scaleMode);
            icon = new ImageIcon(scale);
        }
        return icon;
    }

    public static File getFile(String path) {
        return new File(path);
    }

    public static void main(String[] args) {
        exportPDFReport("export/HoaDon.pdf");
    }
}

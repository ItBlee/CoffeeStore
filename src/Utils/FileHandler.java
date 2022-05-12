package Utils;

import DAO.Mapper.Interfaces.IExcelRowMapper;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Font;

import javax.swing.*;
import java.awt.*;
import java.io.*;
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

    public static Properties importConfig() {
        Properties prop = new Properties();
        try {
            InputStreamReader input = new InputStreamReader(
                    new FileInputStream(CONFIG_FILE_URL),
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

    public static Properties importLanguage(String language) {
        Properties prop = new Properties();
        try {
            InputStreamReader input = new InputStreamReader(
                    new FileInputStream("bin/languages/" + language + ".language"),
                    StandardCharsets.UTF_8);
            prop.load(input);
            input.close();
        } catch (Exception ignored) {}
        return prop;
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
}

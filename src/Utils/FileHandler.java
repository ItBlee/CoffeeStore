package Utils;

import DAO.Mapper.Interfaces.IExcelRowMapper;
import GUI.components.Themes;
import com.formdev.flatlaf.intellijthemes.FlatAllIJThemes;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import javax.swing.*;
import java.awt.*;
import java.awt.Font;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;

import static Utils.SystemConstant.*;

public class FileHandler {
    public static void exportConfig() {
        try {
            OutputStream output = new FileOutputStream(CONFIG_FILE_URL);
            Properties prop = new Properties();

            if (General.USER_IS_REMEMBER) {
                prop.setProperty(CONFIG_PROP_USER_USERNAME, General.USER_USERNAME);
                prop.setProperty(CONFIG_PROP_USER_PASSWORD, General.USER_PASSWORD);
                prop.setProperty(CONFIG_PROP_USER_REMEMBER, String.valueOf(General.USER_IS_REMEMBER));
            }
            prop.setProperty(CONFIG_PROP_DB_HOST, General.DB_HOST);
            prop.setProperty(CONFIG_PROP_DB_NAME, General.DB_NAME);
            prop.setProperty(CONFIG_PROP_DB_USERNAME, General.DB_USERNAME);
            if (!General.DB_PASSWORD.isBlank())
                prop.setProperty(CONFIG_PROP_DB_PASSWORD, General.DB_PASSWORD);
            prop.setProperty(CONFIG_PROP_THEME_NAME, General.THEME_INFO.getName());
            if (General.THEME_FONT != null) {
                prop.setProperty(CONFIG_PROP_THEME_FONT_NAME, General.THEME_FONT.getName());
                prop.setProperty(CONFIG_PROP_THEME_FONT_TYPE, String.valueOf(General.THEME_FONT.getStyle()));
                prop.setProperty(CONFIG_PROP_THEME_FONT_SIZE, String.valueOf(General.THEME_FONT.getSize()));
            }

            prop.store(output, "Coffee Store App Config File");
            output.close();
        } catch (Exception ignored) {}
    }

    public static void importConfig() {
        Properties prop = new Properties();
        try {
            InputStream input = new FileInputStream(CONFIG_FILE_URL);
            prop.load(input);
            input.close();
        } catch (Exception e) {
            return;
        }

        General.USER_USERNAME = prop.getProperty(CONFIG_PROP_USER_USERNAME);
        General.USER_PASSWORD = prop.getProperty(CONFIG_PROP_USER_PASSWORD);
        General.USER_IS_REMEMBER = Boolean.parseBoolean(prop.getProperty(CONFIG_PROP_USER_REMEMBER));

        String dbHost = prop.getProperty(CONFIG_PROP_DB_HOST);
        String dbName = prop.getProperty(CONFIG_PROP_DB_NAME);
        String dbUsername = prop.getProperty(CONFIG_PROP_DB_USERNAME);
        String dbPassword = prop.getProperty(CONFIG_PROP_DB_PASSWORD);
        if (dbHost != null && !dbHost.isBlank())
            General.DB_HOST = dbHost;
        if (dbName != null && !dbName.isBlank())
            General.DB_NAME = dbName;
        if (dbUsername != null && !dbUsername.isBlank())
            General.DB_USERNAME = dbUsername;
        if (dbPassword != null && !dbPassword.isBlank())
            General.DB_PASSWORD = dbPassword;

        String themeName = prop.getProperty(CONFIG_PROP_THEME_NAME);
        FlatAllIJThemes.FlatIJLookAndFeelInfo theme = Themes.getThemeInfoByName(themeName);
        if (theme != null)
            General.THEME_INFO = theme;

        String fontName = prop.getProperty(CONFIG_PROP_THEME_FONT_NAME);
        if (fontName != null && !fontName.isBlank()) {
            int fontStyle, fontSize;
            try {
                fontStyle = Integer.parseInt(prop.getProperty(CONFIG_PROP_THEME_FONT_TYPE));
            } catch (NumberFormatException e) {
                fontStyle = Font.PLAIN;
            }
            try {
                fontSize = Integer.parseInt(prop.getProperty(CONFIG_PROP_THEME_FONT_SIZE));
            } catch (NumberFormatException e) {
                fontSize = 14;
            }
            General.THEME_FONT = new java.awt.Font(fontName, fontStyle, fontSize);
        }
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static <E> void exportExcel(String filePath, ArrayList<E> list, IExcelRowMapper<E> mapper) throws IOException {
        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("Sheet 1");

        int rowIndex = 0;
        mapper.mapExcelHeader(sheet, rowIndex);
        rowIndex++;
        for (E ele : list) {
            Row row = sheet.createRow(rowIndex);
            mapper.mapExcelBody(ele, row);
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
}

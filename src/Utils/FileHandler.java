package Utils;

import Mapper.Interfaces.IExcelRowMapper;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;

import static Utils.SystemConstant.*;

public class FileHandler {
    /**
     * Mở Dialog để người dùng chọn file muốn thao tác
     * @param path đường dẫn mặc định khi mở.
     * @return String - đường dẫn File, trả về NULL nếu hủy/thoát Dialog
     */
    public static String showFileChooser(String path) {
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel Files", "xls");
        JFileChooser fileChooser = new JFileChooser(path);
        fileChooser.setDialogTitle("Chọn file");
        fileChooser.setFileFilter(filter);
        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
            return fileChooser.getSelectedFile().getAbsolutePath();
        return null;
    }

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

            prop.store(output, "Coffee Store App Config File");
            output.close();
        } catch (IOException ignored) {}
    }

    public static void importConfig() {
        Properties prop = new Properties();
        try {
            InputStream input = new FileInputStream(CONFIG_FILE_URL);
            prop.load(input);
            input.close();
        } catch (IOException e) {
            return;
        }

        General.USER_USERNAME = prop.getProperty(CONFIG_PROP_USER_USERNAME);
        General.USER_PASSWORD = prop.getProperty(CONFIG_PROP_USER_PASSWORD);
        General.USER_IS_REMEMBER = Boolean.parseBoolean(prop.getProperty(CONFIG_PROP_USER_REMEMBER));

        String dbHost = prop.getProperty(CONFIG_PROP_DB_HOST);
        String dbName = prop.getProperty(CONFIG_PROP_DB_NAME);
        String dbUsername = prop.getProperty(CONFIG_PROP_DB_USERNAME);
        String dbPassword = prop.getProperty(CONFIG_PROP_DB_PASSWORD);
        if (!dbHost.isBlank())
            General.DB_HOST = dbHost;
        if (!dbName.isBlank())
            General.DB_NAME = dbName;
        if (!dbUsername.isBlank())
            General.DB_USERNAME = dbUsername;
        if (!dbPassword.isBlank())
            General.DB_PASSWORD = dbPassword;
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
}

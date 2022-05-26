package Utils;

import BUS.Interfaces.IKhachHangBUS;
import BUS.Interfaces.INhaCungCapBUS;
import BUS.Interfaces.INhanVienBUS;
import BUS.Interfaces.ISanPhamBUS;
import BUS.KhachHangBUS;
import BUS.NhaCungCapBUS;
import BUS.NhanVienBUS;
import BUS.SanPhamBUS;
import DAO.Mapper.Interfaces.IExcelRowMapper;
import DTO.*;
import DTO.Interface.IDetailEntity;
import DTO.Interface.IEntity;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
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

    public static boolean exportPDFReport(String path, IEntity reportInstance, ArrayList<IDetailEntity> list) {
        ISanPhamBUS sanPhamBUS = new SanPhamBUS();
        INhanVienBUS nhanVienBUS = new NhanVienBUS();

        String title;
        String cooperatorLabel;
        String cooperatorDetail;
        String employeeDetail;
        String timestamp;
        String totalPrice;
        String salePrice = null;
        String payment = null;

        ArrayList<SanPhamDTO> productList = new ArrayList<SanPhamDTO>();
        for (IDetailEntity entity:list) {
            SanPhamDTO dto = sanPhamBUS.findByID(entity.getForeignID());
            if (dto != null && dto.getTinhTrang() != 0) {
                productList.add(dto);
            } else {
                list.remove(entity);
            }
        }


        if (reportInstance instanceof HoaDonDTO) {
            HoaDonDTO hoaDon = (HoaDonDTO) reportInstance;
            title = "Hóa ĐƠn";
            NhanVienDTO nhanVien = nhanVienBUS.findByID(hoaDon.getMaNV());
            employeeDetail = nhanVien.getHoTen() + " - SĐT: " + nhanVien.getSDT();
            IKhachHangBUS khachHangBUS = new KhachHangBUS();
            KhachHangDTO khachHang = khachHangBUS.findByID(hoaDon.getMaKH());
            cooperatorLabel = "Khách hàng";
            cooperatorDetail = khachHang.getHoTen() + " - SĐT: " + khachHang.getSDT();
            timestamp = String.valueOf(hoaDon.getNgayLap());
            totalPrice = String.valueOf(hoaDon.getTongTien());
            salePrice = String.valueOf(hoaDon.getTienKhuyenMai());
            payment = String.valueOf(hoaDon.getTienThanhToan());
        }
        else if (reportInstance instanceof PhieuNhapDTO) {
            PhieuNhapDTO phieuNhap = (PhieuNhapDTO) reportInstance;
            title = "Phiếu Nhập";
            NhanVienDTO nhanVien = nhanVienBUS.findByID(phieuNhap.getMaNV());
            employeeDetail = nhanVien.getHoTen() + " - SĐT: " + nhanVien.getSDT();
            INhaCungCapBUS nhaCungCapBUS = new NhaCungCapBUS();
            NhaCungCapDTO nhaCungCap = nhaCungCapBUS.findByID(phieuNhap.getMaNCC());
            cooperatorLabel = "Nhà cung cấp";
            cooperatorDetail = nhaCungCap.getTenNCC() + " - SĐT: " + nhaCungCap.getSDT();
            timestamp = String.valueOf(phieuNhap.getNgayTao());
            totalPrice = String.valueOf(phieuNhap.getTongTien());
        }
        else
            return false;


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
            content.setFont(fontBold, 20);
            content.newLineAtOffset(270, 690);
            content.showText(title);
            content.endText();

            content.beginText();
            content.setFont(font, 14);
            content.setLeading(20f);
            content.newLineAtOffset(60, 610);
            content.showText(cooperatorLabel + ": " + cooperatorDetail);
            content.newLine();
            content.showText("Nhân viên: " + employeeDetail);
            content.newLine();
            content.showText("Ngày lập: " + timestamp);
            content.newLine();
            content.endText();

            content.beginText();
            content.setFont(fontBold, 16);
            content.newLineAtOffset(80, 540);
            content.showText("Mã");
            content.endText();

            content.beginText();
            content.setFont(fontBold, 16);
            content.newLineAtOffset(200, 540);
            content.showText("Sản phẩm");
            content.endText();

            content.beginText();
            content.setFont(fontBold, 16);
            content.newLineAtOffset(310, 540);
            content.showText("Số lượng");
            content.endText();

            content.beginText();
            content.setFont(fontBold, 16);
            content.newLineAtOffset(410, 540);
            content.showText("Đơn giá");
            content.endText();

            content.beginText();
            content.setFont(fontBold, 16);
            content.newLineAtOffset(510, 540);
            content.showText("Tổng");
            content.endText();


            content.beginText();
            content.setFont(font, 12);
            content.setLeading(20f);
            content.newLineAtOffset(80, 520);
            for (IDetailEntity entity:list) {
                content.showText(String.valueOf(entity.getForeignID()));
                content.newLine();
            }
            content.endText();

            content.beginText();
            content.setFont(font, 12);
            content.setLeading(20f);
            content.newLineAtOffset(200, 520);
            for (int i = 0; i < list.size(); i++) {
                content.showText(productList.get(i).getTenSP());
                content.newLine();
            }
            content.endText();

            content.beginText();
            content.setFont(font, 12);
            content.setLeading(20f);
            content.newLineAtOffset(310, 520);
            for (int i = 0; i < list.size(); i++) {
                content.showText(list.get(i).getSoLuong() + " " + productList.get(i).getDonVi());
                content.newLine();
            }
            content.endText();

            content.beginText();
            content.setFont(font, 12);
            content.setLeading(20f);
            content.newLineAtOffset(410, 520);
            for (int i = 0; i < list.size(); i++) {
                content.showText(list.get(i).getDonGia() + "/" + productList.get(i).getDonVi());
                content.newLine();
            }
            content.endText();

            content.beginText();
            content.setFont(font, 12);
            content.setLeading(20f);
            content.newLineAtOffset(510, 520);
            for (IDetailEntity entity : list) {
                content.showText(String.valueOf(entity.getDonGia() * entity.getSoLuong()));
                content.newLine();
            }
            content.endText();

            content.beginText();
            content.setFont(fontBold, 16);
            content.newLineAtOffset(310, (500-(20*list.size())));
            content.showText("Tổng tiền: " + totalPrice + " đồng");
            content.newLine();
            if (salePrice != null) {
                content.showText("Giảm giá: " + salePrice + " đồng");
                content.newLine();
            }
            if (payment != null) {
                content.showText("Thành tiền: " + payment + " đồng");
                content.endText();
            }

            content.close();
            document.save(path);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
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
}

package DAO.Mapper;

import DTO.TaiKhoanDTO;
import DAO.Mapper.Interfaces.IExcelRowMapper;
import DAO.Mapper.Interfaces.IRowMapper;
import org.apache.poi.ss.usermodel.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class TaiKhoanMapper implements IRowMapper<TaiKhoanDTO>, IExcelRowMapper<TaiKhoanDTO> {
    @Override
    public TaiKhoanDTO mapRow(ResultSet resultSet) {
        try {
            TaiKhoanDTO taiKhoan = new TaiKhoanDTO();
            taiKhoan.setMaTK(resultSet.getInt("MaTK"));
            taiKhoan.setTenDangNhap(resultSet.getString("TenDangNhap"));
            taiKhoan.setMatKhau(resultSet.getString("MatKhau"));
            taiKhoan.setNgayTao(resultSet.getTimestamp("NgayTao"));
            taiKhoan.setNguoiTao(resultSet.getInt("NguoiTao"));
            taiKhoan.setChucVu(resultSet.getString("ChucVu"));
            taiKhoan.setTinhTrang(resultSet.getInt("TinhTrang"));
            return taiKhoan;
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public void mapExcelHeader(Sheet sheet, int rowIndex) {
        // Create font
        Font font = sheet.getWorkbook().createFont();
        font.setFontName("Times New Roman");
        font.setBold(true);
        font.setFontHeightInPoints((short) 14); // font size
        font.setColor(IndexedColors.WHITE.getIndex()); // text color

        // Create CellStyle
        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        cellStyle.setFont(font);
        cellStyle.setFillForegroundColor(IndexedColors.BLUE.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setBorderBottom(BorderStyle.THIN);

        // Create row
        Row row = sheet.createRow(rowIndex);

        // Create cells
        Cell cell = row.createCell(0);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Ma TK");

        cell = row.createCell(1);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Ten Dang Nhap");

        cell = row.createCell(2);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Mat Khau");

        cell = row.createCell(3);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Ngay Tao");

        cell = row.createCell(4);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Nguoi Tao");

        cell = row.createCell(5);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Chuc Vu");

        cell = row.createCell(6);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Tinh Trang");
    }

    @Override
    public void mapExcelBody(TaiKhoanDTO dto, Row row) {
        Cell cell = row.createCell(0);
        cell.setCellValue(dto.getMaTK() != null ? dto.getMaTK() : 0);

        cell = row.createCell(1);
        cell.setCellValue(dto.getTenDangNhap() != null ? dto.getTenDangNhap() : "null");

        cell = row.createCell(2);
        cell.setCellValue(dto.getMatKhau() != null ? dto.getMatKhau() : "null");

        cell = row.createCell(3);
        cell.setCellValue(String.valueOf(dto.getNgayTao() != null ? dto.getNgayTao() : "null"));

        cell = row.createCell(4);
        cell.setCellValue(dto.getNguoiTao() != null ? dto.getNguoiTao() : 0);

        cell = row.createCell(5);
        cell.setCellValue(dto.getChucVu() != null ? dto.getChucVu() : "null");

        cell = row.createCell(6);
        cell.setCellValue(dto.getTinhTrang());
    }

    @Override
    public TaiKhoanDTO mapExcelToDto(TaiKhoanDTO dto, int columnIndex, String cellValue) {
        if (dto == null)
            dto = new TaiKhoanDTO();
        if (cellValue != null && !cellValue.equalsIgnoreCase("null")) {
            switch (columnIndex) {
                case 0:
                    dto.setMaTK(Integer.parseInt(cellValue));
                    break;
                case 1:
                    dto.setTenDangNhap(cellValue);
                    break;
                case 2:
                    dto.setMatKhau(cellValue);
                    break;
                case 3:
                    dto.setNgayTao(Timestamp.valueOf(cellValue));
                    break;
                case 4:
                    dto.setNguoiTao(Integer.parseInt(cellValue));
                    break;
                case 5:
                    dto.setChucVu(cellValue);
                    break;
                case 6:
                    dto.setTinhTrang(Integer.parseInt(cellValue));
                    break;
                default:
                    break;
            }
        }
        return dto;
    }
}

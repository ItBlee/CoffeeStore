package com.itblee.mapper;

import com.itblee.dto.NhanVien;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NhanVienMapper implements RowMapper<NhanVien>, ExcelRowMapper<NhanVien> {
    @Override
    public NhanVien mapRow(ResultSet resultSet) {
        try {
            NhanVien nhanVien = new NhanVien();
            nhanVien.setMaNV(resultSet.getInt("MaNV"));
            nhanVien.setMaTK(resultSet.getInt("MaTK"));
            nhanVien.setHo(resultSet.getString("Ho"));
            nhanVien.setTen(resultSet.getString("Ten"));
            nhanVien.setNgaySinh(resultSet.getDate("NgaySinh"));
            nhanVien.setSdt(resultSet.getString("SDT"));
            nhanVien.setEmail(resultSet.getString("Email"));
            nhanVien.setGioiTinh(resultSet.getInt("GioiTinh"));
            nhanVien.setLuong(resultSet.getInt("Luong"));
            nhanVien.setTinhTrang(resultSet.getInt("TinhTrang"));
            return nhanVien;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void mapExcelHeader(CellStyle cellStyle, Row row) {
        Cell cell = row.createCell(0);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Mã NV");

        cell = row.createCell(1);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Mã TK");

        cell = row.createCell(2);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Họ");

        cell = row.createCell(3);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Tên");

        cell = row.createCell(4);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Ngày sinh");

        cell = row.createCell(5);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Số Đt");

        cell = row.createCell(6);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Email");

        cell = row.createCell(7);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Giới tính");

        cell = row.createCell(8);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Lương");

        cell = row.createCell(9);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Tình trạng");
    }

    @Override
    public void mapExcelBody(NhanVien dto, Row row) {
        Cell cell;
        cell = row.createCell(0);
        cell.setCellValue(dto.getMaNV() != null ? "NV" + dto.getMaNV() : "null");

        cell = row.createCell(1);
        cell.setCellValue(dto.getMaTK() != null ? "TK" + dto.getMaTK() : "null");

        cell = row.createCell(2);
        cell.setCellValue(dto.getHo() != null ? dto.getHo() : "null");

        cell = row.createCell(3);
        cell.setCellValue(dto.getTen() != null ? dto.getTen() : "null");

        cell = row.createCell(4);
        Format formatter = new SimpleDateFormat("dd-MM-yyyy");
        cell.setCellValue(dto.getNgaySinh() != null ? formatter.format(dto.getNgaySinh()) : "null");

        cell = row.createCell(5);
        cell.setCellValue(dto.getSdt() != null ? dto.getSdt() : "null");

        cell = row.createCell(6);
        cell.setCellValue(dto.getEmail() != null ? dto.getEmail() : "null");

        cell = row.createCell(7);
        cell.setCellValue(dto.getGioiTinh() == 1 ? "Nam" : "Nữ");

        cell = row.createCell(8);
        cell.setCellValue(dto.getLuong() != null ? String.valueOf(dto.getLuong()) : "null");

        cell = row.createCell(9);
        cell.setCellValue(dto.getTinhTrang() == 1 ? "Hoạt động" : "Vô hiệu");
    }

    @Override
    public NhanVien mapExcelToDto(NhanVien dto, int columnIndex, String cellValue) {
        if (dto == null)
            dto = new NhanVien();
        if (cellValue != null && !cellValue.equalsIgnoreCase("null")) {
            switch (columnIndex) {
                case 0:
                    dto.setMaNV(Integer.parseInt(cellValue.replace("NV", "")));
                    break;
                case 1:
                    dto.setMaTK(Integer.parseInt(cellValue.replace("TK", "")));
                    break;
                case 2:
                    dto.setHo(cellValue);
                    break;
                case 3:
                    dto.setTen(cellValue);
                    break;
                case 4:
                    try {
                        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                        Date date = dateFormat.parse(cellValue);
                        dto.setNgaySinh(new java.sql.Date(date.getTime()));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    break;
                case 5:
                    dto.setSdt(cellValue);
                    break;
                case 6:
                    dto.setEmail(cellValue);
                    break;
                case 7:
                    dto.setGioiTinh(cellValue.equalsIgnoreCase("Nam") ? 1 : 0);
                    break;
                case 8:
                    dto.setLuong(Integer.parseInt(cellValue));
                    break;
                case 9:
                    dto.setTinhTrang(cellValue.equalsIgnoreCase("Hoạt động") ? 1 : 0);
                    break;
                default:
                    break;
            }
        }
        return dto;
    }
}

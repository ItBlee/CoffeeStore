package com.itblee.mapper;

import com.itblee.dto.SanPham;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SanPhamMapper implements RowMapper<SanPham>, ExcelRowMapper<SanPham> {
    @Override
    public SanPham mapRow(ResultSet resultSet) {
        try {
            SanPham sanPham = new SanPham();
            sanPham.setMaSP(resultSet.getInt("MaSP"));
            sanPham.setMaLoai(resultSet.getInt("MaLoai"));
            sanPham.setMaNCC(resultSet.getInt("MaNCC"));
            sanPham.setTenSP(resultSet.getString("TenSP"));
            sanPham.setMoTa(resultSet.getString("MoTa"));
            sanPham.setHinhAnh(resultSet.getString("HinhAnh"));
            sanPham.setDonGia(resultSet.getInt("DonGia"));
            sanPham.setDonVi(resultSet.getString("DonVi"));
            sanPham.setSoLuong(resultSet.getInt("SoLuong"));
            sanPham.setTinhTrang(resultSet.getInt("TinhTrang"));
            return sanPham;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void mapExcelHeader(CellStyle cellStyle, Row row) {
        Cell cell = row.createCell(0);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Mã TK");
        cell = row.createCell(1);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Mã Sản Phẩm");

        cell = row.createCell(2);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Mã Loại");

        cell = row.createCell(3);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Mã Nhà Cung Cấp");

        cell = row.createCell(4);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Tên Sản Phẩm");

        cell = row.createCell(5);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Mô Tả");

        cell = row.createCell(6);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Hình Ảnh");

        cell = row.createCell(7);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Đơn Giá");
        
        cell = row.createCell(8);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Đơn Vị");   

        cell = row.createCell(9);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Số Lượng");     
        
        cell = row.createCell(10);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Tình Trạng");          
    }

    @Override
    public void mapExcelBody(SanPham dto, Row row) {
    }

    @Override
    public SanPham mapExcelToDto(SanPham dto, int columnIndex, String cellValue) {
        return null;
    }

}

package com.itblee.mapper;

import com.itblee.dto.NhaCungCap;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;

import java.sql.ResultSet;
import java.sql.SQLException;

public class NhaCungCapMapper implements RowMapper<NhaCungCap>, ExcelRowMapper<NhaCungCap> {
    @Override
    public NhaCungCap mapRow(ResultSet resultSet) {
        try {
            NhaCungCap dto = new NhaCungCap();
            dto.setMaNCC(resultSet.getInt("MaNCC"));
            dto.setTenNCC(resultSet.getString("TenNCC"));
            dto.setSdt(resultSet.getString("SDT"));
            dto.setDiaChi(resultSet.getString("DiaChi"));
            dto.setSoTaiKhoan(resultSet.getString("SoTaiKhoan"));
            dto.setTinhTrang(resultSet.getInt("TinhTrang"));
            return dto;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void mapExcelHeader(CellStyle cellStyle, Row row) {

    }

    @Override
    public void mapExcelBody(NhaCungCap dto, Row row) {

    }

    @Override
    public NhaCungCap mapExcelToDto(NhaCungCap dto, int columnIndex, String cellValue) {
        return null;
    }
}

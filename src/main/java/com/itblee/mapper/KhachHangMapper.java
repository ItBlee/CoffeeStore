package com.itblee.mapper;

import com.itblee.dto.KhachHang;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;

import java.sql.ResultSet;
import java.sql.SQLException;

public class KhachHangMapper implements RowMapper<KhachHang>, ExcelRowMapper<KhachHang> {
    @Override
    public KhachHang mapRow(ResultSet resultSet) {
        try {
            KhachHang khachHang = new KhachHang();
            khachHang.setMaKH(resultSet.getInt("MaKH"));
            khachHang.setHo(resultSet.getString("Ho"));
            khachHang.setTen(resultSet.getString("Ten"));
            khachHang.setSdt(resultSet.getString("SDT"));
            khachHang.setEmail(resultSet.getString("Email"));
            khachHang.setDiaChi(resultSet.getString("DiaChi"));
            khachHang.setTinhTrang(resultSet.getInt("TinhTrang"));
            return khachHang;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void mapExcelHeader(CellStyle cellStyle, Row row) {

    }

    @Override
    public void mapExcelBody(KhachHang dto, Row row) {

    }

    @Override
    public KhachHang mapExcelToDto(KhachHang dto, int columnIndex, String cellValue) {
        return null;
    }
}

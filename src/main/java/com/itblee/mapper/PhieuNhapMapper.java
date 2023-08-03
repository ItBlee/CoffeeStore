package com.itblee.mapper;

import com.itblee.dto.PhieuNhap;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PhieuNhapMapper implements RowMapper<PhieuNhap>, ExcelRowMapper<PhieuNhap> {
    @Override
    public PhieuNhap mapRow(ResultSet resultSet) {
        try {
            PhieuNhap phieuNhap = new PhieuNhap();
            phieuNhap.setMaPN(resultSet.getInt("MaPN"));
            phieuNhap.setMaNCC(resultSet.getInt("MaNCC"));
            phieuNhap.setMaNV(resultSet.getInt("MaNV"));
            phieuNhap.setNgayTao(resultSet.getTimestamp("NgayTao"));
            phieuNhap.setTongTien(resultSet.getInt("TongTien"));
            phieuNhap.setTinhTrang(resultSet.getInt("TinhTrang"));
            return phieuNhap;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void mapExcelHeader(CellStyle cellStyle, Row row) {

    }

    @Override
    public void mapExcelBody(PhieuNhap dto, Row row) {

    }

    @Override
    public PhieuNhap mapExcelToDto(PhieuNhap dto, int columnIndex, String cellValue) {
        return null;
    }
}

package com.itblee.mapper;

import com.itblee.dto.CT_PhieuNhap;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CT_PhieuNhapMapper implements RowMapper<CT_PhieuNhap>, ExcelRowMapper<CT_PhieuNhap> {
    @Override
    public CT_PhieuNhap mapRow(ResultSet resultSet) {
        try {
            CT_PhieuNhap ctPhieuNhap = new CT_PhieuNhap();
            ctPhieuNhap.setMaCTPN(resultSet.getInt("MaCTPN"));
            ctPhieuNhap.setMaPN(resultSet.getInt("MaPN"));
            ctPhieuNhap.setMaSP(resultSet.getInt("MaSP"));
            ctPhieuNhap.setSoLuong(resultSet.getInt("SoLuong"));
            ctPhieuNhap.setDonGia(resultSet.getInt("DonGia"));
            ctPhieuNhap.setThanhTien(resultSet.getInt("ThanhTien"));
            return ctPhieuNhap;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void mapExcelHeader(CellStyle cellStyle, Row row) {

    }

    @Override
    public void mapExcelBody(CT_PhieuNhap dto, Row row) {

    }

    @Override
    public CT_PhieuNhap mapExcelToDto(CT_PhieuNhap dto, int columnIndex, String cellValue) {
        return null;
    }
}

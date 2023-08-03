package com.itblee.mapper;

import com.itblee.dto.CT_KhuyenMai;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CT_KhuyenMaiMapper implements RowMapper<CT_KhuyenMai>, ExcelRowMapper<CT_KhuyenMai> {
    @Override
    public CT_KhuyenMai mapRow(ResultSet resultSet) {
        try {
            CT_KhuyenMai ctKhuyenMai = new CT_KhuyenMai();
            ctKhuyenMai.setMaCTKM(resultSet.getInt("MaCTKM"));
            ctKhuyenMai.setMaKM(resultSet.getInt("MaKM"));
            ctKhuyenMai.setMaSP(resultSet.getInt("MaSP"));
            ctKhuyenMai.setGiamGia(resultSet.getInt("GiamGia"));
            return ctKhuyenMai;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void mapExcelHeader(CellStyle cellStyle, Row row) {

    }

    @Override
    public void mapExcelBody(CT_KhuyenMai dto, Row row) {

    }

    @Override
    public CT_KhuyenMai mapExcelToDto(CT_KhuyenMai dto, int columnIndex, String cellValue) {
        return null;
    }
}

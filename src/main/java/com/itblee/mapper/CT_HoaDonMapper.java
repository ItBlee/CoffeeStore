package com.itblee.mapper;

import com.itblee.dto.CT_HoaDon;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CT_HoaDonMapper implements RowMapper<CT_HoaDon>, ExcelRowMapper<CT_HoaDon> {
    @Override
    public CT_HoaDon mapRow(ResultSet resultSet) {
        try {
            CT_HoaDon ctHoaDon = new CT_HoaDon();
            ctHoaDon.setMaCTHD(resultSet.getInt("MaCTHD"));
            ctHoaDon.setMaHD(resultSet.getInt("MaHD"));
            ctHoaDon.setMaSP(resultSet.getInt("MaSP"));
            ctHoaDon.setSoLuong(resultSet.getInt("SoLuong"));
            ctHoaDon.setDonGia(resultSet.getInt("DonGia"));
            ctHoaDon.setTienKhuyenMai(resultSet.getInt("TienKhuyenMai"));
            ctHoaDon.setThanhTien(resultSet.getInt("ThanhTien"));
            return ctHoaDon;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void mapExcelHeader(CellStyle cellStyle, Row row) {

    }

    @Override
    public void mapExcelBody(CT_HoaDon dto, Row row) {

    }

    @Override
    public CT_HoaDon mapExcelToDto(CT_HoaDon dto, int columnIndex, String cellValue) {
        return null;
    }
}

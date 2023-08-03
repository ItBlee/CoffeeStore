package com.itblee.mapper;

import com.itblee.dto.HoaDon;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;

import java.sql.ResultSet;
import java.sql.SQLException;

public class HoaDonMapper implements RowMapper<HoaDon>, ExcelRowMapper<HoaDon> {
    @Override
    public HoaDon mapRow(ResultSet resultSet) {
        try {
            HoaDon hoaDon = new HoaDon();
            hoaDon.setMaHD(resultSet.getInt("MaHD"));
            hoaDon.setMaKH(resultSet.getInt("MaKH"));
            hoaDon.setMaNV(resultSet.getInt("MaNV"));
            hoaDon.setNgayLap(resultSet.getTimestamp("NgayLap"));
            hoaDon.setTongTien(resultSet.getInt("TongTien"));
            hoaDon.setTienKhuyenMai(resultSet.getInt("TienKhuyenMai"));
            hoaDon.setTienThanhToan(resultSet.getInt("TienThanhToan"));
            hoaDon.setTinhTrang(resultSet.getInt("TinhTrang"));
            return hoaDon;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void mapExcelHeader(CellStyle cellStyle, Row row) {

    }

    @Override
    public void mapExcelBody(HoaDon dto, Row row) {

    }

    @Override
    public HoaDon mapExcelToDto(HoaDon dto, int columnIndex, String cellValue) {
        return null;
    }
}

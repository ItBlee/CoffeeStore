package com.itblee.mapper;

import com.itblee.dto.KhuyenMai;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;

import java.sql.ResultSet;
import java.sql.SQLException;

public class KhuyenMaiMapper implements RowMapper<KhuyenMai>, ExcelRowMapper<KhuyenMai> {
    @Override
    public KhuyenMai mapRow(ResultSet resultSet) {
        try {
            KhuyenMai khuyenMai = new KhuyenMai();
            khuyenMai.setMaKM(resultSet.getInt("MaKM"));
            khuyenMai.setTieuDe(resultSet.getString("TieuDe"));
            khuyenMai.setNoiDung(resultSet.getString("NoiDung"));
            khuyenMai.setNgayBD(resultSet.getTimestamp("NgayBD"));
            khuyenMai.setNgayKT(resultSet.getTimestamp("NgayKT"));
            khuyenMai.setTinhTrang(resultSet.getInt("TinhTrang"));
            return khuyenMai;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void mapExcelHeader(CellStyle cellStyle, Row row) {

    }

    @Override
    public void mapExcelBody(KhuyenMai dto, Row row) {

    }

    @Override
    public KhuyenMai mapExcelToDto(KhuyenMai dto, int columnIndex, String cellValue) {
        return null;
    }
}

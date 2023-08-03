package com.itblee.mapper;

import com.itblee.dto.LoaiSP;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LoaiSPMapper implements RowMapper<LoaiSP>, ExcelRowMapper<LoaiSP> {
    @Override
    public LoaiSP mapRow(ResultSet resultSet) {
        try {
            LoaiSP loaiSP = new LoaiSP();
            loaiSP.setMaLoai(resultSet.getInt("MaLoai"));
            loaiSP.setTenLoai(resultSet.getString("TenLoai"));
            loaiSP.setMoTa(resultSet.getString("MoTa"));
            return loaiSP;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void mapExcelHeader(CellStyle cellStyle, Row row) {

    }

    @Override
    public void mapExcelBody(LoaiSP dto, Row row) {

    }

    @Override
    public LoaiSP mapExcelToDto(LoaiSP dto, int columnIndex, String cellValue) {
        return null;
    }
}

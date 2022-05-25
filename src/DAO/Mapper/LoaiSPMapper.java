package DAO.Mapper;

import DAO.Mapper.Interfaces.IExcelRowMapper;
import DAO.Mapper.Interfaces.IRowMapper;
import DTO.LoaiSPDTO;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LoaiSPMapper implements IRowMapper<LoaiSPDTO>, IExcelRowMapper<LoaiSPDTO> {
    @Override
    public LoaiSPDTO mapRow(ResultSet resultSet) {
        try {
            LoaiSPDTO loaiSP = new LoaiSPDTO();
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
    public void mapExcelBody(LoaiSPDTO dto, Row row) {

    }

    @Override
    public LoaiSPDTO mapExcelToDto(LoaiSPDTO dto, int columnIndex, String cellValue) {
        return null;
    }
}

package DAO.Mapper;

import DAO.Mapper.Interfaces.IExcelRowMapper;
import DAO.Mapper.Interfaces.IRowMapper;
import DTO.CT_KhuyenMaiDTO;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CT_KhuyenMaiMapper implements IRowMapper<CT_KhuyenMaiDTO>, IExcelRowMapper<CT_KhuyenMaiDTO> {
    @Override
    public CT_KhuyenMaiDTO mapRow(ResultSet resultSet) {
        try {
            CT_KhuyenMaiDTO ctKhuyenMai = new CT_KhuyenMaiDTO();
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
    public void mapExcelBody(CT_KhuyenMaiDTO dto, Row row) {

    }

    @Override
    public CT_KhuyenMaiDTO mapExcelToDto(CT_KhuyenMaiDTO dto, int columnIndex, String cellValue) {
        return null;
    }
}

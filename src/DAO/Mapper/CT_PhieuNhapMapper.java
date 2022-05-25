package DAO.Mapper;

import DAO.Mapper.Interfaces.IExcelRowMapper;
import DAO.Mapper.Interfaces.IRowMapper;
import DTO.CT_PhieuNhapDTO;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CT_PhieuNhapMapper implements IRowMapper<CT_PhieuNhapDTO>, IExcelRowMapper<CT_PhieuNhapDTO> {
    @Override
    public CT_PhieuNhapDTO mapRow(ResultSet resultSet) {
        try {
            CT_PhieuNhapDTO ctPhieuNhap = new CT_PhieuNhapDTO();
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
    public void mapExcelBody(CT_PhieuNhapDTO dto, Row row) {

    }

    @Override
    public CT_PhieuNhapDTO mapExcelToDto(CT_PhieuNhapDTO dto, int columnIndex, String cellValue) {
        return null;
    }
}

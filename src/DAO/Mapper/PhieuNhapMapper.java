package DAO.Mapper;

import DAO.Mapper.Interfaces.IExcelRowMapper;
import DAO.Mapper.Interfaces.IRowMapper;
import DTO.PhieuNhapDTO;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PhieuNhapMapper implements IRowMapper<PhieuNhapDTO>, IExcelRowMapper<PhieuNhapDTO> {
    @Override
    public PhieuNhapDTO mapRow(ResultSet resultSet) {
        try {
            PhieuNhapDTO phieuNhap = new PhieuNhapDTO();
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
    public void mapExcelBody(PhieuNhapDTO dto, Row row) {

    }

    @Override
    public PhieuNhapDTO mapExcelToDto(PhieuNhapDTO dto, int columnIndex, String cellValue) {
        return null;
    }
}

package DAO.Mapper;

import DAO.Mapper.Interfaces.IExcelRowMapper;
import DAO.Mapper.Interfaces.IRowMapper;
import DTO.CT_HoaDonDTO;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CT_HoaDonMapper implements IRowMapper<CT_HoaDonDTO>, IExcelRowMapper<CT_HoaDonDTO> {
    @Override
    public CT_HoaDonDTO mapRow(ResultSet resultSet) {
        try {
            CT_HoaDonDTO ctHoaDon = new CT_HoaDonDTO();
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
    public void mapExcelBody(CT_HoaDonDTO dto, Row row) {

    }

    @Override
    public CT_HoaDonDTO mapExcelToDto(CT_HoaDonDTO dto, int columnIndex, String cellValue) {
        return null;
    }
}

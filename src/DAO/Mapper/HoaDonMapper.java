package DAO.Mapper;

import DAO.Mapper.Interfaces.IExcelRowMapper;
import DAO.Mapper.Interfaces.IRowMapper;
import DTO.HoaDonDTO;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;

import java.sql.ResultSet;
import java.sql.SQLException;

public class HoaDonMapper implements IRowMapper<HoaDonDTO>, IExcelRowMapper<HoaDonDTO> {
    @Override
    public HoaDonDTO mapRow(ResultSet resultSet) {
        try {
            HoaDonDTO hoaDon = new HoaDonDTO();
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
    public void mapExcelBody(HoaDonDTO dto, Row row) {

    }

    @Override
    public HoaDonDTO mapExcelToDto(HoaDonDTO dto, int columnIndex, String cellValue) {
        return null;
    }
}

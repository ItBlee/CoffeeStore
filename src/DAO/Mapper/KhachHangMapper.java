package DAO.Mapper;

import DAO.Mapper.Interfaces.IExcelRowMapper;
import DAO.Mapper.Interfaces.IRowMapper;
import DTO.KhachHangDTO;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;

import java.sql.ResultSet;
import java.sql.SQLException;

public class KhachHangMapper implements IRowMapper<KhachHangDTO>, IExcelRowMapper<KhachHangDTO> {
    @Override
    public KhachHangDTO mapRow(ResultSet resultSet) {
        try {
            KhachHangDTO khachHang = new KhachHangDTO();
            khachHang.setMaKH(resultSet.getInt(""));
            khachHang.setHo(resultSet.getString("Ho"));
            khachHang.setTen(resultSet.getString("Ten"));
            khachHang.setSDT(resultSet.getString("SDT"));
            khachHang.setEmail(resultSet.getString("Email"));
            khachHang.setDiaChi(resultSet.getString("DiaChi"));
            khachHang.setTinhTrang(resultSet.getInt("TinhTrang"));
            return khachHang;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void mapExcelHeader(CellStyle cellStyle, Row row) {

    }

    @Override
    public void mapExcelBody(KhachHangDTO dto, Row row) {

    }

    @Override
    public KhachHangDTO mapExcelToDto(KhachHangDTO dto, int columnIndex, String cellValue) {
        return null;
    }
}

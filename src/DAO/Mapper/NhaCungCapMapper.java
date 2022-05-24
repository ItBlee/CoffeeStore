package DAO.Mapper;

import DAO.Mapper.Interfaces.IExcelRowMapper;
import DAO.Mapper.Interfaces.IRowMapper;
import DTO.NhaCungCapDTO;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;

import java.sql.ResultSet;
import java.sql.SQLException;

public class NhaCungCapMapper implements IRowMapper<NhaCungCapDTO>, IExcelRowMapper<NhaCungCapDTO> {
    @Override
    public NhaCungCapDTO mapRow(ResultSet resultSet) {
        try {
            NhaCungCapDTO dto = new NhaCungCapDTO();
            dto.setMaNCC(resultSet.getInt("MaNCC"));
            dto.setTenNCC(resultSet.getString("TenNCC"));
            dto.setSDT(resultSet.getString("SDT"));
            dto.setDiaChi(resultSet.getString("DiaChi"));
            dto.setSoTaiKhoan(resultSet.getString("SoTaiKhoan"));
            dto.setTinhTrang(resultSet.getInt("TinhTrang"));
            return dto;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void mapExcelHeader(CellStyle cellStyle, Row row) {

    }

    @Override
    public void mapExcelBody(NhaCungCapDTO dto, Row row) {

    }

    @Override
    public NhaCungCapDTO mapExcelToDto(NhaCungCapDTO dto, int columnIndex, String cellValue) {
        return null;
    }
}

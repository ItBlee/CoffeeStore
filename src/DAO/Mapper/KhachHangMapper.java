package DAO.Mapper;

import DAO.Mapper.Interfaces.IExcelRowMapper;
import DAO.Mapper.Interfaces.IRowMapper;
import DTO.KhachHangDTO;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;

import java.sql.ResultSet;

public class KhachHangMapper implements IRowMapper<KhachHangDTO>, IExcelRowMapper<KhachHangDTO> {
    @Override
    public KhachHangDTO mapRow(ResultSet resultSet) {
        return null;
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

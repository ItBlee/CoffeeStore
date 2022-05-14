package DAO.Mapper;

import DAO.Mapper.Interfaces.IExcelRowMapper;
import DAO.Mapper.Interfaces.IRowMapper;
import DTO.KhuyenMaiDTO;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;

import java.sql.ResultSet;

public class KhuyenMaiMapper implements IRowMapper<KhuyenMaiDTO>, IExcelRowMapper<KhuyenMaiDTO> {
    @Override
    public void mapExcelHeader(CellStyle cellStyle, Row row) {

    }

    @Override
    public void mapExcelBody(KhuyenMaiDTO dto, Row row) {

    }

    @Override
    public KhuyenMaiDTO mapExcelToDto(KhuyenMaiDTO dto, int columnIndex, String cellValue) {
        return null;
    }

    @Override
    public KhuyenMaiDTO mapRow(ResultSet resultSet) {
        return null;
    }
}

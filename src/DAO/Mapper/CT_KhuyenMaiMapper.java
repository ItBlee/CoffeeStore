package DAO.Mapper;

import DAO.Mapper.Interfaces.IExcelRowMapper;
import DAO.Mapper.Interfaces.IRowMapper;
import DTO.CT_KhuyenMaiDTO;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;

import java.sql.ResultSet;

public class CT_KhuyenMaiMapper implements IRowMapper<CT_KhuyenMaiDTO>, IExcelRowMapper<CT_KhuyenMaiDTO> {
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

    @Override
    public CT_KhuyenMaiDTO mapRow(ResultSet resultSet) {
        return null;
    }
}

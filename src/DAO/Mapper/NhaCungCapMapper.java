package DAO.Mapper;

import DAO.Mapper.Interfaces.IExcelRowMapper;
import DAO.Mapper.Interfaces.IRowMapper;
import DTO.NhaCungCapDTO;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;

import java.sql.ResultSet;

public class NhaCungCapMapper implements IRowMapper<NhaCungCapDTO>, IExcelRowMapper<NhaCungCapDTO> {
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

    @Override
    public NhaCungCapDTO mapRow(ResultSet resultSet) {
        return null;
    }
}

package DAO.Mapper;

import DAO.Mapper.Interfaces.IExcelRowMapper;
import DAO.Mapper.Interfaces.IRowMapper;
import DTO.LoaiSPDTO;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;

import java.sql.ResultSet;

public class LoaiSPMapper implements IRowMapper<LoaiSPDTO>, IExcelRowMapper<LoaiSPDTO> {
    @Override
    public void mapExcelHeader(CellStyle cellStyle, Row row) {

    }

    @Override
    public void mapExcelBody(LoaiSPDTO dto, Row row) {

    }

    @Override
    public LoaiSPDTO mapExcelToDto(LoaiSPDTO dto, int columnIndex, String cellValue) {
        return null;
    }

    @Override
    public LoaiSPDTO mapRow(ResultSet resultSet) {
        return null;
    }
}

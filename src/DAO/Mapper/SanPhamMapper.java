package DAO.Mapper;

import DAO.Mapper.Interfaces.IExcelRowMapper;
import DAO.Mapper.Interfaces.IRowMapper;
import DTO.SanPhamDTO;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;

import java.sql.ResultSet;

public class SanPhamMapper implements IRowMapper<SanPhamDTO>, IExcelRowMapper<SanPhamDTO> {
    @Override
    public void mapExcelHeader(CellStyle cellStyle, Row row) {

    }

    @Override
    public void mapExcelBody(SanPhamDTO dto, Row row) {

    }

    @Override
    public SanPhamDTO mapExcelToDto(SanPhamDTO dto, int columnIndex, String cellValue) {
        return null;
    }

    @Override
    public SanPhamDTO mapRow(ResultSet resultSet) {
        return null;
    }
}

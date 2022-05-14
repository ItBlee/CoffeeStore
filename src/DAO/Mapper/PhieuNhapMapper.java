package DAO.Mapper;

import DAO.Mapper.Interfaces.IExcelRowMapper;
import DAO.Mapper.Interfaces.IRowMapper;
import DTO.PhieuNhapDTO;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;

import java.sql.ResultSet;

public class PhieuNhapMapper implements IRowMapper<PhieuNhapDTO>, IExcelRowMapper<PhieuNhapDTO> {
    @Override
    public void mapExcelHeader(CellStyle cellStyle, Row row) {

    }

    @Override
    public void mapExcelBody(PhieuNhapDTO dto, Row row) {

    }

    @Override
    public PhieuNhapDTO mapExcelToDto(PhieuNhapDTO dto, int columnIndex, String cellValue) {
        return null;
    }

    @Override
    public PhieuNhapDTO mapRow(ResultSet resultSet) {
        return null;
    }
}

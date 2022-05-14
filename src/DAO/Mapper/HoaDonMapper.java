package DAO.Mapper;

import DAO.Mapper.Interfaces.IExcelRowMapper;
import DAO.Mapper.Interfaces.IRowMapper;
import DTO.HoaDonDTO;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;

import java.sql.ResultSet;

public class HoaDonMapper implements IRowMapper<HoaDonDTO>, IExcelRowMapper<HoaDonDTO> {
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

    @Override
    public HoaDonDTO mapRow(ResultSet resultSet) {
        return null;
    }
}

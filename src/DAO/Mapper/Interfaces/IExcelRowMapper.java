package DAO.Mapper.Interfaces;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;

public interface IExcelRowMapper<T> {
    void mapExcelHeader(CellStyle cellStyle, Row row);
    void mapExcelBody(T dto, Row row);
    T mapExcelToDto(T dto, int columnIndex, String cellValue);
}

package Mapper.Interfaces;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public interface IExcelRowMapper<T> {
    void mapExcelHeader(Sheet sheet, int rowIndex);
    void mapExcelBody(T dto, Row row);
    T mapExcelToDto(T dto, int columnIndex, String cellValue);
}

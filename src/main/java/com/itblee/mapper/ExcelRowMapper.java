package com.itblee.mapper;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;

public interface ExcelRowMapper<T> {
    void mapExcelHeader(CellStyle cellStyle, Row row);
    void mapExcelBody(T dto, Row row);
    T mapExcelToDto(T dto, int columnIndex, String cellValue);
}

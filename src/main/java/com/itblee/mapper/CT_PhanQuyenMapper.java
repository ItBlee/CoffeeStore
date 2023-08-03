package com.itblee.mapper;

import com.itblee.dto.CT_PhanQuyen;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CT_PhanQuyenMapper implements RowMapper<CT_PhanQuyen>, ExcelRowMapper<CT_PhanQuyen> {
    @Override
    public CT_PhanQuyen mapRow(ResultSet resultSet) {
        try {
            CT_PhanQuyen ctPhanQuyenDTO = new CT_PhanQuyen();
            ctPhanQuyenDTO.setMaCTPQ(resultSet.getInt("MaCTPQ"));
            ctPhanQuyenDTO.setQuyenDoc(resultSet.getInt("QuyenDoc"));
            ctPhanQuyenDTO.setQuyenTao(resultSet.getInt("QuyenTao"));
            ctPhanQuyenDTO.setQuyenSua(resultSet.getInt("QuyenSua"));
            ctPhanQuyenDTO.setQuyenXoa(resultSet.getInt("QuyenXoa"));
            return ctPhanQuyenDTO;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void mapExcelHeader(CellStyle cellStyle, Row row) {
        Cell cell = row.createCell(0);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Mã CTPQ");

        cell = row.createCell(1);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Quyền đọc");

        cell = row.createCell(2);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Quyền tạo");

        cell = row.createCell(3);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Quyền sửa");

        cell = row.createCell(4);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Quyền xóa");
    }

    @Override
    public void mapExcelBody(CT_PhanQuyen dto, Row row) {
        Cell cell;
        cell = row.createCell(0);
        cell.setCellValue(dto.getMaCTPQ() != null ? "MaCTPQ" + dto.getMaCTPQ() : "null");

        cell = row.createCell(1);
        cell.setCellValue(dto.getQuyenDoc() == 1 ? "Có" : "Không");

        cell = row.createCell(2);
        cell.setCellValue(dto.getQuyenTao() == 1 ? "Có" : "Không");

        cell = row.createCell(3);
        cell.setCellValue(dto.getQuyenSua() == 1 ? "Có" : "Không");

        cell = row.createCell(4);
        cell.setCellValue(dto.getQuyenXoa() == 1 ? "Có" : "Không");
    }

    @Override
    public CT_PhanQuyen mapExcelToDto(CT_PhanQuyen dto, int columnIndex, String cellValue) {
        if (dto == null)
            dto = new CT_PhanQuyen();
        if (cellValue != null && !cellValue.equalsIgnoreCase("null")) {
            switch (columnIndex) {
                case 0:
                    dto.setMaCTPQ(Integer.parseInt(cellValue.replace("MaCTPQ","")));
                    break;
                case 1:
                    dto.setQuyenDoc(cellValue.equalsIgnoreCase("Có") ? 1 : 0);
                    break;
                case 2:
                    dto.setQuyenTao(cellValue.equalsIgnoreCase("Có") ? 1 : 0);
                    break;
                case 3:
                    dto.setQuyenSua(cellValue.equalsIgnoreCase("Có") ? 1 : 0);
                    break;
                case 4:
                    dto.setQuyenXoa(cellValue.equalsIgnoreCase("Có") ? 1 : 0);
                    break;
                default:
                    break;
            }
        }
        return dto;
    }
}

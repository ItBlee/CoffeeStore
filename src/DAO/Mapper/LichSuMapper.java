package DAO.Mapper;

import DAO.Mapper.Interfaces.IExcelRowMapper;
import DAO.Mapper.Interfaces.IRowMapper;
import DTO.LichSuDTO;
import DTO.TaiKhoanDTO;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class LichSuMapper implements IRowMapper<LichSuDTO>, IExcelRowMapper<LichSuDTO> {
    @Override
    public LichSuDTO mapRow(ResultSet resultSet) {
        try {
            LichSuDTO lichSu = new LichSuDTO();
            lichSu.setMaLS(resultSet.getInt("MaLS"));
            lichSu.setTenDoiTuong(resultSet.getString("TenDoiTuong"));
            lichSu.setMaDoiTuong(resultSet.getInt("MaDoiTuong"));
            lichSu.setThoiGian(resultSet.getTimestamp("ThoiGian"));
            lichSu.setNguoiThucHien(resultSet.getInt("NguoiThucHien"));
            lichSu.setThaoTac(resultSet.getString("ThaoTac"));
            return lichSu;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void mapExcelHeader(CellStyle cellStyle, Row row) {
        Cell cell = row.createCell(0);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Mã LS");

        cell = row.createCell(1);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Tên đối tượng");

        cell = row.createCell(2);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Mã đối tượng");

        cell = row.createCell(3);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Thời điểm");

        cell = row.createCell(4);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Người thực hiện");

        cell = row.createCell(5);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Thao tác");
    }

    @Override
    public void mapExcelBody(LichSuDTO dto, Row row) {
        Cell cell;
        cell = row.createCell(0);
        cell.setCellValue(dto.getMaLS() != null ? "LS" + dto.getMaLS() : "null");

        cell = row.createCell(1);
        cell.setCellValue(dto.getTenDoiTuong() != null ? dto.getTenDoiTuong() : "null");

        cell = row.createCell(2);
        cell.setCellValue(dto.getMaDoiTuong() != null ? String.valueOf(dto.getMaDoiTuong()) : "null");

        cell = row.createCell(3);
        cell.setCellValue(dto.getThoiGian() != null ? String.valueOf(dto.getThoiGian()) : "null");

        cell = row.createCell(4);
        cell.setCellValue(dto.getNguoiThucHien() != null ? "NV" + dto.getNguoiThucHien() : "null");

        cell = row.createCell(5);
        cell.setCellValue(dto.getThaoTac() != null ? dto.getThaoTac() : "null");
    }

    @Override
    public LichSuDTO mapExcelToDto(LichSuDTO dto, int columnIndex, String cellValue) {
        if (dto == null)
            dto = new LichSuDTO();
        if (cellValue != null && !cellValue.equalsIgnoreCase("null")) {
            switch (columnIndex) {
                case 0:
                    dto.setMaLS(Integer.parseInt(cellValue.replace("LS", "")));
                    break;
                case 1:
                    dto.setTenDoiTuong(cellValue);
                    break;
                case 2:
                    dto.setMaDoiTuong(Integer.valueOf(cellValue));
                    break;
                case 3:
                    dto.setThoiGian(Timestamp.valueOf(cellValue));
                    break;
                case 4:
                    dto.setNguoiThucHien(Integer.parseInt(cellValue.replace("LS", "")));
                    break;
                case 5:
                    dto.setThaoTac(cellValue);
                    break;
                default:
                    break;
            }
        }
        return dto;
    }
}

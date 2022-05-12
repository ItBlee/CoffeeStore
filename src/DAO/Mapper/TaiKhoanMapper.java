package DAO.Mapper;

import DTO.TaiKhoanDTO;
import DAO.Mapper.Interfaces.IExcelRowMapper;
import DAO.Mapper.Interfaces.IRowMapper;
import org.apache.poi.ss.usermodel.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class TaiKhoanMapper implements IRowMapper<TaiKhoanDTO>, IExcelRowMapper<TaiKhoanDTO> {
    @Override
    public TaiKhoanDTO mapRow(ResultSet resultSet) {
        try {
            TaiKhoanDTO taiKhoan = new TaiKhoanDTO();
            taiKhoan.setMaTK(resultSet.getInt("MaTK"));
            taiKhoan.setTenDangNhap(resultSet.getString("TenDangNhap"));
            taiKhoan.setMatKhau(resultSet.getString("MatKhau"));
            taiKhoan.setNgayTao(resultSet.getTimestamp("NgayTao"));
            taiKhoan.setNguoiTao(resultSet.getInt("NguoiTao"));
            taiKhoan.setChucVu(resultSet.getString("ChucVu"));
            taiKhoan.setTinhTrang(resultSet.getInt("TinhTrang"));
            return taiKhoan;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void mapExcelHeader(CellStyle cellStyle, Row row) {
        Cell cell = row.createCell(0);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Mã TK");

        cell = row.createCell(1);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Tên đăng nhập");

        cell = row.createCell(2);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Mật khẩu");

        cell = row.createCell(3);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Ngày tạo");

        cell = row.createCell(4);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Người tạo");

        cell = row.createCell(5);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Chức vụ");

        cell = row.createCell(6);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Tình trạng");
    }

    @Override
    public void mapExcelBody(TaiKhoanDTO dto, Row row) {
        Cell cell;
        cell = row.createCell(0);
        cell.setCellValue(dto.getMaTK() != null ? "TK" + dto.getMaTK() : "null");

        cell = row.createCell(1);
        cell.setCellValue(dto.getTenDangNhap() != null ? dto.getTenDangNhap() : "null");

        cell = row.createCell(2);
        cell.setCellValue(dto.getMatKhau() != null ? dto.getMatKhau() : "null");

        cell = row.createCell(3);
        cell.setCellValue(dto.getNgayTao() != null ? String.valueOf(dto.getNgayTao()) : "null");

        cell = row.createCell(4);
        cell.setCellValue(dto.getNguoiTao() != null ? "TK" + dto.getNguoiTao() : "null");

        cell = row.createCell(5);
        cell.setCellValue(dto.getChucVu() != null ? dto.getChucVu() : "null");

        cell = row.createCell(6);
        cell.setCellValue(dto.getTinhTrang() == 1 ? "Hoạt động" : "Vô hiệu");
    }

    @Override
    public TaiKhoanDTO mapExcelToDto(TaiKhoanDTO dto, int columnIndex, String cellValue) {
        if (dto == null)
            dto = new TaiKhoanDTO();
        if (cellValue != null && !cellValue.equalsIgnoreCase("null")) {
            switch (columnIndex) {
                case 0:
                    dto.setMaTK(Integer.parseInt(cellValue.replace("TK","")));
                    break;
                case 1:
                    dto.setTenDangNhap(cellValue);
                    break;
                case 2:
                    dto.setMatKhau(cellValue);
                    break;
                case 3:
                    dto.setNgayTao(Timestamp.valueOf(cellValue));
                    break;
                case 4:
                    dto.setNguoiTao(Integer.parseInt(cellValue.replace("TK","")));
                    break;
                case 5:
                    dto.setChucVu(cellValue);
                    break;
                case 6:
                    dto.setTinhTrang(cellValue.equalsIgnoreCase("Hoạt động") ? 1 : 0);
                    break;
                default:
                    break;
            }
        }
        return dto;
    }
}

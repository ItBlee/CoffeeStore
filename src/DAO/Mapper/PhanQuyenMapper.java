package DAO.Mapper;

import DAO.Mapper.Interfaces.IExcelRowMapper;
import DAO.Mapper.Interfaces.IRowMapper;
import DTO.PhanQuyenDTO;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PhanQuyenMapper implements IRowMapper<PhanQuyenDTO>, IExcelRowMapper<PhanQuyenDTO> {
    @Override
    public PhanQuyenDTO mapRow(ResultSet resultSet) {
        try {
            PhanQuyenDTO phanQuyenDTO = new PhanQuyenDTO();
            phanQuyenDTO.setMaPQ(resultSet.getInt("MaPQ "));
            phanQuyenDTO.setTenPQ(resultSet.getString("TenPQ"));
            phanQuyenDTO.setQuyenHD(resultSet.getInt("QuyenHD"));
            phanQuyenDTO.setQuyenSP(resultSet.getInt("QuyenSP"));
            phanQuyenDTO.setQuyenPN(resultSet.getInt("QuyenPN"));
            phanQuyenDTO.setQuyenNCC(resultSet.getInt("QuyenNCC"));
            phanQuyenDTO.setQuyenKH(resultSet.getInt("QuyenKH"));
            phanQuyenDTO.setQuyenKM(resultSet.getInt("QuyenKM"));
            phanQuyenDTO.setQuyenTK(resultSet.getInt("QuyenTK"));
            phanQuyenDTO.setQuyenExcel(resultSet.getInt("QuyenExcel"));
            phanQuyenDTO.setQuyenNV(resultSet.getInt("QuyenNV"));
            return phanQuyenDTO;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void mapExcelHeader(CellStyle cellStyle, Row row) {
        Cell cell = row.createCell(0);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Mã PQ");

        cell = row.createCell(1);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Tên quyền");

        cell = row.createCell(2);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Quyền QL hóa đơn");

        cell = row.createCell(3);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Quyền QL sản phẩm");

        cell = row.createCell(4);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Quyền QL phiếu nhập");

        cell = row.createCell(5);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Quyền QL nguồn cung");

        cell = row.createCell(6);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Quyền QL khách hàng");

        cell = row.createCell(7);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Quyền QL khuyến mãi");

        cell = row.createCell(8);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Quyền thống kê");

        cell = row.createCell(9);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Quyền excel");

        cell = row.createCell(10);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Quyền QL nhân sự");
    }

    @Override
    public void mapExcelBody(PhanQuyenDTO dto, Row row) {
        Cell cell;
        cell = row.createCell(0);
        cell.setCellValue(dto.getMaPQ() != null ? "PQ" + dto.getMaPQ() : "null");

        cell = row.createCell(1);
        cell.setCellValue(dto.getTenPQ() != null ? dto.getTenPQ() : "null");

        cell = row.createCell(2);
        cell.setCellValue(dto.getQuyenHD() != null ? "CTPQ" + dto.getQuyenHD() : "null");

        cell = row.createCell(3);
        cell.setCellValue(dto.getQuyenSP() != null ? "CTPQ" + dto.getQuyenSP() : "null");

        cell = row.createCell(4);
        cell.setCellValue(dto.getQuyenPN() != null ? "CTPQ" + dto.getQuyenPN() : "null");

        cell = row.createCell(5);
        cell.setCellValue(dto.getQuyenNCC() != null ? "CTPQ" + dto.getQuyenNCC() : "null");

        cell = row.createCell(6);
        cell.setCellValue(dto.getQuyenKH() != null ? "CTPQ" + dto.getQuyenKH() : "null");

        cell = row.createCell(7);
        cell.setCellValue(dto.getQuyenKM() != null ? "CTPQ" + dto.getQuyenKM() : "null");

        cell = row.createCell(8);
        cell.setCellValue(dto.getQuyenTK() != null ? "CTPQ" + dto.getQuyenTK() : "null");

        cell = row.createCell(9);
        cell.setCellValue(dto.getQuyenExcel() != null ? "CTPQ" + dto.getQuyenExcel() : "null");

        cell = row.createCell(10);
        cell.setCellValue(dto.getQuyenNV() != null ? "CTPQ" + dto.getQuyenNV() : "null");
    }

    @Override
    public PhanQuyenDTO mapExcelToDto(PhanQuyenDTO dto, int columnIndex, String cellValue) {
        if (dto == null)
            dto = new PhanQuyenDTO();
        if (cellValue != null && !cellValue.equalsIgnoreCase("null")) {
            switch (columnIndex) {
                case 0:
                    dto.setMaPQ(Integer.parseInt(cellValue.replace("PQ", "")));
                    break;
                case 1:
                    dto.setTenPQ(cellValue);
                    break;
                case 2:
                    dto.setQuyenHD(Integer.parseInt(cellValue.replace("CTPQ", "")));
                    break;
                case 3:
                    dto.setQuyenSP(Integer.parseInt(cellValue.replace("CTPQ", "")));
                    break;
                case 4:
                    dto.setQuyenPN(Integer.parseInt(cellValue.replace("CTPQ", "")));
                    break;
                case 5:
                    dto.setQuyenNCC(Integer.parseInt(cellValue.replace("CTPQ", "")));
                    break;
                case 6:
                    dto.setQuyenKH(Integer.parseInt(cellValue.replace("CTPQ", "")));
                    break;
                case 7:
                    dto.setQuyenKM(Integer.parseInt(cellValue.replace("CTPQ", "")));
                    break;
                case 8:
                    dto.setQuyenTK(Integer.parseInt(cellValue.replace("CTPQ", "")));
                    break;
                case 9:
                    dto.setQuyenExcel(Integer.parseInt(cellValue.replace("CTPQ", "")));
                    break;
                case 10:
                    dto.setQuyenNV(Integer.parseInt(cellValue.replace("CTPQ", "")));
                    break;
                default:
                    break;
            }
        }
        return dto;
    }
}

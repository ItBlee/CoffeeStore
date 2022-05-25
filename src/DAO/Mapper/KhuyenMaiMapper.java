package DAO.Mapper;

import DAO.Mapper.Interfaces.IExcelRowMapper;
import DAO.Mapper.Interfaces.IRowMapper;
import DTO.KhuyenMaiDTO;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;

import java.sql.ResultSet;
import java.sql.SQLException;

public class KhuyenMaiMapper implements IRowMapper<KhuyenMaiDTO>, IExcelRowMapper<KhuyenMaiDTO> {
    @Override
    public KhuyenMaiDTO mapRow(ResultSet resultSet) {
        try {
            KhuyenMaiDTO khuyenMai = new KhuyenMaiDTO();
            khuyenMai.setMaKM(resultSet.getInt("MaKM"));
            khuyenMai.setTieuDe(resultSet.getString("TieuDe"));
            khuyenMai.setNoiDung(resultSet.getString("NoiDung"));
            khuyenMai.setNgayBD(resultSet.getTimestamp("NgayBD"));
            khuyenMai.setNgayKT(resultSet.getTimestamp("NgayKT"));
            khuyenMai.setTinhTrang(resultSet.getInt("TinhTrang"));
            return khuyenMai;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void mapExcelHeader(CellStyle cellStyle, Row row) {

    }

    @Override
    public void mapExcelBody(KhuyenMaiDTO dto, Row row) {

    }

    @Override
    public KhuyenMaiDTO mapExcelToDto(KhuyenMaiDTO dto, int columnIndex, String cellValue) {
        return null;
    }
}

package DAO;

import DAO.Abstract.AbstractDAO;
import DAO.Interfaces.ILichSuDAO;
import DAO.Mapper.LichSuMapper;
import DTO.LichSuDTO;

import java.util.ArrayList;

public class LichSuDAO extends AbstractDAO<LichSuDTO> implements ILichSuDAO {
    @Override
    public ArrayList<LichSuDTO> findAll() {
        String sql = "SELECT * FROM lichsu";
        return query(sql, new LichSuMapper());
    }

    @Override
    public LichSuDTO findByID(int MaLS) {
        String sql = "SELECT * FROM lichsu WHERE MaLS = ?";
        ArrayList<LichSuDTO> result = query(sql, new LichSuMapper(), MaLS);
        return result.isEmpty() ? null : result.get(0);
    }

    @Override
    public Integer save(LichSuDTO lichSu) {
        String sql = "INSERT INTO lichsu"
                + " (TenDoiTuong, MaDoiTuong, NguoiThucHien, ThaoTac)"
                + " VALUES(?, ?, ?, ?)";
        return insert(sql, lichSu.getTenDoiTuong(), lichSu.getMaDoiTuong(), lichSu.getNguoiThucHien(), lichSu.getThaoTac());
    }

    @Override
    public boolean update(LichSuDTO lichSu) {
        String sql = "UPDATE lichsu"
                + " SET TenDoiTuong = ?, MaDoiTuong = ?, NguoiThucHien = ?, ThaoTac = ?"
                + " WHERE MaLS = ?";
        return update(sql, lichSu.getTenDoiTuong(), lichSu.getMaDoiTuong(), lichSu.getNguoiThucHien(), lichSu.getThaoTac(), lichSu.getMaLS());
    }

    @Override
    public boolean delete(int MaLS) {
        String sql = "DELETE FROM lichsu"
                + " WHERE MaLS = ?";
        return update(sql, MaLS);
    }
}

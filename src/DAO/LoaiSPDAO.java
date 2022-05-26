package DAO;

import DAO.Abstract.AbstractDAO;
import DAO.Interfaces.ILoaiSPDAO;
import DAO.Mapper.LoaiSPMapper;
import DTO.LoaiSPDTO;

import java.util.ArrayList;

public class LoaiSPDAO extends AbstractDAO<LoaiSPDTO> implements ILoaiSPDAO {
    @Override
    public ArrayList<LoaiSPDTO> findAll() {
        String sql = "SELECT * FROM loaisp";
        return query(sql, new LoaiSPMapper());
    }

    @Override
    public LoaiSPDTO findByID(int MaLoai) {
        String sql = "SELECT * FROM loaisp WHERE MaLoai = ?";
        ArrayList<LoaiSPDTO> result = query(sql, new LoaiSPMapper(), MaLoai);
        return result.isEmpty() ? null : result.get(0);
    }
    @Override
    public Integer save(LoaiSPDTO loaiSP) {
        String sql = "INSERT INTO loaisp"
                + " (MaLoai, TenLoai, MoTa)"
                + " VALUES(?, ?, ?)";
        return insert(sql, loaiSP.getMaLoai(), loaiSP.getTenLoai(), loaiSP.getMoTa());
    }

    @Override
    public boolean update(LoaiSPDTO loaiSP) {
        String sql = "UPDATE loaisp"
                + " SET TenLoai = ?, MoTa = ?"
                + " WHERE MaLoai = ?";
        return update(sql, loaiSP.getTenLoai(), loaiSP.getMoTa(), loaiSP.getMaLoai());
    }

    @Override
    public boolean delete(int MaLoai) {
        String sql = "DELETE FROM loaisp"
                + " WHERE MaLoai = ?";
        return update(sql, MaLoai);
    }
}

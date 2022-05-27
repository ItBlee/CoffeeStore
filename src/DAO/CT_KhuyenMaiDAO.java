package DAO;

import DAO.Abstract.AbstractDAO;
import DAO.Interfaces.ICT_KhuyenMaiDAO;
import DAO.Mapper.CT_KhuyenMaiMapper;
import DTO.CT_KhuyenMaiDTO;

import java.util.ArrayList;

public class CT_KhuyenMaiDAO extends AbstractDAO<CT_KhuyenMaiDTO> implements ICT_KhuyenMaiDAO {
    @Override
    public ArrayList<CT_KhuyenMaiDTO> findAll() {
        String sql = "SELECT * FROM ct_khuyenmai";
        return query(sql, new CT_KhuyenMaiMapper());
    }

    @Override
    public CT_KhuyenMaiDTO findByID(int MaCTKM) {
        String sql = "SELECT * FROM ct_khuyenmai WHERE MaCTKM = ?";
        ArrayList<CT_KhuyenMaiDTO> result = query(sql, new CT_KhuyenMaiMapper(), MaCTKM);
        return result.isEmpty() ? null : result.get(0);
    }
    @Override
    public Integer save(CT_KhuyenMaiDTO ctKhuyenMai) {
        String sql = "INSERT INTO ct_khuyenmai"
                + " (MaCTKM, MaKM, MaSP, GiamGia)"
                + " VALUES(?, ?, ?, ?)";
        return insert(sql, ctKhuyenMai.getMaCTKM(), ctKhuyenMai.getMaKM(), ctKhuyenMai.getMaSP(), ctKhuyenMai.getGiamGia());
    }

    @Override
    public boolean update(CT_KhuyenMaiDTO ctKhuyenMai) {
        String sql = "UPDATE ct_khuyenmai"
                + " SET MaKM = ?, MaSP = ?, GiamGia = ?"
                + " WHERE MaCTKM = ?";
        return update(sql, ctKhuyenMai.getMaKM(), ctKhuyenMai.getMaSP(), ctKhuyenMai.getGiamGia(), ctKhuyenMai.getMaCTKM());
    }

    @Override
    public boolean delete(int MaCTKM) {
        String sql = "DELETE FROM ct_khuyenmai"
                + " WHERE MaCTKM = ?";
        return update(sql, MaCTKM);
    }
}

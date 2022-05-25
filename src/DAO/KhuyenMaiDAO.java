package DAO;

import DAO.Abstract.AbstractDAO;
import DAO.Interfaces.IKhuyenMaiDAO;
import DAO.Mapper.KhuyenMaiMapper;
import DTO.KhuyenMaiDTO;

import java.util.ArrayList;

public class KhuyenMaiDAO extends AbstractDAO<KhuyenMaiDTO> implements IKhuyenMaiDAO {
    @Override
    public ArrayList<KhuyenMaiDTO> findAll() {
        String sql = "SELECT * FROM khuyenmai";
        return query(sql, new KhuyenMaiMapper());
    }

    @Override
    public KhuyenMaiDTO findByID(int MaKM) {
        String sql = "SELECT * FROM khuyenmai WHERE MaKM = ?";
        ArrayList<KhuyenMaiDTO> result = query(sql, new KhuyenMaiMapper(), MaKM);
        return result.isEmpty() ? null : result.get(0);
    }
    @Override
    public Integer save(KhuyenMaiDTO khuyenMai) {
        String sql = "INSERT INTO khuyenmai"
                + " (MaKM, TieuDe, NoiDung, NgayBD, NgayKT, TinhTrang)"
                + " VALUES(?, ?, ?, ?, ?, ?)";
        return insert(sql, khuyenMai.getMaKM(), khuyenMai.getTieuDe(), khuyenMai.getNoiDung(),
                khuyenMai.getNgayBD(), khuyenMai.getNgayKT(), khuyenMai.getTinhTrang());
    }

    @Override
    public boolean update(KhuyenMaiDTO khuyenMai) {
        String sql = "UPDATE khuyenmai"
                + " SET TieuDe = ?, NoiDung = ?, NgayBD = ?, NgayKT = ?, TinhTrang = ?"
                + " WHERE MaKM = ?";
        return update(sql, khuyenMai.getTieuDe(), khuyenMai.getNoiDung(),
                khuyenMai.getNgayBD(), khuyenMai.getNgayKT(), khuyenMai.getTinhTrang(), khuyenMai.getMaKM());
    }

    @Override
    public boolean delete(int MaKM) {
        String sql = "DELETE FROM khuyenmai"
                + " WHERE MaKM = ?";
        return update(sql, MaKM);
    }
}

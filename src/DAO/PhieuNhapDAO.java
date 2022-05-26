package DAO;

import DAO.Abstract.AbstractDAO;
import DAO.Interfaces.IPhieuNhapDAO;
import DAO.Mapper.PhieuNhapMapper;
import DTO.PhieuNhapDTO;

import java.util.ArrayList;

public class PhieuNhapDAO extends AbstractDAO<PhieuNhapDTO> implements IPhieuNhapDAO {
    @Override
    public ArrayList<PhieuNhapDTO> findAll() {
        String sql = "SELECT * FROM phieunhap";
        return query(sql, new PhieuNhapMapper());
    }

    @Override
    public PhieuNhapDTO findByID(int MaPN) {
        String sql = "SELECT * FROM phieunhap WHERE MaPN = ?";
        ArrayList<PhieuNhapDTO> result = query(sql, new PhieuNhapMapper(), MaPN);
        return result.isEmpty() ? null : result.get(0);
    }
    @Override
    public Integer save(PhieuNhapDTO phieuNhap) {
        String sql = "INSERT INTO phieunhap"
                + " (MaPN, MaNCC, MaNV, NgayTao, TongTien, TinhTrang)"
                + " VALUES(?, ?, ?, ?, ?, ?)";
        return insert(sql, phieuNhap.getMaPN(), phieuNhap.getMaNCC(), phieuNhap.getMaNV(),
                phieuNhap.getNgayTao(), phieuNhap.getTongTien(), phieuNhap.getTinhTrang());
    }

    @Override
    public boolean update(PhieuNhapDTO phieuNhap) {
        String sql = "UPDATE phieunhap"
                + " SET MaNCC = ?, MaNV = ?, NgayTao = ?, TongTien = ?, TinhTrang = ?"
                + " WHERE MaPN = ?";
        return update(sql, phieuNhap.getMaNCC(), phieuNhap.getMaNV(), phieuNhap.getNgayTao(),
                phieuNhap.getTongTien(), phieuNhap.getTinhTrang(), phieuNhap.getMaPN());
    }

    @Override
    public boolean delete(int MaPN) {
        String sql = "DELETE FROM phieunhap"
                + " WHERE MaPN = ?";
        return update(sql, MaPN);
    }
}

package DAO;

import DAO.Abstract.AbstractDAO;
import DAO.Interfaces.IKhachHangDAO;
import DAO.Mapper.KhachHangMapper;
import DTO.KhachHangDTO;

import java.util.ArrayList;

public class KhachHangDAO extends AbstractDAO<KhachHangDTO> implements IKhachHangDAO {
    @Override
    public ArrayList<KhachHangDTO> findAll() {
        String sql = "SELECT * FROM khachhang";
        return query(sql, new KhachHangMapper());
    }

    @Override
    public KhachHangDTO findByID(int MaKH) {
        String sql = "SELECT * FROM khachhang WHERE MaKH = ?";
        ArrayList<KhachHangDTO> result = query(sql, new KhachHangMapper(), MaKH);
        return result.isEmpty() ? null : result.get(0);
    }
    @Override
    public Integer save(KhachHangDTO khachHang) {
        String sql = "INSERT INTO khachhang"
                + " (MaKH, Ho, Ten, SDT, DiaChi, Email, TinhTrang)"
                + " VALUES(?, ?, ?, ?, ?, ?, ?)";
        return insert(sql, khachHang.getMaKH(), khachHang.getHo(), khachHang.getTen(), khachHang.getSDT(),
                khachHang.getDiaChi(), khachHang.getEmail() , khachHang.getTinhTrang());
    }

    @Override
    public boolean update(KhachHangDTO khachHang) {
        String sql = "UPDATE khachhang"
                + " SET Ho = ?, Ten = ?, SDT = ?, DiaChi = ?, Email = ?, TinhTrang = ?"
                + " WHERE MaKH = ?";
        return update(sql, khachHang.getHo(), khachHang.getTen(), khachHang.getSDT(), khachHang.getDiaChi(),
                khachHang.getEmail() , khachHang.getTinhTrang(), khachHang.getMaKH());
    }

    @Override
    public boolean delete(int MaKH) {
        String sql = "DELETE FROM khachhang"
                + " WHERE MaKH = ?";
        return update(sql, MaKH);
    }
}

package DAO;

import DAO.Abstract.AbstractDAO;
import DAO.Interfaces.INhanVienDAO;
import DAO.Mapper.NhanVienMapper;
import DTO.NhanVienDTO;

import java.util.ArrayList;

public class NhanVienDAO extends AbstractDAO<NhanVienDTO> implements INhanVienDAO {
    @Override
    public ArrayList<NhanVienDTO> findAll() {
        String sql = "SELECT * FROM nhanvien";
        return query(sql, new NhanVienMapper());
    }

    @Override
    public NhanVienDTO findByID(int MaNV) {
        String sql = "SELECT * FROM nhanvien WHERE MaNV = ?";
        ArrayList<NhanVienDTO> result = query(sql, new NhanVienMapper(), MaNV);
        return result.isEmpty() ? null : result.get(0);
    }

    @Override
    public NhanVienDTO findByMaTK(int MaTK) {
        String sql = "SELECT * FROM nhanvien WHERE MaTK = ?";
        ArrayList<NhanVienDTO> result = query(sql, new NhanVienMapper(), MaTK);
        return result.isEmpty() ? null : result.get(0);
    }

    @Override
    public Integer save(NhanVienDTO nhanVien) {
        String sql = "INSERT INTO nhanvien"
                + " (MaTK, Ho, Ten, NgaySinh, SDT, Email, GioiTinh, Luong)"
                + " VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        return insert(sql, nhanVien.getMaTK(), nhanVien.getHo(), nhanVien.getTen(), nhanVien.getNgaySinh(),
                nhanVien.getSDT(), nhanVien.getEmail(), nhanVien.getGioiTinh(), nhanVien.getLuong());
    }

    @Override
    public boolean update(NhanVienDTO nhanVien) {
        String sql = "UPDATE nhanvien"
                + " SET MaTK = ?, Ho = ?, Ten = ?, NgaySinh = ?, SDT = ?, Email = ?, GioiTinh = ?, Luong = ?"
                + " WHERE MaNV = ?";
        return update(sql, nhanVien.getMaTK(), nhanVien.getHo(), nhanVien.getTen(), nhanVien.getNgaySinh(),
                nhanVien.getSDT(), nhanVien.getEmail(), nhanVien.getGioiTinh(), nhanVien.getLuong(), nhanVien.getMaNV());
    }

    @Override
    public boolean delete(int MaNV) {
        String sql = "DELETE FROM nhanvien"
                + " WHERE MaNV = ?";
        return update(sql, MaNV);
    }
}

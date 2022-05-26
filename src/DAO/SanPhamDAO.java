package DAO;

import DAO.Abstract.AbstractDAO;
import DAO.Interfaces.ISanPhamDAO;
import DAO.Mapper.SanPhamMapper;
import DTO.SanPhamDTO;

import java.util.ArrayList;

public class SanPhamDAO extends AbstractDAO<SanPhamDTO> implements ISanPhamDAO {
    @Override
    public ArrayList<SanPhamDTO> findAll() {
        String sql = "SELECT * FROM sanpham";
        return query(sql, new SanPhamMapper());
    }

    @Override
    public SanPhamDTO findByID(int MaSP) {
        String sql = "SELECT * FROM sanpham WHERE MaSP = ?";
        ArrayList<SanPhamDTO> result = query(sql, new SanPhamMapper(), MaSP);
        return result.isEmpty() ? null : result.get(0);
    }
    @Override
    public Integer save(SanPhamDTO sanPham) {
        String sql = "INSERT INTO sanpham"
                + " (MaSP, MaLoai, MaNCC, TenSP, MoTa, HinhAnh, DonGia, DonVi, SoLuong, TinhTrang)"
                + " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return insert(sql, sanPham.getMaSP(), sanPham.getMaLoai(), sanPham.getMaNCC(), sanPham.getTenSP(),
                sanPham.getMoTa(), sanPham.getHinhAnh(), sanPham.getDonGia(), sanPham.getDonVi(),
                sanPham.getSoLuong(), sanPham.getTinhTrang());
    }

    @Override
    public boolean update(SanPhamDTO sanPham) {
        String sql = "UPDATE sanpham"
                + " SET MaLoai = ?, MaNCC = ?, TenSP = ?, MoTa = ?, HinhAnh = ?, DonGia = ?, DonVi = ?, SoLuong = ?, TinhTrang = ?"
                + " WHERE MaSP = ?";
        return update(sql, sanPham.getMaLoai(), sanPham.getMaNCC(), sanPham.getTenSP(), sanPham.getMoTa(),
                sanPham.getHinhAnh(), sanPham.getDonGia(), sanPham.getDonVi(), sanPham.getSoLuong(),
                sanPham.getTinhTrang(), sanPham.getMaSP());
    }

    @Override
    public boolean delete(int MaSP) {
        String sql = "DELETE FROM sanpham"
                + " WHERE MaSP = ?";
        return update(sql, MaSP);
    }
}

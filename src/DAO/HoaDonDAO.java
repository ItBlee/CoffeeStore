package DAO;

import DAO.Abstract.AbstractDAO;
import DAO.Interfaces.IHoaDonDAO;
import DAO.Mapper.HoaDonMapper;
import DTO.HoaDonDTO;

import java.util.ArrayList;

public class HoaDonDAO extends AbstractDAO<HoaDonDTO> implements IHoaDonDAO {
    @Override
    public ArrayList<HoaDonDTO> findAll() {
        String sql = "SELECT * FROM hoadon";
        return query(sql, new HoaDonMapper());
    }

    @Override
    public HoaDonDTO findByID(int MaHD) {
        String sql = "SELECT * FROM hoadon WHERE MaHD = ?";
        ArrayList<HoaDonDTO> result = query(sql, new HoaDonMapper(), MaHD);
        return result.isEmpty() ? null : result.get(0);
    }
    @Override
    public Integer save(HoaDonDTO hoaDon) {
        String sql = "INSERT INTO hoadon"
                + " (MaHD, MaKH, MaNV, NgayLap, TongTien, TienKhuyenMai, TienThanhToan, TinhTrang)"
                + " VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        return insert(sql, hoaDon.getMaHD(), hoaDon.getMaKH(), hoaDon.getMaNV(), hoaDon.getNgayLap(),
                hoaDon.getTongTien(), hoaDon.getTienKhuyenMai(), hoaDon.getTienThanhToan(), hoaDon.getTinhTrang());
    }

    @Override
    public boolean update(HoaDonDTO hoaDon) {
        String sql = "UPDATE hoadon"
                + " SET MaKH = ?, MaNV = ?, NgayLap = ?, TongTien = ?, TienKhuyenMai = ?, TienThanhToan = ?, TinhTrang = ?"
                + " WHERE MaHD = ?";
        return update(sql, hoaDon.getMaKH(), hoaDon.getMaNV(), hoaDon.getNgayLap(), hoaDon.getTongTien(),
                hoaDon.getTienKhuyenMai(), hoaDon.getTienThanhToan(), hoaDon.getTinhTrang(), hoaDon.getMaHD());
    }

    @Override
    public boolean delete(int MaHD) {
        String sql = "DELETE FROM hoadon"
                + " WHERE MaHD = ?";
        return update(sql, MaHD);
    }
}

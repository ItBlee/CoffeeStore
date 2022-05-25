package DAO;

import DAO.Abstract.AbstractDAO;
import DAO.Interfaces.ICT_HoaDonDAO;
import DAO.Mapper.CT_HoaDonMapper;
import DTO.CT_HoaDonDTO;

import java.util.ArrayList;

public class CT_HoaDonDAO extends AbstractDAO<CT_HoaDonDTO> implements ICT_HoaDonDAO {
    @Override
    public ArrayList<CT_HoaDonDTO> findAll() {
        String sql = "SELECT * FROM ct_hoadon";
        return query(sql, new CT_HoaDonMapper());
    }

    @Override
    public CT_HoaDonDTO findByID(int MaCTHD) {
        String sql = "SELECT * FROM ct_hoadon WHERE MaCTHD = ?";
        ArrayList<CT_HoaDonDTO> result = query(sql, new CT_HoaDonMapper(), MaCTHD);
        return result.isEmpty() ? null : result.get(0);
    }
    @Override
    public Integer save(CT_HoaDonDTO ctHoaDon) {
        String sql = "INSERT INTO ct_hoadon"
                + " (MaCTHD, MaHD, MaSP, SoLuong, DonGia, TienKhuyenMai, ThanhTien)"
                + " VALUES(?, ?, ?, ?, ?, ?, ?)";
        return insert(sql, ctHoaDon.getMaCTHD(), ctHoaDon.getMaHD(), ctHoaDon.getMaSP(), ctHoaDon.getSoLuong(),
                ctHoaDon.getDonGia(), ctHoaDon.getTienKhuyenMai(), ctHoaDon.getThanhTien());
    }

    @Override
    public boolean update(CT_HoaDonDTO ctHoaDon) {
        String sql = "UPDATE ct_hoadon"
                + " SET MaHD = ?, MaSP = ?, SoLuong = ?, DonGia = ?, TienKhuyenMai = ?, ThanhTien = ?"
                + " WHERE MaCTHD = ?";
        return update(sql, ctHoaDon.getMaHD(), ctHoaDon.getMaSP(), ctHoaDon.getSoLuong(),
                ctHoaDon.getDonGia(), ctHoaDon.getTienKhuyenMai(), ctHoaDon.getThanhTien(), ctHoaDon.getMaCTHD());
    }

    @Override
    public boolean delete(int MaCTHD) {
        String sql = "DELETE FROM ct_hoadon"
                + " WHERE MaCTHD = ?";
        return update(sql, MaCTHD);
    }
}

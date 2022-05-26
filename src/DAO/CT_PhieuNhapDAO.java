package DAO;

import DAO.Abstract.AbstractDAO;
import DAO.Interfaces.ICT_PhieuNhapDAO;
import DAO.Mapper.CT_PhieuNhapMapper;
import DTO.CT_PhieuNhapDTO;

import java.util.ArrayList;

public class CT_PhieuNhapDAO extends AbstractDAO<CT_PhieuNhapDTO> implements ICT_PhieuNhapDAO {
    @Override
    public ArrayList<CT_PhieuNhapDTO> findAll() {
        String sql = "SELECT * FROM ct_phieunhap";
        return query(sql, new CT_PhieuNhapMapper());
    }

    @Override
    public CT_PhieuNhapDTO findByID(int MaCTPN) {
        String sql = "SELECT * FROM ct_phieunhap WHERE MaCTPN = ?";
        ArrayList<CT_PhieuNhapDTO> result = query(sql, new CT_PhieuNhapMapper(), MaCTPN);
        return result.isEmpty() ? null : result.get(0);
    }
    @Override
    public Integer save(CT_PhieuNhapDTO taiKhoan) {
        String sql = "INSERT INTO ct_phieunhap"
                + " (MaCTPN, MaPN, MaSP, SoLuong, DonGia, ThanhTien)"
                + " VALUES(?, ?, ?, ?, ?, ?)";
        return insert(sql, taiKhoan.getMaCTPN(), taiKhoan.getMaPN(), taiKhoan.getMaSP(),
                taiKhoan.getSoLuong(), taiKhoan.getDonGia(), taiKhoan.getThanhTien());
    }

    @Override
    public boolean update(CT_PhieuNhapDTO taiKhoan) {
        String sql = "UPDATE ct_phieunhap"
                + " SET MaPN = ?, MaSP = ?, SoLuong = ?, DonGia = ?, ThanhTien = ?"
                + " WHERE MaCTPN = ?";
        return update(sql, taiKhoan.getMaPN(), taiKhoan.getMaSP(), taiKhoan.getSoLuong(),
                taiKhoan.getDonGia(), taiKhoan.getThanhTien(), taiKhoan.getMaCTPN());
    }

    @Override
    public boolean delete(int MaCTPN) {
        String sql = "DELETE FROM ct_phieunhap"
                + " WHERE MaCTPN = ?";
        return update(sql, MaCTPN);
    }
}

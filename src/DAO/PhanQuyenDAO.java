package DAO;

import DAO.Abstract.AbstractDAO;
import DAO.Interfaces.IPhanQuyenDAO;
import DAO.Mapper.PhanQuyenMapper;
import DTO.PhanQuyenDTO;

import java.util.ArrayList;

public class PhanQuyenDAO extends AbstractDAO<PhanQuyenDTO> implements IPhanQuyenDAO {
    @Override
    public ArrayList<PhanQuyenDTO> findAll() {
        String sql = "SELECT * FROM phanquyen";
        return query(sql, new PhanQuyenMapper());
    }

    @Override
    public PhanQuyenDTO findByID(int MaPQ) {
        String sql = "SELECT * FROM phanquyen WHERE MaPQ = ?";
        ArrayList<PhanQuyenDTO> result = query(sql, new PhanQuyenMapper(), MaPQ);
        return result.isEmpty() ? null : result.get(0);
    }

    @Override
    public Integer save(PhanQuyenDTO dto) {
        String sql = "INSERT INTO phanquyen"
                + " (MaPQ, TenPQ, QuyenHD, QuyenSP, QuyenPN, QuyenNCC, QuyenKH, QuyenKM, QuyenTK, QuyenExcel, QuyenNV)"
                + " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return insert(sql, dto.getMaPQ(), dto.getTenPQ(), dto.getQuyenHD(), dto.getQuyenSP(),
                dto.getQuyenPN(), dto.getQuyenNCC(), dto.getQuyenKH(), dto.getQuyenKM(),
                dto.getQuyenTK(), dto.getQuyenExcel(), dto.getQuyenNV());
    }

    @Override
    public boolean update(PhanQuyenDTO dto) {
        String sql = "UPDATE phanquyen"
                + " SET TenPQ = ?, QuyenHD = ?, QuyenSP = ?,"
                + " QuyenPN = ?, QuyenNCC = ?, QuyenKH = ?, QuyenKM = ?, QuyenTK = ?, QuyenExcel = ?, QuyenNV = ?"
                + " WHERE MaPQ = ?";
        return update(sql, dto.getTenPQ(), dto.getQuyenHD(), dto.getQuyenSP(),
                dto.getQuyenPN(), dto.getQuyenNCC(), dto.getQuyenKH(), dto.getQuyenKM(),
                dto.getQuyenTK(), dto.getQuyenExcel(), dto.getQuyenNV(), dto.getMaPQ());
    }

    @Override
    public boolean delete(int MaPQ) {
        String sql = "DELETE FROM phanquyen"
                + " WHERE MaPQ = ?";
        return update(sql, MaPQ);
    }
}

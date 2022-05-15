package DAO;

import DAO.Abstract.AbstractDAO;
import DAO.Interfaces.ICT_PhanQuyenDAO;
import DAO.Mapper.CT_PhanQuyenMapper;
import DTO.CT_PhanQuyenDTO;

import java.util.ArrayList;

public class CT_PhanQuyenDAO extends AbstractDAO<CT_PhanQuyenDTO> implements ICT_PhanQuyenDAO {
    @Override
    public ArrayList<CT_PhanQuyenDTO> findAll() {
        String sql = "SELECT * FROM ct_phanquyen";
        return query(sql, new CT_PhanQuyenMapper());
    }

    @Override
    public CT_PhanQuyenDTO findByID(int MaCTPQ) {
        String sql = "SELECT * FROM ct_phanquyen WHERE MaCTPQ = ?";
        ArrayList<CT_PhanQuyenDTO> result = query(sql, new CT_PhanQuyenMapper(), MaCTPQ);
        return result.isEmpty() ? null : result.get(0);
    }

    @Override
    public Integer save(CT_PhanQuyenDTO dto) {
        String sql = "INSERT INTO ct_phanquyen"
                + " (MaCTPQ, QuyenDoc, QuyenTao, QuyenSua, QuyenXoa)"
                + " VALUES(?, ?, ?, ?, ?)";
        return insert(sql, dto.getMaCTPQ(), dto.getQuyenDoc(), dto.getQuyenTao(), dto.getQuyenSua(), dto.getQuyenXoa());
    }

    @Override
    public boolean update(CT_PhanQuyenDTO dto) {
        String sql = "UPDATE ct_phanquyen"
                + " SET QuyenDoc = ?, QuyenTao = ?, QuyenSua = ?, QuyenXoa = ?"
                + " WHERE MaCTPQ = ?";
        return update(sql, dto.getQuyenDoc(), dto.getQuyenTao(), dto.getQuyenSua(), dto.getQuyenXoa(), dto.getMaCTPQ());
    }

    @Override
    public boolean delete(int MaCTPQ) {
        String sql = "DELETE FROM ct_phanquyen"
                + " WHERE MaCTPQ = ?";
        return update(sql, MaCTPQ);
    }
}

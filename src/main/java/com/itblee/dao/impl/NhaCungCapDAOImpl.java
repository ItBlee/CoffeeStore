package com.itblee.dao.impl;

import com.google.auto.service.AutoService;
import com.itblee.dao.NhaCungCapDAO;
import com.itblee.dto.NhaCungCap;
import com.itblee.mapper.NhaCungCapMapper;
import com.itblee.Provider;

import java.util.List;

@AutoService(NhaCungCapDAO.class)
public class NhaCungCapDAOImpl extends AbstractDAO<NhaCungCap> implements NhaCungCapDAO {

    private NhaCungCapMapper mapper = Provider.get(NhaCungCapMapper.class);

    @Override
    public List<NhaCungCap> findAll() {
        String sql = "SELECT * FROM nhacungcap";
        return query(sql, mapper);
    }

    @Override
    public NhaCungCap findByID(int MaNCC) {
        String sql = "SELECT * FROM nhacungcap WHERE MaNCC = ?";
        List<NhaCungCap> result = query(sql, mapper, MaNCC);
        return result.isEmpty() ? null : result.get(0);
    }

    @Override
    public Integer save(NhaCungCap nhaCungCap) {
        String sql = "INSERT INTO nhacungcap"
                + " (MaNCC, TenNCC, SDT, DiaChi, SoTaiKhoan, TinhTrang)"
                + " VALUES(?, ?, ?, ?, ?, ?)";
        return insert(sql, nhaCungCap.getMaNCC(), nhaCungCap.getTenNCC(), nhaCungCap.getSdt(),
                nhaCungCap.getDiaChi(), nhaCungCap.getSoTaiKhoan(), nhaCungCap.getTinhTrang());
    }

    @Override
    public boolean update(NhaCungCap nhaCungCap) {
        String sql = "UPDATE nhacungcap"
                + " SET TenNCC = ?, SDT = ?, DiaChi = ?, SoTaiKhoan = ?, TinhTrang = ?"
                + " WHERE MaNCC = ?";
        return update(sql, nhaCungCap.getTenNCC(), nhaCungCap.getSdt(), nhaCungCap.getDiaChi(),
                nhaCungCap.getSoTaiKhoan(), nhaCungCap.getTinhTrang(), nhaCungCap.getMaNCC());
    }

    @Override
    public boolean delete(int MaNCC) {
        String sql = "DELETE FROM nhacungcap"
                + " WHERE MaNCC = ?";
        return update(sql, MaNCC);
    }
}

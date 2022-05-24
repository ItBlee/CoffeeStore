package DAO;

import DAO.Abstract.AbstractDAO;
import DAO.Interfaces.INhaCungCapDAO;
import DAO.Mapper.NhaCungCapMapper;
import DTO.NhaCungCapDTO;

import java.util.ArrayList;

public class NhaCungCapDAO extends AbstractDAO<NhaCungCapDTO> implements INhaCungCapDAO {
    @Override
    public ArrayList<NhaCungCapDTO> findAll() {
        String sql = "SELECT * FROM nhacungcap";
        return query(sql, new NhaCungCapMapper());
    }

    @Override
    public NhaCungCapDTO findByID(int MaNCC) {
        String sql = "SELECT * FROM nhacungcap WHERE MaNCC = ?";
        ArrayList<NhaCungCapDTO> result = query(sql, new NhaCungCapMapper(), MaNCC);
        return result.isEmpty() ? null : result.get(0);
    }
    @Override
    public Integer save(NhaCungCapDTO nhaCungCap) {
        String sql = "INSERT INTO nhacungcap"
                + " (MaNCC, TenNCC, SDT, DiaChi, SoTaiKhoan, TinhTrang)"
                + " VALUES(?, ?, ?, ?, ?, ?)";
        return insert(sql, nhaCungCap.getMaNCC(), nhaCungCap.getTenNCC(), nhaCungCap.getSDT(),
                nhaCungCap.getDiaChi(), nhaCungCap.getSoTaiKhoan(), nhaCungCap.getTinhTrang());
    }

    @Override
    public boolean update(NhaCungCapDTO nhaCungCap) {
        String sql = "UPDATE nhacungcap"
                + " SET TenNCC = ?, SDT = ?, DiaChi = ?, SoTaiKhoan = ?, TinhTrang = ?"
                + " WHERE MaNCC = ?";
        return update(sql, nhaCungCap.getTenNCC(), nhaCungCap.getSDT(), nhaCungCap.getDiaChi(),
                nhaCungCap.getSoTaiKhoan(), nhaCungCap.getTinhTrang(), nhaCungCap.getMaNCC());
    }

    @Override
    public boolean delete(int MaNCC) {
        String sql = "DELETE FROM nhacungcap"
                + " WHERE MaNCC = ?";
        return update(sql, MaNCC);
    }
}

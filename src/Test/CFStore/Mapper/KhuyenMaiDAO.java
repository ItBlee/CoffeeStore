package Test.CFStore.Mapper;

import DAO.Abstract.AbstractDAO;
import DAO.Interfaces.IKhuyenMaiDAO;
import DTO.KhuyenMaiDTO;

import java.util.ArrayList;

public class KhuyenMaiDAO extends AbstractDAO<KhuyenMaiDTO> implements IKhuyenMaiDAO {
    @Override
    public ArrayList<KhuyenMaiDTO> findAll() {
        return null;
    }

    @Override
    public KhuyenMaiDTO findByID(int MaSP) {
        return null;
    }

    @Override
    public Integer save(KhuyenMaiDTO entity) {
        return null;
    }

    @Override
    public boolean update(KhuyenMaiDTO entity) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
}

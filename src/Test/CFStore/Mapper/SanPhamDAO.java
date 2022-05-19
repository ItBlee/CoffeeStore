package Test.CFStore.Mapper;

import DAO.Abstract.AbstractDAO;
import DAO.Interfaces.ISanPhamDAO;
import DTO.SanPhamDTO;

import java.util.ArrayList;

public class SanPhamDAO extends AbstractDAO<SanPhamDTO> implements ISanPhamDAO {
    @Override
    public ArrayList<SanPhamDTO> findAll() {
        return null;
    }

    @Override
    public SanPhamDTO findByID(int MaSP) {
        return null;
    }

    @Override
    public Integer save(SanPhamDTO entity) {
        return null;
    }

    @Override
    public boolean update(SanPhamDTO entity) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
}

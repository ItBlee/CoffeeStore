package DAO;

import DAO.Abstract.AbstractDAO;
import DAO.Interfaces.INhaCungCapDAO;
import DTO.NhaCungCapDTO;

import java.util.ArrayList;

public class NhaCungCapDAO extends AbstractDAO<NhaCungCapDTO> implements INhaCungCapDAO {
    @Override
    public ArrayList<NhaCungCapDTO> findAll() {
        return null;
    }

    @Override
    public NhaCungCapDTO findByID(int MaSP) {
        return null;
    }

    @Override
    public Integer save(NhaCungCapDTO entity) {
        return null;
    }

    @Override
    public boolean update(NhaCungCapDTO entity) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
}

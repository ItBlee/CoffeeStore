package DAO;

import DAO.Abstract.AbstractDAO;
import DAO.Interfaces.ILoaiSPDAO;
import DTO.LoaiSPDTO;

import java.util.ArrayList;

public class LoaiSPDAO extends AbstractDAO<LoaiSPDTO> implements ILoaiSPDAO {
    @Override
    public ArrayList<LoaiSPDTO> findAll() {
        return null;
    }

    @Override
    public LoaiSPDTO findByID(int MaSP) {
        return null;
    }

    @Override
    public Integer save(LoaiSPDTO entity) {
        return null;
    }

    @Override
    public boolean update(LoaiSPDTO entity) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
}

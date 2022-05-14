package DAO;

import DAO.Abstract.AbstractDAO;
import DAO.Interfaces.IPhieuNhapDAO;
import DTO.PhieuNhapDTO;

import java.util.ArrayList;

public class PhieuNhapDAO extends AbstractDAO<PhieuNhapDTO> implements IPhieuNhapDAO {
    @Override
    public ArrayList<PhieuNhapDTO> findAll() {
        return null;
    }

    @Override
    public PhieuNhapDTO findByID(int MaSP) {
        return null;
    }

    @Override
    public Integer save(PhieuNhapDTO entity) {
        return null;
    }

    @Override
    public boolean update(PhieuNhapDTO entity) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
}

package DAO;

import DAO.Abstract.AbstractDAO;
import DAO.Interfaces.ICT_PhieuNhapDAO;
import DTO.CT_PhieuNhapDTO;

import java.util.ArrayList;

public class CT_PhieuNhapDAO extends AbstractDAO<CT_PhieuNhapDTO> implements ICT_PhieuNhapDAO {
    @Override
    public ArrayList<CT_PhieuNhapDTO> findAll() {
        return null;
    }

    @Override
    public CT_PhieuNhapDTO findByID(int MaSP) {
        return null;
    }

    @Override
    public Integer save(CT_PhieuNhapDTO entity) {
        return null;
    }

    @Override
    public boolean update(CT_PhieuNhapDTO entity) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
}

package DAO;

import DAO.Abstract.AbstractDAO;
import DAO.Interfaces.ICT_HoaDonDAO;
import DTO.CT_HoaDonDTO;

import java.util.ArrayList;

public class CT_HoaDonDAO extends AbstractDAO<CT_HoaDonDTO> implements ICT_HoaDonDAO {
    @Override
    public ArrayList<CT_HoaDonDTO> findAll() {
        return null;
    }

    @Override
    public CT_HoaDonDTO findByID(int MaSP) {
        return null;
    }

    @Override
    public Integer save(CT_HoaDonDTO entity) {
        return null;
    }

    @Override
    public boolean update(CT_HoaDonDTO entity) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
}

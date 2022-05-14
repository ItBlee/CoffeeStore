package DAO;

import DAO.Abstract.AbstractDAO;
import DAO.Interfaces.ICT_KhuyenMaiDAO;
import DTO.CT_KhuyenMaiDTO;

import java.util.ArrayList;

public class CT_KhuyenMaiDAO extends AbstractDAO<CT_KhuyenMaiDTO> implements ICT_KhuyenMaiDAO {
    @Override
    public ArrayList<CT_KhuyenMaiDTO> findAll() {
        return null;
    }

    @Override
    public CT_KhuyenMaiDTO findByID(int MaSP) {
        return null;
    }

    @Override
    public Integer save(CT_KhuyenMaiDTO entity) {
        return null;
    }

    @Override
    public boolean update(CT_KhuyenMaiDTO entity) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
}

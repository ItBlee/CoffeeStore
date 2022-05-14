package DAO;

import DAO.Abstract.AbstractDAO;
import DAO.Interfaces.IHoaDonDAO;
import DTO.HoaDonDTO;

import java.util.ArrayList;

public class HoaDonDAO extends AbstractDAO<HoaDonDTO> implements IHoaDonDAO {
    @Override
    public ArrayList<HoaDonDTO> findAll() {
        return null;
    }

    @Override
    public HoaDonDTO findByID(int MaSP) {
        return null;
    }

    @Override
    public Integer save(HoaDonDTO entity) {
        return null;
    }

    @Override
    public boolean update(HoaDonDTO entity) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
}

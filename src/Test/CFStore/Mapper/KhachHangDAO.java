package Test.CFStore.Mapper;

import DAO.Abstract.AbstractDAO;
import DAO.Interfaces.IKhachHangDAO;
import DTO.KhachHangDTO;

import java.util.ArrayList;

public class KhachHangDAO extends AbstractDAO<KhachHangDTO> implements IKhachHangDAO {
    @Override
    public ArrayList<KhachHangDTO> findAll() {
        return null;
    }

    @Override
    public KhachHangDTO findByID(int id) {
        return null;
    }

    @Override
    public Integer save(KhachHangDTO entity) {
        return null;
    }

    @Override
    public boolean update(KhachHangDTO entity) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
}

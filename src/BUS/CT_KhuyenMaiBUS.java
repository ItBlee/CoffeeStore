package BUS;

import BUS.Abstract.AbstractHistoricBUS;
import BUS.Interfaces.ICT_KhuyenMaiBUS;
import DAO.Interfaces.IKhuyenMaiDAO;
import DAO.KhuyenMaiDAO;
import DTO.CT_KhuyenMaiDTO;

import java.util.ArrayList;
import java.util.HashMap;

public class CT_KhuyenMaiBUS extends AbstractHistoricBUS implements ICT_KhuyenMaiBUS {
    private static ArrayList<CT_KhuyenMaiDTO> listCTKhuyenMai = null;
    private final IKhuyenMaiDAO khuyenMaiDAO;

    public CT_KhuyenMaiBUS() {
        this.khuyenMaiDAO = new KhuyenMaiDAO();
        if (listCTKhuyenMai == null)
            listCTKhuyenMai = findAll();
    }

    @Override
    public ArrayList<CT_KhuyenMaiDTO> findAll() {
        return null;
    }

    @Override
    public CT_KhuyenMaiDTO findByID(int id) {
        return null;
    }

    @Override
    public CT_KhuyenMaiDTO findByMaSP(Integer maSP) {
        return null;
    }

    @Override
    public void save(CT_KhuyenMaiDTO ctKhuyenMai) throws Exception {

    }

    @Override
    public void update(CT_KhuyenMaiDTO ctKhuyenMai) throws Exception {

    }

    @Override
    public void delete(int id) throws Exception {

    }

    @Override
    public HashMap<Integer, Boolean> delete(int[] ids) {
        return null;
    }

    @Override
    public boolean isExist(CT_KhuyenMaiDTO ctKhuyenMai) {
        return false;
    }

    @Override
    public int getTotalCount() {
        return 0;
    }
}

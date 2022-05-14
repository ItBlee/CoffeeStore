package BUS;

import BUS.Abstract.AbstractHistoricBUS;
import BUS.Interfaces.IKhuyenMaiBUS;
import DAO.Interfaces.IKhuyenMaiDAO;
import DAO.KhuyenMaiDAO;
import DTO.KhuyenMaiDTO;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;

public class KhuyenMaiBUS extends AbstractHistoricBUS implements IKhuyenMaiBUS {
    private static ArrayList<KhuyenMaiDTO> listKhuyenMai = null;
    private final IKhuyenMaiDAO khuyenMaiDAO;

    public KhuyenMaiBUS() {
        this.khuyenMaiDAO = new KhuyenMaiDAO();
        if (listKhuyenMai == null)
            listKhuyenMai = findAll();
    }

    @Override
    public ArrayList<KhuyenMaiDTO> findAll() {
        return null;
    }

    @Override
    public KhuyenMaiDTO findByID(int id) {
        return null;
    }

    @Override
    public ArrayList<KhuyenMaiDTO> findByTieuDe(String tieuDe) {
        return null;
    }

    @Override
    public ArrayList<KhuyenMaiDTO> findByThoiGian(Date tuNgay, Date denNgay) {
        return null;
    }

    @Override
    public void save(KhuyenMaiDTO khuyenMai) throws Exception {

    }

    @Override
    public void update(KhuyenMaiDTO khuyenMai) throws Exception {

    }

    @Override
    public void delete(int id) throws Exception {

    }

    @Override
    public HashMap<Integer, Boolean> delete(int[] ids) {
        return null;
    }

    @Override
    public boolean isExist(KhuyenMaiDTO khuyenMai) {
        return false;
    }

    @Override
    public int getTotalCount() {
        return 0;
    }
}

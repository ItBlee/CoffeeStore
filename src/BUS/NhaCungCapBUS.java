package BUS;

import BUS.Abstract.AbstractHistoricBUS;
import BUS.Interfaces.INhaCungCapBUS;
import DAO.Interfaces.INhaCungCapDAO;
import DAO.NhaCungCapDAO;
import DTO.NhaCungCapDTO;

import java.util.ArrayList;
import java.util.HashMap;

public class NhaCungCapBUS extends AbstractHistoricBUS implements INhaCungCapBUS {
    private static ArrayList<NhaCungCapDTO> listNhaCungCap = null;
    private final INhaCungCapDAO nhaCungCapDAO;

    public NhaCungCapBUS() {
        this.nhaCungCapDAO = new NhaCungCapDAO();
        if (listNhaCungCap == null)
            listNhaCungCap = findAll();
    }

    @Override
    public ArrayList<NhaCungCapDTO> findAll() {
        return null;
    }

    @Override
    public NhaCungCapDTO findByID(int id) {
        return null;
    }

    @Override
    public ArrayList<NhaCungCapDTO> findByTenNCC(String tenNCC) {
        return null;
    }

    @Override
    public ArrayList<NhaCungCapDTO> findBySDT(String sdt) {
        return null;
    }

    @Override
    public ArrayList<NhaCungCapDTO> findByDiaChi(String diaChi) {
        return null;
    }

    @Override
    public ArrayList<NhaCungCapDTO> findBySoTaiKhoan(String stk) {
        return null;
    }

    @Override
    public ArrayList<NhaCungCapDTO> findByTinhTrang(Integer tinhTrang) {
        return null;
    }

    @Override
    public void save(NhaCungCapDTO nhaCungCap) throws Exception {

    }

    @Override
    public void update(NhaCungCapDTO nhaCungCap) throws Exception {

    }

    @Override
    public void delete(int id) throws Exception {

    }

    @Override
    public HashMap<Integer, Boolean> delete(int[] ids) {
        return null;
    }

    @Override
    public boolean isExist(NhaCungCapDTO nhaCungCap) {
        return false;
    }

    @Override
    public int getTotalCount() {
        return 0;
    }
}

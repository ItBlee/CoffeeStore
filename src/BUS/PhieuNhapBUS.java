package BUS;

import BUS.Abstract.AbstractHistoricBUS;
import BUS.Interfaces.IPhieuNhapBUS;
import DAO.Interfaces.IPhieuNhapDAO;
import DAO.PhieuNhapDAO;
import DTO.PhieuNhapDTO;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;

public class PhieuNhapBUS extends AbstractHistoricBUS implements IPhieuNhapBUS {
    private static ArrayList<PhieuNhapDTO> listPhieuNhap = null;
    private final IPhieuNhapDAO phieuNhapDAO;

    public PhieuNhapBUS() {
        this.phieuNhapDAO = new PhieuNhapDAO();
        if (listPhieuNhap == null)
            listPhieuNhap = findAll();
    }

    @Override
    public ArrayList<PhieuNhapDTO> findAll() {
        return null;
    }

    @Override
    public PhieuNhapDTO findByID(int id) {
        return null;
    }

    @Override
    public ArrayList<PhieuNhapDTO> findByNCC(Integer maNCC) {
        return null;
    }

    @Override
    public ArrayList<PhieuNhapDTO> findByNCC(String TenNCC) {
        return null;
    }

    @Override
    public ArrayList<PhieuNhapDTO> findByNhanVien(Integer maNV) {
        return null;
    }

    @Override
    public ArrayList<PhieuNhapDTO> findByNhanVien(String TenNV) {
        return null;
    }

    @Override
    public ArrayList<PhieuNhapDTO> findBySDT(String sdt) {
        return null;
    }

    @Override
    public ArrayList<PhieuNhapDTO> findByNgayTao(Date tuNgay, Date denNgay) {
        return null;
    }

    @Override
    public ArrayList<PhieuNhapDTO> findByTongTien(Integer from, Integer to) {
        return null;
    }

    @Override
    public Integer save(PhieuNhapDTO phieuNhap) throws Exception {
        return null;
    }

    @Override
    public void update(PhieuNhapDTO phieuNhap) throws Exception {

    }

    @Override
    public void delete(int id) throws Exception {

    }

    @Override
    public HashMap<Integer, Boolean> delete(int[] ids) {
        return null;
    }

    @Override
    public boolean isExist(PhieuNhapDTO phieuNhap) {
        return false;
    }

    @Override
    public int getTotalCount() {
        return 0;
    }
}

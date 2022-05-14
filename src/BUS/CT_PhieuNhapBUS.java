package BUS;

import BUS.Abstract.AbstractHistoricBUS;
import BUS.Interfaces.ICT_PhieuNhapBUS;
import DAO.CT_PhieuNhapDAO;
import DAO.Interfaces.ICT_PhieuNhapDAO;
import DTO.CT_PhieuNhapDTO;

import java.util.ArrayList;
import java.util.HashMap;

public class CT_PhieuNhapBUS extends AbstractHistoricBUS implements ICT_PhieuNhapBUS {
    private static ArrayList<CT_PhieuNhapDTO> listCTPhieuNhap = null;
    private final ICT_PhieuNhapDAO ictPhieuNhapDAO;

    public CT_PhieuNhapBUS() {
        this.ictPhieuNhapDAO = new CT_PhieuNhapDAO();
        if (listCTPhieuNhap == null)
            listCTPhieuNhap = findAll();
    }

    @Override
    public ArrayList<CT_PhieuNhapDTO> findAll() {
        return null;
    }

    @Override
    public CT_PhieuNhapDTO findByID(int id) {
        return null;
    }

    @Override
    public CT_PhieuNhapDTO findByMaSP(Integer maSP) {
        return null;
    }

    @Override
    public ArrayList<CT_PhieuNhapDTO> findBySoLuong(Integer from, Integer to) {
        return null;
    }

    @Override
    public ArrayList<CT_PhieuNhapDTO> findByDonGia(Integer from, Integer to) {
        return null;
    }

    @Override
    public ArrayList<CT_PhieuNhapDTO> findByThanhTien(Integer from, Integer to) {
        return null;
    }

    @Override
    public void save(CT_PhieuNhapDTO ctPhieuNhap) throws Exception {

    }

    @Override
    public void update(CT_PhieuNhapDTO ctPhieuNhap) throws Exception {

    }

    @Override
    public void delete(int id) throws Exception {

    }

    @Override
    public HashMap<Integer, Boolean> delete(int[] ids) {
        return null;
    }

    @Override
    public boolean isExist(CT_PhieuNhapDTO ctHoaDon) {
        return false;
    }

    @Override
    public int getTotalCount() {
        return 0;
    }
}

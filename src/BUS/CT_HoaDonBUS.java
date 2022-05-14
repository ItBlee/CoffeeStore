package BUS;

import BUS.Abstract.AbstractHistoricBUS;
import BUS.Interfaces.ICT_HoaDonBUS;
import DAO.CT_HoaDonDAO;
import DAO.Interfaces.ICT_HoaDonDAO;
import DTO.CT_HoaDonDTO;

import java.util.ArrayList;
import java.util.HashMap;

public class CT_HoaDonBUS extends AbstractHistoricBUS implements ICT_HoaDonBUS {
    private static ArrayList<CT_HoaDonDTO> listCTHoaDon = null;
    private final ICT_HoaDonDAO ictHoaDonDAO;

    public CT_HoaDonBUS() {
        this.ictHoaDonDAO = new CT_HoaDonDAO();
        if (listCTHoaDon == null)
            listCTHoaDon = findAll();
    }

    @Override
    public ArrayList<CT_HoaDonDTO> findAll() {
        return null;
    }

    @Override
    public CT_HoaDonDTO findByID(int id) {
        return null;
    }

    @Override
    public CT_HoaDonDTO findByMaSP(Integer maSP) {
        return null;
    }

    @Override
    public ArrayList<CT_HoaDonDTO> findBySoLuong(Integer from, Integer to) {
        return null;
    }

    @Override
    public ArrayList<CT_HoaDonDTO> findByDonGia(Integer from, Integer to) {
        return null;
    }

    @Override
    public ArrayList<CT_HoaDonDTO> findByThanhTien(Integer from, Integer to) {
        return null;
    }

    @Override
    public void save(CT_HoaDonDTO ctHoaDon) throws Exception {

    }

    @Override
    public void update(CT_HoaDonDTO ctHoaDon) throws Exception {

    }

    @Override
    public void delete(int id) throws Exception {

    }

    @Override
    public HashMap<Integer, Boolean> delete(int[] ids) {
        return null;
    }

    @Override
    public boolean isExist(CT_HoaDonDTO ctHoaDon) {
        return false;
    }

    @Override
    public int getTotalCount() {
        return 0;
    }
}

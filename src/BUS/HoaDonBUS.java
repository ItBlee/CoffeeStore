package BUS;

import BUS.Abstract.AbstractHistoricBUS;
import BUS.Interfaces.IHoaDonBUS;
import DAO.HoaDonDAO;
import DAO.Interfaces.IHoaDonDAO;
import DTO.HoaDonDTO;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;

public class HoaDonBUS extends AbstractHistoricBUS implements IHoaDonBUS {
    private static ArrayList<HoaDonDTO> listHoaDon = null;
    private final IHoaDonDAO hoaDonDAO;

    public HoaDonBUS() {
        this.hoaDonDAO = new HoaDonDAO();
        if (listHoaDon == null)
            listHoaDon = findAll();
    }

    @Override
    public ArrayList<HoaDonDTO> findAll() {
        return null;
    }

    @Override
    public HoaDonDTO findByID(int id) {
        return null;
    }

    @Override
    public HoaDonDTO findByMaKH(Integer maKH) {
        return null;
    }

    @Override
    public HoaDonDTO findByMaNV(Integer maNV) {
        return null;
    }

    @Override
    public ArrayList<HoaDonDTO> findByNgayLap(Date tuNgay, Date denNgay) {
        return null;
    }

    @Override
    public ArrayList<HoaDonDTO> findByTongTien(Integer from, Integer to) {
        return null;
    }

    @Override
    public ArrayList<HoaDonDTO> findByThanhTien(Integer from, Integer to) {
        return null;
    }

    @Override
    public void save(HoaDonDTO hoaDon) throws Exception {

    }

    @Override
    public void update(HoaDonDTO hoaDon) throws Exception {

    }

    @Override
    public void delete(int id) throws Exception {

    }

    @Override
    public HashMap<Integer, Boolean> delete(int[] ids) {
        return null;
    }

    @Override
    public boolean isExist(HoaDonDTO hoaDon) {
        return false;
    }

    @Override
    public int getTotalCount() {
        return 0;
    }
}

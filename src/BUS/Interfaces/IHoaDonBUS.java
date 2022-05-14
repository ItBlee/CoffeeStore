package BUS.Interfaces;

import DTO.HoaDonDTO;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;

public interface IHoaDonBUS {
    ArrayList<HoaDonDTO> findAll();
    HoaDonDTO findByID(int id);
    HoaDonDTO findByMaKH(Integer maKH);
    HoaDonDTO findByMaNV(Integer maNV);
    ArrayList<HoaDonDTO> findByNgayLap(Date tuNgay, Date denNgay);
    ArrayList<HoaDonDTO> findByTongTien(Integer from, Integer to);
    ArrayList<HoaDonDTO> findByThanhTien(Integer from, Integer to);

    void save(HoaDonDTO hoaDon) throws Exception;
    void update(HoaDonDTO hoaDon) throws Exception;
    void delete(int id) throws Exception;
    HashMap<Integer, Boolean> delete(int[] ids);

    boolean isExist(HoaDonDTO hoaDon);
    int getTotalCount();
}

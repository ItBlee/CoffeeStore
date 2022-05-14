package BUS.Interfaces;

import DTO.CT_HoaDonDTO;

import java.util.ArrayList;
import java.util.HashMap;

public interface ICT_HoaDonBUS {
    ArrayList<CT_HoaDonDTO> findAll();
    CT_HoaDonDTO findByID(int id);
    CT_HoaDonDTO findByMaSP(Integer maSP);
    ArrayList<CT_HoaDonDTO> findBySoLuong(Integer from, Integer to);
    ArrayList<CT_HoaDonDTO> findByDonGia(Integer from, Integer to);
    ArrayList<CT_HoaDonDTO> findByThanhTien(Integer from, Integer to);

    void save(CT_HoaDonDTO ctHoaDon) throws Exception;
    void update(CT_HoaDonDTO ctHoaDon) throws Exception;
    void delete(int id) throws Exception;
    HashMap<Integer, Boolean> delete(int[] ids);

    boolean isExist(CT_HoaDonDTO ctHoaDon);
    int getTotalCount();
}

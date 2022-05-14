package BUS.Interfaces;

import DTO.CT_PhieuNhapDTO;

import java.util.ArrayList;
import java.util.HashMap;

public interface ICT_PhieuNhapBUS {
    ArrayList<CT_PhieuNhapDTO> findAll();
    CT_PhieuNhapDTO findByID(int id);
    CT_PhieuNhapDTO findByMaSP(Integer maSP);
    ArrayList<CT_PhieuNhapDTO> findBySoLuong(Integer from, Integer to);
    ArrayList<CT_PhieuNhapDTO> findByDonGia(Integer from, Integer to);
    ArrayList<CT_PhieuNhapDTO> findByThanhTien(Integer from, Integer to);


    void save(CT_PhieuNhapDTO ctPhieuNhap) throws Exception;
    void update(CT_PhieuNhapDTO ctPhieuNhap) throws Exception;
    void delete(int id) throws Exception;
    HashMap<Integer, Boolean> delete(int[] ids);

    boolean isExist(CT_PhieuNhapDTO ctHoaDon);
    int getTotalCount();
}

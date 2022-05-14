package BUS.Interfaces;

import DTO.PhieuNhapDTO;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;

public interface IPhieuNhapBUS {
    ArrayList<PhieuNhapDTO> findAll();
    PhieuNhapDTO findByID(int id);
    ArrayList<PhieuNhapDTO> findByMaNCC(Integer maNCC);
    ArrayList<PhieuNhapDTO> findByMaNV(Integer maNV);
    ArrayList<PhieuNhapDTO> findBySDT(String sdt);
    ArrayList<PhieuNhapDTO> findByNgayTao(Date tuNgay, Date denNgay);
    ArrayList<PhieuNhapDTO> findByTongTien(Integer from, Integer to);

    void save(PhieuNhapDTO phieuNhap) throws Exception;
    void update(PhieuNhapDTO phieuNhap) throws Exception;
    void delete(int id) throws Exception;
    HashMap<Integer, Boolean> delete(int[] ids);

    boolean isExist(PhieuNhapDTO phieuNhap);
    int getTotalCount();
    int getSoLuongNhapTheoMaSP(Integer maSP);
}

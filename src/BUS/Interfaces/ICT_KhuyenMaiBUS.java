package BUS.Interfaces;

import DTO.CT_KhuyenMaiDTO;

import java.util.ArrayList;
import java.util.HashMap;

public interface ICT_KhuyenMaiBUS {
    ArrayList<CT_KhuyenMaiDTO> findAll();
    CT_KhuyenMaiDTO findByID(int id);
    CT_KhuyenMaiDTO findByMaSP(Integer maSP);

    void save(CT_KhuyenMaiDTO ctKhuyenMai) throws Exception;
    void update(CT_KhuyenMaiDTO ctKhuyenMai) throws Exception;
    void delete(int id) throws Exception;
    HashMap<Integer, Boolean> delete(int[] ids);

    boolean isExist(CT_KhuyenMaiDTO ctKhuyenMai);
    int getTotalCount();
}

package BUS.Interfaces;

import DTO.SanPhamDTO;

import java.util.ArrayList;
import java.util.HashMap;

public interface ISanPhamBUS {
    ArrayList<SanPhamDTO> findAll();
    SanPhamDTO findByID(int id);
    ArrayList<SanPhamDTO> findByMaNCC(Integer maNCC);
    ArrayList<SanPhamDTO> findByMaLoai(Integer maLoai);
    ArrayList<SanPhamDTO> findByTenSP(String tenSP);
    ArrayList<SanPhamDTO> findByDonGia(Integer from, Integer to);
    ArrayList<SanPhamDTO> findByDonVi(String donVi);
    ArrayList<SanPhamDTO> findBySoLuong(Integer from, Integer to);
    ArrayList<SanPhamDTO> findByTinhTrang(Integer tinhTrang);

    void save(SanPhamDTO sanPham) throws Exception;
    void update(SanPhamDTO sanPham) throws Exception;
    void delete(int id) throws Exception;
    HashMap<Integer, Boolean> delete(int[] ids);

    boolean isExist(SanPhamDTO sanPham);
    int getTotalCount();
    int tinhSoLuongSP();
}

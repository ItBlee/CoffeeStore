package BUS.Interfaces;

import DTO.NhaCungCapDTO;

import java.util.ArrayList;
import java.util.HashMap;

public interface INhaCungCapBUS {
    ArrayList<NhaCungCapDTO> findAll();
    NhaCungCapDTO findByID(int id);
    ArrayList<NhaCungCapDTO> findByTenNCC(String tenNCC);
    ArrayList<NhaCungCapDTO> findBySDT(String sdt);
    ArrayList<NhaCungCapDTO> findByDiaChi(String diaChi);
    ArrayList<NhaCungCapDTO> findBySoTaiKhoan(String stk);
    ArrayList<NhaCungCapDTO> findByTinhTrang(Integer tinhTrang);

    void save(NhaCungCapDTO nhaCungCap) throws Exception;
    void update(NhaCungCapDTO nhaCungCap) throws Exception;
    void delete(int id) throws Exception;
    HashMap<Integer, Boolean> delete(int[] ids);

    boolean isExist(NhaCungCapDTO nhaCungCap);
    int getTotalCount();
}

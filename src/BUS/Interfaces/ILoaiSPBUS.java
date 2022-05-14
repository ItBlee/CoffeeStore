package BUS.Interfaces;

import DTO.LoaiSPDTO;

import java.util.ArrayList;
import java.util.HashMap;

public interface ILoaiSPBUS {
    ArrayList<LoaiSPDTO> findAll();
    LoaiSPDTO findByID(int id);
    ArrayList<LoaiSPDTO> findByTenLoai(String tenLoai);

    void save(LoaiSPDTO loaiSP) throws Exception;
    void update(LoaiSPDTO loaiSP) throws Exception;
    void delete(int id) throws Exception;
    HashMap<Integer, Boolean> delete(int[] ids);

    boolean isExist(LoaiSPDTO loaiSP);
    int getTotalCount();
}

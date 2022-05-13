package BUS.Interfaces;

import DTO.CT_PhanQuyenDTO;

import java.util.ArrayList;

public interface ICT_PhanQuyenBUS {
    ArrayList<CT_PhanQuyenDTO> findAll();
    CT_PhanQuyenDTO findByID(int id);

    void save(CT_PhanQuyenDTO ctPhanQuyen) throws Exception;
    void update(CT_PhanQuyenDTO ctPhanQuyen) throws Exception;
    void delete(int id) throws Exception;
    boolean isExist(CT_PhanQuyenDTO ctPhanQuyen);
}

package BUS.Interfaces;

import DTO.PhanQuyenDTO;

import java.util.ArrayList;

public interface IPhanQuyenBUS {
    ArrayList<PhanQuyenDTO> findAll();
    PhanQuyenDTO findByID(int id);

    void save(PhanQuyenDTO phanQuyen) throws Exception;
    void update(PhanQuyenDTO phanQuyen) throws Exception;
    void delete(int id) throws Exception;
    boolean isExist(PhanQuyenDTO phanQuyen);
}

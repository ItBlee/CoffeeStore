package DAO.Interfaces;

import DTO.NhanVienDTO;

import java.util.ArrayList;

public interface INhanVienDAO extends ICrudDAO<NhanVienDTO> {
    ArrayList<NhanVienDTO> findAll();
    NhanVienDTO findByID(int MaNV);
    NhanVienDTO findByMaTK(int MaTK);
}

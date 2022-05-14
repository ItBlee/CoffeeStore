package DAO.Interfaces;

import DTO.SanPhamDTO;

import java.util.ArrayList;

public interface ISanPhamDAO extends ICrudDAO<SanPhamDTO> {
    ArrayList<SanPhamDTO> findAll();
    SanPhamDTO findByID(int MaSP);
}
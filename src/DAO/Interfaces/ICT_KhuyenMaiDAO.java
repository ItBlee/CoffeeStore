package DAO.Interfaces;

import DTO.CT_KhuyenMaiDTO;

import java.util.ArrayList;

public interface ICT_KhuyenMaiDAO extends ICrudDAO<CT_KhuyenMaiDTO> {
    ArrayList<CT_KhuyenMaiDTO> findAll();
    CT_KhuyenMaiDTO findByID(int MaSP);
}
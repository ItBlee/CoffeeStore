package DAO.Interfaces;

import DTO.CT_PhieuNhapDTO;

import java.util.ArrayList;

public interface ICT_PhieuNhapDAO extends ICrudDAO<CT_PhieuNhapDTO> {
    ArrayList<CT_PhieuNhapDTO> findAll();
    CT_PhieuNhapDTO findByID(int MaSP);
}

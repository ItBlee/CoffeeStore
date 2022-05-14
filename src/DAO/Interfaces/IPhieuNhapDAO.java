package DAO.Interfaces;

import DTO.PhieuNhapDTO;

import java.util.ArrayList;

public interface IPhieuNhapDAO  extends ICrudDAO<PhieuNhapDTO> {
    ArrayList<PhieuNhapDTO> findAll();
    PhieuNhapDTO findByID(int MaSP);
}

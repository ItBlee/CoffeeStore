package DAO.Interfaces;

import DTO.PhanQuyenDTO;

import java.util.ArrayList;

public interface IPhanQuyenDAO extends ICrudDAO<PhanQuyenDTO>{
    ArrayList<PhanQuyenDTO> findAll();
    PhanQuyenDTO findByID(int MaPQ);
}

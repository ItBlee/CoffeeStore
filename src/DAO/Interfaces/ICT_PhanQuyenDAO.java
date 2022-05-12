package DAO.Interfaces;

import DTO.CT_PhanQuyenDTO;

import java.util.ArrayList;

public interface ICT_PhanQuyenDAO extends ICrudDAO<CT_PhanQuyenDTO> {
    ArrayList<CT_PhanQuyenDTO> findAll();
    CT_PhanQuyenDTO findByID(int MaCTPQ);
}

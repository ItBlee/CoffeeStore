package DAO.Interfaces;

import DTO.LoaiSPDTO;

import java.util.ArrayList;

public interface ILoaiSPDAO  extends ICrudDAO<LoaiSPDTO> {
    ArrayList<LoaiSPDTO> findAll();
    LoaiSPDTO findByID(int MaSP);
}

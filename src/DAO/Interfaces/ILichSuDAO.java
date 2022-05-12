package DAO.Interfaces;

import DTO.LichSuDTO;

import java.util.ArrayList;

public interface ILichSuDAO extends ICrudDAO<LichSuDTO> {
    ArrayList<LichSuDTO> findAll();
    LichSuDTO findByID(int MaLS);
}

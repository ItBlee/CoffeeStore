package DAO.Interfaces;

import DTO.TaiKhoanDTO;

import java.util.ArrayList;

public interface ITaiKhoanDAO extends IDAO<TaiKhoanDTO> {
    ArrayList<TaiKhoanDTO> findAll();
    TaiKhoanDTO findByID(int id);
}

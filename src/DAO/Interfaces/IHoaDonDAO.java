package DAO.Interfaces;

import DTO.HoaDonDTO;

import java.util.ArrayList;

public interface IHoaDonDAO  extends ICrudDAO<HoaDonDTO> {
    ArrayList<HoaDonDTO> findAll();
    HoaDonDTO findByID(int MaSP);
}

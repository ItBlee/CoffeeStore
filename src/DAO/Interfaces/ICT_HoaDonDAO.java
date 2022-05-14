package DAO.Interfaces;

import DTO.CT_HoaDonDTO;

import java.util.ArrayList;

public interface ICT_HoaDonDAO extends ICrudDAO<CT_HoaDonDTO> {
    ArrayList<CT_HoaDonDTO> findAll();
    CT_HoaDonDTO findByID(int MaSP);
}

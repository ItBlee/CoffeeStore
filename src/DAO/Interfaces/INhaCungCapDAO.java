package DAO.Interfaces;

import DTO.NhaCungCapDTO;

import java.util.ArrayList;

public interface INhaCungCapDAO  extends ICrudDAO<NhaCungCapDTO> {
    ArrayList<NhaCungCapDTO> findAll();
    NhaCungCapDTO findByID(int MaSP);
}

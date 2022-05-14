package DAO.Interfaces;

import DTO.KhuyenMaiDTO;

import java.util.ArrayList;

public interface IKhuyenMaiDAO  extends ICrudDAO<KhuyenMaiDTO> {
    ArrayList<KhuyenMaiDTO> findAll();
    KhuyenMaiDTO findByID(int MaSP);
}

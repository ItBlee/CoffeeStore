package DAO.Interfaces;

import DTO.TaiKhoanDTO;

import java.util.List;

public interface ITaiKhoanDAO extends IDAO<TaiKhoanDTO>{
    List<TaiKhoanDTO> findAll();
    TaiKhoanDTO findByID(int id);
    TaiKhoanDTO findByTenDangNhapAndMatKhauAndTinhTrang(String TenDangNhap, String MatKhau, int TinhTrang);
}

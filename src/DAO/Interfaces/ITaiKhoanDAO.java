package DAO.Interfaces;

import DTO.TaiKhoanDTO;

public interface ITaiKhoanDAO extends IDAO<TaiKhoanDTO> {
    boolean isAdmin();
}

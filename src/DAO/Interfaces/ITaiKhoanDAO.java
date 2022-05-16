package DAO.Interfaces;

import DAO.Interfaces.common.ICrudDAO;
import DAO.Interfaces.common.ISearchableDAO;
import DTO.TaiKhoanDTO;

public interface ITaiKhoanDAO extends ISearchableDAO<TaiKhoanDTO>, ICrudDAO<TaiKhoanDTO> {
}

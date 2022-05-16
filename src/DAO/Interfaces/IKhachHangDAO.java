package DAO.Interfaces;

import DAO.Interfaces.common.ICrudDAO;
import DAO.Interfaces.common.ISearchableDAO;
import DTO.KhachHangDTO;

public interface IKhachHangDAO extends ISearchableDAO<KhachHangDTO>, ICrudDAO<KhachHangDTO> {
}

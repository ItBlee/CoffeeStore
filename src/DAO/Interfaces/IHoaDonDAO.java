package DAO.Interfaces;

import DAO.Interfaces.common.ICrudDAO;
import DAO.Interfaces.common.ISearchableDAO;
import DTO.HoaDonDTO;

public interface IHoaDonDAO extends ISearchableDAO<HoaDonDTO>, ICrudDAO<HoaDonDTO> {
}

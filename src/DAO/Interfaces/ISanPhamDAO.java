package DAO.Interfaces;

import DAO.Interfaces.common.ICrudDAO;
import DAO.Interfaces.common.ISearchableDAO;
import DTO.SanPhamDTO;

public interface ISanPhamDAO extends ISearchableDAO<SanPhamDTO>, ICrudDAO<SanPhamDTO> {
}
package DAO.Interfaces;

import DAO.Interfaces.common.ICrudDAO;
import DAO.Interfaces.common.ISearchableDAO;
import DTO.NhanVienDTO;

public interface INhanVienDAO extends ISearchableDAO<NhanVienDTO>, ICrudDAO<NhanVienDTO> {
}

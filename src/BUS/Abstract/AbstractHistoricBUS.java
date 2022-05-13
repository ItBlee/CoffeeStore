package BUS.Abstract;

import DAO.Interfaces.ILichSuDAO;
import DAO.LichSuDAO;
import DTO.LichSuDTO;
import Utils.General;

public abstract class AbstractHistoricBUS {
    protected static final String SAVE_FLAG = "thêm";
    protected static final String UPDATE_FLAG = "sửa";
    protected static final String DELETE_FLAG = "xóa";

    private ILichSuDAO lichSuDAO;

    protected AbstractHistoricBUS() {
        lichSuDAO = new LichSuDAO();
    }

    protected void saveHistory(Object entity, int MaDoiTuong, String actionFlag) {
        LichSuDTO dto = new LichSuDTO();
        dto.setTenDoiTuong(entity.getClass().getSimpleName().replace("DTO", ""));
        dto.setMaDoiTuong(MaDoiTuong);
        dto.setNguoiThucHien(General.CURRENT_USER.getMaNV());
        dto.setThaoTac(actionFlag);
        lichSuDAO.save(dto);
    }
}

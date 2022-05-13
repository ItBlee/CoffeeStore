package BUS.Abstract;

import BUS.LichSuBUS;
import DTO.Interface.IEntity;
import DTO.LichSuDTO;
import Utils.General;

public abstract class AbstractHistoricBUS {
    public static final String SAVE_FLAG = "thêm";
    public static final String UPDATE_FLAG = "sửa";
    public static final String DELETE_FLAG = "xóa";

    protected AbstractHistoricBUS() {
    }

    protected void save(IEntity entity) throws Exception {
        saveHistory(entity.getClass(), entity.getID(), SAVE_FLAG);
    }

    protected void update(IEntity entity) throws Exception {
        saveHistory(entity.getClass(), entity.getID(), UPDATE_FLAG);
    }

    protected void delete(Class<?> clazz, int id) throws Exception{
        saveHistory(clazz, id, DELETE_FLAG);
    }

    private void saveHistory(Class<?> clazz, int MaDoiTuong, String actionFlag) throws Exception {
        LichSuDTO dto = new LichSuDTO();
        dto.setTenDoiTuong(clazz.getSimpleName().replace("DTO", ""));
        dto.setMaDoiTuong(MaDoiTuong);
        dto.setNguoiThucHien(General.CURRENT_USER.getMaNV());
        dto.setThaoTac(actionFlag);
        LichSuBUS bus = new LichSuBUS();
        bus.save(dto);
    }
}

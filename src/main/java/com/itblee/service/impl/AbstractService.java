package com.itblee.service.impl;

import com.itblee.General;
import com.itblee.Provider;
import com.itblee.dto.BaseEntity;
import com.itblee.dto.LichSu;
import com.itblee.service.LichSuService;

public abstract class AbstractService {
    public static final String SAVE_FLAG = "Thêm";
    public static final String UPDATE_FLAG = "Sửa";
    public static final String DELETE_FLAG = "Xóa";

    private final LichSuService lichSuService = Provider.get(LichSuService.class);

    protected void save(BaseEntity entity) throws Exception {
        record(entity.getClass(), entity.getID(), SAVE_FLAG);
    }

    protected void update(BaseEntity entity) throws Exception {
        record(entity.getClass(), entity.getID(), UPDATE_FLAG);
    }

    protected void delete(Class<?> clazz, int id) throws Exception{
        record(clazz, id, DELETE_FLAG);
    }

    private void record(Class<?> clazz, int MaDoiTuong, String actionFlag) throws Exception {
        LichSu dto = new LichSu();
        dto.setTenDoiTuong(clazz.getSimpleName().replace("DTO", ""));
        dto.setMaDoiTuong(MaDoiTuong);
        dto.setNguoiThucHien(General.user.getMaNV());
        dto.setThaoTac(actionFlag);
        lichSuService.save(dto);
    }
}

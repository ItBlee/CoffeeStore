package com.itblee.service;

import com.itblee.dto.LichSu;

import java.sql.Timestamp;
import java.util.List;

public interface LichSuService extends GenericService<LichSu> {
    List<LichSu> findAll();
    LichSu findByID(int id);
    List<LichSu> findByTenDoiTuong(String tenDoiTuong);
    List<LichSu> findByTenDoiTuongAndMaDoiTuong(String tenDoiTuong, Integer maDoiTuong);
    List<LichSu> findByThoiGian(Timestamp tuNgay, Timestamp denNgay);
    List<LichSu> findByNguoiThucHien(Integer nguoiThucHien);
    List<LichSu> findByThaoTac(String thaoTac);

    Integer save(LichSu taikhoan) throws Exception;
    boolean isExist(LichSu taikhoan);
}

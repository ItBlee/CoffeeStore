package com.itblee.service;

import com.itblee.dto.KhuyenMai;

import java.sql.Date;
import java.util.List;

public interface KhuyenMaiService extends GenericService<KhuyenMai> {
    List<KhuyenMai> findByTieuDe(String tieuDe);
    List<KhuyenMai> findByThoiGian(Date tuNgay, Date denNgay);
}

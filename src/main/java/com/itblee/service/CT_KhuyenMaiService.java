package com.itblee.service;

import com.itblee.dto.CT_KhuyenMai;

import java.util.List;

public interface CT_KhuyenMaiService extends GenericService<CT_KhuyenMai> {
    List<CT_KhuyenMai> findByMaKM(Integer MaKM);
    CT_KhuyenMai findByMaSP(Integer MaSP);
}

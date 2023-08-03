package com.itblee.service;

import com.itblee.dto.CT_HoaDon;

import java.util.List;

public interface CT_HoaDonService extends GenericService<CT_HoaDon> {
    List<CT_HoaDon> findByMaHD(Integer MaHD);
    List<CT_HoaDon> findByMaSP(Integer MaSP);
}

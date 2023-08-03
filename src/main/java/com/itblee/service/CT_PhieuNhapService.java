package com.itblee.service;

import com.itblee.dto.CT_PhieuNhap;

import java.util.List;

public interface CT_PhieuNhapService extends GenericService<CT_PhieuNhap> {
    List<CT_PhieuNhap> findByMaPN(Integer MaPN);
    List<CT_PhieuNhap> findByMaSP(Integer MaSP);
}

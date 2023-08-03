package com.itblee.service;

import com.itblee.dto.LoaiSP;

public interface LoaiSPService extends GenericService<LoaiSP> {
    LoaiSP findByTenLoai(String tenLoai);
}

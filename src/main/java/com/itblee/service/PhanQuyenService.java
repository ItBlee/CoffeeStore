package com.itblee.service;

import com.itblee.dto.PhanQuyen;

public interface PhanQuyenService extends GenericService<PhanQuyen> {
    PhanQuyen findByTenPhanQuyen(String tenPQ);
}

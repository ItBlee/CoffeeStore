package com.itblee.mapper.search;

import com.itblee.Provider;
import com.itblee.dto.BaseEntity;
import com.itblee.service.TaiKhoanService;
import com.itblee.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TaiKhoanSearchMapper implements SearchMapper {

    private final TaiKhoanService taiKhoanService = Provider.get(TaiKhoanService.class);

    @Override
    public List<BaseEntity> searchAll() {
        return new ArrayList<>(taiKhoanService.findAll());
    }

    @Override
    public List<BaseEntity> searchByIndex(Integer index, String value) {
        List<BaseEntity> result = new ArrayList<>();
        try {
            switch (index) {
                case 0:
                    result.add(taiKhoanService.findByID(Integer.parseInt(value)));
                    return result;

                case 1:
                    return new ArrayList<>(taiKhoanService.findByTenDangNhap(value));

                case 2:
                    try {
                        return new ArrayList<>(taiKhoanService.findByPhanQuyen(Integer.valueOf(value)));
                    } catch (Exception ignored) {
                        return new ArrayList<>(taiKhoanService.findByPhanQuyen(value));
                    }

                case 3:
                    try {
                        result.add(taiKhoanService.findByNguoiSoHuu(Integer.valueOf(value)));
                        return result;
                    } catch (Exception ignored) {
                        result.add(taiKhoanService.findByNguoiSoHuu(value));
                        return result;
                    }

                case 4:
                    java.sql.Date sqlDate = java.sql.Date.valueOf(value);
                    return new ArrayList<>(taiKhoanService.findByNgayTao(sqlDate, sqlDate));

                case 5:
                    try {
                        return new ArrayList<>(taiKhoanService.findByNguoiTao(Integer.valueOf(value)));
                    } catch (Exception ignored) {
                        return new ArrayList<>(taiKhoanService.findByNguoiTao(value));
                    }

                case 6:
                    try {
                        return new ArrayList<>(taiKhoanService.findByTinhTrang(Integer.valueOf(value)));
                    } catch (Exception ignored) {
                        if (StringUtils.containsIgnoreCase(value, "Hoạt động"))
                            return new ArrayList<>(taiKhoanService.findByTinhTrang(1));
                        else if (StringUtils.containsIgnoreCase(value, "Vô hiệu"))
                            return new ArrayList<>(taiKhoanService.findByTinhTrang(0));
                        return new ArrayList<>();
                    }

                default:
                    return new ArrayList<>();
            }
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    @Override
    public List<BaseEntity> searchByDate(Date from, Date to) {
        java.sql.Date convertFrom = null;
        java.sql.Date convertTo = null;
        if (from != null) convertFrom = new java.sql.Date(from.getTime());
        if (to != null) convertTo = new java.sql.Date(to.getTime());
        return new ArrayList<>(taiKhoanService.findByNgayTao(convertFrom, convertTo));
    }
}

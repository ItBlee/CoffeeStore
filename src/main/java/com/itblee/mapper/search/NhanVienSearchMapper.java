package com.itblee.mapper.search;

import com.itblee.Provider;
import com.itblee.dto.BaseEntity;
import com.itblee.service.NhanVienService;
import com.itblee.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NhanVienSearchMapper implements SearchMapper {

    private final NhanVienService nhanVienService = Provider.get(NhanVienService.class);

    @Override
    public List<BaseEntity> searchAll() {
        return new ArrayList<>(nhanVienService.findAll());
    }

    @Override
    public List<BaseEntity> searchByIndex(Integer index, String value) {
        try {
            switch (index) {
                case 0:
                    List<BaseEntity> result = new ArrayList<>();
                    result.add(nhanVienService.findByID(Integer.parseInt(value)));
                    return result;

                case 1:
                    List<BaseEntity> result2 = new ArrayList<>();
                    try {
                        result2.add(nhanVienService.findByTaiKhoan(Integer.valueOf(value)));
                        return result2;
                    } catch (Exception ignored) {
                        result2.add(nhanVienService.findByTaiKhoan(value));
                        return result2;
                    }

                case 2:
                    return new ArrayList<>(nhanVienService.findByHoTen(value));

                case 3:
                    java.sql.Date sqlDate = java.sql.Date.valueOf(value);
                    return new ArrayList<>(nhanVienService.findByNgaySinh(sqlDate, sqlDate));

                case 4:
                    return new ArrayList<>(nhanVienService.findBySDT(value));

                case 5:
                    return new ArrayList<>(nhanVienService.findByEmail(value));

                case 6:
                    try {
                        return new ArrayList<>(nhanVienService.findByGioiTinh(Integer.valueOf(value)));
                    } catch (Exception ignored) {
                        if (StringUtils.containsIgnoreCase(value, "Nam"))
                            return new ArrayList<>(nhanVienService.findByGioiTinh(1));
                        else if (StringUtils.containsIgnoreCase(value, "Nữ"))
                            return new ArrayList<>(nhanVienService.findByGioiTinh(0));
                        return new ArrayList<>();
                    }

                case 7:
                    return new ArrayList<>(nhanVienService.findByLuong(Integer.valueOf(value)));

                case 8:
                    try {
                        return new ArrayList<>(nhanVienService.findByTinhTrang(Integer.valueOf(value)));
                    } catch (Exception ignored) {
                        if (StringUtils.containsIgnoreCase(value, "Hoạt động"))
                            return new ArrayList<>(nhanVienService.findByTinhTrang(1));
                        else if (StringUtils.containsIgnoreCase(value, "Vô hiệu"))
                            return new ArrayList<>(nhanVienService.findByTinhTrang(0));
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
        return new ArrayList<>(nhanVienService.findByNgaySinh(convertFrom, convertTo));
    }
}

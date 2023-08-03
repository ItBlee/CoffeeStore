package com.itblee.mapper.search;

import com.itblee.Provider;
import com.itblee.dto.BaseEntity;
import com.itblee.service.PhieuNhapService;
import com.itblee.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PhieuNhapSearchMapper implements SearchMapper {

    private final PhieuNhapService phieuNhapService = Provider.get(PhieuNhapService.class);

    @Override
    public List<BaseEntity> searchAll() {
        return new ArrayList<>(phieuNhapService.findAll());
    }

    @Override
    public List<BaseEntity> searchByIndex(Integer index, String value) {
        try {
            switch (index) {
                case 0:
                    List<BaseEntity> result = new ArrayList<>();
                    result.add(phieuNhapService.findByID(Integer.parseInt(value)));
                    return result;

                case 1:
                    try {
                        return new ArrayList<>(phieuNhapService.findByNCC(Integer.valueOf(value)));
                    } catch (Exception ignored) {
                        return new ArrayList<>(phieuNhapService.findByNCC(value));
                    }

                case 2:
                    try {
                        return new ArrayList<>(phieuNhapService.findByNhanVien(Integer.valueOf(value)));
                    } catch (Exception ignored) {
                        return new ArrayList<>(phieuNhapService.findByNhanVien(value));
                    }

                case 3:
                    java.sql.Date sqlDate = java.sql.Date.valueOf(value);
                    return new ArrayList<>(phieuNhapService.findByNgayTao(sqlDate, sqlDate));

                case 4:
                    return new ArrayList<>(phieuNhapService.findByTongTien(Integer.valueOf(value)));

                case 5:
                    try {
                        return new ArrayList<>(phieuNhapService.findByTinhTrang(Integer.valueOf(value)));
                    } catch (Exception ignored) {
                        if (StringUtils.containsIgnoreCase(value, "Hoạt động"))
                            return new ArrayList<>(phieuNhapService.findByTinhTrang(1));
                        else if (StringUtils.containsIgnoreCase(value, "Vô hiệu"))
                            return new ArrayList<>(phieuNhapService.findByTinhTrang(0));
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
        return new ArrayList<>(phieuNhapService.findByNgayTao(convertFrom, convertTo));
    }
}

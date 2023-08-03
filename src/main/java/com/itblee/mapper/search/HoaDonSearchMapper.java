package com.itblee.mapper.search;

import com.itblee.Provider;
import com.itblee.dto.BaseEntity;
import com.itblee.service.HoaDonService;
import com.itblee.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HoaDonSearchMapper implements SearchMapper {

    private final HoaDonService hoaDonService = Provider.get(HoaDonService.class);

    @Override
    public List<BaseEntity> searchAll() {
        return new ArrayList<>(hoaDonService.findAll());
    }

    @Override
    public List<BaseEntity> searchByIndex(Integer index, String value) {

        try {
            switch (index) {
                case 0:
                    List<BaseEntity> result = new ArrayList<>();
                    result.add(hoaDonService.findByID(Integer.parseInt(value)));
                    return result;

                case 1:
                    try {
                        return new ArrayList<>(hoaDonService.findByKhachHang(Integer.valueOf(value)));
                    } catch (Exception ignored) {
                        return new ArrayList<>(hoaDonService.findByKhachHang(value));
                    }

                case 2:
                    try {
                        return new ArrayList<>(hoaDonService.findByNhanVien(Integer.valueOf(value)));
                    } catch (Exception ignored) {
                        return new ArrayList<>(hoaDonService.findByNhanVien(value));
                    }

                case 3:
                    java.sql.Date sqlDate = java.sql.Date.valueOf(value);
                    return new ArrayList<>(hoaDonService.findByNgayLap(sqlDate, sqlDate));

                case 4:
                    return new ArrayList<>(hoaDonService.findByTongTien(Integer.valueOf(value)));

                case 5:
                    return new ArrayList<>(hoaDonService.findByTienKhuyenMai(Integer.valueOf(value)));

                case 6:
                    try {
                        return new ArrayList<>(hoaDonService.findByTinhTrang(Integer.valueOf(value)));
                    } catch (Exception ignored) {
                        if (StringUtils.containsIgnoreCase(value, "Hoạt động"))
                            return new ArrayList<>(hoaDonService.findByTinhTrang(1));
                        else if (StringUtils.containsIgnoreCase(value, "Vô hiệu"))
                            return new ArrayList<>(hoaDonService.findByTinhTrang(0));
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
        return new ArrayList<>(hoaDonService.findByNgayLap(convertFrom, convertTo));
    }
}

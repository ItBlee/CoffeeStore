package com.itblee.mapper.search;

import com.itblee.Provider;
import com.itblee.dto.BaseEntity;
import com.itblee.service.KhachHangService;
import com.itblee.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class KhachHangSearchMapper implements SearchMapper {

    private final KhachHangService khachHangService = Provider.get(KhachHangService.class);

    @Override
    public List<BaseEntity> searchAll() {
        return new ArrayList<>(khachHangService.findAll());
    }

    @Override
    public List<BaseEntity> searchByIndex(Integer index, String value) {
        try {
            switch (index) {
                case 0:
                    List<BaseEntity> result = new ArrayList<>();
                    result.add(khachHangService.findByID(Integer.parseInt(value)));
                    return result;

                case 1:
                    return new ArrayList<>(khachHangService.findByHoTen(value));

                case 2:
                    return new ArrayList<>(khachHangService.findBySDT(value));

                case 3:
                    return new ArrayList<>(khachHangService.findByDiaChi(value));

                case 4:
                    return new ArrayList<>(khachHangService.findByEmail(value));

                case 5:
                    try {
                        return new ArrayList<>(khachHangService.findByTinhTrang(Integer.valueOf(value)));
                    } catch (Exception ignored) {
                        if (StringUtils.containsIgnoreCase(value, "Hoạt động"))
                            return new ArrayList<>(khachHangService.findByTinhTrang(1));
                        else if (StringUtils.containsIgnoreCase(value, "Vô hiệu"))
                            return new ArrayList<>(khachHangService.findByTinhTrang(0));
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
        return new ArrayList<>(khachHangService.findAll());
    }
}

package com.itblee.mapper.search;

import com.itblee.Provider;
import com.itblee.dto.BaseEntity;
import com.itblee.service.SanPhamService;
import com.itblee.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SanPhamSearchMapper implements SearchMapper {

    private final SanPhamService sanPhamService = Provider.get(SanPhamService.class);

    @Override
    public List<BaseEntity> searchAll() {
        return new ArrayList<>(sanPhamService.findAll());
    }

    @Override
    public List<BaseEntity> searchByIndex(Integer index, String value) {
        List<BaseEntity> result = new ArrayList<>();
        try {
            switch (index) {
                case 0:
                    result.add(sanPhamService.findByID(Integer.parseInt(value)));
                    return result;

                case 1:
                    return new ArrayList<>(sanPhamService.findByTenSP(value));

                case 2:
                    try {
                        return new ArrayList<>(sanPhamService.findByLoaiSP(Integer.valueOf(value)));
                    } catch (Exception ignored) {
                        return new ArrayList<>(sanPhamService.findByLoaiSP(value));
                    }

                case 3:
                    try {
                        return new ArrayList<>(sanPhamService.findByNhaCungCap(Integer.valueOf(value)));
                    } catch (Exception ignored) {
                        return new ArrayList<>(sanPhamService.findByNhaCungCap(value));
                    }

                case 4:
                    return new ArrayList<>(sanPhamService.findByDonGia(Integer.valueOf(value)));

                case 5:
                    return new ArrayList<>(sanPhamService.findByDonVi(value));

                case 6:
                    return new ArrayList<>(sanPhamService.findBySoLuong(Integer.valueOf(value)));

                case 7:
                    try {
                        return new ArrayList<>(sanPhamService.findByTinhTrang(Integer.valueOf(value)));
                    } catch (Exception ignored) {
                        if (StringUtils.containsIgnoreCase(value, "Hoạt động"))
                            return new ArrayList<>(sanPhamService.findByTinhTrang(1));
                        else if (StringUtils.containsIgnoreCase(value, "Vô hiệu"))
                            return new ArrayList<>(sanPhamService.findByTinhTrang(0));
                        return new ArrayList<>();
                    }

                default:
                    return new ArrayList<>();
            }
        } catch (Exception e) {
            return result;
        }
    }

    @Override
    public List<BaseEntity> searchByDate(Date from, Date to) {
        return new ArrayList<>(sanPhamService.findAll());
    }
}

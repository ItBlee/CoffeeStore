package com.itblee.mapper.search;

import com.itblee.Provider;
import com.itblee.dto.BaseEntity;
import com.itblee.service.NhaCungCapService;
import com.itblee.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NhaCungCapSearchMapper implements SearchMapper {

    private final NhaCungCapService nhaCungCapService = Provider.get(NhaCungCapService.class);

    @Override
    public List<BaseEntity> searchAll() {
        return new ArrayList<>(nhaCungCapService.findAll());
    }

    @Override
    public List<BaseEntity> searchByIndex(Integer index, String value) {
        try {
            switch (index) {
                case 0:
                    List<BaseEntity> result = new ArrayList<>();
                    result.add(nhaCungCapService.findByID(Integer.parseInt(value)));
                    return result;

                case 1:
                    return new ArrayList<>(nhaCungCapService.findByTenNCC(value));

                case 2:
                    return new ArrayList<>(nhaCungCapService.findBySDT(value));

                case 3:
                    return new ArrayList<>(nhaCungCapService.findByDiaChi(value));

                case 4:
                    return new ArrayList<>(nhaCungCapService.findBySoTaiKhoan(value));

                case 5:
                    try {
                        return new ArrayList<>(nhaCungCapService.findByTinhTrang(Integer.valueOf(value)));
                    } catch (Exception ignored) {
                        if (StringUtils.containsIgnoreCase(value, "Hoạt động"))
                            return new ArrayList<>(nhaCungCapService.findByTinhTrang(1));
                        else if (StringUtils.containsIgnoreCase(value, "Vô hiệu"))
                            return new ArrayList<>(nhaCungCapService.findByTinhTrang(0));
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
        return new ArrayList<>(nhaCungCapService.findAll());
    }
}

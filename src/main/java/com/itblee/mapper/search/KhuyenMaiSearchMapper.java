package com.itblee.mapper.search;

import com.itblee.Provider;
import com.itblee.dto.BaseEntity;
import com.itblee.service.KhuyenMaiService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class KhuyenMaiSearchMapper implements SearchMapper {

    private final KhuyenMaiService khuyenMaiService = Provider.get(KhuyenMaiService.class);

    @Override
    public List<BaseEntity> searchAll() {
        return new ArrayList<>(khuyenMaiService.findAll());
    }

    @Override
    public List<BaseEntity> searchByIndex(Integer index, String value) {
        try {
            switch (index) {
                case 0:
                    List<BaseEntity> result = new ArrayList<>();
                    result.add(khuyenMaiService.findByID(Integer.parseInt(value)));
                    return result;

                case 1:
                    return new ArrayList<>(khuyenMaiService.findByTieuDe(value));

                case 2:

                case 3:
                    java.sql.Date sqlDate = java.sql.Date.valueOf(value);
                    return new ArrayList<>(khuyenMaiService.findByThoiGian(sqlDate, sqlDate));

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
        return new ArrayList<>(khuyenMaiService.findByThoiGian(convertFrom, convertTo));
    }
}

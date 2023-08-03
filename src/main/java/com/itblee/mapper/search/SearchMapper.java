package com.itblee.mapper.search;

import com.itblee.dto.BaseEntity;

import java.util.Date;
import java.util.List;

public interface SearchMapper {
    List<BaseEntity> searchAll();
    List<BaseEntity> searchByIndex(Integer index, String value);
    List<BaseEntity> searchByDate(Date from, Date to);
}

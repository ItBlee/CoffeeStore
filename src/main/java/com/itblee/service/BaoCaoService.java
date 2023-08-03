package com.itblee.service;

import com.itblee.dto.BaseEntity;
import com.itblee.dto.BaseEntityDetail;

import java.util.List;

public interface BaoCaoService {
    boolean export(String path, BaseEntity reportInstance, List<BaseEntityDetail> list);
}

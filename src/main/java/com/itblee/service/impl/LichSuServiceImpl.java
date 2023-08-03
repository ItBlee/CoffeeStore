package com.itblee.service.impl;

import com.google.auto.service.AutoService;
import com.itblee.Provider;
import com.itblee.dao.LichSuDAO;
import com.itblee.dto.LichSu;
import com.itblee.service.LichSuService;
import com.itblee.util.StringUtils;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@AutoService(LichSuService.class)
public class LichSuServiceImpl implements LichSuService {
    private List<LichSu> listLichSu;

    private final LichSuDAO lichSuDAO = Provider.get(LichSuDAO.class);

    public LichSuServiceImpl() {
        listLichSu = findAll();
    }

    @Override
    public List<LichSu> findAll() {
        if (listLichSu == null || listLichSu.isEmpty())
            listLichSu = lichSuDAO.findAll();
        Collections.sort(listLichSu);
        return listLichSu;
    }

    @Override
    public LichSu findByID(int id) {
        for (LichSu lichSu : listLichSu)
            if (lichSu.getID() == id)
                return lichSu;
        return null;
    }

    @Override
    public List<LichSu> findByTenDoiTuong(String tenDoiTuong) {
        List<LichSu> result = new ArrayList<>();
        for (LichSu lichSu : listLichSu)
            if (StringUtils.containsIgnoreCase(lichSu.getTenDoiTuong(), tenDoiTuong))
                result.add(lichSu);
        return result;
    }

    @Override
    public List<LichSu> findByTenDoiTuongAndMaDoiTuong(String tenDoiTuong, Integer maDoiTuong) {
        List<LichSu> result = new ArrayList<>();
        for (LichSu lichSu : listLichSu)
            if (lichSu.getMaDoiTuong().equals(maDoiTuong)
                && StringUtils.containsIgnoreCase(lichSu.getTenDoiTuong(), tenDoiTuong))
                result.add(lichSu);
        return result;
    }

    @Override
    public List<LichSu> findByThoiGian(Timestamp tuNgay, Timestamp denNgay) {
        List<LichSu> result = new ArrayList<>();
        for (LichSu lichSu : listLichSu)
            if ((lichSu.getThoiGian().after(tuNgay) && lichSu.getThoiGian().before(denNgay))
                || (tuNgay.equals(denNgay) && tuNgay.equals(lichSu.getThoiGian())))
                result.add(lichSu);
        return result;
    }

    @Override
    public List<LichSu> findByNguoiThucHien(Integer nguoiThucHien) {
        List<LichSu> result = new ArrayList<>();
        for (LichSu lichSu : listLichSu)
            if (lichSu.getNguoiThucHien().equals(nguoiThucHien))
                result.add(lichSu);
        return result;
    }

    @Override
    public List<LichSu> findByThaoTac(String thaoTac) {
        List<LichSu> result = new ArrayList<>();
        for (LichSu lichSu : listLichSu)
            if (StringUtils.containsIgnoreCase(lichSu.getThaoTac(), thaoTac))
                result.add(lichSu);
        return result;
    }

    @Override
    public Integer save(LichSu lichSu) throws Exception {
        if (isExist(lichSu))
            throw new Exception("Đã tồn tại lịch sử này.");
        Integer newID = lichSuDAO.save(lichSu);
        if (newID == null)
            throw new Exception("Phát sinh lỗi trong quá trình thêm lịch sử.");
        listLichSu.add(lichSuDAO.findByID(newID));
        return newID;
    }

    @Override
    public void update(LichSu entity) throws Exception {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(int id) throws Exception {
        throw new UnsupportedOperationException();
    }

    @Override
    public Map<Integer, Boolean> delete(int[] ids) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isExist(LichSu lichSu) {
        if (lichSu.getMaLS() == null)
            return false;
        return listLichSu.contains(lichSu);
    }

    @Override
    public int getTotalCount() {
        return listLichSu.size();
    }
}

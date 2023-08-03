package com.itblee.service.impl;

import com.google.auto.service.AutoService;
import com.itblee.Provider;
import com.itblee.dao.CT_PhanQuyenDAO;
import com.itblee.dto.CT_PhanQuyen;
import com.itblee.service.CT_PhanQuyenService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AutoService(CT_PhanQuyenService.class)
public class CT_PhanQuyenServiceImpl implements CT_PhanQuyenService {
    private List<CT_PhanQuyen> listCTPhanQuyen;

    private final CT_PhanQuyenDAO ctPhanQuyenDAO = Provider.get(CT_PhanQuyenDAO.class);

    public CT_PhanQuyenServiceImpl() {
        listCTPhanQuyen = findAll();
    }

    @Override
    public List<CT_PhanQuyen> findAll() {
        if (listCTPhanQuyen == null || listCTPhanQuyen.isEmpty())
            listCTPhanQuyen = ctPhanQuyenDAO.findAll();
        return listCTPhanQuyen;
    }

    @Override
    public CT_PhanQuyen findByID(int id) {
        for (CT_PhanQuyen CT_PhanQuyen : listCTPhanQuyen)
            if (CT_PhanQuyen.getID() == id)
                return CT_PhanQuyen;
        return null;
    }

    @Override
    public Integer save(CT_PhanQuyen ctPhanQuyen) throws Exception {
        if (isExist(ctPhanQuyen))
            throw new Exception("Đã tồn tại chi tiết quyền này.");
        Integer newID = ctPhanQuyenDAO.save(ctPhanQuyen);
        if (newID == null)
            throw new Exception("Phát sinh lỗi trong quá trình thêm chi tiết quyền.");
        ctPhanQuyen = ctPhanQuyenDAO.findByID(newID);
        listCTPhanQuyen.add(ctPhanQuyen);
        return newID;
    }

    @Override
    public void update(CT_PhanQuyen ctPhanQuyen) throws Exception {
        if (!isExist(ctPhanQuyen))
            throw new Exception("Không tồn tại chi tiết quyền (CTPQ" + ctPhanQuyen.getMaCTPQ() + ").");
        if (!ctPhanQuyenDAO.update(ctPhanQuyen))
            throw new Exception("Phát sinh lỗi trong quá trình thêm chi tiết quyền.");
        ctPhanQuyen = ctPhanQuyenDAO.findByID(ctPhanQuyen.getMaCTPQ());
        for (int i = 0; i < listCTPhanQuyen.size(); i++) {
            if (listCTPhanQuyen.get(i).getMaCTPQ().equals(ctPhanQuyen.getMaCTPQ()))
                listCTPhanQuyen.set(i, ctPhanQuyen);
        }
    }

    @Override
    public void delete(int id) throws Exception {
        if (!ctPhanQuyenDAO.delete(id))
            throw new Exception("Không thể xóa chi tiết quyền (CTPQ" + id + ").");
        listCTPhanQuyen.removeIf(CT_PhanQuyenDTO -> CT_PhanQuyenDTO.getMaCTPQ() == id);
    }

    @Override
    public Map<Integer, Boolean> delete(int[] ids) {
        Map<Integer, Boolean> report = new HashMap<>();
        boolean resultExecute;
        for (int id:ids) {
            try {
                delete(id);
                resultExecute = true;
            } catch (Exception e) {
                e.printStackTrace();
                resultExecute = false;
            }
            report.put(id, resultExecute);
        }
        return report;
    }

    @Override
    public boolean isExist(CT_PhanQuyen ctPhanQuyen) {
        if (ctPhanQuyen.getMaCTPQ() == null)
            return false;
        return listCTPhanQuyen.contains(ctPhanQuyen);
    }

    @Override
    public int getTotalCount() {
        return listCTPhanQuyen.size();
    }
}

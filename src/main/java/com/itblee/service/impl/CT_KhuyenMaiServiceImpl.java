package com.itblee.service.impl;

import com.google.auto.service.AutoService;
import com.itblee.Provider;
import com.itblee.dao.CT_KhuyenMaiDAO;
import com.itblee.dto.CT_KhuyenMai;
import com.itblee.service.CT_KhuyenMaiService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AutoService(CT_KhuyenMaiService.class)
public class CT_KhuyenMaiServiceImpl implements CT_KhuyenMaiService {
    private List<CT_KhuyenMai> listCTKhuyenMai;

    private final CT_KhuyenMaiDAO ctKhuyenMaiDAO = Provider.get(CT_KhuyenMaiDAO.class);

    public CT_KhuyenMaiServiceImpl() {
        listCTKhuyenMai = findAll();
    }

    @Override
    public List<CT_KhuyenMai> findAll() {
        if (listCTKhuyenMai == null || listCTKhuyenMai.isEmpty())
            listCTKhuyenMai = ctKhuyenMaiDAO.findAll();
        return listCTKhuyenMai;
    }

    @Override
    public CT_KhuyenMai findByID(int id) {
        for (CT_KhuyenMai ctKhuyenMaiDTO : listCTKhuyenMai)
            if (ctKhuyenMaiDTO.getID() == id)
                return ctKhuyenMaiDTO;
        return null;
    }

    @Override
    public List<CT_KhuyenMai> findByMaKM(Integer MaKM) {
        List<CT_KhuyenMai> result = new ArrayList<>();
        for (CT_KhuyenMai ctKhuyenMaiDTO : listCTKhuyenMai)
            if (ctKhuyenMaiDTO.getMaKM().equals(MaKM))
                result.add(ctKhuyenMaiDTO);
        return result;
    }

    @Override
    public CT_KhuyenMai findByMaSP(Integer MaSP) {
        for (CT_KhuyenMai ctKhuyenMaiDTO : listCTKhuyenMai)
            if (ctKhuyenMaiDTO.getMaSP().equals(MaSP))
                return ctKhuyenMaiDTO;
        return null;
    }

    @Override
    public Integer save(CT_KhuyenMai ctKhuyenMai) throws Exception {
        if (isExist(ctKhuyenMai))
            throw new Exception("Đã tồn tại chi tiết khuyến mãi này.");
        for (CT_KhuyenMai dto:listCTKhuyenMai) {
            if (dto.getMaSP().equals(ctKhuyenMai.getMaSP()))
                throw new Exception("Sản phẩm này đã được khuyến mãi.");
        }
        Integer newID = ctKhuyenMaiDAO.save(ctKhuyenMai);
        if (newID == null)
            throw new Exception("Phát sinh lỗi trong quá trình thêm chi tiết khuyến mãi.");
        ctKhuyenMai = ctKhuyenMaiDAO.findByID(newID);
        listCTKhuyenMai.add(ctKhuyenMai);
        return newID;
    }

    @Override
    public void update(CT_KhuyenMai ctKhuyenMai) throws Exception {
        if (!isExist(ctKhuyenMai))
            throw new Exception("Không tồn tại chi tiết khuyến mãi (CTKM" + ctKhuyenMai.getMaCTKM() + ").");
        if (!ctKhuyenMaiDAO.update(ctKhuyenMai))
            throw new Exception("Phát sinh lỗi trong quá trình thêm chi tiết khuyến mãi.");
        ctKhuyenMai = ctKhuyenMaiDAO.findByID(ctKhuyenMai.getMaCTKM());
        for (int i = 0; i < listCTKhuyenMai.size(); i++) {
            if (listCTKhuyenMai.get(i).getMaCTKM().equals(ctKhuyenMai.getMaCTKM()))
                listCTKhuyenMai.set(i, ctKhuyenMai);
        }
    }

    @Override
    public void delete(int id) throws Exception {
        if (!ctKhuyenMaiDAO.delete(id))
            throw new Exception("Không thể xóa chi tiết khuyến mãi (CTKM" + id + ").");
        listCTKhuyenMai.removeIf(CT_KhuyenMaiDTO -> CT_KhuyenMaiDTO.getMaCTKM() == id);
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
    public boolean isExist(CT_KhuyenMai ctKhuyenMai) {
        if (ctKhuyenMai.getMaCTKM() == null)
            return false;
        return listCTKhuyenMai.contains(ctKhuyenMai);
    }

    @Override
    public int getTotalCount() {
        return listCTKhuyenMai.size();
    }
}

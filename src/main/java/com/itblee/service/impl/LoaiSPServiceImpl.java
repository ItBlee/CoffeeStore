package com.itblee.service.impl;

import com.google.auto.service.AutoService;
import com.itblee.Provider;
import com.itblee.dao.LoaiSPDAO;
import com.itblee.dto.LoaiSP;
import com.itblee.dto.SanPham;
import com.itblee.service.LoaiSPService;
import com.itblee.service.SanPhamService;
import com.itblee.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AutoService(LoaiSPService.class)
public class LoaiSPServiceImpl extends AbstractService implements LoaiSPService {
    private List<LoaiSP> listLoaiSP;

    private final LoaiSPDAO loaiSPDAO = Provider.get(LoaiSPDAO.class);

    public LoaiSPServiceImpl() {
        listLoaiSP = findAll();
    }

    @Override
    public List<LoaiSP> findAll() {
        if (listLoaiSP == null || listLoaiSP.isEmpty())
            listLoaiSP = loaiSPDAO.findAll();
        return listLoaiSP;
    }

    @Override
    public LoaiSP findByID(int id) {
        for (LoaiSP loaiSP : listLoaiSP)
            if (loaiSP.getID() == id)
                return loaiSP;
        return null;
    }

    @Override
    public LoaiSP findByTenLoai(String tenLoai) {
        for (LoaiSP loaiSP : listLoaiSP)
            if (StringUtils.containsIgnoreCase(loaiSP.getTenLoai(), tenLoai))
                return loaiSP;
        return null;
    }

    @Override
    public Integer save(LoaiSP loaiSP) throws Exception {
        if (isExist(loaiSP))
            throw new Exception("Đã tồn tại loại sản phẩm này.");
        Integer newID = loaiSPDAO.save(loaiSP);
        if (newID == null)
            throw new Exception("Phát sinh lỗi trong quá trình thêm loại sản phẩm.");
        loaiSP = loaiSPDAO.findByID(newID);
        listLoaiSP.add(loaiSP);
        super.save(loaiSP);
        return newID;
    }

    @Override
    public void update(LoaiSP loaiSP) throws Exception {
        if (!isExist(loaiSP))
            throw new Exception("Không tồn tại loại sản phẩm (LSP" + loaiSP.getMaLoai() + ").");
        if (!loaiSPDAO.update(loaiSP))
            throw new Exception("Phát sinh lỗi trong quá trình sửa loại sản phẩm.");
        loaiSP = loaiSPDAO.findByID(loaiSP.getMaLoai());
        for (int i = 0; i < listLoaiSP.size(); i++) {
            if (listLoaiSP.get(i).getMaLoai().equals(loaiSP.getMaLoai()))
                listLoaiSP.set(i, loaiSP);
        }
        super.update(loaiSP);
    }

    @Override
    public void delete(int id) throws Exception {
        SanPhamService sanPhamService = Provider.get(SanPhamService.class);
        for (SanPham dto: sanPhamService.findByLoaiSP(id)) {
            int DEFAULT_LSP_ID = 2;
            dto.setMaLoai(DEFAULT_LSP_ID);
            sanPhamService.update(dto);
        }
        if (!loaiSPDAO.delete(id))
            throw new Exception("Không thể xóa loại sản phẩm (LSP" + id + ").");
        listLoaiSP.removeIf(loaiSPDTO -> loaiSPDTO.getMaLoai() == id);
        super.delete(LoaiSP.class, id);
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
    public boolean isExist(LoaiSP loaiSP) {
        if (loaiSP.getMaLoai() == null)
            return false;
        return listLoaiSP.contains(loaiSP);
    }

    @Override
    public int getTotalCount() {
        return listLoaiSP.size();
    }
}

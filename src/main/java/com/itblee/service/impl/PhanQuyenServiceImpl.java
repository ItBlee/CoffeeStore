package com.itblee.service.impl;

import com.google.auto.service.AutoService;
import com.itblee.Provider;
import com.itblee.dao.PhanQuyenDAO;
import com.itblee.dto.PhanQuyen;
import com.itblee.dto.TaiKhoan;
import com.itblee.service.CT_PhanQuyenService;
import com.itblee.service.PhanQuyenService;
import com.itblee.service.TaiKhoanService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AutoService(PhanQuyenService.class)
public class PhanQuyenServiceImpl extends AbstractService implements PhanQuyenService {
    private List<PhanQuyen> listPhanQuyen;

    private final PhanQuyenDAO phanQuyenDAO = Provider.get(PhanQuyenDAO.class);

    public PhanQuyenServiceImpl() {
        listPhanQuyen = findAll();
    }

    @Override
    public List<PhanQuyen> findAll() {
        if (listPhanQuyen == null || listPhanQuyen.isEmpty())
            listPhanQuyen = phanQuyenDAO.findAll();
        return listPhanQuyen;
    }

    @Override
    public PhanQuyen findByID(int id) {
        for (PhanQuyen PhanQuyen : listPhanQuyen)
            if (PhanQuyen.getID() == id)
                return PhanQuyen;
        return null;
    }

    @Override
    public PhanQuyen findByTenPhanQuyen(String tenPQ) {
        for (PhanQuyen PhanQuyen : listPhanQuyen)
            if (PhanQuyen.getTenPQ().equalsIgnoreCase(tenPQ))
                return PhanQuyen;
        return null;
    }

    @Override
    public Integer save(PhanQuyen phanQuyen) throws Exception {
        if (isExist(phanQuyen))
            throw new Exception("Đã tồn tại quyền này.");
        Integer newID = phanQuyenDAO.save(phanQuyen);
        if (newID == null)
            throw new Exception("Phát sinh lỗi trong quá trình thêm quyền.");
        phanQuyen = phanQuyenDAO.findByID(newID);
        listPhanQuyen.add(phanQuyen);
        super.save(phanQuyen);
        return newID;
    }

    @Override
    public void update(PhanQuyen phanQuyen) throws Exception {
        if (!isExist(phanQuyen))
            throw new Exception("Không tồn tại quyền (PQ" + phanQuyen.getMaPQ() + ").");
        if (!phanQuyenDAO.update(phanQuyen))
            throw new Exception("Phát sinh lỗi trong quá trình thêm quyền.");
        phanQuyen = phanQuyenDAO.findByID(phanQuyen.getMaPQ());
        for (int i = 0; i < listPhanQuyen.size(); i++) {
            if (listPhanQuyen.get(i).getMaPQ().equals(phanQuyen.getMaPQ()))
                listPhanQuyen.set(i, phanQuyen);
        }
        super.update(phanQuyen);
    }

    @Override
    public void delete(int id) throws Exception {
        CT_PhanQuyenService ctPhanQuyenService = Provider.get(CT_PhanQuyenService.class);
        TaiKhoanService taiKhoanService = Provider.get(TaiKhoanService.class);
        PhanQuyen dto = findByID(id);
        if (dto == null)
            throw new Exception("Không tìm thấy quyền (PQ" + id + ").");
        for (TaiKhoan taiKhoan : taiKhoanService.findAll()) {
            if (taiKhoan.getMaPQ().equals(dto.getMaPQ())) {
                final int DEFAULT_EMPLOYEE_ROLE_ID = 2;
                taiKhoan.setMaPQ(DEFAULT_EMPLOYEE_ROLE_ID);
                taiKhoanService.update(taiKhoan);
            }
        }
        if (!phanQuyenDAO.delete(dto.getMaPQ()))
            throw new Exception("Không thể xóa quyền (PQ" + dto.getMaPQ() + ").");
        listPhanQuyen.removeIf(PhanQuyenDTO -> PhanQuyenDTO.getMaPQ().equals(dto.getMaPQ()));
        int[] deleteList = new int[9];
        deleteList[0] = dto.getQuyenHD();
        deleteList[1] = dto.getQuyenSP();
        deleteList[2] = dto.getQuyenPN();
        deleteList[3] = dto.getQuyenNCC();
        deleteList[4] = dto.getQuyenKH();
        deleteList[5] = dto.getQuyenKM();
        deleteList[6] = dto.getQuyenTK();
        deleteList[7] = dto.getQuyenExcel();
        deleteList[8] = dto.getQuyenNV();
        ctPhanQuyenService.delete(deleteList);
        super.delete(PhanQuyen.class, id);
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
    public boolean isExist(PhanQuyen phanQuyen) {
        if (phanQuyen.getMaPQ() == null)
            return false;
        return listPhanQuyen.contains(phanQuyen);
    }

    @Override
    public int getTotalCount() {
        return listPhanQuyen.size();
    }
}

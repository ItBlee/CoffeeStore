package com.itblee.service.impl;

import com.google.auto.service.AutoService;
import com.itblee.Provider;
import com.itblee.dao.NhaCungCapDAO;
import com.itblee.dto.CT_PhieuNhap;
import com.itblee.dto.NhaCungCap;
import com.itblee.dto.PhieuNhap;
import com.itblee.dto.SanPham;
import com.itblee.service.CT_PhieuNhapService;
import com.itblee.service.NhaCungCapService;
import com.itblee.service.PhieuNhapService;
import com.itblee.service.SanPhamService;
import com.itblee.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AutoService(NhaCungCapService.class)
public class NhaCungCapServiceImpl extends AbstractService implements NhaCungCapService {
    private List<NhaCungCap> listNhaCungCap;

    private final NhaCungCapDAO nhaCungCapDAO = Provider.get(NhaCungCapDAO.class);

    public NhaCungCapServiceImpl() {
        listNhaCungCap = findAll();
    }

    @Override
    public List<NhaCungCap> findAll() {
        if (listNhaCungCap == null || listNhaCungCap.isEmpty())
            listNhaCungCap = nhaCungCapDAO.findAll();
        return listNhaCungCap;
    }

    @Override
    public NhaCungCap findByID(int id) {
        for (NhaCungCap nhaCungCap : listNhaCungCap)
            if (nhaCungCap.getID() == id)
                return nhaCungCap;
        return null;
    }

    @Override
    public List<NhaCungCap> findByTenNCC(String tenNCC) {
        List<NhaCungCap> result = new ArrayList<>();
        for (NhaCungCap nhaCungCap : listNhaCungCap)
            if (StringUtils.containsIgnoreCase(nhaCungCap.getTenNCC(), tenNCC))
                result.add(nhaCungCap);
        return result;
    }

    @Override
    public List<NhaCungCap> findBySDT(String sdt) {
        List<NhaCungCap> result = new ArrayList<>();
        for (NhaCungCap nhaCungCap : listNhaCungCap)
            if (StringUtils.containsIgnoreCase(nhaCungCap.getSdt(), sdt))
                result.add(nhaCungCap);
        return result;
    }

    @Override
    public List<NhaCungCap> findByDiaChi(String diaChi) {
        List<NhaCungCap> result = new ArrayList<>();
        for (NhaCungCap nhaCungCap : listNhaCungCap)
            if (StringUtils.containsIgnoreCase(nhaCungCap.getDiaChi(), diaChi))
                result.add(nhaCungCap);
        return result;
    }

    @Override
    public List<NhaCungCap> findBySoTaiKhoan(String stk) {
        List<NhaCungCap> result = new ArrayList<>();
        for (NhaCungCap nhaCungCap : listNhaCungCap)
            if (StringUtils.containsIgnoreCase(nhaCungCap.getSoTaiKhoan(), stk))
                result.add(nhaCungCap);
        return result;
    }

    @Override
    public List<NhaCungCap> findByTinhTrang(Integer tinhTrang) {
        List<NhaCungCap> result = new ArrayList<>();
        for (NhaCungCap nhaCungCap : listNhaCungCap)
            if (nhaCungCap.getTinhTrang().equals(tinhTrang))
                result.add(nhaCungCap);
        return result;
    }

    @Override
    public Integer save(NhaCungCap nhaCungCap) throws Exception {
        if (isExist(nhaCungCap))
            throw new Exception("Đã tồn tại nhà cung cấp này.");
        Integer newID = nhaCungCapDAO.save(nhaCungCap);
        if (newID == null)
            throw new Exception("Phát sinh lỗi trong quá trình thêm nhà cung cấp.");
        nhaCungCap = nhaCungCapDAO.findByID(newID);
        listNhaCungCap.add(nhaCungCap);
        super.save(nhaCungCap);
        return newID;
    }

    @Override
    public void update(NhaCungCap nhaCungCap) throws Exception {
        if (!isExist(nhaCungCap))
            throw new Exception("Không tồn tại nhà cung cấp (NCC" + nhaCungCap.getMaNCC() + ").");
        if (!nhaCungCapDAO.update(nhaCungCap))
            throw new Exception("Phát sinh lỗi trong quá trình thêm nhà cung cấp.");
        nhaCungCap = nhaCungCapDAO.findByID(nhaCungCap.getMaNCC());
        for (int i = 0; i < listNhaCungCap.size(); i++) {
            if (listNhaCungCap.get(i).getMaNCC().equals(nhaCungCap.getMaNCC()))
                listNhaCungCap.set(i, nhaCungCap);
        }
        super.update(nhaCungCap);
    }

    @Override
    public void delete(int id) throws Exception {
        CT_PhieuNhapService ctPhieuNhapService = Provider.get(CT_PhieuNhapService.class);
        PhieuNhapService phieuNhapService = Provider.get(PhieuNhapService.class);
        SanPhamService sanPhamService = Provider.get(SanPhamService.class);
        for (PhieuNhap dto: phieuNhapService.findByNCC(id)) {
            for (CT_PhieuNhap child : ctPhieuNhapService.findByMaPN(dto.getMaPN()))
                ctPhieuNhapService.delete(child.getID());
            phieuNhapService.delete(dto.getID());
        }
        for (SanPham dto: sanPhamService.findByNhaCungCap(id)) {
            dto.setMaNCC(null);
            sanPhamService.update(dto);
        }
        if (!nhaCungCapDAO.delete(id))
            throw new Exception("Không thể xóa nhà cung cấp (NCC" + id + ").");
        listNhaCungCap.removeIf(nhaCungCapDTO -> nhaCungCapDTO.getMaNCC() == id);
        super.delete(NhaCungCap.class, id);
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
    public boolean isExist(NhaCungCap nhaCungCap) {
        if (nhaCungCap.getMaNCC() == null)
            return false;
        return listNhaCungCap.contains(nhaCungCap);
    }

    @Override
    public int getTotalCount() {
        return listNhaCungCap.size();
    }
}

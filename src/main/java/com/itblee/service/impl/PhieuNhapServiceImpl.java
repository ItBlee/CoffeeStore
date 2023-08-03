package com.itblee.service.impl;

import com.google.auto.service.AutoService;
import com.itblee.Provider;
import com.itblee.dao.PhieuNhapDAO;
import com.itblee.dto.*;
import com.itblee.service.*;
import com.itblee.util.StringUtils;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AutoService(PhieuNhapService.class)
public class PhieuNhapServiceImpl extends AbstractService implements PhieuNhapService {
    private List<PhieuNhap> listPhieuNhap;

    private final PhieuNhapDAO phieuNhapDAO = Provider.get(PhieuNhapDAO.class);

    public PhieuNhapServiceImpl() {
        listPhieuNhap = findAll();
    }

    @Override
    public List<PhieuNhap> findAll() {
        if (listPhieuNhap == null || listPhieuNhap.isEmpty())
            listPhieuNhap = phieuNhapDAO.findAll();
        return listPhieuNhap;
    }

    @Override
    public PhieuNhap findByID(int id) {
        for (PhieuNhap phieuNhap : listPhieuNhap)
            if (phieuNhap.getID() == id)
                return phieuNhap;
        return null;
    }

    @Override
    public List<PhieuNhap> findByNCC(Integer MaNCC) {
        List<PhieuNhap> result = new ArrayList<>();
        for (PhieuNhap phieuNhap : listPhieuNhap)
            if (phieuNhap.getMaNCC().equals(MaNCC))
                result.add(phieuNhap);
        return result;
    }

    @Override
    public List<PhieuNhap> findByNCC(String TenNCC) {
        NhaCungCapService nhaCungCapService = Provider.get(NhaCungCapService.class);
        for (NhaCungCap dto : nhaCungCapService.findAll()) {
            if (StringUtils.containsIgnoreCase(dto.getTenNCC(), TenNCC))
                return findByNCC(dto.getMaNCC());
        }
        return null;
    }

    @Override
    public List<PhieuNhap> findByNhanVien(Integer MaNV) {
        List<PhieuNhap> result = new ArrayList<>();
        for (PhieuNhap phieuNhap : listPhieuNhap)
            if (phieuNhap.getMaNV().equals(MaNV))
                result.add(phieuNhap);
        return result;
    }

    @Override
    public List<PhieuNhap> findByNhanVien(String TenNV) {
        NhanVienService nhanVienService = Provider.get(NhanVienService.class);
        for (NhanVien dto: nhanVienService.findAll()) {
            if (StringUtils.containsIgnoreCase(dto.getTen(), TenNV))
                return findByNhanVien(dto.getMaNV());
        }
        return null;
    }

    @Override
    public List<PhieuNhap> findByNgayTao(Date tuNgay, Date denNgay) {
        List<PhieuNhap> result = new ArrayList<>();
        for (PhieuNhap phieuNhap : listPhieuNhap) {
            if (tuNgay == null && phieuNhap.getNgayTao().before(denNgay)) {
                result.add(phieuNhap);
                continue;
            }
            if (denNgay == null && phieuNhap.getNgayTao().after(tuNgay)) {
                result.add(phieuNhap);
                continue;
            }
            if ((phieuNhap.getNgayTao().after(tuNgay) && phieuNhap.getNgayTao().before(denNgay))
                    || (tuNgay.equals(denNgay) && tuNgay.toLocalDate().getDayOfYear() == phieuNhap.getNgayTao().toLocalDateTime().toLocalDate().getDayOfYear()))
                result.add(phieuNhap);
        }
        return result;
    }

    @Override
    public List<PhieuNhap> findByTongTien(Integer tien) {
        List<PhieuNhap> result = new ArrayList<>();
        for (PhieuNhap phieuNhap : listPhieuNhap)
            if (phieuNhap.getTongTien().equals(tien))
                result.add(phieuNhap);
        return result;
    }

    @Override
    public List<PhieuNhap> findByTinhTrang(Integer tinhTrang) {
        List<PhieuNhap> result = new ArrayList<>();
        for (PhieuNhap phieuNhap : listPhieuNhap)
            if (phieuNhap.getTinhTrang().equals(tinhTrang))
                result.add(phieuNhap);
        return result;
    }

    @Override
    public Integer save(PhieuNhap phieuNhap) throws Exception {
        if (isExist(phieuNhap))
            throw new Exception("Đã tồn tại phiếu nhập này.");
        Integer newID = phieuNhapDAO.save(phieuNhap);
        if (newID == null)
            throw new Exception("Phát sinh lỗi trong quá trình thêm phiếu nhập.");
        phieuNhap = phieuNhapDAO.findByID(newID);
        listPhieuNhap.add(phieuNhap);
        super.save(phieuNhap);
        return newID;
    }

    @Override
    public void update(PhieuNhap phieuNhap) throws Exception {
        CT_PhieuNhapService ctPhieuNhapService = Provider.get(CT_PhieuNhapService.class);
        SanPhamService sanPhamService = Provider.get(SanPhamService.class);
        if (!isExist(phieuNhap))
            throw new Exception("Không tồn tại phiếu nhập (PN" + phieuNhap.getMaPN() + ").");
        int checkFlag = 0;
        if (phieuNhap.getTinhTrang() == 2) {
            phieuNhap.setTinhTrang(0);
            checkFlag = 1;
        }
        if (phieuNhap.getTinhTrang() == 3) {
            phieuNhap.setTinhTrang(1);
            checkFlag = 2;
        }
        if (!phieuNhapDAO.update(phieuNhap))
            throw new Exception("Phát sinh lỗi trong quá trình thêm phiếu nhập.");
        phieuNhap = phieuNhapDAO.findByID(phieuNhap.getMaPN());
        for (int i = 0; i < listPhieuNhap.size(); i++) {
            if (listPhieuNhap.get(i).getMaPN().equals(phieuNhap.getMaPN()))
                listPhieuNhap.set(i, phieuNhap);
        }
        if (checkFlag == 1) {
            for (CT_PhieuNhap dto: ctPhieuNhapService.findByMaPN(phieuNhap.getMaPN())) {
                SanPham sanPham = sanPhamService.findByID(dto.getMaSP());
                int newSL = sanPham.getSoLuong() - dto.getSoLuong();
                sanPham.setSoLuong(newSL);
                sanPhamService.update(sanPham);
            }
        } else if (checkFlag == 2) {
            for (CT_PhieuNhap dto: ctPhieuNhapService.findByMaPN(phieuNhap.getMaPN())) {
                SanPham sanPham = sanPhamService.findByID(dto.getMaSP());
                int newSL = sanPham.getSoLuong() + dto.getSoLuong();
                sanPham.setSoLuong(newSL);
                sanPhamService.update(sanPham);
            }
        }
        super.update(phieuNhap);
    }

    @Override
    public void delete(int id) throws Exception {
        CT_PhieuNhapService ctPhieuNhapService = Provider.get(CT_PhieuNhapService.class);
        SanPhamService sanPhamService = Provider.get(SanPhamService.class);
        Integer status = findByID(id).getTinhTrang();
        if (status == null)
            throw new Exception("Không thể xóa phiếu nhập (PN" + id + ").");
        if (status == 0) {
            for (CT_PhieuNhap dto: ctPhieuNhapService.findByMaPN(id)) {
                SanPham sanPham = sanPhamService.findByID(dto.getMaSP());
                int newSL = sanPham.getSoLuong() + dto.getSoLuong();
                sanPham.setSoLuong(newSL);
                sanPhamService.update(sanPham);
            }
        }
        for (CT_PhieuNhap dto: ctPhieuNhapService.findByMaPN(id))
            ctPhieuNhapService.delete(dto.getID());
        if (!phieuNhapDAO.delete(id))
            throw new Exception("Không thể xóa phiếu nhập (PN" + id + ").");
        listPhieuNhap.removeIf(phieuNhapDTO -> phieuNhapDTO.getMaPN() == id);
        super.delete(PhieuNhap.class, id);
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
    public boolean isExist(PhieuNhap phieuNhap) {
        if (phieuNhap.getMaPN() == null)
            return false;
        return listPhieuNhap.contains(phieuNhap);
    }

    @Override
    public int getTotalCount() {
        return listPhieuNhap.size();
    }
}

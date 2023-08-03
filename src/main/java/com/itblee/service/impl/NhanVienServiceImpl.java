package com.itblee.service.impl;

import com.google.auto.service.AutoService;
import com.itblee.Provider;
import com.itblee.dao.NhanVienDAO;
import com.itblee.dto.*;
import com.itblee.service.*;
import com.itblee.util.StringUtils;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AutoService(NhanVienService.class)
public class NhanVienServiceImpl extends AbstractService implements NhanVienService {
    private List<NhanVien> listNhanVien;

    private final NhanVienDAO nhanVienDAO = Provider.get(NhanVienDAO.class);

    public NhanVienServiceImpl() {
        listNhanVien = findAll();
    }

    @Override
    public List<NhanVien> findAll() {
        if (listNhanVien == null || listNhanVien.isEmpty())
            listNhanVien = nhanVienDAO.findAll();
        return listNhanVien;
    }

    @Override
    public NhanVien findByID(int id) {
        for (NhanVien NhanVien : listNhanVien)
            if (NhanVien.getID() == id)
                return NhanVien;
        return null;
    }

    @Override
    public NhanVien findByTaiKhoan(Integer maTK) {
        for (NhanVien NhanVien : listNhanVien)
            if (NhanVien.getMaTK().equals(maTK))
                return NhanVien;
        return null;
    }

    @Override
    public NhanVien findByTaiKhoan(String tenTK) {
        TaiKhoanService taiKhoanService = Provider.get(TaiKhoanService.class);
        for (TaiKhoan dto : taiKhoanService.findAll()) {
            if (StringUtils.containsIgnoreCase(dto.getTenDangNhap(), tenTK))
                return findByTaiKhoan(dto.getMaTK());
        }
        return null;
    }

    @Override
    public List<NhanVien> findByHoTen(String hoTen) {
        List<NhanVien> result = new ArrayList<>();
        for (NhanVien nhanVien : listNhanVien)
            if (StringUtils.containsIgnoreCase(nhanVien.getHoTen(), hoTen))
                result.add(nhanVien);
        return result;
    }

    @Override
    public List<NhanVien> findByNgaySinh(Date tuNgay, Date denNgay) {
        List<NhanVien> result = new ArrayList<>();
        for (NhanVien nhanVien : listNhanVien) {
            if (tuNgay == null && nhanVien.getNgaySinh().before(denNgay)) {
                result.add(nhanVien);
                continue;
            }
            if (denNgay == null && nhanVien.getNgaySinh().after(tuNgay)) {
                result.add(nhanVien);
                continue;
            }
            if ((nhanVien.getNgaySinh().after(tuNgay) && nhanVien.getNgaySinh().before(denNgay))
                    || (tuNgay.equals(denNgay) && tuNgay.toLocalDate().getDayOfYear() == nhanVien.getNgaySinh().toLocalDate().getDayOfYear()))
                result.add(nhanVien);
        }
        return result;
    }

    @Override
    public List<NhanVien> findBySDT(String sdt) {
        List<NhanVien> result = new ArrayList<>();
        for (NhanVien nhanVien : listNhanVien)
            if (StringUtils.containsIgnoreCase(nhanVien.getSdt(), sdt))
                result.add(nhanVien);
        return result;
    }

    @Override
    public List<NhanVien> findByEmail(String email) {
        List<NhanVien> result = new ArrayList<>();
        for (NhanVien nhanVien : listNhanVien)
            if (StringUtils.containsIgnoreCase(nhanVien.getEmail(), email))
                result.add(nhanVien);
        return result;
    }

    @Override
    public List<NhanVien> findByGioiTinh(Integer gioiTinh) {
        List<NhanVien> result = new ArrayList<>();
        for (NhanVien nhanVien : listNhanVien)
            if (nhanVien.getGioiTinh().equals(gioiTinh))
                result.add(nhanVien);
        return result;
    }

    @Override
    public List<NhanVien> findByLuong(Integer luong) {
        List<NhanVien> result = new ArrayList<>();
        for (NhanVien nhanVien : listNhanVien)
            if (nhanVien.getLuong().equals(luong))
                result.add(nhanVien);
        return result;
    }

    @Override
    public List<NhanVien> findByTinhTrang(Integer TinhTrang) {
        List<NhanVien> result = new ArrayList<>();
        for (NhanVien nhanVien : listNhanVien)
            if (nhanVien.getTinhTrang().equals(TinhTrang))
                result.add(nhanVien);
        return result;
    }

    @Override
    public Integer save(NhanVien nhanVien) throws Exception {
        if (isExist(nhanVien))
            throw new Exception("Đã tồn tại nhân viên này.");
        Integer newID = nhanVienDAO.save(nhanVien);
        if (newID == null)
            throw new Exception("Phát sinh lỗi trong quá trình thêm nhân viên.");
        nhanVien = nhanVienDAO.findByID(newID);
        listNhanVien.add(nhanVien);
        super.save(nhanVien);
        return newID;
    }

    @Override
    public void update(NhanVien nhanVien) throws Exception {
        if (!isExist(nhanVien))
            throw new Exception("Không tồn tại nhân viên (NV" + nhanVien.getMaNV() + ").");
        if (!nhanVienDAO.update(nhanVien))
            throw new Exception("Phát sinh lỗi trong quá trình sửa nhân viên.");
        nhanVien = nhanVienDAO.findByID(nhanVien.getMaNV());
        for (int i = 0; i < listNhanVien.size(); i++) {
            if (listNhanVien.get(i).getMaNV().equals(nhanVien.getMaNV()))
                listNhanVien.set(i, nhanVien);
        }
        super.update(nhanVien);
    }

    @Override
    public void delete(int id) throws Exception {
        HoaDonService hoaDonService = Provider.get(HoaDonService.class);
        CT_HoaDonService ctHoaDonService = Provider.get(CT_HoaDonService.class);
        CT_PhieuNhapService ctPhieuNhapService = Provider.get(CT_PhieuNhapService.class);
        PhieuNhapService phieuNhapService = Provider.get(PhieuNhapService.class);
        for (HoaDon dto: hoaDonService.findByNhanVien(id)) {
            for (CT_HoaDon child : ctHoaDonService.findByMaHD(dto.getMaHD()))
                ctHoaDonService.delete(child.getID());
            hoaDonService.delete(dto.getID());
        }
        for (PhieuNhap dto: phieuNhapService.findByNhanVien(id)) {
            for (CT_PhieuNhap child : ctPhieuNhapService.findByMaPN(dto.getMaPN()))
                ctHoaDonService.delete(child.getID());
            phieuNhapService.delete(dto.getID());
        }
        if (!nhanVienDAO.delete(id))
            throw new Exception("Không thể xóa nhân viên (NV" + id + ").");
        listNhanVien.removeIf(NhanVienDTO -> NhanVienDTO.getMaNV() == id);
        super.delete(NhanVien.class, id);
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
    public boolean isExist(NhanVien nhanVien) {
        if (nhanVien.getMaNV() == null)
            return false;
        return listNhanVien.contains(nhanVien);
    }

    @Override
    public int getTotalCount() {
        return listNhanVien.size();
    }
}

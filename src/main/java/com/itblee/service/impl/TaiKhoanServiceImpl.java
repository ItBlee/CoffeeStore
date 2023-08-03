package com.itblee.service.impl;

import com.google.auto.service.AutoService;
import com.itblee.General;
import com.itblee.Provider;
import com.itblee.dao.TaiKhoanDAO;
import com.itblee.dto.NhanVien;
import com.itblee.dto.PhanQuyen;
import com.itblee.dto.Role;
import com.itblee.dto.TaiKhoan;
import com.itblee.security.Encryptor;
import com.itblee.service.CT_PhanQuyenService;
import com.itblee.service.NhanVienService;
import com.itblee.service.PhanQuyenService;
import com.itblee.service.TaiKhoanService;
import com.itblee.util.StringUtils;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AutoService(TaiKhoanService.class)
public class TaiKhoanServiceImpl extends AbstractService implements TaiKhoanService {
    private List<TaiKhoan> listTaiKhoan;

    private final TaiKhoanDAO taiKhoanDAO = Provider.get(TaiKhoanDAO.class);

    public TaiKhoanServiceImpl() {
        listTaiKhoan = findAll();
    }

    @Override
    public List<TaiKhoan> findAll() {
        if (listTaiKhoan == null || listTaiKhoan.isEmpty())
            listTaiKhoan = taiKhoanDAO.findAll();
        return listTaiKhoan;
    }

    @Override
    public TaiKhoan findByID(int id) {
        for (TaiKhoan taiKhoan : listTaiKhoan)
            if (taiKhoan.getID() == id)
                return taiKhoan;
        return null;
    }

    @Override
    public List<TaiKhoan> findByPhanQuyen(Integer maPQ) {
        List<TaiKhoan> result = new ArrayList<>();
        for (TaiKhoan taiKhoan : listTaiKhoan)
            if (taiKhoan.getMaPQ().equals(maPQ))
                result.add(taiKhoan);
        return result;
    }

    @Override
    public List<TaiKhoan> findByPhanQuyen(String TenPQ) {
        PhanQuyenService phanQuyenService = Provider.get(PhanQuyenService.class);
        for (PhanQuyen dto : phanQuyenService.findAll())
            if (StringUtils.containsIgnoreCase(dto.getTenPQ(), TenPQ))
                return findByPhanQuyen(dto.getMaPQ());
        return new ArrayList<>();
    }

    @Override
    public List<TaiKhoan> findByTenDangNhap(String tenDangNhap) {
        List<TaiKhoan> result = new ArrayList<>();
        for (TaiKhoan taiKhoan : listTaiKhoan)
            if (StringUtils.containsIgnoreCase(taiKhoan.getTenDangNhap(), tenDangNhap))
                result.add(taiKhoan);
        return result;
    }

    @Override
    public TaiKhoan findByNguoiSoHuu(Integer maNV) {
        NhanVienService nhanVienService = Provider.get(NhanVienService.class);
        for (NhanVien dto: nhanVienService.findAll()) {
            if (dto.getMaNV().equals(maNV))
                return findByID(dto.getMaTK());
        }
        return null;
    }

    @Override
    public TaiKhoan findByNguoiSoHuu(String HoTenNV) {
        NhanVienService nhanVienService = Provider.get(NhanVienService.class);
        for (NhanVien dto: nhanVienService.findAll()) {
            if (StringUtils.containsIgnoreCase(dto.getHoTen(), HoTenNV))
                return findByID(dto.getMaTK());
        }
        return null;
    }

    @Override
    public List<TaiKhoan> findByNgayTao(Date tuNgay, Date denNgay) {
        List<TaiKhoan> result = new ArrayList<>();
        for (TaiKhoan taiKhoan : listTaiKhoan) {
            if (tuNgay == null && taiKhoan.getNgayTao().before(denNgay)) {
                result.add(taiKhoan);
                continue;
            }
            if (denNgay == null && taiKhoan.getNgayTao().after(tuNgay)) {
                result.add(taiKhoan);
                continue;
            }
            if ((taiKhoan.getNgayTao().after(tuNgay) && taiKhoan.getNgayTao().before(denNgay))
                    || (tuNgay.equals(denNgay) && tuNgay.equals(new Date(taiKhoan.getNgayTao().getTime()))))
                result.add(taiKhoan);
        }
        return result;
    }

    @Override
    public List<TaiKhoan> findByNguoiTao(Integer MaNguoiTao) {
        List<TaiKhoan> result = new ArrayList<>();
        for (TaiKhoan taiKhoan : listTaiKhoan)
            if (taiKhoan.getNguoiTao().equals(MaNguoiTao))
                result.add(taiKhoan);
        return result;
    }

    @Override
    public List<TaiKhoan> findByNguoiTao(String TenNguoiTao) {
        NhanVienService nhanVienService = Provider.get(NhanVienService.class);
        List<TaiKhoan> result = new ArrayList<>();
        for (NhanVien dto: nhanVienService.findAll()) {
            if (StringUtils.containsIgnoreCase(dto.getHoTen(), TenNguoiTao))
                result.add(findByID(dto.getMaTK()));
        }
        return result;
    }

    @Override
    public List<TaiKhoan> findByTinhTrang(Integer tinhTrang) {
        List<TaiKhoan> result = new ArrayList<>();
        for (TaiKhoan taiKhoan : listTaiKhoan)
            if (taiKhoan.getTinhTrang().equals(tinhTrang))
                result.add(taiKhoan);
        return result;
    }

    @Override
    public Integer save(TaiKhoan taiKhoan) throws Exception {
        if (isExist(taiKhoan))
            throw new Exception("Đã tồn tại tài khoản này.");
        Integer newID = taiKhoanDAO.save(taiKhoan);
        if (newID == null)
            throw new Exception("Phát sinh lỗi trong quá trình thêm tài khoản.");
        taiKhoan = taiKhoanDAO.findByID(newID);
        listTaiKhoan.add(taiKhoan);
        super.save(taiKhoan);
        return newID;
    }

    @Override
    public void update(TaiKhoan taiKhoan) throws Exception {
        NhanVienService nhanVienService = Provider.get(NhanVienService.class);
        if (!isExist(taiKhoan))
            throw new Exception("Không tồn tại tài khoản (TK" + taiKhoan.getMaTK() + ").");
        if (taiKhoan.getTinhTrang() == 0) {
            NhanVien ownerDto = nhanVienService.findByTaiKhoan(taiKhoan.getMaTK());
            if (ownerDto != null) {
                ownerDto.setMaTK(null);
                nhanVienService.update(ownerDto);
            }
        }
        if (!taiKhoanDAO.update(taiKhoan))
            throw new Exception("Phát sinh lỗi trong quá trình thêm tài khoản.");
        taiKhoan = taiKhoanDAO.findByID(taiKhoan.getMaTK());
        for (int i = 0; i < listTaiKhoan.size(); i++) {
            if (listTaiKhoan.get(i).getMaTK().equals(taiKhoan.getMaTK()))
                listTaiKhoan.set(i, taiKhoan);
        }
        super.update(taiKhoan);
    }

    @Override
    public void delete(int id) throws Exception {
        NhanVienService nhanVienService = Provider.get(NhanVienService.class);
        NhanVien ownerDto = nhanVienService.findByTaiKhoan(id);
        if (ownerDto != null) {
            ownerDto.setMaTK(null);
            nhanVienService.update(ownerDto);
        }
        if (!taiKhoanDAO.delete(id))
            throw new Exception("Không thể xóa tài khoản (TK" + id + ").");
        listTaiKhoan.removeIf(taiKhoanDTO -> taiKhoanDTO.getMaTK() == id);
        super.delete(TaiKhoan.class, id);
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
    public boolean isExist(TaiKhoan taiKhoan) {
        if (taiKhoan.getMaTK() == null)
            return false;
        return listTaiKhoan.contains(taiKhoan);
    }

    @Override
    public boolean login(String username, String password) {
        NhanVienService nhanVienService = Provider.get(NhanVienService.class);
        PhanQuyenService phanQuyenService = Provider.get(PhanQuyenService.class);
        CT_PhanQuyenService ctPhanQuyenService = Provider.get(CT_PhanQuyenService.class);
        TaiKhoan findTaiKhoan = null;
        for (TaiKhoan dto : listTaiKhoan) {
            String hashPassword = Encryptor.applySha256(password, dto.getMatKhauSalt());
            if (dto.getTenDangNhap().equals(username)
                && dto.getMatKhauHash().equals(hashPassword)
                && dto.getTinhTrang() == 1) {
                findTaiKhoan = dto;
                break;
            }
        }
        NhanVien getUser = nhanVienService.findByTaiKhoan(findTaiKhoan.getMaTK());
        PhanQuyen getRole = phanQuyenService.findByID(findTaiKhoan.getMaPQ());
        if (getUser == null || getRole == null)
            return false;
        General.user = getUser;
        Role role = new Role(getRole);
        role.setQuyenHD(ctPhanQuyenService.findByID(getRole.getQuyenHD()));
        role.setQuyenSP(ctPhanQuyenService.findByID(getRole.getQuyenSP()));
        role.setQuyenPN(ctPhanQuyenService.findByID(getRole.getQuyenPN()));
        role.setQuyenNCC(ctPhanQuyenService.findByID(getRole.getQuyenNCC()));
        role.setQuyenKH(ctPhanQuyenService.findByID(getRole.getQuyenKH()));
        role.setQuyenKM(ctPhanQuyenService.findByID(getRole.getQuyenKM()));
        role.setQuyenTK(ctPhanQuyenService.findByID(getRole.getQuyenTK()));
        role.setQuyenExcel(ctPhanQuyenService.findByID(getRole.getQuyenExcel()));
        role.setQuyenNV(ctPhanQuyenService.findByID(getRole.getQuyenNV()));
        General.role = role;
        return General.user != null && General.role.getPhanQuyen().getMaPQ() != null;
    }

    @Override
    public int getTotalCount() {
        return listTaiKhoan.size();
    }
}

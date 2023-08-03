package com.itblee.service.impl;

import com.google.auto.service.AutoService;
import com.itblee.Provider;
import com.itblee.dao.SanPhamDAO;
import com.itblee.dto.*;
import com.itblee.service.*;
import com.itblee.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AutoService(SanPhamService.class)
public class SanPhamServiceImpl extends AbstractService implements SanPhamService {
    private List<SanPham> listSanPham;

    private final SanPhamDAO sanPhamDAO = Provider.get(SanPhamDAO.class);

    public SanPhamServiceImpl() {
        listSanPham = findAll();
    }

    @Override
    public List<SanPham> findAll() {
        if (listSanPham == null || listSanPham.isEmpty())
            listSanPham = sanPhamDAO.findAll();
        return listSanPham;
    }

    @Override
    public SanPham findByID(int id) {
        for (SanPham sanPham : listSanPham)
            if (sanPham.getID() == id)
                return sanPham;
        return null;
    }

    @Override
    public List<SanPham> findByNhaCungCap(Integer MaNCC) {
        List<SanPham> result = new ArrayList<>();
        for (SanPham sanPham : listSanPham)
            if (sanPham.getMaNCC().equals(MaNCC))
                result.add(sanPham);
        return result;
    }

    @Override
    public List<SanPham> findByNhaCungCap(String TenNCC) {
        NhaCungCapService nhaCungCapService = Provider.get(NhaCungCapService.class);
        for (NhaCungCap dto : nhaCungCapService.findAll()) {
            if (StringUtils.containsIgnoreCase(dto.getTenNCC(), TenNCC))
                return findByNhaCungCap(dto.getMaNCC());
        }
        return null;
    }

    @Override
    public List<SanPham> findByLoaiSP(Integer MaLoai) {
        List<SanPham> result = new ArrayList<>();
        for (SanPham sanPham : listSanPham)
            if (sanPham.getMaLoai().equals(MaLoai))
                result.add(sanPham);
        return result;
    }

    @Override
    public List<SanPham> findByLoaiSP(String TenLoai) {
        LoaiSPService loaiSPService = Provider.get(LoaiSPService.class);
        for (LoaiSP dto : loaiSPService.findAll()) {
            if (StringUtils.containsIgnoreCase(dto.getTenLoai(), TenLoai))
                return findByNhaCungCap(dto.getMaLoai());
        }
        return null;
    }

    @Override
    public List<SanPham> findByTenSP(String tenSP) {
        List<SanPham> result = new ArrayList<>();
        for (SanPham sanPham : listSanPham)
            if (StringUtils.containsIgnoreCase(sanPham.getTenSP(), tenSP))
                result.add(sanPham);
        return result;
    }

    @Override
    public List<SanPham> findByDonGia(Integer tien) {
        List<SanPham> result = new ArrayList<>();
        for (SanPham sanPham : listSanPham)
            if (sanPham.getDonGia().equals(tien))
                result.add(sanPham);
        return result;
    }

    @Override
    public List<SanPham> findByDonVi(String donVi) {
        List<SanPham> result = new ArrayList<>();
        for (SanPham sanPham : listSanPham)
            if (StringUtils.containsIgnoreCase(sanPham.getDonVi(), donVi))
                result.add(sanPham);
        return result;
    }

    @Override
    public List<SanPham> findBySoLuong(Integer soluong) {
        List<SanPham> result = new ArrayList<>();
        for (SanPham sanPham : listSanPham)
            if (sanPham.getDonGia().equals(soluong))
                result.add(sanPham);
        return result;
    }

    @Override
    public List<SanPham> findByTinhTrang(Integer tinhTrang) {
        List<SanPham> result = new ArrayList<>();
        for (SanPham sanPham : listSanPham)
            if (sanPham.getDonGia().equals(tinhTrang))
                result.add(sanPham);
        return result;
    }


    @Override
    public Integer save(SanPham sanPham) throws Exception {
        if (isExist(sanPham))
            throw new Exception("Đã tồn tại sản phẩm này.");
        Integer newID = sanPhamDAO.save(sanPham);
        if (newID == null)
            throw new Exception("Phát sinh lỗi trong quá trình thêm sản phẩm.");
        sanPham = sanPhamDAO.findByID(newID);
        listSanPham.add(sanPham);
        super.save(sanPham);
        return newID;
    }

    @Override
    public void update(SanPham sanPham) throws Exception {
        if (!isExist(sanPham))
            throw new Exception("Không tồn tại sản phẩm (SP" + sanPham.getMaSP() + ").");
        if (!sanPhamDAO.update(sanPham))
            throw new Exception("Phát sinh lỗi trong quá trình sửa sản phẩm.");
        sanPham = sanPhamDAO.findByID(sanPham.getMaSP());
        for (int i = 0; i < listSanPham.size(); i++) {
            if (listSanPham.get(i).getMaSP().equals(sanPham.getMaSP()))
                listSanPham.set(i, sanPham);
        }
        super.update(sanPham);
    }

    @Override
    public void delete(int id) throws Exception {
        CT_KhuyenMaiService ctKhuyenMaiService = Provider.get(CT_KhuyenMaiService.class);
        CT_PhieuNhapService ctPhieuNhapService = Provider.get(CT_PhieuNhapService.class);
        CT_HoaDonService ctHoaDonService = Provider.get(CT_HoaDonService.class);
        for (CT_HoaDon dto: ctHoaDonService.findByMaSP(id))
            ctHoaDonService.delete(dto.getID());
        for (CT_PhieuNhap dto: ctPhieuNhapService.findByMaSP(id))
            ctPhieuNhapService.delete(dto.getID());
        ctKhuyenMaiService.delete(ctKhuyenMaiService.findByMaSP(id).getID());
        if (!sanPhamDAO.delete(id))
            throw new Exception("Không thể xóa sản phẩm (SP" + id + ").");
        listSanPham.removeIf(sanPhamDTO -> sanPhamDTO.getMaSP() == id);
        super.delete(SanPham.class, id);
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
    public boolean isExist(SanPham sanPham) {
        if (sanPham.getMaSP() == null)
            return false;
        return listSanPham.contains(sanPham);
    }

    @Override
    public int getTotalCount() {
        return listSanPham.size();
    }
}

package com.itblee.service.impl;

import com.google.auto.service.AutoService;
import com.itblee.Provider;
import com.itblee.dao.HoaDonDAO;
import com.itblee.dto.*;
import com.itblee.service.*;
import com.itblee.util.StringUtils;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AutoService(HoaDonService.class)
public class HoaDonServiceImpl extends AbstractService implements HoaDonService {
    private List<HoaDon> listHoaDon;

    private final HoaDonDAO hoaDonDAO = Provider.get(HoaDonDAO.class);

    public HoaDonServiceImpl() {
        listHoaDon = findAll();
    }

    @Override
    public List<HoaDon> findAll() {
        if (listHoaDon == null || listHoaDon.isEmpty())
            listHoaDon = hoaDonDAO.findAll();
        return listHoaDon;
    }

    @Override
    public HoaDon findByID(int id) {
        for (HoaDon hoaDon : listHoaDon)
            if (hoaDon.getID() == id)
                return hoaDon;
        return null;
    }

    @Override
    public List<HoaDon> findByKhachHang(Integer MaKH) {
        List<HoaDon> result = new ArrayList<>();
        for (HoaDon hoaDon : listHoaDon)
            if (hoaDon.getMaKH().equals(MaKH))
                result.add(hoaDon);
        return result;
    }

    @Override
    public List<HoaDon> findByKhachHang(String TenKH) {
        KhachHangService khachHangService = Provider.get(KhachHangService.class);
        for (KhachHang dto: khachHangService.findAll()) {
            if (StringUtils.containsIgnoreCase(dto.getTen(), TenKH))
                return findByKhachHang(dto.getMaKH());
        }
        return null;
    }

    @Override
    public List<HoaDon> findByNhanVien(Integer MaNV) {
        List<HoaDon> result = new ArrayList<>();
        for (HoaDon hoaDon : listHoaDon)
            if (hoaDon.getMaNV().equals(MaNV))
                result.add(hoaDon);
        return result;
    }

    @Override
    public List<HoaDon> findByNhanVien(String TenNV) {
        NhanVienService nhanVienService = Provider.get(NhanVienService.class);
        for (NhanVien dto: nhanVienService.findAll()) {
            if (StringUtils.containsIgnoreCase(dto.getTen(), TenNV))
                return findByNhanVien(dto.getMaNV());
        }
        return null;
    }

    @Override
    public List<HoaDon> findByNgayLap(Date tuNgay, Date denNgay) {
        List<HoaDon> result = new ArrayList<>();
        for (HoaDon hoaDon : listHoaDon) {
            if (tuNgay == null && hoaDon.getNgayLap().before(denNgay)) {
                result.add(hoaDon);
                continue;
            }
            if (denNgay == null && hoaDon.getNgayLap().after(tuNgay)) {
                result.add(hoaDon);
                continue;
            }
            if ((hoaDon.getNgayLap().after(tuNgay) && hoaDon.getNgayLap().before(denNgay))
                    || (tuNgay.equals(denNgay) && tuNgay.toLocalDate().getDayOfYear() == hoaDon.getNgayLap().toLocalDateTime().toLocalDate().getDayOfYear()))
                result.add(hoaDon);
        }
        return result;
    }

    @Override
    public List<HoaDon> findByTongTien(Integer tien) {
        List<HoaDon> result = new ArrayList<>();
        for (HoaDon hoaDon : listHoaDon)
            if (hoaDon.getTongTien().equals(tien))
                result.add(hoaDon);
        return result;
    }

    @Override
    public List<HoaDon> findByTienKhuyenMai(Integer tien) {
        List<HoaDon> result = new ArrayList<>();
        for (HoaDon hoaDon : listHoaDon)
            if (hoaDon.getTienKhuyenMai().equals(tien))
                result.add(hoaDon);
        return result;
    }

    @Override
    public List<HoaDon> findByTinhTrang(Integer TinhTrang) {
        List<HoaDon> result = new ArrayList<>();
        for (HoaDon hoaDon : listHoaDon)
            if (hoaDon.getTinhTrang().equals(TinhTrang))
                result.add(hoaDon);
        return result;
    }

    @Override
    public Integer save(HoaDon hoaDon) throws Exception {
        if (isExist(hoaDon))
            throw new Exception("Đã tồn tại hóa đơn này.");
        Integer newID = hoaDonDAO.save(hoaDon);
        if (newID == null)
            throw new Exception("Phát sinh lỗi trong quá trình thêm hóa đơn.");
        hoaDon = hoaDonDAO.findByID(newID);
        listHoaDon.add(hoaDon);
        super.save(hoaDon);
        return newID;
    }

    @Override
    public void update(HoaDon hoaDon) throws Exception {
        SanPhamService sanPhamService = Provider.get(SanPhamService.class);
        CT_HoaDonService ctHoaDonService = Provider.get(CT_HoaDonService.class);
        if (!isExist(hoaDon))
            throw new Exception("Không tồn tại hóa đơn (HD" + hoaDon.getMaHD() + ").");
        int checkFlag = 0;
        if (hoaDon.getTinhTrang() == 2) {
            hoaDon.setTinhTrang(0);
            checkFlag = 1;
        }
        if (hoaDon.getTinhTrang() == 3) {
            hoaDon.setTinhTrang(1);
            checkFlag = 2;
        }
        if (!hoaDonDAO.update(hoaDon))
            throw new Exception("Phát sinh lỗi trong quá trình thêm hóa đơn.");
        hoaDon = hoaDonDAO.findByID(hoaDon.getMaHD());
        for (int i = 0; i < listHoaDon.size(); i++) {
            if (listHoaDon.get(i).getMaHD().equals(hoaDon.getMaHD()))
                listHoaDon.set(i, hoaDon);
        }
        if (checkFlag == 1) {
            for (CT_HoaDon dto: ctHoaDonService.findByMaHD(hoaDon.getMaHD())) {
                SanPham sanPham = sanPhamService.findByID(dto.getMaSP());
                int newSL = sanPham.getSoLuong() + dto.getSoLuong();
                sanPham.setSoLuong(newSL);
                sanPhamService.update(sanPham);
            }
        } else if (checkFlag == 2) {
            for (CT_HoaDon dto: ctHoaDonService.findByMaHD(hoaDon.getMaHD())) {
                SanPham sanPham = sanPhamService.findByID(dto.getMaSP());
                int newSL = sanPham.getSoLuong() - dto.getSoLuong();
                sanPham.setSoLuong(newSL);
                sanPhamService.update(sanPham);
            }
        }
        super.update(hoaDon);
    }

    @Override
    public void delete(int id) throws Exception {
        CT_HoaDonService ctHoaDonService = Provider.get(CT_HoaDonService.class);
        CT_PhieuNhapService ctPhieuNhapService = Provider.get(CT_PhieuNhapService.class);
        SanPhamService sanPhamService = Provider.get(SanPhamService.class);
        Integer status = findByID(id).getTinhTrang();
        if (status == null)
            throw new Exception("Không thể xóa hóa đơn (HD" + id + ").");
        if (status == 0) {

            for (CT_PhieuNhap dto: ctPhieuNhapService.findByMaPN(id)) {
                SanPham sanPham = sanPhamService.findByID(dto.getMaSP());
                int newSL = sanPham.getSoLuong() - dto.getSoLuong();
                sanPham.setSoLuong(newSL);
                sanPhamService.update(sanPham);
            }
        }

        for (CT_HoaDon dto: ctHoaDonService.findByMaHD(id))
            ctHoaDonService.delete(dto.getID());
        if (!hoaDonDAO.delete(id))
            throw new Exception("Không thể xóa hóa đơn (HD" + id + ").");
        listHoaDon.removeIf(hoaDonDTO -> hoaDonDTO.getMaHD() == id);
        super.delete(HoaDon.class, id);
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
    public boolean isExist(HoaDon hoaDon) {
        if (hoaDon.getMaHD() == null)
            return false;
        return listHoaDon.contains(hoaDon);
    }

    @Override
    public int getTotalCount() {
        return listHoaDon.size();
    }
}

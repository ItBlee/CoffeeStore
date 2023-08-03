package com.itblee.service.impl;

import com.google.auto.service.AutoService;
import com.itblee.Provider;
import com.itblee.dao.CT_HoaDonDAO;
import com.itblee.dto.CT_HoaDon;
import com.itblee.dto.HoaDon;
import com.itblee.dto.SanPham;
import com.itblee.service.CT_HoaDonService;
import com.itblee.service.HoaDonService;
import com.itblee.service.SanPhamService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AutoService(CT_HoaDonService.class)
public class CT_HoaDonServiceImpl implements CT_HoaDonService {

    private List<CT_HoaDon> listCTHoaDon;

    private final CT_HoaDonDAO ctHoaDonDAO = Provider.get(CT_HoaDonDAO.class);

    public CT_HoaDonServiceImpl() {
        listCTHoaDon = findAll();
    }

    @Override
    public List<CT_HoaDon> findAll() {
        if (listCTHoaDon == null || listCTHoaDon.isEmpty())
            listCTHoaDon = ctHoaDonDAO.findAll();
        return listCTHoaDon;
    }

    @Override
    public CT_HoaDon findByID(int id) {
        for (CT_HoaDon ctHoaDonDTO : listCTHoaDon)
            if (ctHoaDonDTO.getID() == id)
                return ctHoaDonDTO;
        return null;
    }

    @Override
    public List<CT_HoaDon> findByMaHD(Integer MaHD) {
        List<CT_HoaDon> result = new ArrayList<>();
        for (CT_HoaDon ctHoaDonDTO : listCTHoaDon)
            if (ctHoaDonDTO.getMaHD().equals(MaHD))
                result.add(ctHoaDonDTO);
        return result;
    }

    @Override
    public List<CT_HoaDon> findByMaSP(Integer MaSP) {
        List<CT_HoaDon> result = new ArrayList<>();
        for (CT_HoaDon ctHoaDonDTO : listCTHoaDon)
            if (ctHoaDonDTO.getMaSP().equals(MaSP))
                result.add(ctHoaDonDTO);
        return result;
    }

    @Override
    public Integer save(CT_HoaDon ctHoaDon) throws Exception {
        SanPhamService sanPhamService = Provider.get(SanPhamService.class);
        HoaDonService hoaDonService = Provider.get(HoaDonService.class);
        if (isExist(ctHoaDon))
            throw new Exception("Đã tồn tại chi tiết hóa đơn này.");
        for (CT_HoaDon dto:listCTHoaDon) {
            if (dto.getMaHD().equals(ctHoaDon.getMaHD())
            && dto.getMaSP().equals(ctHoaDon.getMaSP()))
                throw new Exception("Đã tồn tại sản phẩm ở hóa đơn này.");
        }
        Integer newID = ctHoaDonDAO.save(ctHoaDon);
        if (newID == null)
            throw new Exception("Phát sinh lỗi trong quá trình thêm chi tiết hóa đơn.");
        ctHoaDon = ctHoaDonDAO.findByID(newID);
        listCTHoaDon.add(ctHoaDon);

        SanPham sanPham = sanPhamService.findByID(ctHoaDon.getMaSP());
        int newSL = sanPham.getSoLuong() - ctHoaDon.getSoLuong();
        if (newSL < 0) newSL = 0;
        sanPham.setSoLuong(newSL);
        sanPhamService.update(sanPham);

        HoaDon parent = hoaDonService.findByID(ctHoaDon.getMaHD());
        int newSale = parent.getTienKhuyenMai() + ctHoaDon.getTienKhuyenMai();
        parent.setTienKhuyenMai(newSale);
        int newTotal = parent.getTongTien() + ctHoaDon.getThanhTien();
        parent.setTongTien(newTotal);
        int newPay = parent.getTongTien() - parent.getTienKhuyenMai();
        parent.setTienThanhToan(newPay);
        hoaDonService.update(parent);
        return newID;
    }

    @Override
    public void update(CT_HoaDon ctHoaDon) throws Exception {
        SanPhamService sanPhamService = Provider.get(SanPhamService.class);
        HoaDonService hoaDonService = Provider.get(HoaDonService.class);
        if (!isExist(ctHoaDon))
            throw new Exception("Không tồn tại chi tiết hóa đơn (CTHD" + ctHoaDon.getMaCTHD() + ").");
        CT_HoaDon oldDto = findByID(ctHoaDon.getID());
        int oldSLSP = oldDto.getSoLuong();
        int oldSale = oldDto.getTienKhuyenMai();
        int oldTotal = oldDto.getThanhTien();
        if (!ctHoaDonDAO.update(ctHoaDon))
            throw new Exception("Phát sinh lỗi trong quá trình thêm chi tiết hóa đơn.");
        ctHoaDon = ctHoaDonDAO.findByID(ctHoaDon.getMaCTHD());
        for (int i = 0; i < listCTHoaDon.size(); i++) {
            if (listCTHoaDon.get(i).getMaCTHD().equals(ctHoaDon.getMaCTHD()))
                listCTHoaDon.set(i, ctHoaDon);
        }

        SanPham sanPham = sanPhamService.findByID(ctHoaDon.getMaSP());
        int newSL = sanPham.getSoLuong() + oldSLSP - ctHoaDon.getSoLuong();
        sanPham.setSoLuong(newSL);
        sanPhamService.update(sanPham);

        HoaDon parent = hoaDonService.findByID(ctHoaDon.getMaHD());
        int newSale = parent.getTienKhuyenMai() - oldSale + ctHoaDon.getTienKhuyenMai();
        parent.setTienKhuyenMai(newSale);
        int newTotal = parent.getTongTien() - oldTotal + ctHoaDon.getThanhTien();
        parent.setTongTien(newTotal);
        int newPay = parent.getTongTien() - parent.getTienKhuyenMai();
        parent.setTienThanhToan(newPay);
        hoaDonService.update(parent);
    }

    @Override
    public void delete(int id) throws Exception {
        SanPhamService sanPhamService = Provider.get(SanPhamService.class);
        HoaDonService hoaDonService = Provider.get(HoaDonService.class);
        CT_HoaDon temp = findByID(id);
        if (!ctHoaDonDAO.delete(id))
            throw new Exception("Không thể xóa chi tiết hóa đơn (CTHD" + id + ").");
        listCTHoaDon.removeIf(CT_HoaDonDTO -> CT_HoaDonDTO.getMaCTHD() == id);

        SanPham sanPham = sanPhamService.findByID(temp.getMaSP());
        int newSL = sanPham.getSoLuong() + temp.getSoLuong();
        sanPham.setSoLuong(newSL);
        sanPhamService.update(sanPham);

        HoaDon parent = hoaDonService.findByID(temp.getMaHD());
        int newSale = parent.getTienKhuyenMai() - temp.getTienKhuyenMai();
        parent.setTienKhuyenMai(newSale);
        int newTotal = parent.getTongTien() - temp.getThanhTien();
        parent.setTongTien(newTotal);
        int newPay = parent.getTongTien() - parent.getTienKhuyenMai();
        parent.setTienThanhToan(newPay);
        hoaDonService.update(parent);
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
    public boolean isExist(CT_HoaDon ctHoaDon) {
        if (ctHoaDon.getMaCTHD() == null)
            return false;
        return listCTHoaDon.contains(ctHoaDon);
    }

    @Override
    public int getTotalCount() {
        return listCTHoaDon.size();
    }
}

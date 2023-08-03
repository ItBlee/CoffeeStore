package com.itblee.service.impl;

import com.google.auto.service.AutoService;
import com.itblee.Provider;
import com.itblee.dao.CT_PhieuNhapDAO;
import com.itblee.dto.CT_PhieuNhap;
import com.itblee.dto.PhieuNhap;
import com.itblee.dto.SanPham;
import com.itblee.service.CT_PhieuNhapService;
import com.itblee.service.PhieuNhapService;
import com.itblee.service.SanPhamService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@AutoService(CT_PhieuNhapService.class)
public class CT_PhieuNhapServiceImpl implements CT_PhieuNhapService {
    private List<CT_PhieuNhap> listCTPhieuNhap;

    private final CT_PhieuNhapDAO ctPhieuNhapDAO = Provider.get(CT_PhieuNhapDAO.class);

    public CT_PhieuNhapServiceImpl() {
        listCTPhieuNhap = findAll();
    }

    @Override
    public List<CT_PhieuNhap> findAll() {
        if (listCTPhieuNhap == null || listCTPhieuNhap.isEmpty())
            listCTPhieuNhap = ctPhieuNhapDAO.findAll();
        return listCTPhieuNhap;
    }

    @Override
    public CT_PhieuNhap findByID(int id) {
        for (CT_PhieuNhap ctPhieuNhapDTO : listCTPhieuNhap)
            if (ctPhieuNhapDTO.getID() == id)
                return ctPhieuNhapDTO;
        return null;
    }

    @Override
    public List<CT_PhieuNhap> findByMaPN(Integer MaPN) {
        List<CT_PhieuNhap> result = new ArrayList<>();
        for (CT_PhieuNhap ctPhieuNhapDTO : listCTPhieuNhap)
            if (ctPhieuNhapDTO.getMaPN().equals(MaPN))
                result.add(ctPhieuNhapDTO);
        return result;
    }

    @Override
    public List<CT_PhieuNhap> findByMaSP(Integer maSP) {
        List<CT_PhieuNhap> result = new ArrayList<>();
        for (CT_PhieuNhap ctPhieuNhapDTO : listCTPhieuNhap)
            if (ctPhieuNhapDTO.getMaSP().equals(maSP))
                result.add(ctPhieuNhapDTO);
        return result;
    }

    @Override
    public Integer save(CT_PhieuNhap ctPhieuNhap) throws Exception {
        SanPhamService sanPhamService = Provider.get(SanPhamService.class);
        PhieuNhapService phieuNhapService = Provider.get(PhieuNhapService.class);
        if (isExist(ctPhieuNhap))
            throw new Exception("Đã tồn tại chi tiết phiếu nhập này.");
        for (CT_PhieuNhap dto:listCTPhieuNhap) {
            if (dto.getMaPN().equals(ctPhieuNhap.getMaPN())
                    && dto.getMaSP().equals(ctPhieuNhap.getMaSP()))
                throw new Exception("Đã tồn tại sản phẩm ở phiếu nhập này.");
        }
        Integer newID = ctPhieuNhapDAO.save(ctPhieuNhap);
        if (newID == null)
            throw new Exception("Phát sinh lỗi trong quá trình thêm chi tiết phiếu nhập.");
        ctPhieuNhap = ctPhieuNhapDAO.findByID(newID);
        listCTPhieuNhap.add(ctPhieuNhap);

        SanPham sanPham = sanPhamService.findByID(ctPhieuNhap.getMaSP());
        int newSL = sanPham.getSoLuong() + ctPhieuNhap.getSoLuong();
        sanPham.setSoLuong(newSL);
        sanPhamService.update(sanPham);

        PhieuNhap parent = phieuNhapService.findByID(ctPhieuNhap.getMaPN());
        int newTotal = parent.getTongTien() + ctPhieuNhap.getThanhTien();
        parent.setTongTien(newTotal);
        phieuNhapService.update(parent);
        return newID;
    }

    @Override
    public void update(CT_PhieuNhap ctPhieuNhap) throws Exception {
        SanPhamService sanPhamService = Provider.get(SanPhamService.class);
        PhieuNhapService phieuNhapService = Provider.get(PhieuNhapService.class);
        if (!isExist(ctPhieuNhap))
            throw new Exception("Không tồn tại chi tiết phiếu nhập (CTPN" + ctPhieuNhap.getMaCTPN() + ").");
        CT_PhieuNhap oldDto = findByID(ctPhieuNhap.getID());
        int oldSLSP = oldDto.getSoLuong();
        int oldTotal = oldDto.getThanhTien();
        if (!ctPhieuNhapDAO.update(ctPhieuNhap))
            throw new Exception("Phát sinh lỗi trong quá trình thêm chi tiết phiếu nhập.");
        ctPhieuNhap = ctPhieuNhapDAO.findByID(ctPhieuNhap.getMaCTPN());
        for (int i = 0; i < listCTPhieuNhap.size(); i++) {
            if (listCTPhieuNhap.get(i).getMaCTPN().equals(ctPhieuNhap.getMaCTPN()))
                listCTPhieuNhap.set(i, ctPhieuNhap);
        }

        SanPham sanPham = sanPhamService.findByID(ctPhieuNhap.getMaSP());
        int newSL = sanPham.getSoLuong() - oldSLSP + ctPhieuNhap.getSoLuong();
        sanPham.setSoLuong(newSL);
        sanPhamService.update(sanPham);

        PhieuNhap parent = phieuNhapService.findByID(ctPhieuNhap.getMaPN());
        int newTotal = parent.getTongTien() - oldTotal + ctPhieuNhap.getThanhTien();
        parent.setTongTien(newTotal);
        phieuNhapService.update(parent);
    }

    @Override
    public void delete(int id) throws Exception {
        SanPhamService sanPhamService = Provider.get(SanPhamService.class);
        PhieuNhapService phieuNhapService = Provider.get(PhieuNhapService.class);
        CT_PhieuNhap temp = findByID(id);
        if (!ctPhieuNhapDAO.delete(id))
            throw new Exception("Không thể xóa chi tiết phiếu nhập (CTPN" + id + ").");
        listCTPhieuNhap.removeIf(CT_PhieuNhapDTO -> CT_PhieuNhapDTO.getMaCTPN() == id);

        SanPham sanPham = sanPhamService.findByID(temp.getMaSP());
        int newSL = sanPham.getSoLuong() - temp.getSoLuong();
        sanPham.setSoLuong(newSL);
        sanPhamService.update(sanPham);

        PhieuNhap parent = phieuNhapService.findByID(temp.getMaPN());
        int newTotal = parent.getTongTien() - temp.getThanhTien();
        parent.setTongTien(newTotal);
        phieuNhapService.update(parent);
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
    public boolean isExist(CT_PhieuNhap ctPhieuNhap) {
        if (ctPhieuNhap.getMaCTPN() == null)
            return false;
        return listCTPhieuNhap.contains(ctPhieuNhap);
    }

    @Override
    public int getTotalCount() {
        return listCTPhieuNhap.size();
    }
}

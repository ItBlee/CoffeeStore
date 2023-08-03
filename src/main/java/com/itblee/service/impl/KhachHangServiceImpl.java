package com.itblee.service.impl;

import com.google.auto.service.AutoService;
import com.itblee.Provider;
import com.itblee.dao.KhachHangDAO;
import com.itblee.dto.CT_HoaDon;
import com.itblee.dto.HoaDon;
import com.itblee.dto.KhachHang;
import com.itblee.service.CT_HoaDonService;
import com.itblee.service.HoaDonService;
import com.itblee.service.KhachHangService;
import com.itblee.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AutoService(KhachHangService.class)
public class KhachHangServiceImpl extends AbstractService implements KhachHangService {
    private List<KhachHang> listKhachHang;

    private final KhachHangDAO khachHangDAO = Provider.get(KhachHangDAO.class);

    public KhachHangServiceImpl() {
        listKhachHang = findAll();
    }

    @Override
    public List<KhachHang> findAll() {
        if (listKhachHang == null || listKhachHang.isEmpty())
            listKhachHang = khachHangDAO.findAll();
        return listKhachHang;
    }

    @Override
    public KhachHang findByID(int id) {
        for (KhachHang khachHang : listKhachHang)
            if (khachHang.getID() == id)
                return khachHang;
        return null;
    }

    @Override
    public List<KhachHang> findByHoTen(String hoTen) {
        List<KhachHang> result = new ArrayList<>();
        for (KhachHang khachHang : listKhachHang)
            if (StringUtils.containsIgnoreCase(khachHang.getHoTen(), hoTen))
                result.add(khachHang);
        return result;
    }

    @Override
    public List<KhachHang> findBySDT(String sdt) {
        List<KhachHang> result = new ArrayList<>();
        for (KhachHang khachHang : listKhachHang)
            if (StringUtils.containsIgnoreCase(khachHang.getSdt(), sdt))
                result.add(khachHang);
        return result;
    }

    @Override
    public List<KhachHang> findByDiaChi(String diaChi) {
        List<KhachHang> result = new ArrayList<>();
        for (KhachHang khachHang : listKhachHang)
            if (StringUtils.containsIgnoreCase(khachHang.getDiaChi(), diaChi))
                result.add(khachHang);
        return result;
    }

    @Override
    public List<KhachHang> findByEmail(String mail) {
        List<KhachHang> result = new ArrayList<>();
        for (KhachHang khachHang : listKhachHang)
            if (StringUtils.containsIgnoreCase(khachHang.getEmail(), mail))
                result.add(khachHang);
        return result;
    }

    @Override
    public List<KhachHang> findByTinhTrang(Integer tinhTrang) {
        List<KhachHang> result = new ArrayList<>();
        for (KhachHang khachHang : listKhachHang)
            if (khachHang.getTinhTrang().equals(tinhTrang))
                result.add(khachHang);
        return result;
    }

    @Override
    public Integer save(KhachHang khachHang) throws Exception {
        if (isExist(khachHang))
            throw new Exception("Đã tồn tại khách hàng này.");
        Integer newID = khachHangDAO.save(khachHang);
        if (newID == null)
            throw new Exception("Phát sinh lỗi trong quá trình thêm khách hàng.");
        khachHang = khachHangDAO.findByID(newID);
        listKhachHang.add(khachHang);
        super.save(khachHang);
        return newID;
    }

    @Override
    public void update(KhachHang khachHang) throws Exception {
        if (!isExist(khachHang))
            throw new Exception("Không tồn tại khách hàng (KH" + khachHang.getMaKH() + ").");
        if (!khachHangDAO.update(khachHang))
            throw new Exception("Phát sinh lỗi trong quá trình thêm khách hàng.");
        khachHang = khachHangDAO.findByID(khachHang.getMaKH());
        for (int i = 0; i < listKhachHang.size(); i++) {
            if (listKhachHang.get(i).getMaKH().equals(khachHang.getMaKH()))
                listKhachHang.set(i, khachHang);
        }
        super.update(khachHang);
    }

    @Override
    public void delete(int id) throws Exception {
        CT_HoaDonService ctHoaDonService = Provider.get(CT_HoaDonService.class);
        HoaDonService hoaDonService = Provider.get(HoaDonService.class);
        for (HoaDon dto: hoaDonService.findByKhachHang(id)) {
            for (CT_HoaDon child : ctHoaDonService.findByMaHD(dto.getMaHD()))
                ctHoaDonService.delete(child.getID());
            hoaDonService.delete(dto.getID());
        }
        if (!khachHangDAO.delete(id))
            throw new Exception("Không thể xóa khách hàng (KH" + id + ").");
        listKhachHang.removeIf(khachHangDTO -> khachHangDTO.getMaKH() == id);
        super.delete(KhachHang.class, id);
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
    public boolean isExist(KhachHang khachHang) {
        if (khachHang.getMaKH() == null)
            return false;
        return listKhachHang.contains(khachHang);
    }

    @Override
    public int getTotalCount() {
        return listKhachHang.size();
    }
}

package com.itblee.service.impl;

import com.google.auto.service.AutoService;
import com.itblee.Provider;
import com.itblee.dao.KhuyenMaiDAO;
import com.itblee.dto.CT_KhuyenMai;
import com.itblee.dto.KhuyenMai;
import com.itblee.service.CT_KhuyenMaiService;
import com.itblee.service.KhuyenMaiService;
import com.itblee.util.StringUtils;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AutoService(KhuyenMaiService.class)
public class KhuyenMaiServiceImpl extends AbstractService implements KhuyenMaiService {
    private List<KhuyenMai> listKhuyenMai;

    private final KhuyenMaiDAO khuyenMaiDAO = Provider.get(KhuyenMaiDAO.class);

    public KhuyenMaiServiceImpl() {
        listKhuyenMai = findAll();
    }

    @Override
    public List<KhuyenMai> findAll() {
        if (listKhuyenMai == null || listKhuyenMai.isEmpty())
            listKhuyenMai = khuyenMaiDAO.findAll();
        return listKhuyenMai;
    }

    @Override
    public KhuyenMai findByID(int id) {
        for (KhuyenMai khuyenMai : listKhuyenMai)
            if (khuyenMai.getID() == id)
                return khuyenMai;
        return null;
    }

    @Override
    public List<KhuyenMai> findByTieuDe(String tieuDe) {
        List<KhuyenMai> result = new ArrayList<>();
        for (KhuyenMai khuyenMai : listKhuyenMai)
            if (StringUtils.containsIgnoreCase(khuyenMai.getTieuDe(), tieuDe))
                result.add(khuyenMai);
        return result;
    }

    @Override
    public List<KhuyenMai> findByThoiGian(Date tuNgay, Date denNgay) {
        List<KhuyenMai> result = new ArrayList<>();
        for (KhuyenMai khuyenMai : listKhuyenMai) {
            if (tuNgay == null && khuyenMai.getNgayBD().before(denNgay)) {
                result.add(khuyenMai);
                continue;
            }
            if (denNgay == null && (khuyenMai.getNgayBD().after(tuNgay) || khuyenMai.getNgayKT().after(tuNgay))) {
                result.add(khuyenMai);
                continue;
            }
            if (tuNgay.equals(denNgay) && khuyenMai.getNgayBD().before(tuNgay) && khuyenMai.getNgayKT().after(tuNgay)) {
                result.add(khuyenMai);
                continue;
            }
            if (khuyenMai.getNgayBD().before(tuNgay) && khuyenMai.getNgayKT().after(tuNgay)) {
                result.add(khuyenMai);
                continue;
            }
            if (khuyenMai.getNgayKT().after(denNgay) && khuyenMai.getNgayBD().before(denNgay)) {
                result.add(khuyenMai);
                continue;
            }
            if (khuyenMai.getNgayBD().after(tuNgay) && khuyenMai.getNgayBD().before(denNgay)) {
                result.add(khuyenMai);
                continue;
            }
            if (khuyenMai.getNgayKT().after(tuNgay) && khuyenMai.getNgayKT().before(denNgay)) {
                result.add(khuyenMai);
            }
        }
        return result;
    }

    @Override
    public Integer save(KhuyenMai khuyenMai) throws Exception {
        if (isExist(khuyenMai))
            throw new Exception("Đã tồn tại khuyến mãi này.");
        Integer newID = khuyenMaiDAO.save(khuyenMai);
        if (newID == null)
            throw new Exception("Phát sinh lỗi trong quá trình thêm khuyến mãi.");
        khuyenMai = khuyenMaiDAO.findByID(newID);
        listKhuyenMai.add(khuyenMai);
        super.save(khuyenMai);
        return newID;
    }

    @Override
    public void update(KhuyenMai khuyenMai) throws Exception {
        if (!isExist(khuyenMai))
            throw new Exception("Không tồn tại khuyến mãi (KM" + khuyenMai.getMaKM() + ").");
        if (!khuyenMaiDAO.update(khuyenMai))
            throw new Exception("Phát sinh lỗi trong quá trình thêm khuyến mãi.");
        khuyenMai = khuyenMaiDAO.findByID(khuyenMai.getMaKM());
        for (int i = 0; i < listKhuyenMai.size(); i++) {
            if (listKhuyenMai.get(i).getMaKM().equals(khuyenMai.getMaKM()))
                listKhuyenMai.set(i, khuyenMai);
        }
        super.update(khuyenMai);
    }

    @Override
    public void delete(int id) throws Exception {
        CT_KhuyenMaiService ctKhuyenMaiService = Provider.get(CT_KhuyenMaiService.class);
        for (CT_KhuyenMai dto: ctKhuyenMaiService.findByMaKM(id))
            ctKhuyenMaiService.delete(dto.getID());
        if (!khuyenMaiDAO.delete(id))
            throw new Exception("Không thể xóa khuyến mãi (KM" + id + ").");
        listKhuyenMai.removeIf(khuyenMaiDTO -> khuyenMaiDTO.getMaKM() == id);
        super.delete(KhuyenMai.class, id);
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
    public boolean isExist(KhuyenMai khuyenMai) {
        if (khuyenMai.getMaKM() == null)
            return false;
        return listKhuyenMai.contains(khuyenMai);
    }

    @Override
    public int getTotalCount() {
        return listKhuyenMai.size();
    }
}

package BUS;

import BUS.Interfaces.ICT_KhuyenMaiBUS;
import DAO.CT_KhuyenMaiDAO;
import DAO.Interfaces.ICT_KhuyenMaiDAO;
import DTO.CT_HoaDonDTO;
import DTO.CT_KhuyenMaiDTO;

import java.util.ArrayList;
import java.util.HashMap;

public class CT_KhuyenMaiBUS implements ICT_KhuyenMaiBUS {
    private static ArrayList<CT_KhuyenMaiDTO> listCTKhuyenMai = null;
    private final ICT_KhuyenMaiDAO ctKhuyenMaiDAO;

    public CT_KhuyenMaiBUS() {
        this.ctKhuyenMaiDAO = new CT_KhuyenMaiDAO();
        if (listCTKhuyenMai == null)
            listCTKhuyenMai = findAll();
    }

    @Override
    public ArrayList<CT_KhuyenMaiDTO> findAll() {
        if (listCTKhuyenMai == null || listCTKhuyenMai.isEmpty())
            listCTKhuyenMai = ctKhuyenMaiDAO.findAll();
        return listCTKhuyenMai;
    }

    @Override
    public CT_KhuyenMaiDTO findByID(int id) {
        for (CT_KhuyenMaiDTO ctKhuyenMaiDTO : listCTKhuyenMai)
            if (ctKhuyenMaiDTO.getID() == id)
                return ctKhuyenMaiDTO;
        return null;
    }

    @Override
    public ArrayList<CT_KhuyenMaiDTO> findByMaKM(Integer MaKM) {
        ArrayList<CT_KhuyenMaiDTO> result = new ArrayList<CT_KhuyenMaiDTO>();
        for (CT_KhuyenMaiDTO ctKhuyenMaiDTO : listCTKhuyenMai)
            if (ctKhuyenMaiDTO.getMaKM().equals(MaKM))
                result.add(ctKhuyenMaiDTO);
        return result;
    }

    @Override
    public ArrayList<CT_KhuyenMaiDTO> findByMaSP(Integer MaSP) {
        ArrayList<CT_KhuyenMaiDTO> result = new ArrayList<CT_KhuyenMaiDTO>();
        for (CT_KhuyenMaiDTO ctKhuyenMaiDTO : listCTKhuyenMai)
            if (ctKhuyenMaiDTO.getMaSP().equals(MaSP))
                result.add(ctKhuyenMaiDTO);
        return result;
    }

    @Override
    public Integer save(CT_KhuyenMaiDTO ctKhuyenMai) throws Exception {
        if (isExist(ctKhuyenMai))
            throw new Exception("Đã tồn tại chi tiết khuyến mãi này.");
        Integer newID = ctKhuyenMaiDAO.save(ctKhuyenMai);
        if (newID == null)
            throw new Exception("Phát sinh lỗi trong quá trình thêm chi tiết khuyến mãi.");
        ctKhuyenMai = ctKhuyenMaiDAO.findByID(newID);
        listCTKhuyenMai.add(ctKhuyenMai);
        return newID;
    }

    @Override
    public void update(CT_KhuyenMaiDTO ctKhuyenMai) throws Exception {
        if (!isExist(ctKhuyenMai))
            throw new Exception("Không tồn tại chi tiết khuyến mãi (CTKM" + ctKhuyenMai.getMaCTKM() + ").");
        if (!ctKhuyenMaiDAO.update(ctKhuyenMai))
            throw new Exception("Phát sinh lỗi trong quá trình thêm chi tiết khuyến mãi.");
        ctKhuyenMai = ctKhuyenMaiDAO.findByID(ctKhuyenMai.getMaCTKM());
        for (int i = 0; i < listCTKhuyenMai.size(); i++) {
            if (listCTKhuyenMai.get(i).getMaCTKM().equals(ctKhuyenMai.getMaCTKM()))
                listCTKhuyenMai.set(i, ctKhuyenMai);
        }
    }

    @Override
    public void delete(int id) throws Exception {
        if (!ctKhuyenMaiDAO.delete(id))
            throw new Exception("Không thể xóa chi tiết khuyến mãi (CTKM" + id + ").");
        listCTKhuyenMai.removeIf(CT_KhuyenMaiDTO -> CT_KhuyenMaiDTO.getMaCTKM() == id);
    }

    @Override
    public HashMap<Integer, Boolean> delete(int[] ids) {
        HashMap<Integer, Boolean> report = new HashMap<Integer, Boolean>();
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
    public boolean isExist(CT_KhuyenMaiDTO ctKhuyenMai) {
        if (ctKhuyenMai.getMaCTKM() == null)
            return false;
        return listCTKhuyenMai.contains(ctKhuyenMai);
    }

    @Override
    public int getTotalCount() {
        return listCTKhuyenMai.size();
    }
}

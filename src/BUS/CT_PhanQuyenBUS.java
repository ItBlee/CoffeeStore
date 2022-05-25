package BUS;

import BUS.Interfaces.ICT_PhanQuyenBUS;
import DAO.CT_PhanQuyenDAO;
import DAO.Interfaces.ICT_PhanQuyenDAO;
import DTO.CT_PhanQuyenDTO;

import java.util.ArrayList;
import java.util.HashMap;

public class CT_PhanQuyenBUS implements ICT_PhanQuyenBUS {
    private static ArrayList<CT_PhanQuyenDTO> listCTPhanQuyen = null;
    private final ICT_PhanQuyenDAO ctPhanQuyenDAO;

    public CT_PhanQuyenBUS() {
        this.ctPhanQuyenDAO = new CT_PhanQuyenDAO();
        if (listCTPhanQuyen == null)
            listCTPhanQuyen = findAll();
    }

    @Override
    public ArrayList<CT_PhanQuyenDTO> findAll() {
        if (listCTPhanQuyen == null || listCTPhanQuyen.isEmpty())
            listCTPhanQuyen = ctPhanQuyenDAO.findAll();
        return listCTPhanQuyen;
    }

    @Override
    public CT_PhanQuyenDTO findByID(int id) {
        for (CT_PhanQuyenDTO CT_PhanQuyenDTO : listCTPhanQuyen)
            if (CT_PhanQuyenDTO.getID() == id)
                return CT_PhanQuyenDTO;
        return null;
    }

    @Override
    public Integer save(CT_PhanQuyenDTO ctPhanQuyen) throws Exception {
        if (isExist(ctPhanQuyen))
            throw new Exception("Đã tồn tại chi tiết quyền này.");
        Integer newID = ctPhanQuyenDAO.save(ctPhanQuyen);
        if (newID == null)
            throw new Exception("Phát sinh lỗi trong quá trình thêm chi tiết quyền.");
        ctPhanQuyen = ctPhanQuyenDAO.findByID(newID);
        listCTPhanQuyen.add(ctPhanQuyen);
        return newID;
    }

    @Override
    public void update(CT_PhanQuyenDTO ctPhanQuyen) throws Exception {
        if (!isExist(ctPhanQuyen))
            throw new Exception("Không tồn tại chi tiết quyền (CTPQ" + ctPhanQuyen.getMaCTPQ() + ").");
        if (!ctPhanQuyenDAO.update(ctPhanQuyen))
            throw new Exception("Phát sinh lỗi trong quá trình thêm chi tiết quyền.");
        ctPhanQuyen = ctPhanQuyenDAO.findByID(ctPhanQuyen.getMaCTPQ());
        for (int i = 0; i < listCTPhanQuyen.size(); i++) {
            if (listCTPhanQuyen.get(i).getMaCTPQ().equals(ctPhanQuyen.getMaCTPQ()))
                listCTPhanQuyen.set(i, ctPhanQuyen);
        }
    }

    @Override
    public void delete(int id) throws Exception {
        if (!ctPhanQuyenDAO.delete(id))
            throw new Exception("Không thể xóa chi tiết quyền (CTPQ" + id + ").");
        listCTPhanQuyen.removeIf(CT_PhanQuyenDTO -> CT_PhanQuyenDTO.getMaCTPQ() == id);
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
    public boolean isExist(CT_PhanQuyenDTO ctPhanQuyen) {
        if (ctPhanQuyen.getMaCTPQ() == null)
            return false;
        return listCTPhanQuyen.contains(ctPhanQuyen);
    }

    @Override
    public int getTotalCount() {
        return listCTPhanQuyen.size();
    }
}

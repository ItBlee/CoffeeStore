package BUS;

import BUS.Abstract.AbstractHistoricBUS;
import BUS.Interfaces.IPhanQuyenBUS;
import DAO.Interfaces.IPhanQuyenDAO;
import DAO.PhanQuyenDAO;
import DTO.PhanQuyenDTO;

import java.util.ArrayList;
import java.util.HashMap;

public class PhanQuyenBUS extends AbstractHistoricBUS implements IPhanQuyenBUS {
    private static ArrayList<PhanQuyenDTO> listPhanQuyen = null;
    private final IPhanQuyenDAO phanQuyenDAO;

    public PhanQuyenBUS() {
        this.phanQuyenDAO = new PhanQuyenDAO();
        if (listPhanQuyen == null)
            listPhanQuyen = findAll();
    }

    @Override
    public ArrayList<PhanQuyenDTO> findAll() {
        return phanQuyenDAO.findAll();
    }

    @Override
    public PhanQuyenDTO findByID(int id) {
        for (PhanQuyenDTO PhanQuyenDTO : listPhanQuyen)
            if (PhanQuyenDTO.getID() == id)
                return PhanQuyenDTO;
        return null;
    }

    @Override
    public void save(PhanQuyenDTO phanQuyen) throws Exception {
        if (isExist(phanQuyen))
            throw new Exception("Đã tồn tại quyền này.");
        Integer newID = phanQuyenDAO.save(phanQuyen);
        if (newID == null)
            throw new Exception("Phát sinh lỗi trong quá trình thêm quyền.");
        listPhanQuyen.add(phanQuyenDAO.findByID(newID));
        super.save(phanQuyen);
    }

    @Override
    public void update(PhanQuyenDTO phanQuyen) throws Exception {
        if (!isExist(phanQuyen))
            throw new Exception("Không tồn tại quyền (PQ" + phanQuyen.getMaPQ() + ").");
        if (!phanQuyenDAO.update(phanQuyen))
            throw new Exception("Phát sinh lỗi trong quá trình thêm quyền.");
        phanQuyen = phanQuyenDAO.findByID(phanQuyen.getMaPQ());
        for (PhanQuyenDTO PhanQuyenDTO : listPhanQuyen) {
            if (PhanQuyenDTO.getMaPQ().equals(phanQuyen.getMaPQ())) {
                PhanQuyenDTO = phanQuyen;
                return;
            }
        }
        listPhanQuyen.add(phanQuyen);
        super.update(phanQuyen);
    }

    @Override
    public void delete(int id) throws Exception {
        if (!phanQuyenDAO.delete(id))
            throw new Exception("Không thể xóa quyền (PQ" + id + ").");
        listPhanQuyen.removeIf(PhanQuyenDTO -> PhanQuyenDTO.getMaPQ() == id);
        super.delete(PhanQuyenDTO.class, id);
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
    public boolean isExist(PhanQuyenDTO phanQuyen) {
        return listPhanQuyen.contains(phanQuyen);
    }

    @Override
    public int getTotalCount() {
        return listPhanQuyen.size();
    }
}

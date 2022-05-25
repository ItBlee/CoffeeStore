package BUS;

import BUS.Abstract.AbstractHistoricBUS;
import BUS.Interfaces.ICT_PhanQuyenBUS;
import BUS.Interfaces.IPhanQuyenBUS;
import BUS.Interfaces.ITaiKhoanBUS;
import DAO.Interfaces.IPhanQuyenDAO;
import DAO.PhanQuyenDAO;
import DTO.PhanQuyenDTO;
import DTO.TaiKhoanDTO;

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
        if (listPhanQuyen == null || listPhanQuyen.isEmpty())
            listPhanQuyen = phanQuyenDAO.findAll();
        return listPhanQuyen;
    }

    @Override
    public PhanQuyenDTO findByID(int id) {
        for (PhanQuyenDTO PhanQuyenDTO : listPhanQuyen)
            if (PhanQuyenDTO.getID() == id)
                return PhanQuyenDTO;
        return null;
    }

    @Override
    public PhanQuyenDTO findByTenPhanQuyen(String tenPQ) {
        for (PhanQuyenDTO PhanQuyenDTO : listPhanQuyen)
            if (PhanQuyenDTO.getTenPQ().equalsIgnoreCase(tenPQ))
                return PhanQuyenDTO;
        return null;
    }

    @Override
    public Integer save(PhanQuyenDTO phanQuyen) throws Exception {
        if (isExist(phanQuyen))
            throw new Exception("Đã tồn tại quyền này.");
        Integer newID = phanQuyenDAO.save(phanQuyen);
        if (newID == null)
            throw new Exception("Phát sinh lỗi trong quá trình thêm quyền.");
        phanQuyen = phanQuyenDAO.findByID(newID);
        listPhanQuyen.add(phanQuyen);
        super.save(phanQuyen);
        return newID;
    }

    @Override
    public void update(PhanQuyenDTO phanQuyen) throws Exception {
        if (!isExist(phanQuyen))
            throw new Exception("Không tồn tại quyền (PQ" + phanQuyen.getMaPQ() + ").");
        if (!phanQuyenDAO.update(phanQuyen))
            throw new Exception("Phát sinh lỗi trong quá trình thêm quyền.");
        phanQuyen = phanQuyenDAO.findByID(phanQuyen.getMaPQ());
        for (int i = 0; i < listPhanQuyen.size(); i++) {
            if (listPhanQuyen.get(i).getMaPQ().equals(phanQuyen.getMaPQ()))
                listPhanQuyen.set(i, phanQuyen);
        }
        super.update(phanQuyen);
    }

    @Override
    public void delete(int id) throws Exception {
        ITaiKhoanBUS taiKhoanBUS = new TaiKhoanBUS();
        ICT_PhanQuyenBUS ctPhanQuyenBUS = new CT_PhanQuyenBUS();
        PhanQuyenDTO dto = findByID(id);
        if (dto == null)
            throw new Exception("Không tìm thấy quyền (PQ" + id + ").");
        for (TaiKhoanDTO taiKhoanDTO :taiKhoanBUS.findAll()) {
            if (taiKhoanDTO.getMaPQ().equals(dto.getMaPQ())) {
                final int DEFAULT_EMPLOYEE_ROLE_ID = 2;
                taiKhoanDTO.setMaPQ(DEFAULT_EMPLOYEE_ROLE_ID);
                taiKhoanBUS.update(taiKhoanDTO);
            }
        }
        if (!phanQuyenDAO.delete(dto.getMaPQ()))
            throw new Exception("Không thể xóa quyền (PQ" + dto.getMaPQ() + ").");
        listPhanQuyen.removeIf(PhanQuyenDTO -> PhanQuyenDTO.getMaPQ().equals(dto.getMaPQ()));
        int[] deleteList = new int[9];
        deleteList[0] = dto.getQuyenHD();
        deleteList[1] = dto.getQuyenSP();
        deleteList[2] = dto.getQuyenPN();
        deleteList[3] = dto.getQuyenNCC();
        deleteList[4] = dto.getQuyenKH();
        deleteList[5] = dto.getQuyenKM();
        deleteList[6] = dto.getQuyenTK();
        deleteList[7] = dto.getQuyenExcel();
        deleteList[8] = dto.getQuyenNV();
        ctPhanQuyenBUS.delete(deleteList);
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
        if (phanQuyen.getMaPQ() == null)
            return false;
        return listPhanQuyen.contains(phanQuyen);
    }

    @Override
    public int getTotalCount() {
        return listPhanQuyen.size();
    }
}

package BUS;

import BUS.Abstract.AbstractHistoricBUS;
import BUS.Interfaces.INhaCungCapBUS;
import DAO.Interfaces.INhaCungCapDAO;
import DAO.NhaCungCapDAO;
import DTO.NhaCungCapDTO;
import DTO.TaiKhoanDTO;
import Utils.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;

public class NhaCungCapBUS extends AbstractHistoricBUS implements INhaCungCapBUS {
    private static ArrayList<NhaCungCapDTO> listNhaCungCap = null;
    private final INhaCungCapDAO nhaCungCapDAO;

    public NhaCungCapBUS() {
        this.nhaCungCapDAO = new NhaCungCapDAO();
        if (listNhaCungCap == null)
            listNhaCungCap = findAll();
    }

    @Override
    public ArrayList<NhaCungCapDTO> findAll() {
        if (listNhaCungCap == null || listNhaCungCap.isEmpty())
            listNhaCungCap = nhaCungCapDAO.findAll();
        return listNhaCungCap;
    }

    @Override
    public NhaCungCapDTO findByID(int id) {
        for (NhaCungCapDTO nhaCungCapDTO : listNhaCungCap)
            if (nhaCungCapDTO.getID() == id)
                return nhaCungCapDTO;
        return null;
    }

    @Override
    public ArrayList<NhaCungCapDTO> findByTenNCC(String tenNCC) {
        ArrayList<NhaCungCapDTO> result = new ArrayList<NhaCungCapDTO>();
        for (NhaCungCapDTO nhaCungCapDTO : listNhaCungCap)
            if (StringUtils.containsIgnoreCase(nhaCungCapDTO.getTenNCC(), tenNCC))
                result.add(nhaCungCapDTO);
        return result;
    }

    @Override
    public ArrayList<NhaCungCapDTO> findBySDT(String sdt) {
        ArrayList<NhaCungCapDTO> result = new ArrayList<NhaCungCapDTO>();
        for (NhaCungCapDTO nhaCungCapDTO : listNhaCungCap)
            if (StringUtils.containsIgnoreCase(nhaCungCapDTO.getSDT(), sdt))
                result.add(nhaCungCapDTO);
        return result;
    }

    @Override
    public ArrayList<NhaCungCapDTO> findByDiaChi(String diaChi) {
        ArrayList<NhaCungCapDTO> result = new ArrayList<NhaCungCapDTO>();
        for (NhaCungCapDTO nhaCungCapDTO : listNhaCungCap)
            if (StringUtils.containsIgnoreCase(nhaCungCapDTO.getDiaChi(), diaChi))
                result.add(nhaCungCapDTO);
        return result;
    }

    @Override
    public ArrayList<NhaCungCapDTO> findBySoTaiKhoan(String stk) {
        ArrayList<NhaCungCapDTO> result = new ArrayList<NhaCungCapDTO>();
        for (NhaCungCapDTO nhaCungCapDTO : listNhaCungCap)
            if (StringUtils.containsIgnoreCase(nhaCungCapDTO.getSoTaiKhoan(), stk))
                result.add(nhaCungCapDTO);
        return result;
    }

    @Override
    public ArrayList<NhaCungCapDTO> findByTinhTrang(Integer tinhTrang) {
        ArrayList<NhaCungCapDTO> result = new ArrayList<NhaCungCapDTO>();
        for (NhaCungCapDTO nhaCungCapDTO : listNhaCungCap)
            if (nhaCungCapDTO.getTinhTrang().equals(tinhTrang))
                result.add(nhaCungCapDTO);
        return result;
    }

    @Override
    public Integer save(NhaCungCapDTO nhaCungCap) throws Exception {
        if (isExist(nhaCungCap))
            throw new Exception("Đã tồn tại nhà cung cấp này.");
        Integer newID = nhaCungCapDAO.save(nhaCungCap);
        if (newID == null)
            throw new Exception("Phát sinh lỗi trong quá trình thêm nhà cung cấp.");
        nhaCungCap = nhaCungCapDAO.findByID(newID);
        listNhaCungCap.add(nhaCungCap);
        super.save(nhaCungCap);
        return newID;
    }

    @Override
    public void update(NhaCungCapDTO nhaCungCap) throws Exception {
        if (!isExist(nhaCungCap))
            throw new Exception("Không tồn tại nhà cung cấp (NCC" + nhaCungCap.getMaNCC() + ").");
        if (!nhaCungCapDAO.update(nhaCungCap))
            throw new Exception("Phát sinh lỗi trong quá trình thêm nhà cung cấp.");
        nhaCungCap = nhaCungCapDAO.findByID(nhaCungCap.getMaNCC());
        for (int i = 0; i < listNhaCungCap.size(); i++) {
            if (listNhaCungCap.get(i).getMaNCC().equals(nhaCungCap.getMaNCC()))
                listNhaCungCap.set(i, nhaCungCap);
        }
        super.update(nhaCungCap);
    }

    @Override
    public void delete(int id) throws Exception {
        if (!nhaCungCapDAO.delete(id))
            throw new Exception("Không thể xóa nhà cung cấp (NCC" + id + ").");
        listNhaCungCap.removeIf(nhaCungCapDTO -> nhaCungCapDTO.getMaNCC() == id);
        super.delete(TaiKhoanDTO.class, id);
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
    public boolean isExist(NhaCungCapDTO nhaCungCap) {
        if (nhaCungCap.getMaNCC() == null)
            return false;
        return listNhaCungCap.contains(nhaCungCap);
    }

    @Override
    public int getTotalCount() {
        return listNhaCungCap.size();
    }
}

package BUS;

import BUS.Abstract.AbstractHistoricBUS;
import BUS.Interfaces.ILoaiSPBUS;
import BUS.Interfaces.ISanPhamBUS;
import DAO.Interfaces.ILoaiSPDAO;
import DAO.LoaiSPDAO;
import DTO.LoaiSPDTO;
import DTO.SanPhamDTO;
import Utils.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;

public class LoaiSPBUS extends AbstractHistoricBUS implements ILoaiSPBUS {
    private static ArrayList<LoaiSPDTO> listLoaiSP = null;
    private final ILoaiSPDAO loaiSPDAO;

    public LoaiSPBUS() {
        this.loaiSPDAO = new LoaiSPDAO();
        if (listLoaiSP == null)
            listLoaiSP = findAll();
    }

    @Override
    public ArrayList<LoaiSPDTO> findAll() {
        if (listLoaiSP == null || listLoaiSP.isEmpty())
            listLoaiSP = loaiSPDAO.findAll();
        return listLoaiSP;
    }

    @Override
    public LoaiSPDTO findByID(int id) {
        for (LoaiSPDTO loaiSPDTO : listLoaiSP)
            if (loaiSPDTO.getID() == id)
                return loaiSPDTO;
        return null;
    }

    @Override
    public ArrayList<LoaiSPDTO> findByTenLoai(String tenLoai) {
        ArrayList<LoaiSPDTO> result = new ArrayList<LoaiSPDTO>();
        for (LoaiSPDTO loaiSPDTO : listLoaiSP)
            if (StringUtils.containsIgnoreCase(loaiSPDTO.getTenLoai(), tenLoai))
                result.add(loaiSPDTO);
        return result;
    }

    @Override
    public Integer save(LoaiSPDTO loaiSP) throws Exception {
        if (isExist(loaiSP))
            throw new Exception("Đã tồn tại loại sản phẩm này.");
        Integer newID = loaiSPDAO.save(loaiSP);
        if (newID == null)
            throw new Exception("Phát sinh lỗi trong quá trình thêm loại sản phẩm.");
        loaiSP = loaiSPDAO.findByID(newID);
        listLoaiSP.add(loaiSP);
        super.save(loaiSP);
        return newID;
    }

    @Override
    public void update(LoaiSPDTO loaiSP) throws Exception {
        if (!isExist(loaiSP))
            throw new Exception("Không tồn tại loại sản phẩm (LSP" + loaiSP.getMaLoai() + ").");
        if (!loaiSPDAO.update(loaiSP))
            throw new Exception("Phát sinh lỗi trong quá trình sửa loại sản phẩm.");
        loaiSP = loaiSPDAO.findByID(loaiSP.getMaLoai());
        for (int i = 0; i < listLoaiSP.size(); i++) {
            if (listLoaiSP.get(i).getMaLoai().equals(loaiSP.getMaLoai()))
                listLoaiSP.set(i, loaiSP);
        }
        super.update(loaiSP);
    }

    @Override
    public void delete(int id) throws Exception {
        ISanPhamBUS sanPhamBUS = new SanPhamBUS();
        for (SanPhamDTO dto:sanPhamBUS.findByLoaiSP(id)) {
            dto.setMaLoai(null);
            sanPhamBUS.update(dto);
        }
        if (!loaiSPDAO.delete(id))
            throw new Exception("Không thể xóa loại sản phẩm (LSP" + id + ").");
        listLoaiSP.removeIf(loaiSPDTO -> loaiSPDTO.getMaLoai() == id);
        super.delete(LoaiSPDTO.class, id);
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
    public boolean isExist(LoaiSPDTO loaiSP) {
        if (loaiSP.getMaLoai() == null)
            return false;
        return listLoaiSP.contains(loaiSP);
    }

    @Override
    public int getTotalCount() {
        return listLoaiSP.size();
    }
}

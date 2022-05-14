package BUS;

import BUS.Abstract.AbstractHistoricBUS;
import BUS.Interfaces.ILoaiSPBUS;
import DAO.Interfaces.ILoaiSPDAO;
import DAO.LoaiSPDAO;
import DTO.LoaiSPDTO;

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
        return null;
    }

    @Override
    public LoaiSPDTO findByID(int id) {
        return null;
    }

    @Override
    public ArrayList<LoaiSPDTO> findByTenLoai(String tenLoai) {
        return null;
    }

    @Override
    public void save(LoaiSPDTO loaiSP) throws Exception {

    }

    @Override
    public void update(LoaiSPDTO loaiSP) throws Exception {

    }

    @Override
    public void delete(int id) throws Exception {

    }

    @Override
    public HashMap<Integer, Boolean> delete(int[] ids) {
        return null;
    }

    @Override
    public boolean isExist(LoaiSPDTO loaiSP) {
        return false;
    }

    @Override
    public int getTotalCount() {
        return 0;
    }
}

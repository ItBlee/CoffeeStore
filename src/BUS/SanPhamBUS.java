package BUS;

import BUS.Abstract.AbstractHistoricBUS;
import BUS.Interfaces.ISanPhamBUS;
import DAO.Interfaces.ISanPhamDAO;
import DAO.SanPhamDAO;
import DTO.SanPhamDTO;

import java.util.ArrayList;
import java.util.HashMap;

public class SanPhamBUS extends AbstractHistoricBUS implements ISanPhamBUS {
    private static ArrayList<SanPhamDTO> listSanPham = null;
    private final ISanPhamDAO sanPhamDAO;

    public SanPhamBUS() {
        this.sanPhamDAO = new SanPhamDAO();
        if (listSanPham == null)
            listSanPham = findAll();
    }

    @Override
    public ArrayList<SanPhamDTO> findAll() {
        return null;
    }

    @Override
    public SanPhamDTO findByID(int id) {
        return null;
    }

    @Override
    public ArrayList<SanPhamDTO> findByMaNCC(Integer maNCC) {
        return null;
    }

    @Override
    public ArrayList<SanPhamDTO> findByMaLoai(Integer maLoai) {
        return null;
    }

    @Override
    public ArrayList<SanPhamDTO> findByTenSP(String tenSP) {
        return null;
    }

    @Override
    public ArrayList<SanPhamDTO> findByDonGia(Integer from, Integer to) {
        return null;
    }

    @Override
    public ArrayList<SanPhamDTO> findByDonVi(String donVi) {
        return null;
    }

    @Override
    public ArrayList<SanPhamDTO> findBySoLuong(Integer from, Integer to) {
        return null;
    }

    @Override
    public ArrayList<SanPhamDTO> findByTinhTrang(Integer tinhTrang) {
        return null;
    }

    @Override
    public void save(SanPhamDTO sanPham) throws Exception {

    }

    @Override
    public void update(SanPhamDTO sanPham) throws Exception {

    }

    @Override
    public void delete(int id) throws Exception {

    }

    @Override
    public HashMap<Integer, Boolean> delete(int[] ids) {
        return null;
    }

    @Override
    public boolean isExist(SanPhamDTO sanPham) {
        return false;
    }

    @Override
    public int getTotalCount() {
        return 0;
    }

    @Override
    public int tinhSoLuongSP() {
        return 0;
    }
}

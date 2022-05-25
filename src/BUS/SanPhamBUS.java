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
    public ArrayList<SanPhamDTO> findByNhaCungCap(Integer MaNCC) {
        return null;
    }

    @Override
    public ArrayList<SanPhamDTO> findByNhaCungCap(String TenNCC) {
        return null;
    }

    @Override
    public ArrayList<SanPhamDTO> findByLoaiSP(Integer MaLoai) {
        return null;
    }

    @Override
    public ArrayList<SanPhamDTO> findByLoaiSP(String TenLoai) {
        return null;
    }

    @Override
    public ArrayList<SanPhamDTO> findByTenSP(String tenSP) {
        return null;
    }

    @Override
    public ArrayList<SanPhamDTO> findByDonGia(Integer tien) {
        return null;
    }

    @Override
    public ArrayList<SanPhamDTO> findByDonVi(String donVi) {
        return null;
    }

    @Override
    public ArrayList<SanPhamDTO> findBySoLuong(Integer soluong) {
        return null;
    }

    @Override
    public ArrayList<SanPhamDTO> findByTinhTrang(Integer tinhTrang) {
        return null;
    }


    @Override
    public Integer save(SanPhamDTO entity) throws Exception {
        return null;
    }

    @Override
    public void update(SanPhamDTO entity) throws Exception {

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
}

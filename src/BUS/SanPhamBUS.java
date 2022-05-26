package BUS;

import BUS.Abstract.AbstractHistoricBUS;
import BUS.Interfaces.*;
import DAO.Interfaces.ISanPhamDAO;
import DAO.SanPhamDAO;
import DTO.*;
import Utils.StringUtils;

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
        if (listSanPham == null || listSanPham.isEmpty())
            listSanPham = sanPhamDAO.findAll();
        return listSanPham;
    }

    @Override
    public SanPhamDTO findByID(int id) {
        for (SanPhamDTO sanPhamDTO : listSanPham)
            if (sanPhamDTO.getID() == id)
                return sanPhamDTO;
        return null;
    }

    @Override
    public ArrayList<SanPhamDTO> findByNhaCungCap(Integer MaNCC) {
        ArrayList<SanPhamDTO> result = new ArrayList<SanPhamDTO>();
        for (SanPhamDTO sanPhamDTO : listSanPham)
            if (sanPhamDTO.getMaNCC().equals(MaNCC))
                result.add(sanPhamDTO);
        return result;
    }

    @Override
    public ArrayList<SanPhamDTO> findByNhaCungCap(String TenNCC) {
        INhaCungCapBUS bus = new NhaCungCapBUS();
        for (NhaCungCapDTO dto : bus.findAll()) {
            if (StringUtils.containsIgnoreCase(dto.getTenNCC(), TenNCC))
                return findByNhaCungCap(dto.getMaNCC());
        }
        return null;
    }

    @Override
    public ArrayList<SanPhamDTO> findByLoaiSP(Integer MaLoai) {
        ArrayList<SanPhamDTO> result = new ArrayList<SanPhamDTO>();
        for (SanPhamDTO sanPhamDTO : listSanPham)
            if (sanPhamDTO.getMaLoai().equals(MaLoai))
                result.add(sanPhamDTO);
        return result;
    }

    @Override
    public ArrayList<SanPhamDTO> findByLoaiSP(String TenLoai) {
        ILoaiSPBUS bus = new LoaiSPBUS();
        for (LoaiSPDTO dto : bus.findAll()) {
            if (StringUtils.containsIgnoreCase(dto.getTenLoai(), TenLoai))
                return findByNhaCungCap(dto.getMaLoai());
        }
        return null;
    }

    @Override
    public ArrayList<SanPhamDTO> findByTenSP(String tenSP) {
        ArrayList<SanPhamDTO> result = new ArrayList<SanPhamDTO>();
        for (SanPhamDTO sanPhamDTO : listSanPham)
            if (StringUtils.containsIgnoreCase(sanPhamDTO.getTenSP(), tenSP))
                result.add(sanPhamDTO);
        return result;
    }

    @Override
    public ArrayList<SanPhamDTO> findByDonGia(Integer tien) {
        ArrayList<SanPhamDTO> result = new ArrayList<SanPhamDTO>();
        for (SanPhamDTO sanPhamDTO : listSanPham)
            if (sanPhamDTO.getDonGia().equals(tien))
                result.add(sanPhamDTO);
        return result;
    }

    @Override
    public ArrayList<SanPhamDTO> findByDonVi(String donVi) {
        ArrayList<SanPhamDTO> result = new ArrayList<SanPhamDTO>();
        for (SanPhamDTO sanPhamDTO : listSanPham)
            if (StringUtils.containsIgnoreCase(sanPhamDTO.getDonVi(), donVi))
                result.add(sanPhamDTO);
        return result;
    }

    @Override
    public ArrayList<SanPhamDTO> findBySoLuong(Integer soluong) {
        ArrayList<SanPhamDTO> result = new ArrayList<SanPhamDTO>();
        for (SanPhamDTO sanPhamDTO : listSanPham)
            if (sanPhamDTO.getDonGia().equals(soluong))
                result.add(sanPhamDTO);
        return result;
    }

    @Override
    public ArrayList<SanPhamDTO> findByTinhTrang(Integer tinhTrang) {
        ArrayList<SanPhamDTO> result = new ArrayList<SanPhamDTO>();
        for (SanPhamDTO sanPhamDTO : listSanPham)
            if (sanPhamDTO.getDonGia().equals(tinhTrang))
                result.add(sanPhamDTO);
        return result;
    }


    @Override
    public Integer save(SanPhamDTO sanPham) throws Exception {
        if (isExist(sanPham))
            throw new Exception("Đã tồn tại sản phẩm này.");
        Integer newID = sanPhamDAO.save(sanPham);
        if (newID == null)
            throw new Exception("Phát sinh lỗi trong quá trình thêm sản phẩm.");
        sanPham = sanPhamDAO.findByID(newID);
        listSanPham.add(sanPham);
        super.save(sanPham);
        return newID;
    }

    @Override
    public void update(SanPhamDTO sanPham) throws Exception {
        if (!isExist(sanPham))
            throw new Exception("Không tồn tại sản phẩm (SP" + sanPham.getMaSP() + ").");
        if (!sanPhamDAO.update(sanPham))
            throw new Exception("Phát sinh lỗi trong quá trình sửa sản phẩm.");
        sanPham = sanPhamDAO.findByID(sanPham.getMaSP());
        for (int i = 0; i < listSanPham.size(); i++) {
            if (listSanPham.get(i).getMaSP().equals(sanPham.getMaSP()))
                listSanPham.set(i, sanPham);
        }
        super.update(sanPham);
    }

    @Override
    public void delete(int id) throws Exception {
        ICT_HoaDonBUS ctHoaDonBUS = new CT_HoaDonBUS();
        for (CT_HoaDonDTO dto:ctHoaDonBUS.findByMaSP(id))
            ctHoaDonBUS.delete(dto.getID());
        ICT_PhieuNhapBUS ctPhieuNhapBUS = new CT_PhieuNhapBUS();
        for (CT_PhieuNhapDTO dto: ctPhieuNhapBUS.findByMaSP(id))
            ctPhieuNhapBUS.delete(dto.getID());
        ICT_KhuyenMaiBUS ctKhuyenMaiBUS = new CT_KhuyenMaiBUS();
        ctKhuyenMaiBUS.delete(ctKhuyenMaiBUS.findByMaSP(id).getID());
        if (!sanPhamDAO.delete(id))
            throw new Exception("Không thể xóa sản phẩm (SP" + id + ").");
        listSanPham.removeIf(sanPhamDTO -> sanPhamDTO.getMaSP() == id);
        super.delete(SanPhamDTO.class, id);
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
    public boolean isExist(SanPhamDTO sanPham) {
        if (sanPham.getMaSP() == null)
            return false;
        return listSanPham.contains(sanPham);
    }

    @Override
    public int getTotalCount() {
        return listSanPham.size();
    }
}

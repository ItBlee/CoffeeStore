package BUS;

import BUS.Abstract.AbstractHistoricBUS;
import BUS.Interfaces.*;
import DAO.HoaDonDAO;
import DAO.Interfaces.IHoaDonDAO;
import DTO.*;
import Utils.StringUtils;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;

public class HoaDonBUS extends AbstractHistoricBUS implements IHoaDonBUS {
    private static ArrayList<HoaDonDTO> listHoaDon = null;
    private final IHoaDonDAO hoaDonDAO;

    public HoaDonBUS() {
        this.hoaDonDAO = new HoaDonDAO();
        if (listHoaDon == null)
            listHoaDon = findAll();
    }

    @Override
    public ArrayList<HoaDonDTO> findAll() {
        if (listHoaDon == null || listHoaDon.isEmpty())
            listHoaDon = hoaDonDAO.findAll();
        return listHoaDon;
    }

    @Override
    public HoaDonDTO findByID(int id) {
        for (HoaDonDTO hoaDonDTO : listHoaDon)
            if (hoaDonDTO.getID() == id)
                return hoaDonDTO;
        return null;
    }

    @Override
    public ArrayList<HoaDonDTO> findByKhachHang(Integer MaKH) {
        ArrayList<HoaDonDTO> result = new ArrayList<HoaDonDTO>();
        for (HoaDonDTO hoaDonDTO : listHoaDon)
            if (hoaDonDTO.getMaKH().equals(MaKH))
                result.add(hoaDonDTO);
        return result;
    }

    @Override
    public ArrayList<HoaDonDTO> findByKhachHang(String TenKH) {
        IKhachHangBUS khachHangBUS = new KhachHangBUS();
        for (KhachHangDTO dto: khachHangBUS.findAll()) {
            if (StringUtils.containsIgnoreCase(dto.getTen(), TenKH))
                return findByKhachHang(dto.getMaKH());
        }
        return null;
    }

    @Override
    public ArrayList<HoaDonDTO> findByNhanVien(Integer MaNV) {
        ArrayList<HoaDonDTO> result = new ArrayList<HoaDonDTO>();
        for (HoaDonDTO hoaDonDTO : listHoaDon)
            if (hoaDonDTO.getMaNV().equals(MaNV))
                result.add(hoaDonDTO);
        return result;
    }

    @Override
    public ArrayList<HoaDonDTO> findByNhanVien(String TenNV) {
        INhanVienBUS nhanVienBUS = new NhanVienBUS();
        for (NhanVienDTO dto: nhanVienBUS.findAll()) {
            if (StringUtils.containsIgnoreCase(dto.getTen(), TenNV))
                return findByNhanVien(dto.getMaNV());
        }
        return null;
    }

    @Override
    public ArrayList<HoaDonDTO> findByNgayLap(Date tuNgay, Date denNgay) {
        ArrayList<HoaDonDTO> result = new ArrayList<HoaDonDTO>();
        for (HoaDonDTO hoaDonDTO : listHoaDon) {
            if (tuNgay == null && hoaDonDTO.getNgayLap().before(denNgay)) {
                result.add(hoaDonDTO);
                continue;
            }
            if (denNgay == null && hoaDonDTO.getNgayLap().after(tuNgay)) {
                result.add(hoaDonDTO);
                continue;
            }
            if ((hoaDonDTO.getNgayLap().after(tuNgay) && hoaDonDTO.getNgayLap().before(denNgay))
                    || (tuNgay.equals(denNgay) && tuNgay.equals(new Date(hoaDonDTO.getNgayLap().getTime()))))
                result.add(hoaDonDTO);
        }
        return result;
    }

    @Override
    public ArrayList<HoaDonDTO> findByTongTien(Integer tien) {
        ArrayList<HoaDonDTO> result = new ArrayList<HoaDonDTO>();
        for (HoaDonDTO hoaDonDTO : listHoaDon)
            if (hoaDonDTO.getTongTien().equals(tien))
                result.add(hoaDonDTO);
        return result;
    }

    @Override
    public ArrayList<HoaDonDTO> findByTienKhuyenMai(Integer tien) {
        ArrayList<HoaDonDTO> result = new ArrayList<HoaDonDTO>();
        for (HoaDonDTO hoaDonDTO : listHoaDon)
            if (hoaDonDTO.getTienKhuyenMai().equals(tien))
                result.add(hoaDonDTO);
        return result;
    }

    @Override
    public ArrayList<HoaDonDTO> findByTinhTrang(Integer TinhTrang) {
        ArrayList<HoaDonDTO> result = new ArrayList<HoaDonDTO>();
        for (HoaDonDTO hoaDonDTO : listHoaDon)
            if (hoaDonDTO.getTinhTrang().equals(TinhTrang))
                result.add(hoaDonDTO);
        return result;
    }

    @Override
    public Integer save(HoaDonDTO hoaDon) throws Exception {
        if (isExist(hoaDon))
            throw new Exception("Đã tồn tại hóa đơn này.");
        Integer newID = hoaDonDAO.save(hoaDon);
        if (newID == null)
            throw new Exception("Phát sinh lỗi trong quá trình thêm hóa đơn.");
        hoaDon = hoaDonDAO.findByID(newID);
        listHoaDon.add(hoaDon);
        super.save(hoaDon);
        return newID;
    }

    @Override
    public void update(HoaDonDTO hoaDon) throws Exception {
        if (!isExist(hoaDon))
            throw new Exception("Không tồn tại hóa đơn (HD" + hoaDon.getMaHD() + ").");
        int checkFlag = 0;
        if (hoaDon.getTinhTrang() == 2) {
            hoaDon.setTinhTrang(0);
            checkFlag = 1;
        }
        if (hoaDon.getTinhTrang() == 3) {
            hoaDon.setTinhTrang(1);
            checkFlag = 2;
        }
        if (!hoaDonDAO.update(hoaDon))
            throw new Exception("Phát sinh lỗi trong quá trình thêm hóa đơn.");
        hoaDon = hoaDonDAO.findByID(hoaDon.getMaHD());
        for (int i = 0; i < listHoaDon.size(); i++) {
            if (listHoaDon.get(i).getMaHD().equals(hoaDon.getMaHD()))
                listHoaDon.set(i, hoaDon);
        }
        if (checkFlag == 1) {
            ICT_HoaDonBUS ctHoaDonBUS = new CT_HoaDonBUS();
            ISanPhamBUS sanPhamBUS = new SanPhamBUS();
            for (CT_HoaDonDTO dto: ctHoaDonBUS.findByMaHD(hoaDon.getMaHD())) {
                SanPhamDTO sanPhamDTO = sanPhamBUS.findByID(dto.getMaSP());
                int newSL = sanPhamDTO.getSoLuong() + dto.getSoLuong();
                sanPhamDTO.setSoLuong(newSL);
                sanPhamBUS.update(sanPhamDTO);
            }
        } else if (checkFlag == 2) {
            ICT_HoaDonBUS ctHoaDonBUS = new CT_HoaDonBUS();
            ISanPhamBUS sanPhamBUS = new SanPhamBUS();
            for (CT_HoaDonDTO dto: ctHoaDonBUS.findByMaHD(hoaDon.getMaHD())) {
                SanPhamDTO sanPhamDTO = sanPhamBUS.findByID(dto.getMaSP());
                int newSL = sanPhamDTO.getSoLuong() - dto.getSoLuong();
                sanPhamDTO.setSoLuong(newSL);
                sanPhamBUS.update(sanPhamDTO);
            }
        }
        super.update(hoaDon);
    }

    @Override
    public void delete(int id) throws Exception {
        Integer status = findByID(id).getTinhTrang();
        if (status == null)
            throw new Exception("Không thể xóa hóa đơn (HD" + id + ").");
        if (status == 0) {
            ICT_PhieuNhapBUS ctPhieuNhapBUS = new CT_PhieuNhapBUS();
            ISanPhamBUS sanPhamBUS = new SanPhamBUS();
            for (CT_PhieuNhapDTO dto:ctPhieuNhapBUS.findByMaPN(id)) {
                SanPhamDTO sanPhamDTO = sanPhamBUS.findByID(dto.getMaSP());
                int newSL = sanPhamDTO.getSoLuong() - dto.getSoLuong();
                sanPhamDTO.setSoLuong(newSL);
                sanPhamBUS.update(sanPhamDTO);
            }
        }
        ICT_HoaDonBUS ctHoaDonBUS = new CT_HoaDonBUS();
        for (CT_HoaDonDTO dto:ctHoaDonBUS.findByMaHD(id))
            ctHoaDonBUS.delete(dto.getID());
        if (!hoaDonDAO.delete(id))
            throw new Exception("Không thể xóa hóa đơn (HD" + id + ").");
        listHoaDon.removeIf(hoaDonDTO -> hoaDonDTO.getMaHD() == id);
        super.delete(HoaDonDTO.class, id);
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
    public boolean isExist(HoaDonDTO hoaDon) {
        if (hoaDon.getMaHD() == null)
            return false;
        return listHoaDon.contains(hoaDon);
    }

    @Override
    public int getTotalCount() {
        return listHoaDon.size();
    }
}

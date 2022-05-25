package BUS;

import BUS.Interfaces.ICT_HoaDonBUS;
import BUS.Interfaces.IHoaDonBUS;
import BUS.Interfaces.ISanPhamBUS;
import DAO.CT_HoaDonDAO;
import DAO.Interfaces.ICT_HoaDonDAO;
import DTO.CT_HoaDonDTO;
import DTO.HoaDonDTO;
import DTO.SanPhamDTO;

import java.util.ArrayList;
import java.util.HashMap;

public class CT_HoaDonBUS implements ICT_HoaDonBUS {
    private static ArrayList<CT_HoaDonDTO> listCTHoaDon = null;
    private final ICT_HoaDonDAO ctHoaDonDAO;

    public CT_HoaDonBUS() {
        this.ctHoaDonDAO = new CT_HoaDonDAO();
        if (listCTHoaDon == null)
            listCTHoaDon = findAll();
    }

    @Override
    public ArrayList<CT_HoaDonDTO> findAll() {
        if (listCTHoaDon == null || listCTHoaDon.isEmpty())
            listCTHoaDon = ctHoaDonDAO.findAll();
        return listCTHoaDon;
    }

    @Override
    public CT_HoaDonDTO findByID(int id) {
        for (CT_HoaDonDTO ctHoaDonDTO : listCTHoaDon)
            if (ctHoaDonDTO.getID() == id)
                return ctHoaDonDTO;
        return null;
    }

    @Override
    public ArrayList<CT_HoaDonDTO> findByMaHD(Integer MaHD) {
        ArrayList<CT_HoaDonDTO> result = new ArrayList<CT_HoaDonDTO>();
        for (CT_HoaDonDTO ctHoaDonDTO : listCTHoaDon)
            if (ctHoaDonDTO.getMaHD().equals(MaHD))
                result.add(ctHoaDonDTO);
        return result;
    }

    @Override
    public ArrayList<CT_HoaDonDTO> findByMaSP(Integer MaSP) {
        ArrayList<CT_HoaDonDTO> result = new ArrayList<CT_HoaDonDTO>();
        for (CT_HoaDonDTO ctHoaDonDTO : listCTHoaDon)
            if (ctHoaDonDTO.getMaSP().equals(MaSP))
                result.add(ctHoaDonDTO);
        return result;
    }

    @Override
    public Integer save(CT_HoaDonDTO ctHoaDon) throws Exception {
        if (isExist(ctHoaDon))
            throw new Exception("Đã tồn tại chi tiết hóa đơn này.");
        for (CT_HoaDonDTO dto:listCTHoaDon) {
            if (dto.getMaHD().equals(ctHoaDon.getMaHD())
            && dto.getMaSP().equals(ctHoaDon.getMaSP()))
                throw new Exception("Đã tồn tại sản phẩm ở hóa đơn này.");
        }
        Integer newID = ctHoaDonDAO.save(ctHoaDon);
        if (newID == null)
            throw new Exception("Phát sinh lỗi trong quá trình thêm chi tiết hóa đơn.");
        ctHoaDon = ctHoaDonDAO.findByID(newID);
        listCTHoaDon.add(ctHoaDon);

        ISanPhamBUS sanPhamBUS = new SanPhamBUS();
        SanPhamDTO sanPhamDTO = sanPhamBUS.findByID(ctHoaDon.getMaSP());
        int newSL = sanPhamDTO.getSoLuong() + ctHoaDon.getSoLuong();
        sanPhamDTO.setSoLuong(newSL);
        sanPhamBUS.update(sanPhamDTO);

        IHoaDonBUS hoaDonBUS = new HoaDonBUS();
        HoaDonDTO parent = hoaDonBUS.findByID(ctHoaDon.getMaHD());
        int newSale = parent.getTienKhuyenMai() + ctHoaDon.getTienKhuyenMai();
        parent.setTienKhuyenMai(newSale);
        int newTotal = parent.getTongTien() + ctHoaDon.getThanhTien();
        parent.setTongTien(newTotal);
        int newPay = parent.getTongTien() - parent.getTienKhuyenMai();
        parent.setTienThanhToan(newPay);
        hoaDonBUS.update(parent);
        return newID;
    }

    @Override
    public void update(CT_HoaDonDTO ctHoaDon) throws Exception {
        if (!isExist(ctHoaDon))
            throw new Exception("Không tồn tại chi tiết hóa đơn (CTHD" + ctHoaDon.getMaCTHD() + ").");
        CT_HoaDonDTO oldDto = findByID(ctHoaDon.getID());
        int oldSLSP = oldDto.getSoLuong();
        int oldSale = oldDto.getTienKhuyenMai();
        int oldTotal = oldDto.getThanhTien();
        if (!ctHoaDonDAO.update(ctHoaDon))
            throw new Exception("Phát sinh lỗi trong quá trình thêm chi tiết hóa đơn.");
        ctHoaDon = ctHoaDonDAO.findByID(ctHoaDon.getMaCTHD());
        for (int i = 0; i < listCTHoaDon.size(); i++) {
            if (listCTHoaDon.get(i).getMaCTHD().equals(ctHoaDon.getMaCTHD()))
                listCTHoaDon.set(i, ctHoaDon);
        }
        SanPhamBUS sanPhamBUS = new SanPhamBUS();
        SanPhamDTO sanPhamDTO = sanPhamBUS.findByID(ctHoaDon.getMaSP());
        int newSL = sanPhamDTO.getSoLuong() - oldSLSP + ctHoaDon.getSoLuong();
        sanPhamDTO.setSoLuong(newSL);
        sanPhamBUS.update(sanPhamDTO);

        IHoaDonBUS hoaDonBUS = new HoaDonBUS();
        HoaDonDTO parent = hoaDonBUS.findByID(ctHoaDon.getMaHD());
        int newSale = parent.getTienKhuyenMai() - oldSale + ctHoaDon.getTienKhuyenMai();
        parent.setTienKhuyenMai(newSale);
        int newTotal = parent.getTongTien() - oldTotal + ctHoaDon.getThanhTien();
        parent.setTongTien(newTotal);
        int newPay = parent.getTongTien() - parent.getTienKhuyenMai();
        parent.setTienThanhToan(newPay);
        hoaDonBUS.update(parent);
    }

    @Override
    public void delete(int id) throws Exception {
        CT_HoaDonDTO temp = findByID(id);
        if (!ctHoaDonDAO.delete(id))
            throw new Exception("Không thể xóa chi tiết hóa đơn (CTHD" + id + ").");
        listCTHoaDon.removeIf(CT_HoaDonDTO -> CT_HoaDonDTO.getMaCTHD() == id);

        ISanPhamBUS sanPhamBUS = new SanPhamBUS();
        SanPhamDTO sanPhamDTO = sanPhamBUS.findByID(temp.getMaSP());
        int newSL = sanPhamDTO.getSoLuong() - temp.getSoLuong();
        sanPhamDTO.setSoLuong(newSL);
        sanPhamBUS.update(sanPhamDTO);

        IHoaDonBUS hoaDonBUS = new HoaDonBUS();
        HoaDonDTO parent = hoaDonBUS.findByID(temp.getMaHD());
        int newSale = parent.getTienKhuyenMai() - temp.getTienKhuyenMai();
        parent.setTienKhuyenMai(newSale);
        int newTotal = parent.getTongTien() - temp.getThanhTien();
        parent.setTongTien(newTotal);
        int newPay = parent.getTongTien() - parent.getTienKhuyenMai();
        parent.setTienThanhToan(newPay);
        hoaDonBUS.update(parent);
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
    public boolean isExist(CT_HoaDonDTO ctHoaDon) {
        if (ctHoaDon.getMaCTHD() == null)
            return false;
        return listCTHoaDon.contains(ctHoaDon);
    }

    @Override
    public int getTotalCount() {
        return listCTHoaDon.size();
    }
}

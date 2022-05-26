package BUS;

import BUS.Abstract.AbstractHistoricBUS;
import BUS.Interfaces.ICT_HoaDonBUS;
import BUS.Interfaces.IHoaDonBUS;
import BUS.Interfaces.IKhachHangBUS;
import DAO.Interfaces.IKhachHangDAO;
import DAO.KhachHangDAO;
import DTO.CT_HoaDonDTO;
import DTO.HoaDonDTO;
import DTO.KhachHangDTO;
import Utils.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;

public class KhachHangBUS extends AbstractHistoricBUS implements IKhachHangBUS {
    private static ArrayList<KhachHangDTO> listKhachHang = null;
    private final IKhachHangDAO khachHangDAO;

    public KhachHangBUS() {
        this.khachHangDAO = new KhachHangDAO();
        if (listKhachHang == null)
            listKhachHang = findAll();
    }

    @Override
    public ArrayList<KhachHangDTO> findAll() {
        if (listKhachHang == null || listKhachHang.isEmpty())
            listKhachHang = khachHangDAO.findAll();
        return listKhachHang;
    }

    @Override
    public KhachHangDTO findByID(int id) {
        for (KhachHangDTO khachHangDTO : listKhachHang)
            if (khachHangDTO.getID() == id)
                return khachHangDTO;
        return null;
    }

    @Override
    public ArrayList<KhachHangDTO> findByHoTen(String hoTen) {
        ArrayList<KhachHangDTO> result = new ArrayList<KhachHangDTO>();
        for (KhachHangDTO khachHangDTO : listKhachHang)
            if (StringUtils.containsIgnoreCase(khachHangDTO.getHoTen(), hoTen))
                result.add(khachHangDTO);
        return result;
    }

    @Override
    public ArrayList<KhachHangDTO> findBySDT(String sdt) {
        ArrayList<KhachHangDTO> result = new ArrayList<KhachHangDTO>();
        for (KhachHangDTO khachHangDTO : listKhachHang)
            if (StringUtils.containsIgnoreCase(khachHangDTO.getSDT(), sdt))
                result.add(khachHangDTO);
        return result;
    }

    @Override
    public ArrayList<KhachHangDTO> findByDiaChi(String diaChi) {
        ArrayList<KhachHangDTO> result = new ArrayList<KhachHangDTO>();
        for (KhachHangDTO khachHangDTO : listKhachHang)
            if (StringUtils.containsIgnoreCase(khachHangDTO.getDiaChi(), diaChi))
                result.add(khachHangDTO);
        return result;
    }

    @Override
    public ArrayList<KhachHangDTO> findByEmail(String mail) {
        ArrayList<KhachHangDTO> result = new ArrayList<KhachHangDTO>();
        for (KhachHangDTO khachHangDTO : listKhachHang)
            if (StringUtils.containsIgnoreCase(khachHangDTO.getEmail(), mail))
                result.add(khachHangDTO);
        return result;
    }

    @Override
    public ArrayList<KhachHangDTO> findByTinhTrang(Integer tinhTrang) {
        ArrayList<KhachHangDTO> result = new ArrayList<KhachHangDTO>();
        for (KhachHangDTO khachHangDTO : listKhachHang)
            if (khachHangDTO.getTinhTrang().equals(tinhTrang))
                result.add(khachHangDTO);
        return result;
    }

    @Override
    public Integer save(KhachHangDTO khachHang) throws Exception {
        if (isExist(khachHang))
            throw new Exception("Đã tồn tại khách hàng này.");
        Integer newID = khachHangDAO.save(khachHang);
        if (newID == null)
            throw new Exception("Phát sinh lỗi trong quá trình thêm khách hàng.");
        khachHang = khachHangDAO.findByID(newID);
        listKhachHang.add(khachHang);
        super.save(khachHang);
        return newID;
    }

    @Override
    public void update(KhachHangDTO khachHang) throws Exception {
        if (!isExist(khachHang))
            throw new Exception("Không tồn tại khách hàng (KH" + khachHang.getMaKH() + ").");
        if (!khachHangDAO.update(khachHang))
            throw new Exception("Phát sinh lỗi trong quá trình thêm khách hàng.");
        khachHang = khachHangDAO.findByID(khachHang.getMaKH());
        for (int i = 0; i < listKhachHang.size(); i++) {
            if (listKhachHang.get(i).getMaKH().equals(khachHang.getMaKH()))
                listKhachHang.set(i, khachHang);
        }
        super.update(khachHang);
    }

    @Override
    public void delete(int id) throws Exception {
        ICT_HoaDonBUS ctHoaDonBUS = new CT_HoaDonBUS();
        IHoaDonBUS hoaDonBUS = new HoaDonBUS();
        for (HoaDonDTO dto:hoaDonBUS.findByKhachHang(id)) {
            for (CT_HoaDonDTO child : ctHoaDonBUS.findByMaHD(dto.getMaHD()))
                ctHoaDonBUS.delete(child.getID());
            hoaDonBUS.delete(dto.getID());
        }
        if (!khachHangDAO.delete(id))
            throw new Exception("Không thể xóa khách hàng (KH" + id + ").");
        listKhachHang.removeIf(khachHangDTO -> khachHangDTO.getMaKH() == id);
        super.delete(KhachHangDTO.class, id);
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
    public boolean isExist(KhachHangDTO khachHang) {
        if (khachHang.getMaKH() == null)
            return false;
        return listKhachHang.contains(khachHang);
    }

    @Override
    public int getTotalCount() {
        return listKhachHang.size();
    }
}

package BUS;

import BUS.Abstract.AbstractHistoricBUS;
import BUS.Interfaces.*;
import DAO.Interfaces.IPhieuNhapDAO;
import DAO.PhieuNhapDAO;
import DTO.*;
import Utils.StringUtils;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;

public class PhieuNhapBUS extends AbstractHistoricBUS implements IPhieuNhapBUS {
    private static ArrayList<PhieuNhapDTO> listPhieuNhap = null;
    private final IPhieuNhapDAO phieuNhapDAO;

    public PhieuNhapBUS() {
        this.phieuNhapDAO = new PhieuNhapDAO();
        if (listPhieuNhap == null)
            listPhieuNhap = findAll();
    }

    @Override
    public ArrayList<PhieuNhapDTO> findAll() {
        if (listPhieuNhap == null || listPhieuNhap.isEmpty())
            listPhieuNhap = phieuNhapDAO.findAll();
        return listPhieuNhap;
    }

    @Override
    public PhieuNhapDTO findByID(int id) {
        for (PhieuNhapDTO phieuNhapDTO : listPhieuNhap)
            if (phieuNhapDTO.getID() == id)
                return phieuNhapDTO;
        return null;
    }

    @Override
    public ArrayList<PhieuNhapDTO> findByNCC(Integer MaNCC) {
        ArrayList<PhieuNhapDTO> result = new ArrayList<PhieuNhapDTO>();
        for (PhieuNhapDTO phieuNhapDTO : listPhieuNhap)
            if (phieuNhapDTO.getMaNCC().equals(MaNCC))
                result.add(phieuNhapDTO);
        return result;
    }

    @Override
    public ArrayList<PhieuNhapDTO> findByNCC(String TenNCC) {
        INhaCungCapBUS bus = new NhaCungCapBUS();
        for (NhaCungCapDTO dto : bus.findAll()) {
            if (StringUtils.containsIgnoreCase(dto.getTenNCC(), TenNCC))
                return findByNCC(dto.getMaNCC());
        }
        return null;
    }

    @Override
    public ArrayList<PhieuNhapDTO> findByNhanVien(Integer MaNV) {
        ArrayList<PhieuNhapDTO> result = new ArrayList<PhieuNhapDTO>();
        for (PhieuNhapDTO phieuNhapDTO : listPhieuNhap)
            if (phieuNhapDTO.getMaNV().equals(MaNV))
                result.add(phieuNhapDTO);
        return result;
    }

    @Override
    public ArrayList<PhieuNhapDTO> findByNhanVien(String TenNV) {
        INhanVienBUS nhanVienBUS = new NhanVienBUS();
        for (NhanVienDTO dto: nhanVienBUS.findAll()) {
            if (StringUtils.containsIgnoreCase(dto.getTen(), TenNV))
                return findByNhanVien(dto.getMaNV());
        }
        return null;
    }

    @Override
    public ArrayList<PhieuNhapDTO> findByNgayTao(Date tuNgay, Date denNgay) {
        ArrayList<PhieuNhapDTO> result = new ArrayList<PhieuNhapDTO>();
        for (PhieuNhapDTO phieuNhapDTO : listPhieuNhap) {
            if (tuNgay == null && phieuNhapDTO.getNgayTao().before(denNgay)) {
                result.add(phieuNhapDTO);
                continue;
            }
            if (denNgay == null && phieuNhapDTO.getNgayTao().after(tuNgay)) {
                result.add(phieuNhapDTO);
                continue;
            }
            if ((phieuNhapDTO.getNgayTao().after(tuNgay) && phieuNhapDTO.getNgayTao().before(denNgay))
                    || (tuNgay.equals(denNgay) && tuNgay.toLocalDate().getDayOfYear() == phieuNhapDTO.getNgayTao().toLocalDateTime().toLocalDate().getDayOfYear()))
                result.add(phieuNhapDTO);
        }
        return result;
    }

    @Override
    public ArrayList<PhieuNhapDTO> findByTongTien(Integer tien) {
        ArrayList<PhieuNhapDTO> result = new ArrayList<PhieuNhapDTO>();
        for (PhieuNhapDTO phieuNhapDTO : listPhieuNhap)
            if (phieuNhapDTO.getTongTien().equals(tien))
                result.add(phieuNhapDTO);
        return result;
    }

    @Override
    public ArrayList<PhieuNhapDTO> findByTinhTrang(Integer tinhTrang) {
        ArrayList<PhieuNhapDTO> result = new ArrayList<PhieuNhapDTO>();
        for (PhieuNhapDTO phieuNhapDTO : listPhieuNhap)
            if (phieuNhapDTO.getTinhTrang().equals(tinhTrang))
                result.add(phieuNhapDTO);
        return result;
    }

    @Override
    public Integer save(PhieuNhapDTO phieuNhap) throws Exception {
        if (isExist(phieuNhap))
            throw new Exception("Đã tồn tại phiếu nhập này.");
        Integer newID = phieuNhapDAO.save(phieuNhap);
        if (newID == null)
            throw new Exception("Phát sinh lỗi trong quá trình thêm phiếu nhập.");
        phieuNhap = phieuNhapDAO.findByID(newID);
        listPhieuNhap.add(phieuNhap);
        super.save(phieuNhap);
        return newID;
    }

    @Override
    public void update(PhieuNhapDTO phieuNhap) throws Exception {
        if (!isExist(phieuNhap))
            throw new Exception("Không tồn tại phiếu nhập (PN" + phieuNhap.getMaPN() + ").");
        int checkFlag = 0;
        if (phieuNhap.getTinhTrang() == 2) {
            phieuNhap.setTinhTrang(0);
            checkFlag = 1;
        }
        if (phieuNhap.getTinhTrang() == 3) {
            phieuNhap.setTinhTrang(1);
            checkFlag = 2;
        }
        if (!phieuNhapDAO.update(phieuNhap))
            throw new Exception("Phát sinh lỗi trong quá trình thêm phiếu nhập.");
        phieuNhap = phieuNhapDAO.findByID(phieuNhap.getMaPN());
        for (int i = 0; i < listPhieuNhap.size(); i++) {
            if (listPhieuNhap.get(i).getMaPN().equals(phieuNhap.getMaPN()))
                listPhieuNhap.set(i, phieuNhap);
        }
        if (checkFlag == 1) {
            ICT_PhieuNhapBUS ctPhieuNhapBUS = new CT_PhieuNhapBUS();
            ISanPhamBUS sanPhamBUS = new SanPhamBUS();
            for (CT_PhieuNhapDTO dto:ctPhieuNhapBUS.findByMaPN(phieuNhap.getMaPN())) {
                SanPhamDTO sanPhamDTO = sanPhamBUS.findByID(dto.getMaSP());
                int newSL = sanPhamDTO.getSoLuong() - dto.getSoLuong();
                sanPhamDTO.setSoLuong(newSL);
                sanPhamBUS.update(sanPhamDTO);
            }
        } else if (checkFlag == 2) {
            ICT_PhieuNhapBUS ctPhieuNhapBUS = new CT_PhieuNhapBUS();
            ISanPhamBUS sanPhamBUS = new SanPhamBUS();
            for (CT_PhieuNhapDTO dto:ctPhieuNhapBUS.findByMaPN(phieuNhap.getMaPN())) {
                SanPhamDTO sanPhamDTO = sanPhamBUS.findByID(dto.getMaSP());
                int newSL = sanPhamDTO.getSoLuong() + dto.getSoLuong();
                sanPhamDTO.setSoLuong(newSL);
                sanPhamBUS.update(sanPhamDTO);
            }
        }
        super.update(phieuNhap);
    }

    @Override
    public void delete(int id) throws Exception {
        Integer status = findByID(id).getTinhTrang();
        if (status == null)
            throw new Exception("Không thể xóa phiếu nhập (PN" + id + ").");
        if (status == 0) {
            ICT_PhieuNhapBUS ctPhieuNhapBUS = new CT_PhieuNhapBUS();
            ISanPhamBUS sanPhamBUS = new SanPhamBUS();
            for (CT_PhieuNhapDTO dto:ctPhieuNhapBUS.findByMaPN(id)) {
                SanPhamDTO sanPhamDTO = sanPhamBUS.findByID(dto.getMaSP());
                int newSL = sanPhamDTO.getSoLuong() + dto.getSoLuong();
                sanPhamDTO.setSoLuong(newSL);
                sanPhamBUS.update(sanPhamDTO);
            }
        }
        ICT_PhieuNhapBUS ctPhieuNhapBUS = new CT_PhieuNhapBUS();
        for (CT_PhieuNhapDTO dto:ctPhieuNhapBUS.findByMaPN(id))
            ctPhieuNhapBUS.delete(dto.getID());
        if (!phieuNhapDAO.delete(id))
            throw new Exception("Không thể xóa phiếu nhập (PN" + id + ").");
        listPhieuNhap.removeIf(phieuNhapDTO -> phieuNhapDTO.getMaPN() == id);
        super.delete(PhieuNhapDTO.class, id);
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
    public boolean isExist(PhieuNhapDTO phieuNhap) {
        if (phieuNhap.getMaPN() == null)
            return false;
        return listPhieuNhap.contains(phieuNhap);
    }

    @Override
    public int getTotalCount() {
        return listPhieuNhap.size();
    }
}

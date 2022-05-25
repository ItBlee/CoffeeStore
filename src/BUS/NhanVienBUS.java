package BUS;

import BUS.Abstract.AbstractHistoricBUS;
import BUS.Interfaces.IHoaDonBUS;
import BUS.Interfaces.INhanVienBUS;
import BUS.Interfaces.IPhieuNhapBUS;
import BUS.Interfaces.ITaiKhoanBUS;
import DAO.Interfaces.INhanVienDAO;
import DAO.NhanVienDAO;
import DTO.HoaDonDTO;
import DTO.NhanVienDTO;
import DTO.PhieuNhapDTO;
import DTO.TaiKhoanDTO;
import Utils.StringUtils;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;

public class NhanVienBUS extends AbstractHistoricBUS implements INhanVienBUS {
    private static ArrayList<NhanVienDTO> listNhanVien = null;
    private final INhanVienDAO nhanVienDAO;

    public NhanVienBUS() {
        this.nhanVienDAO = new NhanVienDAO();
        if (listNhanVien == null)
            listNhanVien = findAll();
    }

    @Override
    public ArrayList<NhanVienDTO> findAll() {
        if (listNhanVien == null || listNhanVien.isEmpty())
            listNhanVien = nhanVienDAO.findAll();
        return listNhanVien;
    }

    @Override
    public NhanVienDTO findByID(int id) {
        for (NhanVienDTO NhanVienDTO : listNhanVien)
            if (NhanVienDTO.getID() == id)
                return NhanVienDTO;
        return null;
    }

    @Override
    public NhanVienDTO findByTaiKhoan(Integer maTK) {
        for (NhanVienDTO NhanVienDTO : listNhanVien)
            if (NhanVienDTO.getMaTK().equals(maTK))
                return NhanVienDTO;
        return null;
    }

    @Override
    public NhanVienDTO findByTaiKhoan(String tenTK) {
        ITaiKhoanBUS bus = new TaiKhoanBUS();
        for (TaiKhoanDTO dto : bus.findAll()) {
            if (StringUtils.containsIgnoreCase(dto.getTenDangNhap(), tenTK))
                return findByTaiKhoan(dto.getMaTK());
        }
        return null;
    }

    @Override
    public ArrayList<NhanVienDTO> findByHoTen(String hoTen) {
        ArrayList<NhanVienDTO> result = new ArrayList<NhanVienDTO>();
        for (NhanVienDTO nhanVienDTO : listNhanVien)
            if (StringUtils.containsIgnoreCase(nhanVienDTO.getHoTen(), hoTen))
                result.add(nhanVienDTO);
        return result;
    }

    @Override
    public ArrayList<NhanVienDTO> findByNgaySinh(Date tuNgay, Date denNgay) {
        ArrayList<NhanVienDTO> result = new ArrayList<NhanVienDTO>();
        for (NhanVienDTO nhanVienDTO : listNhanVien) {
            if (tuNgay == null && nhanVienDTO.getNgaySinh().before(denNgay)) {
                result.add(nhanVienDTO);
                continue;
            }
            if (denNgay == null && nhanVienDTO.getNgaySinh().after(tuNgay)) {
                result.add(nhanVienDTO);
                continue;
            }
            if ((nhanVienDTO.getNgaySinh().after(tuNgay) && nhanVienDTO.getNgaySinh().before(denNgay))
                    || (tuNgay.equals(denNgay) && tuNgay.equals(new Date(nhanVienDTO.getNgaySinh().getTime()))))
                result.add(nhanVienDTO);
        }
        return result;
    }

    @Override
    public ArrayList<NhanVienDTO> findBySDT(Integer sdt) {
        ArrayList<NhanVienDTO> result = new ArrayList<NhanVienDTO>();
        for (NhanVienDTO nhanVienDTO : listNhanVien)
            if (StringUtils.containsIgnoreCase(nhanVienDTO.getSDT(), String.valueOf(sdt)))
                result.add(nhanVienDTO);
        return result;
    }

    @Override
    public ArrayList<NhanVienDTO> findByEmail(String email) {
        ArrayList<NhanVienDTO> result = new ArrayList<NhanVienDTO>();
        for (NhanVienDTO nhanVienDTO : listNhanVien)
            if (StringUtils.containsIgnoreCase(nhanVienDTO.getEmail(), email))
                result.add(nhanVienDTO);
        return result;
    }

    @Override
    public ArrayList<NhanVienDTO> findByGioiTinh(Integer gioiTinh) {
        ArrayList<NhanVienDTO> result = new ArrayList<NhanVienDTO>();
        for (NhanVienDTO nhanVienDTO : listNhanVien)
            if (nhanVienDTO.getGioiTinh().equals(gioiTinh))
                result.add(nhanVienDTO);
        return result;
    }

    @Override
    public ArrayList<NhanVienDTO> findByLuong(Integer luong) {
        ArrayList<NhanVienDTO> result = new ArrayList<NhanVienDTO>();
        for (NhanVienDTO nhanVienDTO : listNhanVien)
            if (nhanVienDTO.getLuong().equals(luong))
                result.add(nhanVienDTO);
        return result;
    }

    @Override
    public ArrayList<NhanVienDTO> findByTinhTrang(Integer TinhTrang) {
        ArrayList<NhanVienDTO> result = new ArrayList<NhanVienDTO>();
        for (NhanVienDTO nhanVienDTO : listNhanVien)
            if (nhanVienDTO.getTinhTrang().equals(TinhTrang))
                result.add(nhanVienDTO);
        return result;
    }

    @Override
    public Integer save(NhanVienDTO nhanVien) throws Exception {
        if (isExist(nhanVien))
            throw new Exception("Đã tồn tại nhân viên này.");
        Integer newID = nhanVienDAO.save(nhanVien);
        if (newID == null)
            throw new Exception("Phát sinh lỗi trong quá trình thêm nhân viên.");
        nhanVien = nhanVienDAO.findByID(newID);
        listNhanVien.add(nhanVien);
        super.save(nhanVien);
        return newID;
    }

    @Override
    public void update(NhanVienDTO nhanVien) throws Exception {
        if (!isExist(nhanVien))
            throw new Exception("Không tồn tại nhân viên (NV" + nhanVien.getMaNV() + ").");
        if (!nhanVienDAO.update(nhanVien))
            throw new Exception("Phát sinh lỗi trong quá trình sửa nhân viên.");
        nhanVien = nhanVienDAO.findByID(nhanVien.getMaNV());
        for (int i = 0; i < listNhanVien.size(); i++) {
            if (listNhanVien.get(i).getMaNV().equals(nhanVien.getMaNV()))
                listNhanVien.set(i, nhanVien);
        }
        super.update(nhanVien);
    }

    @Override
    public void delete(int id) throws Exception {
        IHoaDonBUS hoaDonBUS = new HoaDonBUS();
        for (HoaDonDTO dto:hoaDonBUS.findByNhanVien(id))
            hoaDonBUS.delete(dto.getID());
        IPhieuNhapBUS phieuNhapBUS = new PhieuNhapBUS();
        for (PhieuNhapDTO dto:phieuNhapBUS.findByNhanVien(id))
            phieuNhapBUS.delete(dto.getID());
        if (!nhanVienDAO.delete(id))
            throw new Exception("Không thể xóa nhân viên (NV" + id + ").");
        listNhanVien.removeIf(NhanVienDTO -> NhanVienDTO.getMaNV() == id);
        super.delete(NhanVienDTO.class, id);
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
    public boolean isExist(NhanVienDTO nhanVien) {
        if (nhanVien.getMaNV() == null)
            return false;
        return listNhanVien.contains(nhanVien);
    }

    @Override
    public int getTotalCount() {
        return listNhanVien.size();
    }
}

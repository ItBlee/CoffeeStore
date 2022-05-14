package BUS;

import BUS.Abstract.AbstractHistoricBUS;
import BUS.Interfaces.ICT_PhanQuyenBUS;
import BUS.Interfaces.INhanVienBUS;
import BUS.Interfaces.IPhanQuyenBUS;
import DAO.Interfaces.ITaiKhoanDAO;
import DAO.TaiKhoanDAO;
import DTO.NhanVienDTO;
import DTO.PhanQuyenDTO;
import DTO.TaiKhoanDTO;
import BUS.Interfaces.ITaiKhoanBUS;
import Utils.General;
import Utils.Role;
import Utils.Security;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;

public class TaiKhoanBUS extends AbstractHistoricBUS implements ITaiKhoanBUS {
    private static ArrayList<TaiKhoanDTO> listTaiKhoan = null;
    private final ITaiKhoanDAO taiKhoanDAO;

    public TaiKhoanBUS() {
        this.taiKhoanDAO = new TaiKhoanDAO();
        if (listTaiKhoan == null)
            listTaiKhoan = findAll();
    }

    @Override
    public ArrayList<TaiKhoanDTO> findAll() {
        return taiKhoanDAO.findAll();
    }

    @Override
    public TaiKhoanDTO findByID(int id) {
        for (TaiKhoanDTO taiKhoanDTO : listTaiKhoan)
            if (taiKhoanDTO.getID() == id)
                return taiKhoanDTO;
        return null;
    }

    @Override
    public TaiKhoanDTO findByTenDangNhap(String tenDangNhap) {
        return null;
    }

    @Override
    public ArrayList<TaiKhoanDTO> findByNgayTao(Date tuNgay, Date denNgay) {
        return null;
    }

    @Override
    public ArrayList<TaiKhoanDTO> findByNguoiTao(Integer nguoiTao) {
        return null;
    }

    @Override
    public ArrayList<TaiKhoanDTO> findByChucVu(String chucVu) {
        return null;
    }

    @Override
    public ArrayList<TaiKhoanDTO> findByTinhTrang(Integer tinhTrang) {
        return null;
    }

    @Override
    public void save(TaiKhoanDTO taiKhoan) throws Exception {
        if (isExist(taiKhoan))
            throw new Exception("Đã tồn tại tài khoản này.");
        Integer newID = taiKhoanDAO.save(taiKhoan);
        if (newID == null)
            throw new Exception("Phát sinh lỗi trong quá trình thêm tài khoản.");
        listTaiKhoan.add(taiKhoanDAO.findByID(newID));
        super.save(taiKhoan);
    }

    @Override
    public void update(TaiKhoanDTO taiKhoan) throws Exception {
        if (!isExist(taiKhoan))
            throw new Exception("Không tồn tại tài khoản (TK" + taiKhoan.getMaTK() + ").");
        if (!taiKhoanDAO.update(taiKhoan))
            throw new Exception("Phát sinh lỗi trong quá trình thêm tài khoản.");
        taiKhoan = taiKhoanDAO.findByID(taiKhoan.getMaTK());
        for (TaiKhoanDTO taiKhoanDTO : listTaiKhoan) {
            if (taiKhoanDTO.getMaTK().equals(taiKhoan.getMaTK())) {
                taiKhoanDTO = taiKhoan;
                return;
            }
        }
        listTaiKhoan.add(taiKhoan);
        super.update(taiKhoan);
    }

    @Override
    public void delete(int id) throws Exception {
        if (!taiKhoanDAO.delete(id))
            throw new Exception("Không thể xóa tài khoản (TK" + id + ").");
        listTaiKhoan.removeIf(taiKhoanDTO -> taiKhoanDTO.getMaTK() == id);
        super.delete(TaiKhoanDTO.class, id);
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
    public boolean isExist(TaiKhoanDTO taiKhoan) {
        return listTaiKhoan.contains(taiKhoan);
    }

    @Override
    public boolean login(String username, String password) {
        //d83c81ba59bb048750c30c9d3fd7abd07d27140ce6dfcf17244b944073438fce
        //cdd1a14c970e3c41
        TaiKhoanDTO findTaiKhoan = null;
        for (TaiKhoanDTO dto : listTaiKhoan) {
            String hashPassword = Security.applySha256(password, dto.getMatKhauSalt());
            if (dto.getTenDangNhap().equals(username)
                && dto.getMatKhauHash().equals(hashPassword)
                && dto.getTinhTrang() == 1) {
                findTaiKhoan = dto;
                break;
            }
        }
        INhanVienBUS nhanVienBUS = new NhanVienBUS();
        IPhanQuyenBUS phanQuyenBUS = new PhanQuyenBUS();
        ICT_PhanQuyenBUS ictPhanQuyenBUS = new CT_PhanQuyenBUS();
        NhanVienDTO getUser = nhanVienBUS.findByMaTK(findTaiKhoan.getMaTK());
        PhanQuyenDTO getRole = phanQuyenBUS.findByID(findTaiKhoan.getMaPQ());
        if (getUser == null || getRole == null)
            return false;
        General.CURRENT_USER = getUser;
        General.CURRENT_ROLE = new Role(getRole);
        General.CURRENT_ROLE.setQuyenHD(ictPhanQuyenBUS.findByID(getRole.getQuyenHD()));
        General.CURRENT_ROLE.setQuyenSP(ictPhanQuyenBUS.findByID(getRole.getQuyenSP()));
        General.CURRENT_ROLE.setQuyenPN(ictPhanQuyenBUS.findByID(getRole.getQuyenPN()));
        General.CURRENT_ROLE.setQuyenNCC(ictPhanQuyenBUS.findByID(getRole.getQuyenNCC()));
        General.CURRENT_ROLE.setQuyenKH(ictPhanQuyenBUS.findByID(getRole.getQuyenKH()));
        General.CURRENT_ROLE.setQuyenKM(ictPhanQuyenBUS.findByID(getRole.getQuyenKM()));
        General.CURRENT_ROLE.setQuyenTK(ictPhanQuyenBUS.findByID(getRole.getQuyenTK()));
        General.CURRENT_ROLE.setQuyenExcel(ictPhanQuyenBUS.findByID(getRole.getQuyenExcel()));
        General.CURRENT_ROLE.setQuyenNV(ictPhanQuyenBUS.findByID(getRole.getQuyenNV()));
        return General.CURRENT_USER != null && General.CURRENT_ROLE.getPhanQuyen().getMaPQ() != null;
    }

    @Override
    public void logout() {
        General.CURRENT_USER = null;
    }

    @Override
    public int getTotalCount() {
        return listTaiKhoan.size();
    }
}

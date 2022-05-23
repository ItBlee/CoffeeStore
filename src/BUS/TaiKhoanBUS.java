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
import DTO.Role;
import Utils.Security;
import Utils.StringUtils;

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
        if (listTaiKhoan == null || listTaiKhoan.isEmpty())
            listTaiKhoan = taiKhoanDAO.findAll();
        return listTaiKhoan;
    }

    @Override
    public TaiKhoanDTO findByID(int id) {
        for (TaiKhoanDTO taiKhoanDTO : listTaiKhoan)
            if (taiKhoanDTO.getID() == id)
                return taiKhoanDTO;
        return null;
    }

    @Override
    public ArrayList<TaiKhoanDTO> findByMaPQ(Integer maPQ) {
        ArrayList<TaiKhoanDTO> result = new ArrayList<TaiKhoanDTO>();
        for (TaiKhoanDTO taiKhoanDTO : listTaiKhoan)
            if (taiKhoanDTO.getMaPQ().equals(maPQ))
                result.add(taiKhoanDTO);
        return result;
    }

    @Override
    public ArrayList<TaiKhoanDTO> findByTenDangNhap(String tenDangNhap) {
        ArrayList<TaiKhoanDTO> result = new ArrayList<TaiKhoanDTO>();
        for (TaiKhoanDTO taiKhoanDTO : listTaiKhoan)
            if (StringUtils.containsIgnoreCase(taiKhoanDTO.getTenDangNhap(), tenDangNhap))
                result.add(taiKhoanDTO);
        return result;
    }

    @Override
    public ArrayList<TaiKhoanDTO> findByNgayTao(Date tuNgay, Date denNgay) {
        ArrayList<TaiKhoanDTO> result = new ArrayList<TaiKhoanDTO>();
        for (TaiKhoanDTO taiKhoanDTO : listTaiKhoan)
            if ((taiKhoanDTO.getNgayTao().after(tuNgay) && taiKhoanDTO.getNgayTao().before(denNgay))
                || (tuNgay.equals(denNgay) && tuNgay.equals(new Date(taiKhoanDTO.getNgayTao().getTime()))))
                result.add(taiKhoanDTO);
        return result;
    }

    @Override
    public ArrayList<TaiKhoanDTO> findByNguoiTao(Integer nguoiTao) {
        ArrayList<TaiKhoanDTO> result = new ArrayList<TaiKhoanDTO>();
        for (TaiKhoanDTO taiKhoanDTO : listTaiKhoan)
            if (taiKhoanDTO.getNguoiTao().equals(nguoiTao))
                result.add(taiKhoanDTO);
        return result;
    }

    @Override
    public ArrayList<TaiKhoanDTO> findByTinhTrang(Integer tinhTrang) {
        ArrayList<TaiKhoanDTO> result = new ArrayList<TaiKhoanDTO>();
        for (TaiKhoanDTO taiKhoanDTO : listTaiKhoan)
            if (taiKhoanDTO.getTinhTrang().equals(tinhTrang))
                result.add(taiKhoanDTO);
        return result;
    }

    @Override
    public Integer save(TaiKhoanDTO taiKhoan) throws Exception {
        if (isExist(taiKhoan))
            throw new Exception("Đã tồn tại tài khoản này.");
        Integer newID = taiKhoanDAO.save(taiKhoan);
        if (newID == null)
            throw new Exception("Phát sinh lỗi trong quá trình thêm tài khoản.");
        taiKhoan = taiKhoanDAO.findByID(newID);
        listTaiKhoan.add(taiKhoan);
        super.save(taiKhoan);
        return newID;
    }

    @Override
    public void update(TaiKhoanDTO taiKhoan) throws Exception {
        if (!isExist(taiKhoan))
            throw new Exception("Không tồn tại tài khoản (TK" + taiKhoan.getMaTK() + ").");
        if (!taiKhoanDAO.update(taiKhoan))
            throw new Exception("Phát sinh lỗi trong quá trình thêm tài khoản.");
        taiKhoan = taiKhoanDAO.findByID(taiKhoan.getMaTK());
        for (int i = 0; i < listTaiKhoan.size(); i++) {
            if (listTaiKhoan.get(i).getMaTK().equals(taiKhoan.getMaTK()))
                listTaiKhoan.set(i, taiKhoan);
        }
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
        if (taiKhoan.getMaTK() == null)
            return false;
        return listTaiKhoan.contains(taiKhoan);
    }

    @Override
    public boolean login(String username, String password) {
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

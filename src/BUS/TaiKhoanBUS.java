package BUS;

import BUS.Abstract.AbstractHistoricBUS;
import DAO.Interfaces.ITaiKhoanDAO;
import DAO.NhanVienDAO;
import DAO.TaiKhoanDAO;
import DTO.TaiKhoanDTO;
import BUS.Interfaces.ITaiKhoanBUS;
import Utils.General;

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
    public ArrayList<TaiKhoanDTO> findByNgayTao(Date ngayTao) {
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
        TaiKhoanDTO findTaiKhoan = null;
        for (TaiKhoanDTO dto : listTaiKhoan) {
            if (dto.getTenDangNhap().equals(username)
                && dto.getMatKhau().equals(password)
                && dto.getTinhTrang() == 1) {
                findTaiKhoan = dto;
                break;
            }
        }
        NhanVienDAO nhanVienDAO = new NhanVienDAO();
        General.CURRENT_USER = nhanVienDAO.findByMaTK(findTaiKhoan.getMaTK());
        return General.CURRENT_USER != null;
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

package BUS;

import DAO.Interfaces.ITaiKhoanDAO;
import DAO.TaiKhoanDAO;
import DTO.TaiKhoanDTO;
import BUS.Interfaces.ITaiKhoanBUS;
import Utils.General;

import java.util.ArrayList;
import java.util.HashMap;

public class TaiKhoanBUS implements ITaiKhoanBUS {
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
            if (taiKhoanDTO.getMaTK() == id)
                return taiKhoanDTO;
        return null;
    }

    @Override
    public boolean save(TaiKhoanDTO taikhoan) {
        if (taiKhoanDAO.save(taikhoan) == null)
            return false;
        listTaiKhoan.add(taikhoan);
        return true;
    }

    @Override
    public boolean update(TaiKhoanDTO taikhoan) {
        if (!taiKhoanDAO.update(taikhoan))
            return false;
        for (TaiKhoanDTO taiKhoanDTO : listTaiKhoan) {
            if (taiKhoanDTO.getMaTK() == taikhoan.getMaTK()) {
                taiKhoanDTO = taikhoan;
                return true;
            }
        }
        listTaiKhoan.add(taikhoan);
        return true;
    }

    @Override
    public boolean delete(int id) {
        if (taiKhoanDAO.delete(id)) {
            listTaiKhoan.removeIf(taiKhoanDTO -> taiKhoanDTO.getMaTK() == id);
            return true;
        }
        return false;
    }

    @Override
    public HashMap<Integer, Boolean> delete(int[] ids) {
        HashMap<Integer, Boolean> report = new HashMap<Integer, Boolean>();
        boolean resultExecute;
        for (int id:ids) {
            resultExecute = delete(id);
            report.put(id, resultExecute);
        }
        return report;
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
        General.CURRENT_USER = findTaiKhoan;
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

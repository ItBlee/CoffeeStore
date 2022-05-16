package BUS;

import BUS.Abstract.AbstractHistoricBUS;
import BUS.Interfaces.IKhachHangBUS;
import DTO.KhachHangDTO;

import java.util.ArrayList;
import java.util.HashMap;

public class KhachHangBUS extends AbstractHistoricBUS implements IKhachHangBUS {
    @Override
    public ArrayList<KhachHangDTO> findAll() {
        return null;
    }

    @Override
    public KhachHangDTO findByID(int id) {
        return null;
    }

    @Override
    public KhachHangDTO findByMaTK(Integer maTK) {
        return null;
    }

    @Override
    public ArrayList<KhachHangDTO> findByHoTen(String hoTen) {
        return null;
    }

    @Override
    public ArrayList<KhachHangDTO> findBySDT(String sdt) {
        return null;
    }

    @Override
    public ArrayList<KhachHangDTO> findByDiaChi(String diaChi) {
        return null;
    }

    @Override
    public ArrayList<KhachHangDTO> findBySoTaiKhoan(String soTaiKhoan) {
        return null;
    }

    @Override
    public ArrayList<KhachHangDTO> findByTinhTrang(String tinhTrang) {
        return null;
    }

    @Override
    public void save(KhachHangDTO nhanVien) throws Exception {

    }

    @Override
    public void update(KhachHangDTO nhanVien) throws Exception {

    }

    @Override
    public void delete(int id) throws Exception {

    }

    @Override
    public HashMap<Integer, Boolean> delete(int[] ids) {
        return null;
    }

    @Override
    public boolean isExist(KhachHangDTO nhanVien) {
        return false;
    }

    @Override
    public int getTotalCount() {
        return 0;
    }
}

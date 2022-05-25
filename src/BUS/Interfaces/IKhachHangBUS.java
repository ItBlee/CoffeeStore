package BUS.Interfaces;

import BUS.Interfaces.common.ICrudBUS;
import BUS.Interfaces.common.ISearchableBUS;
import DTO.KhachHangDTO;

import java.util.ArrayList;

public interface IKhachHangBUS extends ISearchableBUS<KhachHangDTO>, ICrudBUS<KhachHangDTO> {
    ArrayList<KhachHangDTO> findByHoTen(String hoTen);
    ArrayList<KhachHangDTO> findBySDT(String sdt);
    ArrayList<KhachHangDTO> findByDiaChi(String diaChi);
    ArrayList<KhachHangDTO> findByEmail(String mail);
    ArrayList<KhachHangDTO> findByTinhTrang(Integer tinhTrang);
}

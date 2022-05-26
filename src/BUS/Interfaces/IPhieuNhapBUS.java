package BUS.Interfaces;

import BUS.Interfaces.common.ICrudBUS;
import BUS.Interfaces.common.ISearchableBUS;
import DTO.PhieuNhapDTO;

import java.sql.Date;
import java.util.ArrayList;

public interface IPhieuNhapBUS extends ISearchableBUS<PhieuNhapDTO>, ICrudBUS<PhieuNhapDTO> {
    ArrayList<PhieuNhapDTO> findByNCC(Integer maNCC);
    ArrayList<PhieuNhapDTO> findByNCC(String TenNCC);
    ArrayList<PhieuNhapDTO> findByNhanVien(Integer maNV);
    ArrayList<PhieuNhapDTO> findByNhanVien(String TenNV);
    ArrayList<PhieuNhapDTO> findByNgayTao(Date tuNgay, Date denNgay);
    ArrayList<PhieuNhapDTO> findByTongTien(Integer tien);
    ArrayList<PhieuNhapDTO> findByTinhTrang(Integer tinhTrang);
}

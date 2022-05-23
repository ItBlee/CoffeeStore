package BUS.Interfaces;

import BUS.Interfaces.common.ICrudBUS;
import BUS.Interfaces.common.ISearchableBUS;
import DTO.PhieuNhapDTO;
import DTO.SanPhamDTO;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;

public interface IPhieuNhapBUS extends ISearchableBUS<PhieuNhapDTO>, ICrudBUS<PhieuNhapDTO> {
    ArrayList<PhieuNhapDTO> findByMaNCC(Integer maNCC);
    ArrayList<PhieuNhapDTO> findByMaNV(Integer maNV);
    ArrayList<PhieuNhapDTO> findBySDT(String sdt);
    ArrayList<PhieuNhapDTO> findByNgayTao(Date tuNgay, Date denNgay);
    ArrayList<PhieuNhapDTO> findByTongTien(Integer from, Integer to);
}

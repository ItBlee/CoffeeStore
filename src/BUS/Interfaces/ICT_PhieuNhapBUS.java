package BUS.Interfaces;

import BUS.Interfaces.common.ICrudBUS;
import BUS.Interfaces.common.ISearchableBUS;
import DTO.CT_PhieuNhapDTO;

import java.util.ArrayList;

public interface ICT_PhieuNhapBUS extends ISearchableBUS<CT_PhieuNhapDTO>, ICrudBUS<CT_PhieuNhapDTO> {
    ArrayList<CT_PhieuNhapDTO> findByMaSP(Integer maSP);
    ArrayList<CT_PhieuNhapDTO> findBySoLuong(Integer from, Integer to);
    ArrayList<CT_PhieuNhapDTO> findByDonGia(Integer from, Integer to);
    ArrayList<CT_PhieuNhapDTO> findByThanhTien(Integer from, Integer to);
}

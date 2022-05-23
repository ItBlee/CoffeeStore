package BUS.Interfaces;

import BUS.Interfaces.common.ICrudBUS;
import BUS.Interfaces.common.ISearchableBUS;
import DTO.CT_HoaDonDTO;
import DTO.CT_PhanQuyenDTO;

import java.util.ArrayList;
import java.util.HashMap;

public interface ICT_HoaDonBUS extends ISearchableBUS<CT_HoaDonDTO>, ICrudBUS<CT_HoaDonDTO> {
    CT_HoaDonDTO findByMaSP(Integer maSP);
    ArrayList<CT_HoaDonDTO> findBySoLuong(Integer from, Integer to);
    ArrayList<CT_HoaDonDTO> findByDonGia(Integer from, Integer to);
    ArrayList<CT_HoaDonDTO> findByThanhTien(Integer from, Integer to);
}

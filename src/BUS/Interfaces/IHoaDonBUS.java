package BUS.Interfaces;

import BUS.Interfaces.common.ICrudBUS;
import BUS.Interfaces.common.ISearchableBUS;
import DTO.HoaDonDTO;

import java.sql.Date;
import java.util.ArrayList;

public interface IHoaDonBUS extends ISearchableBUS<HoaDonDTO>, ICrudBUS<HoaDonDTO> {
    HoaDonDTO findByMaKH(Integer maKH);
    HoaDonDTO findByMaNV(Integer maNV);
    ArrayList<HoaDonDTO> findByNgayLap(Date tuNgay, Date denNgay);
    ArrayList<HoaDonDTO> findByTongTien(Integer from, Integer to);
    ArrayList<HoaDonDTO> findByThanhTien(Integer from, Integer to);
}

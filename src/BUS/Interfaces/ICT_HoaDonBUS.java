package BUS.Interfaces;

import BUS.Interfaces.common.ICrudBUS;
import BUS.Interfaces.common.ISearchableBUS;
import DTO.CT_HoaDonDTO;

import java.util.ArrayList;

public interface ICT_HoaDonBUS extends ISearchableBUS<CT_HoaDonDTO>, ICrudBUS<CT_HoaDonDTO> {
    ArrayList<CT_HoaDonDTO> findByMaHD(Integer MaHD);
    ArrayList<CT_HoaDonDTO> findByMaSP(Integer MaSP);
}

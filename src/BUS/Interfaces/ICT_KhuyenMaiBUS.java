package BUS.Interfaces;

import BUS.Interfaces.common.ICrudBUS;
import BUS.Interfaces.common.ISearchableBUS;
import DTO.CT_KhuyenMaiDTO;

import java.util.ArrayList;

public interface ICT_KhuyenMaiBUS extends ISearchableBUS<CT_KhuyenMaiDTO>, ICrudBUS<CT_KhuyenMaiDTO> {
    ArrayList<CT_KhuyenMaiDTO> findByMaKM(Integer MaKM);
    ArrayList<CT_KhuyenMaiDTO> findByMaSP(Integer MaSP);
}

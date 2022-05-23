package BUS.Interfaces;

import BUS.Interfaces.common.ICrudBUS;
import BUS.Interfaces.common.ISearchableBUS;
import DTO.CT_KhuyenMaiDTO;
import DTO.CT_PhanQuyenDTO;

import java.util.ArrayList;
import java.util.HashMap;

public interface ICT_KhuyenMaiBUS extends ISearchableBUS<CT_KhuyenMaiDTO>, ICrudBUS<CT_KhuyenMaiDTO> {
    CT_KhuyenMaiDTO findByMaSP(Integer maSP);
}

package BUS.Interfaces;

import BUS.Interfaces.common.ICrudBUS;
import BUS.Interfaces.common.ISearchableBUS;
import DTO.KhuyenMaiDTO;

import java.sql.Date;
import java.util.ArrayList;

public interface IKhuyenMaiBUS extends ISearchableBUS<KhuyenMaiDTO>, ICrudBUS<KhuyenMaiDTO> {
    ArrayList<KhuyenMaiDTO> findByTieuDe(String tieuDe);
    ArrayList<KhuyenMaiDTO> findByThoiGian(Date tuNgay, Date denNgay);
}

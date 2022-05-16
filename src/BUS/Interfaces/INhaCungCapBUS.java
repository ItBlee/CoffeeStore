package BUS.Interfaces;

import BUS.Interfaces.common.ICrudBUS;
import BUS.Interfaces.common.ISearchableBUS;
import DTO.NhaCungCapDTO;

import java.util.ArrayList;

public interface INhaCungCapBUS extends ISearchableBUS<NhaCungCapDTO>, ICrudBUS<NhaCungCapDTO> {
    ArrayList<NhaCungCapDTO> findByTenNCC(String tenNCC);
    ArrayList<NhaCungCapDTO> findBySDT(String sdt);
    ArrayList<NhaCungCapDTO> findByDiaChi(String diaChi);
    ArrayList<NhaCungCapDTO> findBySoTaiKhoan(String stk);
    ArrayList<NhaCungCapDTO> findByTinhTrang(Integer tinhTrang);
}

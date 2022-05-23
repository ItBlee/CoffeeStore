package BUS.Interfaces;

import BUS.Interfaces.common.ICrudBUS;
import BUS.Interfaces.common.ISearchableBUS;
import DTO.SanPhamDTO;
import DTO.TaiKhoanDTO;

import java.util.ArrayList;
import java.util.HashMap;

public interface ISanPhamBUS extends ISearchableBUS<SanPhamDTO>, ICrudBUS<SanPhamDTO> {
    ArrayList<SanPhamDTO> findByMaNCC(Integer maNCC);
    ArrayList<SanPhamDTO> findByMaLoai(Integer maLoai);
    ArrayList<SanPhamDTO> findByTenSP(String tenSP);
    ArrayList<SanPhamDTO> findByDonGia(Integer from, Integer to);
    ArrayList<SanPhamDTO> findByDonVi(String donVi);
    ArrayList<SanPhamDTO> findBySoLuong(Integer from, Integer to);
    ArrayList<SanPhamDTO> findByTinhTrang(Integer tinhTrang);
}

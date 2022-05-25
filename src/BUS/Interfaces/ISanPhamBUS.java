package BUS.Interfaces;

import BUS.Interfaces.common.ICrudBUS;
import BUS.Interfaces.common.ISearchableBUS;
import DTO.SanPhamDTO;

import java.util.ArrayList;

public interface ISanPhamBUS extends ISearchableBUS<SanPhamDTO>, ICrudBUS<SanPhamDTO> {
    ArrayList<SanPhamDTO> findByNhaCungCap(Integer MaNCC);
    ArrayList<SanPhamDTO> findByNhaCungCap(String TenNCC);
    ArrayList<SanPhamDTO> findByLoaiSP(Integer MaLoai);
    ArrayList<SanPhamDTO> findByLoaiSP(String TenLoai);
    ArrayList<SanPhamDTO> findByTenSP(String tenSP);
    ArrayList<SanPhamDTO> findByDonGia(Integer tien);
    ArrayList<SanPhamDTO> findByDonVi(String donVi);
    ArrayList<SanPhamDTO> findBySoLuong(Integer soluong);
    ArrayList<SanPhamDTO> findByTinhTrang(Integer tinhTrang);
}

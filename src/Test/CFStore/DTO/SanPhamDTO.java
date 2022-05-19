package Test.CFStore.DTO;

import DTO.Interface.IEntity;
import java.sql.Date;

public class SanPhamDTO implements IEntity {
    private Integer MaSP;
    private Integer MaLoai;
    private Integer MaNCC;
    private String TenSP;
    private String MoTa;
    private String HinhAnh;
    private Integer DonGia;
    private String DonVi;
    private Integer SoLuong;
    private Integer TinhTrang;

    public Integer getMaSP() {
        return MaSP;
    }

    public void setMaSP(Integer MaSP) {
        this.MaSP = MaSP;
    }

    public Integer getMaLoai() {
        return MaLoai;
    }

    public void setMaLoai(Integer MaLoai) {
        this.MaLoai = MaLoai;
    }

    public Integer getMaNCC() {
        return MaNCC;
    }

    public void setMaNCC(Integer MaNCC) {
        this.MaNCC = MaNCC;
    }
    
    public String getTenSP() {
        return TenSP;
    }

    public void setTenSP(String TenSP) {
        this.TenSP = TenSP;
    }
    
    public String getMoTa() {
        return MoTa;
    }

    public void setMoTa(String MoTa) {
        this.MoTa = MoTa;
    }

    public String getHinhAnh() {
        return HinhAnh;
    }

    public void setHinhAnh(String HinhAnh) {
        this.HinhAnh = HinhAnh;
    }

    public Integer getDonGia() {
        return DonGia;
    }

    public void setDonGia(Integer DonGia) {
        this.DonGia = DonGia;
    }
    
    public String getDonVi() {
        return DonVi;
    }

    public void setDonVi(String DonVi) {
        this.DonVi = DonVi;
    }
    
    public Integer getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(Integer SoLuong) {
        this.SoLuong = SoLuong;
    }
    
    public Integer getTinhTrang() {
        return TinhTrang;
    }

    public void setTinhTrang(Integer TinhTrang) {
        this.TinhTrang = TinhTrang;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SanPhamDTO)) return false;
        SanPhamDTO that = (SanPhamDTO) o;
        return getMaSP().equals(that.getMaSP()) || getMaLoai().equals(that.getMaLoai());
    }
    
    
    
    @Override
    public Integer getID() {
        return null;
    }

    @Override
    public void setID(Integer id) {

    }


    
    
    
}

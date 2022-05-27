package DTO;

import DTO.Interface.IEntity;

public class SanPhamDTO implements IEntity {
    private Integer MaSP;
    private Integer MaLoai;
    private Integer MaNCC;
    private String  TenSP;
    private String  MoTa;
    private String  HinhAnh;
    private Integer DonGia;
    private String  DonVi;
    private Integer SoLuong;
    private Integer TinhTrang;
    
    public SanPhamDTO() {
    }

    @Override
    public Integer getID() {
        return MaSP;
    }

    @Override
    public void setID(Integer id) {
        MaSP = id;
    }

    public Integer getMaSP() {
        return MaSP;
    }

    public void setMaSP(Integer maSP) {
        MaSP = maSP;
    }

    public Integer getMaLoai() {
        return MaLoai;
    }

    public void setMaLoai(Integer maLoai) {
        MaLoai = maLoai;
    }

    public Integer getMaNCC() {
        return MaNCC;
    }

    public void setMaNCC(Integer maNCC) {
        MaNCC = maNCC;
    }

    public String getTenSP() {
        return TenSP;
    }

    public void setTenSP(String tenSP) {
        TenSP = tenSP;
    }

    public String getMoTa() {
        return MoTa;
    }

    public void setMoTa(String moTa) {
        MoTa = moTa;
    }

    public String getHinhAnh() {
        return HinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        HinhAnh = hinhAnh;
    }

    public Integer getDonGia() {
        return DonGia;
    }

    public void setDonGia(Integer donGia) {
        DonGia = donGia;
    }

    public String getDonVi() {
        return DonVi;
    }

    public void setDonVi(String donVi) {
        DonVi = donVi;
    }

    public Integer getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(Integer soLuong) {
        SoLuong = soLuong;
    }

    public Integer getTinhTrang() {
        return TinhTrang;
    }

    public void setTinhTrang(Integer tinhTrang) {
        TinhTrang = tinhTrang;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SanPhamDTO)) return false;
        SanPhamDTO that = (SanPhamDTO) o;
        return getMaSP().equals(that.getMaSP()) || getTenSP().equals(that.getTenSP());
    }
}

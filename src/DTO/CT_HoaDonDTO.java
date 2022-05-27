package DTO;

import DTO.Interface.IDetailEntity;

public class CT_HoaDonDTO implements IDetailEntity {
    private Integer MaCTHD;
    private Integer MaHD;
    private Integer MaSP;
    private Integer SoLuong;
    private Integer DonGia;
    private Integer TienKhuyenMai;  
    private Integer ThanhTien;     
    
    public CT_HoaDonDTO(){       
    }
   @Override
    public Integer getID() {
        return this.MaCTHD;
    }

    @Override
    public void setID(Integer id) {
        this.MaCTHD = id;
    }

    @Override
    public Integer getForeignID() {
        return MaSP;
    }

    @Override
    public void setForeignID(Integer foreignID) {
        MaSP = foreignID;
    }

    public Integer getMaCTHD() {
        return MaCTHD;
    }

    public void setMaCTHD(Integer maCTHD) {
        MaCTHD = maCTHD;
    }

    public Integer getMaHD() {
        return MaHD;
    }

    public void setMaHD(Integer maHD) {
        MaHD = maHD;
    }

    public Integer getMaSP() {
        return MaSP;
    }

    public void setMaSP(Integer maSP) {
        MaSP = maSP;
    }

    public Integer getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(Integer soLuong) {
        SoLuong = soLuong;
    }

    public Integer getDonGia() {
        return DonGia;
    }

    public void setDonGia(Integer donGia) {
        DonGia = donGia;
    }

    public Integer getTienKhuyenMai() {
        return TienKhuyenMai;
    }

    public void setTienKhuyenMai(Integer tienKhuyenMai) {
        TienKhuyenMai = tienKhuyenMai;
    }

    public Integer getThanhTien() {
        return ThanhTien;
    }

    public void setThanhTien(Integer thanhTien) {
        ThanhTien = thanhTien;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CT_HoaDonDTO)) return false;
        CT_HoaDonDTO that = (CT_HoaDonDTO) o;
        return getMaHD().equals(that.getMaHD()) && getMaSP().equals(that.getMaSP());
    }
}

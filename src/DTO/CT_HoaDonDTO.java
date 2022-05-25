package DTO;

import DTO.Interface.IEntity;

public class CT_HoaDonDTO implements IEntity {
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
}

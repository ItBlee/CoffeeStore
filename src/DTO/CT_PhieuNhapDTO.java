package DTO;

import DTO.Interface.IDetailEntity;

public class CT_PhieuNhapDTO implements IDetailEntity {
    private Integer MaCTPN;
    private Integer MaPN;
    private Integer MaSP;
    private Integer SoLuong;
    private Integer DonGia;
    private Integer ThanhTien;  

    public CT_PhieuNhapDTO(){
        
    }
        
    @Override
    public Integer getID() {
        return this.MaCTPN;
    }

    @Override
    public void setID(Integer id) {
        this.MaCTPN = id;
    }

    @Override
    public Integer getForeignID() {
        return MaSP;
    }

    @Override
    public void setForeignID(Integer foreignID) {
        MaSP = foreignID;
    }

    public Integer getMaCTPN() {
        return MaCTPN;
    }

    public void setMaCTPN(Integer maCTPN) {
        MaCTPN = maCTPN;
    }

    public Integer getMaPN() {
        return MaPN;
    }

    public void setMaPN(Integer maPN) {
        MaPN = maPN;
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

    public Integer getThanhTien() {
        return ThanhTien;
    }

    public void setThanhTien(Integer thanhTien) {
        ThanhTien = thanhTien;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CT_PhieuNhapDTO)) return false;
        CT_PhieuNhapDTO that = (CT_PhieuNhapDTO) o;
        return getMaPN().equals(that.getMaPN()) && getMaSP().equals(that.getMaSP());
    }
}

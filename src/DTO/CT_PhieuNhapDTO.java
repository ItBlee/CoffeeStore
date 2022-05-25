package DTO;

import DTO.Interface.IEntity;

public class CT_PhieuNhapDTO implements IEntity {
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
}

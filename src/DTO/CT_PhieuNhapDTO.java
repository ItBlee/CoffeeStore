package DTO;

import DTO.Interface.IEntity;

public class CT_PhieuNhapDTO implements IEntity {
    private Integer MaTK;
    private Integer MaPN;
    private Integer MaSP;
    private Integer SoLuong;
    private Integer DonGia;
    private Integer ThanhTien;  

    public CT_PhieuNhapDTO(){
        
    }
        
    @Override
    public Integer getID() {
        return this.MaTK;
    }

    @Override
    public void setID(Integer id) {
        this.MaTK = id;
    }

    public Integer getMaTK() {
        return this.MaTK;
    }

    public void setMaTK(Integer maTK) {
        this.MaTK = maTK;
    }
    public Integer getMaPN() {
        return this.MaPN;
    }
    public void setMaPN(Integer maPN){
        this.MaPN = maPN;
    }
    public Integer getMaSP() {
        return this.MaSP;
    }
    public void setMaSP(Integer maSP){
        this.MaSP = maSP;
    }
    public Integer getSoLuong() {
        return this.SoLuong;
    }
    public void setSoLuong(Integer soLuong){
        this.SoLuong = soLuong;
    }
    public Integer getDonGia() {
        return this.DonGia;
    }
    public void setDonGia(Integer donGia){
        this.DonGia = donGia;
    }
    public Integer getThanhTien() {
        return this.ThanhTien;
    }
    public void setThanhTien(Integer thanhTien){
        this.ThanhTien = thanhTien;
    }    
}

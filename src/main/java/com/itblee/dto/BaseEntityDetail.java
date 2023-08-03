package com.itblee.dto;

public abstract class BaseEntityDetail extends BaseEntity {
    private Integer soLuong;
    private Integer donGia;
    private Integer thanhTien;

    public abstract Integer getForeignID();

    public abstract void setForeignID(Integer foreignID);

    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

    public Integer getDonGia() {
        return donGia;
    }

    public void setDonGia(Integer donGia) {
        this.donGia = donGia;
    }

    public Integer getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(Integer thanhTien) {
        this.thanhTien = thanhTien;
    }
}

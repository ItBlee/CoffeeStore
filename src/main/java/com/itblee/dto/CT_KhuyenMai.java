package com.itblee.dto;

public class CT_KhuyenMai extends BaseEntity {
    private Integer maCTKM;
    private Integer maKM;
    private Integer maSP;
    private Integer giamGia;

    @Override
    public Integer getID() {
        return this.maCTKM;
    }

    @Override
    public void setID(Integer id) {
        this.maCTKM = id;
    }

    public Integer getMaCTKM() {
        return maCTKM;
    }

    public void setMaCTKM(Integer maCTKM) {
        this.maCTKM = maCTKM;
    }

    public Integer getMaKM() {
        return maKM;
    }

    public void setMaKM(Integer maKM) {
        this.maKM = maKM;
    }

    public Integer getMaSP() {
        return maSP;
    }

    public void setMaSP(Integer maSP) {
        this.maSP = maSP;
    }

    public Integer getGiamGia() {
        return giamGia;
    }

    public void setGiamGia(Integer giamGia) {
        this.giamGia = giamGia;
    }
}

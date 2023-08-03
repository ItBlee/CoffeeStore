package com.itblee.dto;

public class CT_HoaDon extends BaseEntityDetail {
    private Integer maCTHD;
    private Integer maHD;
    private Integer maSP;
    private Integer tienKhuyenMai;

    @Override
    public Integer getID() {
        return this.maCTHD;
    }

    @Override
    public void setID(Integer id) {
        this.maCTHD = id;
    }

    @Override
    public Integer getForeignID() {
        return maSP;
    }

    @Override
    public void setForeignID(Integer foreignID) {
        maSP = foreignID;
    }

    public Integer getMaCTHD() {
        return maCTHD;
    }

    public void setMaCTHD(Integer maCTHD) {
        this.maCTHD = maCTHD;
    }

    public Integer getMaHD() {
        return maHD;
    }

    public void setMaHD(Integer maHD) {
        this.maHD = maHD;
    }

    public Integer getMaSP() {
        return maSP;
    }

    public void setMaSP(Integer maSP) {
        this.maSP = maSP;
    }

    public Integer getTienKhuyenMai() {
        return tienKhuyenMai;
    }

    public void setTienKhuyenMai(Integer tienKhuyenMai) {
        this.tienKhuyenMai = tienKhuyenMai;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CT_HoaDon)) return false;
        CT_HoaDon that = (CT_HoaDon) o;
        return getMaHD().equals(that.getMaHD()) && getMaSP().equals(that.getMaSP());
    }
}

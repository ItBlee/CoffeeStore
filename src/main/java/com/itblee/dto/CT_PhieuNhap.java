package com.itblee.dto;

public class CT_PhieuNhap extends BaseEntityDetail {
    private Integer maCTPN;
    private Integer maPN;
    private Integer maSP;

    @Override
    public Integer getID() {
        return this.maCTPN;
    }

    @Override
    public void setID(Integer id) {
        this.maCTPN = id;
    }

    @Override
    public Integer getForeignID() {
        return maSP;
    }

    @Override
    public void setForeignID(Integer foreignID) {
        maSP = foreignID;
    }

    public Integer getMaCTPN() {
        return maCTPN;
    }

    public void setMaCTPN(Integer maCTPN) {
        this.maCTPN = maCTPN;
    }

    public Integer getMaPN() {
        return maPN;
    }

    public void setMaPN(Integer maPN) {
        this.maPN = maPN;
    }

    public Integer getMaSP() {
        return maSP;
    }

    public void setMaSP(Integer maSP) {
        this.maSP = maSP;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CT_PhieuNhap)) return false;
        CT_PhieuNhap that = (CT_PhieuNhap) o;
        return getMaPN().equals(that.getMaPN()) && getMaSP().equals(that.getMaSP());
    }
}

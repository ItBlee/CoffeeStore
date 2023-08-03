package com.itblee.dto;

import java.sql.Timestamp;

public class PhieuNhap extends BaseEntity {
    private Integer maPN;
    private Integer maNCC;
    private Integer maNV;
    private Timestamp ngayTao;
    private Integer tongTien;
    private Integer tinhTrang;
    
    @Override
    public Integer getID() {
        return this.maPN;
    }

    @Override
    public void setID(Integer id) {
        this.maPN = id;
    }

    public Integer getMaPN() {
        return maPN;
    }

    public void setMaPN(Integer maPN) {
        this.maPN = maPN;
    }

    public Integer getMaNCC() {
        return maNCC;
    }

    public void setMaNCC(Integer maNCC) {
        this.maNCC = maNCC;
    }

    public Integer getMaNV() {
        return maNV;
    }

    public void setMaNV(Integer maNV) {
        this.maNV = maNV;
    }

    public Timestamp getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Timestamp ngayTao) {
        this.ngayTao = ngayTao;
    }

    public Integer getTongTien() {
        return tongTien;
    }

    public void setTongTien(Integer tongTien) {
        this.tongTien = tongTien;
    }

    public Integer getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(Integer tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PhieuNhap)) return false;
        PhieuNhap that = (PhieuNhap) o;
        return getMaPN().equals(that.getMaPN());
    }

}

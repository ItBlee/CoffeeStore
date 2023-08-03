package com.itblee.dto;

public class LoaiSP extends BaseEntity {
    private Integer maLoai;
    private String tenLoai;
    private String moTa;

    @Override
    public Integer getID() {
        return this.maLoai;
    }

    @Override
    public void setID(Integer id) {
        this.maLoai = id;
    }

    public Integer getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(Integer maLoai) {
        this.maLoai = maLoai;
    }

    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LoaiSP)) return false;
        LoaiSP loaiSP = (LoaiSP) o;
        return getMaLoai().equals(loaiSP.getMaLoai()) || getTenLoai().equals(loaiSP.getTenLoai());
    }
}

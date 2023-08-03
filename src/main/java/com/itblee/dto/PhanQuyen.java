package com.itblee.dto;

public class PhanQuyen extends BaseEntity {
    private Integer maPQ;
    private String tenPQ;
    private Integer quyenHD;
    private Integer quyenSP;
    private Integer quyenPN;
    private Integer quyenNCC;
    private Integer quyenKH;
    private Integer quyenKM;
    private Integer quyenTK;
    private Integer quyenExcel;
    private Integer quyenNV;

    @Override
    public Integer getID() {
        return maPQ;
    }

    @Override
    public void setID(Integer id) {
        maPQ = id;
    }

    public Integer getMaPQ() {
        return maPQ;
    }

    public void setMaPQ(Integer maPQ) {
        this.maPQ = maPQ;
    }

    public String getTenPQ() {
        return tenPQ;
    }

    public void setTenPQ(String tenPQ) {
        this.tenPQ = tenPQ;
    }

    public Integer getQuyenHD() {
        return quyenHD;
    }

    public void setQuyenHD(Integer quyenHD) {
        this.quyenHD = quyenHD;
    }

    public Integer getQuyenSP() {
        return quyenSP;
    }

    public void setQuyenSP(Integer quyenSP) {
        this.quyenSP = quyenSP;
    }

    public Integer getQuyenPN() {
        return quyenPN;
    }

    public void setQuyenPN(Integer quyenPN) {
        this.quyenPN = quyenPN;
    }

    public Integer getQuyenNCC() {
        return quyenNCC;
    }

    public void setQuyenNCC(Integer quyenNCC) {
        this.quyenNCC = quyenNCC;
    }

    public Integer getQuyenKH() {
        return quyenKH;
    }

    public void setQuyenKH(Integer quyenKH) {
        this.quyenKH = quyenKH;
    }

    public Integer getQuyenKM() {
        return quyenKM;
    }

    public void setQuyenKM(Integer quyenKM) {
        this.quyenKM = quyenKM;
    }

    public Integer getQuyenTK() {
        return quyenTK;
    }

    public void setQuyenTK(Integer quyenTK) {
        this.quyenTK = quyenTK;
    }

    public Integer getQuyenExcel() {
        return quyenExcel;
    }

    public void setQuyenExcel(Integer quyenExcel) {
        this.quyenExcel = quyenExcel;
    }

    public Integer getQuyenNV() {
        return quyenNV;
    }

    public void setQuyenNV(Integer quyenNV) {
        this.quyenNV = quyenNV;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PhanQuyen)) return false;
        PhanQuyen that = (PhanQuyen) o;
        return getMaPQ().equals(that.getMaPQ()) || getTenPQ().equals(that.getTenPQ());
    }
}

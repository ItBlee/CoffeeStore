package com.itblee.dto;

public class Role {
    public static final int DEFAULT_ADMIN_ROLE_ID = 1;
    public static final int DEFAULT_EMPLOYEE_ROLE_ID = 2;

    private PhanQuyen phanQuyen;
    private CT_PhanQuyen quyenHD;
    private CT_PhanQuyen quyenSP;
    private CT_PhanQuyen quyenPN;
    private CT_PhanQuyen quyenNCC;
    private CT_PhanQuyen quyenKH;
    private CT_PhanQuyen quyenKM;
    private CT_PhanQuyen quyenTK;
    private CT_PhanQuyen quyenExcel;
    private CT_PhanQuyen quyenNV;

    public Role(PhanQuyen phanQuyen) {
        this.phanQuyen = phanQuyen;
    }

    public void setPhanQuyen(PhanQuyen phanQuyen) {
        this.phanQuyen = phanQuyen;
    }

    public PhanQuyen getPhanQuyen() {
        return phanQuyen;
    }

    public CT_PhanQuyen getQuyenHD() {
        return quyenHD;
    }

    public void setQuyenHD(CT_PhanQuyen quyenHD) {
        this.quyenHD = quyenHD;
    }

    public CT_PhanQuyen getQuyenSP() {
        return quyenSP;
    }

    public void setQuyenSP(CT_PhanQuyen quyenSP) {
        this.quyenSP = quyenSP;
    }

    public CT_PhanQuyen getQuyenPN() {
        return quyenPN;
    }

    public void setQuyenPN(CT_PhanQuyen quyenPN) {
        this.quyenPN = quyenPN;
    }

    public CT_PhanQuyen getQuyenNCC() {
        return quyenNCC;
    }

    public void setQuyenNCC(CT_PhanQuyen quyenNCC) {
        this.quyenNCC = quyenNCC;
    }

    public CT_PhanQuyen getQuyenKH() {
        return quyenKH;
    }

    public void setQuyenKH(CT_PhanQuyen quyenKH) {
        this.quyenKH = quyenKH;
    }

    public CT_PhanQuyen getQuyenKM() {
        return quyenKM;
    }

    public void setQuyenKM(CT_PhanQuyen quyenKM) {
        this.quyenKM = quyenKM;
    }

    public CT_PhanQuyen getQuyenTK() {
        return quyenTK;
    }

    public void setQuyenTK(CT_PhanQuyen quyenTK) {
        this.quyenTK = quyenTK;
    }

    public CT_PhanQuyen getQuyenExcel() {
        return quyenExcel;
    }

    public void setQuyenExcel(CT_PhanQuyen quyenExcel) {
        this.quyenExcel = quyenExcel;
    }

    public CT_PhanQuyen getQuyenNV() {
        return quyenNV;
    }

    public void setQuyenNV(CT_PhanQuyen quyenNV) {
        this.quyenNV = quyenNV;
    }

    public boolean isAdmin() {
        return getPhanQuyen().getMaPQ() == DEFAULT_ADMIN_ROLE_ID;
    }
}

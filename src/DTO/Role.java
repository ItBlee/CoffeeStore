package DTO;

public class Role {
    public static final int DEFAULT_ADMIN_ROLE_ID = 1;
    public static final int DEFAULT_EMPLOYEE_ROLE_ID = 2;

    private PhanQuyenDTO phanQuyen;
    private CT_PhanQuyenDTO QuyenHD;
    private CT_PhanQuyenDTO QuyenSP;
    private CT_PhanQuyenDTO QuyenPN;
    private CT_PhanQuyenDTO QuyenNCC;
    private CT_PhanQuyenDTO QuyenKH;
    private CT_PhanQuyenDTO QuyenKM;
    private CT_PhanQuyenDTO QuyenTK;
    private CT_PhanQuyenDTO QuyenExcel;
    private CT_PhanQuyenDTO QuyenNV;

    public Role(PhanQuyenDTO phanQuyen) {
        this.phanQuyen = phanQuyen;
    }

    public void setPhanQuyen(PhanQuyenDTO phanQuyen) {
        this.phanQuyen = phanQuyen;
    }

    public PhanQuyenDTO getPhanQuyen() {
        return phanQuyen;
    }

    public CT_PhanQuyenDTO getQuyenHD() {
        return QuyenHD;
    }

    public void setQuyenHD(CT_PhanQuyenDTO quyenHD) {
        QuyenHD = quyenHD;
    }

    public CT_PhanQuyenDTO getQuyenSP() {
        return QuyenSP;
    }

    public void setQuyenSP(CT_PhanQuyenDTO quyenSP) {
        QuyenSP = quyenSP;
    }

    public CT_PhanQuyenDTO getQuyenPN() {
        return QuyenPN;
    }

    public void setQuyenPN(CT_PhanQuyenDTO quyenPN) {
        QuyenPN = quyenPN;
    }

    public CT_PhanQuyenDTO getQuyenNCC() {
        return QuyenNCC;
    }

    public void setQuyenNCC(CT_PhanQuyenDTO quyenNCC) {
        QuyenNCC = quyenNCC;
    }

    public CT_PhanQuyenDTO getQuyenKH() {
        return QuyenKH;
    }

    public void setQuyenKH(CT_PhanQuyenDTO quyenKH) {
        QuyenKH = quyenKH;
    }

    public CT_PhanQuyenDTO getQuyenKM() {
        return QuyenKM;
    }

    public void setQuyenKM(CT_PhanQuyenDTO quyenKM) {
        QuyenKM = quyenKM;
    }

    public CT_PhanQuyenDTO getQuyenTK() {
        return QuyenTK;
    }

    public void setQuyenTK(CT_PhanQuyenDTO quyenTK) {
        QuyenTK = quyenTK;
    }

    public CT_PhanQuyenDTO getQuyenExcel() {
        return QuyenExcel;
    }

    public void setQuyenExcel(CT_PhanQuyenDTO quyenExcel) {
        QuyenExcel = quyenExcel;
    }

    public CT_PhanQuyenDTO getQuyenNV() {
        return QuyenNV;
    }

    public void setQuyenNV(CT_PhanQuyenDTO quyenNV) {
        QuyenNV = quyenNV;
    }

    public boolean isAdmin() {
        return getPhanQuyen().getMaPQ() == DEFAULT_ADMIN_ROLE_ID;
    }
}

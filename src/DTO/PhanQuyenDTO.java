package DTO;

import DTO.Interface.IEntity;

public class PhanQuyenDTO implements IEntity {
    private Integer MaPQ;
    private String TenPQ;
    private Integer QuyenHD;
    private Integer QuyenSP;
    private Integer QuyenPN;
    private Integer QuyenNCC;
    private Integer QuyenKH;
    private Integer QuyenKM;
    private Integer QuyenTK;
    private Integer QuyenExcel;
    private Integer QuyenNV;

    public PhanQuyenDTO() {
    }

    @Override
    public Integer getID() {
        return MaPQ;
    }

    @Override
    public void setID(Integer id) {
        MaPQ = id;
    }

    public Integer getMaPQ() {
        return MaPQ;
    }

    public void setMaPQ(Integer maPQ) {
        MaPQ = maPQ;
    }

    public String getTenPQ() {
        return TenPQ;
    }

    public void setTenPQ(String tenPQ) {
        TenPQ = tenPQ;
    }

    public Integer getQuyenHD() {
        return QuyenHD;
    }

    public void setQuyenHD(Integer quyenHD) {
        QuyenHD = quyenHD;
    }

    public Integer getQuyenSP() {
        return QuyenSP;
    }

    public void setQuyenSP(Integer quyenSP) {
        QuyenSP = quyenSP;
    }

    public Integer getQuyenPN() {
        return QuyenPN;
    }

    public void setQuyenPN(Integer quyenPN) {
        QuyenPN = quyenPN;
    }

    public Integer getQuyenNCC() {
        return QuyenNCC;
    }

    public void setQuyenNCC(Integer quyenNCC) {
        QuyenNCC = quyenNCC;
    }

    public Integer getQuyenKH() {
        return QuyenKH;
    }

    public void setQuyenKH(Integer quyenKH) {
        QuyenKH = quyenKH;
    }

    public Integer getQuyenKM() {
        return QuyenKM;
    }

    public void setQuyenKM(Integer quyenKM) {
        QuyenKM = quyenKM;
    }

    public Integer getQuyenTK() {
        return QuyenTK;
    }

    public void setQuyenTK(Integer quyenTK) {
        QuyenTK = quyenTK;
    }

    public Integer getQuyenExcel() {
        return QuyenExcel;
    }

    public void setQuyenExcel(Integer quyenExcel) {
        QuyenExcel = quyenExcel;
    }

    public Integer getQuyenNV() {
        return QuyenNV;
    }

    public void setQuyenNV(Integer quyenNV) {
        QuyenNV = quyenNV;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PhanQuyenDTO)) return false;
        PhanQuyenDTO that = (PhanQuyenDTO) o;
        return getMaPQ().equals(that.getMaPQ()) || getTenPQ().equals(that.getTenPQ());
    }
}

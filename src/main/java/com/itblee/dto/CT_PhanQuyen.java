package com.itblee.dto;

public class CT_PhanQuyen extends BaseEntity {
    private Integer maCTPQ;
    private Integer quyenDoc;
    private Integer quyenTao;
    private Integer quyenSua;
    private Integer quyenXoa;

    @Override
    public Integer getID() {
        return maCTPQ;
    }

    @Override
    public void setID(Integer id) {
        maCTPQ = id;
    }

    public Integer getMaCTPQ() {
        return maCTPQ;
    }

    public void setMaCTPQ(Integer maCTPQ) {
        this.maCTPQ = maCTPQ;
    }

    public Integer getQuyenDoc() {
        return quyenDoc != null ? quyenDoc : 0;
    }

    public void setQuyenDoc(Integer quyenDoc) {
        this.quyenDoc = quyenDoc;
    }

    public Integer getQuyenTao() {
        return quyenTao != null ? quyenTao : 0;
    }

    public void setQuyenTao(Integer quyenTao) {
        this.quyenTao = quyenTao;
    }

    public Integer getQuyenSua() {
        return quyenSua != null ? quyenSua : 0;
    }

    public void setQuyenSua(Integer quyenSua) {
        this.quyenSua = quyenSua;
    }

    public Integer getQuyenXoa() {
        return quyenXoa != null ? quyenXoa : 0;
    }

    public void setQuyenXoa(Integer quyenXoa) {
        this.quyenXoa = quyenXoa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CT_PhanQuyen)) return false;
        CT_PhanQuyen that = (CT_PhanQuyen) o;
        return getMaCTPQ().equals(that.getMaCTPQ());
    }
}

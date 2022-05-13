package DTO;

import DTO.Interface.IEntity;

public class CT_PhanQuyenDTO implements IEntity {
    private Integer MaCTPQ;
    private Integer QuyenDoc;
    private Integer QuyenTao;
    private Integer QuyenSua;
    private Integer QuyenXoa;

    public CT_PhanQuyenDTO() {
    }

    @Override
    public Integer getID() {
        return MaCTPQ;
    }

    @Override
    public void setID(Integer id) {
        MaCTPQ = id;
    }

    public Integer getMaCTPQ() {
        return MaCTPQ;
    }

    public void setMaCTPQ(Integer maCTPQ) {
        MaCTPQ = maCTPQ;
    }

    public Integer getQuyenDoc() {
        return QuyenDoc;
    }

    public void setQuyenDoc(Integer quyenDoc) {
        QuyenDoc = quyenDoc;
    }

    public Integer getQuyenTao() {
        return QuyenTao;
    }

    public void setQuyenTao(Integer quyenTao) {
        QuyenTao = quyenTao;
    }

    public Integer getQuyenSua() {
        return QuyenSua;
    }

    public void setQuyenSua(Integer quyenSua) {
        QuyenSua = quyenSua;
    }

    public Integer getQuyenXoa() {
        return QuyenXoa;
    }

    public void setQuyenXoa(Integer quyenXoa) {
        QuyenXoa = quyenXoa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CT_PhanQuyenDTO)) return false;
        CT_PhanQuyenDTO that = (CT_PhanQuyenDTO) o;
        return getMaCTPQ().equals(that.getMaCTPQ());
    }
}

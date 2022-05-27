package DTO;

import DTO.Interface.IEntity;

import java.sql.Timestamp;

public class PhieuNhapDTO implements IEntity {
    private Integer MaPN;
    private Integer MaNCC;
    private Integer MaNV;    
    private Timestamp NgayTao;
    private Integer TongTien;
    private Integer TinhTrang;

    public PhieuNhapDTO(){
    }
    
    @Override
    public Integer getID() {
        return this.MaPN;
    }

    @Override
    public void setID(Integer id) {
        this.MaPN = id;
    }

    public Integer getMaPN() {
        return MaPN;
    }

    public void setMaPN(Integer maPN) {
        MaPN = maPN;
    }

    public Integer getMaNCC() {
        return MaNCC;
    }

    public void setMaNCC(Integer maNCC) {
        MaNCC = maNCC;
    }

    public Integer getMaNV() {
        return MaNV;
    }

    public void setMaNV(Integer maNV) {
        MaNV = maNV;
    }

    public Timestamp getNgayTao() {
        return NgayTao;
    }

    public void setNgayTao(Timestamp ngayTao) {
        NgayTao = ngayTao;
    }

    public Integer getTongTien() {
        return TongTien;
    }

    public void setTongTien(Integer tongTien) {
        TongTien = tongTien;
    }

    public Integer getTinhTrang() {
        return TinhTrang;
    }

    public void setTinhTrang(Integer tinhTrang) {
        TinhTrang = tinhTrang;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PhieuNhapDTO)) return false;
        PhieuNhapDTO that = (PhieuNhapDTO) o;
        return getMaPN().equals(that.getMaPN());
    }

}

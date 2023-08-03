package com.itblee.dto;

import java.sql.Timestamp;

public class KhuyenMai extends BaseEntity {
    private Integer maKM;
    private String tieuDe;
    private String noiDung;
    private Timestamp ngayBD;
    private Timestamp ngayKT;
    private Integer tinhTrang;

    @Override
    public Integer getID() {
        return this.maKM;
    }

    @Override
    public void setID(Integer id) {
        this.maKM = id;
    }

    public Integer getMaKM() {
        return maKM;
    }

    public void setMaKM(Integer maKM) {
        this.maKM = maKM;
    }

    public String getTieuDe() {
        return tieuDe;
    }

    public void setTieuDe(String tieuDe) {
        this.tieuDe = tieuDe;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public Timestamp getNgayBD() {
        return ngayBD;
    }

    public void setNgayBD(Timestamp ngayBD) {
        this.ngayBD = ngayBD;
    }

    public Timestamp getNgayKT() {
        return ngayKT;
    }

    public void setNgayKT(Timestamp ngayKT) {
        this.ngayKT = ngayKT;
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
        if (!(o instanceof KhuyenMai)) return false;
        KhuyenMai that = (KhuyenMai) o;
        return getMaKM().equals(that.getMaKM()) || getTieuDe().equals(that.getTieuDe());
    }
}

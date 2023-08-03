package com.itblee.dto;

import java.sql.Timestamp;

public class HoaDon extends BaseEntity {
    private Integer maHD;
    private Integer maKH;
    private Integer maNV;
    private Timestamp ngayLap;
    private Integer tongTien;
    private Integer tienKhuyenMai;
    private Integer tienThanhToan;
    private Integer tinhTrang;

    @Override
    public Integer getID() {
        return maHD;
    }

    @Override
    public void setID(Integer id) {
        maHD = id;
    }

    public Integer getMaHD() {
        return maHD;
    }

    public void setMaHD(Integer maHD) {
        this.maHD = maHD;
    }

    public Integer getMaKH() {
        return maKH;
    }

    public void setMaKH(Integer maKH) {
        this.maKH = maKH;
    }

    public Integer getMaNV() {
        return maNV;
    }

    public void setMaNV(Integer maNV) {
        this.maNV = maNV;
    }

    public Timestamp getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(Timestamp ngayLap) {
        this.ngayLap = ngayLap;
    }

    public Integer getTongTien() {
        return tongTien;
    }

    public void setTongTien(Integer tongTien) {
        this.tongTien = tongTien;
    }

    public Integer getTienKhuyenMai() {
        return tienKhuyenMai;
    }

    public void setTienKhuyenMai(Integer tienKhuyenMai) {
        this.tienKhuyenMai = tienKhuyenMai;
    }

    public Integer getTienThanhToan() {
        return tienThanhToan;
    }

    public void setTienThanhToan(Integer tienThanhToan) {
        this.tienThanhToan = tienThanhToan;
    }

    public Integer getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(Integer tinhTrang) {
        this.tinhTrang = tinhTrang;
    }
}

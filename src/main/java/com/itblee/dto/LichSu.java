package com.itblee.dto;

import java.sql.Timestamp;

public class LichSu extends BaseEntity implements Comparable<LichSu> {
    private Integer maLS;
    private String tenDoiTuong;
    private Integer maDoiTuong;
    private Timestamp thoiGian;
    private Integer nguoiThucHien;
    private String thaoTac;

    @Override
    public Integer getID() {
        return maLS;
    }

    @Override
    public void setID(Integer id) {
        maLS = id;
    }

    public Integer getMaLS() {
        return maLS;
    }

    public void setMaLS(Integer maLS) {
        this.maLS = maLS;
    }

    public String getTenDoiTuong() {
        return tenDoiTuong;
    }

    public void setTenDoiTuong(String tenDoiTuong) {
        this.tenDoiTuong = tenDoiTuong;
    }

    public Integer getMaDoiTuong() {
        return maDoiTuong;
    }

    public void setMaDoiTuong(Integer maDoiTuong) {
        this.maDoiTuong = maDoiTuong;
    }

    public Timestamp getThoiGian() {
        return thoiGian;
    }

    public void setThoiGian(Timestamp thoiGian) {
        this.thoiGian = thoiGian;
    }

    public Integer getNguoiThucHien() {
        return nguoiThucHien;
    }

    public void setNguoiThucHien(Integer nguoiThucHien) {
        this.nguoiThucHien = nguoiThucHien;
    }

    public String getThaoTac() {
        return thaoTac;
    }

    public void setThaoTac(String thaoTac) {
        this.thaoTac = thaoTac;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LichSu)) return false;
        LichSu lichSu = (LichSu) o;
        return getMaLS().equals(lichSu.getMaLS());
    }

    @Override
    public int compareTo(LichSu o) {
        return o.getMaLS() - this.getMaLS();
    }
}

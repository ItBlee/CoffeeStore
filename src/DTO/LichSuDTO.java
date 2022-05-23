package DTO;

import DTO.Interface.IEntity;

import java.sql.Timestamp;

public class LichSuDTO implements IEntity, Comparable<LichSuDTO> {
    private Integer MaLS;
    private String TenDoiTuong;
    private Integer MaDoiTuong;
    private Timestamp ThoiGian;
    private Integer NguoiThucHien;
    private String ThaoTac;

    public LichSuDTO() {
    }

    @Override
    public Integer getID() {
        return MaLS;
    }

    @Override
    public void setID(Integer id) {
        MaLS = id;
    }

    public Integer getMaLS() {
        return MaLS;
    }

    public void setMaLS(Integer maLS) {
        MaLS = maLS;
    }

    public String getTenDoiTuong() {
        return TenDoiTuong;
    }

    public void setTenDoiTuong(String tenDoiTuong) {
        TenDoiTuong = tenDoiTuong;
    }

    public Integer getMaDoiTuong() {
        return MaDoiTuong;
    }

    public void setMaDoiTuong(Integer maDoiTuong) {
        MaDoiTuong = maDoiTuong;
    }

    public Timestamp getThoiGian() {
        return ThoiGian;
    }

    public void setThoiGian(Timestamp thoiGian) {
        ThoiGian = thoiGian;
    }

    public Integer getNguoiThucHien() {
        return NguoiThucHien;
    }

    public void setNguoiThucHien(Integer nguoiThucHien) {
        NguoiThucHien = nguoiThucHien;
    }

    public String getThaoTac() {
        return ThaoTac;
    }

    public void setThaoTac(String thaoTac) {
        ThaoTac = thaoTac;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LichSuDTO)) return false;
        LichSuDTO lichSuDTO = (LichSuDTO) o;
        return getMaLS().equals(lichSuDTO.getMaLS());
    }

    @Override
    public int compareTo(LichSuDTO o) {
        return o.getMaLS() - this.getMaLS();
    }
}

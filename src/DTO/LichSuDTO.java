package DTO;

import java.sql.Timestamp;

public class LichSuDTO {
    private Integer MaLS;
    private String TenDoiTuong;
    private Integer MaDoiTuong;
    private Timestamp ThoiGian;
    private Integer NguoiThucHien;
    private String ThaoTac;

    public LichSuDTO() {
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
}

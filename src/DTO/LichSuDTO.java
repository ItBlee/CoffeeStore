package DTO;

import java.sql.Timestamp;

public class LichSuDTO {
    private Integer MaLS;
    private String MaDoiTuong;
    private Timestamp NgayTao;
    private Timestamp NgaySua;
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

    public String getMaDoiTuong() {
        return MaDoiTuong;
    }

    public void setMaDoiTuong(String maDoiTuong) {
        MaDoiTuong = maDoiTuong;
    }

    public Timestamp getNgayTao() {
        return NgayTao;
    }

    public void setNgayTao(Timestamp ngayTao) {
        NgayTao = ngayTao;
    }

    public Timestamp getNgaySua() {
        return NgaySua;
    }

    public void setNgaySua(Timestamp ngaySua) {
        NgaySua = ngaySua;
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

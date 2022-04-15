package DTO;

import java.sql.Timestamp;

public class TaiKhoanDTO {
    private int MaTK;
    private String TenDangNhap;
    private String MatKhau;
    private Timestamp NgayTao;
    private int NguoiTao;
    private String ChucVu;
    private int TinhTrang;

    public TaiKhoanDTO() {
    }

    public int getMaTK() {
        return this.MaTK;
    }

    public void setMaTK(int maTK) {
        this.MaTK = maTK;
    }

    public String getTenDangNhap() {
        return this.TenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        this.TenDangNhap = tenDangNhap;
    }

    public String getMatKhau() {
        return this.MatKhau;
    }

    public void setMatKhau(String matKhau) {
        this.MatKhau = matKhau;
    }

    public Timestamp getNgayTao() {
        return this.NgayTao;
    }

    public void setNgayTao(Timestamp ngayTao) {
        this.NgayTao = ngayTao;
    }

    public int getNguoiTao() {
        return this.NguoiTao;
    }

    public void setNguoiTao(int nguoiTao) {
        this.NguoiTao = nguoiTao;
    }

    public String getChucVu() {
        return this.ChucVu;
    }

    public void setChucVu(String chucVu) {
        this.ChucVu = chucVu;
    }

    public int getTinhTrang() {
        return this.TinhTrang;
    }

    public void setTinhTrang(int tinhTrang) {
        this.TinhTrang = tinhTrang;
    }

    @Override
    public String toString() {
        return "TaiKhoanDTO{" +
                "MaTK=" + MaTK +
                ", TenDangNhap='" + TenDangNhap + '\'' +
                ", MatKhau='" + MatKhau + '\'' +
                ", NgayTao=" + NgayTao +
                ", NguoiTao=" + NguoiTao +
                ", ChucVu='" + ChucVu + '\'' +
                ", TinhTrang=" + TinhTrang +
                '}';
    }
}

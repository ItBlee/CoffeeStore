package DTO;

import DTO.Interface.IEntity;

import java.sql.Timestamp;
import java.util.Objects;

public class TaiKhoanDTO implements IEntity {
    private Integer MaTK;
    private String TenDangNhap;
    private String MatKhau;
    private Timestamp NgayTao;
    private Integer NguoiTao;
    private String ChucVu;
    private Integer TinhTrang;

    public TaiKhoanDTO() {
    }

    @Override
    public Integer getID() {
        return this.MaTK;
    }

    @Override
    public void setID(Integer id) {
        this.MaTK = id;
    }

    public Integer getMaTK() {
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

    public Integer getNguoiTao() {
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

    public Integer getTinhTrang() {
        return Objects.requireNonNullElse(this.TinhTrang, 1);
    }

    public void setTinhTrang(int tinhTrang) {
        this.TinhTrang = tinhTrang;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TaiKhoanDTO)) return false;
        TaiKhoanDTO dto = (TaiKhoanDTO) o;
        return getMaTK().equals(dto.getMaTK())
                || getTenDangNhap().equals(dto.getTenDangNhap());
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

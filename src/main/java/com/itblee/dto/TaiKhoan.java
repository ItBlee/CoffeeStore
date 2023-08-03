package com.itblee.dto;

import java.sql.Timestamp;

public class TaiKhoan extends BaseEntity {
    private Integer maTK;
    private String tenDangNhap;
    private String matKhauHash;
    private String matKhauSalt;
    private Timestamp ngayTao;
    private Integer nguoiTao;
    private Integer maPQ;
    private Integer tinhTrang;

    @Override
    public Integer getID() {
        return this.maTK;
    }

    @Override
    public void setID(Integer id) {
        this.maTK = id;
    }

    public Integer getMaTK() {
        return this.maTK;
    }

    public void setMaTK(Integer maTK) {
        this.maTK = maTK;
    }

    public String getTenDangNhap() {
        return this.tenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
    }

    public String getMatKhauHash() {
        return matKhauHash;
    }

    public void setMatKhauHash(String matKhauHash) {
        this.matKhauHash = matKhauHash;
    }

    public String getMatKhauSalt() {
        return matKhauSalt;
    }

    public void setMatKhauSalt(String matKhauSalt) {
        this.matKhauSalt = matKhauSalt;
    }

    public Timestamp getNgayTao() {
        return this.ngayTao;
    }

    public void setNgayTao(Timestamp ngayTao) {
        this.ngayTao = ngayTao;
    }

    public Integer getNguoiTao() {
        return this.nguoiTao;
    }

    public void setNguoiTao(Integer nguoiTao) {
        this.nguoiTao = nguoiTao;
    }

    public Integer getMaPQ() {
        return this.maPQ;
    }

    public void setMaPQ(Integer maPQ) {
        this.maPQ = maPQ;
    }

    public Integer getTinhTrang() {
        return tinhTrang != null ? tinhTrang : 1;
    }

    public void setTinhTrang(Integer tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TaiKhoan)) return false;
        TaiKhoan dto = (TaiKhoan) o;
        return getMaTK().equals(dto.getMaTK())
                || getTenDangNhap().equals(dto.getTenDangNhap());
    }
}

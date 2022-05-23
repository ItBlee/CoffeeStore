package DTO;

import DTO.Interface.IEntity;

import java.sql.Timestamp;
import java.util.Objects;

public class TaiKhoanDTO implements IEntity {
    private Integer MaTK;
    private String TenDangNhap;
    private String MatKhauHash;
    private String MatKhauSalt;
    private Timestamp NgayTao;
    private Integer NguoiTao;
    private Integer MaPQ;
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

    public void setMaTK(Integer maTK) {
        this.MaTK = maTK;
    }

    public String getTenDangNhap() {
        return this.TenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        this.TenDangNhap = tenDangNhap;
    }

    public String getMatKhauHash() {
        return MatKhauHash;
    }

    public void setMatKhauHash(String matKhauHash) {
        MatKhauHash = matKhauHash;
    }

    public String getMatKhauSalt() {
        return MatKhauSalt;
    }

    public void setMatKhauSalt(String matKhauSalt) {
        MatKhauSalt = matKhauSalt;
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

    public void setNguoiTao(Integer nguoiTao) {
        this.NguoiTao = nguoiTao;
    }

    public Integer getMaPQ() {
        return this.MaPQ;
    }

    public void setMaPQ(Integer maPQ) {
        this.MaPQ = maPQ;
    }

    public Integer getTinhTrang() {
        return Objects.requireNonNullElse(this.TinhTrang, 1);
    }

    public void setTinhTrang(Integer tinhTrang) {
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
}

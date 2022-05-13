package DTO;

import DTO.Interface.IEntity;

import java.sql.Date;
import java.util.Objects;

public class NhanVienDTO implements IEntity {
    private Integer MaNV;
    private Integer MaTK;
    private String Ho;
    private String Ten;
    private Date NgaySinh;
    private String SDT;
    private String Email;
    private Integer GioiTinh;
    private Integer Luong;

    public NhanVienDTO() {
    }

    @Override
    public Integer getID() {
        return MaNV;
    }

    @Override
    public void setID(Integer id) {
        MaNV = id;
    }

    public Integer getMaNV() {
        return MaNV;
    }

    public void setMaNV(Integer maNV) {
        MaNV = maNV;
    }

    public Integer getMaTK() {
        return MaTK;
    }

    public void setMaTK(Integer maTK) {
        MaTK = maTK;
    }

    public String getHo() {
        return Ho;
    }

    public void setHo(String ho) {
        Ho = ho;
    }

    public String getTen() {
        return Ten;
    }

    public void setTen(String ten) {
        Ten = ten;
    }

    public Date getNgaySinh() {
        return NgaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        NgaySinh = ngaySinh;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public Integer getGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(Integer gioiTinh) {
        GioiTinh = gioiTinh;
    }

    public Integer getLuong() {
        return Luong;
    }

    public void setLuong(Integer luong) {
        Luong = luong;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NhanVienDTO)) return false;
        NhanVienDTO that = (NhanVienDTO) o;
        return getMaNV().equals(that.getMaNV()) || getMaTK().equals(that.getMaTK());
    }
}

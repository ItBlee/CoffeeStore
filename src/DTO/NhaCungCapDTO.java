package DTO;

import DTO.Interface.IEntity;

public class NhaCungCapDTO implements IEntity {
    private Integer MaNCC;
    private String TenNCC;
    private String SDT;
    private String DiaChi;
    private String SoTaiKhoan;
    private Integer TinhTrang;

    @Override
    public Integer getID() {
        return MaNCC;
    }

    @Override
    public void setID(Integer id) {
        MaNCC = id;
    }

    public Integer getMaNCC() {
        return MaNCC;
    }

    public void setMaNCC(Integer maNCC) {
        MaNCC = maNCC;
    }

    public String getTenNCC() {
        return TenNCC;
    }

    public void setTenNCC(String tenNCC) {
        TenNCC = tenNCC;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }

    public String getSoTaiKhoan() {
        return SoTaiKhoan;
    }

    public void setSoTaiKhoan(String soTaiKhoan) {
        SoTaiKhoan = soTaiKhoan;
    }

    public Integer getTinhTrang() {
        return TinhTrang;
    }

    public void setTinhTrang(Integer tinhTrang) {
        TinhTrang = tinhTrang;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NhaCungCapDTO)) return false;
        NhaCungCapDTO that = (NhaCungCapDTO) o;
        return getMaNCC().equals(that.getMaNCC()) || getTenNCC().equals(that.getTenNCC());
    }
}

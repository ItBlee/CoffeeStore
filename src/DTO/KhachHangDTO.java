package DTO;

import DTO.Interface.IEntity;

public class KhachHangDTO implements IEntity {
    private Integer MaKH;
    private String Ho;
    private String Ten;
    private String SDT;
    private String DiaChi;
    private String Email;
    private Integer TinhTrang;

    @Override
    public Integer getID() {
        return MaKH;
    }

    @Override
    public void setID(Integer id) {
        MaKH = id;
    }

    public Integer getMaKH() {
        return MaKH;
    }

    public void setMaKH(Integer maKH) {
        MaKH = maKH;
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

    public String getHoTen() {
        return Ho + " " + Ten;
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

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
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
        if (!(o instanceof KhachHangDTO)) return false;
        KhachHangDTO that = (KhachHangDTO) o;
        return getMaKH().equals(that.getMaKH());
    }
}

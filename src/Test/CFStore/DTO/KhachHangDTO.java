package Test.CFStore.DTO;

import DTO.Interface.IEntity;

public class KhachHangDTO implements IEntity {
    private Integer MaKH;
    private String Ho;
    private String Ten;
    private Integer SDT;
    private String DiaChi;
    private String Email;

    public Integer getMaKH() {
        return MaKH;
    }

    public void setMaKH(Integer MaKH) {
        this.MaKH = MaKH;
    }

    public String getHo() {
        return Ho;
    }

    public void setHo(String Ho) {
        this.Ho = Ho;
    }

    public String getTen() {
        return Ten;
    }

    public void setTen(String Ten) {
        this.Ten = Ten;
    }

    public Integer getSDT() {
        return SDT;
    }

    public void setSDT(Integer SDT) {
        this.SDT = SDT;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }
    
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof KhachHangDTO)) return false;
        KhachHangDTO that = (KhachHangDTO) o;
        return getMaKH().equals(that.getMaKH()) || getTen().equals(that.getTen());
    }
    @Override
    public Integer getID() {
        return null;
    }

    @Override
    public void setID(Integer id) {

    }

    
}

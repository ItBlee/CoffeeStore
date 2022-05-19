package Test.CFStore.DTO;

import DTO.Interface.IEntity;

public class CT_KhuyenMaiDTO implements IEntity {
    private Integer MaKM;
    private Integer MaSP;
    private Integer GiamGia;

    public Integer getMaKM() {
        return MaKM;
    }

    public void setMaKM(Integer MaKM) {
        this.MaKM = MaKM;
    }

    public Integer getMaSP() {
        return MaSP;
    }

    public void setMaSP(Integer MaSP) {
        this.MaSP = MaSP;
    }

    public Integer getGiamGia() {
        return GiamGia;
    }

    public void setGiamGia(Integer GiamGia) {
        this.GiamGia = GiamGia;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CT_KhuyenMaiDTO)) return false;
       CT_KhuyenMaiDTO that = (CT_KhuyenMaiDTO) o;
        return getMaKM().equals(that.getMaKM());
    }
    
    
    
    @Override
    public Integer getID() {
        return null;
    }

    @Override
    public void setID(Integer id) {

    }
}

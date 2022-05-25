package DTO;

import DTO.Interface.IEntity;

public class CT_KhuyenMaiDTO implements IEntity {
    private Integer MaCTKM;
    private Integer MaKM;
    private Integer MaSP;    
    private Integer GiamGia;    
    
    public CT_KhuyenMaiDTO(){
        
    }
    @Override
    public Integer getID() {
        return this.MaCTKM;
    }

    @Override
    public void setID(Integer id) {
        this.MaCTKM = id;
    }

    public Integer getMaCTKM() {
        return MaCTKM;
    }

    public void setMaCTKM(Integer maCTKM) {
        MaCTKM = maCTKM;
    }

    public Integer getMaKM() {
        return MaKM;
    }

    public void setMaKM(Integer maKM) {
        MaKM = maKM;
    }

    public Integer getMaSP() {
        return MaSP;
    }

    public void setMaSP(Integer maSP) {
        MaSP = maSP;
    }

    public Integer getGiamGia() {
        return GiamGia;
    }

    public void setGiamGia(Integer giamGia) {
        GiamGia = giamGia;
    }
}

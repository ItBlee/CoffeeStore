package DTO;

import DTO.Interface.IEntity;

public class LoaiSPDTO implements IEntity {
    private Integer MaTK;    
    private Integer  MaLoai;
    private String   TenLoai;
    private String   MoTa;    
    
    public LoaiSPDTO(){
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
    public Integer getMaLoai() {
        return this.MaLoai;
    }
    public void setMaLoai(Integer maLoai){
        this.MaLoai = maLoai;
    }
    public String getTenLoai() {
        return this.TenLoai;
    }
    public void setTenLoai(String tenLoai){
        this.TenLoai = tenLoai;
    } 
    public String getMoTa() {
        return this.MoTa;
    }
    public void setMoTa(String moTa){
        this.MoTa = moTa;
    }    
}

package DTO;

import DTO.Interface.IEntity;

public class LoaiSPDTO implements IEntity {
    private Integer MaLoai;
    private String TenLoai;
    private String MoTa;
    
    public LoaiSPDTO(){
    }

    @Override
    public Integer getID() {
        return this.MaLoai;
    }

    @Override
    public void setID(Integer id) {
        this.MaLoai = id;
    }

    public Integer getMaLoai() {
        return MaLoai;
    }

    public void setMaLoai(Integer maLoai) {
        MaLoai = maLoai;
    }

    public String getTenLoai() {
        return TenLoai;
    }

    public void setTenLoai(String tenLoai) {
        TenLoai = tenLoai;
    }

    public String getMoTa() {
        return MoTa;
    }

    public void setMoTa(String moTa) {
        MoTa = moTa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LoaiSPDTO)) return false;
        LoaiSPDTO loaiSPDTO = (LoaiSPDTO) o;
        return getMaLoai().equals(loaiSPDTO.getMaLoai()) || getTenLoai().equals(loaiSPDTO.getTenLoai());
    }
}

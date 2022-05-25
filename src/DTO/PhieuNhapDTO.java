package DTO;

import DTO.Interface.IEntity;

import java.sql.Timestamp;

public class PhieuNhapDTO implements IEntity {
    private Integer MaTK;
    private Integer MaPN;
    private Integer MaNCC;
    private Integer MaNV;    
    private Timestamp NgayLap;
    private Integer TongTien;
    
    public PhieuNhapDTO(){
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
    public Integer getMaPN() {
        return this.MaPN;
    }
    public void setMaPN(Integer maPN){
        this.MaPN = maPN;
    }
    public Integer getMaNCC() {
        return this.MaNCC;
    }
    public void setMaNCC(Integer maNCC){
        this.MaNCC = maNCC;
    }    
    public Integer getMaNV() {
        return this.MaNV;
    }
    public void setMaNV(Integer maNV){
        this.MaNV = maNV;
    }
    public Timestamp getNgayLap() {
        return this.NgayLap;
    }
    public void setNgayLap(Timestamp ngayLap){
        this.NgayLap = ngayLap;
    }
    public Integer getTongTien() {
        return this.TongTien;
    }
    public void setTongTien(Integer tongTien){
        this.TongTien = tongTien;
    }
}

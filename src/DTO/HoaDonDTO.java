package DTO;

import DTO.Interface.IEntity;

import java.sql.Timestamp;

public class HoaDonDTO implements IEntity {
    private Integer MaHD;
    private Integer MaKH;
    private Integer MaNV;
    private Timestamp NgayLap;
    private Integer TongTien;
    private Integer TienKhuyenMai;  
    private Integer TienThanhToan;
    private Integer TinhTrang;
    
    public HoaDonDTO(){    
    }
    @Override
    public Integer getID() {
        return MaHD;
    }

    @Override
    public void setID(Integer id) {
        MaHD = id;
    }

    public Integer getMaHD() {
        return MaHD;
    }

    public void setMaHD(Integer maHD) {
        MaHD = maHD;
    }

    public Integer getMaKH() {
        return MaKH;
    }

    public void setMaKH(Integer maKH) {
        MaKH = maKH;
    }

    public Integer getMaNV() {
        return MaNV;
    }

    public void setMaNV(Integer maNV) {
        MaNV = maNV;
    }

    public Timestamp getNgayLap() {
        return NgayLap;
    }

    public void setNgayLap(Timestamp ngayLap) {
        NgayLap = ngayLap;
    }

    public Integer getTongTien() {
        return TongTien;
    }

    public void setTongTien(Integer tongTien) {
        TongTien = tongTien;
    }

    public Integer getTienKhuyenMai() {
        return TienKhuyenMai;
    }

    public void setTienKhuyenMai(Integer tienKhuyenMai) {
        TienKhuyenMai = tienKhuyenMai;
    }

    public Integer getTienThanhToan() {
        return TienThanhToan;
    }

    public void setTienThanhToan(Integer tienThanhToan) {
        TienThanhToan = tienThanhToan;
    }

    public Integer getTinhTrang() {
        return TinhTrang;
    }

    public void setTinhTrang(Integer tinhTrang) {
        TinhTrang = tinhTrang;
    }
}

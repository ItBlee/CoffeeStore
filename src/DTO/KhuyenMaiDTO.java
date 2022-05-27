package DTO;

import DTO.Interface.IEntity;
import java.sql.Timestamp;

public class KhuyenMaiDTO implements IEntity {
    private Integer MaKM;
    private String TieuDe;
    private String NoiDung;
    private Timestamp NgayBD;    
    private Timestamp NgayKT;
    private Integer TinhTrang;
    
    public KhuyenMaiDTO(){
    }

    @Override
    public Integer getID() {
        return this.MaKM;
    }

    @Override
    public void setID(Integer id) {
        this.MaKM = id;
    }

    public Integer getMaKM() {
        return MaKM;
    }

    public void setMaKM(Integer maKM) {
        MaKM = maKM;
    }

    public String getTieuDe() {
        return TieuDe;
    }

    public void setTieuDe(String tieuDe) {
        TieuDe = tieuDe;
    }

    public String getNoiDung() {
        return NoiDung;
    }

    public void setNoiDung(String noiDung) {
        NoiDung = noiDung;
    }

    public Timestamp getNgayBD() {
        return NgayBD;
    }

    public void setNgayBD(Timestamp ngayBD) {
        NgayBD = ngayBD;
    }

    public Timestamp getNgayKT() {
        return NgayKT;
    }

    public void setNgayKT(Timestamp ngayKT) {
        NgayKT = ngayKT;
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
        if (!(o instanceof KhuyenMaiDTO)) return false;
        KhuyenMaiDTO that = (KhuyenMaiDTO) o;
        return getMaKM().equals(that.getMaKM()) || getTieuDe().equals(that.getTieuDe());
    }
}

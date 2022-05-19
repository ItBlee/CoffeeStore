package Test.CFStore.DTO;

import DTO.Interface.IEntity;
import java.sql.Date;

public class KhuyenMaiDTO implements IEntity {
    private Integer MaKM;
    private String TieuDe;
    private String NoiDung;
    private Date NgayBD;
    private Date NgayKT;

    public Integer getMaKM() {
        return MaKM;
    }

    public void setMaKM(Integer MaKM) {
        this.MaKM = MaKM;
    }

    public String getTieuDe() {
        return TieuDe;
    }

    public void setTieuDe(String TieuDe) {
        this.TieuDe = TieuDe;
    }

    public String getNoiDung() {
        return NoiDung;
    }

    public void setNoiDung(String NoiDung) {
        this.NoiDung = NoiDung;
    }

    public Date getNgayBD() {
        return NgayBD;
    }

    public void setNgayBD(Date NgayBD) {
        this.NgayBD = NgayBD;
    }

    public Date getNgayKT() {
        return NgayKT;
    }

    public void setNgayKT(Date NgayKT) {
        this.NgayKT = NgayKT;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof KhuyenMaiDTO)) return false;
        KhuyenMaiDTO that = (KhuyenMaiDTO) o;
        return getMaKM().equals(that.getMaKM()) || getTieuDe().equals(that.getTieuDe());
    }
    
    
    @Override
    
    public Integer getID() {
        return null;
    }

    @Override
    public void setID(Integer id) {

    }
}

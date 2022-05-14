package BUS.Interfaces;

import DTO.KhuyenMaiDTO;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;

public interface IKhuyenMaiBUS {
    ArrayList<KhuyenMaiDTO> findAll();
    KhuyenMaiDTO findByID(int id);
    ArrayList<KhuyenMaiDTO> findByTieuDe(String tieuDe);
    ArrayList<KhuyenMaiDTO> findByNoiDung(String noiDung);
    ArrayList<KhuyenMaiDTO> findBySDT(String sdt);
    ArrayList<KhuyenMaiDTO> findByEmail(String email);
    ArrayList<KhuyenMaiDTO> findByThoiGian(Date tuNgay, Date denNgay);

    void save(KhuyenMaiDTO khuyenMai) throws Exception;
    void update(KhuyenMaiDTO khuyenMai) throws Exception;
    void delete(int id) throws Exception;
    HashMap<Integer, Boolean> delete(int[] ids);

    boolean isExist(KhuyenMaiDTO khuyenMai);
    int getTotalCount();
}

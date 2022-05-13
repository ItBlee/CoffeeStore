package BUS.Interfaces;

import DTO.LichSuDTO;

import java.sql.Timestamp;
import java.util.ArrayList;

public interface ILichSuBUS {
    ArrayList<LichSuDTO> findAll();
    LichSuDTO findByID(int id);
    ArrayList<LichSuDTO> findByTenDoiTuong(String tenDoiTuong);
    ArrayList<LichSuDTO> findByTenDoiTuongAndMaDoiTuong(String tenDoiTuong, Integer maDoiTuong);
    ArrayList<LichSuDTO> findByThoiGian(Timestamp thoiGian);
    ArrayList<LichSuDTO> findByNguoiThucHien(Integer nguoiThucHien);
    ArrayList<LichSuDTO> findByThaoTac(String thaoTac);


    void save(LichSuDTO taikhoan) throws Exception;
    boolean isExist(LichSuDTO taikhoan);
}

package BUS.Interfaces;

import BUS.Interfaces.common.ISearchableBUS;
import DTO.LichSuDTO;

import java.sql.Timestamp;
import java.util.ArrayList;

public interface ILichSuBUS extends ISearchableBUS<LichSuDTO> {
    ArrayList<LichSuDTO> findAll();
    LichSuDTO findByID(int id);
    ArrayList<LichSuDTO> findByTenDoiTuong(String tenDoiTuong);
    ArrayList<LichSuDTO> findByTenDoiTuongAndMaDoiTuong(String tenDoiTuong, Integer maDoiTuong);
    ArrayList<LichSuDTO> findByThoiGian(Timestamp tuNgay, Timestamp denNgay);
    ArrayList<LichSuDTO> findByNguoiThucHien(Integer nguoiThucHien);
    ArrayList<LichSuDTO> findByThaoTac(String thaoTac);

    void save(LichSuDTO taikhoan) throws Exception;
    boolean isExist(LichSuDTO taikhoan);
}

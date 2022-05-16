package BUS;

import BUS.Interfaces.ILichSuBUS;
import DAO.Interfaces.ILichSuDAO;
import DAO.LichSuDAO;
import DTO.LichSuDTO;
import Utils.StringUtils;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;

public class LichSuBUS implements ILichSuBUS {
    private static ArrayList<LichSuDTO> listLichSu = null;
    private final ILichSuDAO lichSuDAO;

    public LichSuBUS() {
        this.lichSuDAO = new LichSuDAO();
        if (listLichSu == null)
            listLichSu = findAll();
    }

    @Override
    public ArrayList<LichSuDTO> findAll() {
        return lichSuDAO.findAll();
    }

    @Override
    public LichSuDTO findByID(int id) {
        for (LichSuDTO lichSuDTO : listLichSu)
            if (lichSuDTO.getID() == id)
                return lichSuDTO;
        return null;
    }

    @Override
    public ArrayList<LichSuDTO> findByTenDoiTuong(String tenDoiTuong) {
        ArrayList<LichSuDTO> result = new ArrayList<LichSuDTO>();
        for (LichSuDTO lichSuDTO : listLichSu)
            if (StringUtils.containsIgnoreCase(lichSuDTO.getTenDoiTuong(), tenDoiTuong))
                result.add(lichSuDTO);
        return result;
    }

    @Override
    public ArrayList<LichSuDTO> findByTenDoiTuongAndMaDoiTuong(String tenDoiTuong, Integer maDoiTuong) {
        ArrayList<LichSuDTO> result = new ArrayList<LichSuDTO>();
        for (LichSuDTO lichSuDTO : listLichSu)
            if (lichSuDTO.getMaDoiTuong().equals(maDoiTuong)
                && StringUtils.containsIgnoreCase(lichSuDTO.getTenDoiTuong(), tenDoiTuong))
                result.add(lichSuDTO);
        return result;
    }

    @Override
    public ArrayList<LichSuDTO> findByThoiGian(Timestamp tuNgay, Timestamp denNgay) {
        ArrayList<LichSuDTO> result = new ArrayList<LichSuDTO>();
        for (LichSuDTO lichSuDTO : listLichSu)
            if ((lichSuDTO.getThoiGian().after(tuNgay) && lichSuDTO.getThoiGian().before(denNgay))
                || (tuNgay.equals(denNgay) && tuNgay.equals(lichSuDTO.getThoiGian())))
                result.add(lichSuDTO);
        return result;
    }

    @Override
    public ArrayList<LichSuDTO> findByNguoiThucHien(Integer nguoiThucHien) {
        ArrayList<LichSuDTO> result = new ArrayList<LichSuDTO>();
        for (LichSuDTO lichSuDTO : listLichSu)
            if (lichSuDTO.getNguoiThucHien().equals(nguoiThucHien))
                result.add(lichSuDTO);
        return result;
    }

    @Override
    public ArrayList<LichSuDTO> findByThaoTac(String thaoTac) {
        ArrayList<LichSuDTO> result = new ArrayList<LichSuDTO>();
        for (LichSuDTO lichSuDTO : listLichSu)
            if (StringUtils.containsIgnoreCase(lichSuDTO.getThaoTac(), thaoTac))
                result.add(lichSuDTO);
        return result;
    }

    @Override
    public void save(LichSuDTO lichSu) throws Exception {
        if (isExist(lichSu))
            throw new Exception("Đã tồn tại lịch sử này.");
        Integer newID = lichSuDAO.save(lichSu);
        if (newID == null)
            throw new Exception("Phát sinh lỗi trong quá trình thêm lịch sử.");
        listLichSu.add(lichSuDAO.findByID(newID));
    }

    @Override
    public boolean isExist(LichSuDTO lichSu) {
        return listLichSu.contains(lichSu);
    }

    @Override
    public int getTotalCount() {
        return listLichSu.size();
    }
}

package BUS;

import BUS.Abstract.AbstractHistoricBUS;
import BUS.Interfaces.ICT_KhuyenMaiBUS;
import BUS.Interfaces.IKhuyenMaiBUS;
import DAO.Interfaces.IKhuyenMaiDAO;
import DAO.KhuyenMaiDAO;
import DTO.CT_KhuyenMaiDTO;
import DTO.KhuyenMaiDTO;
import Utils.StringUtils;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;

public class KhuyenMaiBUS extends AbstractHistoricBUS implements IKhuyenMaiBUS {
    private static ArrayList<KhuyenMaiDTO> listKhuyenMai = null;
    private final IKhuyenMaiDAO khuyenMaiDAO;

    public KhuyenMaiBUS() {
        this.khuyenMaiDAO = new KhuyenMaiDAO();
        if (listKhuyenMai == null)
            listKhuyenMai = findAll();
    }

    @Override
    public ArrayList<KhuyenMaiDTO> findAll() {
        if (listKhuyenMai == null || listKhuyenMai.isEmpty())
            listKhuyenMai = khuyenMaiDAO.findAll();
        return listKhuyenMai;
    }

    @Override
    public KhuyenMaiDTO findByID(int id) {
        for (KhuyenMaiDTO khuyenMaiDTO : listKhuyenMai)
            if (khuyenMaiDTO.getID() == id)
                return khuyenMaiDTO;
        return null;
    }

    @Override
    public ArrayList<KhuyenMaiDTO> findByTieuDe(String tieuDe) {
        ArrayList<KhuyenMaiDTO> result = new ArrayList<KhuyenMaiDTO>();
        for (KhuyenMaiDTO khuyenMaiDTO : listKhuyenMai)
            if (StringUtils.containsIgnoreCase(khuyenMaiDTO.getTieuDe(), tieuDe))
                result.add(khuyenMaiDTO);
        return result;
    }

    @Override
    public ArrayList<KhuyenMaiDTO> findByThoiGian(Date tuNgay, Date denNgay) {
        ArrayList<KhuyenMaiDTO> result = new ArrayList<KhuyenMaiDTO>();
        for (KhuyenMaiDTO khuyenMaiDTO : listKhuyenMai) {
            if (tuNgay == null && khuyenMaiDTO.getNgayBD().before(denNgay)) {
                result.add(khuyenMaiDTO);
                continue;
            }
            if (denNgay == null && (khuyenMaiDTO.getNgayBD().after(tuNgay) || khuyenMaiDTO.getNgayKT().after(tuNgay))) {
                result.add(khuyenMaiDTO);
                continue;
            }
            if (tuNgay.equals(denNgay) && khuyenMaiDTO.getNgayBD().before(tuNgay) && khuyenMaiDTO.getNgayKT().after(tuNgay)) {
                result.add(khuyenMaiDTO);
                continue;
            }
            if (khuyenMaiDTO.getNgayBD().before(tuNgay) && khuyenMaiDTO.getNgayKT().after(tuNgay)) {
                result.add(khuyenMaiDTO);
                continue;
            }
            if (khuyenMaiDTO.getNgayKT().after(denNgay) && khuyenMaiDTO.getNgayBD().before(denNgay)) {
                result.add(khuyenMaiDTO);
                continue;
            }
            if (khuyenMaiDTO.getNgayBD().after(tuNgay) && khuyenMaiDTO.getNgayBD().before(denNgay)) {
                result.add(khuyenMaiDTO);
                continue;
            }
            if (khuyenMaiDTO.getNgayKT().after(tuNgay) && khuyenMaiDTO.getNgayKT().before(denNgay)) {
                result.add(khuyenMaiDTO);
            }
        }
        return result;
    }

    @Override
    public Integer save(KhuyenMaiDTO khuyenMai) throws Exception {
        if (isExist(khuyenMai))
            throw new Exception("Đã tồn tại khuyến mãi này.");
        Integer newID = khuyenMaiDAO.save(khuyenMai);
        if (newID == null)
            throw new Exception("Phát sinh lỗi trong quá trình thêm khuyến mãi.");
        khuyenMai = khuyenMaiDAO.findByID(newID);
        listKhuyenMai.add(khuyenMai);
        super.save(khuyenMai);
        return newID;
    }

    @Override
    public void update(KhuyenMaiDTO khuyenMai) throws Exception {
        if (!isExist(khuyenMai))
            throw new Exception("Không tồn tại khuyến mãi (KM" + khuyenMai.getMaKM() + ").");
        if (!khuyenMaiDAO.update(khuyenMai))
            throw new Exception("Phát sinh lỗi trong quá trình thêm khuyến mãi.");
        khuyenMai = khuyenMaiDAO.findByID(khuyenMai.getMaKM());
        for (int i = 0; i < listKhuyenMai.size(); i++) {
            if (listKhuyenMai.get(i).getMaKM().equals(khuyenMai.getMaKM()))
                listKhuyenMai.set(i, khuyenMai);
        }
        super.update(khuyenMai);
    }

    @Override
    public void delete(int id) throws Exception {
        ICT_KhuyenMaiBUS ctKhuyenMaiBUS = new CT_KhuyenMaiBUS();
        for (CT_KhuyenMaiDTO dto:ctKhuyenMaiBUS.findByMaKM(id))
            ctKhuyenMaiBUS.delete(dto.getID());
        if (!khuyenMaiDAO.delete(id))
            throw new Exception("Không thể xóa khuyến mãi (KM" + id + ").");
        listKhuyenMai.removeIf(khuyenMaiDTO -> khuyenMaiDTO.getMaKM() == id);
        super.delete(KhuyenMaiDTO.class, id);
    }

    @Override
    public HashMap<Integer, Boolean> delete(int[] ids) {
        HashMap<Integer, Boolean> report = new HashMap<Integer, Boolean>();
        boolean resultExecute;
        for (int id:ids) {
            try {
                delete(id);
                resultExecute = true;
            } catch (Exception e) {
                e.printStackTrace();
                resultExecute = false;
            }
            report.put(id, resultExecute);
        }
        return report;
    }

    @Override
    public boolean isExist(KhuyenMaiDTO khuyenMai) {
        if (khuyenMai.getMaKM() == null)
            return false;
        return listKhuyenMai.contains(khuyenMai);
    }

    @Override
    public int getTotalCount() {
        return listKhuyenMai.size();
    }
}

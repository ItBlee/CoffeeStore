package BUS;

import BUS.Abstract.AbstractHistoricBUS;
import BUS.Interfaces.INhanVienBUS;
import DAO.Interfaces.INhanVienDAO;
import DAO.NhanVienDAO;
import DTO.NhanVienDTO;
import DTO.TaiKhoanDTO;
import Utils.StringUtils;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;

public class NhanVienBUS extends AbstractHistoricBUS implements INhanVienBUS {
    private static ArrayList<NhanVienDTO> listNhanVien = null;
    private final INhanVienDAO nhanVienDAO;

    public NhanVienBUS() {
        this.nhanVienDAO = new NhanVienDAO();
        if (listNhanVien == null)
            listNhanVien = findAll();
    }

    @Override
    public ArrayList<NhanVienDTO> findAll() {
        return nhanVienDAO.findAll();
    }

    @Override
    public NhanVienDTO findByID(int id) {
        for (NhanVienDTO NhanVienDTO : listNhanVien)
            if (NhanVienDTO.getID() == id)
                return NhanVienDTO;
        return null;
    }

    @Override
    public NhanVienDTO findByMaTK(Integer maTK) {
        for (NhanVienDTO NhanVienDTO : listNhanVien)
            if (NhanVienDTO.getMaTK().equals(maTK))
                return NhanVienDTO;
        return null;
    }

    @Override
    public ArrayList<NhanVienDTO> findByHoTen(String hoTen) {
        ArrayList<NhanVienDTO> result = new ArrayList<NhanVienDTO>();
        for (NhanVienDTO nhanVienDTO : listNhanVien)
            if (StringUtils.containsIgnoreCase(nhanVienDTO.getHoTen(), hoTen))
                result.add(nhanVienDTO);
        return result;
    }

    @Override
    public ArrayList<NhanVienDTO> findByNgaySinh(Date ngaySinh) {
        ArrayList<NhanVienDTO> result = new ArrayList<NhanVienDTO>();
        for (NhanVienDTO nhanVienDTO : listNhanVien)
            if (nhanVienDTO.getNgaySinh().equals(ngaySinh))
                result.add(nhanVienDTO);
        return result;
    }

    @Override
    public ArrayList<NhanVienDTO> findBySDT(Integer sdt) {
        ArrayList<NhanVienDTO> result = new ArrayList<NhanVienDTO>();
        for (NhanVienDTO nhanVienDTO : listNhanVien)
            if (StringUtils.containsIgnoreCase(nhanVienDTO.getSDT(), String.valueOf(sdt)))
                result.add(nhanVienDTO);
        return result;
    }

    @Override
    public ArrayList<NhanVienDTO> findByEmail(String email) {
        ArrayList<NhanVienDTO> result = new ArrayList<NhanVienDTO>();
        for (NhanVienDTO nhanVienDTO : listNhanVien)
            if (StringUtils.containsIgnoreCase(nhanVienDTO.getEmail(), email))
                result.add(nhanVienDTO);
        return result;
    }

    @Override
    public ArrayList<NhanVienDTO> findByGioiTinh(Integer gioiTinh) {
        ArrayList<NhanVienDTO> result = new ArrayList<NhanVienDTO>();
        for (NhanVienDTO nhanVienDTO : listNhanVien)
            if (nhanVienDTO.getGioiTinh().equals(gioiTinh))
                result.add(nhanVienDTO);
        return result;
    }

    @Override
    public ArrayList<NhanVienDTO> findByLuong(Integer luong) {
        ArrayList<NhanVienDTO> result = new ArrayList<NhanVienDTO>();
        for (NhanVienDTO nhanVienDTO : listNhanVien)
            if (nhanVienDTO.getLuong().equals(luong))
                result.add(nhanVienDTO);
        return result;
    }

    @Override
    public void save(NhanVienDTO nhanVien) throws Exception {
        if (isExist(nhanVien))
            throw new Exception("Đã tồn tại nhân viên này.");
        Integer newID = nhanVienDAO.save(nhanVien);
        if (newID == null)
            throw new Exception("Phát sinh lỗi trong quá trình thêm nhân viên.");
        listNhanVien.add(nhanVienDAO.findByID(newID));
        super.save(nhanVien);
    }

    @Override
    public void update(NhanVienDTO nhanVien) throws Exception {
        if (!isExist(nhanVien))
            throw new Exception("Không tồn tại nhân viên (NV" + nhanVien.getMaNV() + ").");
        if (!nhanVienDAO.update(nhanVien))
            throw new Exception("Phát sinh lỗi trong quá trình thêm nhân viên.");
        nhanVien = nhanVienDAO.findByID(nhanVien.getMaNV());
        for (NhanVienDTO NhanVienDTO : listNhanVien) {
            if (NhanVienDTO.getMaNV().equals(nhanVien.getMaNV())) {
                NhanVienDTO = nhanVien;
                return;
            }
        }
        listNhanVien.add(nhanVien);
        super.update(nhanVien);
    }

    @Override
    public void delete(int id) throws Exception {
        if (!nhanVienDAO.delete(id))
            throw new Exception("Không thể xóa nhân viên (NV" + id + ").");
        listNhanVien.removeIf(NhanVienDTO -> NhanVienDTO.getMaNV() == id);
        super.delete(NhanVienDTO.class, id);
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
    public boolean isExist(NhanVienDTO nhanVien) {
        return listNhanVien.contains(nhanVien);
    }

    @Override
    public int getTotalCount() {
        return listNhanVien.size();
    }
}

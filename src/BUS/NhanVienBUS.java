package BUS;

import BUS.Abstract.AbstractHistoricBUS;
import BUS.Interfaces.INhanVienBUS;
import DAO.Interfaces.INhanVienDAO;
import DAO.NhanVienDAO;
import DTO.NhanVienDTO;

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
        return null;
    }

    @Override
    public ArrayList<NhanVienDTO> findByNgaySinh(Date ngaySinh) {
        return null;
    }

    @Override
    public ArrayList<NhanVienDTO> findBySDT(String sdt) {
        return null;
    }

    @Override
    public ArrayList<NhanVienDTO> findByEmail(String email) {
        return null;
    }

    @Override
    public ArrayList<NhanVienDTO> findByGioiTinh(String gioiTinh) {
        return null;
    }

    @Override
    public ArrayList<NhanVienDTO> findByLuong(Integer luong) {
        return null;
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

package BUS;

import BUS.Interfaces.ICT_PhieuNhapBUS;
import BUS.Interfaces.IPhieuNhapBUS;
import BUS.Interfaces.ISanPhamBUS;
import DAO.CT_PhieuNhapDAO;
import DAO.Interfaces.ICT_PhieuNhapDAO;
import DTO.CT_PhieuNhapDTO;
import DTO.PhieuNhapDTO;
import DTO.SanPhamDTO;

import java.util.ArrayList;
import java.util.HashMap;

public class CT_PhieuNhapBUS implements ICT_PhieuNhapBUS {
    private static ArrayList<CT_PhieuNhapDTO> listCTPhieuNhap = null;
    private final ICT_PhieuNhapDAO ctPhieuNhapDAO;

    public CT_PhieuNhapBUS() {
        this.ctPhieuNhapDAO = new CT_PhieuNhapDAO();
        if (listCTPhieuNhap == null)
            listCTPhieuNhap = findAll();
    }

    @Override
    public ArrayList<CT_PhieuNhapDTO> findAll() {
        if (listCTPhieuNhap == null || listCTPhieuNhap.isEmpty())
            listCTPhieuNhap = ctPhieuNhapDAO.findAll();
        return listCTPhieuNhap;
    }

    @Override
    public CT_PhieuNhapDTO findByID(int id) {
        for (CT_PhieuNhapDTO ctPhieuNhapDTO : listCTPhieuNhap)
            if (ctPhieuNhapDTO.getID() == id)
                return ctPhieuNhapDTO;
        return null;
    }

    @Override
    public ArrayList<CT_PhieuNhapDTO> findByMaPN(Integer MaPN) {
        ArrayList<CT_PhieuNhapDTO> result = new ArrayList<CT_PhieuNhapDTO>();
        for (CT_PhieuNhapDTO ctPhieuNhapDTO : listCTPhieuNhap)
            if (ctPhieuNhapDTO.getMaPN().equals(MaPN))
                result.add(ctPhieuNhapDTO);
        return result;
    }

    @Override
    public ArrayList<CT_PhieuNhapDTO> findByMaSP(Integer maSP) {
        ArrayList<CT_PhieuNhapDTO> result = new ArrayList<CT_PhieuNhapDTO>();
        for (CT_PhieuNhapDTO ctPhieuNhapDTO : listCTPhieuNhap)
            if (ctPhieuNhapDTO.getMaSP().equals(maSP))
                result.add(ctPhieuNhapDTO);
        return result;
    }

    @Override
    public Integer save(CT_PhieuNhapDTO ctPhieuNhap) throws Exception {
        if (isExist(ctPhieuNhap))
            throw new Exception("Đã tồn tại chi tiết phiếu nhập này.");
        for (CT_PhieuNhapDTO dto:listCTPhieuNhap) {
            if (dto.getMaPN().equals(ctPhieuNhap.getMaPN())
                    && dto.getMaSP().equals(ctPhieuNhap.getMaSP()))
                throw new Exception("Đã tồn tại sản phẩm ở phiếu nhập này.");
        }
        Integer newID = ctPhieuNhapDAO.save(ctPhieuNhap);
        if (newID == null)
            throw new Exception("Phát sinh lỗi trong quá trình thêm chi tiết phiếu nhập.");
        ctPhieuNhap = ctPhieuNhapDAO.findByID(newID);
        listCTPhieuNhap.add(ctPhieuNhap);

        ISanPhamBUS sanPhamBUS = new SanPhamBUS();
        SanPhamDTO sanPhamDTO = sanPhamBUS.findByID(ctPhieuNhap.getMaSP());
        int newSL = sanPhamDTO.getSoLuong() + ctPhieuNhap.getSoLuong();
        sanPhamDTO.setSoLuong(newSL);
        sanPhamBUS.update(sanPhamDTO);

        IPhieuNhapBUS phieuNhapBUS = new PhieuNhapBUS();
        PhieuNhapDTO parent = phieuNhapBUS.findByID(ctPhieuNhap.getMaPN());
        int newTotal = parent.getTongTien() + ctPhieuNhap.getThanhTien();
        parent.setTongTien(newTotal);
        phieuNhapBUS.update(parent);
        return newID;
    }

    @Override
    public void update(CT_PhieuNhapDTO ctPhieuNhap) throws Exception {
        if (!isExist(ctPhieuNhap))
            throw new Exception("Không tồn tại chi tiết phiếu nhập (CTPN" + ctPhieuNhap.getMaCTPN() + ").");
        CT_PhieuNhapDTO oldDto = findByID(ctPhieuNhap.getID());
        int oldSLSP = oldDto.getSoLuong();
        int oldTotal = oldDto.getThanhTien();
        if (!ctPhieuNhapDAO.update(ctPhieuNhap))
            throw new Exception("Phát sinh lỗi trong quá trình thêm chi tiết phiếu nhập.");
        ctPhieuNhap = ctPhieuNhapDAO.findByID(ctPhieuNhap.getMaCTPN());
        for (int i = 0; i < listCTPhieuNhap.size(); i++) {
            if (listCTPhieuNhap.get(i).getMaCTPN().equals(ctPhieuNhap.getMaCTPN()))
                listCTPhieuNhap.set(i, ctPhieuNhap);
        }
        SanPhamBUS sanPhamBUS = new SanPhamBUS();
        SanPhamDTO sanPhamDTO = sanPhamBUS.findByID(ctPhieuNhap.getMaSP());
        int newSL = sanPhamDTO.getSoLuong() - oldSLSP + ctPhieuNhap.getSoLuong();
        sanPhamDTO.setSoLuong(newSL);
        sanPhamBUS.update(sanPhamDTO);

        IPhieuNhapBUS phieuNhapBUS = new PhieuNhapBUS();
        PhieuNhapDTO parent = phieuNhapBUS.findByID(ctPhieuNhap.getMaPN());
        int newTotal = parent.getTongTien() - oldTotal + ctPhieuNhap.getThanhTien();
        parent.setTongTien(newTotal);
        phieuNhapBUS.update(parent);
    }

    @Override
    public void delete(int id) throws Exception {
        CT_PhieuNhapDTO temp = findByID(id);
        if (!ctPhieuNhapDAO.delete(id))
            throw new Exception("Không thể xóa chi tiết phiếu nhập (CTPN" + id + ").");
        listCTPhieuNhap.removeIf(CT_PhieuNhapDTO -> CT_PhieuNhapDTO.getMaCTPN() == id);

        ISanPhamBUS sanPhamBUS = new SanPhamBUS();
        SanPhamDTO sanPhamDTO = sanPhamBUS.findByID(temp.getMaSP());
        int newSL = sanPhamDTO.getSoLuong() - temp.getSoLuong();
        sanPhamDTO.setSoLuong(newSL);
        sanPhamBUS.update(sanPhamDTO);

        IPhieuNhapBUS phieuNhapBUS = new PhieuNhapBUS();
        PhieuNhapDTO parent = phieuNhapBUS.findByID(temp.getMaPN());
        int newTotal = parent.getTongTien() - temp.getThanhTien();
        parent.setTongTien(newTotal);
        phieuNhapBUS.update(parent);
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
    public boolean isExist(CT_PhieuNhapDTO ctPhieuNhap) {
        if (ctPhieuNhap.getMaCTPN() == null)
            return false;
        return listCTPhieuNhap.contains(ctPhieuNhap);
    }

    @Override
    public int getTotalCount() {
        return listCTPhieuNhap.size();
    }
}

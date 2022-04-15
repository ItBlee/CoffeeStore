package Services;

import DAO.Interfaces.ITaiKhoanDAO;
import DAO.TaiKhoanDAO;
import DTO.TaiKhoanDTO;
import Services.Interfaces.ITaiKhoanService;
import Utils.General;

import java.util.HashMap;
import java.util.List;

public class TaiKhoanService implements ITaiKhoanService {
    private ITaiKhoanDAO taiKhoanDAO;

    public TaiKhoanService() {
        this.taiKhoanDAO = new TaiKhoanDAO();
    }

    @Override
    public List<TaiKhoanDTO> findAll() {
        return taiKhoanDAO.findAll();
    }

    @Override
    public TaiKhoanDTO findByID(int id) {
        return taiKhoanDAO.findByID(id);
    }

    @Override
    public TaiKhoanDTO save(TaiKhoanDTO taikhoan) {
        Integer saveID = taiKhoanDAO.save(taikhoan);
        if (saveID == null)
            return null;
        return taiKhoanDAO.findByID(saveID);
    }

    @Override
    public TaiKhoanDTO update(TaiKhoanDTO taikhoan) {
        boolean updateResult = taiKhoanDAO.update(taikhoan);
        return updateResult ? taiKhoanDAO.findByID(taikhoan.getMaTK()) : null;
    }

    @Override
    public HashMap<Integer, Boolean> delete(int[] ids) {
        HashMap<Integer, Boolean> report = new HashMap<>();
        boolean resultExecute;
        for (int id = 0; id < ids.length; id++) {
            resultExecute = taiKhoanDAO.delete(id);
            report.put(id, resultExecute);
        }
        return report;
    }

    @Override
    public boolean login(String username, String password) {
        TaiKhoanDTO findTaiKhoan = taiKhoanDAO.findByTenDangNhapAndMatKhauAndTinhTrang(username, password, 1);
        if (findTaiKhoan != null) {
            General.CURRENT_USER = findTaiKhoan;
            return true;
        }
        return false;
    }

    @Override
    public int getTotalCount() {
        return taiKhoanDAO.count();
    }
}

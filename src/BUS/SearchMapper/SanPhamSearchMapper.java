package BUS.SearchMapper;

import BUS.Interfaces.ISanPhamBUS;
import BUS.SanPhamBUS;
import BUS.SearchMapper.Interfaces.ISearchMapper;
import DTO.Interface.IEntity;
import Utils.StringUtils;

import java.util.ArrayList;
import java.util.Date;

public class SanPhamSearchMapper implements ISearchMapper {
    @Override
    public ArrayList<IEntity> searchAll() {
        ISanPhamBUS bus = new SanPhamBUS();
        return new ArrayList<IEntity>(bus.findAll());
    }

    @Override
    public ArrayList<IEntity> searchByIndex(Integer index, String value) {
        ISanPhamBUS bus = new SanPhamBUS();
        ArrayList<IEntity> result = new ArrayList<IEntity>();
        try {
            switch (index) {
                case 0:
                    result.add(bus.findByID(Integer.parseInt(value)));
                    return result;

                case 1:
                    return new ArrayList<IEntity>(bus.findByTenSP(value));

                case 2:
                    try {
                        return new ArrayList<IEntity>(bus.findByLoaiSP(Integer.valueOf(value)));
                    } catch (Exception ignored) {
                        return new ArrayList<IEntity>(bus.findByLoaiSP(value));
                    }

                case 3:
                    try {
                        return new ArrayList<IEntity>(bus.findByNhaCungCap(Integer.valueOf(value)));
                    } catch (Exception ignored) {
                        return new ArrayList<IEntity>(bus.findByNhaCungCap(value));
                    }

                case 4:
                    return new ArrayList<IEntity>(bus.findByDonGia(Integer.valueOf(value)));

                case 5:
                    return new ArrayList<IEntity>(bus.findByDonVi(value));

                case 6:
                    return new ArrayList<IEntity>(bus.findBySoLuong(Integer.valueOf(value)));

                case 7:
                    try {
                        return new ArrayList<IEntity>(bus.findByTinhTrang(Integer.valueOf(value)));
                    } catch (Exception ignored) {
                        if (StringUtils.containsIgnoreCase(value, "Hoạt động"))
                            return new ArrayList<IEntity>(bus.findByTinhTrang(1));
                        else if (StringUtils.containsIgnoreCase(value, "Vô hiệu"))
                            return new ArrayList<IEntity>(bus.findByTinhTrang(0));
                        return result;
                    }

                default:
                    return result;
            }
        } catch (Exception e) {
            return result;
        }
    }

    @Override
    public ArrayList<IEntity> searchByDate(Date from, Date to) {
        ISanPhamBUS bus = new SanPhamBUS();
        return new ArrayList<IEntity>(bus.findAll());
    }
}

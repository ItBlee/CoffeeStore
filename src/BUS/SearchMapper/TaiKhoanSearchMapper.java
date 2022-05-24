package BUS.SearchMapper;

import BUS.Interfaces.ITaiKhoanBUS;
import BUS.SearchMapper.Interfaces.ISearchMapper;
import BUS.TaiKhoanBUS;
import DTO.Interface.IEntity;
import Utils.StringUtils;

import java.util.ArrayList;
import java.util.Date;

public class TaiKhoanSearchMapper implements ISearchMapper {
    @Override
    public ArrayList<IEntity> searchByIndex(Integer index, String value) {
        ITaiKhoanBUS bus = new TaiKhoanBUS();
        ArrayList<IEntity> result = new ArrayList<IEntity>();
        try {
            switch (index) {
                case 0:
                    result.add(bus.findByID(Integer.parseInt(value)));
                    return result;

                case 1:
                    return new ArrayList<IEntity>(bus.findByTenDangNhap(value));

                case 2:
                    try {
                        return new ArrayList<IEntity>(bus.findByPhanQuyen(Integer.valueOf(value)));
                    } catch (Exception ignored) {
                        return new ArrayList<IEntity>(bus.findByPhanQuyen(value));
                    }

                case 3:
                    try {
                        result.add(bus.findByNguoiSoHuu(Integer.valueOf(value)));
                        return result;
                    } catch (Exception ignored) {
                        result.add(bus.findByNguoiSoHuu(value));
                        return result;
                    }

                case 4:
                    java.sql.Date sqlDate = java.sql.Date.valueOf(value);
                    return new ArrayList<IEntity>(bus.findByNgayTao(sqlDate, sqlDate));

                case 5:
                    try {
                        return new ArrayList<IEntity>(bus.findByNguoiTao(Integer.valueOf(value)));
                    } catch (Exception ignored) {
                        return new ArrayList<IEntity>(bus.findByNguoiTao(value));
                    }

                case 6:
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
        ITaiKhoanBUS bus = new TaiKhoanBUS();
        java.sql.Date convertFrom = new java.sql.Date(from.getTime());
        java.sql.Date convertTo = new java.sql.Date(to.getTime());
        return new ArrayList<IEntity>(bus.findByNgayTao(convertFrom, convertTo));
    }
}

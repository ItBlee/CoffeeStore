package BUS.SearchMapper;

import BUS.Interfaces.INhanVienBUS;
import BUS.NhanVienBUS;
import BUS.SearchMapper.Interfaces.ISearchMapper;
import DTO.Interface.IEntity;
import Utils.StringUtils;

import java.util.ArrayList;
import java.util.Date;

public class NhanVienSearchMapper implements ISearchMapper {
    @Override
    public ArrayList<IEntity> searchByIndex(Integer index, String value) {
        INhanVienBUS bus = new NhanVienBUS();
        ArrayList<IEntity> result = new ArrayList<IEntity>();
        try {
            switch (index) {
                case 0:
                    result.add(bus.findByID(Integer.parseInt(value)));
                    return result;

                case 1:
                    try {
                        result.add(bus.findByTaiKhoan(Integer.valueOf(value)));
                        return result;
                    } catch (Exception ignored) {
                        result.add(bus.findByTaiKhoan(value));
                        return result;
                    }

                case 2:
                    return new ArrayList<IEntity>(bus.findByHoTen(value));

                case 3:
                    java.sql.Date sqlDate = java.sql.Date.valueOf(value);
                    return new ArrayList<IEntity>(bus.findByNgaySinh(sqlDate, sqlDate));

                case 4:
                    return new ArrayList<IEntity>(bus.findBySDT(Integer.valueOf(value)));

                case 5:
                    return new ArrayList<IEntity>(bus.findByEmail(value));

                case 6:
                    try {
                        return new ArrayList<IEntity>(bus.findByGioiTinh(Integer.valueOf(value)));
                    } catch (Exception ignored) {
                        if (StringUtils.containsIgnoreCase(value, "Nam"))
                            return new ArrayList<IEntity>(bus.findByGioiTinh(1));
                        else if (StringUtils.containsIgnoreCase(value, "Nữ"))
                            return new ArrayList<IEntity>(bus.findByGioiTinh(0));
                        return result;
                    }

                case 7:
                    return new ArrayList<IEntity>(bus.findByLuong(Integer.valueOf(value)));

                case 8:
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
        INhanVienBUS bus = new NhanVienBUS();
        java.sql.Date convertFrom = new java.sql.Date(from.getTime());
        java.sql.Date convertTo = new java.sql.Date(to.getTime());
        return new ArrayList<IEntity>(bus.findByNgaySinh(convertFrom, convertTo));
    }
}

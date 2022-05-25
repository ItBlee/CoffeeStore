package BUS.SearchMapper;

import BUS.Interfaces.IKhachHangBUS;
import BUS.KhachHangBUS;
import BUS.SearchMapper.Interfaces.ISearchMapper;
import DTO.Interface.IEntity;
import Utils.StringUtils;

import java.util.ArrayList;
import java.util.Date;

public class SanPhamSearchMapper implements ISearchMapper {
    @Override
    public ArrayList<IEntity> searchByIndex(Integer index, String value) {
        IKhachHangBUS bus = new KhachHangBUS();
        ArrayList<IEntity> result = new ArrayList<IEntity>();
        try {
            switch (index) {
                case 0:
                    result.add(bus.findByID(Integer.parseInt(value)));
                    return result;

                case 1:
                    return new ArrayList<IEntity>(bus.findByHoTen(value));

                case 2:
                    return new ArrayList<IEntity>(bus.findBySDT(value));

                case 3:
                    return new ArrayList<IEntity>(bus.findByDiaChi(value));

                case 4:
                    return new ArrayList<IEntity>(bus.findByEmail(value));

                case 5:
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
        IKhachHangBUS bus = new KhachHangBUS();
        return new ArrayList<IEntity>(bus.findAll());
    }
}

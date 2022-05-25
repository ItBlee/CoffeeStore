package BUS.SearchMapper;

import BUS.Interfaces.IKhuyenMaiBUS;
import BUS.KhuyenMaiBUS;
import BUS.SearchMapper.Interfaces.ISearchMapper;
import DTO.Interface.IEntity;

import java.util.ArrayList;
import java.util.Date;

public class KhuyenMaiSearchMapper implements ISearchMapper {
    @Override
    public ArrayList<IEntity> searchByIndex(Integer index, String value) {
        IKhuyenMaiBUS bus = new KhuyenMaiBUS();
        ArrayList<IEntity> result = new ArrayList<IEntity>();
        try {
            switch (index) {
                case 0:
                    result.add(bus.findByID(Integer.parseInt(value)));
                    return result;

                case 1:
                    return new ArrayList<IEntity>(bus.findByTieuDe(value));

                default:
                    return result;
            }
        } catch (Exception e) {
            return result;
        }
    }

    @Override
    public ArrayList<IEntity> searchByDate(Date from, Date to) {
        IKhuyenMaiBUS bus = new KhuyenMaiBUS();
        java.sql.Date convertFrom = new java.sql.Date(from.getTime());
        java.sql.Date convertTo = new java.sql.Date(to.getTime());
        return new ArrayList<IEntity>(bus.findByThoiGian(convertFrom, convertTo));
    }
}

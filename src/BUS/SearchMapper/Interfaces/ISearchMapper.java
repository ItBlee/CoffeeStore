package BUS.SearchMapper.Interfaces;

import DTO.Interface.IEntity;

import java.util.ArrayList;
import java.util.Date;

public interface ISearchMapper {
    ArrayList<IEntity> searchAll();
    ArrayList<IEntity> searchByIndex(Integer index, String value);
    ArrayList<IEntity> searchByDate(Date from, Date to);
}

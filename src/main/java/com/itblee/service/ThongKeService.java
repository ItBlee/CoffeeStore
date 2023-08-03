package com.itblee.service;

import com.itblee.dto.ThongKe;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface ThongKeService {
    ThongKe fillThongKeByYear(int year);
    ThongKe fillThongKeByMonth(int month, int year);
    List<ThongKe> fillThongKeByDate(LocalDate from, LocalDate to);
    Integer isExistDay(LocalDate date);
    Integer isExistMonth(int month, int year);
    Integer isExistYear(int year);
    Map<String, Integer> sortByValue(Map<String, Integer> unSortMap, boolean ascOrder);
    boolean isValidateFlag();
    void setValidateFlag(boolean b);
}

package com.itblee.service.impl;

import com.google.auto.service.AutoService;
import com.itblee.Provider;
import com.itblee.dto.*;
import com.itblee.service.*;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@AutoService(ThongKeService.class)
public class ThongKeServiceImpl implements ThongKeService {
    private boolean validateFlag;
    private static List<ThongKe> listByDay;
    private static List<ThongKe> listByMonth;
    private static List<ThongKe> listByYear;

    public ThongKeServiceImpl() {
        listByDay = new ArrayList<>();
        listByMonth = new ArrayList<>();
        listByYear = new ArrayList<>();
    }

    @Override
    public ThongKe fillThongKeByYear(int year) {
        if (year > LocalDate.now().getYear())
            return null;
        Integer check = isExistYear(year);
        if (check != null && !validateFlag)
            return listByYear.get(check);

        ThongKe yearTK = new ThongKe();
        yearTK.setYear(year);
        for (int i = 1; i < 12; i++) {
            ThongKe dto = fillThongKeByMonth(i, yearTK.getYear());
            if (dto != null) {
                Map<String, Integer> tempSeller = yearTK.getBestSeller();
                yearTK.setIncome(yearTK.getIncome() + dto.getIncome());
                yearTK.setExpenses(yearTK.getExpenses() + dto.getExpenses());
                yearTK.setCustomer(yearTK.getCustomer() + dto.getCustomer());
                yearTK.setInvoice(yearTK.getInvoice() + dto.getInvoice());
                yearTK.setInput(yearTK.getInput() + dto.getInput());
                yearTK.setSale(yearTK.getSale() + dto.getSale());
                yearTK.setInProduct(yearTK.getInProduct() + dto.getInProduct());
                yearTK.setOutProduct(yearTK.getOutProduct() + dto.getOutProduct());
                for (Map.Entry<String, Integer> entry : dto.getBestSeller().entrySet()) {
                    if (!tempSeller.containsKey(entry.getKey())) {
                        tempSeller.put(entry.getKey(), entry.getValue());
                    } else
                        tempSeller.put(entry.getKey(), tempSeller.get(entry.getKey()) + entry.getValue());
                }
                yearTK.setBestSeller(sortByValue(tempSeller, false));
            }
        }
        listByYear.add(yearTK);
        return yearTK;
    }

    @Override
    public ThongKe fillThongKeByMonth(int month, int year) {
        if (month <=0 || month > 12)
            return null;
        if (year > LocalDate.now().getYear())
            return null;
        Integer check = isExistMonth(month, year);
        if (check != null && !validateFlag)
            return listByMonth.get(check);

        String dateStr = month + "." + year;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M.yyyy");
        YearMonth ym = YearMonth.parse(dateStr, formatter);

        ThongKe monthTK = new ThongKe();
        monthTK.setMonth(ym);

        List<ThongKe> fillAllDateOfMonth = fillThongKeByDate(ym.atDay(1), ym.atEndOfMonth());
        if (!fillAllDateOfMonth.isEmpty()) {
            Map<String, Integer> tempSeller = monthTK.getBestSeller();
            for (ThongKe dto:fillAllDateOfMonth) {
                monthTK.setIncome(monthTK.getIncome() + dto.getIncome());
                monthTK.setExpenses(monthTK.getExpenses() + dto.getExpenses());
                monthTK.setCustomer(monthTK.getCustomer() + dto.getCustomer());
                monthTK.setInvoice(monthTK.getInvoice() + dto.getInvoice());
                monthTK.setInput(monthTK.getInput() + dto.getInput());
                monthTK.setSale(monthTK.getSale() + dto.getSale());
                monthTK.setInProduct(monthTK.getInProduct() + dto.getInProduct());
                monthTK.setOutProduct(monthTK.getOutProduct() + dto.getOutProduct());
                for (Map.Entry<String, Integer> entry:dto.getBestSeller().entrySet()) {
                    if (!tempSeller.containsKey(entry.getKey())) {
                        tempSeller.put(entry.getKey(), entry.getValue());
                    } else
                        tempSeller.put(entry.getKey(), tempSeller.get(entry.getKey()) + entry.getValue());
                }
            }
            monthTK.setBestSeller(sortByValue(tempSeller, false));
        }
        listByMonth.add(monthTK);
        return monthTK;
    }

    @Override
    public List<ThongKe> fillThongKeByDate(LocalDate from, LocalDate to) {
        CT_HoaDonService ctHoaDonService = Provider.get(CT_HoaDonService.class);
        HoaDonService hoaDonService = Provider.get(HoaDonService.class);
        PhieuNhapService phieuNhapService = Provider.get(PhieuNhapService.class);
        CT_PhieuNhapService ctPhieuNhapService = Provider.get(CT_PhieuNhapService.class);
        SanPhamService sanPhamService = Provider.get(SanPhamService.class);
        List<ThongKe> result = new ArrayList<>();
        for (LocalDate date = from; date.isBefore(to); date = date.plusDays(1)) {
            Integer check = isExistDay(date);
            if (check != null && !validateFlag) {
                result.add(listByDay.get(check));
                continue;
            }

            Integer income = 0;
            Integer expenses = 0;
            Set<Integer> customerIDList = new HashSet<>();
            Integer invoice = 0;
            Integer input = 0;
            Integer sale = 0;
            Integer inProduct = 0;
            Integer outProduct = 0;
            HashMap<String, Integer> productSellList = new HashMap<>();

            for (HoaDon dto: hoaDonService.findAll()) {
                LocalDate dtoDate = dto.getNgayLap().toLocalDateTime().toLocalDate();
                if (dtoDate.getDayOfYear() == date.getDayOfYear()
                        && dto.getTinhTrang() != 0) {
                    invoice++;
                    income += dto.getTienThanhToan();
                    sale += dto.getTienKhuyenMai();
                    customerIDList.add(dto.getMaKH());
                    for (CT_HoaDon child: ctHoaDonService.findByMaHD(dto.getMaHD())) {
                        outProduct += child.getSoLuong();
                        SanPham sp = sanPhamService.findByID(child.getMaSP());
                        if (productSellList.containsKey(sp.getTenSP()))
                            productSellList.put(sp.getTenSP(), productSellList.get(sp.getTenSP()) + child.getSoLuong());
                        else productSellList.put(sp.getTenSP(), child.getSoLuong());
                    }
                }
            }

            for (PhieuNhap dto: phieuNhapService.findAll()) {
                LocalDate dtoDate = dto.getNgayTao().toLocalDateTime().toLocalDate();
                if (dtoDate.getDayOfYear() == date.getDayOfYear()
                        && dto.getTinhTrang() != 0) {
                    input++;
                    expenses += dto.getTongTien();
                    for (CT_PhieuNhap child: ctPhieuNhapService.findByMaPN(dto.getMaPN()))
                        inProduct += child.getSoLuong();
                }
            }

            ThongKe dto = new ThongKe(date);
            dto.setIncome(income);
            dto.setExpenses(expenses);
            dto.setCustomer(customerIDList.size());
            dto.setInvoice(invoice);
            dto.setInput(input);
            dto.setSale(sale);
            dto.setInProduct(inProduct);
            dto.setOutProduct(outProduct);
            dto.setBestSeller(sortByValue(productSellList, false));
            listByDay.add(dto);
            result.add(dto);
        }
        return result;
    }

    @Override
    public Map<String, Integer> sortByValue(Map<String, Integer> unSortMap, boolean ascOrder) {
        List<Map.Entry<String, Integer>> entries = new LinkedList<>(unSortMap.entrySet());
        entries.sort((o1, o2) -> ascOrder ? o1.getValue().compareTo(o2.getValue()) == 0
                ? o1.getKey().compareTo(o2.getKey())
                : o1.getValue().compareTo(o2.getValue()) : o2.getValue().compareTo(o1.getValue()) == 0
                ? o2.getKey().compareTo(o1.getKey())
                : o2.getValue().compareTo(o1.getValue()));
        return entries.stream().collect(
                Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (a, b) -> b, LinkedHashMap::new)
        );
    }

    @Override
    public Integer isExistDay(LocalDate date) {
        for (int i = 0; i < listByDay.size(); i++) {
            if (listByDay.get(i).equals(date))
                return i;
        }
        return null;
    }

    @Override
    public Integer isExistMonth(int month, int year) {
        for (int i = 0; i < listByDay.size(); i++) {
            if (listByDay.get(i).getMonth() == month
                    && listByDay.get(i).getYear() == year)
                return i;
        }
        return null;
    }

    @Override
    public Integer isExistYear(int year) {
        for (int i = 0; i < listByDay.size(); i++) {
            if (listByDay.get(i).getYear() == year)
                return i;
        }
        return null;
    }

    public boolean isValidateFlag() {
        return validateFlag;
    }

    public void setValidateFlag(boolean b) {
        this.validateFlag = b;
    }
}

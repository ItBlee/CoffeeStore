package DTO;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ThongKeDTO {
    private LocalDate date;
    private YearMonth month;
    private int year;

    private Integer income = 0;
    private Integer expenses = 0;
    private Integer customer = 0;
    private Integer invoice = 0;
    private Integer input = 0;
    private Integer sale = 0;

    private Integer inProduct = 0;
    private Integer outProduct = 0;
    private Map<String, Integer> bestSeller = new HashMap<String, Integer>();

    public ThongKeDTO() {
    }

    public ThongKeDTO(LocalDate date) {
        this.date = date;
        month = YearMonth.from(this.date);
        year = this.date.getYear();
    }

    public ThongKeDTO(Date date) {
        this.date = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        month = YearMonth.from(this.date);
        year = this.date.getYear();
    }

    public Integer getDay() {
        return date.getDayOfMonth();
    }

    public void setDate(Date date) {
        this.date = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public Integer getMonth() {
        return month.getMonthValue();
    }

    public Integer getYear() {
        return year;
    }

    public Integer getIncome() {
        return income;
    }

    public void setMonth(YearMonth month) {
        this.month = month;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setIncome(Integer income) {
        this.income = income;
    }

    public Integer getExpenses() {
        return expenses;
    }

    public void setExpenses(Integer expenses) {
        this.expenses = expenses;
    }

    public Integer getCustomer() {
        return customer;
    }

    public void setCustomer(Integer customer) {
        this.customer = customer;
    }

    public Integer getInvoice() {
        return invoice;
    }

    public void setInvoice(Integer invoice) {
        this.invoice = invoice;
    }

    public Integer getInput() {
        return input;
    }

    public void setInput(Integer input) {
        this.input = input;
    }

    public Integer getSale() {
        return sale;
    }

    public void setSale(Integer sale) {
        this.sale = sale;
    }

    public Integer getInProduct() {
        return inProduct;
    }

    public void setInProduct(Integer inProduct) {
        this.inProduct = inProduct;
    }

    public Integer getOutProduct() {
        return outProduct;
    }

    public void setOutProduct(Integer outProduct) {
        this.outProduct = outProduct;
    }

    public Map<String, Integer> getBestSeller() {
        return bestSeller;
    }

    public void setBestSeller(Map<String, Integer> bestSeller) {
        this.bestSeller = bestSeller;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o instanceof ThongKeDTO) {
            ThongKeDTO that = (ThongKeDTO) o;
            return date.equals(that.date);
        }
        if (o instanceof Date) {
            Date that = (Date) o;
            return this.date.equals(that.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        }
        if (o instanceof LocalDate) {
            LocalDate that = (LocalDate) o;
            return this.date.equals(that);
        }
        return false;
    }
}

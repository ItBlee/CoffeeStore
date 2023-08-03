package com.itblee.gui.Form;

import com.itblee.Provider;
import com.itblee.dto.NhanVien;
import com.itblee.dto.ThongKe;
import com.itblee.gui.common.MyColor;
import com.itblee.gui.components.chart.Chart;
import com.itblee.gui.components.chart.ModelChart;
import com.itblee.service.KhuyenMaiService;
import com.itblee.service.NhanVienService;
import com.itblee.service.SanPhamService;
import com.itblee.service.ThongKeService;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class FormThongKe extends JTablePanel {

    private final ThongKeService thongKeService = Provider.get(ThongKeService.class);

    private final SanPhamService sanPhamService = Provider.get(SanPhamService.class);

    private final NhanVienService nhanVienService = Provider.get(NhanVienService.class);

    private final KhuyenMaiService khuyenMaiService = Provider.get(KhuyenMaiService.class);

    public FormThongKe() {

        initComponents();
        fillFormByCurrentMonth();
        rbSalesByMonth.setSelected(true);
    }

    private void fillFormByCurrentMonth() {
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
        ThongKe currentMonth = thongKeService.fillThongKeByMonth(YearMonth.now().getMonthValue(), YearMonth.now().getYear());
        ThongKe lastMonth = thongKeService.fillThongKeByMonth(YearMonth.now().getMonthValue()-1, YearMonth.now().getYear());

        double lastRevenue = lastMonth.getIncome() - lastMonth.getExpenses();
        double currentRevenue = currentMonth.getIncome() - currentMonth.getExpenses();
        int percentRevenue = 100;
        if (lastRevenue != 0)
            percentRevenue = (int) ((currentRevenue/lastRevenue) * 100);

        lbPercentRevenue.setText(currencyVN.format((int) currentRevenue).replace(" ₫", "").replace(".",",") + " (" + percentRevenue +"%)");
        int lowState = 20;
        int mediumState = 50;
        if (percentRevenue < lowState) {
            lbPercentRevenue.setForeground(MyColor.RED);
            lbCurrentRevenue.setBackground(new Color(255, 153, 153));
        } else if (percentRevenue < mediumState) {
            lbPercentRevenue.setForeground(MyColor.ORANGE);
            lbCurrentRevenue.setBackground(new Color(255, 231, 153));
        } else {
            lbPercentRevenue.setForeground(MyColor.GREEN);
            lbCurrentRevenue.setBackground(new Color(153, 255, 153));
        }
        lbCurrentRevenue.setSize((lbOldRevenue.getWidth() * percentRevenue)/100 , lbCurrentRevenue.getHeight());

        if (lastMonth.getIncome() == 0 || currentMonth.getIncome() == 0) {
            lbTotalSalesCompare.setForeground(MyColor.RED);
            lbTotalSalesCompare.setText("+0%");
        } else if (currentMonth.getIncome() < lastMonth.getIncome()) {
            double deviant = lastMonth.getIncome() - currentMonth.getIncome();
            double percent = (deviant/(double)lastMonth.getIncome())*100;
            lbTotalSalesCompare.setForeground(MyColor.RED);
            lbTotalSalesCompare.setText("-" + (int) percent + "%");
        } else {
            double deviant = currentMonth.getIncome() - lastMonth.getIncome();
            double percent = (deviant/(double)lastMonth.getIncome())*100;
            lbTotalSalesCompare.setForeground(MyColor.GREEN);
            lbTotalSalesCompare.setText("+" + (int) percent + "%");
        }

        if (lastMonth.getExpenses() == 0 || currentMonth.getExpenses() == 0) {
            lbTotalExpensesCompare.setForeground(MyColor.RED);
            lbTotalExpensesCompare.setText("-0%");
        } else if (currentMonth.getExpenses() < lastMonth.getExpenses()) {
            double deviant = lastMonth.getExpenses() - currentMonth.getExpenses();
            double percent = (deviant/(double)lastMonth.getExpenses())*100;
            lbTotalExpensesCompare.setForeground(MyColor.GREEN);
            lbTotalExpensesCompare.setText("-" + (int) percent + "%");
        } else {
            double deviant = currentMonth.getExpenses() - lastMonth.getExpenses();
            double percent = (deviant/(double)lastMonth.getExpenses())*100;
            lbTotalExpensesCompare.setForeground(MyColor.RED);
            lbTotalExpensesCompare.setText("+" + (int) percent + "%");
        }

        if (lastMonth.getCustomer() == 0 || currentMonth.getCustomer() == 0) {
            lbTotalCustomerCompare.setForeground(MyColor.RED);
            lbTotalCustomerCompare.setText("+0%");
        } else if (currentMonth.getCustomer() < lastMonth.getCustomer()) {
            double deviant = lastMonth.getCustomer() - currentMonth.getCustomer();
            double percent = (deviant/(double)lastMonth.getCustomer())*100;
            lbTotalCustomerCompare.setForeground(MyColor.RED);
            lbTotalCustomerCompare.setText("-" + (int) percent + "%");
        } else {
            double deviant = currentMonth.getCustomer() - lastMonth.getCustomer();
            double percent = (deviant/(double)lastMonth.getCustomer())*100;
            lbTotalCustomerCompare.setForeground(MyColor.GREEN);
            lbTotalCustomerCompare.setText("+" + (int) percent + "%");
        }

        if (lastMonth.getInvoice() == 0 || currentMonth.getInvoice() == 0) {
            lbTotalBillPanelCompare.setForeground(MyColor.RED);
            lbTotalBillPanelCompare.setText("+0%");
        } else if (currentMonth.getInvoice() < lastMonth.getInvoice()) {
            double deviant = lastMonth.getInvoice() - currentMonth.getInvoice();
            double percent = (deviant/(double)lastMonth.getInvoice())*100;
            lbTotalBillPanelCompare.setForeground(MyColor.RED);
            lbTotalBillPanelCompare.setText("-" + (int) percent + "%");
        } else {
            double deviant = currentMonth.getInvoice() - lastMonth.getInvoice();
            double percent = (deviant/(double)lastMonth.getInvoice())*100;
            lbTotalBillPanelCompare.setForeground(MyColor.GREEN);
            lbTotalBillPanelCompare.setText("+" + (int) percent + "%");
        }

        lbTotalSalesValue.setText(currencyVN.format(currentMonth.getIncome()).replace(" ₫", "").replace(".",","));
        lbTotalExpensesValue.setText(currencyVN.format(currentMonth.getExpenses()).replace(" ₫", "").replace(".",","));
        lbTotalCustomerValue.setText(String.valueOf(currentMonth.getCustomer()));
        lbTotalBillPanelValue.setText(String.valueOf(currentMonth.getInvoice()));
        lbEmployeeCount.setText(nhanVienService.getTotalCount() + " nhân viên");

        int totalEmployeeSalary = 0;
        for (NhanVien dto: nhanVienService.findAll())
            totalEmployeeSalary += dto.getLuong();
        lbEmployeeValue.setText(currencyVN.format(totalEmployeeSalary).replace(" ₫", "").replace(".",","));

        lbProductCount.setText(sanPhamService.getTotalCount() + " sản phẩm");
        lbProductReceivedValue.setText(currentMonth.getInProduct() + " sp");
        lbProductSoldValue.setText(currentMonth.getOutProduct() + " sp");

        lbStockReceivedCount.setText(currentMonth.getInput() + " phiếu nhập");
        lbStockReceivedValue.setText(currencyVN.format(currentMonth.getExpenses()).replace(" ₫", "").replace(".",","));

        lbPromotionCount.setText(khuyenMaiService.getTotalCount() + " khuyến mãi");
        lbPromotionValue.setText(currencyVN.format(currentMonth.getSale()).replace(" ₫", "").replace(".",","));

        int index = 0;
        String[] bestName = new String[4];
        Integer[] bestSold = new Integer[4];
        for (Map.Entry<String, Integer> entry:currentMonth.getBestSeller().entrySet()) {
            if (index == 4)
                break;
            bestName[index] = entry.getKey();
            bestSold[index] = entry.getValue();
            index++;
        }
        bestSellNameColumn1.setText(bestName[0]);
        bestSellNameColumn2.setText(bestName[1]);
        bestSellNameColumn3.setText(bestName[2]);
        bestSellNameColumn4.setText(bestName[3]);
        bestSellSoldColumn1.setText(bestSold[0] != null ? String.valueOf(bestSold[0]) : "");
        bestSellSoldColumn2.setText(bestSold[1] != null ? String.valueOf(bestSold[1]) : "");
        bestSellSoldColumn3.setText(bestSold[2] != null ? String.valueOf(bestSold[2]) : "");
        bestSellSoldColumn4.setText(bestSold[3] != null ? String.valueOf(bestSold[3]) : "");
    }
    
    private void initComponents() {
        setLayout(null);

        overviewPanel.setBackground(new Color(255, 255, 255));
        overviewPanel.setLayout(null);


        totalExpensesPanel.setBackground(new Color(254, 228, 226));
        totalExpensesPanel.setLayout(null);

        lbOverviewTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lbOverviewTitle.setForeground(new Color(37, 57, 111));
        lbOverviewTitle.setText("Tổng quan");
        overviewPanel.add(lbOverviewTitle);
        lbOverviewTitle.setBounds(10, 10, 230, 30);

        JLabel icTotalExpenses = new JLabel();
        icTotalExpenses.setIcon(new ImageIcon("bin/images/FormThongKe/pay.png"));
        totalExpensesPanel.add(icTotalExpenses);
        icTotalExpenses.setBounds(10, 10, 24, 24);

        lbTotalExpensesCompare.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbTotalExpensesCompare.setForeground(new Color(47, 168, 79));
        lbTotalExpensesCompare.setText("+100%");
        totalExpensesPanel.add(lbTotalExpensesCompare);
        lbTotalExpensesCompare.setBounds(120, 10, 50, 20);

        lbTotalExpensesValue.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lbTotalExpensesValue.setForeground(new Color(37, 57, 111));
        lbTotalExpensesValue.setText("2.000.000.000");
        totalExpensesPanel.add(lbTotalExpensesValue);
        lbTotalExpensesValue.setBounds(10, 40, 150, 25);

        lbTotalExpensesLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbTotalExpensesLabel.setForeground(new Color(145, 160, 193));
        lbTotalExpensesLabel.setText("Tổng chi");
        totalExpensesPanel.add(lbTotalExpensesLabel);
        lbTotalExpensesLabel.setBounds(10, 70, 150, 20);

        overviewPanel.add(totalExpensesPanel);
        totalExpensesPanel.setBounds(190, 60, 170, 100);

        totalSalesPanel.setBackground(new Color(220, 247, 227));
        totalSalesPanel.setLayout(null);

        JLabel icTotalSales = new JLabel();
        icTotalSales.setIcon(new ImageIcon("bin/images/FormThongKe/income.png"));
        totalSalesPanel.add(icTotalSales);
        icTotalSales.setBounds(10, 10, 24, 24);

        lbTotalSalesCompare.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbTotalSalesCompare.setForeground(new Color(234, 61, 47));
        lbTotalSalesCompare.setText("-37%");
        totalSalesPanel.add(lbTotalSalesCompare);
        lbTotalSalesCompare.setBounds(120, 10, 50, 20);

        lbTotalSalesValue.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lbTotalSalesValue.setForeground(new Color(37, 57, 111));
        lbTotalSalesValue.setText("3.000.000.000");
        totalSalesPanel.add(lbTotalSalesValue);
        lbTotalSalesValue.setBounds(10, 40, 150, 25);

        lbTotalSalesLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbTotalSalesLabel.setForeground(new Color(145, 160, 193));
        lbTotalSalesLabel.setText("Tổng thu");
        totalSalesPanel.add(lbTotalSalesLabel);
        lbTotalSalesLabel.setBounds(10, 70, 150, 20);

        overviewPanel.add(totalSalesPanel);
        totalSalesPanel.setBounds(10, 60, 170, 100);

        totalBillPanel.setBackground(new Color(229, 239, 255));
        totalBillPanel.setLayout(null);

        JLabel icTotalBill = new JLabel();
        icTotalBill.setIcon(new ImageIcon("bin/images/FormThongKe/bill.png"));
        totalBillPanel.add(icTotalBill);
        icTotalBill.setBounds(10, 10, 24, 24);

        lbTotalBillPanelCompare.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbTotalBillPanelCompare.setForeground(new Color(47, 168, 79));
        lbTotalBillPanelCompare.setText("+100%");
        totalBillPanel.add(lbTotalBillPanelCompare);
        lbTotalBillPanelCompare.setBounds(120, 10, 50, 20);

        lbTotalBillPanelValue.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lbTotalBillPanelValue.setForeground(new Color(37, 57, 111));
        lbTotalBillPanelValue.setText("4.500");
        totalBillPanel.add(lbTotalBillPanelValue);
        lbTotalBillPanelValue.setBounds(10, 40, 150, 25);

        lbTotalBillPanelLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbTotalBillPanelLabel.setForeground(new Color(145, 160, 193));
        lbTotalBillPanelLabel.setText("Hóa đơn");
        totalBillPanel.add(lbTotalBillPanelLabel);
        lbTotalBillPanelLabel.setBounds(10, 70, 150, 20);

        overviewPanel.add(totalBillPanel);
        totalBillPanel.setBounds(550, 60, 170, 100);

        totalCustomerPanel.setBackground(new Color(252, 243, 215));
        totalCustomerPanel.setLayout(null);

        JLabel icTotalCustomer = new JLabel();
        icTotalCustomer.setIcon(new ImageIcon("bin/images/FormThongKe/customer.png"));
        totalCustomerPanel.add(icTotalCustomer);
        icTotalCustomer.setBounds(10, 10, 24, 24);

        lbTotalCustomerCompare.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbTotalCustomerCompare.setForeground(new Color(243, 170, 24));
        lbTotalCustomerCompare.setText("-55%");
        totalCustomerPanel.add(lbTotalCustomerCompare);
        lbTotalCustomerCompare.setBounds(120, 10, 50, 20);

        lbTotalCustomerValue.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lbTotalCustomerValue.setForeground(new Color(37, 57, 111));
        lbTotalCustomerValue.setText("2.000");
        totalCustomerPanel.add(lbTotalCustomerValue);
        lbTotalCustomerValue.setBounds(10, 40, 150, 25);

        lbTotalCustomerLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbTotalCustomerLabel.setForeground(new Color(145, 160, 193));
        lbTotalCustomerLabel.setText("Khách hàng");
        totalCustomerPanel.add(lbTotalCustomerLabel);
        lbTotalCustomerLabel.setBounds(10, 70, 150, 20);

        overviewPanel.add(totalCustomerPanel);
        totalCustomerPanel.setBounds(370, 60, 170, 100);

        btnReset.setIcon(new ImageIcon("bin/images/components/reset.png"));
        btnReset.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnReset.addActionListener(e -> onClickBtnResetListener());
        overviewPanel.add(btnReset);
        btnReset.setBounds(680, 10, 40, 40);

        add(overviewPanel);
        overviewPanel.setBounds(10, 10, 730, 170);

        salesPanel.setBackground(new Color(255, 255, 255));
        salesPanel.setLayout(null);

        rbSalesByDay.setText("Theo ngày");
        rbSalesByDay.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                applyDateSelector();
            }
        });
        salesPanel.add(rbSalesByDay);
        rbSalesByDay.setBounds(740, 20, 104, 22);

        rbSalesByYear.setText("Theo năm");
        rbSalesByYear.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                applyDateSelector();
            }
        });
        salesPanel.add(rbSalesByYear);
        rbSalesByYear.setBounds(540, 20, 81, 22);

        rbSalesByMonth.setText("Theo tháng");
        rbSalesByMonth.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                applyDateSelector();
            }
        });
        salesPanel.add(rbSalesByMonth);
        rbSalesByMonth.setBounds(640, 20, 104, 22);

        group.add(rbSalesByDay);
        group.add(rbSalesByMonth);
        group.add(rbSalesByYear);

        dcSelectDate.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        dcSelectDate.setDateFormatString("dd/MM/yyyy");
        dcSelectDate.setEnabled(false);
        dcSelectDate.getCalendarButton().setEnabled(true);
        dcSelectDate.setDate(new Date(System.currentTimeMillis()));
        dcSelectDate.getDateEditor().addPropertyChangeListener(e -> {
            if ("date".equals(e.getPropertyName()))
                applyDateSelector();
        });
        salesPanel.add(dcSelectDate);
        dcSelectDate.setBounds(840, 10, 130, 40);

        lvSalesTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lvSalesTitle.setForeground(new Color(37, 57, 111));
        lvSalesTitle.setText("Doanh số");
        salesPanel.add(lvSalesTitle);
        lvSalesTitle.setBounds(10, 10, 190, 30);

        salesPanel.add(chart);
        chart.setBounds(22, 60, 940, 350);

        add(salesPanel);
        salesPanel.setBounds(10, 380, 980, 420);

        bestsellerPanel.setBackground(new Color(255, 255, 255));
        bestsellerPanel.setLayout(null);

        lbBestSellTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lbBestSellTitle.setForeground(new Color(37, 57, 111));
        lbBestSellTitle.setText("Bán chạy");
        bestsellerPanel.add(lbBestSellTitle);
        lbBestSellTitle.setBounds(10, 10, 190, 30);

        bestSellHeaderTable.setBackground(new Color(245, 249, 255));
        bestSellHeaderTable.setLayout(null);

        lbBestsellerProduct.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lbBestsellerProduct.setForeground(new Color(124, 141, 181));
        lbBestsellerProduct.setText("Sản phẩm");
        bestSellHeaderTable.add(lbBestsellerProduct);
        lbBestsellerProduct.setBounds(10, 10, 70, 18);

        lbBestsellerSold.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lbBestsellerSold.setForeground(new Color(124, 141, 181));
        lbBestsellerSold.setText("Bán");
        bestSellHeaderTable.add(lbBestsellerSold);
        lbBestsellerSold.setBounds(160, 10, 30, 18);

        bestsellerPanel.add(bestSellHeaderTable);
        bestSellHeaderTable.setBounds(10, 60, 220, 40);

        JLabel icBestseller = new JLabel();
        icBestseller.setIcon(new ImageIcon("bin/images/FormThongKe/best.png"));
        bestsellerPanel.add(icBestseller);
        icBestseller.setBounds(47, 110, 20, 20);

        lbBestsellerBest.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbBestsellerBest.setForeground(new Color(155, 84, 225));
        lbBestsellerBest.setText("BEST");
        bestsellerPanel.add(lbBestsellerBest);
        lbBestsellerBest.setBounds(10, 110, 32, 20);

        bestSellColumnLine.setBackground(new Color(245, 249, 255));
        bestSellColumnLine.setLayout(null);

        bestsellerPanel.add(bestSellColumnLine);
        bestSellColumnLine.setBounds(160, 110, 5, 300);

        bestsellerBestRow.setBackground(new Color(255, 255, 255));
        bestsellerBestRow.setBorder(new LineBorder(new Color(155, 84, 225), 2, true));
        bestsellerBestRow.setOpaque(false);
        bestsellerBestRow.setLayout(null);

        bestSellNameColumn1.setFont(new Font("Segoe UI", Font.BOLD, 13));
        bestSellNameColumn1.setText("Coca cola");
        bestsellerBestRow.add(bestSellNameColumn1);
        bestSellNameColumn1.setBounds(10, 10, 140, 18);

        bestSellSoldColumn1.setText("200");
        bestsellerBestRow.add(bestSellSoldColumn1);
        bestSellSoldColumn1.setBounds(160, 10, 100, 18);

        bestsellerPanel.add(bestsellerBestRow);
        bestsellerBestRow.setBounds(10, 130, 220, 40);

        bestsellerRow1.setBackground(new Color(255, 255, 255));
        bestsellerRow1.setBorder(BorderFactory.createMatteBorder(0, 0, 5, 0, new Color(245, 249, 255)));
        bestsellerRow1.setOpaque(false);
        bestsellerRow1.setLayout(null);

        bestSellNameColumn2.setFont(new Font("Segoe UI", Font.BOLD, 13));
        bestSellNameColumn2.setText("Coca cola");
        bestsellerRow1.add(bestSellNameColumn2);
        bestSellNameColumn2.setBounds(10, 10, 140, 18);

        bestSellSoldColumn2.setText("200");
        bestsellerRow1.add(bestSellSoldColumn2);
        bestSellSoldColumn2.setBounds(160, 10, 100, 18);

        bestsellerPanel.add(bestsellerRow1);
        bestsellerRow1.setBounds(10, 180, 220, 40);

        bestsellerRow2.setBackground(new Color(255, 255, 255));
        bestsellerRow2.setBorder(BorderFactory.createMatteBorder(0, 0, 5, 0, new Color(245, 249, 255)));
        bestsellerRow2.setOpaque(false);
        bestsellerRow2.setLayout(null);

        bestSellNameColumn3.setFont(new Font("Segoe UI", Font.BOLD, 13));
        bestSellNameColumn3.setText("Coca cola");
        bestsellerRow2.add(bestSellNameColumn3);
        bestSellNameColumn3.setBounds(10, 10, 140, 18);

        bestSellSoldColumn3.setText("200");
        bestsellerRow2.add(bestSellSoldColumn3);
        bestSellSoldColumn3.setBounds(160, 10, 100, 18);

        bestsellerPanel.add(bestsellerRow2);
        bestsellerRow2.setBounds(10, 230, 220, 40);

        bestsellerRow3.setBackground(new Color(255, 255, 255));
        bestsellerRow3.setBorder(BorderFactory.createMatteBorder(0, 0, 5, 0, new Color(245, 249, 255)));
        bestsellerRow3.setOpaque(false);
        bestsellerRow3.setLayout(null);

        bestSellNameColumn4.setFont(new Font("Segoe UI", Font.BOLD, 13));
        bestSellNameColumn4.setText("Coca cola");
        bestsellerRow3.add(bestSellNameColumn4);
        bestSellNameColumn4.setBounds(10, 10, 140, 18);

        bestSellSoldColumn4.setText("200");
        bestsellerRow3.add(bestSellSoldColumn4);
        bestSellSoldColumn4.setBounds(160, 10, 100, 18);

        bestsellerPanel.add(bestsellerRow3);
        bestsellerRow3.setBounds(10, 280, 220, 40);

        add(bestsellerPanel);
        bestsellerPanel.setBounds(750, 10, 240, 360);

        detailPanel.setLayout(null);

        promotionPanel.setBackground(new Color(255, 255, 255));
        promotionPanel.setLayout(null);

        lbPromotionLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbPromotionLabel.setForeground(new Color(145, 160, 193));
        lbPromotionLabel.setText("Tiền giảm");
        promotionPanel.add(lbPromotionLabel);
        lbPromotionLabel.setBounds(10, 60, 150, 20);

        lbPromotionCount.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lbPromotionCount.setForeground(new Color(145, 160, 193));
        lbPromotionCount.setHorizontalAlignment(SwingConstants.CENTER);
        lbPromotionCount.setText("12 Khuyến mãi");
        promotionPanel.add(lbPromotionCount);
        lbPromotionCount.setBounds(20, 20, 130, 25);

        lbPromotionValue.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lbPromotionValue.setForeground(new Color(37, 57, 111));
        lbPromotionValue.setText("50.000.000 đ");
        promotionPanel.add(lbPromotionValue);
        lbPromotionValue.setBounds(10, 80, 150, 25);

        detailPanel.add(promotionPanel);
        promotionPanel.setBounds(550, 0, 180, 120);

        productPanel.setBackground(new Color(255, 255, 255));
        productPanel.setLayout(null);

        lbProductSold.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbProductSold.setForeground(new Color(145, 160, 193));
        lbProductSold.setText("Bán:");
        productPanel.add(lbProductSold);
        lbProductSold.setBounds(10, 80, 30, 20);

        lbProductCount.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lbProductCount.setForeground(new Color(145, 160, 193));
        lbProductCount.setHorizontalAlignment(SwingConstants.CENTER);
        lbProductCount.setText("200 Sản phẩm");
        productPanel.add(lbProductCount);
        lbProductCount.setBounds(20, 20, 130, 25);

        lbProductLabelReceived.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbProductLabelReceived.setForeground(new Color(145, 160, 193));
        lbProductLabelReceived.setText("Nhập:");
        productPanel.add(lbProductLabelReceived);
        lbProductLabelReceived.setBounds(10, 60, 40, 20);

        lbProductReceivedValue.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbProductReceivedValue.setForeground(new Color(37, 57, 111));
        lbProductReceivedValue.setText("500 sp");
        productPanel.add(lbProductReceivedValue);
        lbProductReceivedValue.setBounds(60, 60, 90, 20);

        lbProductSoldValue.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbProductSoldValue.setForeground(new Color(37, 57, 111));
        lbProductSoldValue.setText("300 sp");
        productPanel.add(lbProductSoldValue);
        lbProductSoldValue.setBounds(60, 80, 90, 20);

        detailPanel.add(productPanel);
        productPanel.setBounds(190, 0, 170, 120);

        employeePanel.setBackground(new Color(255, 255, 255));
        employeePanel.setLayout(null);

        lbEmployeeLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbEmployeeLabel.setForeground(new Color(145, 160, 193));
        lbEmployeeLabel.setText("Tiền lương");
        employeePanel.add(lbEmployeeLabel);
        lbEmployeeLabel.setBounds(10, 60, 150, 20);

        lbEmployeeCount.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lbEmployeeCount.setForeground(new Color(145, 160, 193));
        lbEmployeeCount.setHorizontalAlignment(SwingConstants.CENTER);
        lbEmployeeCount.setText("20 Nhân viên");
        employeePanel.add(lbEmployeeCount);
        lbEmployeeCount.setBounds(20, 20, 130, 25);

        lbEmployeeValue.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lbEmployeeValue.setForeground(new Color(37, 57, 111));
        lbEmployeeValue.setText("200.000.000 đ");
        employeePanel.add(lbEmployeeValue);
        lbEmployeeValue.setBounds(10, 80, 150, 25);

        detailPanel.add(employeePanel);
        employeePanel.setBounds(0, 0, 180, 120);

        stockReceivedPanel.setBackground(new Color(255, 255, 255));
        stockReceivedPanel.setLayout(null);

        lbStockReceivedLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbStockReceivedLabel.setForeground(new Color(145, 160, 193));
        lbStockReceivedLabel.setText("Tiền chi");
        stockReceivedPanel.add(lbStockReceivedLabel);
        lbStockReceivedLabel.setBounds(10, 60, 150, 20);

        lbStockReceivedCount.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lbStockReceivedCount.setForeground(new Color(145, 160, 193));
        lbStockReceivedCount.setHorizontalAlignment(SwingConstants.CENTER);
        lbStockReceivedCount.setText("56 phiếu nhập");
        stockReceivedPanel.add(lbStockReceivedCount);
        lbStockReceivedCount.setBounds(20, 20, 130, 25);

        lbStockReceivedValue.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lbStockReceivedValue.setForeground(new Color(37, 57, 111));
        lbStockReceivedValue.setText("700.000.000 đ");
        stockReceivedPanel.add(lbStockReceivedValue);
        lbStockReceivedValue.setBounds(10, 80, 150, 25);

        detailPanel.add(stockReceivedPanel);
        stockReceivedPanel.setBounds(370, 0, 170, 120);

        add(detailPanel);
        detailPanel.setBounds(10, 250, 730, 120);

        revenuePanel.setBackground(new Color(255, 255, 255));
        revenuePanel.setLayout(null);

        lbRevenueTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lbRevenueTitle.setForeground(new Color(37, 57, 111));
        lbRevenueTitle.setText("Doanh thu:");
        revenuePanel.add(lbRevenueTitle);
        lbRevenueTitle.setBounds(10, 10, 140, 30);

        lbPercentRevenue.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lbPercentRevenue.setForeground(new Color(47, 168, 79));
        lbPercentRevenue.setHorizontalAlignment(SwingConstants.CENTER);
        lbPercentRevenue.setText("1.000.000.000 (80%)");
        revenuePanel.add(lbPercentRevenue);
        lbPercentRevenue.setBounds(190, 10, 530, 30);

        lbCurrentRevenue.setBackground(new Color(153, 255, 153));
        lbCurrentRevenue.setLayout(null);
        revenuePanel.add(lbCurrentRevenue);
        lbCurrentRevenue.setBounds(190, 10, 440, 30);

        lbOldRevenue.setLayout(null);
        revenuePanel.add(lbOldRevenue);
        lbOldRevenue.setBounds(190, 10, 530, 30);

        add(revenuePanel);
        revenuePanel.setBounds(10, 190, 730, 50);
    }

    private void onClickBtnResetListener() {
        thongKeService.setValidateFlag(true);
        fillFormByCurrentMonth();
        dcSelectDate.setDate(new Date(System.currentTimeMillis()));
        thongKeService.setValidateFlag(false);
    }

    private void applyDateSelector() {
        thongKeService.setValidateFlag(true);
        LocalDate date = dcSelectDate.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        salesPanel.remove(chart);
        chart = new Chart();
        chart.addLegend("Tổng thu", new Color(47, 168, 79));
        chart.addLegend("Tông chi", new Color(234, 61, 47));
        chart.addLegend("Doanh thu", new Color(54, 123, 245));

        String label = "";
        String[] dateStr = new String[5];
        double[][] value = new double[5][3];
        LocalDate[] dates = new LocalDate[5];
        if (rbSalesByDay.isSelected()) {
            label = "";
            dates[0] = date.minusDays(2);
            dates[1] = date.minusDays(1);
            dates[2] = date;
            dates[3] = date.plusDays(1);
            dates[4] = date.plusDays(2);
            for (int i = 0; i < 5; i++) {
                dateStr[i] = dates[i].getDayOfMonth() + "/" + dates[i].getMonthValue() + "/" + dates[i].getYear();
            }
            List<ThongKe> dtos = thongKeService.fillThongKeByDate(dates[0], dates[4].plusDays(1));
            for (int i = 0; i < 5; i++) {
                double income = dtos.get(i).getIncome();
                double expenses = dtos.get(i).getExpenses();
                double profit = dtos.get(i).getIncome() - dtos.get(i).getExpenses();
                if (profit < 0)
                    profit = 0;
                value[i] = new double[] {income, expenses, profit};
            }
        } else if (rbSalesByMonth.isSelected()) {
            label = "Tháng ";
            dates[0] = date.minusMonths(2);
            dates[1] = date.minusMonths(1);
            dates[2] = date;
            dates[3] = date.plusMonths(1);
            dates[4] = date.plusMonths(2);
            for (int i = 0; i < 5; i++) {
                dateStr[i] = String.valueOf(dates[i].getMonthValue());
            }
            for (int i = 0; i < 5; i++) {
                ThongKe dto = thongKeService.fillThongKeByMonth(dates[i].getMonthValue(), dates[i].getYear());
                if (dto == null)
                    continue;
                double income = dto.getIncome();
                double expenses = dto.getExpenses();
                double profit = dto.getIncome() - dto.getExpenses();
                if (profit < 0)
                    profit = 0;
                value[i] = new double[] {income, expenses, profit};
            }
        } else if (rbSalesByYear.isSelected()) {
            label = "";
            dates[0] = date.minusYears(2);
            dates[1] = date.minusYears(1);
            dates[2] = date;
            dates[3] = date.plusYears(1);
            dates[4] = date.plusYears(2);
            for (int i = 0; i < 5; i++) {
                dateStr[i] = String.valueOf(dates[i].getYear());
            }
            for (int i = 0; i < 5; i++) {
                ThongKe dto = thongKeService.fillThongKeByYear(dates[i].getYear());
                if (dto == null)
                    continue;
                double income = dto.getIncome();
                double expenses = dto.getExpenses();
                double profit = dto.getIncome() - dto.getExpenses();
                if (profit < 0)
                    profit = 0;
                value[i] = new double[] {income, expenses, profit};
            }
        }

        for (int i = 0; i < 5; i++)
            chart.addData(new ModelChart(label + dateStr[i], value[i]));
        salesPanel.add(chart);
        chart.setBounds(22, 60, 940, 350);
        salesPanel.revalidate();
        salesPanel.repaint();
        thongKeService.setValidateFlag(false);
    }

    private Chart chart = new Chart();
    private final JButton btnReset = new JButton();
    private final ButtonGroup group = new ButtonGroup();
    private final JPanel overviewPanel = new JPanel();
    private final JPanel totalExpensesPanel = new JPanel();
    private final JLabel lbTotalExpensesCompare = new JLabel();
    private final JLabel lbTotalExpensesValue = new JLabel();
    private final JLabel lbTotalExpensesLabel = new JLabel();
    private final JLabel lbOverviewTitle = new JLabel();
    private final JPanel totalSalesPanel = new JPanel();
    private final JLabel lbTotalSalesCompare = new JLabel();
    private final JLabel lbTotalSalesValue = new JLabel();
    private final JLabel lbTotalSalesLabel = new JLabel();
    private final JPanel totalBillPanel = new JPanel();
    private final JLabel lbTotalBillPanelCompare = new JLabel();
    private final JLabel lbTotalBillPanelValue = new JLabel();
    private final JLabel lbTotalBillPanelLabel = new JLabel();
    private final JPanel totalCustomerPanel = new JPanel();
    private final JLabel lbTotalCustomerCompare = new JLabel();
    private final JLabel lbTotalCustomerValue = new JLabel();
    private final JLabel lbTotalCustomerLabel = new JLabel();
    private final JPanel salesPanel = new JPanel();
    private final JRadioButton rbSalesByDay = new JRadioButton();
    private final JRadioButton rbSalesByYear = new JRadioButton();
    private final JRadioButton rbSalesByMonth = new JRadioButton();
    private final JDateChooser dcSelectDate = new JDateChooser();
    private final JLabel lvSalesTitle = new JLabel();
    private final JPanel bestsellerPanel = new JPanel();
    private final JLabel lbBestSellTitle = new JLabel();
    private final JPanel bestSellHeaderTable = new JPanel();
    private final JLabel lbBestsellerProduct = new JLabel();
    private final JLabel lbBestsellerSold = new JLabel();
    private final JLabel lbBestsellerBest = new JLabel();
    private final JPanel bestSellColumnLine = new JPanel();
    private final JPanel bestsellerBestRow = new JPanel();
    private final JLabel bestSellNameColumn1 = new JLabel();
    private final JLabel bestSellSoldColumn1 = new JLabel();
    private final JPanel bestsellerRow1 = new JPanel();
    private final JLabel bestSellNameColumn2 = new JLabel();
    private final JLabel bestSellSoldColumn2 = new JLabel();
    private final JPanel bestsellerRow2 = new JPanel();
    private final JLabel bestSellNameColumn3 = new JLabel();
    private final JLabel bestSellSoldColumn3 = new JLabel();
    private final JPanel bestsellerRow3 = new JPanel();
    private final JLabel bestSellNameColumn4 = new JLabel();
    private final JLabel bestSellSoldColumn4 = new JLabel();
    private final JPanel detailPanel = new JPanel();
    private final JPanel promotionPanel = new JPanel();
    private final JLabel lbPromotionLabel = new JLabel();
    private final JLabel lbPromotionCount = new JLabel();
    private final JLabel lbPromotionValue = new JLabel();
    private final JPanel productPanel = new JPanel();
    private final JLabel lbProductSold = new JLabel();
    private final JLabel lbProductCount = new JLabel();
    private final JLabel lbProductLabelReceived = new JLabel();
    private final JLabel lbProductReceivedValue = new JLabel();
    private final JLabel lbProductSoldValue = new JLabel();
    private final JPanel employeePanel = new JPanel();
    private final JLabel lbEmployeeLabel = new JLabel();
    private final JLabel lbEmployeeCount = new JLabel();
    private final JLabel lbEmployeeValue = new JLabel();
    private final JPanel stockReceivedPanel = new JPanel();
    private final JLabel lbStockReceivedLabel = new JLabel();
    private final JLabel lbStockReceivedCount = new JLabel();
    private final JLabel lbStockReceivedValue = new JLabel();
    private final JPanel revenuePanel = new JPanel();
    private final JLabel lbRevenueTitle = new JLabel();
    private final JLabel lbPercentRevenue = new JLabel();
    private final JPanel lbCurrentRevenue = new JPanel();
    private final JPanel lbOldRevenue = new JPanel();
}

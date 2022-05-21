package GUI.Form;

import GUI.components.chart.Chart;
import GUI.components.chart.ModelChart;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class FormThongKe extends JPanel {
    public FormThongKe() {
        initComponents();
    }
    
    private void initComponents() {
        JPanel overviewPanel = new JPanel();
        JPanel totalExpensesPanel = new JPanel();
        JLabel lbTotalExpensesCompare = new JLabel();
        JLabel lbTotalExpensesValue = new JLabel();
        JLabel lbTotalExpensesLabel = new JLabel();
        JLabel lbOverviewTitle = new JLabel();
        JPanel totalSalesPanel = new JPanel();
        JLabel lbTotalSalesCompare = new JLabel();
        JLabel lbTotalSalesValue = new JLabel();
        JLabel lbTotalSalesLabel = new JLabel();
        JPanel totalBillPanel = new JPanel();
        JLabel lbTotalBillPanelCompare = new JLabel();
        JLabel lbTotalBillPanelValue = new JLabel();
        JLabel lbTotalBillPanelLabel = new JLabel();
        JPanel totalCustomerPanel = new JPanel();
        JLabel lbTotalCustomerCompare = new JLabel();
        JLabel lbTotalCustomerValue = new JLabel();
        JLabel lbTotalCustomerLabel = new JLabel();
        JPanel salesPanel = new JPanel();
        JRadioButton rbSalesByDay = new JRadioButton();
        JRadioButton rbSalesByYear = new JRadioButton();
        JRadioButton rbSalesByMonth = new JRadioButton();
        JComboBox<String> cbSelectDate = new JComboBox<>();
        JLabel lvSalesTitle = new JLabel();
        JPanel bestsellerPanel = new JPanel();
        JLabel lbBestSellTitle = new JLabel();
        JPanel bestSellHeaderTable = new JPanel();
        JLabel lbBestsellerProduct = new JLabel();
        JLabel lbBestsellerSold = new JLabel();
        JLabel lbBestsellerBest = new JLabel();
        JPanel bestSellColumnLine = new JPanel();
        JPanel bestsellerBestRow = new JPanel();
        JLabel bestSellNameColumn1 = new JLabel();
        JLabel bestSellSoldColumn1 = new JLabel();
        JPanel bestsellerRow1 = new JPanel();
        JLabel bestSellNameColumn2 = new JLabel();
        JLabel bestSellSoldColumn2 = new JLabel();
        JPanel bestsellerRow2 = new JPanel();
        JLabel bestSellNameColumn3 = new JLabel();
        JLabel bestSellSoldColumn3 = new JLabel();
        JPanel bestsellerRow3 = new JPanel();
        JLabel bestSellNameColumn4 = new JLabel();
        JLabel bestSellSoldColumn4 = new JLabel();
        JPanel detailPanel = new JPanel();
        JPanel promotionPanel = new JPanel();
        JLabel lbPromotionLabel = new JLabel();
        JLabel lbPromotionCount = new JLabel();
        JLabel lbPromotionValue = new JLabel();
        JPanel productPanel = new JPanel();
        JLabel lbProductSold = new JLabel();
        JLabel lbProductCount = new JLabel();
        JLabel lbProductLabelReceived = new JLabel();
        JLabel lbProductReceivedValue = new JLabel();
        JLabel lbProductSoldValue = new JLabel();
        JPanel employeePanel = new JPanel();
        JLabel lbEmployeeLabel = new JLabel();
        JLabel lbEmployeeCount = new JLabel();
        JLabel lbEmployeeValue = new JLabel();
        JPanel stockReceivedPanel = new JPanel();
        JLabel lbStockReceivedLabel = new JLabel();
        JLabel lbStockReceivedCount = new JLabel();
        JLabel lbStockReceivedValue = new JLabel();
        JPanel revenuePanel = new JPanel();
        JLabel lbRevenueTitle = new JLabel();
        JLabel lbPercentRevenue = new JLabel();
        JPanel lbCurrentRevenue = new JPanel();
        JPanel lbOldRevenue = new JPanel();

        setLayout(null);

        overviewPanel.setBackground(new Color(255, 255, 255));
        overviewPanel.setLayout(null);

        totalExpensesPanel.setBackground(new Color(254, 228, 226));
        totalExpensesPanel.setLayout(null);

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

        lbOverviewTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lbOverviewTitle.setForeground(new Color(37, 57, 111));
        lbOverviewTitle.setText("Tổng quan");
        overviewPanel.add(lbOverviewTitle);
        lbOverviewTitle.setBounds(10, 10, 230, 30);

        totalSalesPanel.setBackground(new Color(220, 247, 227));
        totalSalesPanel.setLayout(null);

        lbTotalSalesCompare.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbTotalSalesCompare.setForeground(new Color(47, 168, 79));
        lbTotalSalesCompare.setText("+100%");
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

        lbTotalCustomerCompare.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbTotalCustomerCompare.setForeground(new Color(47, 168, 79));
        lbTotalCustomerCompare.setText("+100%");
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

        add(overviewPanel);
        overviewPanel.setBounds(10, 10, 730, 170);

        salesPanel.setBackground(new Color(255, 255, 255));
        salesPanel.setLayout(null);

        rbSalesByDay.setText("Theo ngày");
        salesPanel.add(rbSalesByDay);
        rbSalesByDay.setBounds(760, 20, 104, 22);

        rbSalesByYear.setText("Theo năm");
        salesPanel.add(rbSalesByYear);
        rbSalesByYear.setBounds(560, 20, 81, 22);

        rbSalesByMonth.setText("Theo tháng");
        salesPanel.add(rbSalesByMonth);
        rbSalesByMonth.setBounds(660, 20, 104, 22);

        cbSelectDate.setModel(new DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        salesPanel.add(cbSelectDate);
        cbSelectDate.setBounds(860, 10, 110, 40);

        lvSalesTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lvSalesTitle.setForeground(new Color(37, 57, 111));
        lvSalesTitle.setText("Doanh số");
        salesPanel.add(lvSalesTitle);
        lvSalesTitle.setBounds(10, 10, 190, 30);

        Chart chart = new Chart();
        chart.addLegend("Tổng thu", new Color(47, 168, 79));
        chart.addLegend("Tông chi", new Color(234, 61, 47));
        chart.addLegend("Doanh thu", new Color(54, 123, 245));
        chart.addLegend("Khách hàng", new Color(243, 170, 24));
        chart.addData(new ModelChart("January", new double[]{100, 150, 200, 500}));
        chart.addData(new ModelChart("February", new double[]{600, 750, 300, 150}));
        chart.addData(new ModelChart("March", new double[]{200, 350, 1000, 900}));
        chart.addData(new ModelChart("April", new double[]{480, 150, 750, 700}));
        chart.addData(new ModelChart("May", new double[]{350, 540, 300, 150}));
        chart.addData(new ModelChart("June", new double[]{190, 500, 700, 1000}));
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
        bestSellSoldColumn1.setBounds(160, 10, 21, 18);

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
        bestSellSoldColumn2.setBounds(160, 10, 21, 18);

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
        bestSellSoldColumn3.setBounds(160, 10, 21, 18);

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
        bestSellSoldColumn4.setBounds(160, 10, 21, 18);

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
}

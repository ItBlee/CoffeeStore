package GUI.Form;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class FormExcel extends JPanel {
    public FormExcel() {
        initComponents();
    }
    
    private void initComponents() {
        JPanel importPanel = new JPanel();
        JPanel dragDropPanel = new JPanel();
        JLabel lbDragDrop = new JLabel();
        JLabel lbDragDrop2 = new JLabel();
        JLabel lbDragDrop3 = new JLabel();
        JButton btnImport = new JButton();
        JLabel icFile = new JLabel();
        JLabel lbPercentUpload = new JLabel();
        JPanel progressUpload = new JPanel();
        JPanel progressUploadHolder = new JPanel();
        JLabel lbFileName = new JLabel();
        JComboBox<String> cbTarget = new JComboBox<>();
        JLabel lbTarget = new JLabel();
        JLabel lbTitleImport = new JLabel();
        JPanel exportPanel = new JPanel();
        JLabel lbTitleExport = new JLabel();
        JLabel lbTickHD = new JLabel();
        JPanel exportHDPanel = new JPanel();
        JLabel lbIconHD = new JLabel();
        JLabel lbNameHD = new JLabel();
        JLabel lbSubHD = new JLabel();
        JLabel lbQuantityHD = new JLabel();
        JButton btnImport2 = new JButton();
        JLabel lbTickPN = new JLabel();
        JPanel exportPNPanel = new JPanel();
        JLabel jLabel8 = new JLabel();
        JLabel jLabel9 = new JLabel();
        JLabel jLabel11 = new JLabel();
        JLabel jLabel12 = new JLabel();
        JLabel lbTickKH = new JLabel();
        JPanel exportKHPanel = new JPanel();
        JLabel jLabel14 = new JLabel();
        JLabel jLabel15 = new JLabel();
        JLabel jLabel16 = new JLabel();
        JLabel jLabel17 = new JLabel();
        JLabel lbTickKM = new JLabel();
        JPanel exportKMPanel = new JPanel();
        JLabel jLabel19 = new JLabel();
        JLabel jLabel20 = new JLabel();
        JLabel jLabel21 = new JLabel();
        JLabel jLabel22 = new JLabel();
        JLabel jLabel28 = new JLabel();
        JPanel exportHDPanel4 = new JPanel();
        JLabel jLabel24 = new JLabel();
        JLabel jLabel25 = new JLabel();
        JLabel jLabel26 = new JLabel();
        JLabel jLabel27 = new JLabel();
        JLabel lbTickNV = new JLabel();
        JPanel exportNVPanel = new JPanel();
        JLabel jLabel29 = new JLabel();
        JLabel jLabel30 = new JLabel();
        JLabel jLabel31 = new JLabel();
        JLabel jLabel32 = new JLabel();
        JLabel lbTickSP = new JLabel();
        JPanel exportSPPanel = new JPanel();
        JLabel jLabel34 = new JLabel();
        JLabel jLabel35 = new JLabel();
        JLabel jLabel36 = new JLabel();
        JLabel jLabel37 = new JLabel();
        JLabel lbTickNCC = new JLabel();
        JPanel exportNCCPanel = new JPanel();
        JLabel jLabel39 = new JLabel();
        JLabel jLabel40 = new JLabel();
        JLabel jLabel41 = new JLabel();
        JLabel jLabel42 = new JLabel();
        JPanel mainPanel = new JPanel();
        JLabel lbTitlePDF = new JLabel();
        JButton btnExportPDF = new JButton();
        JTextField txtDetailPDF = new JTextField();
        JButton btnSelectHDPDF = new JButton();
        JButton btnSelectPNPDF = new JButton();
        JLabel lbCTExportPDF = new JLabel();

        setFont(new Font("Segoe UI", Font.BOLD, 13));
        setLayout(null);

        importPanel.setBackground(new Color(255, 255, 255));
        importPanel.setBorder(new LineBorder(new Color(72, 139, 255), 2, true));
        importPanel.setLayout(null);

        dragDropPanel.setBorder(BorderFactory.createDashedBorder(new Color(72, 139, 255), 2, 5, 2, true));
        dragDropPanel.setOpaque(false);
        dragDropPanel.setLayout(new BoxLayout(dragDropPanel, BoxLayout.PAGE_AXIS));

        dragDropPanel.add(Box.createVerticalGlue());
        lbDragDrop.setText("Kéo và Thả tập tin vào đây");
        lbDragDrop.setAlignmentX(CENTER_ALIGNMENT);
        dragDropPanel.add(lbDragDrop);
        dragDropPanel.add(Box.createRigidArea(new Dimension(0, 5)));

        lbDragDrop2.setText("hoặc");
        lbDragDrop2.setAlignmentX(CENTER_ALIGNMENT);
        dragDropPanel.add(lbDragDrop2);
        dragDropPanel.add(Box.createRigidArea(new Dimension(0, 5)));

        lbDragDrop3.setForeground(new Color(72, 139, 255));
        lbDragDrop3.setText("duyệt");
        lbDragDrop3.setAlignmentX(CENTER_ALIGNMENT);
        dragDropPanel.add(lbDragDrop3);
        dragDropPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        dragDropPanel.add(Box.createVerticalGlue());

        importPanel.add(dragDropPanel);
        dragDropPanel.setBounds(20, 220, 360, 180);

        btnImport.setBackground(new Color(72, 139, 255));
        btnImport.setFont(new Font("Segoe UI", Font.BOLD, 18));
        btnImport.setForeground(new Color(255, 255, 255));
        btnImport.setText("Nhập");
        btnImport.setActionCommand("Duyệt");
        importPanel.add(btnImport);
        btnImport.setBounds(20, 420, 360, 40);

        icFile.setOpaque(false);
        Image icFileImage = new ImageIcon("bin/images/components/excel.png").getImage().getScaledInstance(40,40,Image.SCALE_SMOOTH);
        icFile.setIcon(new ImageIcon(icFileImage));
        importPanel.add(icFile);
        icFile.setBounds(20, 160, 40, 40);

        lbPercentUpload.setText("0%");
        importPanel.add(lbPercentUpload);
        lbPercentUpload.setBounds(350, 160, 32, 18);

        progressUpload.setBackground(new Color(252, 201, 76));
        progressUpload.setLayout(null);
        importPanel.add(progressUpload);
        progressUpload.setBounds(70, 190, 0, 2);

        progressUploadHolder.setBackground(new Color(204, 204, 204));
        progressUploadHolder.setLayout(null);
        importPanel.add(progressUploadHolder);
        progressUploadHolder.setBounds(70, 190, 310, 2);

        lbFileName.setText("Chưa nhập file");
        importPanel.add(lbFileName);
        lbFileName.setBounds(70, 160, 360, 18);

        cbTarget.setModel(new DefaultComboBoxModel<>(new String[] { "Hóa đơn", "Item 2", "Item 3", "Item 4" }));
        importPanel.add(cbTarget);
        cbTarget.setBounds(20, 100, 360, 40);

        lbTarget.setText("Chọn đối tượng muốn nhập:");
        importPanel.add(lbTarget);
        lbTarget.setBounds(20, 80, 170, 18);

        lbTitleImport.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lbTitleImport.setForeground(new Color(72, 139, 255));
        lbTitleImport.setText("Nhập danh sách");
        importPanel.add(lbTitleImport);
        lbTitleImport.setBounds(120, 20, 250, 25);

        add(importPanel);
        importPanel.setBounds(20, 20, 400, 480);

        exportPanel.setBackground(new Color(255, 255, 255));
        exportPanel.setBorder(new LineBorder(new Color(1, 114, 58), 2, true));
        exportPanel.setLayout(null);

        lbTitleExport.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lbTitleExport.setForeground(new Color(1, 114, 58));
        lbTitleExport.setText("Xuất danh sách");
        exportPanel.add(lbTitleExport);
        lbTitleExport.setBounds(195, 20, 250, 25);

        Image tickImage = new ImageIcon("bin/images/components/check.png").getImage().getScaledInstance(40,40,Image.SCALE_SMOOTH);
        lbTickHD.setIcon(new ImageIcon(tickImage));
        exportPanel.add(lbTickHD);
        lbTickHD.setBounds(235, 68, 40, 40);

        exportHDPanel.setBackground(new Color(51, 196, 129));
        exportHDPanel.setForeground(new Color(255, 255, 255));
        exportHDPanel.setLayout(null);

        Image IconHD = new ImageIcon("bin/images/components/HD.png").getImage().getScaledInstance(40,40,Image.SCALE_SMOOTH);
        lbIconHD.setIcon(new ImageIcon(IconHD));
        exportHDPanel.add(lbIconHD);
        lbIconHD.setBounds(20, 50, 40, 40);

        lbNameHD.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lbNameHD.setForeground(new Color(255, 255, 255));
        lbNameHD.setText("Hóa đơn");
        exportHDPanel.add(lbNameHD);
        lbNameHD.setBounds(80, 20, 130, 30);

        lbSubHD.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lbSubHD.setForeground(new Color(255, 255, 255));
        lbSubHD.setText("Kèm theo DS CT HĐ ");
        exportHDPanel.add(lbSubHD);
        lbSubHD.setBounds(80, 80, 140, 18);

        lbQuantityHD.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lbQuantityHD.setForeground(new Color(255, 255, 255));
        lbQuantityHD.setText("Số lượng: 10");
        exportHDPanel.add(lbQuantityHD);
        lbQuantityHD.setBounds(80, 60, 140, 16);

        exportPanel.add(exportHDPanel);
        exportHDPanel.setBounds(30, 90, 230, 125);

        btnImport2.setBackground(new Color(1, 114, 58));
        btnImport2.setFont(new Font("Segoe UI", Font.BOLD, 18));
        btnImport2.setForeground(new Color(255, 255, 255));
        btnImport2.setText("Xuất");
        btnImport2.setActionCommand("Duyệt");
        exportPanel.add(btnImport2);
        btnImport2.setBounds(20, 720, 500, 40);

        lbTickPN.setIcon(new ImageIcon(tickImage));
        exportPanel.add(lbTickPN);
        lbTickPN.setBounds(235, 218, 40, 40);

        exportPNPanel.setBackground(new Color(51, 196, 129));
        exportPNPanel.setForeground(new Color(255, 255, 255));
        exportPNPanel.setLayout(null);

        Image IconPN = new ImageIcon("bin/images/components/PN.png").getImage().getScaledInstance(40,40,Image.SCALE_SMOOTH);
        jLabel8.setIcon(new ImageIcon(IconPN));
        exportPNPanel.add(jLabel8);
        jLabel8.setBounds(20, 50, 40, 40);

        jLabel9.setFont(new Font("Segoe UI", Font.BOLD, 24));
        jLabel9.setForeground(new Color(255, 255, 255));
        jLabel9.setText("Phiếu nhập");
        exportPNPanel.add(jLabel9);
        jLabel9.setBounds(80, 20, 130, 30);

        jLabel11.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        jLabel11.setForeground(new Color(255, 255, 255));
        jLabel11.setText("Kèm theo DS CT HĐ ");
        exportPNPanel.add(jLabel11);
        jLabel11.setBounds(80, 80, 140, 18);

        jLabel12.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        jLabel12.setForeground(new Color(255, 255, 255));
        jLabel12.setText("Số lượng: 10");
        exportPNPanel.add(jLabel12);
        jLabel12.setBounds(80, 60, 140, 16);

        exportPanel.add(exportPNPanel);
        exportPNPanel.setBounds(30, 240, 230, 125);

        lbTickKH.setIcon(new ImageIcon(tickImage));
        exportPanel.add(lbTickKH);
        lbTickKH.setBounds(235, 368, 40, 40);

        exportKHPanel.setBackground(new Color(51, 196, 129));
        exportKHPanel.setForeground(new Color(255, 255, 255));
        exportKHPanel.setLayout(null);

        Image IconKH = new ImageIcon("bin/images/components/KH.png").getImage().getScaledInstance(40,40,Image.SCALE_SMOOTH);
        jLabel14.setIcon(new ImageIcon(IconKH));
        exportKHPanel.add(jLabel14);
        jLabel14.setBounds(20, 50, 40, 40);

        jLabel15.setFont(new Font("Segoe UI", Font.BOLD, 24));
        jLabel15.setForeground(new Color(255, 255, 255));
        jLabel15.setText("Khách hàng");
        exportKHPanel.add(jLabel15);
        jLabel15.setBounds(80, 20, 150, 30);

        jLabel16.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        jLabel16.setForeground(new Color(255, 255, 255));
        jLabel16.setText("Kèm theo DS CT HĐ ");
        exportKHPanel.add(jLabel16);
        jLabel16.setBounds(80, 80, 140, 18);

        jLabel17.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        jLabel17.setForeground(new Color(255, 255, 255));
        jLabel17.setText("Số lượng: 10");
        exportKHPanel.add(jLabel17);
        jLabel17.setBounds(80, 60, 140, 16);

        exportPanel.add(exportKHPanel);
        exportKHPanel.setBounds(30, 390, 230, 125);

        lbTickKM.setIcon(new ImageIcon(tickImage));
        exportPanel.add(lbTickKM);
        lbTickKM.setBounds(235, 518, 40, 40);

        exportKMPanel.setBackground(new Color(51, 196, 129));
        exportKMPanel.setForeground(new Color(255, 255, 255));
        exportKMPanel.setLayout(null);

        Image IconKM = new ImageIcon("bin/images/components/KM.png").getImage().getScaledInstance(40,40,Image.SCALE_SMOOTH);
        jLabel19.setIcon(new ImageIcon(IconKM));
        exportKMPanel.add(jLabel19);
        jLabel19.setBounds(20, 50, 40, 40);

        jLabel20.setFont(new Font("Segoe UI", Font.BOLD, 24));
        jLabel20.setForeground(new Color(255, 255, 255));
        jLabel20.setText("Khuyến mãi");
        exportKMPanel.add(jLabel20);
        jLabel20.setBounds(80, 20, 150, 30);

        jLabel21.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        jLabel21.setForeground(new Color(255, 255, 255));
        jLabel21.setText("Kèm theo DS CT HĐ ");
        exportKMPanel.add(jLabel21);
        jLabel21.setBounds(80, 80, 140, 18);

        jLabel22.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        jLabel22.setForeground(new Color(255, 255, 255));
        jLabel22.setText("Số lượng: 10");
        exportKMPanel.add(jLabel22);
        jLabel22.setBounds(80, 60, 140, 16);

        exportPanel.add(exportKMPanel);
        exportKMPanel.setBounds(30, 540, 230, 125);

        jLabel28.setIcon(new ImageIcon(tickImage));
        exportPanel.add(jLabel28);
        jLabel28.setBounds(485, 518, 40, 40);

        exportHDPanel4.setBackground(new Color(51, 196, 129));
        exportHDPanel4.setForeground(new Color(255, 255, 255));
        exportHDPanel4.setLayout(null);

        Image IconTK = new ImageIcon("bin/images/components/TK.png").getImage().getScaledInstance(40,40,Image.SCALE_SMOOTH);
        jLabel24.setIcon(new ImageIcon(IconTK));
        exportHDPanel4.add(jLabel24);
        jLabel24.setBounds(20, 50, 40, 40);

        jLabel25.setFont(new Font("Segoe UI", Font.BOLD, 24));
        jLabel25.setForeground(new Color(255, 255, 255));
        jLabel25.setText("Thống kê");
        exportHDPanel4.add(jLabel25);
        jLabel25.setBounds(80, 20, 130, 30);

        jLabel26.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        jLabel26.setForeground(new Color(255, 255, 255));
        jLabel26.setText("Kèm theo DS CT HĐ ");
        exportHDPanel4.add(jLabel26);
        jLabel26.setBounds(80, 80, 140, 18);

        jLabel27.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        jLabel27.setForeground(new Color(255, 255, 255));
        jLabel27.setText("Số lượng: 10");
        exportHDPanel4.add(jLabel27);
        jLabel27.setBounds(80, 60, 140, 16);

        exportPanel.add(exportHDPanel4);
        exportHDPanel4.setBounds(280, 540, 230, 125);

        lbTickNV.setIcon(new ImageIcon(tickImage));
        exportPanel.add(lbTickNV);
        lbTickNV.setBounds(485, 368, 40, 40);

        exportNVPanel.setBackground(new Color(51, 196, 129));
        exportNVPanel.setForeground(new Color(255, 255, 255));
        exportNVPanel.setLayout(null);

        Image IconNV = new ImageIcon("bin/images/components/NV.png").getImage().getScaledInstance(40,40,Image.SCALE_SMOOTH);
        jLabel29.setIcon(new ImageIcon(IconNV));
        exportNVPanel.add(jLabel29);
        jLabel29.setBounds(20, 50, 40, 40);

        jLabel30.setFont(new Font("Segoe UI", Font.BOLD, 24));
        jLabel30.setForeground(new Color(255, 255, 255));
        jLabel30.setText("Nhân Sự");
        exportNVPanel.add(jLabel30);
        jLabel30.setBounds(80, 20, 150, 30);

        jLabel31.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        jLabel31.setForeground(new Color(255, 255, 255));
        jLabel31.setText("Kèm theo DS CT HĐ ");
        exportNVPanel.add(jLabel31);
        jLabel31.setBounds(80, 80, 140, 18);

        jLabel32.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        jLabel32.setForeground(new Color(255, 255, 255));
        jLabel32.setText("Số lượng: 10");
        exportNVPanel.add(jLabel32);
        jLabel32.setBounds(80, 60, 140, 16);

        exportPanel.add(exportNVPanel);
        exportNVPanel.setBounds(280, 390, 230, 125);

        lbTickSP.setIcon(new ImageIcon(tickImage));
        exportPanel.add(lbTickSP);
        lbTickSP.setBounds(485, 218, 40, 40);

        exportSPPanel.setBackground(new Color(51, 196, 129));
        exportSPPanel.setForeground(new Color(255, 255, 255));
        exportSPPanel.setLayout(null);

        Image IconSP = new ImageIcon("bin/images/components/SP.png").getImage().getScaledInstance(40,40,Image.SCALE_SMOOTH);
        jLabel34.setIcon(new ImageIcon(IconSP));
        exportSPPanel.add(jLabel34);
        jLabel34.setBounds(20, 50, 40, 40);

        jLabel35.setFont(new Font("Segoe UI", Font.BOLD, 24));
        jLabel35.setForeground(new Color(255, 255, 255));
        jLabel35.setText("Sản phẩm");
        exportSPPanel.add(jLabel35);
        jLabel35.setBounds(80, 20, 150, 30);

        jLabel36.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        jLabel36.setForeground(new Color(255, 255, 255));
        jLabel36.setText("Kèm theo DS CT HĐ ");
        exportSPPanel.add(jLabel36);
        jLabel36.setBounds(80, 80, 140, 18);

        jLabel37.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        jLabel37.setForeground(new Color(255, 255, 255));
        jLabel37.setText("Số lượng: 10");
        exportSPPanel.add(jLabel37);
        jLabel37.setBounds(80, 60, 140, 16);

        exportPanel.add(exportSPPanel);
        exportSPPanel.setBounds(280, 240, 230, 125);

        lbTickNCC.setIcon(new ImageIcon(tickImage));
        exportPanel.add(lbTickNCC);
        lbTickNCC.setBounds(485, 68, 40, 40);

        exportNCCPanel.setBackground(new Color(51, 196, 129));
        exportNCCPanel.setForeground(new Color(255, 255, 255));
        exportNCCPanel.setLayout(null);

        Image IconNCC = new ImageIcon("bin/images/components/NCC.png").getImage().getScaledInstance(40,40,Image.SCALE_SMOOTH);
        jLabel39.setIcon(new ImageIcon(IconNCC));
        exportNCCPanel.add(jLabel39);
        jLabel39.setBounds(20, 50, 40, 40);

        jLabel40.setFont(new Font("Segoe UI", Font.BOLD, 24));
        jLabel40.setForeground(new Color(255, 255, 255));
        jLabel40.setText("Nguồn cung");
        exportNCCPanel.add(jLabel40);
        jLabel40.setBounds(80, 20, 150, 30);

        jLabel41.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        jLabel41.setForeground(new Color(255, 255, 255));
        jLabel41.setText("Kèm theo DS CT HĐ ");
        exportNCCPanel.add(jLabel41);
        jLabel41.setBounds(80, 80, 140, 18);

        jLabel42.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        jLabel42.setForeground(new Color(255, 255, 255));
        jLabel42.setText("Số lượng: 10");
        exportNCCPanel.add(jLabel42);
        jLabel42.setBounds(80, 60, 140, 16);

        exportPanel.add(exportNCCPanel);
        exportNCCPanel.setBounds(280, 90, 230, 125);

        add(exportPanel);
        exportPanel.setBounds(440, 20, 540, 780);

        mainPanel.setBackground(new Color(255, 255, 255));
        mainPanel.setBorder(new LineBorder(new Color(244, 15, 2), 2, true));
        mainPanel.setLayout(null);

        lbTitlePDF.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lbTitlePDF.setForeground(new Color(244, 15, 2));
        lbTitlePDF.setText("Báo cáo PDF");
        mainPanel.add(lbTitlePDF);
        lbTitlePDF.setBounds(125, 20, 160, 25);

        btnExportPDF.setBackground(new Color(244, 15, 2));
        btnExportPDF.setFont(new Font("Segoe UI", Font.BOLD, 18));
        btnExportPDF.setForeground(new Color(255, 255, 255));
        btnExportPDF.setText("Xuất");
        btnExportPDF.setActionCommand("Duyệt");
        mainPanel.add(btnExportPDF);
        btnExportPDF.setBounds(20, 220, 360, 40);

        txtDetailPDF.setEditable(false);
        txtDetailPDF.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        txtDetailPDF.setRequestFocusEnabled(false);
        mainPanel.add(txtDetailPDF);
        txtDetailPDF.setBounds(20, 90, 360, 40);

        btnSelectHDPDF.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        btnSelectHDPDF.setText("Chọn hóa đơn");
        btnSelectHDPDF.setBorder(new LineBorder(new Color(244, 15, 2), 1, true));
        mainPanel.add(btnSelectHDPDF);
        btnSelectHDPDF.setBounds(210, 140, 170, 40);

        btnSelectPNPDF.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        btnSelectPNPDF.setText("Chọn phiếu nhập");
        btnSelectPNPDF.setBorder(new LineBorder(new Color(244, 15, 2), 1, true));
        mainPanel.add(btnSelectPNPDF);
        btnSelectPNPDF.setBounds(20, 140, 170, 40);

        lbCTExportPDF.setText("Chi tiết xuất:");
        mainPanel.add(lbCTExportPDF);
        lbCTExportPDF.setBounds(20, 70, 100, 18);

        add(mainPanel);
        mainPanel.setBounds(20, 520, 400, 280);
    }

}

package com.itblee.gui.Form;

import com.itblee.Provider;
import com.itblee.dto.*;
import com.itblee.gui.FrameSelect;
import com.itblee.gui.common.MyColor;
import com.itblee.gui.components.ChooserJDialog;
import com.itblee.mapper.*;
import com.itblee.mapper.search.HoaDonSearchMapper;
import com.itblee.mapper.search.PhieuNhapSearchMapper;
import com.itblee.service.*;
import com.itblee.util.FileUtil;
import com.itblee.util.StringUtils;
import net.iharder.dnd.FileDrop;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class FormExcel extends JPanel {
    private File importExcelFile;
    private boolean selectedHD = false;
    private boolean selectedNCC = false;
    private boolean selectedPN = false;
    private boolean selectedSP = false;
    private boolean selectedKH = false;
    private boolean selectedNS = false;
    private boolean selectedKM = false;
    private boolean selectedTK = false;

    public FormExcel() {
        initComponents();
    }
    
    private void initComponents() {
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
        lbDragDrop3.setFont(lbDragDrop3.getFont().deriveFont(Font.BOLD).deriveFont(18f));
        lbDragDrop3.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lbDragDrop3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                onClickBrowser();
            }
        });
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
        btnImport.addActionListener(e -> onClickBtnImport());
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

        cbTarget.setModel(new DefaultComboBoxModel<>(
                new String[] { "Hóa đơn", "Chi tiết hóa đơn", "Sản phẩm", "Loại sản phẩm", "Phiếu nhập",
                        "Chi tiết phiếu nhập", "Nhà cung cấp", "Khuyến mãi", "Chi tiết khuyến mãi", "Khách hàng",
                        "Nhân viên", "Tài khoản", "Phân quyền", "Chi tiết phân quyền",  }));
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

        lbTickHD.setIcon(new ImageIcon(tickImage));
        lbTickHD.setVisible(false);
        exportPanel.add(lbTickHD);
        lbTickHD.setBounds(235, 68, 40, 40);

        exportHDPanel.setBackground(new Color(51, 196, 129));
        exportHDPanel.setForeground(new Color(255, 255, 255));
        exportHDPanel.setLayout(null);
        exportHDPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        exportHDPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setSelectedHD(!isSelectedHD());
            }
        });

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
        lbQuantityHD.setText("Số lượng: " + hoaDonService.getTotalCount());
        exportHDPanel.add(lbQuantityHD);
        lbQuantityHD.setBounds(80, 60, 140, 16);

        exportPanel.add(exportHDPanel);
        exportHDPanel.setBounds(30, 90, 230, 125);

        btnExportExcel.setBackground(new Color(1, 114, 58));
        btnExportExcel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        btnExportExcel.setForeground(new Color(255, 255, 255));
        btnExportExcel.setText("Xuất");
        btnExportExcel.setActionCommand("Duyệt");
        btnExportExcel.addActionListener(e -> onClickBtnExportExcel());
        exportPanel.add(btnExportExcel);
        btnExportExcel.setBounds(20, 720, 500, 40);

        lbTickPN.setIcon(new ImageIcon(tickImage));
        lbTickPN.setVisible(false);
        exportPanel.add(lbTickPN);
        lbTickPN.setBounds(235, 218, 40, 40);

        exportPNPanel.setBackground(new Color(51, 196, 129));
        exportPNPanel.setForeground(new Color(255, 255, 255));
        exportPNPanel.setLayout(null);
        exportPNPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        exportPNPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setSelectedPN(!isSelectedPN());
            }
        });

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
        jLabel11.setText("Kèm theo DS CT PN ");
        exportPNPanel.add(jLabel11);
        jLabel11.setBounds(80, 80, 140, 18);

        jLabel12.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        jLabel12.setForeground(new Color(255, 255, 255));
        jLabel12.setText("Số lượng: " + phieuNhapService.getTotalCount());
        exportPNPanel.add(jLabel12);
        jLabel12.setBounds(80, 60, 140, 16);

        exportPanel.add(exportPNPanel);
        exportPNPanel.setBounds(30, 240, 230, 125);

        lbTickKH.setIcon(new ImageIcon(tickImage));
        lbTickKH.setVisible(false);
        exportPanel.add(lbTickKH);
        lbTickKH.setBounds(235, 368, 40, 40);

        exportKHPanel.setBackground(new Color(51, 196, 129));
        exportKHPanel.setForeground(new Color(255, 255, 255));
        exportKHPanel.setLayout(null);
        exportKHPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        exportKHPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setSelectedKH(!isSelectedKH());
            }
        });

        Image IconKH = new ImageIcon("bin/images/components/KH.png").getImage().getScaledInstance(40,40,Image.SCALE_SMOOTH);
        jLabel14.setIcon(new ImageIcon(IconKH));
        exportKHPanel.add(jLabel14);
        jLabel14.setBounds(20, 50, 40, 40);

        jLabel15.setFont(new Font("Segoe UI", Font.BOLD, 24));
        jLabel15.setForeground(new Color(255, 255, 255));
        jLabel15.setText("Khách hàng");
        exportKHPanel.add(jLabel15);
        jLabel15.setBounds(80, 20, 150, 30);

        /*jLabel16.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        jLabel16.setForeground(new Color(255, 255, 255));
        jLabel16.setText("Kèm theo DS CT HĐ ");
        exportKHPanel.add(jLabel16);
        jLabel16.setBounds(80, 80, 140, 18);*/

        jLabel17.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        jLabel17.setForeground(new Color(255, 255, 255));
        jLabel17.setText("Số lượng: " + khachHangService.getTotalCount());
        exportKHPanel.add(jLabel17);
        jLabel17.setBounds(80, 60, 140, 16);

        exportPanel.add(exportKHPanel);
        exportKHPanel.setBounds(30, 390, 230, 125);

        lbTickKM.setIcon(new ImageIcon(tickImage));
        lbTickKM.setVisible(false);
        exportPanel.add(lbTickKM);
        lbTickKM.setBounds(235, 518, 40, 40);

        exportKMPanel.setBackground(new Color(51, 196, 129));
        exportKMPanel.setForeground(new Color(255, 255, 255));
        exportKMPanel.setLayout(null);
        exportKMPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        exportKMPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setSelectedKM(!isSelectedKM());
            }
        });

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
        jLabel21.setText("Kèm theo DS CT KM ");
        exportKMPanel.add(jLabel21);
        jLabel21.setBounds(80, 80, 140, 18);

        jLabel22.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        jLabel22.setForeground(new Color(255, 255, 255));
        jLabel22.setText("Số lượng: " + khachHangService.getTotalCount());
        exportKMPanel.add(jLabel22);
        jLabel22.setBounds(80, 60, 140, 16);

        exportPanel.add(exportKMPanel);
        exportKMPanel.setBounds(30, 540, 230, 125);

        /*jLabel28.setIcon(new ImageIcon(tickImage));
        exportPanel.add(jLabel28);
        jLabel28.setBounds(485, 518, 40, 40);*/

        exportTKPanel.setBackground(new Color(51, 196, 129));
        exportTKPanel.setForeground(new Color(255, 255, 255));
        exportTKPanel.setLayout(null);

        Image IconTK = new ImageIcon("bin/images/components/TK.png").getImage().getScaledInstance(40,40,Image.SCALE_SMOOTH);
        jLabel24.setIcon(new ImageIcon(IconTK));
        exportTKPanel.add(jLabel24);
        jLabel24.setBounds(20, 50, 40, 40);

        jLabel25.setFont(new Font("Segoe UI", Font.BOLD, 24));
        jLabel25.setForeground(new Color(255, 255, 255));
        jLabel25.setText("Thống kê");
        exportTKPanel.add(jLabel25);
        jLabel25.setBounds(80, 20, 130, 30);

        /*jLabel26.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        jLabel26.setForeground(new Color(255, 255, 255));
        jLabel26.setText("Kèm theo DS CT HĐ ");
        exportTKPanel.add(jLabel26);
        jLabel26.setBounds(80, 80, 140, 18);*/

        jLabel27.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        jLabel27.setForeground(new Color(255, 255, 255));
        jLabel27.setText("Số lượng: 0");
        exportTKPanel.add(jLabel27);
        jLabel27.setBounds(80, 60, 140, 16);

        exportPanel.add(exportTKPanel);
        exportTKPanel.setBounds(280, 540, 230, 125);

        lbTickNV.setIcon(new ImageIcon(tickImage));
        lbTickNV.setVisible(false);
        exportPanel.add(lbTickNV);
        lbTickNV.setBounds(485, 368, 40, 40);

        exportNVPanel.setBackground(new Color(51, 196, 129));
        exportNVPanel.setForeground(new Color(255, 255, 255));
        exportNVPanel.setLayout(null);
        exportNVPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        exportNVPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setSelectedNS(!isSelectedNS());
            }
        });

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
        jLabel31.setText("Kèm theo DS NV,TK,PQ ");
        exportNVPanel.add(jLabel31);
        jLabel31.setBounds(80, 80, 140, 18);

        jLabel32.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        jLabel32.setForeground(new Color(255, 255, 255));
        jLabel32.setText("Số lượng: " + nhanVienService.getTotalCount());
        exportNVPanel.add(jLabel32);
        jLabel32.setBounds(80, 60, 140, 16);

        exportPanel.add(exportNVPanel);
        exportNVPanel.setBounds(280, 390, 230, 125);

        lbTickSP.setIcon(new ImageIcon(tickImage));
        lbTickSP.setVisible(false);
        exportPanel.add(lbTickSP);
        lbTickSP.setBounds(485, 218, 40, 40);

        exportSPPanel.setBackground(new Color(51, 196, 129));
        exportSPPanel.setForeground(new Color(255, 255, 255));
        exportSPPanel.setLayout(null);
        exportSPPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        exportSPPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setSelectedSP(!isSelectedSP());
            }
        });

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
        jLabel36.setText("Kèm theo DS Loai SP ");
        exportSPPanel.add(jLabel36);
        jLabel36.setBounds(80, 80, 140, 18);

        jLabel37.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        jLabel37.setForeground(new Color(255, 255, 255));
        jLabel37.setText("Số lượng: " + sanPhamService.getTotalCount());
        exportSPPanel.add(jLabel37);
        jLabel37.setBounds(80, 60, 140, 16);

        exportPanel.add(exportSPPanel);
        exportSPPanel.setBounds(280, 240, 230, 125);

        lbTickNCC.setIcon(new ImageIcon(tickImage));
        lbTickNCC.setVisible(false);
        exportPanel.add(lbTickNCC);
        lbTickNCC.setBounds(485, 68, 40, 40);

        exportNCCPanel.setBackground(new Color(51, 196, 129));
        exportNCCPanel.setForeground(new Color(255, 255, 255));
        exportNCCPanel.setLayout(null);
        exportNCCPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        exportNCCPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setSelectedNCC(!isSelectedNCC());
            }
        });

        Image IconNCC = new ImageIcon("bin/images/components/NCC.png").getImage().getScaledInstance(40,40,Image.SCALE_SMOOTH);
        jLabel39.setIcon(new ImageIcon(IconNCC));
        exportNCCPanel.add(jLabel39);
        jLabel39.setBounds(20, 50, 40, 40);

        jLabel40.setFont(new Font("Segoe UI", Font.BOLD, 24));
        jLabel40.setForeground(new Color(255, 255, 255));
        jLabel40.setText("Nguồn cung");
        exportNCCPanel.add(jLabel40);
        jLabel40.setBounds(80, 20, 150, 30);

        /*jLabel41.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        jLabel41.setForeground(new Color(255, 255, 255));
        jLabel41.setText("Kèm theo DS CT HĐ ");
        exportNCCPanel.add(jLabel41);
        jLabel41.setBounds(80, 80, 140, 18);*/

        jLabel42.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        jLabel42.setForeground(new Color(255, 255, 255));
        jLabel42.setText("Số lượng: " + nhaCungCapService.getTotalCount());
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
        btnExportPDF.addActionListener(e -> onClickBtnExportPDF());
        mainPanel.add(btnExportPDF);
        btnExportPDF.setBounds(20, 220, 360, 40);

        txtDetailPDF.setEnabled(false);
        txtDetailPDF.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        txtDetailPDF.setRequestFocusEnabled(false);
        mainPanel.add(txtDetailPDF);
        txtDetailPDF.setBounds(20, 90, 360, 40);

        btnSelectHDPDF.setBackground(Color.white);
        btnSelectHDPDF.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnSelectHDPDF.setText("Chọn hóa đơn");
        btnSelectHDPDF.setBorder(new LineBorder(new Color(244, 15, 2), 1, true));
        btnSelectHDPDF.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnSelectHDPDF.addActionListener(e -> onClickBtnSelectHDPDF());
        mainPanel.add(btnSelectHDPDF);
        btnSelectHDPDF.setBounds(210, 140, 170, 40);

        btnSelectPNPDF.setBackground(Color.white);
        btnSelectPNPDF.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnSelectPNPDF.setText("Chọn phiếu nhập");
        btnSelectPNPDF.setBorder(new LineBorder(new Color(244, 15, 2), 1, true));
        btnSelectPNPDF.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnSelectPNPDF.addActionListener(e -> onClickBtnSelectPNPDF());
        mainPanel.add(btnSelectPNPDF);
        btnSelectPNPDF.setBounds(20, 140, 170, 40);

        lbCTExportPDF.setText("Chi tiết xuất:");
        mainPanel.add(lbCTExportPDF);
        lbCTExportPDF.setBounds(20, 70, 100, 18);

        add(mainPanel);
        mainPanel.setBounds(20, 520, 400, 280);
    }

    private final JPanel dragDropPanel = new JPanel();
    FileDrop drop = new FileDrop(dragDropPanel, new FileDrop.Listener() {
        public void  filesDropped(java.io.File[] files ) {
            File file = files[0];
            if (file != null && file.isFile()) {
                lbFileName.setText(file.getName());
                animatedProgressUpload();
                importExcelFile = file;
            }
        }
    });

    private void onClickBrowser() {
        File file = ChooserJDialog.showFileChooser("export");
        if (file != null && file.isFile()) {
            lbFileName.setText(file.getName());
            animatedProgressUpload();
            importExcelFile = file;
        }
    }

    private void onClickBtnImport() {
        try {
            String path = importExcelFile.getAbsolutePath();
            String targetName = String.valueOf(cbTarget.getSelectedItem());

            switch (targetName) {
                case "Hóa đơn":
                    List<HoaDon> listHD = FileUtil.importExcel(path, hoaDonMapper);
                    for (HoaDon dto: listHD) {
                        if (hoaDonService.isExist(dto))
                            hoaDonService.update(dto);
                        else hoaDonService.save(dto);
                    }
                    break;

                case "Chi tiết hóa đơn":
                    List<CT_HoaDon> listCTHD = FileUtil.importExcel(path, ctHoaDonMapper);
                    for (CT_HoaDon dto: listCTHD) {
                        if (ctHoaDonService.isExist(dto))
                            ctHoaDonService.update(dto);
                        else ctHoaDonService.save(dto);
                    }
                    break;

                case "Sản phẩm":
                    List<SanPham> listSP = FileUtil.importExcel(path, sanPhamMapper);
                    for (SanPham dto: listSP) {
                        if (sanPhamService.isExist(dto))
                            sanPhamService.update(dto);
                        else sanPhamService.save(dto);
                    }
                    break;

                case "Loại sản phẩm":
                    List<LoaiSP> listLSP = FileUtil.importExcel(path, loaiSPMapper);
                    for (LoaiSP dto: listLSP) {
                        if (loaiSPService.isExist(dto))
                            loaiSPService.update(dto);
                        else loaiSPService.save(dto);
                    }
                    break;

                case "Phiếu nhập":
                    List<PhieuNhap> listPN = FileUtil.importExcel(path, phieuNhapMapper);
                    for (PhieuNhap dto: listPN) {
                        if (phieuNhapService.isExist(dto))
                            phieuNhapService.update(dto);
                        else phieuNhapService.save(dto);
                    }
                    break;

                case "Chi tiết phiếu nhập":
                    List<CT_PhieuNhap> listCTPN = FileUtil.importExcel(path, ctPhieuNhapMapper);
                    for (CT_PhieuNhap dto: listCTPN) {
                        if (ctPhieuNhapService.isExist(dto))
                            ctPhieuNhapService.update(dto);
                        else ctPhieuNhapService.save(dto);
                    }
                    break;

                case "Nhà cung cấp":
                    List<NhaCungCap> listNCC = FileUtil.importExcel(path, nhaCungCapMapper);
                    for (NhaCungCap dto: listNCC) {
                        if (nhaCungCapService.isExist(dto))
                            nhaCungCapService.update(dto);
                        else nhaCungCapService.save(dto);
                    }
                    break;

                case "Khuyến mãi":
                    List<KhuyenMai> listKM = FileUtil.importExcel(path, khuyenMaiMapper);
                    for (KhuyenMai dto: listKM) {
                        if (khuyenMaiService.isExist(dto))
                            khuyenMaiService.update(dto);
                        else khuyenMaiService.save(dto);
                    }
                    break;

                case "Chi tiết khuyến mãi":
                    List<CT_KhuyenMai> listCTKM = FileUtil.importExcel(path, ctKhuyenMaiMapper);
                    for (CT_KhuyenMai dto: listCTKM) {
                        if (ctKhuyenMaiService.isExist(dto))
                            ctKhuyenMaiService.update(dto);
                        else ctKhuyenMaiService.save(dto);
                    }
                    break;

                case "Khách hàng":
                    List<KhachHang> listKH = FileUtil.importExcel(path, khachHangMapper);
                    for (KhachHang dto: listKH) {
                        if (khachHangService.isExist(dto))
                            khachHangService.update(dto);
                        else khachHangService.save(dto);
                    }
                    break;

                case "Nhân viên":
                    List<NhanVien> listNV = FileUtil.importExcel(path, nhanVienMapper);
                    for (NhanVien dto: listNV) {
                        if (nhanVienService.isExist(dto))
                            nhanVienService.update(dto);
                        else nhanVienService.save(dto);
                    }
                    break;

                case "Tài khoản":
                    List<TaiKhoan> listTK = FileUtil.importExcel(path, taiKhoanMapper);
                    for (TaiKhoan dto: listTK) {
                        if (taiKhoanService.isExist(dto))
                            taiKhoanService.update(dto);
                        else taiKhoanService.save(dto);
                    }
                    break;

                case "Phân quyền":
                    List<PhanQuyen> listPQ = FileUtil.importExcel(path, phanQuyenMapper);
                    for (PhanQuyen dto: listPQ) {
                        if (phanQuyenService.isExist(dto))
                            phanQuyenService.update(dto);
                        else phanQuyenService.save(dto);
                    }
                    break;

                case "Chi tiết phân quyền":
                    List<CT_PhanQuyen> listCTPQ = FileUtil.importExcel(path, ctPhanQuyenMapper);
                    for (CT_PhanQuyen dto: listCTPQ) {
                        if (ctPhanQuyenService.isExist(dto))
                            ctPhanQuyenService.update(dto);
                        else ctPhanQuyenService.save(dto);
                    }
                    break;

                default:
                    throw new Exception("Đối tượng nhập không đúng.");
            }
            JOptionPane.showMessageDialog(FormExcel.this, "Nhập file thành công.\n", "Hoàn tất", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(FormExcel.this, "Nhập file thất bại.\n" + e.getMessage(), "Không hợp lệ", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void onClickBtnExportExcel() {
        String exportPath;
        try {
            if (isSelectedHD()) {
                exportPath = "export/HoaDon.xls";
                FileUtil.exportExcel(exportPath, hoaDonService.findAll(), hoaDonMapper);
                exportPath = "export/CT_HoaDon.xls";
                FileUtil.exportExcel(exportPath, ctHoaDonService.findAll(), ctHoaDonMapper);
            }

            if (isSelectedSP()) {
                exportPath = "export/SanPham.xls";
                FileUtil.exportExcel(exportPath, sanPhamService.findAll(), sanPhamMapper);
                exportPath = "export/LoaiSP.xls";
                FileUtil.exportExcel(exportPath, loaiSPService.findAll(), loaiSPMapper);
            }

            if (isSelectedPN()) {
                exportPath = "export/PhieuNhap.xls";
                FileUtil.exportExcel(exportPath, phieuNhapService.findAll(), phieuNhapMapper);
                exportPath = "export/CT_PhieuNhap.xls";
                FileUtil.exportExcel(exportPath, ctPhieuNhapService.findAll(), ctPhieuNhapMapper);
            }

            if (isSelectedNCC()) {
                exportPath = "export/NhaCungCap.xls";
                FileUtil.exportExcel(exportPath, nhaCungCapService.findAll(), nhaCungCapMapper);
            }

            if (isSelectedNS()) {
                exportPath = "export/NhanVien.xls";
                FileUtil.exportExcel(exportPath, nhanVienService.findAll(), nhanVienMapper);
                exportPath = "export/TaiKhoan.xls";
                FileUtil.exportExcel(exportPath, taiKhoanService.findAll(), taiKhoanMapper);
                exportPath = "export/PhanQuyen.xls";
                FileUtil.exportExcel(exportPath, phanQuyenService.findAll(), phanQuyenMapper);
                exportPath = "export/CT_PhanQuyen.xls";
                FileUtil.exportExcel(exportPath, ctPhanQuyenService.findAll(), ctPhanQuyenMapper);
            }

            if (isSelectedKM()) {
                exportPath = "export/KhuyenMai.xls";
                FileUtil.exportExcel(exportPath, khuyenMaiService.findAll(), khuyenMaiMapper);
                exportPath = "export/CT_KhuyenMai.xls";
                FileUtil.exportExcel(exportPath, ctKhuyenMaiService.findAll(), ctKhuyenMaiMapper);
            }

            if (isSelectedKH()) {
                exportPath = "export/KhachHang.xls";
                FileUtil.exportExcel(exportPath, khachHangService.findAll(), khachHangMapper);
            }

            JOptionPane.showMessageDialog(FormExcel.this, "Xuất file thành công.\n", "Hoàn tất", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(FormExcel.this, "Xuất file thất bại.\n" + e.getMessage(), "Không hợp lệ", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void onClickBtnExportPDF() {
        int id;
        try {
            id = Integer.parseInt(StringUtils.removeLetter(txtDetailPDF.getText()));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(FormExcel.this, "Đối tượng xuất PDF không đúng.", "Không hợp lệ", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (txtDetailPDF.getText().startsWith("HD")) {
            HoaDon dto = hoaDonService.findByID(id);
            if (dto == null)
                return;
            List<CT_HoaDon> list = ctHoaDonService.findByMaHD(dto.getMaHD());
            if (list == null)
                return;
            String path = "export/HD" + dto.getMaHD() + "-" + new SimpleDateFormat("dd-MM-yyyy").format(dto.getNgayLap()) + ".pdf";
            if (baoCaoService.export(path, dto, new ArrayList<>(list))) {
                JOptionPane.showMessageDialog(FormExcel.this, "Xuất PDF hóa đơn thành công.", "Không hợp lệ", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(FormExcel.this, "Xuất PDF hóa đơn thất bại.", "Không hợp lệ", JOptionPane.ERROR_MESSAGE);
            }
        }
        else if (txtDetailPDF.getText().startsWith("PN")) {
            PhieuNhap dto = phieuNhapService.findByID(id);
            if (dto == null)
                return;
            List<CT_PhieuNhap> list = ctPhieuNhapService.findByMaPN(dto.getMaPN());
            if (list == null)
                return;
            String path = "export/PN" + dto.getMaPN() + "-" + new SimpleDateFormat("dd-MM-yyyy").format(dto.getNgayTao()) + ".pdf";
            if (baoCaoService.export(path, dto, new ArrayList<>(list))) {
                JOptionPane.showMessageDialog(FormExcel.this, "Xuất PDF phiếu nhập thành công.", "Không hợp lệ", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(FormExcel.this, "Xuất PDF phiếu nhập thất bại.", "Không hợp lệ", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void onClickBtnSelectHDPDF() {
        try {
            JFrame frame = new FrameSelect("hóa đơn", txtDetailPDF, hoaDonSearchMapper, FormHoaDon.class, FormExcel.class);
            EventQueue.invokeLater(() -> frame.setVisible(true));
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(FormExcel.this, e.getMessage(), "Không hợp lệ", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void onClickBtnSelectPNPDF() {
        try {
            JFrame frame = new FrameSelect("phiếu nhập", txtDetailPDF, phieuNhapSearchMapper, FormPhieuNhap.class, FormExcel.class);
            EventQueue.invokeLater(() -> frame.setVisible(true));
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(FormExcel.this, e.getMessage(), "Không hợp lệ", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void animatedProgressUpload() {
        new Thread(() -> {
            int currentPercent = 0;
            while (currentPercent <= 100) {
                lbPercentUpload.setText(currentPercent + "%");
                progressUpload.setSize(10 + (3 * currentPercent), progressUpload.getHeight());
                try {
                    Thread.sleep(20);
                } catch (InterruptedException ignored) {}
                currentPercent += 2;
            }
        }).start();
    }

    private boolean isSelectedHD() {
        return selectedHD;
    }

    private void setSelectedHD(boolean selectedHD) {
        this.selectedHD = selectedHD;
        if (selectedHD) {
            exportHDPanel.setBorder(new MatteBorder(5,5,5,5, MyColor.LIGHTGREEN));
            lbTickHD.setVisible(true);
        } else {
            exportHDPanel.setBorder(null);
            lbTickHD.setVisible(false);
        }
    }

    private boolean isSelectedNCC() {
        return selectedNCC;
    }

    private void setSelectedNCC(boolean selectedNCC) {
        this.selectedNCC = selectedNCC;
        if (selectedNCC) {
            exportNCCPanel.setBorder(new MatteBorder(5,5,5,5, MyColor.LIGHTGREEN));
            lbTickNCC.setVisible(true);
        } else {
            exportNCCPanel.setBorder(null);
            lbTickNCC.setVisible(false);
        }
    }

    private boolean isSelectedPN() {
        return selectedPN;
    }

    private void setSelectedPN(boolean selectedPN) {
        this.selectedPN = selectedPN;
        if (selectedPN) {
            exportPNPanel.setBorder(new MatteBorder(5,5,5,5, MyColor.LIGHTGREEN));
            lbTickPN.setVisible(true);
        } else {
            exportPNPanel.setBorder(null);
            lbTickPN.setVisible(false);
        }
    }

    private boolean isSelectedSP() {
        return selectedSP;
    }

    private void setSelectedSP(boolean selectedSP) {
        this.selectedSP = selectedSP;
        if (selectedSP) {
            exportSPPanel.setBorder(new MatteBorder(5,5,5,5, MyColor.LIGHTGREEN));
            lbTickSP.setVisible(true);
        } else {
            exportSPPanel.setBorder(null);
            lbTickSP.setVisible(false);
        }
    }

    private boolean isSelectedKH() {
        return selectedKH;
    }

    private void setSelectedKH(boolean selectedKH) {
        this.selectedKH = selectedKH;
        if (selectedKH) {
            exportKHPanel.setBorder(new MatteBorder(5,5,5,5, MyColor.LIGHTGREEN));
            lbTickKH.setVisible(true);
        } else {
            exportKHPanel.setBorder(null);
            lbTickKH.setVisible(false);
        }
    }

    private boolean isSelectedNS() {
        return selectedNS;
    }

    private void setSelectedNS(boolean selectedNS) {
        this.selectedNS = selectedNS;
        if (selectedNS) {
            exportNVPanel.setBorder(new MatteBorder(5,5,5,5, MyColor.LIGHTGREEN));
            lbTickNV.setVisible(true);
        } else {
            exportNVPanel.setBorder(null);
            lbTickNV.setVisible(false);
        }
    }

    private boolean isSelectedKM() {
        return selectedKM;
    }

    private void setSelectedKM(boolean selectedKM) {
        this.selectedKM = selectedKM;
        if (selectedKM) {
            exportKMPanel.setBorder(new MatteBorder(5,5,5,5, MyColor.LIGHTGREEN));
            lbTickKM.setVisible(true);
        } else {
            exportKMPanel.setBorder(null);
            lbTickKM.setVisible(false);
        }
    }

    private boolean isSelectedTK() {
        return selectedTK;
    }

    private void setSelectedTK(boolean selectedTK) {
        this.selectedTK = selectedTK;
    }

    private final Image tickImage = new ImageIcon("bin/images/components/check.png").getImage().getScaledInstance(40,40,Image.SCALE_SMOOTH);
    private final JPanel importPanel = new JPanel();
    private final JLabel lbDragDrop = new JLabel();
    private final JLabel lbDragDrop2 = new JLabel();
    private final JLabel lbDragDrop3 = new JLabel();
    private final JButton btnImport = new JButton();
    private final JLabel icFile = new JLabel();
    private final JLabel lbPercentUpload = new JLabel();
    private final JPanel progressUpload = new JPanel();
    private final JPanel progressUploadHolder = new JPanel();
    private final JLabel lbFileName = new JLabel();
    private final JComboBox<String> cbTarget = new JComboBox<>();
    private final JLabel lbTarget = new JLabel();
    private final JLabel lbTitleImport = new JLabel();
    private final JPanel exportPanel = new JPanel();
    private final JLabel lbTitleExport = new JLabel();
    private final JLabel lbTickHD = new JLabel();
    private final JPanel exportHDPanel = new JPanel();
    private final JLabel lbIconHD = new JLabel();
    private final JLabel lbNameHD = new JLabel();
    private final JLabel lbSubHD = new JLabel();
    private final JLabel lbQuantityHD = new JLabel();
    private final JButton btnExportExcel = new JButton();
    private final JLabel lbTickPN = new JLabel();
    private final JPanel exportPNPanel = new JPanel();
    private final JLabel jLabel8 = new JLabel();
    private final JLabel jLabel9 = new JLabel();
    private final JLabel jLabel11 = new JLabel();
    private final JLabel jLabel12 = new JLabel();
    private final JLabel lbTickKH = new JLabel();
    private final JPanel exportKHPanel = new JPanel();
    private final JLabel jLabel14 = new JLabel();
    private final JLabel jLabel15 = new JLabel();
    private final JLabel jLabel17 = new JLabel();
    private final JLabel lbTickKM = new JLabel();
    private final JPanel exportKMPanel = new JPanel();
    private final JLabel jLabel19 = new JLabel();
    private final JLabel jLabel20 = new JLabel();
    private final JLabel jLabel21 = new JLabel();
    private final JLabel jLabel22 = new JLabel();
    private final JPanel exportTKPanel = new JPanel();
    private final JLabel jLabel24 = new JLabel();
    private final JLabel jLabel25 = new JLabel();
    private final JLabel jLabel27 = new JLabel();
    private final JLabel lbTickNV = new JLabel();
    private final JPanel exportNVPanel = new JPanel();
    private final JLabel jLabel29 = new JLabel();
    private final JLabel jLabel30 = new JLabel();
    private final JLabel jLabel31 = new JLabel();
    private final JLabel jLabel32 = new JLabel();
    private final JLabel lbTickSP = new JLabel();
    private final JPanel exportSPPanel = new JPanel();
    private final JLabel jLabel34 = new JLabel();
    private final JLabel jLabel35 = new JLabel();
    private final JLabel jLabel36 = new JLabel();
    private final JLabel jLabel37 = new JLabel();
    private final JLabel lbTickNCC = new JLabel();
    private final JPanel exportNCCPanel = new JPanel();
    private final JLabel jLabel39 = new JLabel();
    private final JLabel jLabel40 = new JLabel();
    private final JLabel jLabel41 = new JLabel();
    private final JLabel jLabel42 = new JLabel();
    private final JPanel mainPanel = new JPanel();
    private final JLabel lbTitlePDF = new JLabel();
    private final JButton btnExportPDF = new JButton();
    private final JTextField txtDetailPDF = new JTextField();
    private final JButton btnSelectHDPDF = new JButton();
    private final JButton btnSelectPNPDF = new JButton();
    private final JLabel lbCTExportPDF = new JLabel();

    private final BaoCaoService baoCaoService = Provider.get(BaoCaoService.class);

    private final HoaDonService hoaDonService = Provider.get(HoaDonService.class);

    private final CT_HoaDonService ctHoaDonService = Provider.get(CT_HoaDonService.class);

    private final SanPhamService sanPhamService = Provider.get(SanPhamService.class);

    private final LoaiSPService loaiSPService = Provider.get(LoaiSPService.class);

    private final PhieuNhapService phieuNhapService = Provider.get(PhieuNhapService.class);

    private final CT_PhieuNhapService ctPhieuNhapService = Provider.get(CT_PhieuNhapService.class);

    private final NhaCungCapService nhaCungCapService = Provider.get(NhaCungCapService.class);

    private final KhuyenMaiService khuyenMaiService = Provider.get(KhuyenMaiService.class);

    private final CT_KhuyenMaiService ctKhuyenMaiService = Provider.get(CT_KhuyenMaiService.class);

    private final KhachHangService khachHangService = Provider.get(KhachHangService.class);

    private final NhanVienService nhanVienService = Provider.get(NhanVienService.class);

    private final TaiKhoanService taiKhoanService = Provider.get(TaiKhoanService.class);

    private final PhanQuyenService phanQuyenService = Provider.get(PhanQuyenService.class);

    private final CT_PhanQuyenService ctPhanQuyenService = Provider.get(CT_PhanQuyenService.class);

    private final HoaDonMapper hoaDonMapper = Provider.get(HoaDonMapper.class);

    private final CT_HoaDonMapper ctHoaDonMapper = Provider.get(CT_HoaDonMapper.class);

    private final SanPhamMapper sanPhamMapper = Provider.get(SanPhamMapper.class);

    private final LoaiSPMapper loaiSPMapper = Provider.get(LoaiSPMapper.class);

    private final PhieuNhapMapper phieuNhapMapper = Provider.get(PhieuNhapMapper.class);

    private final CT_PhieuNhapMapper ctPhieuNhapMapper = Provider.get(CT_PhieuNhapMapper.class);

    private final NhaCungCapMapper nhaCungCapMapper = Provider.get(NhaCungCapMapper.class);

    private final KhuyenMaiMapper khuyenMaiMapper = Provider.get(KhuyenMaiMapper.class);

    private final CT_KhuyenMaiMapper ctKhuyenMaiMapper = Provider.get(CT_KhuyenMaiMapper.class);

    private final KhachHangMapper khachHangMapper = Provider.get(KhachHangMapper.class);

    private final NhanVienMapper nhanVienMapper = Provider.get(NhanVienMapper.class);

    private final TaiKhoanMapper taiKhoanMapper = Provider.get(TaiKhoanMapper.class);

    private final PhanQuyenMapper phanQuyenMapper = Provider.get(PhanQuyenMapper.class);

    private final CT_PhanQuyenMapper ctPhanQuyenMapper = Provider.get(CT_PhanQuyenMapper.class);

    private final HoaDonSearchMapper hoaDonSearchMapper = Provider.get(HoaDonSearchMapper.class);

    private final PhieuNhapSearchMapper phieuNhapSearchMapper = Provider.get(PhieuNhapSearchMapper.class);
}

package com.itblee.service.impl;

import com.google.auto.service.AutoService;
import com.itblee.Provider;
import com.itblee.dto.*;
import com.itblee.service.*;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@AutoService(BaoCaoService.class)
public class BaoCaoServiceImpl implements BaoCaoService {

    @Override
    public boolean export(String path, BaseEntity reportInstance, List<BaseEntityDetail> list) {
        SanPhamService sanPhamService = Provider.get(SanPhamService.class);
        NhanVienService nhanVienService = Provider.get(NhanVienService.class);
        KhachHangService khachHangService = Provider.get(KhachHangService.class);
        NhaCungCapService nhaCungCapService = Provider.get(NhaCungCapService.class);
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);

        String title;
        String cooperatorLabel;
        String cooperatorDetail;
        String employeeDetail;
        String timestamp;
        Integer totalPrice;
        Integer salePrice = null;
        Integer payment = null;

        List<SanPham> productList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            SanPham dto = sanPhamService.findByID(list.get(i).getForeignID());
            if (dto != null) {
                productList.add(dto);
            } else {
                list.remove(list.get(i));
            }
        }

        if (reportInstance instanceof HoaDon) {
            HoaDon hoaDon = (HoaDon) reportInstance;
            title = "Hóa ĐƠn";
            NhanVien nhanVien = nhanVienService.findByID(hoaDon.getMaNV());
            employeeDetail = nhanVien.getHoTen() + " - SĐT: " + nhanVien.getSdt();
            KhachHang khachHang = khachHangService.findByID(hoaDon.getMaKH());
            cooperatorLabel = "Khách hàng";
            cooperatorDetail = khachHang.getHoTen() + " - SĐT: " + khachHang.getSdt();
            timestamp = String.valueOf(hoaDon.getNgayLap());
            totalPrice = hoaDon.getTongTien();
            salePrice = hoaDon.getTienKhuyenMai();
            payment = hoaDon.getTienThanhToan();
        }
        else if (reportInstance instanceof PhieuNhap) {
            PhieuNhap phieuNhap = (PhieuNhap) reportInstance;
            title = "Phiếu Nhập";
            NhanVien nhanVien = nhanVienService.findByID(phieuNhap.getMaNV());
            employeeDetail = nhanVien.getHoTen() + " - SĐT: " + nhanVien.getSdt();
            NhaCungCap nhaCungCap = nhaCungCapService.findByID(phieuNhap.getMaNCC());
            cooperatorLabel = "Nhà cung cấp";
            cooperatorDetail = nhaCungCap.getTenNCC() + " - SĐT: " + nhaCungCap.getSdt();
            timestamp = String.valueOf(phieuNhap.getNgayTao());
            totalPrice = phieuNhap.getTongTien();
        }
        else
            return false;


        PDDocument document = new PDDocument();
        PDPage newPage = new PDPage();
        document.addPage(newPage);
        PDPage getPage = document.getPage(0);
        try {
            InputStream fontInput = new FileInputStream("bin/font/segoeui.ttf");
            InputStream fontBoldInput = new FileInputStream("bin/font/seguibl.ttf");
            PDFont font = PDType0Font.load(document, fontInput);
            PDFont fontBold = PDType0Font.load(document, fontBoldInput);
            PDPageContentStream content = new PDPageContentStream(document, getPage);

            content.beginText();
            content.setFont(fontBold, 20);
            content.newLineAtOffset(200, 750);
            content.showText("THE CROSSING COFFEE");
            content.endText();

            content.beginText();
            content.setFont(fontBold, 20);
            content.newLineAtOffset(270, 690);
            content.showText(title);
            content.endText();

            content.beginText();
            content.setFont(font, 14);
            content.setLeading(20f);
            content.newLineAtOffset(60, 610);
            content.showText(cooperatorLabel + ": " + cooperatorDetail);
            content.newLine();
            content.showText("Nhân viên: " + employeeDetail);
            content.newLine();
            content.showText("Ngày lập: " + timestamp);
            content.newLine();
            content.endText();

            content.beginText();
            content.setFont(fontBold, 14);
            content.newLineAtOffset(80, 540);
            content.showText("Mã");
            content.endText();

            content.beginText();
            content.setFont(fontBold, 14);
            content.newLineAtOffset(200, 540);
            content.showText("Sản phẩm");
            content.endText();

            content.beginText();
            content.setFont(fontBold, 14);
            content.newLineAtOffset(310, 540);
            content.showText("Số lượng");
            content.endText();

            content.beginText();
            content.setFont(fontBold, 14);
            content.newLineAtOffset(410, 540);
            content.showText("Đơn giá");
            content.endText();

            content.beginText();
            content.setFont(fontBold, 14);
            content.newLineAtOffset(510, 540);
            content.showText("Tổng");
            content.endText();


            content.beginText();
            content.setFont(font, 12);
            content.setLeading(20f);
            content.newLineAtOffset(80, 520);
            for (BaseEntityDetail entity:list) {
                content.showText(String.valueOf(entity.getForeignID()));
                content.newLine();
            }
            content.endText();

            content.beginText();
            content.setFont(font, 12);
            content.setLeading(20f);
            content.newLineAtOffset(200, 520);
            for (int i = 0; i < list.size(); i++) {
                content.showText(productList.get(i).getTenSP());
                content.newLine();
            }
            content.endText();

            content.beginText();
            content.setFont(font, 12);
            content.setLeading(20f);
            content.newLineAtOffset(310, 520);
            for (int i = 0; i < list.size(); i++) {
                content.showText(list.get(i).getSoLuong() + " " + productList.get(i).getDonVi());
                content.newLine();
            }
            content.endText();

            content.beginText();
            content.setFont(font, 12);
            content.setLeading(20f);
            content.newLineAtOffset(410, 520);
            for (int i = 0; i < list.size(); i++) {
                content.showText(currencyVN.format(list.get(i).getDonGia()) + "/" + productList.get(i).getDonVi());
                content.newLine();
            }
            content.endText();

            content.beginText();
            content.setFont(font, 12);
            content.setLeading(20f);
            content.newLineAtOffset(510, 520);
            for (BaseEntityDetail entity : list) {
                content.showText(currencyVN.format((long) entity.getDonGia() * entity.getSoLuong()));
                content.newLine();
            }
            content.endText();

            content.beginText();
            content.setFont(fontBold, 14);
            content.newLineAtOffset(310, (500-(20*list.size())));
            content.showText("Tổng tiền: " + currencyVN.format(totalPrice) + " đồng");
            content.newLine();
            if (salePrice != null) {
                content.showText("Giảm giá: " + currencyVN.format(salePrice) + " đồng");
                content.newLine();
            }
            if (payment != null) {
                content.showText("Thành tiền: " + currencyVN.format(payment) + " đồng");
                content.endText();
            }

            content.close();
            document.save(path);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

}

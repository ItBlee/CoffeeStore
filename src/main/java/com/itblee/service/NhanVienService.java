package com.itblee.service;

import com.itblee.dto.NhanVien;

import java.sql.Date;
import java.util.List;

public interface NhanVienService extends GenericService<NhanVien> {
    NhanVien findByTaiKhoan(Integer maTK);
    NhanVien findByTaiKhoan(String tenTK);
    List<NhanVien> findByHoTen(String hoTen);
    List<NhanVien> findByNgaySinh(Date tuNgay, Date denNgay);
    List<NhanVien> findBySDT(String sdt);
    List<NhanVien> findByEmail(String email);
    List<NhanVien> findByGioiTinh(Integer gioiTinh);
    List<NhanVien> findByLuong(Integer luong);
    List<NhanVien> findByTinhTrang(Integer TinhTrang);

}

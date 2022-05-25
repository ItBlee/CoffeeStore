-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th5 25, 2022 lúc 05:14 PM
-- Phiên bản máy phục vụ: 10.4.11-MariaDB
-- Phiên bản PHP: 7.4.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `coffeestore`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `ct_hoadon`
--

CREATE TABLE `ct_hoadon` (
  `MaCTHD` int(10) UNSIGNED NOT NULL,
  `MaHD` int(10) UNSIGNED NOT NULL,
  `MaSP` int(10) UNSIGNED NOT NULL,
  `SoLuong` int(10) NOT NULL,
  `DonGia` int(10) NOT NULL,
  `TienKhuyenMai` int(10) NOT NULL,
  `ThanhTien` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `ct_khuyenmai`
--

CREATE TABLE `ct_khuyenmai` (
  `MaCTKM` int(11) NOT NULL,
  `MaKM` int(10) UNSIGNED NOT NULL,
  `MaSP` int(10) UNSIGNED NOT NULL,
  `GiamGia` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `ct_phanquyen`
--

CREATE TABLE `ct_phanquyen` (
  `MaCTPQ` int(11) NOT NULL,
  `QuyenDoc` tinyint(1) NOT NULL DEFAULT 0,
  `QuyenTao` tinyint(1) NOT NULL DEFAULT 0,
  `QuyenSua` tinyint(1) NOT NULL DEFAULT 0,
  `QuyenXoa` tinyint(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `ct_phanquyen`
--

INSERT INTO `ct_phanquyen` (`MaCTPQ`, `QuyenDoc`, `QuyenTao`, `QuyenSua`, `QuyenXoa`) VALUES
(1, 1, 1, 1, 1),
(2, 1, 1, 1, 1),
(3, 1, 1, 1, 1),
(4, 1, 1, 1, 1),
(5, 1, 1, 1, 1),
(6, 1, 1, 1, 1),
(7, 1, 1, 1, 1),
(8, 1, 1, 1, 1),
(9, 1, 1, 1, 1),
(10, 1, 1, 1, 1),
(11, 1, 1, 1, 1),
(12, 1, 1, 1, 1),
(13, 1, 1, 1, 1),
(14, 1, 1, 1, 1),
(15, 0, 0, 0, 0),
(16, 0, 0, 0, 0),
(17, 0, 0, 0, 0),
(18, 0, 0, 0, 0),
(28, 1, 1, 1, 1),
(29, 1, 1, 1, 1),
(30, 1, 1, 1, 1),
(31, 0, 0, 0, 0),
(32, 1, 1, 1, 1),
(33, 1, 1, 1, 1),
(34, 0, 0, 0, 0),
(35, 0, 0, 0, 0),
(36, 0, 0, 0, 0),
(37, 1, 1, 1, 1),
(38, 1, 1, 1, 1),
(39, 1, 1, 1, 1),
(40, 1, 1, 1, 1),
(41, 0, 0, 0, 0),
(42, 0, 0, 0, 0),
(43, 0, 0, 0, 0),
(44, 0, 0, 0, 0),
(45, 0, 0, 0, 0),
(46, 0, 0, 0, 0),
(47, 0, 0, 0, 0),
(48, 0, 0, 0, 0),
(49, 0, 0, 0, 0),
(50, 0, 0, 0, 0),
(51, 0, 0, 0, 0),
(52, 1, 1, 1, 1),
(53, 1, 1, 1, 1),
(54, 1, 1, 1, 1),
(55, 1, 1, 1, 1),
(56, 1, 1, 1, 1),
(57, 0, 0, 0, 0),
(58, 0, 0, 0, 0),
(59, 1, 1, 1, 1),
(60, 1, 1, 1, 1),
(61, 1, 1, 1, 1),
(62, 0, 0, 0, 0),
(63, 0, 0, 0, 0);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `ct_phieunhap`
--

CREATE TABLE `ct_phieunhap` (
  `MaCTPN` int(11) NOT NULL,
  `MaPN` int(10) UNSIGNED NOT NULL,
  `MaSP` int(10) UNSIGNED NOT NULL,
  `SoLuong` int(10) NOT NULL,
  `DonGia` int(10) NOT NULL,
  `ThanhTien` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `hoadon`
--

CREATE TABLE `hoadon` (
  `MaHD` int(10) UNSIGNED NOT NULL,
  `MaKH` int(10) UNSIGNED NOT NULL,
  `MaNV` int(10) UNSIGNED NOT NULL,
  `NgayLap` timestamp NOT NULL DEFAULT current_timestamp(),
  `TongTien` int(20) NOT NULL,
  `TienKhuyenMai` int(20) NOT NULL,
  `TienThanhToan` int(20) NOT NULL,
  `TinhTrang` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `khachhang`
--

CREATE TABLE `khachhang` (
  `MaKH` int(10) UNSIGNED NOT NULL,
  `Ho` varchar(50) NOT NULL,
  `Ten` varchar(50) NOT NULL,
  `SDT` varchar(20) NOT NULL,
  `DiaChi` varchar(200) NOT NULL,
  `Email` varchar(100) NOT NULL,
  `TinhTrang` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `khuyenmai`
--

CREATE TABLE `khuyenmai` (
  `MaKM` int(10) UNSIGNED NOT NULL,
  `TieuDe` varchar(100) NOT NULL,
  `NoiDung` text NOT NULL,
  `NgayBD` timestamp NULL DEFAULT NULL,
  `NgayKT` timestamp NULL DEFAULT NULL,
  `TinhTrang` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `lichsu`
--

CREATE TABLE `lichsu` (
  `MaLS` int(11) NOT NULL,
  `TenDoiTuong` varchar(30) NOT NULL,
  `MaDoiTuong` int(10) NOT NULL,
  `ThoiGian` timestamp NOT NULL DEFAULT current_timestamp(),
  `NguoiThucHien` int(11) NOT NULL,
  `ThaoTac` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `lichsu`
--

INSERT INTO `lichsu` (`MaLS`, `TenDoiTuong`, `MaDoiTuong`, `ThoiGian`, `NguoiThucHien`, `ThaoTac`) VALUES
(1, 'PhanQuyen', 1, '2022-05-22 02:20:52', 1, 'Sửa'),
(2, 'PhanQuyen', 1, '2022-05-22 02:36:04', 1, 'Sửa'),
(3, 'PhanQuyen', 1, '2022-05-22 02:39:13', 1, 'Sửa'),
(4, 'PhanQuyen', 1, '2022-05-22 02:49:04', 1, 'Sửa'),
(5, 'PhanQuyen', 1, '2022-05-22 02:57:44', 1, 'Sửa'),
(6, 'PhanQuyen', 1, '2022-05-22 02:59:31', 1, 'Sửa'),
(7, 'PhanQuyen', 1, '2022-05-22 03:00:16', 1, 'Sửa'),
(8, 'PhanQuyen', 1, '2022-05-22 03:01:00', 1, 'Sửa'),
(9, 'PhanQuyen', 1, '2022-05-22 03:34:10', 1, 'Sửa'),
(10, 'PhanQuyen', 1, '2022-05-22 03:34:30', 1, 'Sửa'),
(11, 'PhanQuyen', 3, '2022-05-22 03:57:04', 1, 'Thêm'),
(12, 'PhanQuyen', 3, '2022-05-22 04:08:46', 1, 'Xóa'),
(13, 'PhanQuyen', 4, '2022-05-22 07:18:54', 1, 'Thêm'),
(14, 'PhanQuyen', 5, '2022-05-22 07:20:17', 1, 'Thêm'),
(15, 'PhanQuyen', 6, '2022-05-22 07:27:43', 1, 'Thêm'),
(16, 'PhanQuyen', 7, '2022-05-22 07:28:40', 1, 'Thêm'),
(17, 'TaiKhoan', 4, '2022-05-23 04:07:08', 1, 'Sửa'),
(18, 'TaiKhoan', 4, '2022-05-23 04:07:15', 1, 'Sửa'),
(19, 'TaiKhoan', 4, '2022-05-23 04:09:28', 1, 'Sửa'),
(20, 'TaiKhoan', 4, '2022-05-23 04:12:37', 1, 'Sửa'),
(21, 'TaiKhoan', 4, '2022-05-23 04:12:41', 1, 'Xóa'),
(22, 'TaiKhoan', 5, '2022-05-23 04:22:21', 1, 'Thêm'),
(23, 'TaiKhoan', 5, '2022-05-23 04:32:39', 1, 'Sửa'),
(24, 'TaiKhoan', 5, '2022-05-23 04:33:00', 1, 'Sửa'),
(25, 'NhanVien', 3, '2022-05-24 13:27:34', 1, 'Thêm'),
(26, 'NhanVien', 3, '2022-05-24 13:33:19', 1, 'Sửa'),
(27, 'NhanVien', 3, '2022-05-24 13:45:54', 1, 'Sửa'),
(28, 'NhanVien', 3, '2022-05-24 13:48:36', 1, 'Sửa'),
(29, 'NhanVien', 3, '2022-05-24 13:49:41', 1, 'Sửa'),
(30, 'NhanVien', 3, '2022-05-24 13:49:47', 1, 'Sửa'),
(31, 'NhanVien', 3, '2022-05-24 13:50:03', 1, 'Sửa'),
(32, 'NhanVien', 3, '2022-05-24 13:51:05', 1, 'Sửa'),
(33, 'NhanVien', 3, '2022-05-24 14:04:59', 1, 'Sửa'),
(34, 'NhanVien', 3, '2022-05-24 14:06:16', 1, 'Sửa'),
(35, 'NhanVien', 3, '2022-05-24 14:24:25', 1, 'Sửa'),
(36, 'NhanVien', 3, '2022-05-24 14:24:27', 1, 'Sửa'),
(37, 'NhanVien', 3, '2022-05-24 14:24:37', 1, 'Xóa'),
(38, 'NhanVien', 4, '2022-05-24 15:33:56', 1, 'Thêm'),
(39, 'NhanVien', 4, '2022-05-24 15:34:06', 1, 'Sửa'),
(40, 'NhaCungCap', 11, '2022-05-24 16:17:11', 1, 'Thêm'),
(41, 'NhaCungCap', 11, '2022-05-24 16:17:32', 1, 'Sửa'),
(42, 'NhaCungCap', 11, '2022-05-24 16:17:37', 1, 'Sửa'),
(43, 'NhaCungCap', 11, '2022-05-24 16:17:40', 1, 'Sửa'),
(44, 'NhaCungCap', 11, '2022-05-24 16:17:42', 1, 'Sửa'),
(45, 'TaiKhoan', 11, '2022-05-24 16:17:45', 1, 'Xóa'),
(46, 'NhaCungCap', 12, '2022-05-24 16:19:11', 1, 'Thêm'),
(47, 'NhaCungCap', 14, '2022-05-24 16:27:01', 1, 'Thêm'),
(48, 'NhaCungCap', 15, '2022-05-24 16:29:47', 1, 'Thêm');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `loaisp`
--

CREATE TABLE `loaisp` (
  `MaLoai` int(10) UNSIGNED NOT NULL,
  `TenLoai` varchar(100) NOT NULL,
  `MoTa` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `loaisp`
--

INSERT INTO `loaisp` (`MaLoai`, `TenLoai`, `MoTa`) VALUES
(1, 'CAFE', 'Các loại cà phê'),
(2, 'Nước Ngọt', 'Các loại nước ngọt'),
(3, 'Matcha', 'Matcha '),
(4, 'Cà Phê Phin', 'Cà Phê Phin'),
(5, 'Cà Phê Pha Máy', 'Cà Phê Pha Máy'),
(6, 'Cà Phê Nguyên Chất', 'Cà Phê Nguyên Chất'),
(7, 'Cà Phê Latte', 'Cà Phê Latte'),
(8, 'Cà Phê Nguyên Chất', 'Cà Phê Nguyên Chất'),
(9, 'Capuchino', 'Capuchino từ cà phê nguyên chất'),
(10, 'Macchiato', 'Macchiato có vị béo thơm đặc trưng của sữa quyện lẫn với vị ngọt nhẹ hoặc đôi chút hơi mặn');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `nhacungcap`
--

CREATE TABLE `nhacungcap` (
  `MaNCC` int(10) UNSIGNED NOT NULL,
  `TenNCC` varchar(200) NOT NULL,
  `SDT` varchar(20) NOT NULL,
  `DiaChi` varchar(200) NOT NULL,
  `SoTaiKhoan` varchar(20) NOT NULL,
  `TinhTrang` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `nhacungcap`
--

INSERT INTO `nhacungcap` (`MaNCC`, `TenNCC`, `SDT`, `DiaChi`, `SoTaiKhoan`, `TinhTrang`) VALUES
(1, 'Đại lý cà phê', '0961197985', 'ở đâu ai mà biết', '961197985', 1),
(2, 'Đại Lý Đường', '0945327185', '125 lý Thường Kiệt', '232435245', 1),
(3, 'Đại lý sữa', '0955333444', '157 Bàu Cát', '777744444', 1),
(4, 'Đại Lý Kem', '0955522233', '78 Chu Văn An', '44447777', 1),
(5, 'Đại Lý Nước', '0977744488', '85 Âu Cơ', '77844444', 1),
(6, 'Đại Lý Đá', '0999888777', '85 Âu Cơ Quá', '11112222', 1),
(7, 'Đại lý giấy in bill', '0922333444', '190 an dương vương', '60233322', 1),
(8, 'Đại Lý Cà Phê Hạt', '0976456876', '183 âu cơ', '999911299', 1),
(9, 'Đại Lý Bao Đựng ly', '0788866642', '452 Lê Hồng Phong', '60255544', 1),
(10, 'Đại Lý Hộp Nhựa', '0965111555', '335 bảy hiền', '60233355', 1),
(12, 'CocaCola', '0978923421', '213/16 Trần Xuân Soạn, Phường 7, Quận 5, HCM', '93712946293', 1),
(14, 'Pepsi', '0978923421', '213/16 Trần Xuân Soạn, Phường 7, Quận 5, HCM', '93712946293', 1),
(15, 'String', '0976523612', '175 An Dương Vương, P3, Q6, HCM', '31241245124', 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `nhanvien`
--

CREATE TABLE `nhanvien` (
  `MaNV` int(10) UNSIGNED NOT NULL,
  `MaTK` int(10) UNSIGNED DEFAULT NULL,
  `Ho` varchar(50) NOT NULL,
  `Ten` varchar(50) NOT NULL,
  `NgaySinh` date NOT NULL,
  `SDT` varchar(20) NOT NULL,
  `Email` varchar(100) NOT NULL,
  `GioiTinh` tinyint(1) NOT NULL,
  `Luong` int(20) UNSIGNED NOT NULL,
  `TinhTrang` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `nhanvien`
--

INSERT INTO `nhanvien` (`MaNV`, `MaTK`, `Ho`, `Ten`, `NgaySinh`, `SDT`, `Email`, `GioiTinh`, `Luong`, `TinhTrang`) VALUES
(1, 1, 'Sếp', 'Tổng', '2002-01-01', '0913111222', 'crossingcoffee@gmail.com', 1, 0, 1),
(4, 5, 'Trần Tuấn', 'Anh', '2002-05-21', '0397642331', 'trantuananh@gmail.com', 1, 11, 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `phanquyen`
--

CREATE TABLE `phanquyen` (
  `MaPQ` int(11) NOT NULL,
  `TenPQ` varchar(100) NOT NULL DEFAULT 'Nhân viên',
  `QuyenHD` int(11) DEFAULT NULL,
  `QuyenSP` int(11) DEFAULT NULL,
  `QuyenPN` int(11) DEFAULT NULL,
  `QuyenNCC` int(11) DEFAULT NULL,
  `QuyenKH` int(11) DEFAULT NULL,
  `QuyenKM` int(11) DEFAULT NULL,
  `QuyenTK` int(11) DEFAULT NULL,
  `QuyenExcel` int(11) DEFAULT NULL,
  `QuyenNV` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `phanquyen`
--

INSERT INTO `phanquyen` (`MaPQ`, `TenPQ`, `QuyenHD`, `QuyenSP`, `QuyenPN`, `QuyenNCC`, `QuyenKH`, `QuyenKM`, `QuyenTK`, `QuyenExcel`, `QuyenNV`) VALUES
(1, 'Quản trị viên', 1, 2, 3, 4, 5, 6, 7, 8, 9),
(2, 'Nhân viên', 10, 11, 12, 13, 14, 15, 16, 17, 18),
(4, 'Nhân viên bán hàng', 28, 29, 30, 31, 32, 33, 34, 35, 36),
(5, 'Thủ kho', 37, 38, 39, 40, 41, 42, 43, 44, 45),
(6, 'Quản lý nhân sự', 46, 47, 48, 49, 50, 51, 52, 53, 54),
(7, 'Quản lý chiến lược', 55, 56, 57, 58, 59, 60, 61, 62, 63);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `phieunhap`
--

CREATE TABLE `phieunhap` (
  `MaPN` int(10) UNSIGNED NOT NULL,
  `MaNCC` int(10) UNSIGNED NOT NULL,
  `MaNV` int(10) UNSIGNED NOT NULL,
  `NgayTao` timestamp NOT NULL DEFAULT current_timestamp(),
  `TongTien` int(20) NOT NULL,
  `TinhTrang` int(11) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `sanpham`
--

CREATE TABLE `sanpham` (
  `MaSP` int(10) UNSIGNED NOT NULL,
  `MaLoai` int(10) UNSIGNED NOT NULL,
  `MaNCC` int(10) UNSIGNED NOT NULL,
  `TenSP` varchar(100) NOT NULL,
  `MoTa` text NOT NULL,
  `HinhAnh` varchar(200) NOT NULL,
  `DonGia` int(20) UNSIGNED NOT NULL,
  `DonVi` varchar(50) NOT NULL,
  `SoLuong` int(10) UNSIGNED NOT NULL,
  `TinhTrang` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `taikhoan`
--

CREATE TABLE `taikhoan` (
  `MaTK` int(10) UNSIGNED NOT NULL,
  `TenDangNhap` varchar(20) NOT NULL,
  `MatKhauHash` varchar(200) NOT NULL,
  `MatKhauSalt` varchar(50) NOT NULL,
  `NgayTao` timestamp NOT NULL DEFAULT current_timestamp(),
  `NguoiTao` int(10) UNSIGNED NOT NULL,
  `MaPQ` int(10) NOT NULL,
  `TinhTrang` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `taikhoan`
--

INSERT INTO `taikhoan` (`MaTK`, `TenDangNhap`, `MatKhauHash`, `MatKhauSalt`, `NgayTao`, `NguoiTao`, `MaPQ`, `TinhTrang`) VALUES
(1, 'admin', 'd83c81ba59bb048750c30c9d3fd7abd07d27140ce6dfcf17244b944073438fce', 'cdd1a14c970e3c41', '2022-04-03 10:42:55', 1, 1, 1),
(5, 'testt', '595c42a1acecd90b16ae62de32897d7455a4ff17c40e396af406e5bd1130a3b1', '48505f476a7ea472', '2022-05-23 04:22:21', 1, 5, 1);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `ct_hoadon`
--
ALTER TABLE `ct_hoadon`
  ADD PRIMARY KEY (`MaCTHD`,`MaSP`),
  ADD KEY `FK_CTHD_HD` (`MaHD`),
  ADD KEY `FK_CTHD_SP` (`MaSP`);

--
-- Chỉ mục cho bảng `ct_khuyenmai`
--
ALTER TABLE `ct_khuyenmai`
  ADD PRIMARY KEY (`MaCTKM`,`MaSP`),
  ADD KEY `FK_CTKM_KM` (`MaKM`),
  ADD KEY `FK_CTKM_SP` (`MaSP`);

--
-- Chỉ mục cho bảng `ct_phanquyen`
--
ALTER TABLE `ct_phanquyen`
  ADD PRIMARY KEY (`MaCTPQ`);

--
-- Chỉ mục cho bảng `ct_phieunhap`
--
ALTER TABLE `ct_phieunhap`
  ADD PRIMARY KEY (`MaCTPN`),
  ADD KEY `FK_CTPN_PN` (`MaPN`),
  ADD KEY `FK_CTPN_SP` (`MaSP`);

--
-- Chỉ mục cho bảng `hoadon`
--
ALTER TABLE `hoadon`
  ADD PRIMARY KEY (`MaHD`),
  ADD KEY `FK_HD_NV` (`MaNV`),
  ADD KEY `FK_HD_KH` (`MaKH`);

--
-- Chỉ mục cho bảng `khachhang`
--
ALTER TABLE `khachhang`
  ADD PRIMARY KEY (`MaKH`);

--
-- Chỉ mục cho bảng `khuyenmai`
--
ALTER TABLE `khuyenmai`
  ADD PRIMARY KEY (`MaKM`),
  ADD UNIQUE KEY `UNIQUE_KM` (`TieuDe`);

--
-- Chỉ mục cho bảng `lichsu`
--
ALTER TABLE `lichsu`
  ADD PRIMARY KEY (`MaLS`);

--
-- Chỉ mục cho bảng `loaisp`
--
ALTER TABLE `loaisp`
  ADD PRIMARY KEY (`MaLoai`);

--
-- Chỉ mục cho bảng `nhacungcap`
--
ALTER TABLE `nhacungcap`
  ADD PRIMARY KEY (`MaNCC`,`TenNCC`),
  ADD UNIQUE KEY `UNIQUE_TenNCC` (`TenNCC`);

--
-- Chỉ mục cho bảng `nhanvien`
--
ALTER TABLE `nhanvien`
  ADD PRIMARY KEY (`MaNV`),
  ADD KEY `FK_NV_TK` (`MaTK`);

--
-- Chỉ mục cho bảng `phanquyen`
--
ALTER TABLE `phanquyen`
  ADD PRIMARY KEY (`MaPQ`,`TenPQ`),
  ADD KEY `FK_PQ_QuyenHD` (`QuyenHD`),
  ADD KEY `FK_PQ_QuyenSP` (`QuyenSP`),
  ADD KEY `FK_PQ_QuyenNCC` (`QuyenNCC`),
  ADD KEY `FK_PQ_QuyenPN` (`QuyenPN`),
  ADD KEY `FK_PQ_QuyenKH` (`QuyenKH`),
  ADD KEY `FK_PQ_QuyenKM` (`QuyenKM`),
  ADD KEY `FK_PQ_QuyenTK` (`QuyenTK`),
  ADD KEY `FK_PQ_QuyenExcel` (`QuyenExcel`),
  ADD KEY `FK_PQ_QuyenNV` (`QuyenNV`);

--
-- Chỉ mục cho bảng `phieunhap`
--
ALTER TABLE `phieunhap`
  ADD PRIMARY KEY (`MaPN`),
  ADD KEY `FK_PN_NCC` (`MaNCC`),
  ADD KEY `FK_PN_NV` (`MaNV`);

--
-- Chỉ mục cho bảng `sanpham`
--
ALTER TABLE `sanpham`
  ADD PRIMARY KEY (`MaSP`),
  ADD UNIQUE KEY `TenSP` (`TenSP`),
  ADD KEY `FK_SP_LOAISP` (`MaLoai`),
  ADD KEY `FK_SP_NCC` (`MaNCC`);

--
-- Chỉ mục cho bảng `taikhoan`
--
ALTER TABLE `taikhoan`
  ADD PRIMARY KEY (`MaTK`),
  ADD UNIQUE KEY `UNIQUE_TenDangNhap` (`TenDangNhap`) USING BTREE,
  ADD KEY `FK_TK_PQ` (`MaPQ`),
  ADD KEY `FK_NguoiTao_MaTK` (`NguoiTao`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `ct_hoadon`
--
ALTER TABLE `ct_hoadon`
  MODIFY `MaCTHD` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `ct_khuyenmai`
--
ALTER TABLE `ct_khuyenmai`
  MODIFY `MaCTKM` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `ct_phanquyen`
--
ALTER TABLE `ct_phanquyen`
  MODIFY `MaCTPQ` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=64;

--
-- AUTO_INCREMENT cho bảng `ct_phieunhap`
--
ALTER TABLE `ct_phieunhap`
  MODIFY `MaCTPN` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `hoadon`
--
ALTER TABLE `hoadon`
  MODIFY `MaHD` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `khachhang`
--
ALTER TABLE `khachhang`
  MODIFY `MaKH` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `khuyenmai`
--
ALTER TABLE `khuyenmai`
  MODIFY `MaKM` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `lichsu`
--
ALTER TABLE `lichsu`
  MODIFY `MaLS` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=49;

--
-- AUTO_INCREMENT cho bảng `loaisp`
--
ALTER TABLE `loaisp`
  MODIFY `MaLoai` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT cho bảng `nhacungcap`
--
ALTER TABLE `nhacungcap`
  MODIFY `MaNCC` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT cho bảng `nhanvien`
--
ALTER TABLE `nhanvien`
  MODIFY `MaNV` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT cho bảng `phanquyen`
--
ALTER TABLE `phanquyen`
  MODIFY `MaPQ` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT cho bảng `phieunhap`
--
ALTER TABLE `phieunhap`
  MODIFY `MaPN` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `sanpham`
--
ALTER TABLE `sanpham`
  MODIFY `MaSP` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `taikhoan`
--
ALTER TABLE `taikhoan`
  MODIFY `MaTK` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `ct_hoadon`
--
ALTER TABLE `ct_hoadon`
  ADD CONSTRAINT `FK_CTHD_HD` FOREIGN KEY (`MaHD`) REFERENCES `hoadon` (`MaHD`),
  ADD CONSTRAINT `FK_CTHD_SP` FOREIGN KEY (`MaSP`) REFERENCES `sanpham` (`MaSP`);

--
-- Các ràng buộc cho bảng `ct_khuyenmai`
--
ALTER TABLE `ct_khuyenmai`
  ADD CONSTRAINT `FK_CTKM_KM` FOREIGN KEY (`MaKM`) REFERENCES `khuyenmai` (`MaKM`),
  ADD CONSTRAINT `FK_CTKM_SP` FOREIGN KEY (`MaSP`) REFERENCES `sanpham` (`MaSP`);

--
-- Các ràng buộc cho bảng `ct_phieunhap`
--
ALTER TABLE `ct_phieunhap`
  ADD CONSTRAINT `FK_CTPN_PN` FOREIGN KEY (`MaPN`) REFERENCES `phieunhap` (`MaPN`),
  ADD CONSTRAINT `FK_CTPN_SP` FOREIGN KEY (`MaSP`) REFERENCES `sanpham` (`MaSP`);

--
-- Các ràng buộc cho bảng `hoadon`
--
ALTER TABLE `hoadon`
  ADD CONSTRAINT `FK_HD_KH` FOREIGN KEY (`MaKH`) REFERENCES `khachhang` (`MaKH`),
  ADD CONSTRAINT `FK_HD_NV` FOREIGN KEY (`MaNV`) REFERENCES `nhanvien` (`MaNV`);

--
-- Các ràng buộc cho bảng `nhanvien`
--
ALTER TABLE `nhanvien`
  ADD CONSTRAINT `FK_NV_TK` FOREIGN KEY (`MaTK`) REFERENCES `taikhoan` (`MaTK`);

--
-- Các ràng buộc cho bảng `phanquyen`
--
ALTER TABLE `phanquyen`
  ADD CONSTRAINT `FK_PQ_QuyenExcel` FOREIGN KEY (`QuyenExcel`) REFERENCES `ct_phanquyen` (`MaCTPQ`),
  ADD CONSTRAINT `FK_PQ_QuyenHD` FOREIGN KEY (`QuyenHD`) REFERENCES `ct_phanquyen` (`MaCTPQ`),
  ADD CONSTRAINT `FK_PQ_QuyenKH` FOREIGN KEY (`QuyenKH`) REFERENCES `ct_phanquyen` (`MaCTPQ`),
  ADD CONSTRAINT `FK_PQ_QuyenKM` FOREIGN KEY (`QuyenKM`) REFERENCES `ct_phanquyen` (`MaCTPQ`),
  ADD CONSTRAINT `FK_PQ_QuyenNCC` FOREIGN KEY (`QuyenNCC`) REFERENCES `ct_phanquyen` (`MaCTPQ`),
  ADD CONSTRAINT `FK_PQ_QuyenNV` FOREIGN KEY (`QuyenNV`) REFERENCES `ct_phanquyen` (`MaCTPQ`),
  ADD CONSTRAINT `FK_PQ_QuyenPN` FOREIGN KEY (`QuyenPN`) REFERENCES `ct_phanquyen` (`MaCTPQ`),
  ADD CONSTRAINT `FK_PQ_QuyenSP` FOREIGN KEY (`QuyenSP`) REFERENCES `ct_phanquyen` (`MaCTPQ`),
  ADD CONSTRAINT `FK_PQ_QuyenTK` FOREIGN KEY (`QuyenTK`) REFERENCES `ct_phanquyen` (`MaCTPQ`);

--
-- Các ràng buộc cho bảng `phieunhap`
--
ALTER TABLE `phieunhap`
  ADD CONSTRAINT `FK_PN_NCC` FOREIGN KEY (`MaNCC`) REFERENCES `nhacungcap` (`MaNCC`),
  ADD CONSTRAINT `FK_PN_NV` FOREIGN KEY (`MaNV`) REFERENCES `nhanvien` (`MaNV`);

--
-- Các ràng buộc cho bảng `sanpham`
--
ALTER TABLE `sanpham`
  ADD CONSTRAINT `FK_SP_LOAISP` FOREIGN KEY (`MaLoai`) REFERENCES `loaisp` (`MaLoai`),
  ADD CONSTRAINT `FK_SP_NCC` FOREIGN KEY (`MaNCC`) REFERENCES `nhacungcap` (`MaNCC`);

--
-- Các ràng buộc cho bảng `taikhoan`
--
ALTER TABLE `taikhoan`
  ADD CONSTRAINT `FK_NguoiTao_MaTK` FOREIGN KEY (`NguoiTao`) REFERENCES `nhanvien` (`MaNV`),
  ADD CONSTRAINT `FK_TK_PQ` FOREIGN KEY (`MaPQ`) REFERENCES `phanquyen` (`MaPQ`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

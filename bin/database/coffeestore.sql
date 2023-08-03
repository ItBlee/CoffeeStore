-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th5 27, 2022 lúc 07:31 PM
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `ct_hoadon`
--

INSERT INTO `ct_hoadon` (`MaCTHD`, `MaHD`, `MaSP`, `SoLuong`, `DonGia`, `TienKhuyenMai`, `ThanhTien`) VALUES
(2, 87, 1, 50, 40000, 50000, 1950000),
(3, 87, 4, 13, 35000, 50000, 405000),
(8, 60, 7, 500, 70000, 80000, 34920000),
(10, 60, 8, 100, 75000, 0, 7500000),
(11, 1, 1, 45, 40000, 50000, 1750000),
(12, 1, 5, 30, 55000, 80000, 1570000),
(13, 1, 6, 60, 60000, 0, 3600000),
(14, 2, 8, 353, 75000, 0, 26475000),
(15, 2, 5, 67, 55000, 80000, 3605000),
(16, 2, 2, 165, 45000, 100000, 7325000),
(17, 2, 6, 26, 60000, 0, 1560000),
(18, 3, 2, 125, 45000, 100000, 5525000),
(19, 35, 7, 50, 70000, 0, 3500000),
(20, 35, 4, 15, 35000, 50000, 475000),
(21, 35, 3, 65, 50000, 70000, 3180000),
(22, 69, 1, 1000, 40000, 50000, 39950000),
(23, 87, 8, 700, 75000, 0, 52500000),
(24, 69, 10, 600, 35000, 0, 21000000),
(25, 69, 2, 789, 45000, 100000, 35405000),
(26, 56, 7, 65, 70000, 0, 4550000),
(27, 56, 3, 23, 50000, 70000, 1080000),
(28, 41, 6, 51, 60000, 0, 3060000),
(29, 41, 3, 24, 50000, 70000, 1130000),
(30, 41, 7, 54, 70000, 0, 3780000),
(31, 88, 5, 50, 55000, 80000, 2670000);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `ct_khuyenmai`
--

CREATE TABLE `ct_khuyenmai` (
  `MaCTKM` int(11) NOT NULL,
  `MaKM` int(10) UNSIGNED NOT NULL,
  `MaSP` int(10) UNSIGNED NOT NULL,
  `GiamGia` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `ct_khuyenmai`
--

INSERT INTO `ct_khuyenmai` (`MaCTKM`, `MaKM`, `MaSP`, `GiamGia`) VALUES
(2, 100, 1, 50000),
(3, 201, 5, 80000),
(4, 203, 3, 70000),
(5, 393, 2, 100000),
(6, 402, 4, 50000),
(7, 404, 10, 25000);

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `ct_phieunhap`
--

INSERT INTO `ct_phieunhap` (`MaCTPN`, `MaPN`, `MaSP`, `SoLuong`, `DonGia`, `ThanhTien`) VALUES
(4, 204, 1, 100, 32000, 3200000),
(5, 101, 2, 14, 36000, 504000),
(6, 101, 6, 200, 48000, 9600000),
(7, 101, 4, 36, 28000, 1008000),
(8, 104, 5, 373, 44000, 16412000),
(9, 104, 9, 259, 44000, 11396000),
(10, 201, 3, 89, 40000, 3560000),
(11, 201, 5, 77, 44000, 3388000),
(12, 201, 1, 101, 32000, 3232000),
(13, 202, 7, 754, 56000, 42224000),
(14, 202, 8, 1237, 60000, 74220000),
(15, 203, 2, 136, 36000, 4896000),
(16, 203, 4, 278, 28000, 7784000),
(17, 203, 8, 159, 60000, 9540000),
(18, 204, 10, 657, 28000, 18396000),
(19, 208, 3, 156, 40000, 6240000),
(20, 208, 6, 12, 48000, 576000),
(21, 208, 9, 134, 44000, 5896000),
(22, 207, 2, 112, 36000, 4032000),
(23, 207, 1, 35, 32000, 1120000),
(24, 207, 7, 145, 56000, 8120000),
(25, 207, 3, 573, 40000, 22920000),
(26, 205, 1, 894, 32000, 28608000),
(27, 206, 6, 473, 48000, 22704000),
(28, 206, 2, 942, 36000, 33912000);

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `hoadon`
--

INSERT INTO `hoadon` (`MaHD`, `MaKH`, `MaNV`, `NgayLap`, `TongTien`, `TienKhuyenMai`, `TienThanhToan`, `TinhTrang`) VALUES
(1, 101, 4, '2022-05-13 01:03:49', 6920000, 130000, 6790000, 1),
(2, 102, 5, '2022-05-13 02:46:35', 38965000, 180000, 38785000, 1),
(3, 103, 5, '2022-05-14 08:02:50', 5525000, 100000, 5425000, 1),
(35, 304, 5, '2022-05-17 03:35:08', 7155000, 120000, 7035000, 1),
(41, 345, 4, '2022-05-18 07:12:10', 7970000, 70000, 7900000, 1),
(56, 406, 6, '2022-05-24 03:50:36', 5630000, 70000, 5560000, 1),
(60, 446, 6, '2022-05-27 09:31:34', 42420000, 80000, 42340000, 1),
(69, 484, 5, '2022-04-01 06:01:20', 96355000, 150000, 96205000, 1),
(87, 513, 5, '2022-04-04 04:25:43', 54855000, 100000, 54755000, 1),
(88, 101, 1, '2022-05-27 16:47:10', 2670000, 80000, 2590000, 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `khachhang`
--

CREATE TABLE `khachhang` (
  `MaKH` int(10) UNSIGNED NOT NULL,
  `Ho` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `Ten` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `sdt` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  `DiaChi` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL,
  `Email` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `TinhTrang` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `khachhang`
--

INSERT INTO `khachhang` (`MaKH`, `Ho`, `Ten`, `sdt`, `DiaChi`, `Email`, `TinhTrang`) VALUES
(101, 'Lê', 'Anh', '0930212039', 'Thành phố Đà Lạt, Lâm Đồng', 'leanh30@gmail.com', 1),
(102, 'Nguyễn', 'Tú', '0909123412', 'Quận Thủ Đức, TP.HCM', 'tunguyen123@gmail.com', 1),
(103, 'Lý', 'Thảo', '0928488428', 'Huyện Nam Đông, Thừa Thiên Huế', 'thaothaoly35@gmail.com', 1),
(304, 'Trần', 'Nhân', '0838848828', 'Bà Rịa Vũng Tàu', 'nhannhan11@gmail.com', 1),
(345, 'Lê', 'Vân', '0938887377', 'Thành phố Hạ Long, Quảng Ninh', 'thuyvan222@gmail.com', 1),
(406, 'Hồ', 'Nam', '0913888883', 'Thành phố Rạch Giá, Kiên Giang', 'hohaonam99@gmail.com', 1),
(446, 'Nguyễn', 'Nhật', '0848372945', 'Huyện Bến Cát, Bình Dương', 'nhatnm18@gmail.com', 1),
(484, 'Trần', 'Vũ', '0919293488', 'Quận 10, TP.HCM', 'vuuungoo@gmail.com', 1),
(509, 'Nguyễn', 'Hải', '0938379292', 'Thành phố Hưng Yên, Hưng Yên', 'pikopikopi@gmail.com', 1),
(513, 'Trương', 'Văn', '0988139288', 'Huyện Gò Gầu, Tây Ninh', 'vanchuong89@gmail.com', 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `khuyenmai`
--

CREATE TABLE `khuyenmai` (
  `MaKM` int(10) UNSIGNED NOT NULL,
  `TieuDe` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `NoiDung` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `NgayBD` timestamp NULL DEFAULT NULL,
  `NgayKT` timestamp NULL DEFAULT NULL,
  `TinhTrang` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `khuyenmai`
--

INSERT INTO `khuyenmai` (`MaKM`, `TieuDe`, `NoiDung`, `NgayBD`, `NgayKT`, `TinhTrang`) VALUES
(100, 'Rộn Ràng Khai Trương', 'Giảm 50k giá Cafe Capuchino', '2022-04-29 17:00:00', '2023-04-30 16:59:59', 1),
(201, 'Happy Women\'s Day', 'Giảm 80k giá Cafe Mocha cho khách hàng', '2022-03-07 17:00:00', '2023-03-08 16:59:59', 1),
(203, 'Chúc mừng U23 Việt Nam', 'Giảm 70k giá Cafe Latte', '2022-05-21 17:00:00', '2022-06-22 16:59:59', 1),
(303, 'Capuchino Miễn phí!', 'Tặng 1 ly Cafe Capuchino miễn phí khi mua trên 4 hộp Arabica', '2022-05-29 17:00:00', '2022-06-15 16:59:59', 1),
(393, 'Happy weekend!', 'Giảm 100k giá Cafe Espresso (áp dụng T7 & CN)', '2022-04-29 17:00:00', '2022-10-30 16:59:59', 1),
(402, 'Americanon buổi sáng', 'Giảm 50k giá Cafe Americano (8H-9H30)', '2022-04-29 17:00:00', '2022-07-30 16:59:59', 1),
(404, 'Cuối tuần tưng bừng', 'Giảm nhẹ 25k cho Cà phê Moka', '2022-05-28 15:28:53', '2022-05-29 15:28:56', 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `lichsu`
--

CREATE TABLE `lichsu` (
  `MaLS` int(11) NOT NULL,
  `TenDoiTuong` varchar(30) COLLATE utf8mb4_unicode_ci NOT NULL,
  `MaDoiTuong` int(10) NOT NULL,
  `ThoiGian` timestamp NOT NULL DEFAULT current_timestamp(),
  `NguoiThucHien` int(11) NOT NULL,
  `ThaoTac` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

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
(48, 'NhaCungCap', 15, '2022-05-24 16:29:47', 1, 'Thêm'),
(49, 'NhanVien', 4, '2022-05-27 13:03:19', 1, 'Sửa'),
(50, 'NhanVien', 4, '2022-05-27 13:03:31', 1, 'Sửa'),
(51, 'NhanVien', 4, '2022-05-27 13:03:43', 1, 'Sửa'),
(52, 'NhanVien', 2, '2022-05-27 13:03:50', 1, 'Sửa'),
(53, 'KhachHang', 514, '2022-05-27 13:10:45', 1, 'Thêm'),
(54, 'KhachHang', 514, '2022-05-27 13:10:48', 1, 'Sửa'),
(55, 'KhachHang', 514, '2022-05-27 13:10:51', 1, 'Sửa'),
(56, 'KhachHang', 514, '2022-05-27 13:15:45', 1, 'Sửa'),
(57, 'KhachHang', 514, '2022-05-27 13:15:49', 1, 'Sửa'),
(58, 'KhachHang', 514, '2022-05-27 13:15:54', 1, 'Xóa'),
(59, 'NhaCungCap', 16, '2022-05-27 13:18:12', 1, 'Thêm'),
(60, 'NhaCungCap', 16, '2022-05-27 13:18:17', 1, 'Sửa'),
(61, 'NhaCungCap', 16, '2022-05-27 13:18:21', 1, 'Sửa'),
(62, 'NhaCungCap', 16, '2022-05-27 13:18:23', 1, 'Sửa'),
(63, 'NhaCungCap', 16, '2022-05-27 13:18:25', 1, 'Sửa'),
(64, 'NhaCungCap', 16, '2022-05-27 13:18:28', 1, 'Xóa'),
(65, 'KhuyenMai', 402, '2022-05-27 13:19:03', 1, 'Sửa'),
(66, 'KhuyenMai', 393, '2022-05-27 13:19:11', 1, 'Sửa'),
(67, 'KhuyenMai', 203, '2022-05-27 13:19:27', 1, 'Sửa'),
(68, 'KhuyenMai', 201, '2022-05-27 13:19:33', 1, 'Sửa'),
(69, 'KhuyenMai', 100, '2022-05-27 13:19:39', 1, 'Sửa'),
(70, 'KhuyenMai', 403, '2022-05-27 13:20:00', 1, 'Thêm'),
(71, 'KhuyenMai', 403, '2022-05-27 13:20:03', 1, 'Sửa'),
(72, 'KhuyenMai', 403, '2022-05-27 13:20:06', 1, 'Sửa'),
(73, 'KhuyenMai', 403, '2022-05-27 13:20:08', 1, 'Sửa'),
(74, 'KhuyenMai', 403, '2022-05-27 13:20:10', 1, 'Xóa'),
(75, 'TaiKhoan', 1, '2022-05-27 13:37:48', 1, 'Sửa'),
(76, 'TaiKhoan', 5, '2022-05-27 13:37:48', 1, 'Sửa'),
(77, 'KhuyenMai', 201, '2022-05-27 14:30:45', 1, 'Sửa'),
(78, 'SanPham', 1, '2022-05-27 14:48:34', 1, 'Sửa'),
(79, 'PhieuNhap', 204, '2022-05-27 14:48:34', 1, 'Sửa'),
(80, 'SanPham', 1, '2022-05-27 14:57:42', 1, 'Sửa'),
(81, 'PhieuNhap', 204, '2022-05-27 14:57:42', 1, 'Sửa'),
(82, 'SanPham', 2, '2022-05-27 15:13:23', 1, 'Sửa'),
(83, 'PhieuNhap', 101, '2022-05-27 15:13:23', 1, 'Sửa'),
(84, 'SanPham', 6, '2022-05-27 15:16:11', 1, 'Sửa'),
(85, 'PhieuNhap', 101, '2022-05-27 15:16:11', 1, 'Sửa'),
(86, 'SanPham', 4, '2022-05-27 15:16:24', 1, 'Sửa'),
(87, 'PhieuNhap', 101, '2022-05-27 15:16:24', 1, 'Sửa'),
(88, 'SanPham', 5, '2022-05-27 15:16:51', 1, 'Sửa'),
(89, 'PhieuNhap', 104, '2022-05-27 15:16:52', 1, 'Sửa'),
(90, 'SanPham', 9, '2022-05-27 15:17:01', 1, 'Sửa'),
(91, 'PhieuNhap', 104, '2022-05-27 15:17:01', 1, 'Sửa'),
(92, 'SanPham', 3, '2022-05-27 15:17:23', 1, 'Sửa'),
(93, 'PhieuNhap', 201, '2022-05-27 15:17:23', 1, 'Sửa'),
(94, 'SanPham', 5, '2022-05-27 15:17:34', 1, 'Sửa'),
(95, 'PhieuNhap', 201, '2022-05-27 15:17:34', 1, 'Sửa'),
(96, 'SanPham', 1, '2022-05-27 15:17:42', 1, 'Sửa'),
(97, 'PhieuNhap', 201, '2022-05-27 15:17:42', 1, 'Sửa'),
(98, 'SanPham', 7, '2022-05-27 15:18:08', 1, 'Sửa'),
(99, 'PhieuNhap', 202, '2022-05-27 15:18:08', 1, 'Sửa'),
(100, 'SanPham', 8, '2022-05-27 15:18:19', 1, 'Sửa'),
(101, 'PhieuNhap', 202, '2022-05-27 15:18:19', 1, 'Sửa'),
(102, 'SanPham', 2, '2022-05-27 15:18:32', 1, 'Sửa'),
(103, 'PhieuNhap', 203, '2022-05-27 15:18:32', 1, 'Sửa'),
(104, 'SanPham', 4, '2022-05-27 15:18:46', 1, 'Sửa'),
(105, 'PhieuNhap', 203, '2022-05-27 15:18:46', 1, 'Sửa'),
(106, 'SanPham', 8, '2022-05-27 15:19:11', 1, 'Sửa'),
(107, 'PhieuNhap', 203, '2022-05-27 15:19:11', 1, 'Sửa'),
(108, 'SanPham', 10, '2022-05-27 15:21:46', 1, 'Sửa'),
(109, 'PhieuNhap', 204, '2022-05-27 15:21:46', 1, 'Sửa'),
(110, 'KhuyenMai', 404, '2022-05-27 15:30:35', 1, 'Thêm'),
(111, 'PhieuNhap', 205, '2022-05-27 15:31:30', 1, 'Thêm'),
(112, 'PhieuNhap', 206, '2022-05-27 15:33:47', 1, 'Thêm'),
(113, 'PhieuNhap', 207, '2022-05-27 15:33:52', 1, 'Thêm'),
(114, 'PhieuNhap', 208, '2022-05-27 15:34:25', 1, 'Thêm'),
(115, 'SanPham', 3, '2022-05-27 15:34:37', 1, 'Sửa'),
(116, 'PhieuNhap', 208, '2022-05-27 15:34:37', 1, 'Sửa'),
(117, 'SanPham', 6, '2022-05-27 15:34:47', 1, 'Sửa'),
(118, 'PhieuNhap', 208, '2022-05-27 15:34:47', 1, 'Sửa'),
(119, 'SanPham', 9, '2022-05-27 15:34:54', 1, 'Sửa'),
(120, 'PhieuNhap', 208, '2022-05-27 15:34:54', 1, 'Sửa'),
(121, 'SanPham', 2, '2022-05-27 15:35:08', 1, 'Sửa'),
(122, 'PhieuNhap', 207, '2022-05-27 15:35:08', 1, 'Sửa'),
(123, 'SanPham', 1, '2022-05-27 15:35:16', 1, 'Sửa'),
(124, 'PhieuNhap', 207, '2022-05-27 15:35:16', 1, 'Sửa'),
(125, 'SanPham', 7, '2022-05-27 15:35:27', 1, 'Sửa'),
(126, 'PhieuNhap', 207, '2022-05-27 15:35:27', 1, 'Sửa'),
(127, 'SanPham', 3, '2022-05-27 15:35:33', 1, 'Sửa'),
(128, 'PhieuNhap', 207, '2022-05-27 15:35:33', 1, 'Sửa'),
(129, 'SanPham', 1, '2022-05-27 15:37:02', 1, 'Sửa'),
(130, 'PhieuNhap', 205, '2022-05-27 15:37:02', 1, 'Sửa'),
(131, 'SanPham', 6, '2022-05-27 15:37:17', 1, 'Sửa'),
(132, 'PhieuNhap', 206, '2022-05-27 15:37:17', 1, 'Sửa'),
(133, 'SanPham', 2, '2022-05-27 15:37:32', 1, 'Sửa'),
(134, 'PhieuNhap', 206, '2022-05-27 15:37:32', 1, 'Sửa'),
(135, 'PhieuNhap', 209, '2022-05-27 16:02:55', 1, 'Thêm'),
(136, 'SanPham', 4, '2022-05-27 16:03:05', 1, 'Sửa'),
(137, 'PhieuNhap', 209, '2022-05-27 16:03:05', 1, 'Sửa'),
(138, 'PhieuNhap', 209, '2022-05-27 16:03:15', 1, 'Sửa'),
(139, 'PhieuNhap', 209, '2022-05-27 16:03:26', 1, 'Sửa'),
(140, 'PhieuNhap', 209, '2022-05-27 16:06:09', 1, 'Sửa'),
(141, 'PhieuNhap', 209, '2022-05-27 16:06:31', 1, 'Sửa'),
(142, 'SanPham', 4, '2022-05-27 16:13:21', 1, 'Sửa'),
(143, 'PhieuNhap', 209, '2022-05-27 16:13:21', 1, 'Sửa'),
(144, 'SanPham', 4, '2022-05-27 16:13:28', 1, 'Sửa'),
(145, 'PhieuNhap', 209, '2022-05-27 16:13:28', 1, 'Sửa'),
(146, 'SanPham', 4, '2022-05-27 16:13:35', 1, 'Sửa'),
(147, 'PhieuNhap', 209, '2022-05-27 16:13:35', 1, 'Sửa'),
(148, 'SanPham', 4, '2022-05-27 16:13:38', 1, 'Sửa'),
(149, 'SanPham', 4, '2022-05-27 16:13:38', 1, 'Sửa'),
(150, 'PhieuNhap', 209, '2022-05-27 16:13:38', 1, 'Sửa'),
(151, 'PhieuNhap', 209, '2022-05-27 16:13:38', 1, 'Xóa'),
(152, 'SanPham', 1, '2022-05-27 16:22:43', 1, 'Sửa'),
(153, 'HoaDon', 87, '2022-05-27 16:22:43', 1, 'Sửa'),
(154, 'SanPham', 4, '2022-05-27 16:23:06', 1, 'Sửa'),
(155, 'HoaDon', 87, '2022-05-27 16:23:06', 1, 'Sửa'),
(156, 'SanPham', 2, '2022-05-27 16:23:36', 1, 'Sửa'),
(157, 'HoaDon', 83, '2022-05-27 16:23:36', 1, 'Sửa'),
(158, 'SanPham', 3, '2022-05-27 16:23:45', 1, 'Sửa'),
(159, 'HoaDon', 83, '2022-05-27 16:23:45', 1, 'Sửa'),
(160, 'SanPham', 3, '2022-05-27 16:24:04', 1, 'Sửa'),
(161, 'HoaDon', 83, '2022-05-27 16:24:04', 1, 'Sửa'),
(162, 'SanPham', 10, '2022-05-27 16:25:39', 1, 'Sửa'),
(163, 'HoaDon', 83, '2022-05-27 16:25:39', 1, 'Sửa'),
(164, 'SanPham', 7, '2022-05-27 16:25:53', 1, 'Sửa'),
(165, 'HoaDon', 83, '2022-05-27 16:25:53', 1, 'Sửa'),
(166, 'SanPham', 2, '2022-05-27 16:26:29', 1, 'Sửa'),
(167, 'SanPham', 3, '2022-05-27 16:26:29', 1, 'Sửa'),
(168, 'SanPham', 10, '2022-05-27 16:26:29', 1, 'Sửa'),
(169, 'SanPham', 7, '2022-05-27 16:26:29', 1, 'Sửa'),
(170, 'HoaDon', 83, '2022-05-27 16:26:29', 1, 'Sửa'),
(171, 'SanPham', 2, '2022-05-27 16:26:37', 1, 'Sửa'),
(172, 'HoaDon', 83, '2022-05-27 16:26:37', 1, 'Sửa'),
(173, 'SanPham', 3, '2022-05-27 16:26:37', 1, 'Sửa'),
(174, 'HoaDon', 83, '2022-05-27 16:26:37', 1, 'Sửa'),
(175, 'SanPham', 10, '2022-05-27 16:26:37', 1, 'Sửa'),
(176, 'HoaDon', 83, '2022-05-27 16:26:37', 1, 'Sửa'),
(177, 'SanPham', 7, '2022-05-27 16:26:38', 1, 'Sửa'),
(178, 'HoaDon', 83, '2022-05-27 16:26:38', 1, 'Sửa'),
(179, 'HoaDon', 83, '2022-05-27 16:26:38', 1, 'Xóa'),
(180, 'SanPham', 7, '2022-05-27 16:35:32', 1, 'Sửa'),
(181, 'HoaDon', 60, '2022-05-27 16:35:33', 1, 'Sửa'),
(182, 'SanPham', 8, '2022-05-27 16:37:20', 1, 'Sửa'),
(183, 'HoaDon', 60, '2022-05-27 16:37:20', 1, 'Sửa'),
(184, 'SanPham', 8, '2022-05-27 16:37:44', 1, 'Sửa'),
(185, 'HoaDon', 60, '2022-05-27 16:37:44', 1, 'Sửa'),
(186, 'SanPham', 8, '2022-05-27 16:41:04', 1, 'Sửa'),
(187, 'HoaDon', 60, '2022-05-27 16:41:04', 1, 'Sửa'),
(188, 'SanPham', 1, '2022-05-27 16:41:44', 1, 'Sửa'),
(189, 'HoaDon', 1, '2022-05-27 16:41:44', 1, 'Sửa'),
(190, 'SanPham', 5, '2022-05-27 16:41:52', 1, 'Sửa'),
(191, 'HoaDon', 1, '2022-05-27 16:41:52', 1, 'Sửa'),
(192, 'SanPham', 6, '2022-05-27 16:42:00', 1, 'Sửa'),
(193, 'HoaDon', 1, '2022-05-27 16:42:00', 1, 'Sửa'),
(194, 'SanPham', 8, '2022-05-27 16:42:11', 1, 'Sửa'),
(195, 'HoaDon', 2, '2022-05-27 16:42:11', 1, 'Sửa'),
(196, 'SanPham', 5, '2022-05-27 16:42:25', 1, 'Sửa'),
(197, 'HoaDon', 2, '2022-05-27 16:42:25', 1, 'Sửa'),
(198, 'SanPham', 2, '2022-05-27 16:42:35', 1, 'Sửa'),
(199, 'HoaDon', 2, '2022-05-27 16:42:36', 1, 'Sửa'),
(200, 'SanPham', 6, '2022-05-27 16:43:00', 1, 'Sửa'),
(201, 'HoaDon', 2, '2022-05-27 16:43:00', 1, 'Sửa'),
(202, 'SanPham', 2, '2022-05-27 16:43:11', 1, 'Sửa'),
(203, 'HoaDon', 3, '2022-05-27 16:43:11', 1, 'Sửa'),
(204, 'SanPham', 7, '2022-05-27 16:43:21', 1, 'Sửa'),
(205, 'HoaDon', 35, '2022-05-27 16:43:21', 1, 'Sửa'),
(206, 'SanPham', 4, '2022-05-27 16:43:29', 1, 'Sửa'),
(207, 'HoaDon', 35, '2022-05-27 16:43:29', 1, 'Sửa'),
(208, 'SanPham', 3, '2022-05-27 16:43:35', 1, 'Sửa'),
(209, 'HoaDon', 35, '2022-05-27 16:43:35', 1, 'Sửa'),
(210, 'SanPham', 1, '2022-05-27 16:43:59', 1, 'Sửa'),
(211, 'HoaDon', 69, '2022-05-27 16:43:59', 1, 'Sửa'),
(212, 'SanPham', 8, '2022-05-27 16:44:12', 1, 'Sửa'),
(213, 'HoaDon', 87, '2022-05-27 16:44:12', 1, 'Sửa'),
(214, 'SanPham', 10, '2022-05-27 16:44:30', 1, 'Sửa'),
(215, 'HoaDon', 69, '2022-05-27 16:44:30', 1, 'Sửa'),
(216, 'SanPham', 2, '2022-05-27 16:44:49', 1, 'Sửa'),
(217, 'HoaDon', 69, '2022-05-27 16:44:49', 1, 'Sửa'),
(218, 'SanPham', 7, '2022-05-27 16:45:00', 1, 'Sửa'),
(219, 'HoaDon', 56, '2022-05-27 16:45:00', 1, 'Sửa'),
(220, 'SanPham', 3, '2022-05-27 16:45:09', 1, 'Sửa'),
(221, 'HoaDon', 56, '2022-05-27 16:45:09', 1, 'Sửa'),
(222, 'SanPham', 6, '2022-05-27 16:45:18', 1, 'Sửa'),
(223, 'HoaDon', 41, '2022-05-27 16:45:18', 1, 'Sửa'),
(224, 'SanPham', 3, '2022-05-27 16:45:28', 1, 'Sửa'),
(225, 'HoaDon', 41, '2022-05-27 16:45:28', 1, 'Sửa'),
(226, 'SanPham', 7, '2022-05-27 16:45:33', 1, 'Sửa'),
(227, 'HoaDon', 41, '2022-05-27 16:45:33', 1, 'Sửa'),
(228, 'HoaDon', 88, '2022-05-27 16:47:10', 1, 'Thêm'),
(229, 'SanPham', 5, '2022-05-27 16:47:20', 1, 'Sửa'),
(230, 'HoaDon', 88, '2022-05-27 16:47:20', 1, 'Sửa'),
(231, 'SanPham', 1, '2022-05-27 16:50:23', 1, 'Sửa'),
(232, 'SanPham', 1, '2022-05-27 16:50:35', 1, 'Sửa');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `loaisp`
--

CREATE TABLE `loaisp` (
  `MaLoai` int(10) UNSIGNED NOT NULL,
  `TenLoai` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `MoTa` text COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `loaisp`
--

INSERT INTO `loaisp` (`MaLoai`, `TenLoai`, `MoTa`) VALUES
(1, 'Cà phê Ý', 'Các loại cafe có nguồn gốc ở Italy'),
(2, 'Cà phê Việt Nam', 'Các loại cà phê ở Việt Nam trồng phổ biến hiện nay'),
(3, 'Cafe Mỹ', 'Các loại cafe có nguồn gốc từ Mỹ');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `nhacungcap`
--

CREATE TABLE `nhacungcap` (
  `MaNCC` int(10) UNSIGNED NOT NULL,
  `TenNCC` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL,
  `sdt` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  `DiaChi` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL,
  `SoTaiKhoan` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  `TinhTrang` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `nhacungcap`
--

INSERT INTO `nhacungcap` (`MaNCC`, `TenNCC`, `sdt`, `DiaChi`, `SoTaiKhoan`, `TinhTrang`) VALUES
(1, '90S Coffee', '0929 899 998', '20 Đường số 3, P. Trường Thọ, Q. Thủ Đức.', '45010003666916', 1),
(2, 'Lăk Coffee', '0917 762 211', 'xã Quảng Hiệp, huyện Cư M’gar, tỉnh DakLak', '45071113676916', 1),
(3, 'Chồn Vàng Coffee', '0907 330 011', 'Số 3, Ấp 1, Xã Tân Bửu, Huyện Bến Lức, Tỉnh Long An', '45071213126916', 1),
(4, 'Cà Phê Triều Nguyên', '0966 770 770', '120A Lý Thái Tổ, Xã Đamb’ri, TP Bảo Lộc  tỉnh Lâm Đồng', '65721213126931', 1),
(5, 'Cà phê Buôn Mê', '0909 555 301', '35/4A Ao Đôi, Bình Trị Đông A, Quận Bình Tân, TPHCM', '66721213126909', 1),
(6, 'Retro Coffee', '0911 222 678', '02 Nguyễn Ngọc Nhựt, Quận Tân Phú, TPHCM', '34721263126831', 1),
(7, 'Dakland Coffee', '0987 008 811', '949 Nguyễn Kiệm , P.3, Quận Gò Vấp, TPHCM', '25183263126837', 1),
(8, 'Mộc Miên', '0914 471 444‬', '73 Bà Hạt, Phường 9, Quận 10, TP HCM', '37625263126467', 1),
(9, 'Sơn Việt Coffee ', '0937 442 338', '148 Lý Thái Tổ, Thôn 6, ĐamBri , Bảo Lộc , tỉnh Lâm Đồng', '54013603671912', 1),
(10, 'Công ty TNHH cà phê LYON', '0909 587 675', '564/12 Phạm Văn Chiêu, Phường 16, Quận Gò Vấp TPHCM', '35183793126437', 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `nhanvien`
--

CREATE TABLE `nhanvien` (
  `MaNV` int(10) UNSIGNED NOT NULL,
  `MaTK` int(10) UNSIGNED DEFAULT NULL,
  `Ho` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `Ten` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `NgaySinh` date NOT NULL,
  `sdt` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  `Email` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `GioiTinh` tinyint(1) NOT NULL,
  `Luong` int(20) UNSIGNED NOT NULL,
  `TinhTrang` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `nhanvien`
--

INSERT INTO `nhanvien` (`MaNV`, `MaTK`, `Ho`, `Ten`, `NgaySinh`, `sdt`, `Email`, `GioiTinh`, `Luong`, `TinhTrang`) VALUES
(1, 1, 'Sếp', 'Tổng', '1999-01-12', '0913111222', 'crossingcoffee@gmail.com', 1, 50000000, 1),
(2, 5, 'Trần Tuấn', 'Anh', '2001-05-02', '0397642331', 'trantuananh@gmail.com', 1, 5000000, 1),
(3, 0, 'Trần Thúy', 'Vy', '2000-10-10', '0909382194', 'vyvytt@gmail.com', 0, 12000000, 1),
(4, NULL, 'Nguyễn Văn', 'Minh', '2002-02-28', '0914020828', 'vanminh01@gmail.com', 1, 5000000, 1),
(5, 0, 'Lê Nhật ', 'Anh', '2001-12-20', '0983873828', 'nhatanhle1220@gmail.com', 1, 6000000, 1),
(6, 0, 'Hồ Ngọc ', 'An', '2003-03-12', '0919929838', 'ngocan123@gmail.com', 0, 6000000, 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `phanquyen`
--

CREATE TABLE `phanquyen` (
  `MaPQ` int(11) NOT NULL,
  `TenPQ` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'Nhân viên',
  `QuyenHD` int(11) DEFAULT NULL,
  `QuyenSP` int(11) DEFAULT NULL,
  `QuyenPN` int(11) DEFAULT NULL,
  `QuyenNCC` int(11) DEFAULT NULL,
  `QuyenKH` int(11) DEFAULT NULL,
  `QuyenKM` int(11) DEFAULT NULL,
  `QuyenTK` int(11) DEFAULT NULL,
  `QuyenExcel` int(11) DEFAULT NULL,
  `QuyenNV` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `phieunhap`
--

INSERT INTO `phieunhap` (`MaPN`, `MaNCC`, `MaNV`, `NgayTao`, `TongTien`, `TinhTrang`) VALUES
(101, 6, 2, '2021-10-15 06:00:40', 11112000, 1),
(104, 10, 3, '2021-11-01 02:41:30', 27808000, 1),
(201, 3, 3, '2022-02-24 08:13:34', 10180000, 1),
(202, 4, 2, '2022-03-10 03:20:13', 116444000, 1),
(203, 2, 2, '2022-03-12 09:29:02', 22220000, 1),
(204, 5, 2, '2022-04-25 01:20:41', 21596000, 1),
(205, 7, 1, '2022-05-27 15:31:30', 28608000, 1),
(206, 8, 1, '2022-05-27 15:33:47', 56616000, 1),
(207, 9, 1, '2022-05-27 15:33:52', 36192000, 1),
(208, 1, 1, '2022-05-27 15:34:25', 12712000, 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `sanpham`
--

CREATE TABLE `sanpham` (
  `MaSP` int(10) UNSIGNED NOT NULL,
  `MaLoai` int(10) UNSIGNED NOT NULL,
  `MaNCC` int(10) UNSIGNED DEFAULT NULL,
  `TenSP` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `MoTa` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `HinhAnh` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL,
  `DonGia` int(20) UNSIGNED NOT NULL,
  `DonVi` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `SoLuong` int(10) UNSIGNED NOT NULL,
  `TinhTrang` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `sanpham`
--

INSERT INTO `sanpham` (`MaSP`, `MaLoai`, `MaNCC`, `TenSP`, `MoTa`, `HinhAnh`, `DonGia`, `DonVi`, `SoLuong`, `TinhTrang`) VALUES
(1, 1, 1, 'Cà Phê Capuchino', 'Các loại cafe có nguồn gốc ở Italy', 'capuchino.png', 40000, 'Ly', 35, 1),
(2, 1, 7, 'Cà phê Espresso', 'Espresso là loại cà phê nổi tiếng ở phương Tây, đặc biệt là Ý. Tại Việt Nam loại cà phê này cũng được xem là loại thức uống được ưa chuộng. Espresso được chế biến bằng cà phê bột mịn pha với nước nóng kết hợp với lớp kem được phủ trên bề mặt. Espresso có vị đắng, hàm lượng caffeine cao hơn so với các loại đồ uống khác.', 'espresso.png', 45000, 'Ly', 200, 1),
(3, 1, 6, 'Cà phê Latte', 'Tương tự như Capuchino, cà phê Latte cũng mang hương vị ngọt và béo ngậy của sữa.Trong thành phần của Latte cũng chứa cà phê Espresso, sữa nóng, bọt sữa. Điểm khác biệt ở cà phê Latte chính là lượng sữa bọt khi chế biến chỉ bằng một nửa sữa nóng.', 'latte.png', 50000, 'Ly', 751, 1),
(4, 3, 2, 'Cà phê Americano', 'Cà phê Americano là một loại khác thuộc dòng cà phê Espresso. Tuy có cách thức pha giống như Espresso nhưng lượng nước để pha Americano sẽ cần gấp đôi. Vì vậy mà Americano sẽ có hương vị nhẹ nhàng hơn, ít đắng, ít chua kèm với đó là hàm lượng caffeine thấp hơn. ', 'americano.png', 35000, 'Ly', 299, 1),
(5, 1, 6, 'Cà phê Mocha', 'Mocha là loại cà phê cuối cùng nằm trong top 10 loại cafe ngon ở Việt Nam. Loại cà phê này được giới trẻ Việt Nam vô cùng yêu thích nhờ có hương thơm quyến rũ, vị béo ngậy của kem tươi.', 'mocha.png', 55000, 'Ly', 303, 1),
(6, 2, 5, 'Cà phê Robusta ', 'Cà phê Robusta là loại cà phê được trồng phổ biến ở Việt Nam, đặc biệt là các tỉnh Tây Nguyên. Hương vị của cà phê Robusta được đánh giá là thơm nồng, không chua và có vị đậm đặc. Nhờ hương vị đặc trưng này mà Robusta được xem là một trong các loại cafe ngon nhất Việt Nam. \r\n\r\nCà phê Robusta chứa hàm lượng caffeine khá nhiều nên được người Việt ưa chuộng và ưu tiên lựa chọn. Người ta thường rang xay cà phê để giữ được độ nguyên chất. Như vậy sau khi pha sẽ có mùi vị đặc trưng, thơm ngon đậm đà.', 'robusta.png', 60000, 'Hộp', 548, 1),
(7, 2, 9, 'Cà phê Arabica', 'Cà phê Arabica hay còn gọi là cà phê chè được trồng nhiều ở Lâm Đồng. Loại cà phê này có hương thơm nồng nàn, hậu vị đắng như Robusta và rất dễ uống. Vậy nên đây là loại cà phê được nhiều người phương Tây ưa chuộng, đặc biệt là phụ nữ. \r\n\r\nLoại cà phê này thường được ủ men trước khi đưa đi sấy khô. Vậy nên khi uống chúng ta sẽ cảm nhận được hương vị chua nhẹ quyến rũ, sau đó chuyển sang vị đắng đặc trưng. ', 'arabica.png', 70000, 'Hộp', 530, 1),
(8, 2, 3, 'Cà phê Culi', 'Cà phê Culi cũng được đánh giá là một trong các loại cà phê Việt Nam ngon và được ưa chuộng. Culi được đánh giá là có hàm lượng caffeine và hương vị đậm đặc hơn Robusta, nên được phái mạnh ưa chuộng. Cà phê Culi khi pha sẽ có vị đắng gắt, hương thơm nồng nàn, nếu uống không quen sẽ rất dễ bị say.', 'culi.png', 75000, 'Hộp', 243, 1),
(9, 2, 4, 'Cà phê Cherry', 'Cà phê Cherry được đánh giá là bắt mắt nhờ có màu sắc vàng nâu, sáng bóng rất đẹp. Khi pha cà phê sẽ cho hương thơm thoang thoảng như mùi mít, có vị chua nhẹ. Khi uống sẽ cảm nhận được vị đắng nhẹ và có cảm giác vừa dân dã vừa sang trọng.', 'cherry.png', 55000, 'Hộp', 393, 1),
(10, 2, 8, 'Cà phê Moka', 'Cà phê Moka là giống cà phê thuộc chi Arabica, được trồng nhiều ở Lâm Đồng. Đây là loại cà phê khó trồng, sản lượng ít nên được đánh giá là khác hiếm. Vì vậy mà giá của loại cà phê này trên thị trường khá cao. Cà phê Moka là loại cà phê ngon với hương vị quyến rũ, vị chua nhẹ hậu vị đắng đặc trưng. \r\n\r\nDo loại cà phê này khá hiếm và có hương vị thơm ngon độc đáo nên thường được dùng để làm quà biếu.', 'moka.png', 35000, 'Hộp', 211, 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `taikhoan`
--

CREATE TABLE `taikhoan` (
  `MaTK` int(10) UNSIGNED NOT NULL,
  `TenDangNhap` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  `MatKhauHash` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL,
  `MatKhauSalt` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `NgayTao` timestamp NOT NULL DEFAULT current_timestamp(),
  `NguoiTao` int(10) NOT NULL,
  `MaPQ` int(10) NOT NULL,
  `TinhTrang` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `taikhoan`
--

INSERT INTO `taikhoan` (`MaTK`, `TenDangNhap`, `MatKhauHash`, `MatKhauSalt`, `NgayTao`, `NguoiTao`, `MaPQ`, `TinhTrang`) VALUES
(1, 'admin', 'd83c81ba59bb048750c30c9d3fd7abd07d27140ce6dfcf17244b944073438fce', 'cdd1a14c970e3c41', '2022-04-03 10:42:55', 1, 1, 1),
(5, 'testta', '595c42a1acecd90b16ae62de32897d7455a4ff17c40e396af406e5bd1130a3b1', '48505f476a7ea472', '2022-05-23 04:22:21', 1, 5, 1);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `ct_hoadon`
--
ALTER TABLE `ct_hoadon`
  ADD PRIMARY KEY (`MaCTHD`),
  ADD KEY `FK_CTHD_HD` (`MaHD`),
  ADD KEY `FK_CTHD_SP` (`MaSP`);

--
-- Chỉ mục cho bảng `ct_khuyenmai`
--
ALTER TABLE `ct_khuyenmai`
  ADD PRIMARY KEY (`MaCTKM`),
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
  ADD PRIMARY KEY (`MaNV`);

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
  ADD KEY `FK_SP_LOAISP` (`MaLoai`);

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
  MODIFY `MaCTHD` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;

--
-- AUTO_INCREMENT cho bảng `ct_khuyenmai`
--
ALTER TABLE `ct_khuyenmai`
  MODIFY `MaCTKM` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT cho bảng `ct_phanquyen`
--
ALTER TABLE `ct_phanquyen`
  MODIFY `MaCTPQ` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=64;

--
-- AUTO_INCREMENT cho bảng `ct_phieunhap`
--
ALTER TABLE `ct_phieunhap`
  MODIFY `MaCTPN` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;

--
-- AUTO_INCREMENT cho bảng `hoadon`
--
ALTER TABLE `hoadon`
  MODIFY `MaHD` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=89;

--
-- AUTO_INCREMENT cho bảng `khachhang`
--
ALTER TABLE `khachhang`
  MODIFY `MaKH` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=515;

--
-- AUTO_INCREMENT cho bảng `khuyenmai`
--
ALTER TABLE `khuyenmai`
  MODIFY `MaKM` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=405;

--
-- AUTO_INCREMENT cho bảng `lichsu`
--
ALTER TABLE `lichsu`
  MODIFY `MaLS` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=233;

--
-- AUTO_INCREMENT cho bảng `loaisp`
--
ALTER TABLE `loaisp`
  MODIFY `MaLoai` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT cho bảng `nhacungcap`
--
ALTER TABLE `nhacungcap`
  MODIFY `MaNCC` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT cho bảng `nhanvien`
--
ALTER TABLE `nhanvien`
  MODIFY `MaNV` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT cho bảng `phanquyen`
--
ALTER TABLE `phanquyen`
  MODIFY `MaPQ` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT cho bảng `phieunhap`
--
ALTER TABLE `phieunhap`
  MODIFY `MaPN` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=210;

--
-- AUTO_INCREMENT cho bảng `sanpham`
--
ALTER TABLE `sanpham`
  MODIFY `MaSP` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT cho bảng `taikhoan`
--
ALTER TABLE `taikhoan`
  MODIFY `MaTK` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

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
  ADD CONSTRAINT `FK_HD_NV` FOREIGN KEY (`MaNV`) REFERENCES `nhanvien` (`manv`);

--
-- Các ràng buộc cho bảng `nhanvien`
--
ALTER TABLE `nhanvien`
  ADD CONSTRAINT `FK_NV_TK` FOREIGN KEY (`MaTK`) REFERENCES `taikhoan` (`matk`);

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
  ADD CONSTRAINT `FK_TK_PQ` FOREIGN KEY (`MaPQ`) REFERENCES `phanquyen` (`MaPQ`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

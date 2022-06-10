-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th6 01, 2020 lúc 04:53 AM
-- Phiên bản máy phục vụ: 10.4.11-MariaDB
-- Phiên bản PHP: 7.2.31

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `sieuthimini`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chitiethd`
--

CREATE TABLE `chitiethd` (
  `MAHD` char(6) COLLATE utf8_unicode_ci NOT NULL,
  `MASP` char(6) COLLATE utf8_unicode_ci NOT NULL,
  `TENSP` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `SOLUONG` int(11) DEFAULT NULL,
  `DONGIA` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `chitiethd`
--

INSERT INTO `chitiethd` (`MAHD`, `MASP`, `TENSP`, `SOLUONG`, `DONGIA`) VALUES
('001', '001', 'IPhone 7', 10, 80000000),
('002', '004', 'IPhone 11', 1, 22000000),
('003', '001', 'Iphone 7', 1, 8000000),
('004', '001', 'Iphone 7', 1, 8000000),
('004', '012', 'Nokia 7', 1, 7000000),
('005', '013', 'Nokia 105', 1, 7000000);

--
-- Bẫy `chitiethd`
--
DELIMITER $$
CREATE TRIGGER `Them_ChiTiet` AFTER INSERT ON `chitiethd` FOR EACH ROW UPDATE `sanpham`
SET SOLUONG = SOLUONG - NEW.SOLUONG
WHERE MASP = NEW.MASP
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `Xoa_ChiTiet` AFTER DELETE ON `chitiethd` FOR EACH ROW BEGIN
 	UPDATE `sanpham`
	SET SOLUONG = SOLUONG + OLD.SOLUONG
	WHERE `sanpham`.`MASP` = OLD.MASP;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `hoadon`
--

CREATE TABLE `hoadon` (
  `MAHD` char(6) COLLATE utf8_unicode_ci NOT NULL,
  `MAKH` char(6) COLLATE utf8_unicode_ci DEFAULT NULL,
  `MANV` char(6) COLLATE utf8_unicode_ci NOT NULL,
  `NGAYHD` datetime NOT NULL,
  `TONGTIEN` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `hoadon`
--

INSERT INTO `hoadon` (`MAHD`, `MAKH`, `MANV`, `NGAYHD`, `TONGTIEN`) VALUES
('001', '001', '001', '2020-05-27 00:00:00', 80000000),
('002', '001', '002', '2020-05-21 19:02:08', 22000000),
('003', NULL, '001', '2020-05-31 19:27:53', 8000000),
('004', NULL, '002', '2020-05-31 19:29:12', 15000000),
('005', '001', '002', '2020-05-31 19:44:14', 7000000);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `khachhang`
--

CREATE TABLE `khachhang` (
  `MAKH` char(6) COLLATE utf8_unicode_ci NOT NULL,
  `HOKH` char(50) COLLATE utf8_unicode_ci NOT NULL,
  `TENKH` char(50) COLLATE utf8_unicode_ci NOT NULL,
  `DIACHI` char(50) COLLATE utf8_unicode_ci NOT NULL,
  `SDT` int(11) NOT NULL,
  `enable` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `khachhang`
--

INSERT INTO `khachhang` (`MAKH`, `HOKH`, `TENKH`, `DIACHI`, `SDT`, `enable`) VALUES
('001', 'Trần Văn', 'Minh', 'null', 212132, 1),
('002', 'Nguyễn Văn', 'AN', 'Quận 9 tphcm', 123456789, 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `loai`
--

CREATE TABLE `loai` (
  `MALOAI` char(6) COLLATE utf8_unicode_ci NOT NULL,
  `TENLOAI` char(50) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `loai`
--

INSERT INTO `loai` (`MALOAI`, `TENLOAI`) VALUES
('001', 'SmartPhone'),
('002', 'PhimCung'),
('003', 'BanCamUng');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `loaigg`
--

CREATE TABLE `loaigg` (
  `MALOAIGG` char(6) COLLATE utf8_unicode_ci NOT NULL,
  `PHANTRAMGG` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `loaigg`
--

INSERT INTO `loaigg` (`MALOAIGG`, `PHANTRAMGG`) VALUES
('001', 5),
('002', 10);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `nhacungcap`
--

CREATE TABLE `nhacungcap` (
  `MANCC` char(6) COLLATE utf8_unicode_ci NOT NULL,
  `TENNCC` char(50) COLLATE utf8_unicode_ci NOT NULL,
  `DIACHINCC` char(200) COLLATE utf8_unicode_ci NOT NULL,
  `DIENTHOAI` varchar(11) COLLATE utf8_unicode_ci NOT NULL,
  `SOFAX` varchar(11) COLLATE utf8_unicode_ci NOT NULL,
  `enable` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `nhacungcap`
--

INSERT INTO `nhacungcap` (`MANCC`, `TENNCC`, `DIACHINCC`, `DIENTHOAI`, `SOFAX`, `enable`) VALUES
('001', 'Apple Singapore', 'null', '12332132', '0123456789', 1),
('002', 'Samsung VN', 'Bitexco Financial Tower, 2 Hải Triều, Bến Nghé, Quận 1, Hồ Chí Minh', '093867278', '012345678', 1),
('003', 'Điện tử VN', '123 Bến Nghé, Quận 1, Hồ Chí Minh', '093215136', '012345564', 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `nhanvien`
--

CREATE TABLE `nhanvien` (
  `MANV` char(6) COLLATE utf8_unicode_ci NOT NULL,
  `HONV` char(50) COLLATE utf8_unicode_ci NOT NULL,
  `TENNV` char(50) COLLATE utf8_unicode_ci NOT NULL,
  `NAMSINH` int(4) NOT NULL,
  `PHAI` char(50) COLLATE utf8_unicode_ci NOT NULL,
  `MUCLUONG` float DEFAULT NULL,
  `DIACHI` char(50) COLLATE utf8_unicode_ci NOT NULL,
  `IMG` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `enable` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `nhanvien`
--

INSERT INTO `nhanvien` (`MANV`, `HONV`, `TENNV`, `NAMSINH`, `PHAI`, `MUCLUONG`, `DIACHI`, `IMG`, `enable`) VALUES
('001', 'Mạc', 'Phàm', 1999, 'Nam', 5000000, '113 streets 109', 'nv1.jpg', 1),
('002', 'Mục Ninh', 'Tuyết', 1999, 'Nữ', 5000000, '654 An Minh Quận 5', 'nv2.jpg', 1),
('003', 'Triệu', 'Thiên', 1996, 'Nam', 5000000, '140 Hùng Vương Q1', 'nv4.jpg', 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `nhasanxuat`
--

CREATE TABLE `nhasanxuat` (
  `MANSX` char(6) COLLATE utf8_unicode_ci NOT NULL,
  `TENNSX` char(50) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `nhasanxuat`
--

INSERT INTO `nhasanxuat` (`MANSX`, `TENNSX`) VALUES
('001', 'Iphone'),
('002', 'SamSung'),
('003', 'Xiaomi'),
('004', 'Nokia'),
('005', 'Vertu');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `phieunhaphang`
--

CREATE TABLE `phieunhaphang` (
  `IDNHAP` char(6) COLLATE utf8_unicode_ci NOT NULL,
  `MANCC` char(6) COLLATE utf8_unicode_ci NOT NULL,
  `MASP` char(6) COLLATE utf8_unicode_ci NOT NULL,
  `NGAYNHAP` datetime(6) NOT NULL,
  `DONGIANHAP` int(11) NOT NULL,
  `SOLUONG` int(11) NOT NULL,
  `TONGTIEN` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `phieunhaphang`
--

INSERT INTO `phieunhaphang` (`IDNHAP`, `MANCC`, `MASP`, `NGAYNHAP`, `DONGIANHAP`, `SOLUONG`, `TONGTIEN`) VALUES
('001', '001', '001', '2020-05-13 19:05:46.000000', 6000000, 20, 120000000),
('002', '001', '001', '2020-05-13 19:05:46.000000', 16000000, 20, 320000000);

--
-- Bẫy `phieunhaphang`
--
DELIMITER $$
CREATE TRIGGER `ThemNhapHang` AFTER INSERT ON `phieunhaphang` FOR EACH ROW UPDATE sanpham SET SOLUONG = SOLUONG + NEW.SOLUONG WHERE MASP = NEW.MASP
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `sanpham`
--

CREATE TABLE `sanpham` (
  `MASP` char(6) COLLATE utf8_unicode_ci NOT NULL,
  `TENSP` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SOLUONG` int(11) NOT NULL,
  `GIA` int(11) DEFAULT NULL,
  `DONVITINH` char(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `MALOAI` char(6) COLLATE utf8_unicode_ci NOT NULL,
  `MANSX` char(6) COLLATE utf8_unicode_ci NOT NULL,
  `IMG` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `enable` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `sanpham`
--

INSERT INTO `sanpham` (`MASP`, `TENSP`, `SOLUONG`, `GIA`, `DONVITINH`, `MALOAI`, `MANSX`, `IMG`, `enable`) VALUES
('001', 'Iphone 7', 48, 8000000, 'Chiec', '001', '001', 'ip1.jpg', 1),
('002', 'Iphone 8', 40, 10000000, 'Chiec', '001', '001', 'ip2.jpg', 1),
('003', 'IPhone X', 50, 15000000, 'Chiec', '001', '001', 'ip3.jpg', 1),
('004', 'IPhone 11', 60, 22000000, 'Chiec', '001', '001', 'ip4.jpg', 1),
('005', 'Samsung Note 10', 50, 18000000, 'Chiec', '001', '002', 'ss1.jpg', 1),
('006', 'Samsung A51', 31, 12000000, 'Chiec', '001', '002', 'ss2.jpg', 1),
('007', 'Samsung S10', 63, 16000000, 'Chiec', '001', '002', 'ss3.jpg', 1),
('008', 'Samsung Ford', 63, 50000000, 'Chiec', '001', '002', 'ss5.jpg', 1),
('009', 'Xiaomi Mi9T', 63, 9000000, 'Chiec', '001', '003', 'xi1.jpg', 1),
('010', 'Xiaomi Mi8', 63, 6000000, 'Chiec', '001', '003', 'xi2.jpg', 1),
('011', 'Nokia 6', 63, 5000000, 'Chiec', '001', '004', 'no1.jpg', 1),
('012', 'Nokia 7', 49, 7000000, 'Chiec', '001', '004', 'no2.jpg', 1),
('013', 'Nokia 105', 49, 7000000, 'Cap', '002', '004', 'no2.jpg', 1),
('014', 'Vertu Singnuter S', 50, 7000000, 'Cap', '002', '005', 'no2.jpg', 1),
('015', 'Vertu Angel', 50, 7000000, 'Cap', '001', '005', 've2.jpg', 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `thongtingiamgia`
--

CREATE TABLE `thongtingiamgia` (
  `MALOAIGG` char(6) COLLATE utf8_unicode_ci NOT NULL,
  `MASP` char(6) COLLATE utf8_unicode_ci NOT NULL,
  `DOTGG` datetime NOT NULL,
  `BATDAU` datetime NOT NULL,
  `KETTHUC` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `thongtingiamgia`
--

INSERT INTO `thongtingiamgia` (`MALOAIGG`, `MASP`, `DOTGG`, `BATDAU`, `KETTHUC`) VALUES
('001', '001', '2020-05-15 19:16:24', '2020-05-29 19:16:24', '2020-06-30 19:16:24'),
('002', '013', '2020-05-15 19:16:24', '2020-05-01 19:16:24', '2020-06-30 19:16:24');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `user`
--

CREATE TABLE `user` (
  `userID` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `username` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `role` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `enable` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `user`
--

INSERT INTO `user` (`userID`, `username`, `password`, `role`, `enable`) VALUES
('001', 'admin', 'admin', 'Admin', 1),
('002', 'NVKM', 'NVKM', 'Nhân Viên', 1),
('003', 'NVKM2', 'NVKM2', 'Nhân Viên', 1);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `chitiethd`
--
ALTER TABLE `chitiethd`
  ADD PRIMARY KEY (`MAHD`,`MASP`),
  ADD KEY `FK_CTHD` (`MASP`,`MAHD`) USING BTREE;

--
-- Chỉ mục cho bảng `hoadon`
--
ALTER TABLE `hoadon`
  ADD PRIMARY KEY (`MAHD`) USING BTREE,
  ADD KEY `FK_HD` (`MAKH`),
  ADD KEY `FK_HD_1` (`MANV`);

--
-- Chỉ mục cho bảng `khachhang`
--
ALTER TABLE `khachhang`
  ADD PRIMARY KEY (`MAKH`);

--
-- Chỉ mục cho bảng `loai`
--
ALTER TABLE `loai`
  ADD PRIMARY KEY (`MALOAI`);

--
-- Chỉ mục cho bảng `loaigg`
--
ALTER TABLE `loaigg`
  ADD PRIMARY KEY (`MALOAIGG`);

--
-- Chỉ mục cho bảng `nhacungcap`
--
ALTER TABLE `nhacungcap`
  ADD PRIMARY KEY (`MANCC`);

--
-- Chỉ mục cho bảng `nhanvien`
--
ALTER TABLE `nhanvien`
  ADD PRIMARY KEY (`MANV`);

--
-- Chỉ mục cho bảng `nhasanxuat`
--
ALTER TABLE `nhasanxuat`
  ADD PRIMARY KEY (`MANSX`);

--
-- Chỉ mục cho bảng `phieunhaphang`
--
ALTER TABLE `phieunhaphang`
  ADD PRIMARY KEY (`IDNHAP`),
  ADD KEY `FK_MANCC` (`MANCC`),
  ADD KEY `FK_SP` (`MASP`);

--
-- Chỉ mục cho bảng `sanpham`
--
ALTER TABLE `sanpham`
  ADD PRIMARY KEY (`MASP`),
  ADD KEY `FK_SP` (`MALOAI`),
  ADD KEY `FK_SP_1` (`MANSX`);

--
-- Chỉ mục cho bảng `thongtingiamgia`
--
ALTER TABLE `thongtingiamgia`
  ADD PRIMARY KEY (`MALOAIGG`,`MASP`),
  ADD KEY `FK_TTGG_1` (`MASP`);

--
-- Chỉ mục cho bảng `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`userID`),
  ADD UNIQUE KEY `FK_ID` (`userID`);

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `chitiethd`
--
ALTER TABLE `chitiethd`
  ADD CONSTRAINT `FK_CTHD` FOREIGN KEY (`MASP`) REFERENCES `sanpham` (`MASP`),
  ADD CONSTRAINT `FK_CTHD_1` FOREIGN KEY (`MAHD`) REFERENCES `hoadon` (`MAHD`);

--
-- Các ràng buộc cho bảng `hoadon`
--
ALTER TABLE `hoadon`
  ADD CONSTRAINT `FK_HD` FOREIGN KEY (`MAKH`) REFERENCES `khachhang` (`MAKH`),
  ADD CONSTRAINT `FK_HD_1` FOREIGN KEY (`MANV`) REFERENCES `nhanvien` (`MANV`);

--
-- Các ràng buộc cho bảng `phieunhaphang`
--
ALTER TABLE `phieunhaphang`
  ADD CONSTRAINT `phieunhaphang_ibfk_1` FOREIGN KEY (`MASP`) REFERENCES `sanpham` (`MASP`),
  ADD CONSTRAINT `phieunhaphang_ibfk_2` FOREIGN KEY (`MANCC`) REFERENCES `nhacungcap` (`MANCC`);

--
-- Các ràng buộc cho bảng `sanpham`
--
ALTER TABLE `sanpham`
  ADD CONSTRAINT `FK_SP` FOREIGN KEY (`MALOAI`) REFERENCES `loai` (`MALOAI`),
  ADD CONSTRAINT `FK_SP_1` FOREIGN KEY (`MANSX`) REFERENCES `nhasanxuat` (`MANSX`);

--
-- Các ràng buộc cho bảng `thongtingiamgia`
--
ALTER TABLE `thongtingiamgia`
  ADD CONSTRAINT `FK_TTGG` FOREIGN KEY (`MALOAIGG`) REFERENCES `loaigg` (`MALOAIGG`),
  ADD CONSTRAINT `FK_TTGG_1` FOREIGN KEY (`MASP`) REFERENCES `sanpham` (`MASP`);

--
-- Các ràng buộc cho bảng `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `user_ibfk_1` FOREIGN KEY (`userID`) REFERENCES `nhanvien` (`MANV`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

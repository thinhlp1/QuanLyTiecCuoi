--Tạo CSDL
--CREATE DATABASE QuanLyTrungTamTiecCuoi
--DROP DATABASE QuanLyTrungTamTiecCuoi
GO
--Gọi CSDL
USE QuanLyTrungTamTiecCuoi
GO
--Tạo bảng "ChiTietDichVu"
--DROP TABLE ChiTietDichVu
CREATE TABLE ChiTietDichVu(
	MaHD varchar(5) NOT NULL,
	MaCSVC varchar(25) NOT NULL,
	MaDV varchar(25) NOT NULL,
	ChiPhi bigint NOT NULL,
	ChiPhiPhatSinh bigint NULL,
	GhiChu nvarchar(255) NULL,
 CONSTRAINT PK_ChiTietDichVu PRIMARY KEY 
(
	MaHD ASC,
	MaCSVC ASC,
	MaDV ASC
)
)
GO
--Tạo bảng "ChiTietDatMon"
CREATE TABLE ChiTietDatMon(
	MaHD varchar(5) NOT NULL,
	MaMA varchar(25) NOT NULL,
	ThuTu int NOT NULL,
	GhiChu nvarchar(255) NULL,
 CONSTRAINT PK_ChiTietDatMon PRIMARY KEY 
(
	MaHD ASC,
	MaMA ASC
)
) 
GO
--Tạo bảng "HopDongDichVu"
CREATE TABLE HopDongDichVu(
	MaHD varchar(5) NOT NULL,
	MaDV varchar(25) NOT NULL,
	MaGoi varchar(25) NULL,
	ChiPhi bigint NOT NULL,
	GhiChu nvarchar(255) NULL,
 CONSTRAINT PK_DichVuTrangTru PRIMARY KEY 
(
	MaHD ASC,
	MaDV ASC
)
)
GO
--Tạo bảng "ChiTietDichVuDiKem
--DROP TABLE ChiTietDichVuDiKem
CREATE TABLE ChiTietDichVuDiKem(
	MaHD varchar(5) NOT NULL,
	MaDV varchar(25) NOT NULL,
	GhiChu nvarchar(255) NULL,
	ChiPhi BIGINT NULL,
	ChiPhiPhatSinh bigint NULL,
	
 CONSTRAINT PK_ChiTietDichVuDiKem PRIMARY KEY CLUSTERED 
(
	MaHD ASC,
	MaDV ASC
)
)
GO
--Tạo bảng "ChiTietDVDuaDon"
CREATE TABLE ChiTietDVDuaDon(
	MaHD varchar(5) NOT NULL,
	MaNV varchar(5) NOT NULL,	
	LoaiXe varchar(25) NOT NULL,
 CONSTRAINT PK_ChiTietDVDuaDon PRIMARY KEY CLUSTERED 
(
	MaHD ASC,
	MaNV ASC
)
)
GO
--Tạo bảng "ChiTietGoiDihcVu"
CREATE TABLE ChiTietGoiDichVu(
	MaGoi varchar(25) NOT NULL,
	MaCSVC varchar(25) NOT NULL,
	
	GhiChu nvarchar(255) NULL,
 CONSTRAINT PK_ChiTietGoiDichVu PRIMARY KEY CLUSTERED 
(
	MaGoi ASC,
	MaCSVC ASC
)
)
GO
--Tạo bảng "ChiTietPhanCong"
CREATE TABLE ChiTietPhanCong(
	MaPC int  NOT NULL,
	MaNV varchar(5) NOT NULL,
	NgayPC date NOT NULL,
	GioBatDau time(7) NOT NULL,
	GioKetThuc time(7) NOT NULL,
 CONSTRAINT PK_ChiTietPhanCong PRIMARY KEY CLUSTERED 
(
	MaPC ASC,
	MaNV ASC,
	NgayPC ASC
)
)
GO
--Tạo bảng "ChiTietThucDon"
CREATE TABLE ChiTietThucDon(
	MaTD varchar(25) NOT NULL,
	MaMA varchar(25) NOT NULL,
	ThuTu int NOT NULL,
	GhiChu nvarchar(255) NULL,
 CONSTRAINT PK_ChiTietThucDon PRIMARY KEY CLUSTERED 
(
	MaTD ASC,
	MaMA ASC
)
)
--Tạo bảng "CoSoVatChat"
CREATE TABLE CoSoVatChat(
	MaCSVC varchar(25) NOT NULL,
	TenCSVC nvarchar(50) NOT NULL,
	MaDMC varchar(25) NOT NULL,
	SoLuong bigint NOT NULL,
	GiaThue bigint NULL,
	GhiChu nvarchar(255) NULL,
 CONSTRAINT PK_CoSoVatChat PRIMARY KEY CLUSTERED 
(
	MaCSVC ASC
)
)
--Tạo bảng "DanhMuc"
CREATE TABLE DanhMuc(
	MaDM varchar(25) NOT NULL,
	TenDM nvarchar(50) NOT NULL,
 CONSTRAINT PK_DanhMuc PRIMARY KEY CLUSTERED 
(
	MaDM ASC
)
)
GO
--Tạo bảng "DanhMucCon"
CREATE TABLE DanhMucCon(
	MaDMC varchar(25) NOT NULL,
	MaDM varchar(25) NOT NULL,
	TenDM nvarchar(50) NOT NULL,
 CONSTRAINT PK_DanhMucCon PRIMARY KEY CLUSTERED 
(
	MaDMC ASC
)
)
GO
--Tạo bảng "DichVu"
CREATE TABLE DichVu(
	MaDV varchar(25) NOT NULL,
	MaPLDV varchar(25) NULL,
	TenDV nvarchar(50) NOT NULL,
 CONSTRAINT PK_DichVu PRIMARY KEY CLUSTERED 
(
	MaDV ASC
)
)
GO
--Tạo bảng "DichVuDatMon"
CREATE TABLE DichVuDatMon(
	MaHD varchar(5) NOT NULL,
	MaTD varchar(25) NULL,
	ChiPhi bigint NOT NULL,
	GhiChu nvarchar(255) NULL,
 CONSTRAINT PK_DichVuDatMon1 PRIMARY KEY CLUSTERED 
(
	MaHD ASC
)
)
GO
--Tạo bảng "DichVuDiKem"
CREATE TABLE DichVuDiKem(
	MaDV varchar(25) NOT NULL,
	TenDV nvarchar(50) NOT NULL,
	Gia bigint NOT NULL,
 CONSTRAINT PK_DichVuDiKem PRIMARY KEY CLUSTERED 
(
	MaDV ASC
)
)
GO
--Tạo bảng DichVuDuaDon
CREATE TABLE DichVuDuaDon(
	MaHD varchar(5) NOT NULL,
	NgayKhoiHanh date NOT NULL,
	GioKhoiHanh time(7) NOT NULL,
	DiaDiem nvarchar(255) NOT NULL,
	GioVe time(7) NOT NULL,
	SoLuongKhach bigint NOT NULL,
	GhiChu nvarchar(255) NULL,
 CONSTRAINT PK_DichVuDuaDon PRIMARY KEY CLUSTERED 
(
	MaHD ASC
)
)
GO
--Tạo bảng "GoiDichVu"
CREATE TABLE GoiDichVu(
	MaGoi varchar(25) NOT NULL,
	MaDV varchar(25) NOT NULL,
	TenGoi nvarchar(255) NOT NULL,
	ChiPhi nchar(10) NOT NULL,
	GhiChu nvarchar(255) NULL,
	HinhAnh varchar(50) NULL,
 CONSTRAINT PK_GoiDichVuCong PRIMARY KEY CLUSTERED 
(
	MaGoi ASC
)
)
-- 
--Tạo bảng "HoaDon"
CREATE TABLE HoaDon(
	MaHoaDon int IDENTITY(1,1) NOT NULL,
	MaHD varchar(5) NOT NULL,
	NgayLap date NOT NULL,
	MaNV varchar(5) NOT NULL,
	TrangTriCong bigint NULL,   
	TrangTriBan bigint NULL,
	TrangTriSanKhau bigint NULL,
	DiKem bigint NULL,
	NgheThuat bigint NULL,
	ThucDon bigint NULL,
	TienCoc bigint NULL,
	PhatSinh bigint NULL,
	TongTien bigint NOT NULL,
	TrangThai int NOT NULL,

 CONSTRAINT PK_HoaDon PRIMARY KEY CLUSTERED 
(
	MaHoaDon ASC
)
)
GO
--Tạo bảng "HopDong"
CREATE TABLE HopDong(
	MaHD varchar(5) NOT NULL,
	MaNL varchar(5) NOT NULL,
	SoLuongBan bigint NOT NULL,
	Sanh varchar(25) NOT NULL,
	NgayLap date NOT NULL,
	NgayDuyet date NULL,
	MaND varchar(5) NULL,
	MaKH int NOT NULL,
	NgayToChuc date NOT NULL,
	ThoiGianBatDau time(7) NOT NULL,
	ThoiGianKetThuc time(7) NOT NULL,
	TrangThai varchar(25) NOT NULL,
	The bigint NOT NULL,
	TienCoc bigint NOT NULL,
	ChiPhiPhatSinh bigint NULL,
	TongTien bigint NULL,
 CONSTRAINT PK_HopDong PRIMARY KEY CLUSTERED 
(
	MaHD ASC
)
)
GO
--Tạo bảng KhachHang
CREATE TABLE KhachHang(
	MaKH  int IDENTITY(1,1) NOT NULL,
	HoTen nvarchar(50) NOT NULL,
	SoDienThoai varchar(15) NOT NULL,
	CCCD varchar(15) NOT NULL,
	DiaChi nvarchar(255) NOT NULL,
	HoTenCoDau nvarchar(50) NOT NULL,
	HoTenChuRe nvarchar(50) NOT NULL,
 CONSTRAINT PK_KhachHang PRIMARY KEY CLUSTERED 
(
	MaKH ASC
)
)
--Tạo bảng MonAn
CREATE TABLE MonAn(
	MaMA varchar(25) NOT NULL,
	TenMA nvarchar(50) NOT NULL,
	HinhAnh nvarchar(50) NOT NULL,
	GiaTien varchar(255) NOT NULL,
	MaPL varchar(25) NOT NULL,
 CONSTRAINT PK_MonAn PRIMARY KEY CLUSTERED 
(
	MaMA ASC
)
)
GO
--Tạo bảng NhanVien
CREATE TABLE NhanVien(
	MaNV varchar(5) NOT NULL,
	HoTen nvarchar(50) NOT NULL,
	NgaySinh date NOT NULL,
	GioiTinh bit NOT NULL,
	SoDienThoai varchar(15) NOT NULL,
	CCCD_CMND varchar(15) NOT NULL,
	Email varchar(255)  NULL,
	HinhAnh varchar(255) NOT NULL,
	MaPB varchar(25) NOT NULL,
	MaVT varchar(25) NOT NULL,
	TrangThai bigint NOT NULL,
 CONSTRAINT PK_NhanVien PRIMARY KEY CLUSTERED 
(
	MaNV ASC
)
)
GO
--Tạo bảng PhanCong
CREATE TABLE PhanCong(
	MaPC int IDENTITY(1,1) NOT NULL,
	MaHD varchar(5) NOT NULL,
	MaNguoiPC varchar(5) NOT NULL,
 CONSTRAINT PK_PhanCong PRIMARY KEY CLUSTERED 
(
	MaPC ASC
)
)
GO
--Tạo bảng PhanLoaiDichVu
CREATE TABLE PhanLoaiDichVu(
	MaPLDV varchar(25) NOT NULL,
	TenDV nvarchar(255) NOT NULL,
 CONSTRAINT PK_PhanLoaiDichVu PRIMARY KEY CLUSTERED 
(
	MaPLDV ASC
)
)
GO
--Tạo bảng PhanLoaiMonAn
CREATE TABLE PhanLoaiMonAn(
	MaPL varchar(25) NOT NULL,
	TenPL nvarchar(50) NOT NULL,
 CONSTRAINT PK_PhanLoaiMonAn PRIMARY KEY CLUSTERED 
(
	MaPL ASC
)
)
GO
--Tạo bảng PhanLoaiSanh
CREATE TABLE PhanLoaiSanh(
	MaPL varchar(25) NOT NULL,
	TenPL nvarchar(50) NOT NULL,
 CONSTRAINT PK_PhanLoaiSanh PRIMARY KEY CLUSTERED 
(
	MaPL ASC
)
)
GO
--Tạo bảng PhongBan
CREATE TABLE PhongBan(
	MaPB varchar(25) NOT NULL,
	TenPB nvarchar(50) NOT NULL,
 CONSTRAINT PK_PhongBan PRIMARY KEY CLUSTERED 
(
	MaPB ASC
)
)
GO
--Tạo bảng Sanh
CREATE TABLE Sanh(
	MaSanh varchar(25) NOT NULL,
	TenSanh nvarchar(50) NOT NULL,
	MaPL varchar(25) NOT NULL,
	SucChua bigint NOT NULL,
	GiaThueSanh bigint NOT NULL,
	GiaBan bigint NOT NULL,
 CONSTRAINT PK_Sanh PRIMARY KEY CLUSTERED 
(
	MaSanh ASC
)
)
GO
--Tạo bảng "TaiKhoan"
CREATE TABLE TaiKhoan(
	MaTaiKhoan int IDENTITY(1,1) NOT NULL,
	MaNhanVien varchar(5) NOT NULL,
	TenDangNhap varchar(50) NOT NULL,
	MatKhau varchar(50) NOT NULL,
	VaiTro varchar(25) NOT NULL,
 CONSTRAINT PK_TaiKhoan PRIMARY KEY CLUSTERED 
(
	MaTaiKhoan ASC
)
)
GO
--Tạo bảng ThucDon
CREATE TABLE ThucDon(
	MaTD varchar(25) NOT NULL,
	TenTD nvarchar(50) NOT NULL,
	GhiChu nvarchar(255) NULL,
	
 CONSTRAINT PK_ThucDon1 PRIMARY KEY CLUSTERED 
(
	MaTD ASC
)
)
GO

GO
--Tạo bảng TrangThaiHopDong
CREATE TABLE TrangThaiHopDong(
	MaTT varchar(25) NOT NULL,
	TenTT nvarchar(50) NOT NULL,
 CONSTRAINT PK_TrangThaiHopDong PRIMARY KEY CLUSTERED 
(
	MaTT ASC
)
)
GO
--Tạo bảng VaiTro
CREATE TABLE VaiTro(
	MaVT varchar(25) NOT NULL,
	MaPB varchar(25) NOT NULL,
	TenVT nvarchar(50) NOT NULL,
 CONSTRAINT PK_VaiTro PRIMARY KEY CLUSTERED 
(
	MaVT ASC
)
)

-- tạo bảng ChiPhiPhatSinh 


GO
--Tạo bảng VaiTroTaiKhoan
CREATE TABLE VaiTroTaiKhoan(
	MaVT varchar(25) NOT NULL,
	TenVT nvarchar(50) NOT NULL,
 CONSTRAINT PK_VaiTroTaiKhoan PRIMARY KEY CLUSTERED 
(
	MaVT ASC
)
)

--Tạo bảng ChiPhiPhatSinh
CREATE TABLE ChiPhiPhatSinh (
	MaHD varchar(5) NOT NULL ,
	MaPLDV varchar(25) NOT NULL,
	ChiPhi bigint NOT NULL,
	LyDo nvarchar(255) NULL,
	 CONSTRAINT PK_ChiPhiPhatSinh PRIMARY KEY CLUSTERED 
(
	MaHD, MaPLDV
)
)
--Tạo bảng ChiTietChiPhiDichVu

GO
--Thêm dữ liệu 
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV,ChiPhi ,ChiPhiPhatSinh, GhiChu) VALUES (N'HD001', N'AOGHEDO', N'TTBANTIEC',0 ,10000, NULL)
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV,ChiPhi ,ChiPhiPhatSinh, GhiChu) VALUES (N'HD001', N'BANGTEN1', N'TTSANKHAU',0, NULL, NULL)
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV,ChiPhi ,ChiPhiPhatSinh, GhiChu) VALUES (N'HD001', N'CONGTRON', N'TTCONG', 0, 10000, NULL)
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV,ChiPhi ,ChiPhiPhatSinh, GhiChu) VALUES (N'HD001', N'GIAGO', N'TTCONG',0, NULL, NULL)
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV,ChiPhi ,ChiPhiPhatSinh, GhiChu) VALUES (N'HD001', N'HOACUCDO', N'TTCONG', 0, NULL, NULL)
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV,ChiPhi ,ChiPhiPhatSinh, GhiChu) VALUES (N'HD001', N'HOACUCDO', N'TTSANKHAU',0, NULL, NULL)
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV,ChiPhi ,ChiPhiPhatSinh, GhiChu) VALUES (N'HD001', N'HOAHONGDO', N'TTBANTIEC',0, NULL, NULL)
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV,ChiPhi ,ChiPhiPhatSinh, GhiChu) VALUES (N'HD001', N'HOAHONGDO', N'TTCONG',0, NULL, NULL)
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV,ChiPhi ,ChiPhiPhatSinh, GhiChu) VALUES (N'HD001', N'HOAHONGDO', N'TTSANKHAU',0, NULL, NULL)
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV,ChiPhi ,ChiPhiPhatSinh, GhiChu) VALUES (N'HD001', N'LIENKHUC1', N'NGHETHUAT', 0,NULL, NULL)
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV,ChiPhi ,ChiPhiPhatSinh, GhiChu) VALUES (N'HD001', N'THAMDO', N'TTSANKHAU',0, NULL, NULL)
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV,ChiPhi ,ChiPhiPhatSinh, GhiChu) VALUES (N'HD001', N'TRAIBANTRANG', N'TTBANTIEC',0, NULL, NULL)
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV,ChiPhi ,ChiPhiPhatSinh, GhiChu) VALUES (N'HD002', N'AOGHEDO', N'TTBANTIEC',0, NULL, NULL)
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV,ChiPhi ,ChiPhiPhatSinh, GhiChu) VALUES (N'HD002', N'BANGTEN1', N'TTSANKHAU',0, NULL, NULL)
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV,ChiPhi ,ChiPhiPhatSinh, GhiChu) VALUES (N'HD002', N'CONGVUONG', N'TTCONG', 0,NULL, NULL)
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV,ChiPhi ,ChiPhiPhatSinh, GhiChu) VALUES (N'HD002', N'GIASAT', N'TTCONG',0, NULL, NULL)
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV,ChiPhi ,ChiPhiPhatSinh, GhiChu) VALUES (N'HD002', N'HOAHONGXANH', N'TTBANTIEC',0, NULL, NULL)
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV,ChiPhi ,ChiPhiPhatSinh, GhiChu) VALUES (N'HD002', N'HOAHONGXANH', N'TTCONG',0, NULL, NULL)
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV,ChiPhi ,ChiPhiPhatSinh, GhiChu) VALUES (N'HD002', N'HOAHONGXANH', N'TTSANKHAU', 0,NULL, NULL)
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV,ChiPhi ,ChiPhiPhatSinh, GhiChu) VALUES (N'HD002', N'HOAHUE', N'TTCONG', 0,NULL, NULL)
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV,ChiPhi ,ChiPhiPhatSinh, GhiChu) VALUES (N'HD002', N'HOAHUE', N'TTSANKHAU',0, NULL, NULL)
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV,ChiPhi ,ChiPhiPhatSinh, GhiChu) VALUES (N'HD002', N'LIENKHUC2', N'NGHETHUAT',0, NULL, NULL)
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV,ChiPhi ,ChiPhiPhatSinh, GhiChu) VALUES (N'HD002', N'THAMCO', N'TTSANKHAU',0, NULL, NULL)
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV,ChiPhi ,ChiPhiPhatSinh, GhiChu) VALUES (N'HD002', N'TRAIBANTRANG', N'TTBANTIEC',0, NULL, NULL)
GO
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, GhiChu) VALUES (N'HD001', N'BANHPLAN', 1, NULL)
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, GhiChu) VALUES (N'HD001', N'BOLAGU', 2, NULL)
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, GhiChu) VALUES (N'HD001', N'CHAOGA', 3, NULL)
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, GhiChu) VALUES (N'HD001', N'CHAOVIT', 4, NULL)
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, GhiChu) VALUES (N'HD001', N'COCACOLA', 9, NULL)
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, GhiChu) VALUES (N'HD001', N'COMCHIENHAISAN', 6, NULL)
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, GhiChu) VALUES (N'HD001', N'HOTVITLON', 7, NULL)
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, GhiChu) VALUES (N'HD001', N'LAUDE', 8, NULL)
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, GhiChu) VALUES (N'HD001', N'NGHEUHAP', 5, NULL)
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, GhiChu) VALUES (N'HD002', N'BIA333', 9, NULL)
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, GhiChu) VALUES (N'HD002', N'CHAOVIT', 2, NULL)
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, GhiChu) VALUES (N'HD002', N'COMCHIENHAISAN', 3, NULL)
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, GhiChu) VALUES (N'HD002', N'GABOXOI', 4, NULL)
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, GhiChu) VALUES (N'HD002', N'GAHAP', 5, NULL)
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, GhiChu) VALUES (N'HD002', N'LAUCABOP', 6, NULL)
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, GhiChu) VALUES (N'HD002', N'NGHEUHAP', 7, NULL)
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, GhiChu) VALUES (N'HD002', N'SUACHUA', 8, NULL)
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, GhiChu) VALUES (N'HD002', N'SUPHAISAN', 1, NULL)
GO
INSERT HopDongDichVu (MaHD, MaDV,MaGoi ,ChiPhi, GhiChu) VALUES (N'HD001', N'NGHETHUAT',NULL ,1200000, NULL)
INSERT HopDongDichVu (MaHD, MaDV, MaGoi ,ChiPhi, GhiChu) VALUES (N'HD001', N'TTBANTIEC',NULL , 4000000, NULL)
INSERT HopDongDichVu (MaHD, MaDV, MaGoi ,ChiPhi, GhiChu) VALUES (N'HD001', N'TTCONG',NULL , 1000000, NULL)
INSERT HopDongDichVu (MaHD, MaDV, MaGoi ,ChiPhi, GhiChu) VALUES (N'HD001', N'TTSANKHAU',NULL , 3000000, NULL)
INSERT HopDongDichVu (MaHD, MaDV, MaGoi ,ChiPhi, GhiChu) VALUES (N'HD002', N'NGHETHUAT',NULL , 1200000, NULL)
INSERT HopDongDichVu (MaHD, MaDV, MaGoi ,ChiPhi, GhiChu) VALUES (N'HD002', N'TTBANTIEC',NULL , 4000000, NULL)
INSERT HopDongDichVu (MaHD, MaDV, MaGoi ,ChiPhi, GhiChu) VALUES (N'HD002', N'TTCONG',NULL , 1000000, NULL)
INSERT HopDongDichVu (MaHD, MaDV, MaGoi ,ChiPhi, GhiChu) VALUES (N'HD002', N'TTSANKHAU',NULL , 3000000, NULL)
GO
INSERT ChiTietDichVuDiKem (MaHD, MaDV, GhiChu, ChiPhiPhatSinh, ChiPhi) VALUES (N'HD001', N'BANHKEM', N'3 tầng', 120000, 0)
INSERT ChiTietDichVuDiKem (MaHD, MaDV, GhiChu, ChiPhiPhatSinh, ChiPhi) VALUES (N'HD001', N'MAYTAOKHOI', NULL, NULL, 0)
INSERT ChiTietDichVuDiKem (MaHD, MaDV, GhiChu, ChiPhiPhatSinh, ChiPhi) VALUES (N'HD001', N'PHAOKIMTUYEN', N'100 cái', 1000000, 0)
INSERT ChiTietDichVuDiKem (MaHD, MaDV, GhiChu, ChiPhiPhatSinh, ChiPhi) VALUES (N'HD002', N'BANHKEM', N'4 tầng', 1000000, 0)
INSERT ChiTietDichVuDiKem (MaHD, MaDV, GhiChu, ChiPhiPhatSinh, ChiPhi) VALUES (N'HD002', N'PHAOKIMTUYEN', N'54 cái', 479999, 0)
INSERT ChiTietDichVuDiKem (MaHD, MaDV, GhiChu, ChiPhiPhatSinh, ChiPhi) VALUES (N'HD002', N'THIENNGABANG', N'2 con màu xanh', 400000, 0)
GO
INSERT ChiTietDVDuaDon (MaHD, MaNV, LoaiXe) VALUES (N'HD001', N'NV002', N'XE17CHO')
INSERT ChiTietDVDuaDon (MaHD, MaNV, LoaiXe) VALUES (N'HD001', N'NV006', N'XE17CHO')
INSERT ChiTietDVDuaDon (MaHD, MaNV, LoaiXe) VALUES (N'HD002', N'NV002', N'XE17CHO')
INSERT ChiTietDVDuaDon (MaHD, MaNV, LoaiXe) VALUES (N'HD002', N'NV006', N'XE17CHO')
GO
INSERT ChiTietGoiDichVu (MaGoi, MaCSVC, GhiChu) VALUES (N'NGHETHUAT1', N'LIENKHUC1', NULL)
INSERT ChiTietGoiDichVu (MaGoi, MaCSVC, GhiChu) VALUES (N'NGHETHUAT1', N'VUDAO1', NULL)
INSERT ChiTietGoiDichVu (MaGoi, MaCSVC, GhiChu) VALUES (N'TTBANGHE1', N'AOGHEDO', NULL)
INSERT ChiTietGoiDichVu (MaGoi, MaCSVC, GhiChu) VALUES (N'TTBANGHE1', N'HOAHONGDO', NULL)
INSERT ChiTietGoiDichVu (MaGoi, MaCSVC, GhiChu) VALUES (N'TTBANGHE1', N'TRAIBANTRANG', NULL)
INSERT ChiTietGoiDichVu (MaGoi, MaCSVC, GhiChu) VALUES (N'TTBANGHE3', N'AOGHEXANH', NULL)
INSERT ChiTietGoiDichVu (MaGoi, MaCSVC, GhiChu) VALUES (N'TTBANGHE3', N'HOAHONGXANH', NULL)
INSERT ChiTietGoiDichVu (MaGoi, MaCSVC, GhiChu) VALUES (N'TTBANGHE3', N'HOAHUE', NULL)
INSERT ChiTietGoiDichVu (MaGoi, MaCSVC, GhiChu) VALUES (N'TTCONG1', N'CONGTRON', N'màu xanh')
INSERT ChiTietGoiDichVu (MaGoi, MaCSVC, GhiChu) VALUES (N'TTCONG1', N'GIASAT', NULL)
INSERT ChiTietGoiDichVu (MaGoi, MaCSVC, GhiChu) VALUES (N'TTCONG1', N'HOACUCDO', NULL)
INSERT ChiTietGoiDichVu (MaGoi, MaCSVC, GhiChu) VALUES (N'TTCONG1', N'HOAHONGDO', NULL)
INSERT ChiTietGoiDichVu (MaGoi, MaCSVC, GhiChu) VALUES (N'TTCONG2', N'GIAGO', NULL)
INSERT ChiTietGoiDichVu (MaGoi, MaCSVC, GhiChu) VALUES (N'TTCONG2', N'HOAHONGXANH', NULL)
INSERT ChiTietGoiDichVu (MaGoi, MaCSVC, GhiChu) VALUES (N'TTCONG2', N'HOAHUE', NULL)
INSERT ChiTietGoiDichVu (MaGoi, MaCSVC, GhiChu) VALUES (N'TTCONG2', N'THAMDO', NULL)
INSERT ChiTietGoiDichVu (MaGoi, MaCSVC, GhiChu) VALUES (N'TTSANKHAU1', N'BANGTEN1', NULL)
INSERT ChiTietGoiDichVu (MaGoi, MaCSVC, GhiChu) VALUES (N'TTSANKHAU1', N'HOACUCDO', NULL)
INSERT ChiTietGoiDichVu (MaGoi, MaCSVC, GhiChu) VALUES (N'TTSANKHAU1', N'HOAHONGDO', NULL)
INSERT ChiTietGoiDichVu (MaGoi, MaCSVC, GhiChu) VALUES (N'TTSANKHAU2', N'BANGTEN1', NULL)
GO
INSERT ChiTietPhanCong (MaPC, MaNV, NgayPC, GioBatDau, GioKetThuc) VALUES (1, N'NV001', CAST(N'2022-01-01' AS Date), CAST(N'05:00:00' AS Time), CAST(N'07:00:00' AS Time))
INSERT ChiTietPhanCong (MaPC, MaNV, NgayPC, GioBatDau, GioKetThuc) VALUES (1, N'NV002', CAST(N'2022-01-01' AS Date), CAST(N'05:00:00' AS Time), CAST(N'07:00:00' AS Time))
INSERT ChiTietPhanCong (MaPC, MaNV, NgayPC, GioBatDau, GioKetThuc) VALUES (1, N'NV003', CAST(N'2022-01-01' AS Date), CAST(N'06:00:00' AS Time), CAST(N'13:00:00' AS Time))
INSERT ChiTietPhanCong (MaPC, MaNV, NgayPC, GioBatDau, GioKetThuc) VALUES (1, N'NV004', CAST(N'2002-01-01' AS Date), CAST(N'05:00:00' AS Time), CAST(N'14:00:00' AS Time))
INSERT ChiTietPhanCong (MaPC, MaNV, NgayPC, GioBatDau, GioKetThuc) VALUES (2, N'NV003', CAST(N'2022-01-01' AS Date), CAST(N'05:00:00' AS Time), CAST(N'17:00:00' AS Time))
INSERT ChiTietPhanCong (MaPC, MaNV, NgayPC, GioBatDau, GioKetThuc) VALUES (2, N'NV005', CAST(N'2022-01-01' AS Date), CAST(N'05:00:00' AS Time), CAST(N'17:00:00' AS Time))
INSERT ChiTietPhanCong (MaPC, MaNV, NgayPC, GioBatDau, GioKetThuc) VALUES (3, N'NV001', CAST(N'2022-01-01' AS Date), CAST(N'05:00:00' AS Time), CAST(N'17:00:00' AS Time))
INSERT ChiTietPhanCong (MaPC, MaNV, NgayPC, GioBatDau, GioKetThuc) VALUES (3, N'NV002', CAST(N'2022-01-01' AS Date), CAST(N'05:00:00' AS Time), CAST(N'17:00:00' AS Time))
INSERT ChiTietPhanCong (MaPC, MaNV, NgayPC, GioBatDau, GioKetThuc) VALUES (3, N'NV003', CAST(N'2022-01-01' AS Date), CAST(N'05:00:00' AS Time), CAST(N'17:00:00' AS Time))
INSERT ChiTietPhanCong (MaPC, MaNV, NgayPC, GioBatDau, GioKetThuc) VALUES (3, N'NV004', CAST(N'2022-01-01' AS Date), CAST(N'05:00:00' AS Time), CAST(N'17:00:00' AS Time))
INSERT ChiTietPhanCong (MaPC, MaNV, NgayPC, GioBatDau, GioKetThuc) VALUES (4, N'NV001', CAST(N'2022-01-01' AS Date), CAST(N'05:00:00' AS Time), CAST(N'17:00:00' AS Time))
INSERT ChiTietPhanCong (MaPC, MaNV, NgayPC, GioBatDau, GioKetThuc) VALUES (4, N'NV002', CAST(N'2022-01-01' AS Date), CAST(N'05:00:00' AS Time), CAST(N'17:00:00' AS Time))
INSERT ChiTietPhanCong (MaPC, MaNV, NgayPC, GioBatDau, GioKetThuc) VALUES (5, N'NV001', CAST(N'2021-08-02' AS Date), CAST(N'05:00:00' AS Time), CAST(N'17:00:00' AS Time))
INSERT ChiTietPhanCong (MaPC, MaNV, NgayPC, GioBatDau, GioKetThuc) VALUES (5, N'NV002', CAST(N'2021-08-02' AS Date), CAST(N'05:00:00' AS Time), CAST(N'17:00:00' AS Time))
INSERT ChiTietPhanCong (MaPC, MaNV, NgayPC, GioBatDau, GioKetThuc) VALUES (5, N'NV003', CAST(N'2021-08-02' AS Date), CAST(N'05:00:00' AS Time), CAST(N'17:00:00' AS Time))
INSERT ChiTietPhanCong (MaPC, MaNV, NgayPC, GioBatDau, GioKetThuc) VALUES (5, N'NV004', CAST(N'2021-08-02' AS Date), CAST(N'05:00:00' AS Time), CAST(N'17:00:00' AS Time))
INSERT ChiTietPhanCong (MaPC, MaNV, NgayPC, GioBatDau, GioKetThuc) VALUES (5, N'NV005', CAST(N'2021-08-02' AS Date), CAST(N'05:00:00' AS Time), CAST(N'17:00:00' AS Time))
GO
INSERT ChiTietThucDon (MaTD, MaMA, ThuTu, GhiChu) VALUES (N'TD001', N'BANHPLAN', 1, NULL)
INSERT ChiTietThucDon (MaTD, MaMA, ThuTu, GhiChu) VALUES (N'TD001', N'BOLAGU', 2, NULL)
INSERT ChiTietThucDon (MaTD, MaMA, ThuTu, GhiChu) VALUES (N'TD001', N'CHAOGA', 3, NULL)
INSERT ChiTietThucDon (MaTD, MaMA, ThuTu, GhiChu) VALUES (N'TD001', N'CHAOVIT', 4, NULL)
INSERT ChiTietThucDon (MaTD, MaMA, ThuTu, GhiChu) VALUES (N'TD001', N'COCACOLA', 9, NULL)
INSERT ChiTietThucDon (MaTD, MaMA, ThuTu, GhiChu) VALUES (N'TD001', N'COMCHIENHAISAN', 6, NULL)
INSERT ChiTietThucDon (MaTD, MaMA, ThuTu, GhiChu) VALUES (N'TD001', N'HOTVITLON', 7, NULL)
INSERT ChiTietThucDon (MaTD, MaMA, ThuTu, GhiChu) VALUES (N'TD001', N'LAUDE', 8, NULL)
INSERT ChiTietThucDon (MaTD, MaMA, ThuTu, GhiChu) VALUES (N'TD001', N'NGHEUHAP', 5, NULL)
INSERT ChiTietThucDon (MaTD, MaMA, ThuTu, GhiChu) VALUES (N'TD002', N'BIA333', 9, NULL)
INSERT ChiTietThucDon (MaTD, MaMA, ThuTu, GhiChu) VALUES (N'TD002', N'CHAOVIT', 2, NULL)
INSERT ChiTietThucDon (MaTD, MaMA, ThuTu, GhiChu) VALUES (N'TD002', N'COMCHIENHAISAN', 3, NULL)
INSERT ChiTietThucDon (MaTD, MaMA, ThuTu, GhiChu) VALUES (N'TD002', N'GABOXOI', 4, NULL)
INSERT ChiTietThucDon (MaTD, MaMA, ThuTu, GhiChu) VALUES (N'TD002', N'GAHAP', 5, NULL)
INSERT ChiTietThucDon (MaTD, MaMA, ThuTu, GhiChu) VALUES (N'TD002', N'LAUCABOP', 6, NULL)
INSERT ChiTietThucDon (MaTD, MaMA, ThuTu, GhiChu) VALUES (N'TD002', N'NGHEUHAP', 7, NULL)
INSERT ChiTietThucDon (MaTD, MaMA, ThuTu, GhiChu) VALUES (N'TD002', N'SUACHUA', 8, NULL)
INSERT ChiTietThucDon (MaTD, MaMA, ThuTu, GhiChu) VALUES (N'TD002', N'SUPHAISAN', 1, NULL)
INSERT ChiTietThucDon (MaTD, MaMA, ThuTu, GhiChu) VALUES (N'TD003', N'BOCUBE', 1, NULL)
INSERT ChiTietThucDon (MaTD, MaMA, ThuTu, GhiChu) VALUES (N'TD003', N'BOLUCLAC', 2, NULL)
INSERT ChiTietThucDon (MaTD, MaMA, ThuTu, GhiChu) VALUES (N'TD003', N'CANGCUABACHHOA', 3, NULL)
INSERT ChiTietThucDon (MaTD, MaMA, ThuTu, GhiChu) VALUES (N'TD003', N'CARIGA', 4, NULL)
INSERT ChiTietThucDon (MaTD, MaMA, ThuTu, GhiChu) VALUES (N'TD003', N'CUAHOANGDE', 5, NULL)
INSERT ChiTietThucDon (MaTD, MaMA, ThuTu, GhiChu) VALUES (N'TD003', N'GAQUAYNGUVI', 6, NULL)
INSERT ChiTietThucDon (MaTD, MaMA, ThuTu, GhiChu) VALUES (N'TD003', N'LAUCABOP', 7, NULL)
INSERT ChiTietThucDon (MaTD, MaMA, ThuTu, GhiChu) VALUES (N'TD003', N'RUOUVANG', 8, NULL)
INSERT ChiTietThucDon (MaTD, MaMA, ThuTu, GhiChu) VALUES (N'TD003', N'TOYEN', 9, NULL)
GO
INSERT CoSoVatChat (MaCSVC, TenCSVC, MaDMC, SoLuong, GiaThue,GhiChu) VALUES (N'AOGHEDO', N'Áo ghế đỏ', N'AOGHE', 1000,30000 ,NULL)
INSERT CoSoVatChat (MaCSVC, TenCSVC, MaDMC, SoLuong, GiaThue,GhiChu) VALUES (N'AOGHEXANH', N'Áo ghế xanh', N'AOGHE', 1000, 40000, NULL)
INSERT CoSoVatChat (MaCSVC, TenCSVC, MaDMC, SoLuong, GiaThue,GhiChu) VALUES (N'BANGTEN1', N'Bảng tên tròn', N'BANGTEN', 0,500000 ,NULL)
INSERT CoSoVatChat (MaCSVC, TenCSVC, MaDMC, SoLuong, GiaThue,GhiChu) VALUES (N'CONGDEN', N'Cổng đen', N'CONG', 1,1000000 ,NULL)
INSERT CoSoVatChat (MaCSVC, TenCSVC, MaDMC, SoLuong, GiaThue,GhiChu) VALUES (N'CONGOVAN', N'Cổng Ovan', N'CONG', 20,1200000, NULL)
INSERT CoSoVatChat (MaCSVC, TenCSVC, MaDMC, SoLuong, GiaThue,GhiChu) VALUES (N'CONGTRON', N'Cổng tròn xanh', N'CONG', 3,1300000, N'không có')
INSERT CoSoVatChat (MaCSVC, TenCSVC, MaDMC, SoLuong, GiaThue,GhiChu) VALUES (N'CONGVUONG', N'Cổng vuông', N'CONG', 10,1000000, NULL)
INSERT CoSoVatChat (MaCSVC, TenCSVC, MaDMC, SoLuong, GiaThue,GhiChu) VALUES (N'GIAGO', N'Giá gỗ', N'GIA', 10,500000, NULL)
INSERT CoSoVatChat (MaCSVC, TenCSVC, MaDMC, SoLuong, GiaThue,GhiChu) VALUES (N'GIASAT', N'Giá sắt', N'GIA', 10,600000, NULL)
INSERT CoSoVatChat (MaCSVC, TenCSVC, MaDMC, SoLuong, GiaThue,GhiChu) VALUES (N'HOACUCDO', N'Hoa cúc', N'HOAPHUDAO', 100,100000, NULL)
INSERT CoSoVatChat (MaCSVC, TenCSVC, MaDMC, SoLuong, GiaThue,GhiChu) VALUES (N'HOAHONGDO', N'Hoa hồng', N'HOACHUDAO', 10,200000, NULL)
INSERT CoSoVatChat (MaCSVC, TenCSVC, MaDMC, SoLuong, GiaThue,GhiChu) VALUES (N'HOAHONGXANH', N'Hoa hồng xanh', N'HOACHUDAO', 30,300000, NULL)
INSERT CoSoVatChat (MaCSVC, TenCSVC, MaDMC, SoLuong, GiaThue,GhiChu) VALUES (N'HOAHUE', N'Hoa huệ', N'HOAPHUDAO', 10,100000 ,NULL)
INSERT CoSoVatChat (MaCSVC, TenCSVC, MaDMC, SoLuong, GiaThue,GhiChu) VALUES (N'LIENKHUC1', N'Liên khúc nhạc nhẹ', N'LIENKHUC', 0,1000000 ,NULL)
INSERT CoSoVatChat (MaCSVC, TenCSVC, MaDMC, SoLuong, GiaThue,GhiChu) VALUES (N'LIENKHUC2', N'Liên khúc nhạc soi động', N'LIENKHUC', 0,1000000, NULL)
INSERT CoSoVatChat (MaCSVC, TenCSVC, MaDMC, SoLuong, GiaThue,GhiChu) VALUES (N'PHAOHOANHO', N'Pháo hoa nhỏ', N'PHAOHOA', 300,100000 ,N'pháo hoa màu đỏ')
INSERT CoSoVatChat (MaCSVC, TenCSVC, MaDMC, SoLuong, GiaThue,GhiChu) VALUES (N'THAMCO', N'Thảm cỏ', N'THAM', 5,500000 ,NULL)
INSERT CoSoVatChat (MaCSVC, TenCSVC, MaDMC, SoLuong, GiaThue,GhiChu) VALUES (N'THAMDO', N'Thảm đỏ', N'THAM', 5,500000, NULL)
INSERT CoSoVatChat (MaCSVC, TenCSVC, MaDMC, SoLuong, GiaThue,GhiChu) VALUES (N'TRAIBANTRANG', N'Trãi bàn trắng', N'TRAIBAN', 300,50000, NULL)
INSERT CoSoVatChat (MaCSVC, TenCSVC, MaDMC, SoLuong, GiaThue,GhiChu) VALUES (N'TRAIBANVANG', N'Trãi bàn vàng', N'TRAIBAN', 200,60000, NULL)
INSERT CoSoVatChat (MaCSVC, TenCSVC, MaDMC, SoLuong, GiaThue,GhiChu) VALUES (N'VUDAO1', N'Vũ đạo nhẹ nhàng', N'VUDAO', 0, 1000000,NULL)
INSERT CoSoVatChat (MaCSVC, TenCSVC, MaDMC, SoLuong, GiaThue,GhiChu) VALUES (N'VUDAO2', N'Vũ đạo sôi động ', N'VUDAO', 0, 1000000,NULL)
INSERT CoSoVatChat (MaCSVC, TenCSVC, MaDMC, SoLuong, GiaThue,GhiChu) VALUES (N'XE17CHO', N'Xe 17 chỗ', N'XEKHACH', 3, 0,N'Xe honda')
INSERT CoSoVatChat (MaCSVC, TenCSVC, MaDMC, SoLuong, GiaThue,GhiChu) VALUES (N'XE5CHO', N'Xe 5 chỗ', N'XEKHACH', 2, 0,N'xe luxury porcheso')
GO
INSERT DanhMuc (MaDM, TenDM) VALUES (N'BANGHE', N'Bàn Ghế')
INSERT DanhMuc (MaDM, TenDM) VALUES (N'DIENTU', N'Điện tử')
INSERT DanhMuc (MaDM, TenDM) VALUES (N'GIADUNG', N'Đồ gia dụng')
INSERT DanhMuc (MaDM, TenDM) VALUES (N'NGHETHUAT', N'Nghệ Thuật')
INSERT DanhMuc (MaDM, TenDM) VALUES (N'TRANGPHUC', N'Trang phục')
INSERT DanhMuc (MaDM, TenDM) VALUES (N'TRANGTRI', N'Trang trí')
INSERT DanhMuc (MaDM, TenDM) VALUES (N'VANCHUYEN', N'Xe vận chuyển')
GO
INSERT DanhMucCon (MaDMC, MaDM, TenDM) VALUES (N'AODAI', N'TRANGPHUC', N'Áo dài')
INSERT DanhMucCon (MaDMC, MaDM, TenDM) VALUES (N'AOGHE', N'TRANGTRI', N'Áo ghế')
INSERT DanhMucCon (MaDMC, MaDM, TenDM) VALUES (N'BAN', N'BANGHE', N'Bàn')
INSERT DanhMucCon (MaDMC, MaDM, TenDM) VALUES (N'BANGTEN', N'TRANGTRI', N'Bảng tên')
INSERT DanhMucCon (MaDMC, MaDM, TenDM) VALUES (N'CONG', N'TRANGTRI', N'Cổng')
INSERT DanhMucCon (MaDMC, MaDM, TenDM) VALUES (N'DAM', N'TRANGPHUC', N'Đầm')
INSERT DanhMucCon (MaDMC, MaDM, TenDM) VALUES (N'GHE', N'BANGHE', N'Ghế')
INSERT DanhMucCon (MaDMC, MaDM, TenDM) VALUES (N'GIA', N'TRANGTRI', N'Giá để ảnh')
INSERT DanhMucCon (MaDMC, MaDM, TenDM) VALUES (N'HOACHUDAO', N'TRANGTRI', N'Hoa chủ đạo')
INSERT DanhMucCon (MaDMC, MaDM, TenDM) VALUES (N'HOAPHUDAO', N'TRANGTRI', N'Hoa phụ đạo')
INSERT DanhMucCon (MaDMC, MaDM, TenDM) VALUES (N'LIENKHUC', N'NGHETHUAT', N'Bài hát')
INSERT DanhMucCon (MaDMC, MaDM, TenDM) VALUES (N'PHAOHOA', N'TRANGTRI', N'Pháo hoa')
INSERT DanhMucCon (MaDMC, MaDM, TenDM) VALUES (N'Soiree', N'TRANGPHUC', N'Váy Soiree')
INSERT DanhMucCon (MaDMC, MaDM, TenDM) VALUES (N'THAM', N'TRANGTRI', N'Thảm ')
INSERT DanhMucCon (MaDMC, MaDM, TenDM) VALUES (N'TRAIBAN', N'TRANGTRI', N'Thảm trãi bàn')
INSERT DanhMucCon (MaDMC, MaDM, TenDM) VALUES (N'VUDAO', N'NGHETHUAT', N'Vũ Đạo')
INSERT DanhMucCon (MaDMC, MaDM, TenDM) VALUES (N'XECUOI', N'VANCHUYEN', N'Xe cưới')
INSERT DanhMucCon (MaDMC, MaDM, TenDM) VALUES (N'XEKHACH', N'VANCHUYEN', N'Xe khách')
INSERT DanhMucCon (MaDMC, MaDM, TenDM) VALUES (N'XETAI', N'VANCHUYEN', N'Xe tải')
GO
INSERT DichVu (MaDV, MaPLDV, TenDV) VALUES (N'DATMON', N'DATMON', N'Đặt món')
INSERT DichVu (MaDV, MaPLDV, TenDV) VALUES (N'DUADON', N'DUADON', N'Đưa đón')
INSERT DichVu (MaDV, MaPLDV, TenDV) VALUES (N'NGHETHUAT', N'NGHETHUAT', N'Nghệ thuật')
INSERT DichVu (MaDV, MaPLDV, TenDV) VALUES (N'TTBANTIEC', N'TRANGTRI', N'Trang trí bàn ghế')
INSERT DichVu (MaDV, MaPLDV, TenDV) VALUES (N'TTCONG', N'TRANGTRI', N'Trang trí cổng')
INSERT DichVu (MaDV, MaPLDV, TenDV) VALUES (N'TTSANKHAU', N'TRANGTRI', N'Trang trí sân khấu')
GO
INSERT DichVuDatMon (MaHD,MaTD, ChiPhi, GhiChu) VALUES (N'HD001',NULL, 20000000, NULL)
INSERT DichVuDatMon (MaHD,MaTD, ChiPhi, GhiChu) VALUES (N'HD002',NULL, 32000000, NULL)
GO
INSERT DichVuDiKem (MaDV, TenDV, Gia) VALUES (N'BANHKEM', N'Bánh kem ', 600000000)
INSERT DichVuDiKem (MaDV, TenDV, Gia) VALUES (N'MAYTAOKHOI', N'Máy tạo khói', 2000000)
INSERT DichVuDiKem (MaDV, TenDV, Gia) VALUES (N'PHAOKIMTUYEN', N'Pháo kim tuyến', 1000000)
INSERT DichVuDiKem (MaDV, TenDV, Gia) VALUES (N'THIENNGABANG', N'Thiên nga băng', 900000000)
GO
INSERT DichVuDuaDon (MaHD, NgayKhoiHanh, GioKhoiHanh, DiaDiem, GioVe, SoLuongKhach, GhiChu) VALUES (N'HD001', CAST(N'2022-01-10' AS Date), CAST(N'05:00:00' AS Time), N'Cần Thơ', CAST(N'15:00:00' AS Time), 20, N'có thể tăng')
INSERT DichVuDuaDon (MaHD, NgayKhoiHanh, GioKhoiHanh, DiaDiem, GioVe, SoLuongKhach, GhiChu) VALUES (N'HD002', CAST(N'2022-01-12' AS Date), CAST(N'05:00:00' AS Time), N'Cần Thơ', CAST(N'13:00:00' AS Time), 23, N'có thể tăng')
GO
INSERT GoiDichVu (MaGoi, MaDV, TenGoi, ChiPhi, GhiChu, HinhAnh) VALUES (N'NGHETHUAT1', N'NGHETHUAT', N'Nghệ thuật soi động', N'12000000  ', NULL, NULL)
INSERT GoiDichVu (MaGoi, MaDV, TenGoi, ChiPhi, GhiChu, HinhAnh) VALUES (N'TTBANGHE1', N'TTBANTIEC', N'Bàn xanh ghế trắng', N'300000    ', NULL, NULL)
INSERT GoiDichVu (MaGoi, MaDV, TenGoi, ChiPhi, GhiChu, HinhAnh) VALUES (N'TTBANGHE3', N'TTBANTIEC', N'Bàn trắng ghế đỏ', N'300000    ', NULL, NULL)
INSERT GoiDichVu (MaGoi, MaDV, TenGoi, ChiPhi, GhiChu, HinhAnh) VALUES (N'TTCONG1', N'TTCONG', N'Cổng hoa hồng đỏ', N'10000000  ', N'hoa thật', NULL)
INSERT GoiDichVu (MaGoi, MaDV, TenGoi, ChiPhi, GhiChu, HinhAnh) VALUES (N'TTCONG2', N'TTCONG', N'Cổng hoa hồng đỏ', N'3000000   ', N'hoa fa ke', NULL)
INSERT GoiDichVu (MaGoi, MaDV, TenGoi, ChiPhi, GhiChu, HinhAnh) VALUES (N'TTSANKHAU1', N'TTSANKHAU', N'Sân khấu nhiều hoa', N'3000000   ', N'gì đó', NULL)
INSERT GoiDichVu (MaGoi, MaDV, TenGoi, ChiPhi, GhiChu, HinhAnh) VALUES (N'TTSANKHAU2', N'TTSANKHAU', N'Sân khấu bự', N'5000000   ', N'có kim tuyến....', NULL)
GO
--SET IDENTITY_INSERT HoaDon ON 

INSERT HoaDon ( MaHD, NgayLap, MaNV,TrangTriCong, TrangTriBan, TrangTriSanKhau, DiKem,NgheThuat,ThucDon,TienCoc,PhatSinh,  TongTien,TrangThai) VALUES ( N'HD001', CAST(N'2022-01-01' AS Date), N'NV001',1000000, 4000000 ,3000000,1000000,1200000,100000,55000000,0, 110000000,0)
--SET IDENTITY_INSERT HoaDon OFF
GO
INSERT HopDong (MaHD, MaNL, SoLuongBan, Sanh, NgayLap, NgayDuyet, MaND, MaKH, NgayToChuc, ThoiGianBatDau, ThoiGianKetThuc, TrangThai, The, TienCoc, ChiPhiPhatSinh, TongTien) VALUES (N'HD001', N'NV001', 10, N'SANH01', CAST(N'2022-01-01' AS Date), CAST(N'2022-01-01' AS Date), N'NV001', 1, CAST(N'2022-01-10' AS Date), CAST(N'05:00:00' AS Time), CAST(N'07:00:00' AS Time), N'DATHUCHIEN', 10, 10000000, 1500000, 100000000)
INSERT HopDong (MaHD, MaNL, SoLuongBan, Sanh, NgayLap, NgayDuyet, MaND, MaKH, NgayToChuc, ThoiGianBatDau, ThoiGianKetThuc, TrangThai, The, TienCoc, ChiPhiPhatSinh, TongTien) VALUES (N'HD002', N'NV003', 10, N'SANH02', CAST(N'2022-01-05' AS Date), CAST(N'2022-01-05' AS Date), N'NV001', 1, CAST(N'2022-01-12' AS Date), CAST(N'05:00:00' AS Time), CAST(N'07:00:00' AS Time), N'THUCHIEN', 10, 15000000, 0, 150000000)
INSERT HopDong (MaHD, MaNL, SoLuongBan, Sanh, NgayLap, NgayDuyet, MaND, MaKH, NgayToChuc, ThoiGianBatDau, ThoiGianKetThuc, TrangThai, The, TienCoc, ChiPhiPhatSinh, TongTien) VALUES (N'HD003', N'NV003', 20, N'SANH03', CAST(N'2022-05-11' AS Date), CAST(N'2022-05-11' AS Date), N'NV001', 2, CAST(N'2022-05-20' AS Date), CAST(N'06:00:00' AS Time), CAST(N'13:00:00' AS Time), N'CHODUYET', 15, 40000000, 0, 400000000)
INSERT HopDong (MaHD, MaNL, SoLuongBan, Sanh, NgayLap, NgayDuyet, MaND, MaKH, NgayToChuc, ThoiGianBatDau, ThoiGianKetThuc, TrangThai, The, TienCoc, ChiPhiPhatSinh, TongTien) VALUES (N'HD004', N'NV003', 10, N'SANH01', CAST(N'2022-08-01' AS Date), NULL, N'', 2, CAST(N'2022-08-08' AS Date), CAST(N'07:00:00' AS Time), CAST(N'14:00:00' AS Time), N'CHODUYET', 10, 30000000, 0, 300000000)
INSERT HopDong (MaHD, MaNL, SoLuongBan, Sanh, NgayLap, NgayDuyet, MaND, MaKH, NgayToChuc, ThoiGianBatDau, ThoiGianKetThuc, TrangThai, The, TienCoc, ChiPhiPhatSinh, TongTien) VALUES (N'HD005', N'NV003', 10, N'SANH02', CAST(N'2021-08-02' AS Date), NULL, NULL, 3, CAST(N'2021-08-10' AS Date), CAST(N'07:00:00' AS Time), CAST(N'17:00:00' AS Time), N'CHODUYET', 10, 38000000, 0, 380000000)
GO
--SET IDENTITY_INSERT KhachHang ON 

INSERT KhachHang ( HoTen, SoDienThoai, CCCD, DiaChi, HoTenCoDau, HoTenChuRe) VALUES ( N'Lê Phước Thịnh', N'0334831013', N'02245252654', N'Cần Thơ', N'Lê Thị B', N'Nguyễn Văn A')
INSERT KhachHang ( HoTen, SoDienThoai, CCCD, DiaChi, HoTenCoDau, HoTenChuRe) VALUES ( N'Tô Văn Tấn', N'0593822432', N'04562523433', N'Cần Thơ', N'Huyền Thị C', N'Nguyễn Văn B')
INSERT KhachHang (HoTen, SoDienThoai, CCCD, DiaChi, HoTenCoDau, HoTenChuRe) VALUES ( N'Nguyễn Hồng Quang', N'0315997562', N'0541552668952', N'Cần Thơ', N'Thi Hà', N'Nguyễn Hồng Quang')
INSERT KhachHang ( HoTen, SoDienThoai, CCCD, DiaChi, HoTenCoDau, HoTenChuRe) VALUES ( N'Mai Quốc Bảo', N'0254362584632', N'0354224487878', N'Cần Thơ', N'Hồ Anh Mai', N'Nguyễn Minh Bữu')
INSERT KhachHang ( HoTen, SoDienThoai, CCCD, DiaChi, HoTenCoDau, HoTenChuRe) VALUES (N'trần hoàng toàn', N'035922154687', N'04687985', N'Cần Thơ ', N'Na Bí Bồ', N'Hảo Si Mê')
--SET IDENTITY_INSERT KhachHang OFF
GO
INSERT MonAn (MaMA, TenMA, HinhAnh, GiaTien, MaPL) VALUES (N'7UP', N'7up', N'abv.png', N'180000', N'NUOC')
INSERT MonAn (MaMA, TenMA, HinhAnh, GiaTien, MaPL) VALUES (N'AQUA', N'aqua', N'ass.png', N'100000', N'NUOC')
INSERT MonAn (MaMA, TenMA, HinhAnh, GiaTien, MaPL) VALUES (N'BANHMOCHI', N'bánh mochi Nhật', N'asdc.png', N'480000', N'TRANGMIENG')
INSERT MonAn (MaMA, TenMA, HinhAnh, GiaTien, MaPL) VALUES (N'BANHPLAN', N'Bánh Flan', N'noimg.png', N'10000', N'TRANGMIENG')
INSERT MonAn (MaMA, TenMA, HinhAnh, GiaTien, MaPL) VALUES (N'BIA333', N'Bia 333', N'noimg.png', N'250000', N'NUOC')
INSERT MonAn (MaMA, TenMA, HinhAnh, GiaTien, MaPL) VALUES (N'BOCUBE', N'bò cube', N'asd.png', N'1700000', N'MONCHINH')
INSERT MonAn (MaMA, TenMA, HinhAnh, GiaTien, MaPL) VALUES (N'BOLAGU', N'Bò nấu lagu ', N'mas.png', N'670000', N'MONCHINH')
INSERT MonAn (MaMA, TenMA, HinhAnh, GiaTien, MaPL) VALUES (N'BOLUCLAC', N'bò lúc lắc sốt tiêu', N'asdcm.png', N'900000', N'MONPHU')
INSERT MonAn (MaMA, TenMA, HinhAnh, GiaTien, MaPL) VALUES (N'CANGCUABACHHOA', N'càng cua bách hoa', N'asd.png', N'700000', N'KHAIVI')
INSERT MonAn (MaMA, TenMA, HinhAnh, GiaTien, MaPL) VALUES (N'CARIGA', N'cà ri gà', N'asc.png', N'400000', N'MONPHU')
INSERT MonAn (MaMA, TenMA, HinhAnh, GiaTien, MaPL) VALUES (N'CHAOGA', N'Cháo gà', N'noumg.png', N'150000', N'KHAIVI')
INSERT MonAn (MaMA, TenMA, HinhAnh, GiaTien, MaPL) VALUES (N'CHAOVIT', N'Cháo vịt', N'noimg.png', N'300000', N'MONCHINH')
INSERT MonAn (MaMA, TenMA, HinhAnh, GiaTien, MaPL) VALUES (N'CHEHATSEN', N'Chè dưỡng nhan hạt sen', N'asc.png', N'200000', N'TRANGMIENG')
INSERT MonAn (MaMA, TenMA, HinhAnh, GiaTien, MaPL) VALUES (N'COCACOLA', N'Nước Cocacola', N'noimg', N'200000', N'NUOC')
INSERT MonAn (MaMA, TenMA, HinhAnh, GiaTien, MaPL) VALUES (N'COMCHIENHAISAN', N'Cơm chiên hải sản', N'masd.png', N'70000', N'MONPHU')
INSERT MonAn (MaMA, TenMA, HinhAnh, GiaTien, MaPL) VALUES (N'CUAHOANGDE', N'cua hoàng đế hấp rượu', N'xcxc.png', N'28000000', N'MONCHINH')
INSERT MonAn (MaMA, TenMA, HinhAnh, GiaTien, MaPL) VALUES (N'GABOXOI', N'Gà bó xôi chiên', N'asc.png', N'280000', N'MONPHU')
INSERT MonAn (MaMA, TenMA, HinhAnh, GiaTien, MaPL) VALUES (N'GAHAP', N'Gà hấp bẹ cải xanh', N'asd.pngs', N'450000', N'MONCHINH')
INSERT MonAn (MaMA, TenMA, HinhAnh, GiaTien, MaPL) VALUES (N'GAQUAYNGUVI', N'gà quay ngũ vị', N'asmcd.png', N'15500000', N'MONPHU')
INSERT MonAn (MaMA, TenMA, HinhAnh, GiaTien, MaPL) VALUES (N'GOIBOBOP', N'gỏi bò bóp thấu', N'asd.png', N'120000', N'KHAIVI')
INSERT MonAn (MaMA, TenMA, HinhAnh, GiaTien, MaPL) VALUES (N'GOIDUDU', N'gỏi đu đủ', N'nas.png', N'90000', N'KHAIVI')
INSERT MonAn (MaMA, TenMA, HinhAnh, GiaTien, MaPL) VALUES (N'HEOQUAY', N'Heo quay', N'bs.png', N'1600000', N'MONPHU')
INSERT MonAn (MaMA, TenMA, HinhAnh, GiaTien, MaPL) VALUES (N'HOTVITLON', N'Hột vịt lộn', N'noimg.png', N'10000', N'MONPHU')
INSERT MonAn (MaMA, TenMA, HinhAnh, GiaTien, MaPL) VALUES (N'LAUCABOP', N'lẫu cá bớp', N'acv.png', N'2100000', N'MONCHINH')
INSERT MonAn (MaMA, TenMA, HinhAnh, GiaTien, MaPL) VALUES (N'LAUDE', N'Lẩu dê', N'noima.png', N'1200000', N'MONCHINH')
INSERT MonAn (MaMA, TenMA, HinhAnh, GiaTien, MaPL) VALUES (N'NGHEUHAP', N'Nghêu hấp thái', N'lsk.png', N'130000', N'MONPHU')
INSERT MonAn (MaMA, TenMA, HinhAnh, GiaTien, MaPL) VALUES (N'PEPSI', N'pepsi', N'amd.png', N'200000', N'NUOC')
INSERT MonAn (MaMA, TenMA, HinhAnh, GiaTien, MaPL) VALUES (N'RAUCAU', N'rau cau', N'mas.png', N'320000', N'TRANGMIENG')
INSERT MonAn (MaMA, TenMA, HinhAnh, GiaTien, MaPL) VALUES (N'RUOUVANG', N'rượu vang', N'vcvd.png', N'12000000', N'NUOC')
INSERT MonAn (MaMA, TenMA, HinhAnh, GiaTien, MaPL) VALUES (N'SUACHUA', N'sữa chua', N'aw.png', N'450000', N'TRANGMIENG')
INSERT MonAn (MaMA, TenMA, HinhAnh, GiaTien, MaPL) VALUES (N'SUPBAPCUA', N'súp bắp cua', N'nasd.png', N'150000', N'KHAIVI')
INSERT MonAn (MaMA, TenMA, HinhAnh, GiaTien, MaPL) VALUES (N'SUPHAISAN', N'súp hải sản', N'sad.png', N'200000', N'KHAIVI')
INSERT MonAn (MaMA, TenMA, HinhAnh, GiaTien, MaPL) VALUES (N'TOYEN', N'tổ yến', N'acc.png', N'550000', N'TRANGMIENG')
GO
INSERT NhanVien (MaNV, HoTen, NgaySinh, GioiTinh, SoDienThoai,Email, CCCD_CMND, HinhAnh, MaPB, MaVT, TrangThai) VALUES (N'NV001', N'Lê Phước Thịnh', CAST(N'2003-12-08' AS Date), 1, N'0334831013','thinhlpc0231@gmail.com', N'5223252452452', N'noimg', N'QUANLY', N'QLCC', 1)
INSERT NhanVien (MaNV, HoTen, NgaySinh, GioiTinh, SoDienThoai,Email, CCCD_CMND, HinhAnh, MaPB, MaVT, TrangThai) VALUES (N'NV002', N'Tô Văn Tấn', CAST(N'2003-01-01' AS Date), 1, N'0734238290', 'tovantan@gmail.com',N'5223252452452', N'noimag', N'VANCHUYEN', N'TAIXE', 1)
INSERT NhanVien (MaNV, HoTen, NgaySinh, GioiTinh, SoDienThoai,Email, CCCD_CMND, HinhAnh, MaPB, MaVT, TrangThai) VALUES (N'NV003', N'Phạm Xuân Vinh', CAST(N'2003-01-01' AS Date), 1, N'0907778028','phamxuanvinh@gmail.com', N'9283735736412', N'noimg', N'TIEPTAN', N'TIEPTAN', 1)
INSERT NhanVien (MaNV, HoTen, NgaySinh, GioiTinh, SoDienThoai,Email, CCCD_CMND, HinhAnh, MaPB, MaVT, TrangThai) VALUES (N'NV004', N'Mai Quốc Bảo', CAST(N'2003-01-01' AS Date), 1, N'0984722028', 'maiquocbao@gmail.com',N'47285192831952', N'noumg', N'KETOAN', N'TINHHOADON', 1)
INSERT NhanVien (MaNV, HoTen, NgaySinh, GioiTinh, SoDienThoai,Email, CCCD_CMND, HinhAnh, MaPB, MaVT, TrangThai) VALUES (N'NV005', N'Nguyễn Hồng Quang', CAST(N'2003-01-01' AS Date), 1, N'0708578029','nguyenhongquang@gmail.com', N'1679452302159', N'noimg', N'CSVC', N'KHO', 1)
INSERT NhanVien (MaNV, HoTen, NgaySinh, GioiTinh, SoDienThoai,Email, CCCD_CMND, HinhAnh, MaPB, MaVT, TrangThai) VALUES (N'NV006', N'Nguyễn Văn A', CAST(N'2003-01-01' AS Date), 1, N'0453456345','nguyenvana@gmail.com', N'43654734556', N'noimg', N'VANCHUYEN', N'TAIXE', 1)
GO

SET IDENTITY_INSERT PhanCong OFF 
INSERT PhanCong (MaHD, MaNguoiPC) VALUES ( N'HD001', N'NV001')
INSERT PhanCong ( MaHD, MaNguoiPC) VALUES ( N'HD002', N'NV002')
INSERT PhanCong ( MaHD, MaNguoiPC) VALUES ( N'HD003', N'NV001')
INSERT PhanCong ( MaHD, MaNguoiPC) VALUES ( N'HD004', N'NV001')
INSERT PhanCong ( MaHD, MaNguoiPC) VALUES ( N'HD005', N'NV001')

GO
INSERT PhanLoaiDichVu (MaPLDV, TenDV) VALUES (N'DATMON', N'Đặt món')
INSERT PhanLoaiDichVu (MaPLDV, TenDV) VALUES (N'DUADON', N'Đưa đón')
INSERT PhanLoaiDichVu (MaPLDV, TenDV) VALUES (N'NGHETHUAT', N'Nghệ thuật')
INSERT PhanLoaiDichVu (MaPLDV, TenDV) VALUES (N'TRANGTRI', N'Trang trí')
GO
INSERT PhanLoaiMonAn (MaPL, TenPL) VALUES (N'KHAIVI', N'Khai vị')
INSERT PhanLoaiMonAn (MaPL, TenPL) VALUES (N'MONCHINH', N'Món chính')
INSERT PhanLoaiMonAn (MaPL, TenPL) VALUES (N'MONPHU', N'Món phụ')
INSERT PhanLoaiMonAn (MaPL, TenPL) VALUES (N'NUOC', N'Nước')
INSERT PhanLoaiMonAn (MaPL, TenPL) VALUES (N'TRANGMIENG', N'Tráng miệng')
GO
INSERT PhanLoaiSanh (MaPL, TenPL) VALUES (N'CAOCAP', N'Cao cấp')
INSERT PhanLoaiSanh (MaPL, TenPL) VALUES (N'DONGIAN', N'Đơn Giản')
INSERT PhanLoaiSanh (MaPL, TenPL) VALUES (N'TRUNGBINH', N'Trung Bình')
GO
INSERT PhongBan (MaPB, TenPB) VALUES (N'BAOVE', N'Bảo vệ')
INSERT PhongBan (MaPB, TenPB) VALUES (N'CSVC', N'Cơ sở vật chất')
INSERT PhongBan (MaPB, TenPB) VALUES (N'KETOAN', N'Kế Toán')
INSERT PhongBan (MaPB, TenPB) VALUES (N'KYTHUAT', N'Kỹ Thuật')
INSERT PhongBan (MaPB, TenPB) VALUES (N'NGHETHUAT', N'Nghệ thuật')
INSERT PhongBan (MaPB, TenPB) VALUES (N'PHUCVU', N'Phục vụ')
INSERT PhongBan (MaPB, TenPB) VALUES (N'TIEPTAN', N'Tiếp tân')
INSERT PhongBan (MaPB, TenPB) VALUES (N'VANCHUYEN', N'Vận chuyển')
INSERT PhongBan (MaPB, TenPB) VALUES (N'YTE', N'Y tế')
INSERT PhongBan (MaPB, TenPB) VALUES (N'QUANLY', N'Quản lý')
GO
INSERT Sanh (MaSanh, TenSanh, MaPL, SucChua, GiaThueSanh, GiaBan) VALUES (N'SANH01', N'Sảnh 01', N'DONGIAN', 100, 5000000, 300000)
INSERT Sanh (MaSanh, TenSanh, MaPL, SucChua, GiaThueSanh, GiaBan) VALUES (N'SANH02', N'Sảnh 02', N'TRUNGBINH', 150, 7000000, 435000)
INSERT Sanh (MaSanh, TenSanh, MaPL, SucChua, GiaThueSanh, GiaBan) VALUES (N'SANH03', N'Sảnh 03', N'CAOCAP', 200, 18000000, 840000)
INSERT Sanh (MaSanh, TenSanh, MaPL, SucChua, GiaThueSanh, GiaBan) VALUES (N'SANH04', N'Sảnh 04', N'TRUNGBINH', 130, 5400000, 410000)
INSERT Sanh (MaSanh, TenSanh, MaPL, SucChua, GiaThueSanh, GiaBan) VALUES (N'SANH05', N'Sảnh 05', N'DONGIAN', 120, 6000000, 350000)
GO
--SET IDENTITY_INSERT TaiKhoan ON 

INSERT TaiKhoan ( MaNhanVien, TenDangNhap, MatKhau, VaiTro) VALUES ( N'NV001', N'ThinhLP', N'123456', N'QLCC')
INSERT TaiKhoan ( MaNhanVien, TenDangNhap, MatKhau, VaiTro) VALUES ( N'NV002', N'VinhPX', N'123456', N'KHO')
INSERT TaiKhoan ( MaNhanVien, TenDangNhap, MatKhau, VaiTro) VALUES ( N'NV003', N'QuangNH', N'123', N'TIEPTAN')
INSERT TaiKhoan ( MaNhanVien, TenDangNhap, MatKhau, VaiTro) VALUES ( N'NV001', N'admin', N'admin', N'ADMIN')
--SET IDENTITY_INSERT TaiKhoan OFF
GO
INSERT ThucDon (MaTD, TenTD, GhiChu) VALUES (N'TD001', N'Thực đơn bình dân', N'') 
INSERT ThucDon (MaTD, TenTD, GhiChu) VALUES (N'TD002', N'Thực trung lưu', NULL)
INSERT ThucDon (MaTD, TenTD, GhiChu) VALUES (N'TD003', N'Thực đơn hoàng gia', NULL)

GO
INSERT TrangThaiHopDong (MaTT, TenTT) VALUES (N'CHODUYET', N'Đang chờ duyệt')
INSERT TrangThaiHopDong (MaTT, TenTT) VALUES (N'DATHUCHIEN', N'Đã thực hiện')
INSERT TrangThaiHopDong (MaTT, TenTT) VALUES (N'THUCHIEN', N'Đang thực hiện')
INSERT TrangThaiHopDong (MaTT, TenTT) VALUES (N'XOA', N'Đánh dấu xóa')
INSERT TrangThaiHopDong (MaTT, TenTT) VALUES (N'CHOKYKET', N'Chờ ký kết')
GO
INSERT VaiTro (MaVT, MaPB, TenVT) VALUES (N'AMTHANH', N'KYTHUAT', N'Âm thanh')
INSERT VaiTro (MaVT, MaPB, TenVT) VALUES (N'ANHSANG', N'KYTHUAT', N'Ánh sáng')
INSERT VaiTro (MaVT, MaPB, TenVT) VALUES (N'BAOVE', N'BAOVE', N'Bảo vệ')
INSERT VaiTro (MaVT, MaPB, TenVT) VALUES (N'CASI', N'NGHETHUAT', N'Ca sĩ')
INSERT VaiTro (MaVT, MaPB, TenVT) VALUES (N'KHO', N'CSVC', N'Nhân viên kho')
INSERT VaiTro (MaVT, MaPB, TenVT) VALUES (N'MC', N'NGHETHUAT', N'MC')
INSERT VaiTro (MaVT, MaPB, TenVT) VALUES (N'PHUCVU', N'PHUCVU', N'Phục vụ')
INSERT VaiTro (MaVT, MaPB, TenVT) VALUES (N'TAIXE', N'VANCHUYEN', N'Đưa đón khách')
INSERT VaiTro (MaVT, MaPB, TenVT) VALUES (N'TAIXE2', N'VANCHUYEN', N'Vận chuyển đồ đạc')
INSERT VaiTro (MaVT, MaPB, TenVT) VALUES (N'TIEPTAN', N'TIEPTAN', N'Tiếp tân')
INSERT VaiTro (MaVT, MaPB, TenVT) VALUES (N'TINHHOADON', N'TIEPTAN', N'Tính hóa đơn')
INSERT VaiTro (MaVT, MaPB, TenVT) VALUES (N'TRANGTRI', N'NGHETHUAT', N'Trang trí')
INSERT VaiTro (MaVT, MaPB, TenVT) VALUES (N'YTA', N'YTE', N'Y tá')
INSERT VaiTro (MaVT, MaPB, TenVT) VALUES (N'QLCC', N'QUANLY', N'Quản lý cấp cao')
GO
INSERT VaiTroTaiKhoan (MaVT, TenVT) VALUES (N'ADMIN', N'Admin')
INSERT VaiTroTaiKhoan (MaVT, TenVT) VALUES (N'KHO', N'Quản lý kho')
INSERT VaiTroTaiKhoan (MaVT, TenVT) VALUES (N'QLCC', N'Quản lý cấp cao')
INSERT VaiTroTaiKhoan (MaVT, TenVT) VALUES (N'TIEPTAN', N'Tiếp tân')


--Thêm khóa ngoại
ALTER TABLE NhanVien ADD  CONSTRAINT DF_NhanVien_TrangThai  DEFAULT ((0)) FOR TrangThai
GO
ALTER TABLE Sanh ADD  CONSTRAINT DF_Sanh_SucChua  DEFAULT ((0)) FOR SucChua
GO
ALTER TABLE Sanh ADD  CONSTRAINT DF_Sanh_GiaThueSanh  DEFAULT ((0)) FOR GiaThueSanh
GO
ALTER TABLE Sanh ADD  CONSTRAINT DF_Sanh_GiaBan  DEFAULT ((0)) FOR GiaBan
GO
ALTER TABLE ChiTietDichVu  WITH CHECK ADD  CONSTRAINT FK_ChiTietDichVu_HopDong FOREIGN KEY(MaHD)
REFERENCES HopDong (MaHD)
ON UPDATE CASCADE
ON DELETE CASCADE 
GO
ALTER TABLE ChiTietDichVu CHECK CONSTRAINT FK_ChiTietDichVu_HopDong
GO
ALTER TABLE ChiTietDichVu  WITH CHECK ADD  CONSTRAINT FK_ChiTietDichVu_CoSoVatChat FOREIGN KEY(MaCSVC)
REFERENCES CoSoVatChat (MaCSVC)
ON UPDATE CASCADE
GO
ALTER TABLE ChiTietDichVu CHECK CONSTRAINT FK_ChiTietDichVu_CoSoVatChat
GO
ALTER TABLE ChiTietDichVu  WITH CHECK ADD  CONSTRAINT FK_ChiTietDichVu_DichVu FOREIGN KEY(MaDV)
REFERENCES DichVu (MaDV)
ON UPDATE CASCADE
GO
ALTER TABLE ChiTietDichVu CHECK CONSTRAINT FK_ChiTietDichVu_DichVu
GO
ALTER TABLE ChiTietDatMon  WITH CHECK ADD  CONSTRAINT FK_ChiTietDatMon_DichVuDatMon1 FOREIGN KEY(MaHD)
REFERENCES DichVuDatMon (MaHD)
ON UPDATE CASCADE
ON DELETE CASCADE 
GO
ALTER TABLE ChiTietDatMon CHECK CONSTRAINT FK_ChiTietDatMon_DichVuDatMon1
GO
ALTER TABLE ChiTietDatMon  WITH CHECK ADD  CONSTRAINT FK_ChiTietDatMon_MonAn FOREIGN KEY(MaMA)
REFERENCES MonAn (MaMA)
ON UPDATE CASCADE
GO
ALTER TABLE ChiTietDatMon CHECK CONSTRAINT FK_ChiTietDatMon_MonAn
GO
ALTER TABLE HopDongDichVu  WITH CHECK ADD  CONSTRAINT FK_HopDongDichVu_DichVu FOREIGN KEY(MaDV)
REFERENCES DichVu (MaDV)
ON UPDATE CASCADE
GO
ALTER TABLE HopDongDichVu CHECK CONSTRAINT FK_HopDongDichVu_DichVu
GO
ALTER TABLE HopDongDichVu  WITH CHECK ADD  CONSTRAINT FK_HopDongDichVu_HopDong FOREIGN KEY(MaHD)
REFERENCES HopDong (MaHD)
ON UPDATE CASCADE
ON DELETE CASCADE 
GO
ALTER TABLE HopDongDichVu CHECK CONSTRAINT FK_HopDongDichVu_HopDong
GO
ALTER TABLE ChiTietDichVuDiKem  WITH CHECK ADD  CONSTRAINT FK_ChiTietDichVuDiKem_DichVuDiKem FOREIGN KEY(MaDV)
REFERENCES DichVuDiKem (MaDV)
ON UPDATE CASCADE
ON DELETE CASCADE 
GO
ALTER TABLE ChiTietDichVuDiKem CHECK CONSTRAINT FK_ChiTietDichVuDiKem_DichVuDiKem
GO
ALTER TABLE ChiTietDichVuDiKem  WITH CHECK ADD  CONSTRAINT FK_ChiTietDichVuDiKem_HopDong FOREIGN KEY(MaHD)
REFERENCES HopDong (MaHD)
ON UPDATE CASCADE
ON DELETE CASCADE 
GO
ALTER TABLE ChiTietDichVuDiKem CHECK CONSTRAINT FK_ChiTietDichVuDiKem_HopDong
GO
ALTER TABLE ChiTietDVDuaDon  WITH CHECK ADD  CONSTRAINT FK_ChiTietDVDuaDon_CoSoVatChat FOREIGN KEY(LoaiXe)
REFERENCES CoSoVatChat (MaCSVC)
ON UPDATE CASCADE
GO
ALTER TABLE ChiTietDVDuaDon CHECK CONSTRAINT FK_ChiTietDVDuaDon_CoSoVatChat
GO
ALTER TABLE ChiTietDVDuaDon  WITH CHECK ADD  CONSTRAINT FK_ChiTietDVDuaDon_DichVuDuaDon FOREIGN KEY(MaHD)
REFERENCES DichVuDuaDon (MaHD)
ON UPDATE CASCADE
GO
ALTER TABLE ChiTietDVDuaDon CHECK CONSTRAINT FK_ChiTietDVDuaDon_DichVuDuaDon
GO
ALTER TABLE ChiTietDVDuaDon  WITH CHECK ADD  CONSTRAINT FK_ChiTietDVDuaDon_NhanVien FOREIGN KEY(MaNV)
REFERENCES NhanVien (MaNV)
GO
ALTER TABLE ChiTietDVDuaDon CHECK CONSTRAINT FK_ChiTietDVDuaDon_NhanVien
GO
ALTER TABLE ChiTietGoiDichVu  WITH CHECK ADD  CONSTRAINT FK_ChiTietGoiDichVu_CoSoVatChat FOREIGN KEY(MaCSVC)
REFERENCES CoSoVatChat (MaCSVC)
ON UPDATE CASCADE
GO
ALTER TABLE ChiTietGoiDichVu CHECK CONSTRAINT FK_ChiTietGoiDichVu_CoSoVatChat
GO
ALTER TABLE ChiTietGoiDichVu  WITH CHECK ADD  CONSTRAINT FK_ChiTietGoiDichVu_GoiDichVu FOREIGN KEY(MaGoi)
REFERENCES GoiDichVu (MaGoi)
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE ChiTietGoiDichVu CHECK CONSTRAINT FK_ChiTietGoiDichVu_GoiDichVu
GO
ALTER TABLE ChiTietPhanCong  WITH CHECK ADD  CONSTRAINT FK_ChiTietPhanCong_NhanVien FOREIGN KEY(MaNV)
REFERENCES NhanVien (MaNV)
GO
ALTER TABLE ChiTietPhanCong CHECK CONSTRAINT FK_ChiTietPhanCong_NhanVien
GO
ALTER TABLE ChiTietPhanCong  WITH CHECK ADD  CONSTRAINT FK_ChiTietPhanCong_PhanCong FOREIGN KEY(MaPC)
REFERENCES PhanCong (MaPC)
ON UPDATE CASCADE
ON DELETE CASCADE 
GO
ALTER TABLE ChiTietPhanCong CHECK CONSTRAINT FK_ChiTietPhanCong_PhanCong
GO
ALTER TABLE ChiTietThucDon  WITH CHECK ADD  CONSTRAINT FK_ChiTietThucDon_MonAn FOREIGN KEY(MaMA)
REFERENCES MonAn (MaMA)
ON UPDATE CASCADE
ON DELETE CASCADE 
GO
ALTER TABLE ChiTietThucDon CHECK CONSTRAINT FK_ChiTietThucDon_MonAn
GO
ALTER TABLE ChiTietThucDon  WITH CHECK ADD  CONSTRAINT FK_ChiTietThucDon_ThucDon1 FOREIGN KEY(MaTD)
REFERENCES ThucDon (MaTD)
ON UPDATE CASCADE
GO
ALTER TABLE ChiTietThucDon CHECK CONSTRAINT FK_ChiTietThucDon_ThucDon1
GO
ALTER TABLE CoSoVatChat  WITH CHECK ADD  CONSTRAINT FK_CoSoVatChat_DanhMucCon FOREIGN KEY(MaDMC)
REFERENCES DanhMucCon (MaDMC)
ON UPDATE CASCADE
GO
ALTER TABLE CoSoVatChat CHECK CONSTRAINT FK_CoSoVatChat_DanhMucCon
GO
ALTER TABLE DanhMucCon  WITH CHECK ADD  CONSTRAINT FK_DanhMucCon_DanhMuc FOREIGN KEY(MaDM)
REFERENCES DanhMuc (MaDM)
ON UPDATE CASCADE
GO
ALTER TABLE DanhMucCon CHECK CONSTRAINT FK_DanhMucCon_DanhMuc
GO
ALTER TABLE DichVu  WITH CHECK ADD  CONSTRAINT FK_DichVu_PhanLoaiDichVu FOREIGN KEY(MaPLDV)
REFERENCES PhanLoaiDichVu (MaPLDV)
ON UPDATE CASCADE
GO
ALTER TABLE DichVu CHECK CONSTRAINT FK_DichVu_PhanLoaiDichVu
GO
ALTER TABLE DichVuDatMon  WITH CHECK ADD  CONSTRAINT FK_DichVuDatMon1_HopDong FOREIGN KEY(MaHD)
REFERENCES HopDong (MaHD)
ON UPDATE CASCADE
ON DELETE CASCADE 

GO
ALTER TABLE DichVuDatMon CHECK CONSTRAINT FK_DichVuDatMon1_HopDong
GO
ALTER TABLE GoiDichVu  WITH CHECK ADD  CONSTRAINT FK_GoiDichVu_DichVu FOREIGN KEY(MaDV)
REFERENCES DichVu (MaDV)
ON UPDATE CASCADE
ON DELETE CASCADE 
GO
ALTER TABLE GoiDichVu CHECK CONSTRAINT FK_GoiDichVu_DichVu
GO
ALTER TABLE HoaDon  WITH CHECK ADD  CONSTRAINT FK_HoaDon_HopDong FOREIGN KEY(MaHD)
REFERENCES HopDong (MaHD)
ON UPDATE CASCADE
ON DELETE CASCADE 
GO
ALTER TABLE HoaDon CHECK CONSTRAINT FK_HoaDon_HopDong

GO
ALTER TABLE HopDong  WITH CHECK ADD  CONSTRAINT FK_HopDong_KhachHang FOREIGN KEY(MaKH)
REFERENCES KhachHang (MaKH)
ON DELETE CASCADE 
GO
ALTER TABLE HopDong CHECK CONSTRAINT FK_HopDong_KhachHang
GO
ALTER TABLE HopDong  WITH CHECK ADD  CONSTRAINT FK_HopDong_NhanVien FOREIGN KEY(MaNL)
REFERENCES NhanVien (MaNV)
ON UPDATE CASCADE
GO
ALTER TABLE HopDong CHECK CONSTRAINT FK_HopDong_NhanVien
GO
ALTER TABLE HopDong  WITH CHECK ADD  CONSTRAINT FK_HopDong_Sanh FOREIGN KEY(Sanh)
REFERENCES Sanh (MaSanh)
ON UPDATE CASCADE
GO
ALTER TABLE HopDong CHECK CONSTRAINT FK_HopDong_Sanh
GO
ALTER TABLE HopDong  WITH CHECK ADD  CONSTRAINT FK_HopDong_TrangThaiHopDong FOREIGN KEY(TrangThai)
REFERENCES TrangThaiHopDong (MaTT)
ON UPDATE CASCADE
GO
ALTER TABLE HopDong CHECK CONSTRAINT FK_HopDong_TrangThaiHopDong
GO
ALTER TABLE MonAn  WITH CHECK ADD  CONSTRAINT FK_MonAn_PhanLoaiMonAn FOREIGN KEY(MaPL)
REFERENCES PhanLoaiMonAn (MaPL)
GO
ALTER TABLE MonAn CHECK CONSTRAINT FK_MonAn_PhanLoaiMonAn
GO
ALTER TABLE NhanVien  WITH CHECK ADD  CONSTRAINT FK_NhanVien_VaiTro FOREIGN KEY(MaVT)
REFERENCES VaiTro (MaVT)
ON UPDATE CASCADE
GO
ALTER TABLE NhanVien CHECK CONSTRAINT FK_NhanVien_VaiTro
GO
ALTER TABLE PhanCong  WITH CHECK ADD  CONSTRAINT FK_PhanCong_HopDong FOREIGN KEY(MaHD)
REFERENCES HopDong (MaHD)
ON UPDATE CASCADE
ON DELETE CASCADE 
GO
ALTER TABLE PhanCong CHECK CONSTRAINT FK_PhanCong_HopDong
GO
ALTER TABLE Sanh  WITH CHECK ADD  CONSTRAINT FK_Sanh_PhanLoaiSanh FOREIGN KEY(MaPL)
REFERENCES PhanLoaiSanh (MaPL)
ON UPDATE CASCADE
GO
ALTER TABLE Sanh CHECK CONSTRAINT FK_Sanh_PhanLoaiSanh
GO
ALTER TABLE TaiKhoan  WITH CHECK ADD  CONSTRAINT FK_TaiKhoan_NhanVien FOREIGN KEY(MaNhanVien)
REFERENCES NhanVien (MaNV)
ON UPDATE CASCADE
GO
ALTER TABLE TaiKhoan CHECK CONSTRAINT FK_TaiKhoan_NhanVien
GO
ALTER TABLE TaiKhoan  WITH CHECK ADD  CONSTRAINT FK_TaiKhoan_VaiTroTaiKhoan FOREIGN KEY(VaiTro)
REFERENCES VaiTroTaiKhoan (MaVT)
GO
ALTER TABLE TaiKhoan CHECK CONSTRAINT FK_TaiKhoan_VaiTroTaiKhoan
GO
ALTER TABLE VaiTro  WITH CHECK ADD  CONSTRAINT FK_VaiTro_PhongBan FOREIGN KEY(MaPB)
REFERENCES PhongBan (MaPB)
ON UPDATE CASCADE
GO
ALTER TABLE VaiTro CHECK CONSTRAINT FK_VaiTro_PhongBan
GO

ALTER TABLE ChiPhiPhatSinh  WITH CHECK ADD  CONSTRAINT FK_ChiPhiPhatSinh_HopDong FOREIGN KEY(MaHD)
REFERENCES HopDong (MaHD)
ON UPDATE CASCADE 
ON DELETE CASCADE 

ALTER TABLE ChiPhiPhatSinh  WITH CHECK ADD  CONSTRAINT FK_ChiPhiPhatSinh_DichVu FOREIGN KEY(MaPLDV)
REFERENCES DichVu (MaDV)
ON UPDATE CASCADE 

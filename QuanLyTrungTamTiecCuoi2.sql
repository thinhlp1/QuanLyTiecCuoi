--Tạo CSDL
CREATE DATABASE QuanLyTrungTamTiecCuoi
--DROP DATABASE QuanLyTrungTamTiecCuoi  SELECT * FROM ChiTietDatMon
GO
--Gọi CSDL
USE QuanLyTrungTamTiecCuoi
GO
--Tạo bảng "ChiTietDichVu"
--DROP TABLE ChiTietDichVu
CREATE TABLE ChiTietDichVu(
	MaHD varchar(50) NOT NULL,
	MaCSVC varchar(25) NOT NULL,
	MaDV varchar(25) NOT NULL,
	ChiPhi bigint NULL,
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
	MaHD varchar(50) NOT NULL,
	MaMA varchar(25) NOT NULL,
	ThuTu int NOT NULL,
	MaTD varchar(25) NOT NULL,
	SoLuong int NOT NULL DEFAULT 0,
	ChiPhiPhatSinh bigint null,
	GhiChu nvarchar(255) NULL,
 CONSTRAINT PK_ChiTietDatMon PRIMARY KEY 
(
	MaHD ASC,
	MaMA ASC,
	MaTD
)
) 
GO
--Tạo bảng "HopDongDichVu"
CREATE TABLE HopDongDichVu(
	MaHD varchar(50) NOT NULL,
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
--Tạo bảng HopDongDichVuDiKem
CREATE TABLE HopDongDichVuDiKem(
	MaHD varchar(50) NOT NULL,
	GhiChu nvarchar(255) NULL,
	ChiPhi BIGINT NULL,
	ChiPhiPhatSinh bigint NULL,

 CONSTRAINT PK_HopDongDichVuDiKem PRIMARY KEY CLUSTERED 
(
	MaHD ASC
))

--Tạo bảng "ChiTietDichVuDiKem
CREATE TABLE ChiTietDichVuDiKem(
	MaHD varchar(50) NOT NULL,
	MaDV varchar(25) NOT NULL,
	GhiChu nvarchar(255) NULL,
	ChiPhi BIGINT NULL,
	ChiPhiPhatSinh bigint NULL,
	SoLuong int NOT NULL,
 CONSTRAINT PK_ChiTietDichVuDiKem PRIMARY KEY CLUSTERED 
(
	MaHD ASC,
	MaDV ASC
)
)
GO
--Tạo bảng "ChiTietDVDuaDon"
CREATE TABLE ChiTietDVDuaDon(
	MaHD varchar(50) NOT NULL,
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
	ChiPhi bigint NULL, 	
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
	MaHD varchar(50) NOT NULL,
	MaTD varchar(25) NOT NULL,
	ChiPhi bigint NOT NULL,
	ChiPhiPhatSinh bigint NULL,
	GhiChu nvarchar(255) NULL,
 CONSTRAINT PK_DichVuDatMon1 PRIMARY KEY CLUSTERED 
(
	MaHD ASC,
	MaTD
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
	MaHD varchar(50) NOT NULL,
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
	MaHD varchar(50) NOT NULL,
	NgayLap date NOT NULL,
	NgayLapLan2 date NULL,
	MaNV varchar(5) NOT NULL,
	TrangThai int NOT NULL,
	MaNLLan2 varchar(5) NULL,
	TongTien bigint NULL,

 CONSTRAINT PK_HoaDon PRIMARY KEY CLUSTERED 
(
	MaHoaDon ASC
)
)
GO
--Tạo bảng "HopDong"
CREATE TABLE HopDong(
	MaHD varchar(50) NOT NULL,
	MaNL varchar(5) NOT NULL,
	SoLuongBan bigint NOT NULL,
	Sanh varchar(25) NOT NULL,
	NgayLap date NOT NULL,
	NgayDuyet date NULL,
	MaND varchar(5) NULL,
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
	MaHD varchar(50) NOT NULL,
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
	MaHD varchar(50) NOT NULL,
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
	MaHD varchar(50) NOT NULL ,
	MaDV varchar(25) NOT NULL,
	ChiPhi bigint  NULL,
	LyDo nvarchar(255) NULL,
	 CONSTRAINT PK_ChiPhiPhatSinh PRIMARY KEY CLUSTERED 
(
	MaHD, MaDV
)
)
--Tạo bảng ChiTietChiPhiDichVu

GO

GO
INSERT ChiPhiPhatSinh (MaHD, MaDV, ChiPhi, LyDo) VALUES (N'HD20221205011', N'TTCONG', 1500000, N'')
INSERT ChiPhiPhatSinh (MaHD, MaDV, ChiPhi, LyDo) VALUES (N'HD20221205011', N'TTSANKHAU', 2000000, N'')
INSERT ChiPhiPhatSinh (MaHD, MaDV, ChiPhi, LyDo) VALUES (N'HD20221207001', N'TTCONG', 100000, N'cháy đồ')
INSERT ChiPhiPhatSinh (MaHD, MaDV, ChiPhi, LyDo) VALUES (N'HD20221207001', N'TTSANKHAU', 10000000, N'hư máy tạo khói')
INSERT ChiPhiPhatSinh (MaHD, MaDV, ChiPhi, LyDo) VALUES (N'HD20221207002', N'TTCONG', 100000, N'cháy nhà')
INSERT ChiPhiPhatSinh (MaHD, MaDV, ChiPhi, LyDo) VALUES (N'HD20221207002', N'TTSANKHAU', 5000000, N'hư máy tạo khói')
INSERT ChiPhiPhatSinh (MaHD, MaDV, ChiPhi, LyDo) VALUES (N'HD20221209001', N'TTBANTIEC', 1000000, N'hư đồ')
INSERT ChiPhiPhatSinh (MaHD, MaDV, ChiPhi, LyDo) VALUES (N'HD20221209001', N'TTCONG', 1000000, N'cháy đồ')
GO


--Thêm dữ liệu 
INSERT ChiTietGoiDichVu (MaGoi, MaCSVC,ChiPhi, GhiChu) VALUES (N'NGHETHUAT1', N'LIENKHUC1',10000, '')
INSERT ChiTietGoiDichVu (MaGoi, MaCSVC,ChiPhi, GhiChu) VALUES (N'NGHETHUAT1', N'VUDAO1',10000, '')

INSERT ChiTietGoiDichVu (MaGoi, MaCSVC,ChiPhi, GhiChu) VALUES (N'NGHETHUAT2', N'LIENKHUC1',10000, '')
INSERT ChiTietGoiDichVu (MaGoi, MaCSVC,ChiPhi, GhiChu) VALUES (N'NGHETHUAT2', N'VUDAO1',10000, '')

INSERT ChiTietGoiDichVu (MaGoi, MaCSVC,ChiPhi, GhiChu) VALUES (N'TTBANTIEC1', N'AOGHEDO', 10000,'')
INSERT ChiTietGoiDichVu (MaGoi, MaCSVC,ChiPhi, GhiChu) VALUES (N'TTBANTIEC1', N'HOAHONGDO',10000, '')
INSERT ChiTietGoiDichVu (MaGoi, MaCSVC,ChiPhi, GhiChu) VALUES (N'TTBANTIEC1', N'TRAIBANTRANG',10000, '')

INSERT ChiTietGoiDichVu (MaGoi, MaCSVC,ChiPhi, GhiChu) VALUES (N'TTBANTIEC3', N'AOGHEXANH',10000, '')
INSERT ChiTietGoiDichVu (MaGoi, MaCSVC,ChiPhi, GhiChu) VALUES (N'TTBANTIEC3', N'HOAHONGXANH', 10000,'')
INSERT ChiTietGoiDichVu (MaGoi, MaCSVC,ChiPhi, GhiChu) VALUES (N'TTBANTIEC3', N'TRAIBANVANG',10000, '')

INSERT ChiTietGoiDichVu (MaGoi, MaCSVC,ChiPhi, GhiChu) VALUES (N'TTCONG1', N'CONGTRON',10000, N'màu xanh')
INSERT ChiTietGoiDichVu (MaGoi, MaCSVC,ChiPhi, GhiChu) VALUES (N'TTCONG1', N'BANGTEN1',10000, '')
INSERT ChiTietGoiDichVu (MaGoi, MaCSVC,ChiPhi, GhiChu) VALUES (N'TTCONG1', N'HOACUCDO',10000, '')
INSERT ChiTietGoiDichVu (MaGoi, MaCSVC,ChiPhi, GhiChu) VALUES (N'TTCONG1', N'HOAHONGDO',10000, '')
INSERT ChiTietGoiDichVu (MaGoi, MaCSVC,ChiPhi, GhiChu) VALUES (N'TTCONG1', N'THAMDO',10000, '')

INSERT ChiTietGoiDichVu (MaGoi, MaCSVC,ChiPhi, GhiChu) VALUES (N'TTCONG2', N'CONGTRON',10000, N'màu xanh')
INSERT ChiTietGoiDichVu (MaGoi, MaCSVC,ChiPhi, GhiChu) VALUES (N'TTCONG2', N'HOAHONGXANH',1000000, '')
INSERT ChiTietGoiDichVu (MaGoi, MaCSVC,ChiPhi, GhiChu) VALUES (N'TTCONG2', N'HOAHUE', 1000000,'')
INSERT ChiTietGoiDichVu (MaGoi, MaCSVC,ChiPhi, GhiChu) VALUES (N'TTCONG2', N'THAMDO',10000, '')
INSERT ChiTietGoiDichVu (MaGoi, MaCSVC,ChiPhi, GhiChu) VALUES (N'TTCONG2', N'BANGTEN1',10000, '')

INSERT ChiTietGoiDichVu (MaGoi, MaCSVC,ChiPhi, GhiChu) VALUES (N'TTSANKHAU1', N'BANGTEN1',10000, '')
INSERT ChiTietGoiDichVu (MaGoi, MaCSVC,ChiPhi, GhiChu) VALUES (N'TTSANKHAU1', N'HOACUCDO',1000000, '')
INSERT ChiTietGoiDichVu (MaGoi, MaCSVC,ChiPhi, GhiChu) VALUES (N'TTSANKHAU1', N'HOAHONGDO',1000000, '')
INSERT ChiTietGoiDichVu (MaGoi, MaCSVC,ChiPhi, GhiChu) VALUES (N'TTSANKHAU1', N'THAMDO',10000, '')

INSERT ChiTietGoiDichVu (MaGoi, MaCSVC,ChiPhi, GhiChu) VALUES (N'TTSANKHAU2', N'BANGTEN1',10000, '')
INSERT ChiTietGoiDichVu (MaGoi, MaCSVC,ChiPhi, GhiChu) VALUES (N'TTSANKHAU2', N'HOACUCDO',1000000, '')
INSERT ChiTietGoiDichVu (MaGoi, MaCSVC,ChiPhi, GhiChu) VALUES (N'TTSANKHAU2', N'HOAHONGXANH',10000, '')
INSERT ChiTietGoiDichVu (MaGoi, MaCSVC,ChiPhi, GhiChu) VALUES (N'TTSANKHAU2', N'THAMCO',10000, '')

GO
INSERT CoSoVatChat (MaCSVC, TenCSVC, MaDMC, SoLuong, GiaThue,GhiChu) VALUES (N'AOGHEDO', N'Áo ghế đỏ', N'AOGHE', 1000,30000 ,'')
INSERT CoSoVatChat (MaCSVC, TenCSVC, MaDMC, SoLuong, GiaThue,GhiChu) VALUES (N'AOGHEXANH', N'Áo ghế xanh', N'AOGHE', 1000, 40000, '')
INSERT CoSoVatChat (MaCSVC, TenCSVC, MaDMC, SoLuong, GiaThue,GhiChu) VALUES (N'BANGTEN1', N'Bảng tên tròn', N'BANGTEN', 0,500000 ,'')
INSERT CoSoVatChat (MaCSVC, TenCSVC, MaDMC, SoLuong, GiaThue,GhiChu) VALUES (N'CONGDEN', N'Cổng đen', N'CONG', 1,1000000 ,'')
INSERT CoSoVatChat (MaCSVC, TenCSVC, MaDMC, SoLuong, GiaThue,GhiChu) VALUES (N'CONGOVAN', N'Cổng Ovan', N'CONG', 20,1200000, '')
INSERT CoSoVatChat (MaCSVC, TenCSVC, MaDMC, SoLuong, GiaThue,GhiChu) VALUES (N'CONGTRON', N'Cổng tròn xanh', N'CONG', 3,1300000, N'không có')
INSERT CoSoVatChat (MaCSVC, TenCSVC, MaDMC, SoLuong, GiaThue,GhiChu) VALUES (N'CONGVUONG', N'Cổng vuông', N'CONG', 10,1000000, '')
INSERT CoSoVatChat (MaCSVC, TenCSVC, MaDMC, SoLuong, GiaThue,GhiChu) VALUES (N'GIAGO', N'Giá gỗ', N'GIA', 10,500000, '')
INSERT CoSoVatChat (MaCSVC, TenCSVC, MaDMC, SoLuong, GiaThue,GhiChu) VALUES (N'GIASAT', N'Giá sắt', N'GIA', 10,600000, '')
INSERT CoSoVatChat (MaCSVC, TenCSVC, MaDMC, SoLuong, GiaThue,GhiChu) VALUES (N'HOACUCDO', N'Hoa cúc', N'HOAPHUDAO', 100,100000, '')
INSERT CoSoVatChat (MaCSVC, TenCSVC, MaDMC, SoLuong, GiaThue,GhiChu) VALUES (N'HOAHONGDO', N'Hoa hồng', N'HOACHUDAO', 10,200000, '')
INSERT CoSoVatChat (MaCSVC, TenCSVC, MaDMC, SoLuong, GiaThue,GhiChu) VALUES (N'HOAHONGXANH', N'Hoa hồng xanh', N'HOACHUDAO', 30,300000, '')
INSERT CoSoVatChat (MaCSVC, TenCSVC, MaDMC, SoLuong, GiaThue,GhiChu) VALUES (N'HOAHUE', N'Hoa huệ', N'HOAPHUDAO', 10,100000 ,'')
INSERT CoSoVatChat (MaCSVC, TenCSVC, MaDMC, SoLuong, GiaThue,GhiChu) VALUES (N'LIENKHUC1', N'Liên khúc nhạc nhẹ', N'LIENKHUC', 0,1000000 ,'')
INSERT CoSoVatChat (MaCSVC, TenCSVC, MaDMC, SoLuong, GiaThue,GhiChu) VALUES (N'LIENKHUC2', N'Liên khúc nhạc soi động', N'LIENKHUC', 0,1000000, '')
INSERT CoSoVatChat (MaCSVC, TenCSVC, MaDMC, SoLuong, GiaThue,GhiChu) VALUES (N'PHAOHOANHO', N'Pháo hoa nhỏ', N'PHAOHOA', 300,100000 ,N'pháo hoa màu đỏ')
INSERT CoSoVatChat (MaCSVC, TenCSVC, MaDMC, SoLuong, GiaThue,GhiChu) VALUES (N'THAMCO', N'Thảm cỏ', N'THAM', 5,500000 ,'')
INSERT CoSoVatChat (MaCSVC, TenCSVC, MaDMC, SoLuong, GiaThue,GhiChu) VALUES (N'THAMDO', N'Thảm đỏ', N'THAM', 5,500000, '')
INSERT CoSoVatChat (MaCSVC, TenCSVC, MaDMC, SoLuong, GiaThue,GhiChu) VALUES (N'TRAIBANTRANG', N'Trãi bàn trắng', N'TRAIBAN', 300,50000, '')
INSERT CoSoVatChat (MaCSVC, TenCSVC, MaDMC, SoLuong, GiaThue,GhiChu) VALUES (N'TRAIBANVANG', N'Trãi bàn vàng', N'TRAIBAN', 200,60000, '')
INSERT CoSoVatChat (MaCSVC, TenCSVC, MaDMC, SoLuong, GiaThue,GhiChu) VALUES (N'VUDAO1', N'Vũ đạo nhẹ nhàng', N'VUDAO', 0, 1000000,'')
INSERT CoSoVatChat (MaCSVC, TenCSVC, MaDMC, SoLuong, GiaThue,GhiChu) VALUES (N'VUDAO2', N'Vũ đạo sôi động ', N'VUDAO', 0, 1000000,'')
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
INSERT DichVu (MaDV, MaPLDV, TenDV) VALUES (N'DIKEM', N'DIKEM', N'Đi kèm')
GO

GO
INSERT DichVuDiKem (MaDV, TenDV, Gia) VALUES (N'BANHKEM', N'Bánh kem ', 1500000)
INSERT DichVuDiKem (MaDV, TenDV, Gia) VALUES (N'MAYTAOKHOI', N'Máy tạo khói', 2000000)
INSERT DichVuDiKem (MaDV, TenDV, Gia) VALUES (N'PHAOKIMTUYEN', N'Pháo kim tuyến', 100000)
INSERT DichVuDiKem (MaDV, TenDV, Gia) VALUES (N'THIENNGABANG', N'Thiên nga băng', 4000000)

GO
INSERT GoiDichVu (MaGoi, MaDV, TenGoi, ChiPhi, GhiChu, HinhAnh) VALUES (N'NGHETHUAT2', N'NGHETHUAT', N'Âm nhạc lãng mạn', N'12000000  ', '', '')
INSERT GoiDichVu (MaGoi, MaDV, TenGoi, ChiPhi, GhiChu, HinhAnh) VALUES (N'NGHETHUAT1', N'NGHETHUAT', N'Nghệ thuật soi động', N'12000000  ', '', '')
INSERT GoiDichVu (MaGoi, MaDV, TenGoi, ChiPhi, GhiChu, HinhAnh) VALUES (N'TTBANTIEC1', N'TTBANTIEC', N'Bàn xanh ghế trắng', N'300000    ', '', '')
INSERT GoiDichVu (MaGoi, MaDV, TenGoi, ChiPhi, GhiChu, HinhAnh) VALUES (N'TTBANTIEC3', N'TTBANTIEC', N'Bàn trắng ghế đỏ', N'300000    ', '', '')
INSERT GoiDichVu (MaGoi, MaDV, TenGoi, ChiPhi, GhiChu, HinhAnh) VALUES (N'TTCONG1', N'TTCONG', N'Cổng hoa hồng đỏ', N'10000000  ', N'hoa thật', '')
INSERT GoiDichVu (MaGoi, MaDV, TenGoi, ChiPhi, GhiChu, HinhAnh) VALUES (N'TTCONG2', N'TTCONG', N'Cổng hoa hồng đỏ', N'3000000   ', N'hoa fa ke', '')
INSERT GoiDichVu (MaGoi, MaDV, TenGoi, ChiPhi, GhiChu, HinhAnh) VALUES (N'TTSANKHAU1', N'TTSANKHAU', N'Sân khấu nhiều hoa', N'3000000   ', N'', '')
INSERT GoiDichVu (MaGoi, MaDV, TenGoi, ChiPhi, GhiChu, HinhAnh) VALUES (N'TTSANKHAU2', N'TTSANKHAU', N'Sân khấu bự', N'5000000   ', N'có kim tuyến....', '')
GO



INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221101004', N'BANHPLAN', 1, N'TD001', 8, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221101004', N'BICUONCHAY', 6, N'TD004', 2, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221101004', N'BOKHOCHAY', 7, N'TD004', 2, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221101004', N'BOLAGU', 2, N'TD001', 8, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221101004', N'BOSOTTIEUXANH', 3, N'TD004', 2, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221101004', N'CHAOGA', 3, N'TD001', 8, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221101004', N'CHAOVIT', 4, N'TD001', 8, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221101004', N'COCACOLA', 9, N'TD001', 200, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221101004', N'COCACOLA', 9, N'TD004', 10, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221101004', N'COMCHIENHAISAN', 6, N'TD001', 8, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221101004', N'HOTVITLON', 7, N'TD001', 8, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221101004', N'LAUCATHACLAC', 4, N'TD004', 2, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221101004', N'LAUDE', 8, N'TD001', 8, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221101004', N'LAUNAMCHAY', 8, N'TD004', 2, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221101004', N'NGHEUHAP', 5, N'TD001', 8, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221101004', N'SUPCHAYTHAPCAM', 5, N'TD004', 2, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221101004', N'TAUHU', 2, N'TD004', 2, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221101004', N'TOYEN', 1, N'TD004', 2, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201001', N'BANHPLAN', 1, N'TD001', 9, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201001', N'BICUONCHAY', 6, N'TD004', 1, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201001', N'BOKHOCHAY', 7, N'TD004', 1, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201001', N'BOLAGU', 2, N'TD001', 9, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201001', N'BOSOTTIEUXANH', 3, N'TD004', 1, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201001', N'CHAOGA', 3, N'TD001', 9, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201001', N'CHAOVIT', 4, N'TD001', 9, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201001', N'COCACOLA', 9, N'TD001', 200, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201001', N'COCACOLA', 9, N'TD004', 10, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201001', N'COMCHIENHAISAN', 6, N'TD001', 9, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201001', N'HOTVITLON', 7, N'TD001', 9, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201001', N'LAUCATHACLAC', 4, N'TD004', 1, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201001', N'LAUDE', 8, N'TD001', 9, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201001', N'LAUNAMCHAY', 8, N'TD004', 1, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201001', N'NGHEUHAP', 5, N'TD001', 9, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201001', N'SUPCHAYTHAPCAM', 5, N'TD004', 1, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201001', N'TAUHU', 2, N'TD004', 1, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201001', N'TOYEN', 1, N'TD004', 1, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201002', N'BOCUBE', 1, N'TD003', 20, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201002', N'BOLUCLAC', 2, N'TD003', 20, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201002', N'CANGCUABACHHOA', 3, N'TD003', 20, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201002', N'CARIGA', 4, N'TD003', 20, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201002', N'CUAHOANGDE', 5, N'TD003', 20, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201002', N'GAQUAYNGUVI', 6, N'TD003', 20, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201002', N'LAUCABOP', 7, N'TD003', 20, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201002', N'RUOUVANG', 8, N'TD003', 0, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201002', N'TOYEN', 9, N'TD003', 20, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201003', N'BANHPLAN', 11, N'TD003', 15, 0, NULL)
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201003', N'BIA333', 10, N'TD003', 0, 0, NULL)
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201003', N'BOCUBE', 1, N'TD003', 15, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201003', N'BOLUCLAC', 2, N'TD003', 15, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201003', N'CANGCUABACHHOA', 3, N'TD003', 15, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201003', N'CARIGA', 4, N'TD003', 15, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201003', N'CUAHOANGDE', 5, N'TD003', 15, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201003', N'GAQUAYNGUVI', 6, N'TD003', 15, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201003', N'LAUCABOP', 7, N'TD003', 15, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201003', N'RUOUVANG', 8, N'TD003', 0, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201003', N'TOYEN', 9, N'TD003', 15, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201004', N'BANHPLAN', 1, N'TD001', 12, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201004', N'BOLAGU', 2, N'TD001', 12, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201004', N'CHAOGA', 3, N'TD001', 12, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201004', N'CHAOVIT', 4, N'TD001', 12, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201004', N'COCACOLA', 9, N'TD001', 200, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201004', N'COMCHIENHAISAN', 6, N'TD001', 12, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201004', N'HOTVITLON', 7, N'TD001', 12, 10000, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201004', N'LAUDE', 8, N'TD001', 12, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201004', N'NGHEUHAP', 5, N'TD001', 12, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201005', N'BICUONCHAY', 6, N'TD004', 10, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201005', N'BOKHOCHAY', 7, N'TD004', 10, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201005', N'BOSOTTIEUXANH', 3, N'TD004', 10, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201005', N'COCACOLA', 9, N'TD004', 10, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201005', N'LAUCATHACLAC', 4, N'TD004', 10, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201005', N'LAUNAMCHAY', 8, N'TD004', 10, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201005', N'SUPCHAYTHAPCAM', 5, N'TD004', 10, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201005', N'TAUHU', 2, N'TD004', 10, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201005', N'TOYEN', 1, N'TD004', 10, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201006', N'BANHPLAN', 1, N'TD001', 10, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201006', N'BOLAGU', 2, N'TD001', 10, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201006', N'CHAOGA', 3, N'TD001', 10, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201006', N'CHAOVIT', 4, N'TD001', 10, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201006', N'COCACOLA', 9, N'TD001', 200, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201006', N'COMCHIENHAISAN', 6, N'TD001', 10, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201006', N'HOTVITLON', 7, N'TD001', 10, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201006', N'LAUDE', 8, N'TD001', 10, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201006', N'NGHEUHAP', 5, N'TD001', 10, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201007', N'BANHPLAN', 1, N'TD001', 12, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201007', N'BOLAGU', 2, N'TD001', 12, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201007', N'CHAOGA', 3, N'TD001', 12, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201007', N'CHAOVIT', 4, N'TD001', 12, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201007', N'COCACOLA', 9, N'TD001', 200, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201007', N'COMCHIENHAISAN', 6, N'TD001', 12, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201007', N'HOTVITLON', 7, N'TD001', 12, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201007', N'LAUDE', 8, N'TD001', 12, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201007', N'NGHEUHAP', 5, N'TD001', 12, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201008', N'BIA333', 9, N'TD002', 100, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201008', N'CHAOVIT', 2, N'TD002', 10, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201008', N'COMCHIENHAISAN', 3, N'TD002', 10, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201008', N'GABOXOI', 4, N'TD002', 10, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201008', N'GAHAP', 5, N'TD002', 10, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201008', N'LAUCABOP', 6, N'TD002', 10, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201008', N'NGHEUHAP', 7, N'TD002', 10, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201008', N'SUACHUA', 8, N'TD002', 10, 0, N'')
GO
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201008', N'SUPHAISAN', 1, N'TD002', 10, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201009', N'BANHPLAN', 1, N'TD001', 10, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201009', N'BOLAGU', 2, N'TD001', 10, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201009', N'CHAOGA', 3, N'TD001', 10, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201009', N'CHAOVIT', 4, N'TD001', 10, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201009', N'COCACOLA', 9, N'TD001', 200, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201009', N'COMCHIENHAISAN', 6, N'TD001', 10, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201009', N'HOTVITLON', 7, N'TD001', 10, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201009', N'LAUDE', 8, N'TD001', 10, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201009', N'NGHEUHAP', 5, N'TD001', 10, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201010', N'BIA333', 9, N'TD002', 100, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201010', N'CHAOVIT', 2, N'TD002', 10, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201010', N'COMCHIENHAISAN', 3, N'TD002', 10, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201010', N'GABOXOI', 4, N'TD002', 10, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201010', N'GAHAP', 5, N'TD002', 10, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201010', N'LAUCABOP', 6, N'TD002', 10, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201010', N'NGHEUHAP', 7, N'TD002', 10, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201010', N'SUACHUA', 8, N'TD002', 10, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201010', N'SUPHAISAN', 1, N'TD002', 10, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221205011', N'BANHPLAN', 1, N'TD001', 9, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221205011', N'BIA333', 9, N'TD002', 100, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221205011', N'BOLAGU', 2, N'TD001', 9, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221205011', N'CHAOGA', 3, N'TD001', 9, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221205011', N'CHAOVIT', 4, N'TD001', 9, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221205011', N'CHAOVIT', 2, N'TD002', 1, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221205011', N'COCACOLA', 9, N'TD001', 200, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221205011', N'COMCHIENHAISAN', 6, N'TD001', 9, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221205011', N'COMCHIENHAISAN', 3, N'TD002', 1, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221205011', N'GABOXOI', 4, N'TD002', 1, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221205011', N'GAHAP', 5, N'TD002', 1, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221205011', N'HOTVITLON', 7, N'TD001', 9, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221205011', N'LAUCABOP', 6, N'TD002', 1, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221205011', N'LAUDE', 8, N'TD001', 9, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221205011', N'NGHEUHAP', 5, N'TD001', 9, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221205011', N'NGHEUHAP', 7, N'TD002', 1, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221205011', N'SUACHUA', 8, N'TD002', 1, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221205011', N'SUPHAISAN', 1, N'TD002', 1, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221206001', N'BANHPLAN', 1, N'TD001', 9, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221206001', N'BIA333', 9, N'TD002', 100, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221206001', N'BOLAGU', 2, N'TD001', 9, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221206001', N'CHAOGA', 3, N'TD001', 9, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221206001', N'CHAOVIT', 4, N'TD001', 9, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221206001', N'CHAOVIT', 2, N'TD002', 1, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221206001', N'COCACOLA', 9, N'TD001', 200, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221206001', N'COMCHIENHAISAN', 6, N'TD001', 9, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221206001', N'COMCHIENHAISAN', 3, N'TD002', 1, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221206001', N'GABOXOI', 4, N'TD002', 1, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221206001', N'GAHAP', 5, N'TD002', 1, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221206001', N'HOTVITLON', 7, N'TD001', 9, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221206001', N'LAUCABOP', 6, N'TD002', 1, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221206001', N'LAUDE', 8, N'TD001', 9, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221206001', N'NGHEUHAP', 5, N'TD001', 9, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221206001', N'NGHEUHAP', 7, N'TD002', 1, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221206001', N'SUACHUA', 8, N'TD002', 1, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221206001', N'SUPHAISAN', 1, N'TD002', 1, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221206002', N'BANHPLAN', 1, N'TD001', 9, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221206002', N'BIA333', 9, N'TD002', 100, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221206002', N'BOLAGU', 2, N'TD001', 9, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221206002', N'CHAOGA', 3, N'TD001', 9, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221206002', N'CHAOVIT', 4, N'TD001', 9, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221206002', N'CHAOVIT', 2, N'TD002', 1, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221206002', N'COCACOLA', 9, N'TD001', 200, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221206002', N'COMCHIENHAISAN', 6, N'TD001', 9, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221206002', N'COMCHIENHAISAN', 3, N'TD002', 1, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221206002', N'GABOXOI', 4, N'TD002', 1, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221206002', N'GAHAP', 5, N'TD002', 1, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221206002', N'HOTVITLON', 7, N'TD001', 9, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221206002', N'LAUCABOP', 6, N'TD002', 1, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221206002', N'LAUDE', 8, N'TD001', 9, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221206002', N'NGHEUHAP', 5, N'TD001', 9, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221206002', N'NGHEUHAP', 7, N'TD002', 1, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221206002', N'SUACHUA', 8, N'TD002', 1, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221206002', N'SUPHAISAN', 1, N'TD002', 1, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221207001', N'BANHPLAN', 1, N'TD001', 9, 0, N'nhiều đường')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221207001', N'BICUONCHAY', 6, N'TD004', 1, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221207001', N'BOKHOCHAY', 7, N'TD004', 1, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221207001', N'BOLAGU', 2, N'TD001', 9, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221207001', N'BOSOTTIEUXANH', 3, N'TD004', 1, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221207001', N'CARIGA', 10, N'TD001', 9, 0, NULL)
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221207001', N'CHAOGA', 3, N'TD001', 9, 20000, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221207001', N'CHAOVIT', 4, N'TD001', 9, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221207001', N'CHEHATSEN', 12, N'TD001', 9, 0, N'2 ngọt')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221207001', N'COCACOLA', 9, N'TD001', 200, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221207001', N'COCACOLA', 9, N'TD004', 10, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221207001', N'COMCHIENHAISAN', 6, N'TD001', 9, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221207001', N'LAUCATHACLAC', 4, N'TD004', 1, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221207001', N'LAUDE', 8, N'TD001', 9, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221207001', N'LAUNAMCHAY', 8, N'TD004', 1, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221207001', N'NGHEUHAP', 5, N'TD001', 9, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221207001', N'SUPCHAYTHAPCAM', 5, N'TD004', 1, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221207001', N'TAUHU', 2, N'TD004', 1, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221207001', N'TOYEN', 1, N'TD004', 1, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221207002', N'BANHPLAN', 1, N'TD001', 9, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221207002', N'BIA333', 10, N'TD001', 100, 0, NULL)
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221207002', N'BICUONCHAY', 6, N'TD004', 1, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221207002', N'BOKHOCHAY', 7, N'TD004', 1, 1000000, N'thịt kobe')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221207002', N'BOLAGU', 2, N'TD001', 9, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221207002', N'BOSOTTIEUXANH', 3, N'TD004', 1, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221207002', N'CHAOGA', 3, N'TD001', 9, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221207002', N'CHAOVIT', 4, N'TD001', 9, 0, N'')
GO
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221207002', N'CHEHATSEN', 10, N'TD004', 1, 0, NULL)
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221207002', N'COCACOLA', 9, N'TD001', 200, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221207002', N'COCACOLA', 9, N'TD004', 10, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221207002', N'COMCHIENHAISAN', 6, N'TD001', 9, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221207002', N'COMCHIENHAISAN', 11, N'TD004', 1, 20000, NULL)
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221207002', N'CUAHOANGDE', 12, N'TD004', 1, 0, NULL)
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221207002', N'HOTVITLON', 7, N'TD001', 9, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221207002', N'LAUCATHACLAC', 4, N'TD004', 1, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221207002', N'LAUDE', 8, N'TD001', 9, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221207002', N'LAUNAMCHAY', 8, N'TD004', 1, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221207002', N'NGHEUHAP', 5, N'TD001', 9, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221207002', N'SUPCHAYTHAPCAM', 5, N'TD004', 1, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221207002', N'TAUHU', 2, N'TD004', 1, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221207002', N'TOYEN', 1, N'TD004', 1, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221207003', N'BANHPLAN', 1, N'TD001', 9, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221207003', N'BIA333', 9, N'TD002', 100, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221207003', N'BOLAGU', 2, N'TD001', 9, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221207003', N'CHAOGA', 3, N'TD001', 9, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221207003', N'CHAOVIT', 4, N'TD001', 9, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221207003', N'CHAOVIT', 2, N'TD002', 1, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221207003', N'COCACOLA', 9, N'TD001', 200, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221207003', N'COCACOLA', 10, N'TD002', 0, 0, NULL)
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221207003', N'COMCHIENHAISAN', 6, N'TD001', 9, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221207003', N'COMCHIENHAISAN', 3, N'TD002', 1, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221207003', N'GABOXOI', 4, N'TD002', 1, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221207003', N'GAHAP', 5, N'TD002', 1, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221207003', N'HOTVITLON', 7, N'TD001', 9, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221207003', N'LAUCABOP', 6, N'TD002', 1, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221207003', N'LAUDE', 8, N'TD001', 9, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221207003', N'NGHEUHAP', 5, N'TD001', 9, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221207003', N'NGHEUHAP', 7, N'TD002', 1, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221207003', N'SUACHUA', 8, N'TD002', 1, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221207003', N'SUPHAISAN', 1, N'TD002', 1, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221208001', N'BANHPLAN', 1, N'TD001', 9, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221208001', N'BIA333', 9, N'TD002', -1, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221208001', N'BOLAGU', 2, N'TD001', 9, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221208001', N'CHAOGA', 3, N'TD001', 9, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221208001', N'CHAOVIT', 4, N'TD001', 9, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221208001', N'CHAOVIT', 2, N'TD002', 1, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221208001', N'COCACOLA', 9, N'TD001', 200, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221208001', N'COMCHIENHAISAN', 6, N'TD001', 9, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221208001', N'COMCHIENHAISAN', 3, N'TD002', 1, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221208001', N'GABOXOI', 4, N'TD002', 1, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221208001', N'GAHAP', 5, N'TD002', 1, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221208001', N'HOTVITLON', 7, N'TD001', 9, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221208001', N'LAUCABOP', 6, N'TD002', 1, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221208001', N'LAUDE', 8, N'TD001', 9, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221208001', N'NGHEUHAP', 5, N'TD001', 9, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221208001', N'NGHEUHAP', 7, N'TD002', 1, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221208001', N'SUACHUA', 8, N'TD002', 1, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221208001', N'SUPHAISAN', 1, N'TD002', 1, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221209001', N'BANHPLAN', 1, N'TD001', 9, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221209001', N'BICUONCHAY', 6, N'TD004', 1, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221209001', N'BOKHOCHAY', 10, N'TD001', 9, 0, NULL)
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221209001', N'BOKHOCHAY', 7, N'TD004', 1, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221209001', N'BOLAGU', 2, N'TD001', 9, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221209001', N'BOSOTTIEUXANH', 3, N'TD004', 1, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221209001', N'CHAOGA', 3, N'TD001', 9, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221209001', N'CHAOVIT', 4, N'TD001', 9, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221209001', N'CHEHATSEN', 10, N'TD004', 1, 0, NULL)
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221209001', N'COCACOLA', 9, N'TD001', 200, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221209001', N'COCACOLA', 9, N'TD004', -1, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221209001', N'COMCHIENHAISAN', 6, N'TD001', 9, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221209001', N'HOTVITLON', 7, N'TD001', 9, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221209001', N'LAUCATHACLAC', 4, N'TD004', 1, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221209001', N'LAUDE', 8, N'TD001', 9, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221209001', N'LAUNAMCHAY', 8, N'TD004', 1, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221209001', N'NGHEUHAP', 5, N'TD001', 9, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221209001', N'SUPCHAYTHAPCAM', 5, N'TD004', 1, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221209001', N'TAUHU', 2, N'TD004', 1, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221209001', N'TOYEN', 1, N'TD004', 1, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221213002', N'BANHPLAN', 1, N'TD001', 20, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221213002', N'BOLAGU', 2, N'TD001', 20, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221213002', N'CHAOGA', 3, N'TD001', 20, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221213002', N'CHAOVIT', 4, N'TD001', 20, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221213002', N'COCACOLA', 9, N'TD001', -1, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221213002', N'COMCHIENHAISAN', 6, N'TD001', 20, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221213002', N'HOTVITLON', 7, N'TD001', 20, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221213002', N'LAUDE', 8, N'TD001', 20, 0, N'')
INSERT ChiTietDatMon (MaHD, MaMA, ThuTu, MaTD, SoLuong, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221213002', N'NGHEUHAP', 5, N'TD001', 20, 0, N'')
GO
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221101004', N'AOGHEXANH', N'TTBANTIEC', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221101004', N'BANGTEN1', N'TTCONG', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221101004', N'BANGTEN1', N'TTSANKHAU', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221101004', N'CONGTRON', N'TTCONG', 0, 0, N'màu xanh')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221101004', N'HOACUCDO', N'TTCONG', 1000000, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221101004', N'HOACUCDO', N'TTSANKHAU', 1000000, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221101004', N'HOAHONGDO', N'TTCONG', 10000000, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221101004', N'HOAHONGXANH', N'TTBANTIEC', 10000, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221101004', N'HOAHONGXANH', N'TTSANKHAU', 10000, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221101004', N'LIENKHUC1', N'NGHETHUAT', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221101004', N'THAMCO', N'TTSANKHAU', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221101004', N'THAMDO', N'TTCONG', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221101004', N'TRAIBANTRANG', N'TTBANTIEC', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221101004', N'VUDAO1', N'NGHETHUAT', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201001', N'AOGHEDO', N'TTBANTIEC', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201001', N'BANGTEN1', N'TTCONG', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201001', N'BANGTEN1', N'TTSANKHAU', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201001', N'CONGTRON', N'TTCONG', 0, 0, N'màu xanh')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201001', N'HOACUCDO', N'TTCONG', 10000, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201001', N'HOACUCDO', N'TTSANKHAU', 10000000, 0, N'hoa tươi')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201001', N'HOAHONGDO', N'TTBANTIEC', 10000, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201001', N'HOAHONGDO', N'TTCONG', 10000000, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201001', N'HOAHONGDO', N'TTSANKHAU', 10000000, 0, N'hoa tươi')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201001', N'LIENKHUC1', N'NGHETHUAT', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201001', N'THAMDO', N'TTCONG', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201001', N'THAMDO', N'TTSANKHAU', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201001', N'TRAIBANTRANG', N'TTBANTIEC', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201001', N'VUDAO1', N'NGHETHUAT', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201002', N'AOGHEDO', N'TTBANTIEC', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201002', N'BANGTEN1', N'TTCONG', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201002', N'BANGTEN1', N'TTSANKHAU', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201002', N'CONGTRON', N'TTCONG', 0, 0, N'màu xanh')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201002', N'HOACUCDO', N'TTCONG', 1000000, 0, N'hoa tươi')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201002', N'HOACUCDO', N'TTSANKHAU', 1000000, 0, N'hoa tươi')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201002', N'HOAHONGDO', N'TTBANTIEC', 10000, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201002', N'HOAHONGDO', N'TTCONG', 10000000, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201002', N'HOAHONGDO', N'TTSANKHAU', 10000000, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201002', N'LIENKHUC1', N'NGHETHUAT', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201002', N'THAMDO', N'TTCONG', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201002', N'THAMDO', N'TTSANKHAU', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201002', N'TRAIBANTRANG', N'TTBANTIEC', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201002', N'VUDAO1', N'NGHETHUAT', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201003', N'AOGHEDO', N'TTBANTIEC', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201003', N'BANGTEN1', N'TTCONG', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201003', N'BANGTEN1', N'TTSANKHAU', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201003', N'CONGTRON', N'TTCONG', 0, 0, N'màu xanh')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201003', N'HOACUCDO', N'TTSANKHAU', 1000000, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201003', N'HOAHONGDO', N'TTBANTIEC', 10000, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201003', N'HOAHONGDO', N'TTSANKHAU', 1000000, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201003', N'HOAHONGXANH', N'TTCONG', 1000000, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201003', N'HOAHUE', N'TTCONG', 1000000, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201003', N'LIENKHUC1', N'NGHETHUAT', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201003', N'THAMDO', N'TTCONG', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201003', N'THAMDO', N'TTSANKHAU', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201003', N'TRAIBANTRANG', N'TTBANTIEC', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201003', N'VUDAO1', N'NGHETHUAT', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201004', N'AOGHEDO', N'TTBANTIEC', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201004', N'BANGTEN1', N'TTCONG', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201004', N'BANGTEN1', N'TTSANKHAU', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201004', N'CONGTRON', N'TTCONG', 0, 0, N'màu xanh')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201004', N'HOACUCDO', N'TTCONG', 1000000, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201004', N'HOACUCDO', N'TTSANKHAU', 1000000, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201004', N'HOAHONGDO', N'TTBANTIEC', 10000000, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201004', N'HOAHONGDO', N'TTCONG', 2000000, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201004', N'HOAHONGDO', N'TTSANKHAU', 1000000, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201004', N'LIENKHUC1', N'NGHETHUAT', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201004', N'THAMDO', N'TTCONG', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201004', N'THAMDO', N'TTSANKHAU', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201004', N'TRAIBANTRANG', N'TTBANTIEC', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201004', N'VUDAO1', N'NGHETHUAT', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201005', N'AOGHEDO', N'TTBANTIEC', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201005', N'BANGTEN1', N'TTCONG', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201005', N'BANGTEN1', N'TTSANKHAU', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201005', N'CONGTRON', N'TTCONG', 0, 0, N'màu xanh')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201005', N'HOACUCDO', N'TTCONG', 10000, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201005', N'HOACUCDO', N'TTSANKHAU', 1000000, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201005', N'HOAHONGDO', N'TTBANTIEC', 10000, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201005', N'HOAHONGXANH', N'TTCONG', 10000000, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201005', N'HOAHONGXANH', N'TTSANKHAU', 10000, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201005', N'LIENKHUC1', N'NGHETHUAT', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201005', N'THAMDO', N'TTCONG', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201005', N'THAMDO', N'TTSANKHAU', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201005', N'TRAIBANTRANG', N'TTBANTIEC', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201005', N'VUDAO2', N'NGHETHUAT', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201006', N'AOGHEDO', N'TTBANTIEC', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201006', N'BANGTEN1', N'TTCONG', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201006', N'BANGTEN1', N'TTSANKHAU', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201006', N'CONGTRON', N'TTCONG', 0, 0, N'màu xanh')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201006', N'HOACUCDO', N'TTCONG', 10000, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201006', N'HOACUCDO', N'TTSANKHAU', 1000000, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201006', N'HOAHONGDO', N'TTBANTIEC', 10000, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201006', N'HOAHONGDO', N'TTCONG', 10000, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201006', N'HOAHONGDO', N'TTSANKHAU', 1000000, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201006', N'LIENKHUC1', N'NGHETHUAT', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201006', N'THAMDO', N'TTCONG', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201006', N'THAMDO', N'TTSANKHAU', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201006', N'TRAIBANTRANG', N'TTBANTIEC', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201006', N'VUDAO1', N'NGHETHUAT', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201007', N'AOGHEDO', N'TTBANTIEC', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201007', N'BANGTEN1', N'TTCONG', 0, 0, N'')
GO
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201007', N'BANGTEN1', N'TTSANKHAU', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201007', N'CONGTRON', N'TTCONG', 0, 0, N'màu xanh')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201007', N'HOACUCDO', N'TTCONG', 10000, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201007', N'HOACUCDO', N'TTSANKHAU', 1000000, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201007', N'HOAHONGDO', N'TTBANTIEC', 10000, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201007', N'HOAHONGDO', N'TTCONG', 10000, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201007', N'HOAHONGDO', N'TTSANKHAU', 1000000, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201007', N'LIENKHUC1', N'NGHETHUAT', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201007', N'THAMDO', N'TTCONG', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201007', N'THAMDO', N'TTSANKHAU', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201007', N'TRAIBANTRANG', N'TTBANTIEC', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201007', N'VUDAO1', N'NGHETHUAT', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201008', N'AOGHEDO', N'TTBANTIEC', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201008', N'BANGTEN1', N'TTCONG', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201008', N'BANGTEN1', N'TTSANKHAU', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201008', N'CONGTRON', N'TTCONG', 0, 0, N'màu xanh')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201008', N'HOACUCDO', N'TTCONG', 10000, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201008', N'HOACUCDO', N'TTSANKHAU', 1000000, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201008', N'HOAHONGDO', N'TTBANTIEC', 10000, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201008', N'HOAHONGDO', N'TTCONG', 10000, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201008', N'HOAHONGXANH', N'TTSANKHAU', 10000, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201008', N'LIENKHUC1', N'NGHETHUAT', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201008', N'THAMCO', N'TTSANKHAU', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201008', N'THAMDO', N'TTCONG', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201008', N'TRAIBANTRANG', N'TTBANTIEC', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201008', N'VUDAO1', N'NGHETHUAT', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201009', N'AOGHEDO', N'TTBANTIEC', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201009', N'BANGTEN1', N'TTCONG', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201009', N'BANGTEN1', N'TTSANKHAU', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201009', N'CONGTRON', N'TTCONG', 0, 0, N'màu xanh')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201009', N'HOACUCDO', N'TTCONG', 10000, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201009', N'HOACUCDO', N'TTSANKHAU', 1000000, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201009', N'HOAHONGDO', N'TTBANTIEC', 10000, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201009', N'HOAHONGDO', N'TTCONG', 10000, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201009', N'HOAHONGXANH', N'TTSANKHAU', 10000, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201009', N'LIENKHUC1', N'NGHETHUAT', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201009', N'THAMCO', N'TTSANKHAU', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201009', N'THAMDO', N'TTCONG', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201009', N'TRAIBANTRANG', N'TTBANTIEC', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201009', N'VUDAO1', N'NGHETHUAT', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201010', N'AOGHEDO', N'TTBANTIEC', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201010', N'BANGTEN1', N'TTCONG', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201010', N'BANGTEN1', N'TTSANKHAU', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201010', N'CONGTRON', N'TTCONG', 0, 0, N'màu xanh')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201010', N'HOACUCDO', N'TTCONG', 10000, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201010', N'HOACUCDO', N'TTSANKHAU', 1000000, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201010', N'HOAHONGDO', N'TTBANTIEC', 10000, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201010', N'HOAHONGDO', N'TTCONG', 10000, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201010', N'HOAHONGXANH', N'TTSANKHAU', 10000, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201010', N'LIENKHUC1', N'NGHETHUAT', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201010', N'THAMCO', N'TTSANKHAU', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201010', N'THAMDO', N'TTCONG', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201010', N'TRAIBANTRANG', N'TTBANTIEC', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201010', N'VUDAO1', N'NGHETHUAT', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221205011', N'AOGHEDO', N'TTBANTIEC', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221205011', N'BANGTEN1', N'TTCONG', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221205011', N'BANGTEN1', N'TTSANKHAU', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221205011', N'CONGTRON', N'TTCONG', 0, 0, N'màu xanh')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221205011', N'HOACUCDO', N'TTCONG', 10000, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221205011', N'HOACUCDO', N'TTSANKHAU', 1000000, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221205011', N'HOAHONGDO', N'TTBANTIEC', 10000, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221205011', N'HOAHONGDO', N'TTCONG', 10000, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221205011', N'HOAHONGDO', N'TTSANKHAU', 1000000, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221205011', N'LIENKHUC1', N'NGHETHUAT', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221205011', N'THAMDO', N'TTCONG', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221205011', N'THAMDO', N'TTSANKHAU', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221205011', N'TRAIBANTRANG', N'TTBANTIEC', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221205011', N'VUDAO1', N'NGHETHUAT', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221206001', N'AOGHEXANH', N'TTBANTIEC', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221206001', N'BANGTEN1', N'TTCONG', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221206001', N'BANGTEN1', N'TTSANKHAU', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221206001', N'CONGTRON', N'TTCONG', 0, 0, N'màu xanh')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221206001', N'HOACUCDO', N'TTCONG', 10000, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221206001', N'HOACUCDO', N'TTSANKHAU', 1000000, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221206001', N'HOAHONGDO', N'TTCONG', 10000, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221206001', N'HOAHONGDO', N'TTSANKHAU', 1000000, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221206001', N'HOAHONGXANH', N'TTBANTIEC', 10000, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221206001', N'LIENKHUC1', N'NGHETHUAT', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221206001', N'THAMDO', N'TTCONG', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221206001', N'THAMDO', N'TTSANKHAU', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221206001', N'TRAIBANVANG', N'TTBANTIEC', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221206001', N'VUDAO1', N'NGHETHUAT', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221206002', N'AOGHEDO', N'TTBANTIEC', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221206002', N'BANGTEN1', N'TTCONG', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221206002', N'BANGTEN1', N'TTSANKHAU', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221206002', N'CONGTRON', N'TTCONG', 0, 0, N'màu xanh')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221206002', N'HOACUCDO', N'TTCONG', 10000, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221206002', N'HOACUCDO', N'TTSANKHAU', 1000000, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221206002', N'HOAHONGDO', N'TTBANTIEC', 10000, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221206002', N'HOAHONGDO', N'TTCONG', 10000, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221206002', N'HOAHONGDO', N'TTSANKHAU', 1000000, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221206002', N'LIENKHUC1', N'NGHETHUAT', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221206002', N'THAMDO', N'TTCONG', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221206002', N'THAMDO', N'TTSANKHAU', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221206002', N'TRAIBANTRANG', N'TTBANTIEC', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221206002', N'VUDAO1', N'NGHETHUAT', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221207001', N'AOGHEDO', N'TTBANTIEC', 0, 1000000, N'thêm nơ ')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221207001', N'BANGTEN1', N'TTCONG', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221207001', N'BANGTEN1', N'TTSANKHAU', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221207001', N'CONGTRON', N'TTCONG', 0, 0, N'màu xanh')
GO
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221207001', N'HOACUCDO', N'TTCONG', 10000, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221207001', N'HOACUCDO', N'TTSANKHAU', 1000000, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221207001', N'HOAHONGDO', N'TTCONG', 10000, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221207001', N'HOAHONGDO', N'TTSANKHAU', 1000000, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221207001', N'HOAHONGXANH', N'TTBANTIEC', 15000000, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221207001', N'LIENKHUC1', N'NGHETHUAT', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221207001', N'THAMDO', N'TTCONG', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221207001', N'THAMDO', N'TTSANKHAU', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221207001', N'TRAIBANTRANG', N'TTBANTIEC', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221207001', N'VUDAO1', N'NGHETHUAT', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221207002', N'AOGHEXANH', N'TTBANTIEC', 0, 0, N'thêm nơ')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221207002', N'BANGTEN1', N'TTCONG', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221207002', N'BANGTEN1', N'TTSANKHAU', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221207002', N'CONGOVAN', N'TTCONG', 0, 0, N'màu xanh')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221207002', N'HOACUCDO', N'TTCONG', 10000, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221207002', N'HOACUCDO', N'TTSANKHAU', 1000000, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221207002', N'HOAHONGDO', N'TTBANTIEC', 15000000, 0, N'hoa tươi')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221207002', N'HOAHONGDO', N'TTCONG', 10000, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221207002', N'HOAHONGDO', N'TTSANKHAU', 1000000, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221207002', N'LIENKHUC1', N'NGHETHUAT', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221207002', N'THAMDO', N'TTCONG', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221207002', N'THAMDO', N'TTSANKHAU', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221207002', N'TRAIBANTRANG', N'TTBANTIEC', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221207002', N'VUDAO1', N'NGHETHUAT', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221207003', N'AOGHEDO', N'TTBANTIEC', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221207003', N'BANGTEN1', N'TTCONG', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221207003', N'BANGTEN1', N'TTSANKHAU', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221207003', N'CONGTRON', N'TTCONG', 0, 0, N'màu xanh')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221207003', N'HOACUCDO', N'TTCONG', 10000, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221207003', N'HOACUCDO', N'TTSANKHAU', 1000000, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221207003', N'HOAHONGDO', N'TTBANTIEC', 10000, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221207003', N'HOAHONGDO', N'TTCONG', 10000, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221207003', N'HOAHONGDO', N'TTSANKHAU', 1000000, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221207003', N'LIENKHUC1', N'NGHETHUAT', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221207003', N'THAMDO', N'TTCONG', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221207003', N'THAMDO', N'TTSANKHAU', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221207003', N'TRAIBANTRANG', N'TTBANTIEC', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221207003', N'VUDAO1', N'NGHETHUAT', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221208001', N'AOGHEDO', N'TTBANTIEC', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221208001', N'BANGTEN1', N'TTCONG', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221208001', N'BANGTEN1', N'TTSANKHAU', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221208001', N'CONGTRON', N'TTCONG', 0, 0, N'màu xanh')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221208001', N'HOACUCDO', N'TTCONG', 10000, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221208001', N'HOACUCDO', N'TTSANKHAU', 1000000, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221208001', N'HOAHONGDO', N'TTBANTIEC', 10000, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221208001', N'HOAHONGDO', N'TTCONG', 10000, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221208001', N'HOAHONGDO', N'TTSANKHAU', 1000000, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221208001', N'LIENKHUC1', N'NGHETHUAT', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221208001', N'THAMDO', N'TTCONG', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221208001', N'THAMDO', N'TTSANKHAU', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221208001', N'TRAIBANTRANG', N'TTBANTIEC', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221208001', N'VUDAO1', N'NGHETHUAT', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221209001', N'AOGHEXANH', N'TTBANTIEC', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221209001', N'BANGTEN1', N'TTCONG', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221209001', N'BANGTEN1', N'TTSANKHAU', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221209001', N'CONGOVAN', N'TTCONG', 0, 0, N'màu xanh')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221209001', N'HOACUCDO', N'TTCONG', 10000000, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221209001', N'HOACUCDO', N'TTSANKHAU', 1000000, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221209001', N'HOAHONGDO', N'TTCONG', 10000000, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221209001', N'HOAHONGXANH', N'TTBANTIEC', 10000000, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221209001', N'HOAHONGXANH', N'TTSANKHAU', 10000, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221209001', N'LIENKHUC1', N'NGHETHUAT', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221209001', N'THAMCO', N'TTSANKHAU', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221209001', N'THAMDO', N'TTCONG', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221209001', N'TRAIBANTRANG', N'TTBANTIEC', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221209001', N'VUDAO1', N'NGHETHUAT', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221213002', N'AOGHEXANH', N'TTBANTIEC', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221213002', N'BANGTEN1', N'TTCONG', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221213002', N'BANGTEN1', N'TTSANKHAU', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221213002', N'CONGTRON', N'TTCONG', 0, 0, N'màu xanh')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221213002', N'HOACUCDO', N'TTCONG', 10000, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221213002', N'HOACUCDO', N'TTSANKHAU', 1000000, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221213002', N'HOAHONGDO', N'TTCONG', 10000, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221213002', N'HOAHONGDO', N'TTSANKHAU', 1000000, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221213002', N'HOAHONGXANH', N'TTBANTIEC', 10000, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221213002', N'LIENKHUC1', N'NGHETHUAT', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221213002', N'THAMDO', N'TTCONG', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221213002', N'THAMDO', N'TTSANKHAU', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221213002', N'TRAIBANVANG', N'TTBANTIEC', 0, 0, N'')
INSERT ChiTietDichVu (MaHD, MaCSVC, MaDV, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221213002', N'VUDAO1', N'NGHETHUAT', 0, 0, N'')
GO

INSERT ChiTietDichVuDiKem (MaHD, MaDV, GhiChu, ChiPhi, ChiPhiPhatSinh, SoLuong) VALUES (N'HD20221101004', N'BANHKEM', N'', 1500000, 0, 1)
INSERT ChiTietDichVuDiKem (MaHD, MaDV, GhiChu, ChiPhi, ChiPhiPhatSinh, SoLuong) VALUES (N'HD20221101004', N'MAYTAOKHOI', N'', 2000000, 0, 1)
INSERT ChiTietDichVuDiKem (MaHD, MaDV, GhiChu, ChiPhi, ChiPhiPhatSinh, SoLuong) VALUES (N'HD20221101004', N'PHAOKIMTUYEN', N'', 100000, 0, 10)
INSERT ChiTietDichVuDiKem (MaHD, MaDV, GhiChu, ChiPhi, ChiPhiPhatSinh, SoLuong) VALUES (N'HD20221101004', N'THIENNGABANG', N'', 4000000, 0, 1)
INSERT ChiTietDichVuDiKem (MaHD, MaDV, GhiChu, ChiPhi, ChiPhiPhatSinh, SoLuong) VALUES (N'HD20221201001', N'BANHKEM', N'', 1500000, 0, 1)
INSERT ChiTietDichVuDiKem (MaHD, MaDV, GhiChu, ChiPhi, ChiPhiPhatSinh, SoLuong) VALUES (N'HD20221201001', N'MAYTAOKHOI', N'', 2000000, 0, 1)
INSERT ChiTietDichVuDiKem (MaHD, MaDV, GhiChu, ChiPhi, ChiPhiPhatSinh, SoLuong) VALUES (N'HD20221201001', N'PHAOKIMTUYEN', N'', 100000, 0, 15)
INSERT ChiTietDichVuDiKem (MaHD, MaDV, GhiChu, ChiPhi, ChiPhiPhatSinh, SoLuong) VALUES (N'HD20221201001', N'THIENNGABANG', N'', 4000000, 0, 1)
INSERT ChiTietDichVuDiKem (MaHD, MaDV, GhiChu, ChiPhi, ChiPhiPhatSinh, SoLuong) VALUES (N'HD20221201002', N'BANHKEM', N'', 1500000, 0, 1)
INSERT ChiTietDichVuDiKem (MaHD, MaDV, GhiChu, ChiPhi, ChiPhiPhatSinh, SoLuong) VALUES (N'HD20221201002', N'MAYTAOKHOI', N'', 2000000, 0, 1)
INSERT ChiTietDichVuDiKem (MaHD, MaDV, GhiChu, ChiPhi, ChiPhiPhatSinh, SoLuong) VALUES (N'HD20221201002', N'PHAOKIMTUYEN', N'', 100000, 0, -1)
INSERT ChiTietDichVuDiKem (MaHD, MaDV, GhiChu, ChiPhi, ChiPhiPhatSinh, SoLuong) VALUES (N'HD20221201002', N'THIENNGABANG', N'', 4000000, 0, 1)
INSERT ChiTietDichVuDiKem (MaHD, MaDV, GhiChu, ChiPhi, ChiPhiPhatSinh, SoLuong) VALUES (N'HD20221201003', N'MAYTAOKHOI', N'', 2000000, 0, 1)
INSERT ChiTietDichVuDiKem (MaHD, MaDV, GhiChu, ChiPhi, ChiPhiPhatSinh, SoLuong) VALUES (N'HD20221201003', N'PHAOKIMTUYEN', N'', 100000, 0, 0)
INSERT ChiTietDichVuDiKem (MaHD, MaDV, GhiChu, ChiPhi, ChiPhiPhatSinh, SoLuong) VALUES (N'HD20221201003', N'THIENNGABANG', N'', 4000000, 0, 1)
INSERT ChiTietDichVuDiKem (MaHD, MaDV, GhiChu, ChiPhi, ChiPhiPhatSinh, SoLuong) VALUES (N'HD20221201004', N'BANHKEM', N'', 1500000, 0, 1)
INSERT ChiTietDichVuDiKem (MaHD, MaDV, GhiChu, ChiPhi, ChiPhiPhatSinh, SoLuong) VALUES (N'HD20221201004', N'MAYTAOKHOI', N'', 2000000, 0, 1)
INSERT ChiTietDichVuDiKem (MaHD, MaDV, GhiChu, ChiPhi, ChiPhiPhatSinh, SoLuong) VALUES (N'HD20221201004', N'PHAOKIMTUYEN', N'', 100000, 0, -1)
INSERT ChiTietDichVuDiKem (MaHD, MaDV, GhiChu, ChiPhi, ChiPhiPhatSinh, SoLuong) VALUES (N'HD20221201005', N'BANHKEM', N'', 1500000, 0, 1)
INSERT ChiTietDichVuDiKem (MaHD, MaDV, GhiChu, ChiPhi, ChiPhiPhatSinh, SoLuong) VALUES (N'HD20221201005', N'MAYTAOKHOI', N'', 2000000, 0, 1)
INSERT ChiTietDichVuDiKem (MaHD, MaDV, GhiChu, ChiPhi, ChiPhiPhatSinh, SoLuong) VALUES (N'HD20221201005', N'THIENNGABANG', N'', 4000000, 0, 1)
INSERT ChiTietDichVuDiKem (MaHD, MaDV, GhiChu, ChiPhi, ChiPhiPhatSinh, SoLuong) VALUES (N'HD20221201006', N'BANHKEM', N'', 1500000, 0, 1)
INSERT ChiTietDichVuDiKem (MaHD, MaDV, GhiChu, ChiPhi, ChiPhiPhatSinh, SoLuong) VALUES (N'HD20221201006', N'MAYTAOKHOI', N'', 2000000, 0, 1)
INSERT ChiTietDichVuDiKem (MaHD, MaDV, GhiChu, ChiPhi, ChiPhiPhatSinh, SoLuong) VALUES (N'HD20221201006', N'THIENNGABANG', N'', 4000000, 0, 1)
INSERT ChiTietDichVuDiKem (MaHD, MaDV, GhiChu, ChiPhi, ChiPhiPhatSinh, SoLuong) VALUES (N'HD20221201007', N'BANHKEM', N'', 1500000, 0, 1)
INSERT ChiTietDichVuDiKem (MaHD, MaDV, GhiChu, ChiPhi, ChiPhiPhatSinh, SoLuong) VALUES (N'HD20221201007', N'MAYTAOKHOI', N'', 2000000, 0, 1)
INSERT ChiTietDichVuDiKem (MaHD, MaDV, GhiChu, ChiPhi, ChiPhiPhatSinh, SoLuong) VALUES (N'HD20221201007', N'PHAOKIMTUYEN', N'', 100000, 0, -1)
INSERT ChiTietDichVuDiKem (MaHD, MaDV, GhiChu, ChiPhi, ChiPhiPhatSinh, SoLuong) VALUES (N'HD20221201007', N'THIENNGABANG', N'', 4000000, 0, 1)
INSERT ChiTietDichVuDiKem (MaHD, MaDV, GhiChu, ChiPhi, ChiPhiPhatSinh, SoLuong) VALUES (N'HD20221201008', N'BANHKEM', N'', 1500000, 0, 1)
INSERT ChiTietDichVuDiKem (MaHD, MaDV, GhiChu, ChiPhi, ChiPhiPhatSinh, SoLuong) VALUES (N'HD20221201008', N'MAYTAOKHOI', N'', 2000000, 0, 1)
INSERT ChiTietDichVuDiKem (MaHD, MaDV, GhiChu, ChiPhi, ChiPhiPhatSinh, SoLuong) VALUES (N'HD20221201008', N'THIENNGABANG', N'', 4000000, 0, 1)
INSERT ChiTietDichVuDiKem (MaHD, MaDV, GhiChu, ChiPhi, ChiPhiPhatSinh, SoLuong) VALUES (N'HD20221201009', N'MAYTAOKHOI', N'', 2000000, 0, 1)
INSERT ChiTietDichVuDiKem (MaHD, MaDV, GhiChu, ChiPhi, ChiPhiPhatSinh, SoLuong) VALUES (N'HD20221201009', N'PHAOKIMTUYEN', N'', 100000, 0, -1)
INSERT ChiTietDichVuDiKem (MaHD, MaDV, GhiChu, ChiPhi, ChiPhiPhatSinh, SoLuong) VALUES (N'HD20221201009', N'THIENNGABANG', N'', 4000000, 0, 1)
INSERT ChiTietDichVuDiKem (MaHD, MaDV, GhiChu, ChiPhi, ChiPhiPhatSinh, SoLuong) VALUES (N'HD20221205011', N'MAYTAOKHOI', N'', 2000000, 0, 1)
INSERT ChiTietDichVuDiKem (MaHD, MaDV, GhiChu, ChiPhi, ChiPhiPhatSinh, SoLuong) VALUES (N'HD20221206001', N'BANHKEM', N'', 1500000, 0, 1)
INSERT ChiTietDichVuDiKem (MaHD, MaDV, GhiChu, ChiPhi, ChiPhiPhatSinh, SoLuong) VALUES (N'HD20221206001', N'PHAOKIMTUYEN', N'', 100000, 20000, 15)
INSERT ChiTietDichVuDiKem (MaHD, MaDV, GhiChu, ChiPhi, ChiPhiPhatSinh, SoLuong) VALUES (N'HD20221206002', N'BANHKEM', N'', 1500000, 0, 1)
INSERT ChiTietDichVuDiKem (MaHD, MaDV, GhiChu, ChiPhi, ChiPhiPhatSinh, SoLuong) VALUES (N'HD20221206002', N'MAYTAOKHOI', N'', 2000000, 0, 1)
INSERT ChiTietDichVuDiKem (MaHD, MaDV, GhiChu, ChiPhi, ChiPhiPhatSinh, SoLuong) VALUES (N'HD20221206002', N'PHAOKIMTUYEN', N'', 100000, 0, 50)
INSERT ChiTietDichVuDiKem (MaHD, MaDV, GhiChu, ChiPhi, ChiPhiPhatSinh, SoLuong) VALUES (N'HD20221206002', N'THIENNGABANG', N'', 4000000, 0, 1)
INSERT ChiTietDichVuDiKem (MaHD, MaDV, GhiChu, ChiPhi, ChiPhiPhatSinh, SoLuong) VALUES (N'HD20221207001', N'BANHKEM', N'3 tầng', 1500000, 0, 1)
INSERT ChiTietDichVuDiKem (MaHD, MaDV, GhiChu, ChiPhi, ChiPhiPhatSinh, SoLuong) VALUES (N'HD20221207001', N'MAYTAOKHOI', N'', 2000000, 0, 1)
INSERT ChiTietDichVuDiKem (MaHD, MaDV, GhiChu, ChiPhi, ChiPhiPhatSinh, SoLuong) VALUES (N'HD20221207001', N'PHAOKIMTUYEN', N'', 100000, 0, 15)
INSERT ChiTietDichVuDiKem (MaHD, MaDV, GhiChu, ChiPhi, ChiPhiPhatSinh, SoLuong) VALUES (N'HD20221207001', N'THIENNGABANG', N'', 4000000, 0, 1)
INSERT ChiTietDichVuDiKem (MaHD, MaDV, GhiChu, ChiPhi, ChiPhiPhatSinh, SoLuong) VALUES (N'HD20221207002', N'BANHKEM', N'3 tầng', 1500000, 10000, 2)
INSERT ChiTietDichVuDiKem (MaHD, MaDV, GhiChu, ChiPhi, ChiPhiPhatSinh, SoLuong) VALUES (N'HD20221207002', N'MAYTAOKHOI', N'', 2000000, 0, 3)
INSERT ChiTietDichVuDiKem (MaHD, MaDV, GhiChu, ChiPhi, ChiPhiPhatSinh, SoLuong) VALUES (N'HD20221207002', N'PHAOKIMTUYEN', N'', 100000, 0, 50)
INSERT ChiTietDichVuDiKem (MaHD, MaDV, GhiChu, ChiPhi, ChiPhiPhatSinh, SoLuong) VALUES (N'HD20221207002', N'THIENNGABANG', N'', 4000000, 0, 1)
INSERT ChiTietDichVuDiKem (MaHD, MaDV, GhiChu, ChiPhi, ChiPhiPhatSinh, SoLuong) VALUES (N'HD20221207003', N'BANHKEM', N'', 1500000, 0, 1)
INSERT ChiTietDichVuDiKem (MaHD, MaDV, GhiChu, ChiPhi, ChiPhiPhatSinh, SoLuong) VALUES (N'HD20221207003', N'MAYTAOKHOI', N'', 2000000, 0, 5)
INSERT ChiTietDichVuDiKem (MaHD, MaDV, GhiChu, ChiPhi, ChiPhiPhatSinh, SoLuong) VALUES (N'HD20221207003', N'PHAOKIMTUYEN', N'', 100000, 0, 10)
INSERT ChiTietDichVuDiKem (MaHD, MaDV, GhiChu, ChiPhi, ChiPhiPhatSinh, SoLuong) VALUES (N'HD20221207003', N'THIENNGABANG', N'', 4000000, 0, 2)
INSERT ChiTietDichVuDiKem (MaHD, MaDV, GhiChu, ChiPhi, ChiPhiPhatSinh, SoLuong) VALUES (N'HD20221208001', N'BANHKEM', N'', 1500000, 0, 1)
INSERT ChiTietDichVuDiKem (MaHD, MaDV, GhiChu, ChiPhi, ChiPhiPhatSinh, SoLuong) VALUES (N'HD20221208001', N'MAYTAOKHOI', N'', 2000000, 0, 1)
INSERT ChiTietDichVuDiKem (MaHD, MaDV, GhiChu, ChiPhi, ChiPhiPhatSinh, SoLuong) VALUES (N'HD20221208001', N'PHAOKIMTUYEN', N'', 100000, 0, -1)
INSERT ChiTietDichVuDiKem (MaHD, MaDV, GhiChu, ChiPhi, ChiPhiPhatSinh, SoLuong) VALUES (N'HD20221208001', N'THIENNGABANG', N'', 4000000, 0, 1)
INSERT ChiTietDichVuDiKem (MaHD, MaDV, GhiChu, ChiPhi, ChiPhiPhatSinh, SoLuong) VALUES (N'HD20221209001', N'BANHKEM', N'', 1500000, 0, 2)
INSERT ChiTietDichVuDiKem (MaHD, MaDV, GhiChu, ChiPhi, ChiPhiPhatSinh, SoLuong) VALUES (N'HD20221209001', N'MAYTAOKHOI', N'', 2000000, 0, 3)
INSERT ChiTietDichVuDiKem (MaHD, MaDV, GhiChu, ChiPhi, ChiPhiPhatSinh, SoLuong) VALUES (N'HD20221209001', N'PHAOKIMTUYEN', N'', 100000, 0, 15)
INSERT ChiTietDichVuDiKem (MaHD, MaDV, GhiChu, ChiPhi, ChiPhiPhatSinh, SoLuong) VALUES (N'HD20221209001', N'THIENNGABANG', N'', 4000000, 0, 2)
INSERT ChiTietDichVuDiKem (MaHD, MaDV, GhiChu, ChiPhi, ChiPhiPhatSinh, SoLuong) VALUES (N'HD20221213002', N'BANHKEM', N'', 1500000, 0, 1)
INSERT ChiTietDichVuDiKem (MaHD, MaDV, GhiChu, ChiPhi, ChiPhiPhatSinh, SoLuong) VALUES (N'HD20221213002', N'MAYTAOKHOI', N'', 2000000, 0, 1)
INSERT ChiTietDichVuDiKem (MaHD, MaDV, GhiChu, ChiPhi, ChiPhiPhatSinh, SoLuong) VALUES (N'HD20221213002', N'PHAOKIMTUYEN', N'', 100000, 0, -1)
INSERT ChiTietDichVuDiKem (MaHD, MaDV, GhiChu, ChiPhi, ChiPhiPhatSinh, SoLuong) VALUES (N'HD20221213002', N'THIENNGABANG', N'', 4000000, 0, 1)
GO

GO
INSERT MonAn (MaMA, TenMA, HinhAnh, GiaTien, MaPL) VALUES (N'7UP', N'7up', N'noimg.png', N'18000', N'NUOC')
INSERT MonAn (MaMA, TenMA, HinhAnh, GiaTien, MaPL) VALUES (N'AQUA', N'Nước uống Aqua', N'noimg.png', N'10000', N'NUOC')
INSERT MonAn (MaMA, TenMA, HinhAnh, GiaTien, MaPL) VALUES (N'BANHMOCHI', N'Bánh mochi Nhật', N'noimg.png', N'200000', N'TRANGMIENG')
INSERT MonAn (MaMA, TenMA, HinhAnh, GiaTien, MaPL) VALUES (N'BANHPLAN', N'Bánh Flan', N'noimg.png', N'10000', N'TRANGMIENG')
INSERT MonAn (MaMA, TenMA, HinhAnh, GiaTien, MaPL) VALUES (N'BIA333', N'Bia 333', N'noimg.png', N'25000', N'NUOC')
INSERT MonAn (MaMA, TenMA, HinhAnh, GiaTien, MaPL) VALUES (N'BOCUBE', N'Bò cube nướng Parasama', N'noimg.png', N'1700000', N'MONCHINH')
INSERT MonAn (MaMA, TenMA, HinhAnh, GiaTien, MaPL) VALUES (N'BOLAGU', N'Bò nấu lagu ', N'noimg.png', N'670000', N'MONCHINH')
INSERT MonAn (MaMA, TenMA, HinhAnh, GiaTien, MaPL) VALUES (N'BOLUCLAC', N'Bò lúc lắc sốt tiêu', N'noimg.png', N'900000', N'MONPHU')
INSERT MonAn (MaMA, TenMA, HinhAnh, GiaTien, MaPL) VALUES (N'CANGCUABACHHOA', N'Càng cua bách hoa', N'noimg.png', N'700000', N'KHAIVI')
INSERT MonAn (MaMA, TenMA, HinhAnh, GiaTien, MaPL) VALUES (N'CARIGA', N'Cà ri gà', N'noimg.png', N'400000', N'MONPHU')
INSERT MonAn (MaMA, TenMA, HinhAnh, GiaTien, MaPL) VALUES (N'CHAOGA', N'Cháo gà', N'noimg.png', N'150000', N'KHAIVI')
INSERT MonAn (MaMA, TenMA, HinhAnh, GiaTien, MaPL) VALUES (N'CHAOVIT', N'Cháo vịt', N'noimg.png', N'300000', N'MONCHINH')
INSERT MonAn (MaMA, TenMA, HinhAnh, GiaTien, MaPL) VALUES (N'CHEHATSEN', N'Chè dưỡng nhan hạt sen', N'asc.png', N'200000', N'TRANGMIENG')
INSERT MonAn (MaMA, TenMA, HinhAnh, GiaTien, MaPL) VALUES (N'COCACOLA', N'Nước Cocacola', N'noimg.png', N'20000', N'NUOC')
INSERT MonAn (MaMA, TenMA, HinhAnh, GiaTien, MaPL) VALUES (N'COMCHIENHAISAN', N'Cơm chiên hải sản', N'noimg.png', N'100000', N'MONPHU')
INSERT MonAn (MaMA, TenMA, HinhAnh, GiaTien, MaPL) VALUES (N'CUAHOANGDE', N'Cua hoàng đế hấp rượu', N'noimg.png', N'8000000', N'MONCHINH')
INSERT MonAn (MaMA, TenMA, HinhAnh, GiaTien, MaPL) VALUES (N'GABOXOI', N'Gà bó xôi chiên', N'noimg.png', N'280000', N'MONPHU')
INSERT MonAn (MaMA, TenMA, HinhAnh, GiaTien, MaPL) VALUES (N'GAHAP', N'Gà hấp bẹ cải xanh', N'noimg.png', N'450000', N'MONCHINH')
INSERT MonAn (MaMA, TenMA, HinhAnh, GiaTien, MaPL) VALUES (N'GAQUAYNGUVI', N'Gà quay ngũ vị', N'noimg.png', N'1000000', N'MONPHU')
INSERT MonAn (MaMA, TenMA, HinhAnh, GiaTien, MaPL) VALUES (N'GOIBOBOP', N'Gỏi bò bóp thấu', N'noimg.png', N'120000', N'KHAIVI')
INSERT MonAn (MaMA, TenMA, HinhAnh, GiaTien, MaPL) VALUES (N'GOIDUDU', N'Gỏi đu đủ', N'noimg.png', N'90000', N'KHAIVI')
INSERT MonAn (MaMA, TenMA, HinhAnh, GiaTien, MaPL) VALUES (N'HEOQUAY', N'Heo quay', N'noimg.png', N'600000', N'MONPHU')
INSERT MonAn (MaMA, TenMA, HinhAnh, GiaTien, MaPL) VALUES (N'HOTVITLON', N'Hột vịt lộn', N'noimg.png', N'10000', N'MONPHU')
INSERT MonAn (MaMA, TenMA, HinhAnh, GiaTien, MaPL) VALUES (N'LAUCABOP', N'Lẫu cá bớp', N'noimg.png', N'1100000', N'MONCHINH')
INSERT MonAn (MaMA, TenMA, HinhAnh, GiaTien, MaPL) VALUES (N'LAUDE', N'Lẩu dê', N'noimg.png', N'500000', N'MONCHINH')
INSERT MonAn (MaMA, TenMA, HinhAnh, GiaTien, MaPL) VALUES (N'NGHEUHAP', N'Nghêu hấp thái', N'noimg.png', N'130000', N'MONPHU')
INSERT MonAn (MaMA, TenMA, HinhAnh, GiaTien, MaPL) VALUES (N'PEPSI', N'Nước Pepsi', N'noimg.png', N'20000', N'NUOC')
INSERT MonAn (MaMA, TenMA, HinhAnh, GiaTien, MaPL) VALUES (N'RAUCAU', N'Rau cau', N'noimg.png', N'100000', N'TRANGMIENG')
INSERT MonAn (MaMA, TenMA, HinhAnh, GiaTien, MaPL) VALUES (N'RUOUVANG', N'Rượu vang Đà lạt', N'noimg.png', N'500000', N'NUOC')
INSERT MonAn (MaMA, TenMA, HinhAnh, GiaTien, MaPL) VALUES (N'SUACHUA', N'Sữa chua', N'noimg', N'10000', N'TRANGMIENG')
INSERT MonAn (MaMA, TenMA, HinhAnh, GiaTien, MaPL) VALUES (N'SUPBAPCUA', N'Súp bắp cua', N'noimg.png', N'150000', N'KHAIVI')
INSERT MonAn (MaMA, TenMA, HinhAnh, GiaTien, MaPL) VALUES (N'SUPHAISAN', N'Súp hải sản', N'noimg.png', N'200000', N'KHAIVI')
INSERT MonAn (MaMA, TenMA, HinhAnh, GiaTien, MaPL) VALUES (N'TOYEN', N'Tổ yến', N'noimg.png', N'550000', N'TRANGMIENG')
INSERT MonAn (MaMA, TenMA, HinhAnh, GiaTien, MaPL) VALUES (N'TAUHU', N'Tàu Hủ Ky Cuộn Bắp Non', N'noimg.png', N'100000', N'MONCHINH')
INSERT MonAn (MaMA, TenMA, HinhAnh, GiaTien, MaPL) VALUES (N'BOSOTTIEUXANH', N'Bò Sốt Tiêu Xanh Bánh Mì', N'noimg.png', N'100000', N'MONCHINH')
INSERT MonAn (MaMA, TenMA, HinhAnh, GiaTien, MaPL) VALUES (N'LAUCATHACLAC', N'Lẩu Cá Thác Lác', N'noimg.png', N'100000', N'MONCHINH')
INSERT MonAn (MaMA, TenMA, HinhAnh, GiaTien, MaPL) VALUES (N'SUPCHAYTHAPCAM', N'Súp chay thập cẩm', N'noimg.png', N'130000', N'KHAIVI')
INSERT MonAn (MaMA, TenMA, HinhAnh, GiaTien, MaPL) VALUES (N'BICUONCHAY', N'Bì cuốn chay', N'noimg.png', N'130000', N'KHAIVI')
INSERT MonAn (MaMA, TenMA, HinhAnh, GiaTien, MaPL) VALUES (N'GOINGONSEN', N'Gỏi ngó sen tôm chay', N'noimg.png', N'430000', N'MONCHINH')
INSERT MonAn (MaMA, TenMA, HinhAnh, GiaTien, MaPL) VALUES (N'BOKHOCHAY', N'Bò kho chay', N'noimg.png', N'230000', N'MONCHINH')
INSERT MonAn (MaMA, TenMA, HinhAnh, GiaTien, MaPL) VALUES (N'LAUNAMCHAY', N'Lẩu nấm chay', N'noimg.png', N'230000', N'MONCHINH')

GO
INSERT DichVuDatMon (MaHD, MaTD, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221101004', N'TD001', 14960000, 2000000, N'')
INSERT DichVuDatMon (MaHD, MaTD, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221101004', N'TD004', 3140000, 400000, N'')
INSERT DichVuDatMon (MaHD, MaTD, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201001', N'TD001', 16830000, NULL, N'')
INSERT DichVuDatMon (MaHD, MaTD, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201001', N'TD004', 1570000, NULL, N'')
INSERT DichVuDatMon (MaHD, MaTD, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201002', N'TD003', 287000000, NULL, N'')
INSERT DichVuDatMon (MaHD, MaTD, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201003', N'TD003', 215400000, 0, N'')
INSERT DichVuDatMon (MaHD, MaTD, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201004', N'TD001', 22440000, 0, N'')
INSERT DichVuDatMon (MaHD, MaTD, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201005', N'TD004', 15700000, NULL, N'')
INSERT DichVuDatMon (MaHD, MaTD, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201006', N'TD001', 18700000, NULL, N'')
INSERT DichVuDatMon (MaHD, MaTD, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201007', N'TD001', 22440000, NULL, N'')
INSERT DichVuDatMon (MaHD, MaTD, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201008', N'TD002', 25700000, NULL, N'')
INSERT DichVuDatMon (MaHD, MaTD, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201009', N'TD001', 18700000, NULL, N'')
INSERT DichVuDatMon (MaHD, MaTD, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221201010', N'TD002', 50700000, NULL, N'')
INSERT DichVuDatMon (MaHD, MaTD, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221205011', N'TD001', 16830000, 0, N'')
INSERT DichVuDatMon (MaHD, MaTD, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221205011', N'TD002', 2570000, NULL, N'')
INSERT DichVuDatMon (MaHD, MaTD, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221206001', N'TD001', 16830000, 2000000, N'')
INSERT DichVuDatMon (MaHD, MaTD, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221206001', N'TD002', 2570000, 2500000, N'')
INSERT DichVuDatMon (MaHD, MaTD, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221206002', N'TD001', 16830000, 0, N'')
INSERT DichVuDatMon (MaHD, MaTD, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221206002', N'TD002', 2570000, 2500000, N'')
INSERT DichVuDatMon (MaHD, MaTD, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221207001', N'TD001', 22140000, 2180000, N'')
INSERT DichVuDatMon (MaHD, MaTD, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221207001', N'TD004', 1570000, 2000000, N'')
INSERT DichVuDatMon (MaHD, MaTD, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221207002', N'TD001', 16830000, 0, N'')
INSERT DichVuDatMon (MaHD, MaTD, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221207002', N'TD004', 9870000, NULL, N'')
INSERT DichVuDatMon (MaHD, MaTD, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221207003', N'TD001', 16830000, 1000000, N'')
INSERT DichVuDatMon (MaHD, MaTD, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221207003', N'TD002', 2570000, 2500000, N'')
INSERT DichVuDatMon (MaHD, MaTD, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221208001', N'TD001', 16830000, 0, N'')
INSERT DichVuDatMon (MaHD, MaTD, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221208001', N'TD002', 2570000, NULL, N'')
INSERT DichVuDatMon (MaHD, MaTD, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221209001', N'TD001', 18900000, 4000000, N'')
INSERT DichVuDatMon (MaHD, MaTD, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221209001', N'TD004', 1770000, 0, N'')
INSERT DichVuDatMon (MaHD, MaTD, ChiPhi, ChiPhiPhatSinh, GhiChu) VALUES (N'HD20221213002', N'TD001', 37400000, NULL, N'')
GO
INSERT HoaDon ( MaHD, NgayLap, NgayLapLan2, MaNV, TrangThai, MaNLLan2, TongTien) VALUES ( N'HD20221201001', CAST(N'2021-12-01' AS Date), CAST(N'2021-12-05' AS Date), N'NV001', 1, N'NV001', 100000000)
INSERT HoaDon ( MaHD, NgayLap, NgayLapLan2, MaNV, TrangThai, MaNLLan2, TongTien) VALUES ( N'HD20221101004', CAST(N'2021-11-01' AS Date), CAST(N'2021-11-06' AS Date), N'NV001', 1, N'NV001', 34221000)
INSERT HoaDon ( MaHD, NgayLap, NgayLapLan2, MaNV, TrangThai, MaNLLan2, TongTien) VALUES ( N'HD20221201003', CAST(N'2022-12-04' AS Date), CAST(N'2022-12-13' AS Date), N'NV001', 1, N'NV001', 137216750)
INSERT HoaDon ( MaHD, NgayLap, NgayLapLan2, MaNV, TrangThai, MaNLLan2, TongTien) VALUES ( N'HD20221201008', CAST(N'2022-12-05' AS Date), NULL, N'NV001', 0, NULL, NULL)
INSERT HoaDon ( MaHD, NgayLap, NgayLapLan2, MaNV, TrangThai, MaNLLan2, TongTien) VALUES (N'HD20221201010', CAST(N'2022-12-05' AS Date), NULL, N'NV001', 0, NULL, NULL)
INSERT HoaDon ( MaHD, NgayLap, NgayLapLan2, MaNV, TrangThai, MaNLLan2, TongTien) VALUES ( N'HD20221205011', CAST(N'2022-12-05' AS Date), CAST(N'2022-12-06' AS Date), N'NV001', 1, N'NV001', 100000000)
INSERT HoaDon ( MaHD, NgayLap, NgayLapLan2, MaNV, TrangThai, MaNLLan2, TongTien) VALUES ( N'HD20221206001', CAST(N'2022-12-06' AS Date), CAST(N'2022-12-06' AS Date), N'NV001', 1, N'NV001', 100000000)
INSERT HoaDon ( MaHD, NgayLap, NgayLapLan2, MaNV, TrangThai, MaNLLan2, TongTien) VALUES ( N'HD20221206002', CAST(N'2022-12-06' AS Date), NULL, N'NV001', 0, NULL, NULL)
INSERT HoaDon ( MaHD, NgayLap, NgayLapLan2, MaNV, TrangThai, MaNLLan2, TongTien) VALUES ( N'HD20221207001', CAST(N'2022-12-07' AS Date), CAST(N'2022-12-07' AS Date), N'NV001', 0, N'NV001', NULL)
INSERT HoaDon ( MaHD, NgayLap, NgayLapLan2, MaNV, TrangThai, MaNLLan2, TongTien) VALUES ( N'HD20221207002', CAST(N'2022-12-07' AS Date), NULL, N'NV001', 0, NULL, NULL)
INSERT HoaDon ( MaHD, NgayLap, NgayLapLan2, MaNV, TrangThai, MaNLLan2, TongTien) VALUES (N'HD20221207003', CAST(N'2022-12-07' AS Date), CAST(N'2022-12-07' AS Date), N'NV001', 1, N'NV001', 36701500)
INSERT HoaDon ( MaHD, NgayLap, NgayLapLan2, MaNV, TrangThai, MaNLLan2, TongTien) VALUES ( N'HD20221208001', CAST(N'2022-12-08' AS Date), NULL, N'NV001', 0, NULL, NULL)
INSERT HoaDon ( MaHD, NgayLap, NgayLapLan2, MaNV, TrangThai, MaNLLan2, TongTien) VALUES ( N'HD20221201007', CAST(N'2022-12-08' AS Date), NULL, N'NV001', 0, NULL, NULL)
INSERT HoaDon ( MaHD, NgayLap, NgayLapLan2, MaNV, TrangThai, MaNLLan2, TongTien) VALUES ( N'HD20221209001', CAST(N'2022-12-09' AS Date), CAST(N'2022-12-09' AS Date), N'NV001', 1, N'NV001', 57601500)
GO
INSERT HopDong (MaHD, MaNL, SoLuongBan, Sanh, NgayLap, NgayDuyet, MaND, NgayToChuc, ThoiGianBatDau, ThoiGianKetThuc, TrangThai, The, TienCoc, ChiPhiPhatSinh, TongTien) VALUES (N'HD20221101004', N'NV001', 10, N'SANH01', CAST(N'2021-11-01' AS Date), NULL, NULL, CAST(N'2021-11-10' AS Date), CAST(N'09:00:00' AS Time), CAST(N'16:00:00' AS Time), N'DATHUCHIEN', 10, 30481000, 0, 60962000)
INSERT HopDong (MaHD, MaNL, SoLuongBan, Sanh, NgayLap, NgayDuyet, MaND, NgayToChuc, ThoiGianBatDau, ThoiGianKetThuc, TrangThai, The, TienCoc, ChiPhiPhatSinh, TongTien) VALUES (N'HD20221201001', N'NV001', 10, N'SANH01', CAST(N'2021-12-01' AS Date), NULL, NULL, CAST(N'2021-01-01' AS Date), CAST(N'08:00:00' AS Time), CAST(N'15:00:00' AS Time), N'DATHUCHIEN', 10, 40821000, 0, 81642000)
INSERT HopDong (MaHD, MaNL, SoLuongBan, Sanh, NgayLap, NgayDuyet, MaND, NgayToChuc, ThoiGianBatDau, ThoiGianKetThuc, TrangThai, The, TienCoc, ChiPhiPhatSinh, TongTien) VALUES (N'HD20221201002', N'NV001', 20, N'SANH03', CAST(N'2022-12-01' AS Date), NULL, NULL, CAST(N'2022-12-31' AS Date), CAST(N'08:00:00' AS Time), CAST(N'11:00:00' AS Time), N'CHOKYKET', 10, 199985500, 0, 399971000)
INSERT HopDong (MaHD, MaNL, SoLuongBan, Sanh, NgayLap, NgayDuyet, MaND, NgayToChuc, ThoiGianBatDau, ThoiGianKetThuc, TrangThai, The, TienCoc, ChiPhiPhatSinh, TongTien) VALUES (N'HD20221201003', N'NV001', 15, N'SANH02', CAST(N'2022-12-04' AS Date), NULL, NULL, CAST(N'2022-12-22' AS Date), CAST(N'08:00:00' AS Time), CAST(N'15:00:00' AS Time), N'XOA', 10, 137216750, 0, 274433500)
INSERT HopDong (MaHD, MaNL, SoLuongBan, Sanh, NgayLap, NgayDuyet, MaND, NgayToChuc, ThoiGianBatDau, ThoiGianKetThuc, TrangThai, The, TienCoc, ChiPhiPhatSinh, TongTien) VALUES (N'HD20221201004', N'NV001', 12, N'SANH02', CAST(N'2022-12-13' AS Date), NULL, NULL, CAST(N'2022-12-15' AS Date), CAST(N'08:00:00' AS Time), CAST(N'13:00:00' AS Time), N'CHOKYKET', 10, 34529000, 0, 69058000)
INSERT HopDong (MaHD, MaNL, SoLuongBan, Sanh, NgayLap, NgayDuyet, MaND, NgayToChuc, ThoiGianBatDau, ThoiGianKetThuc, TrangThai, The, TienCoc, ChiPhiPhatSinh, TongTien) VALUES (N'HD20221201005', N'NV001', 10, N'SANH01', CAST(N'2022-12-01' AS Date), NULL, NULL, CAST(N'2022-12-10' AS Date), CAST(N'07:00:00' AS Time), CAST(N'13:00:00' AS Time), N'XOA', 10, 28066500, 0, 56133000)
INSERT HopDong (MaHD, MaNL, SoLuongBan, Sanh, NgayLap, NgayDuyet, MaND, NgayToChuc, ThoiGianBatDau, ThoiGianKetThuc, TrangThai, The, TienCoc, ChiPhiPhatSinh, TongTien) VALUES (N'HD20221201006', N'NV001', 10, N'SANH01', CAST(N'2022-12-01' AS Date), NULL, NULL, CAST(N'2022-12-17' AS Date), CAST(N'08:00:00' AS Time), CAST(N'11:00:00' AS Time), N'CHOKYKET', 10, 24766500, 0, 49533000)
INSERT HopDong (MaHD, MaNL, SoLuongBan, Sanh, NgayLap, NgayDuyet, MaND, NgayToChuc, ThoiGianBatDau, ThoiGianKetThuc, TrangThai, The, TienCoc, ChiPhiPhatSinh, TongTien) VALUES (N'HD20221201007', N'NV001', 12, N'SANH05', CAST(N'2022-12-01' AS Date), NULL, NULL, CAST(N'2022-12-25' AS Date), CAST(N'08:00:00' AS Time), CAST(N'16:00:00' AS Time), N'THUCHIEN', 10, 28418500, 0, 56837000)
INSERT HopDong (MaHD, MaNL, SoLuongBan, Sanh, NgayLap, NgayDuyet, MaND, NgayToChuc, ThoiGianBatDau, ThoiGianKetThuc, TrangThai, The, TienCoc, ChiPhiPhatSinh, TongTien) VALUES (N'HD20221201008', N'NV001', 10, N'SANH01', CAST(N'2022-12-01' AS Date), NULL, NULL, CAST(N'2022-12-14' AS Date), CAST(N'08:00:00' AS Time), CAST(N'12:10:00' AS Time), N'THUCHIEN', 10, 28072000, 0, 56144000)
INSERT HopDong (MaHD, MaNL, SoLuongBan, Sanh, NgayLap, NgayDuyet, MaND, NgayToChuc, ThoiGianBatDau, ThoiGianKetThuc, TrangThai, The, TienCoc, ChiPhiPhatSinh, TongTien) VALUES (N'HD20221201009', N'NV001', 10, N'SANH02', CAST(N'2022-12-08' AS Date), NULL, NULL, CAST(N'2022-12-21' AS Date), CAST(N'08:00:00' AS Time), CAST(N'16:00:00' AS Time), N'CHOKYKET', 10, 25239500, 0, 50479000)
INSERT HopDong (MaHD, MaNL, SoLuongBan, Sanh, NgayLap, NgayDuyet, MaND, NgayToChuc, ThoiGianBatDau, ThoiGianKetThuc, TrangThai, The, TienCoc, ChiPhiPhatSinh, TongTien) VALUES (N'HD20221201010', N'NV001', 10, N'SANH01', CAST(N'2022-12-05' AS Date), NULL, NULL, CAST(N'2022-11-11' AS Date), CAST(N'08:00:00' AS Time), CAST(N'16:00:00' AS Time), N'DATHUCHIEN', 10, 37697000, 0, 75394000)
INSERT HopDong (MaHD, MaNL, SoLuongBan, Sanh, NgayLap, NgayDuyet, MaND, NgayToChuc, ThoiGianBatDau, ThoiGianKetThuc, TrangThai, The, TienCoc, ChiPhiPhatSinh, TongTien) VALUES (N'HD20221205011', N'NV001', 10, N'SANH01', CAST(N'2022-12-05' AS Date), NULL, NULL, CAST(N'2022-12-04' AS Date), CAST(N'08:00:00' AS Time), CAST(N'10:00:00' AS Time), N'DATHUCHIEN', 10, 22126500, NULL, 44253000)
INSERT HopDong (MaHD, MaNL, SoLuongBan, Sanh, NgayLap, NgayDuyet, MaND, NgayToChuc, ThoiGianBatDau, ThoiGianKetThuc, TrangThai, The, TienCoc, ChiPhiPhatSinh, TongTien) VALUES (N'HD20221206001', N'NV001', 10, N'SANH01', CAST(N'2022-12-06' AS Date), NULL, NULL, CAST(N'2022-12-04' AS Date), CAST(N'19:40:00' AS Time), CAST(N'21:40:00' AS Time), N'DATHUCHIEN', 10, 22621500, NULL, 45243000)
INSERT HopDong (MaHD, MaNL, SoLuongBan, Sanh, NgayLap, NgayDuyet, MaND, NgayToChuc, ThoiGianBatDau, ThoiGianKetThuc, TrangThai, The, TienCoc, ChiPhiPhatSinh, TongTien) VALUES (N'HD20221206002', N'NV001', 10, N'SANH01', CAST(N'2022-12-06' AS Date), NULL, NULL, CAST(N'2022-11-05' AS Date), CAST(N'08:00:00' AS Time), CAST(N'10:00:00' AS Time), N'THUCHIEN', 10, 25151500, NULL, 50303000)
INSERT HopDong (MaHD, MaNL, SoLuongBan, Sanh, NgayLap, NgayDuyet, MaND, NgayToChuc, ThoiGianBatDau, ThoiGianKetThuc, TrangThai, The, TienCoc, ChiPhiPhatSinh, TongTien) VALUES (N'HD20221207001', N'NV001', 10, N'SANH01', CAST(N'2022-12-07' AS Date), NULL, NULL, CAST(N'2022-12-04' AS Date), CAST(N'09:00:00' AS Time), CAST(N'12:00:00' AS Time), N'DATHUCHIEN', 10, 36415500, NULL, 72831000)
INSERT HopDong (MaHD, MaNL, SoLuongBan, Sanh, NgayLap, NgayDuyet, MaND, NgayToChuc, ThoiGianBatDau, ThoiGianKetThuc, TrangThai, The, TienCoc, ChiPhiPhatSinh, TongTien) VALUES (N'HD20221207002', N'NV001', 10, N'SANH03', CAST(N'2022-12-07' AS Date), NULL, NULL, CAST(N'2022-12-06' AS Date), CAST(N'08:00:00' AS Time), CAST(N'22:00:00' AS Time), N'DATHUCHIEN', 10, 51623000, NULL, 103246000)
INSERT HopDong (MaHD, MaNL, SoLuongBan, Sanh, NgayLap, NgayDuyet, MaND, NgayToChuc, ThoiGianBatDau, ThoiGianKetThuc, TrangThai, The, TienCoc, ChiPhiPhatSinh, TongTien) VALUES (N'HD20221207003', N'NV001', 10, N'SANH01', CAST(N'2022-12-07' AS Date), NULL, NULL, CAST(N'2022-12-05' AS Date), CAST(N'08:00:00' AS Time), CAST(N'10:00:00' AS Time), N'DATHUCHIEN', 10, 31751500, NULL, 63503000)
INSERT HopDong (MaHD, MaNL, SoLuongBan, Sanh, NgayLap, NgayDuyet, MaND, NgayToChuc, ThoiGianBatDau, ThoiGianKetThuc, TrangThai, The, TienCoc, ChiPhiPhatSinh, TongTien) VALUES (N'HD20221208001', N'NV001', 10, N'SANH01', CAST(N'2022-12-08' AS Date), NULL, NULL, CAST(N'2022-12-11' AS Date), CAST(N'08:00:00' AS Time), CAST(N'10:00:00' AS Time), N'THUCHIEN', 10, 25151500, NULL, 50303000)
INSERT HopDong (MaHD, MaNL, SoLuongBan, Sanh, NgayLap, NgayDuyet, MaND, NgayToChuc, ThoiGianBatDau, ThoiGianKetThuc, TrangThai, The, TienCoc, ChiPhiPhatSinh, TongTien) VALUES (N'HD20221209001', N'NV001', 10, N'SANH02', CAST(N'2022-12-09' AS Date), NULL, NULL, CAST(N'2022-12-08' AS Date), CAST(N'08:00:00' AS Time), CAST(N'10:00:00' AS Time), N'THUCHIEN', 10, 49351500, NULL, 98703000)
INSERT HopDong (MaHD, MaNL, SoLuongBan, Sanh, NgayLap, NgayDuyet, MaND, NgayToChuc, ThoiGianBatDau, ThoiGianKetThuc, TrangThai, The, TienCoc, ChiPhiPhatSinh, TongTien) VALUES (N'HD20221213001', N'NV003', 10, N'SANH01', CAST(N'2022-12-13' AS Date), NULL, NULL, CAST(N'2022-12-15' AS Date), CAST(N'08:00:00' AS Time), CAST(N'10:00:00' AS Time), N'DANGLAP', 10, 4400000, NULL, 8800000)
INSERT HopDong (MaHD, MaNL, SoLuongBan, Sanh, NgayLap, NgayDuyet, MaND, NgayToChuc, ThoiGianBatDau, ThoiGianKetThuc, TrangThai, The, TienCoc, ChiPhiPhatSinh, TongTien) VALUES (N'HD20221213002', N'NV001', 20, N'SANH03', CAST(N'2022-12-13' AS Date), NULL, NULL, CAST(N'2022-12-17' AS Date), CAST(N'08:00:00' AS Time), CAST(N'10:00:00' AS Time), N'CHODUYET', 10, 52926500, NULL, 105853000)
GO


GO
INSERT HopDongDichVu (MaHD, MaDV, MaGoi, ChiPhi, GhiChu) VALUES (N'HD20221101004', N'NGHETHUAT', N'NGHETHUAT1', 2000000, N'')
INSERT HopDongDichVu (MaHD, MaDV, MaGoi, ChiPhi, GhiChu) VALUES (N'HD20221101004', N'TTBANTIEC', N'TTBANTIEC3', 4510000, N'')
INSERT HopDongDichVu (MaHD, MaDV, MaGoi, ChiPhi, GhiChu) VALUES (N'HD20221101004', N'TTCONG', N'TTCONG1', 13300000, N'')
INSERT HopDongDichVu (MaHD, MaDV, MaGoi, ChiPhi, GhiChu) VALUES (N'HD20221101004', N'TTSANKHAU', N'TTSANKHAU2', 2010000, N'')
INSERT HopDongDichVu (MaHD, MaDV, MaGoi, ChiPhi, GhiChu) VALUES (N'HD20221201001', N'NGHETHUAT', N'NGHETHUAT2', 2000000, N'')
INSERT HopDongDichVu (MaHD, MaDV, MaGoi, ChiPhi, GhiChu) VALUES (N'HD20221201001', N'TTBANTIEC', N'TTBANTIEC1', 3510000, N'')
INSERT HopDongDichVu (MaHD, MaDV, MaGoi, ChiPhi, GhiChu) VALUES (N'HD20221201001', N'TTCONG', N'TTCONG1', 12310000, N'')
INSERT HopDongDichVu (MaHD, MaDV, MaGoi, ChiPhi, GhiChu) VALUES (N'HD20221201001', N'TTSANKHAU', N'TTSANKHAU1', 21000000, N'')
INSERT HopDongDichVu (MaHD, MaDV, MaGoi, ChiPhi, GhiChu) VALUES (N'HD20221201002', N'NGHETHUAT', N'NGHETHUAT1', 2000000, N'')
INSERT HopDongDichVu (MaHD, MaDV, MaGoi, ChiPhi, GhiChu) VALUES (N'HD20221201002', N'TTBANTIEC', N'TTBANTIEC1', 7010000, N'')
INSERT HopDongDichVu (MaHD, MaDV, MaGoi, ChiPhi, GhiChu) VALUES (N'HD20221201002', N'TTCONG', N'TTCONG1', 13300000, N'')
INSERT HopDongDichVu (MaHD, MaDV, MaGoi, ChiPhi, GhiChu) VALUES (N'HD20221201002', N'TTSANKHAU', N'TTSANKHAU1', 12000000, N'')
INSERT HopDongDichVu (MaHD, MaDV, MaGoi, ChiPhi, GhiChu) VALUES (N'HD20221201003', N'NGHETHUAT', N'NGHETHUAT2', 2000000, N'')
INSERT HopDongDichVu (MaHD, MaDV, MaGoi, ChiPhi, GhiChu) VALUES (N'HD20221201003', N'TTBANTIEC', N'TTBANTIEC1', 5260000, N'')
INSERT HopDongDichVu (MaHD, MaDV, MaGoi, ChiPhi, GhiChu) VALUES (N'HD20221201003', N'TTCONG', N'TTCONG2', 4300000, N'')
INSERT HopDongDichVu (MaHD, MaDV, MaGoi, ChiPhi, GhiChu) VALUES (N'HD20221201003', N'TTSANKHAU', N'TTSANKHAU1', 3000000, N'')
INSERT HopDongDichVu (MaHD, MaDV, MaGoi, ChiPhi, GhiChu) VALUES (N'HD20221201004', N'NGHETHUAT', N'NGHETHUAT1', 2000000, N'')
INSERT HopDongDichVu (MaHD, MaDV, MaGoi, ChiPhi, GhiChu) VALUES (N'HD20221201004', N'TTBANTIEC', N'TTBANTIEC1', 14200000, N'')
INSERT HopDongDichVu (MaHD, MaDV, MaGoi, ChiPhi, GhiChu) VALUES (N'HD20221201004', N'TTCONG', N'TTCONG1', 5300000, N'')
INSERT HopDongDichVu (MaHD, MaDV, MaGoi, ChiPhi, GhiChu) VALUES (N'HD20221201004', N'TTSANKHAU', N'TTSANKHAU1', 3000000, N'')
INSERT HopDongDichVu (MaHD, MaDV, MaGoi, ChiPhi, GhiChu) VALUES (N'HD20221201005', N'NGHETHUAT', N'NGHETHUAT1', 2000000, N'')
INSERT HopDongDichVu (MaHD, MaDV, MaGoi, ChiPhi, GhiChu) VALUES (N'HD20221201005', N'TTBANTIEC', N'TTBANTIEC1', 3510000, N'')
INSERT HopDongDichVu (MaHD, MaDV, MaGoi, ChiPhi, GhiChu) VALUES (N'HD20221201005', N'TTCONG', N'TTCONG1', 12310000, N'')
INSERT HopDongDichVu (MaHD, MaDV, MaGoi, ChiPhi, GhiChu) VALUES (N'HD20221201005', N'TTSANKHAU', N'TTSANKHAU2', 2010000, N'')
INSERT HopDongDichVu (MaHD, MaDV, MaGoi, ChiPhi, GhiChu) VALUES (N'HD20221201006', N'NGHETHUAT', N'NGHETHUAT1', 2000000, N'')
INSERT HopDongDichVu (MaHD, MaDV, MaGoi, ChiPhi, GhiChu) VALUES (N'HD20221201006', N'TTBANTIEC', N'TTBANTIEC1', 3510000, N'')
INSERT HopDongDichVu (MaHD, MaDV, MaGoi, ChiPhi, GhiChu) VALUES (N'HD20221201006', N'TTCONG', N'TTCONG1', 2320000, N'')
INSERT HopDongDichVu (MaHD, MaDV, MaGoi, ChiPhi, GhiChu) VALUES (N'HD20221201006', N'TTSANKHAU', N'TTSANKHAU1', 3000000, N'')
INSERT HopDongDichVu (MaHD, MaDV, MaGoi, ChiPhi, GhiChu) VALUES (N'HD20221201007', N'NGHETHUAT', N'NGHETHUAT1', 2000000, N'')
INSERT HopDongDichVu (MaHD, MaDV, MaGoi, ChiPhi, GhiChu) VALUES (N'HD20221201007', N'TTBANTIEC', N'TTBANTIEC1', 4210000, N'')
INSERT HopDongDichVu (MaHD, MaDV, MaGoi, ChiPhi, GhiChu) VALUES (N'HD20221201007', N'TTCONG', N'TTCONG1', 2320000, N'')
INSERT HopDongDichVu (MaHD, MaDV, MaGoi, ChiPhi, GhiChu) VALUES (N'HD20221201007', N'TTSANKHAU', N'TTSANKHAU1', 3000000, N'')
INSERT HopDongDichVu (MaHD, MaDV, MaGoi, ChiPhi, GhiChu) VALUES (N'HD20221201008', N'NGHETHUAT', N'NGHETHUAT1', 2000000, N'')
INSERT HopDongDichVu (MaHD, MaDV, MaGoi, ChiPhi, GhiChu) VALUES (N'HD20221201008', N'TTBANTIEC', N'TTBANTIEC1', 3510000, N'')
INSERT HopDongDichVu (MaHD, MaDV, MaGoi, ChiPhi, GhiChu) VALUES (N'HD20221201008', N'TTCONG', N'TTCONG1', 2320000, N'')
INSERT HopDongDichVu (MaHD, MaDV, MaGoi, ChiPhi, GhiChu) VALUES (N'HD20221201008', N'TTSANKHAU', N'TTSANKHAU2', 2010000, N'')
INSERT HopDongDichVu (MaHD, MaDV, MaGoi, ChiPhi, GhiChu) VALUES (N'HD20221201009', N'NGHETHUAT', N'NGHETHUAT2', 2000000, N'')
INSERT HopDongDichVu (MaHD, MaDV, MaGoi, ChiPhi, GhiChu) VALUES (N'HD20221201009', N'TTBANTIEC', N'TTBANTIEC1', 3510000, N'')
INSERT HopDongDichVu (MaHD, MaDV, MaGoi, ChiPhi, GhiChu) VALUES (N'HD20221201009', N'TTCONG', N'TTCONG1', 2320000, N'')
INSERT HopDongDichVu (MaHD, MaDV, MaGoi, ChiPhi, GhiChu) VALUES (N'HD20221201009', N'TTSANKHAU', N'TTSANKHAU2', 2010000, N'')
INSERT HopDongDichVu (MaHD, MaDV, MaGoi, ChiPhi, GhiChu) VALUES (N'HD20221201010', N'NGHETHUAT', N'NGHETHUAT1', 2000000, N'')
INSERT HopDongDichVu (MaHD, MaDV, MaGoi, ChiPhi, GhiChu) VALUES (N'HD20221201010', N'TTBANTIEC', N'TTBANTIEC1', 3510000, N'')
INSERT HopDongDichVu (MaHD, MaDV, MaGoi, ChiPhi, GhiChu) VALUES (N'HD20221201010', N'TTCONG', N'TTCONG1', 2320000, N'')
INSERT HopDongDichVu (MaHD, MaDV, MaGoi, ChiPhi, GhiChu) VALUES (N'HD20221201010', N'TTSANKHAU', N'TTSANKHAU2', 2010000, N'')
INSERT HopDongDichVu (MaHD, MaDV, MaGoi, ChiPhi, GhiChu) VALUES (N'HD20221205011', N'NGHETHUAT', N'NGHETHUAT1', 2000000, N'')
INSERT HopDongDichVu (MaHD, MaDV, MaGoi, ChiPhi, GhiChu) VALUES (N'HD20221205011', N'TTBANTIEC', N'TTBANTIEC1', 3510000, N'')
INSERT HopDongDichVu (MaHD, MaDV, MaGoi, ChiPhi, GhiChu) VALUES (N'HD20221205011', N'TTCONG', N'TTCONG1', 2320000, N'')
INSERT HopDongDichVu (MaHD, MaDV, MaGoi, ChiPhi, GhiChu) VALUES (N'HD20221205011', N'TTSANKHAU', N'TTSANKHAU1', 3000000, N'')
INSERT HopDongDichVu (MaHD, MaDV, MaGoi, ChiPhi, GhiChu) VALUES (N'HD20221206001', N'NGHETHUAT', N'NGHETHUAT1', 2000000, N'')
INSERT HopDongDichVu (MaHD, MaDV, MaGoi, ChiPhi, GhiChu) VALUES (N'HD20221206001', N'TTBANTIEC', N'TTBANTIEC3', 4610000, N'')
INSERT HopDongDichVu (MaHD, MaDV, MaGoi, ChiPhi, GhiChu) VALUES (N'HD20221206001', N'TTCONG', N'TTCONG1', 2320000, N'')
INSERT HopDongDichVu (MaHD, MaDV, MaGoi, ChiPhi, GhiChu) VALUES (N'HD20221206001', N'TTSANKHAU', N'TTSANKHAU1', 3000000, N'')
INSERT HopDongDichVu (MaHD, MaDV, MaGoi, ChiPhi, GhiChu) VALUES (N'HD20221206002', N'NGHETHUAT', N'NGHETHUAT1', 2000000, N'')
INSERT HopDongDichVu (MaHD, MaDV, MaGoi, ChiPhi, GhiChu) VALUES (N'HD20221206002', N'TTBANTIEC', N'TTBANTIEC1', 3510000, N'')
INSERT HopDongDichVu (MaHD, MaDV, MaGoi, ChiPhi, GhiChu) VALUES (N'HD20221206002', N'TTCONG', N'TTCONG1', 2320000, N'')
INSERT HopDongDichVu (MaHD, MaDV, MaGoi, ChiPhi, GhiChu) VALUES (N'HD20221206002', N'TTSANKHAU', N'TTSANKHAU1', 3000000, N'')
INSERT HopDongDichVu (MaHD, MaDV, MaGoi, ChiPhi, GhiChu) VALUES (N'HD20221207001', N'NGHETHUAT', N'NGHETHUAT1', 2000000, N'')
INSERT HopDongDichVu (MaHD, MaDV, MaGoi, ChiPhi, GhiChu) VALUES (N'HD20221207001', N'TTBANTIEC', N'TTBANTIEC3', 18500000, N'')
INSERT HopDongDichVu (MaHD, MaDV, MaGoi, ChiPhi, GhiChu) VALUES (N'HD20221207001', N'TTCONG', N'TTCONG1', 2320000, N'')
INSERT HopDongDichVu (MaHD, MaDV, MaGoi, ChiPhi, GhiChu) VALUES (N'HD20221207001', N'TTSANKHAU', N'TTSANKHAU1', 3000000, N'')
INSERT HopDongDichVu (MaHD, MaDV, MaGoi, ChiPhi, GhiChu) VALUES (N'HD20221207002', N'NGHETHUAT', N'NGHETHUAT1', 2000000, N'')
INSERT HopDongDichVu (MaHD, MaDV, MaGoi, ChiPhi, GhiChu) VALUES (N'HD20221207002', N'TTBANTIEC', N'TTBANTIEC1', 19500000, N'')
INSERT HopDongDichVu (MaHD, MaDV, MaGoi, ChiPhi, GhiChu) VALUES (N'HD20221207002', N'TTCONG', N'TTCONG1', 2220000, N'')
INSERT HopDongDichVu (MaHD, MaDV, MaGoi, ChiPhi, GhiChu) VALUES (N'HD20221207002', N'TTSANKHAU', N'TTSANKHAU1', 3000000, N'')
INSERT HopDongDichVu (MaHD, MaDV, MaGoi, ChiPhi, GhiChu) VALUES (N'HD20221207003', N'NGHETHUAT', N'NGHETHUAT1', 2000000, N'')
INSERT HopDongDichVu (MaHD, MaDV, MaGoi, ChiPhi, GhiChu) VALUES (N'HD20221207003', N'TTBANTIEC', N'TTBANTIEC1', 3510000, N'')
INSERT HopDongDichVu (MaHD, MaDV, MaGoi, ChiPhi, GhiChu) VALUES (N'HD20221207003', N'TTCONG', N'TTCONG1', 2320000, N'')
INSERT HopDongDichVu (MaHD, MaDV, MaGoi, ChiPhi, GhiChu) VALUES (N'HD20221207003', N'TTSANKHAU', N'TTSANKHAU1', 3000000, N'')
INSERT HopDongDichVu (MaHD, MaDV, MaGoi, ChiPhi, GhiChu) VALUES (N'HD20221208001', N'NGHETHUAT', N'NGHETHUAT1', 2000000, N'')
INSERT HopDongDichVu (MaHD, MaDV, MaGoi, ChiPhi, GhiChu) VALUES (N'HD20221208001', N'TTBANTIEC', N'TTBANTIEC1', 3510000, N'')
INSERT HopDongDichVu (MaHD, MaDV, MaGoi, ChiPhi, GhiChu) VALUES (N'HD20221208001', N'TTCONG', N'TTCONG1', 2320000, N'')
INSERT HopDongDichVu (MaHD, MaDV, MaGoi, ChiPhi, GhiChu) VALUES (N'HD20221208001', N'TTSANKHAU', N'TTSANKHAU1', 3000000, N'')
INSERT HopDongDichVu (MaHD, MaDV, MaGoi, ChiPhi, GhiChu) VALUES (N'HD20221209001', N'NGHETHUAT', N'NGHETHUAT1', 2000000, N'')
INSERT HopDongDichVu (MaHD, MaDV, MaGoi, ChiPhi, GhiChu) VALUES (N'HD20221209001', N'TTBANTIEC', N'TTBANTIEC1', 14500000, N'')
INSERT HopDongDichVu (MaHD, MaDV, MaGoi, ChiPhi, GhiChu) VALUES (N'HD20221209001', N'TTCONG', N'TTCONG1', 22200000, N'')
INSERT HopDongDichVu (MaHD, MaDV, MaGoi, ChiPhi, GhiChu) VALUES (N'HD20221209001', N'TTSANKHAU', N'TTSANKHAU2', 2010000, N'')
INSERT HopDongDichVu (MaHD, MaDV, MaGoi, ChiPhi, GhiChu) VALUES (N'HD20221213002', N'NGHETHUAT', N'NGHETHUAT1', 2000000, N'')
INSERT HopDongDichVu (MaHD, MaDV, MaGoi, ChiPhi, GhiChu) VALUES (N'HD20221213002', N'TTBANTIEC', N'TTBANTIEC3', 9210000, N'')
INSERT HopDongDichVu (MaHD, MaDV, MaGoi, ChiPhi, GhiChu) VALUES (N'HD20221213002', N'TTCONG', N'TTCONG1', 2320000, N'')
INSERT HopDongDichVu (MaHD, MaDV, MaGoi, ChiPhi, GhiChu) VALUES (N'HD20221213002', N'TTSANKHAU', N'TTSANKHAU1', 3000000, N'')
GO
INSERT HopDongDichVuDiKem (MaHD, GhiChu, ChiPhi, ChiPhiPhatSinh) VALUES (N'HD20221101004', N'', 7500000, 1000000)
INSERT HopDongDichVuDiKem (MaHD, GhiChu, ChiPhi, ChiPhiPhatSinh) VALUES (N'HD20221201001', N'', 9000000, 0)
INSERT HopDongDichVuDiKem (MaHD, GhiChu, ChiPhi, ChiPhiPhatSinh) VALUES (N'HD20221201002', N'', 7500000, 0)
INSERT HopDongDichVuDiKem (MaHD, GhiChu, ChiPhi, ChiPhiPhatSinh) VALUES (N'HD20221201003', N'6000000', 6000000, 0)
INSERT HopDongDichVuDiKem (MaHD, GhiChu, ChiPhi, ChiPhiPhatSinh) VALUES (N'HD20221201004', N'', 3500000, 0)
INSERT HopDongDichVuDiKem (MaHD, GhiChu, ChiPhi, ChiPhiPhatSinh) VALUES (N'HD20221201005', N'', 7500000, 0)
INSERT HopDongDichVuDiKem (MaHD, GhiChu, ChiPhi, ChiPhiPhatSinh) VALUES (N'HD20221201006', N'', 7500000, 0)
INSERT HopDongDichVuDiKem (MaHD, GhiChu, ChiPhi, ChiPhiPhatSinh) VALUES (N'HD20221201007', N'', 7500000, 0)
INSERT HopDongDichVuDiKem (MaHD, GhiChu, ChiPhi, ChiPhiPhatSinh) VALUES (N'HD20221201008', N'', 7500000, 0)
INSERT HopDongDichVuDiKem (MaHD, GhiChu, ChiPhi, ChiPhiPhatSinh) VALUES (N'HD20221201009', N'', 6000000, 0)
INSERT HopDongDichVuDiKem (MaHD, GhiChu, ChiPhi, ChiPhiPhatSinh) VALUES (N'HD20221201010', N'', 0, 0)
INSERT HopDongDichVuDiKem (MaHD, GhiChu, ChiPhi, ChiPhiPhatSinh) VALUES (N'HD20221205011', N'', 2000000, 0)
INSERT HopDongDichVuDiKem (MaHD, GhiChu, ChiPhi, ChiPhiPhatSinh) VALUES (N'HD20221206001', N'', 1500000, 0)
INSERT HopDongDichVuDiKem (MaHD, GhiChu, ChiPhi, ChiPhiPhatSinh) VALUES (N'HD20221206002', N'', 7500000, 5000000)
INSERT HopDongDichVuDiKem (MaHD, GhiChu, ChiPhi, ChiPhiPhatSinh) VALUES (N'HD20221207001', N'', 7500000, 1500000)
INSERT HopDongDichVuDiKem (MaHD, GhiChu, ChiPhi, ChiPhiPhatSinh) VALUES (N'HD20221207002', N'', 13000000, 5010000)
INSERT HopDongDichVuDiKem (MaHD, GhiChu, ChiPhi, ChiPhiPhatSinh) VALUES (N'HD20221207003', N'', 19500000, 1000000)
INSERT HopDongDichVuDiKem (MaHD, GhiChu, ChiPhi, ChiPhiPhatSinh) VALUES (N'HD20221208001', N'', 7500000, 0)
INSERT HopDongDichVuDiKem (MaHD, GhiChu, ChiPhi, ChiPhiPhatSinh) VALUES (N'HD20221209001', N'', 17000000, 1500000)
INSERT HopDongDichVuDiKem (MaHD, GhiChu, ChiPhi, ChiPhiPhatSinh) VALUES (N'HD20221213002', N'', 7500000, 0)
GO

GO

INSERT KhachHang ( MaHD, HoTen, SoDienThoai, CCCD, DiaChi, HoTenCoDau, HoTenChuRe) VALUES ( N'HD20221201001', N'Lê Văn A', N'0333483101', N'123456789', N'cần thơ', N'Lê Thị B', N'Lê Văn B')
INSERT KhachHang ( MaHD, HoTen, SoDienThoai, CCCD, DiaChi, HoTenCoDau, HoTenChuRe) VALUES ( N'HD20221201002', N'Huỳnh Thị D', N'0349684034', N'084558712456', N'Long Hưng, Ô Môn, Cần Thơ', N'Nguyễn Cẩm C', N'Lê Văn B')
INSERT KhachHang ( MaHD, HoTen, SoDienThoai, CCCD, DiaChi, HoTenCoDau, HoTenChuRe) VALUES (N'HD20221201003', N'Lê Huyền D', N'0984748323', N'084573395038', N'Thạnh Hòa, Thốt Nốt, Cần Thơ', N'Châu Thị E', N'Trần Văn M')
INSERT KhachHang ( MaHD, HoTen, SoDienThoai, CCCD, DiaChi, HoTenCoDau, HoTenChuRe) VALUES ( N'HD20221101004', N'Lương Văn D', N'0784584923', N'084768943446', N'An Khánh, Ninh Kiều, Cần Thơ', N'Dương Mỹ X', N'Trần Quốc A')
INSERT KhachHang ( MaHD, HoTen, SoDienThoai, CCCD, DiaChi, HoTenCoDau, HoTenChuRe) VALUES ( N'HD20221201004', N'Giang Hà T', N'0987425345', N'084386352882', N'Cái Khế, Ninh Kiều, Cần Thơ', N'Lê Kim Y', N'Trần Nghĩa D')
INSERT KhachHang ( MaHD, HoTen, SoDienThoai, CCCD, DiaChi, HoTenCoDau, HoTenChuRe) VALUES ( N'HD20221201005', N'Lê Văn D', N'0984434353', N'082448890123', N'cái khế, Ninh Kiều, Cần Thơ', N'Lê Thị E', N'Cương Văn D')
INSERT KhachHang ( MaHD, HoTen, SoDienThoai, CCCD, DiaChi, HoTenCoDau, HoTenChuRe) VALUES ( N'HD20221201006', N'Lê Phước Thịnh', N'0334831013', N'084356434675', N'Ninh Kiều, Cần Thơ', N'Như', N'Lê Xuân Vinh')
INSERT KhachHang ( MaHD, HoTen, SoDienThoai, CCCD, DiaChi, HoTenCoDau, HoTenChuRe) VALUES ( N'HD20221201007', N'Lê Phước Thịnh', N'0334831013', N'098533343245', N'Ninh Kiều, Cần Thơ', N'Như', N'Phạm Xuân Vinh')
INSERT KhachHang ( MaHD, HoTen, SoDienThoai, CCCD, DiaChi, HoTenCoDau, HoTenChuRe) VALUES ( N'HD20221201008', N'Trần Thị E', N'0334831013', N'094322343255', N'an thới, ninh kiều, cần thơ', N'Lê Ngọc V', N'Đình Hoàng C')
INSERT KhachHang ( MaHD, HoTen, SoDienThoai, CCCD, DiaChi, HoTenCoDau, HoTenChuRe) VALUES ( N'HD20221201009', N'Trần Thị H', N'0334831013', N'123456789', N'sfsdfsdf', N'dsfsdf', N'Trần Nghĩa D')
INSERT KhachHang ( MaHD, HoTen, SoDienThoai, CCCD, DiaChi, HoTenCoDau, HoTenChuRe) VALUES ( N'HD20221201010', N'Lê Văn C', N'0334831013', N'123456789', N'sfsdf', N'Lê Thị E', N'Trần Nghĩa D')
INSERT KhachHang ( MaHD, HoTen, SoDienThoai, CCCD, DiaChi, HoTenCoDau, HoTenChuRe) VALUES ( N'HD20221205011', N'Lê Văn C', N'0334823024', N'454353454354', N'sdfsdfds', N'Lê Thị L', N'Trần Nghĩa D')
INSERT KhachHang ( MaHD, HoTen, SoDienThoai, CCCD, DiaChi, HoTenCoDau, HoTenChuRe) VALUES ( N'HD20221206001', N'le phuoc thinh', N'0334831013', N'123456785665', N'sfsfdss', N'Lê Thị K', N'Trần Nghĩa D')
INSERT KhachHang ( MaHD, HoTen, SoDienThoai, CCCD, DiaChi, HoTenCoDau, HoTenChuRe) VALUES ( N'HD20221206002', N'Lê Phước Thịnh', N'0334831013', N'123456789', N'cần thơ', N'Lê Thị B', N'Nguyễn Thị B')
INSERT KhachHang ( MaHD, HoTen, SoDienThoai, CCCD, DiaChi, HoTenCoDau, HoTenChuRe) VALUES ( N'HD20221207001', N'Lê Phước Thịnh', N'0334831013', N'123456789994', N'cần thơ', N'Lê THị B', N'Nguyễn thị B')
INSERT KhachHang ( MaHD, HoTen, SoDienThoai, CCCD, DiaChi, HoTenCoDau, HoTenChuRe) VALUES ( N'HD20221207002', N'Lê Phước Thịnh', N'0334831033', N'123456789912', N'cần thơ', N'Lê Thị A', N'Lê Thị B')
INSERT KhachHang ( MaHD, HoTen, SoDienThoai, CCCD, DiaChi, HoTenCoDau, HoTenChuRe) VALUES (N'HD20221207003', N'Le Thi A', N'0334831013', N'123456789', N'cần thơ', N'Le Thi A', N'Nguyen Van B')
INSERT KhachHang ( MaHD, HoTen, SoDienThoai, CCCD, DiaChi, HoTenCoDau, HoTenChuRe) VALUES ( N'HD20221208001', N'Nguyễn Trường A', N'0333483101', N'033483101313', N'sdfsdf', N'Châu Thị A', N'Trần Nghĩa D')
INSERT KhachHang ( MaHD, HoTen, SoDienThoai, CCCD, DiaChi, HoTenCoDau, HoTenChuRe) VALUES ( N'HD20221209001', N'Lê Phước Thịnh', N'0334831013', N'098454233434', N'cần thơ', N'Lê THị A', N'Nguyễn Thị B')
INSERT KhachHang ( MaHD, HoTen, SoDienThoai, CCCD, DiaChi, HoTenCoDau, HoTenChuRe) VALUES ( N'HD20221213001', N'Nguyễn Trường D', N'0334831013', N'123123313123', N'dfsdfdf', N'Châu Thị H', N'Trần Nghĩa D')
INSERT KhachHang ( MaHD, HoTen, SoDienThoai, CCCD, DiaChi, HoTenCoDau, HoTenChuRe) VALUES ( N'HD20221213002', N'Nguyễn Trường E', N'0334831013', N'123212342342', N'fsdfsdfdsd', N'Châu Thị M', N'Trần Nghĩa D')

GO
INSERT ChiTietThucDon (MaTD, MaMA, ThuTu, GhiChu) VALUES (N'TD001', N'BANHPLAN', 1, '')
INSERT ChiTietThucDon (MaTD, MaMA, ThuTu, GhiChu) VALUES (N'TD001', N'BOLAGU', 2, '')
INSERT ChiTietThucDon (MaTD, MaMA, ThuTu, GhiChu) VALUES (N'TD001', N'CHAOGA', 3, '')
INSERT ChiTietThucDon (MaTD, MaMA, ThuTu, GhiChu) VALUES (N'TD001', N'CHAOVIT', 4, '')
INSERT ChiTietThucDon (MaTD, MaMA, ThuTu, GhiChu) VALUES (N'TD001', N'COCACOLA', 9, '')
INSERT ChiTietThucDon (MaTD, MaMA, ThuTu, GhiChu) VALUES (N'TD001', N'COMCHIENHAISAN', 6, '')
INSERT ChiTietThucDon (MaTD, MaMA, ThuTu, GhiChu) VALUES (N'TD001', N'HOTVITLON', 7, '')
INSERT ChiTietThucDon (MaTD, MaMA, ThuTu, GhiChu) VALUES (N'TD001', N'LAUDE', 8, '')
INSERT ChiTietThucDon (MaTD, MaMA, ThuTu, GhiChu) VALUES (N'TD001', N'NGHEUHAP', 5, '')
INSERT ChiTietThucDon (MaTD, MaMA, ThuTu, GhiChu) VALUES (N'TD002', N'BIA333', 9, '')
INSERT ChiTietThucDon (MaTD, MaMA, ThuTu, GhiChu) VALUES (N'TD002', N'CHAOVIT', 2, '')
INSERT ChiTietThucDon (MaTD, MaMA, ThuTu, GhiChu) VALUES (N'TD002', N'COMCHIENHAISAN', 3, '')
INSERT ChiTietThucDon (MaTD, MaMA, ThuTu, GhiChu) VALUES (N'TD002', N'GABOXOI', 4, '')
INSERT ChiTietThucDon (MaTD, MaMA, ThuTu, GhiChu) VALUES (N'TD002', N'GAHAP', 5, '')
INSERT ChiTietThucDon (MaTD, MaMA, ThuTu, GhiChu) VALUES (N'TD002', N'LAUCABOP', 6, '')
INSERT ChiTietThucDon (MaTD, MaMA, ThuTu, GhiChu) VALUES (N'TD002', N'NGHEUHAP', 7, '')
INSERT ChiTietThucDon (MaTD, MaMA, ThuTu, GhiChu) VALUES (N'TD002', N'SUACHUA', 8, '')
INSERT ChiTietThucDon (MaTD, MaMA, ThuTu, GhiChu) VALUES (N'TD002', N'SUPHAISAN', 1, '')
INSERT ChiTietThucDon (MaTD, MaMA, ThuTu, GhiChu) VALUES (N'TD003', N'BOCUBE', 1, '')
INSERT ChiTietThucDon (MaTD, MaMA, ThuTu, GhiChu) VALUES (N'TD003', N'BOLUCLAC', 2, '')
INSERT ChiTietThucDon (MaTD, MaMA, ThuTu, GhiChu) VALUES (N'TD003', N'CANGCUABACHHOA', 3, '')
INSERT ChiTietThucDon (MaTD, MaMA, ThuTu, GhiChu) VALUES (N'TD003', N'CARIGA', 4, '')
INSERT ChiTietThucDon (MaTD, MaMA, ThuTu, GhiChu) VALUES (N'TD003', N'CUAHOANGDE', 5, '')
INSERT ChiTietThucDon (MaTD, MaMA, ThuTu, GhiChu) VALUES (N'TD003', N'GAQUAYNGUVI', 6, '')
INSERT ChiTietThucDon (MaTD, MaMA, ThuTu, GhiChu) VALUES (N'TD003', N'LAUCABOP', 7, '')
INSERT ChiTietThucDon (MaTD, MaMA, ThuTu, GhiChu) VALUES (N'TD003', N'RUOUVANG', 8, '')
INSERT ChiTietThucDon (MaTD, MaMA, ThuTu, GhiChu) VALUES (N'TD003', N'TOYEN', 9, '')

INSERT ChiTietThucDon (MaTD, MaMA, ThuTu, GhiChu) VALUES (N'TD004', N'TOYEN', 1, '')
INSERT ChiTietThucDon (MaTD, MaMA, ThuTu, GhiChu) VALUES (N'TD004', N'TAUHU', 2, '')
INSERT ChiTietThucDon (MaTD, MaMA, ThuTu, GhiChu) VALUES (N'TD004', N'BOSOTTIEUXANH', 3, '')
INSERT ChiTietThucDon (MaTD, MaMA, ThuTu, GhiChu) VALUES (N'TD004', N'LAUCATHACLAC', 4, '')
INSERT ChiTietThucDon (MaTD, MaMA, ThuTu, GhiChu) VALUES (N'TD004', N'SUPCHAYTHAPCAM', 5, '')
INSERT ChiTietThucDon (MaTD, MaMA, ThuTu, GhiChu) VALUES (N'TD004', N'BICUONCHAY', 6, '')
INSERT ChiTietThucDon (MaTD, MaMA, ThuTu, GhiChu) VALUES (N'TD004', N'BOKHOCHAY', 7, '')
INSERT ChiTietThucDon (MaTD, MaMA, ThuTu, GhiChu) VALUES (N'TD004', N'LAUNAMCHAY', 8, '')
INSERT ChiTietThucDon (MaTD, MaMA, ThuTu, GhiChu) VALUES (N'TD004', N'COCACOLA', 9, '')

GO
INSERT NhanVien (MaNV, HoTen, NgaySinh, GioiTinh, SoDienThoai,Email, CCCD_CMND, HinhAnh, MaPB, MaVT, TrangThai) VALUES (N'NV001', N'Lê Phước Thịnh', CAST(N'2003-12-08' AS Date), 1, N'0334831013','thinhlpc0231@gmail.com', N'5223252452452', N'noimg', N'QUANLY', N'QLCC', 1)
INSERT NhanVien (MaNV, HoTen, NgaySinh, GioiTinh, SoDienThoai,Email, CCCD_CMND, HinhAnh, MaPB, MaVT, TrangThai) VALUES (N'NV002', N'Tô Văn Tấn', CAST(N'2003-01-01' AS Date), 1, N'0734238290', 'tovantan@gmail.com',N'5223345452452', N'noimag', N'VANCHUYEN', N'TAIXE', 1)
INSERT NhanVien (MaNV, HoTen, NgaySinh, GioiTinh, SoDienThoai,Email, CCCD_CMND, HinhAnh, MaPB, MaVT, TrangThai) VALUES (N'NV003', N'Phạm Xuân Vinh', CAST(N'2003-01-01' AS Date), 1, N'0907778028','phamxuanvinh@gmail.com', N'9283735736412', N'noimg', N'TIEPTAN', N'TIEPTAN', 1)
INSERT NhanVien (MaNV, HoTen, NgaySinh, GioiTinh, SoDienThoai,Email, CCCD_CMND, HinhAnh, MaPB, MaVT, TrangThai) VALUES (N'NV004', N'Mai Quốc Bảo', CAST(N'2003-01-01' AS Date), 1, N'0984722028', 'maiquocbao666@gmail.com',N'47285192831952', N'noumg', N'KETOAN', N'TINHHOADON', 1)
INSERT NhanVien (MaNV, HoTen, NgaySinh, GioiTinh, SoDienThoai,Email, CCCD_CMND, HinhAnh, MaPB, MaVT, TrangThai) VALUES (N'NV005', N'Nguyễn Hồng Quang', CAST(N'2003-01-01' AS Date), 1, N'0708578029','nguyenhongquang@gmail.com', N'1679452302159', N'noimg', N'CSVC', N'KHO', 1)
INSERT NhanVien (MaNV, HoTen, NgaySinh, GioiTinh, SoDienThoai,Email, CCCD_CMND, HinhAnh, MaPB, MaVT, TrangThai) VALUES (N'NV007', N'Nguyễn Văn A', CAST(N'2003-01-01' AS Date), 1, N'0458776345','nguyenvana@gmail.com', N'08435643354', N'noimg', N'BAOVE', N'BAOVE', 1)
INSERT NhanVien (MaNV, HoTen, NgaySinh, GioiTinh, SoDienThoai,Email, CCCD_CMND, HinhAnh, MaPB, MaVT, TrangThai) VALUES (N'NV008', N'Nguyễn Văn B', CAST(N'1998-01-01' AS Date), 1, N'0453486754','nguyenvanb@gmail.com', N'08445633214', N'noimg', N'BAOVE', N'BAOVE', 1)
INSERT NhanVien (MaNV, HoTen, NgaySinh, GioiTinh, SoDienThoai,Email, CCCD_CMND, HinhAnh, MaPB, MaVT, TrangThai) VALUES (N'NV009', N'Nguyễn Văn C', CAST(N'1980-01-01' AS Date), 1, N'0453456799','nguyenvanc@gmail.com', N'08490008867', N'noimg', N'PHUCVU', N'TAIXE', 1)
INSERT NhanVien (MaNV, HoTen, NgaySinh, GioiTinh, SoDienThoai,Email, CCCD_CMND, HinhAnh, MaPB, MaVT, TrangThai) VALUES (N'NV010', N'Nguyễn Văn D', CAST(N'2001-01-01' AS Date), 1, N'0453567976','nguyenvand@gmail.com', N'08408647463', N'noimg', N'PHUCVU', N'PHUCVU', 1)
INSERT NhanVien (MaNV, HoTen, NgaySinh, GioiTinh, SoDienThoai,Email, CCCD_CMND, HinhAnh, MaPB, MaVT, TrangThai) VALUES (N'NV011', N'Nguyễn Văn E', CAST(N'1979-01-01' AS Date), 1, N'0348354543','nguyenvane@gmail.com', N'08465868532', N'noimg', N'PHUCVU', N'PHUCVU', 1)
INSERT NhanVien (MaNV, HoTen, NgaySinh, GioiTinh, SoDienThoai,Email, CCCD_CMND, HinhAnh, MaPB, MaVT, TrangThai) VALUES (N'NV012', N'Nguyễn Văn H', CAST(N'1999-01-01' AS Date), 1, N'0349483187','nguyenvanh@gmail.com', N'08453543222', N'noimg', N'PHUCVU', N'PHUCVU', 1)
INSERT NhanVien (MaNV, HoTen, NgaySinh, GioiTinh, SoDienThoai,Email, CCCD_CMND, HinhAnh, MaPB, MaVT, TrangThai) VALUES (N'NV013', N'Nguyễn Văn J', CAST(N'1995-01-01' AS Date), 1, N'0344673544','nguyenvanj@gmail.com', N'08489744224', N'noimg', N'PHUCVU', N'PHUCVU', 1)
INSERT NhanVien (MaNV, HoTen, NgaySinh, GioiTinh, SoDienThoai,Email, CCCD_CMND, HinhAnh, MaPB, MaVT, TrangThai) VALUES (N'NV014', N'Nguyễn Văn K', CAST(N'1999-01-01' AS Date), 1, N'0341222399','nguyenvank@gmail.com', N'08434257734', N'noimg', N'NGHETHUAT', N'TAIXE', 1)
INSERT NhanVien (MaNV, HoTen, NgaySinh, GioiTinh, SoDienThoai,Email, CCCD_CMND, HinhAnh, MaPB, MaVT, TrangThai) VALUES (N'NV015', N'Nguyễn Văn T', CAST(N'1988-01-01' AS Date), 1, N'0343366754','nguyenvant@gmail.com', N'08434563386', N'noimg', N'NGHETHUAT', N'TRANGTRI', 1)
INSERT NhanVien (MaNV, HoTen, NgaySinh, GioiTinh, SoDienThoai,Email, CCCD_CMND, HinhAnh, MaPB, MaVT, TrangThai) VALUES (N'NV016', N'Nguyễn Văn T', CAST(N'2000-01-01' AS Date), 1, N'0342453567','nguyenvant@gmail.com', N'08499774424', N'noimg', N'NGHETHUAT', N'TRANGTRI', 1)
INSERT NhanVien (MaNV, HoTen, NgaySinh, GioiTinh, SoDienThoai,Email, CCCD_CMND, HinhAnh, MaPB, MaVT, TrangThai) VALUES (N'NV017', N'Nguyễn Văn L', CAST(N'1080-01-01' AS Date), 1, N'0344474422','nguyenvanl@gmail.com', N'08467436538', N'noimg', N'NGHETHUAT', N'MC', 1)
INSERT NhanVien (MaNV, HoTen, NgaySinh, GioiTinh, SoDienThoai,Email, CCCD_CMND, HinhAnh, MaPB, MaVT, TrangThai) VALUES (N'NV018', N'Nguyễn Văn M', CAST(N'1091-01-01' AS Date), 1, N'0343437885','nguyenvanm@gmail.com', N'08425366784', N'noimg', N'KYTHUAT', N'AMTHANH', 1)
INSERT NhanVien (MaNV, HoTen, NgaySinh, GioiTinh, SoDienThoai,Email, CCCD_CMND, HinhAnh, MaPB, MaVT, TrangThai) VALUES (N'NV019', N'Nguyễn Văn N', CAST(N'2003-01-01' AS Date), 1, N'0348825563','nguyenvann@gmail.com', N'08497554444', N'noimg', N'KYTHUAT', N'ANHSANG', 1)



GO
INSERT PhanLoaiDichVu (MaPLDV, TenDV) VALUES (N'DATMON', N'Đặt món')
INSERT PhanLoaiDichVu (MaPLDV, TenDV) VALUES (N'DUADON', N'Đưa đón')
INSERT PhanLoaiDichVu (MaPLDV, TenDV) VALUES (N'NGHETHUAT', N'Nghệ thuật')
INSERT PhanLoaiDichVu (MaPLDV, TenDV) VALUES (N'TRANGTRI', N'Trang trí')
INSERT PhanLoaiDichVu (MaPLDV, TenDV) VALUES (N'DIKEM', N'Đi kèm')
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
INSERT ThucDon (MaTD, TenTD, GhiChu) VALUES (N'TD002', N'Thực trung lưu', '')
INSERT ThucDon (MaTD, TenTD, GhiChu) VALUES (N'TD003', N'Thực đơn hoàng gia', '')
INSERT ThucDon (MaTD, TenTD, GhiChu) VALUES (N'TD004', N'Thực đơn chay', '')
GO
INSERT TrangThaiHopDong (MaTT, TenTT) VALUES (N'DANGLAP', N'Đang lập')
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
ALTER TABLE ChiTietDatMon  WITH CHECK ADD  CONSTRAINT FK_ChiTietDatMon_DichVuDatMon1 FOREIGN KEY(MaHD,MaTD)
REFERENCES DichVuDatMon (MaHD,MaTD)
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


GO
ALTER TABLE HoaDon  WITH CHECK ADD  CONSTRAINT FK_HoaDon_NhanVien FOREIGN KEY(MaNV)
REFERENCES NhanVien (MaNV)
GO

GO
ALTER TABLE HoaDon  WITH CHECK ADD  CONSTRAINT FK_HoaDon_NhanVien2 FOREIGN KEY(MaNLLan2)
REFERENCES NhanVien (MaNV)
GO

GO
ALTER TABLE KhachHang  WITH CHECK ADD  CONSTRAINT FK_KhachHang_HopDong FOREIGN KEY(MaHD)
REFERENCES HopDong (MaHD)
ON DELETE CASCADE 
ON UPDATE CASCADE
GO
ALTER TABLE KhachHang CHECK CONSTRAINT FK_KhachHang_HopDong
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

ALTER TABLE ChiPhiPhatSinh  WITH CHECK ADD  CONSTRAINT FK_ChiPhiPhatSinh_DichVu FOREIGN KEY(MaDV)
REFERENCES DichVu (MaDV)
ON UPDATE CASCADE 

ALTER TABLE HopDongDichVuDiKem WITH CHECK ADD  CONSTRAINT FK_HDDVDK_HopDong FOREIGN KEY(MaHD)
REFERENCES HopDong (MaHD)
ON UPDATE CASCADE 
ON DELETE CASCADE 

ALTER TABLE ChiTietDichVuDiKem  WITH CHECK ADD  CONSTRAINT FK_CTDVDK_HDDVDK FOREIGN KEY (MaHD)
REFERENCES HopDongDichVuDiKem (MaHD)
ON UPDATE CASCADE 
ON DELETE CASCADE 




GO
CREATE PROCEDURE tinhTien( @MaHD varchar(50) )
AS
BEGIN
	DECLARE @ChiPhi bigint,@ChiPhiPhatSinh bigint;
	DECLARE @chiPhiHopDongDichVu bigint, @chiPhiDatMon bigint, @chiPhiDiKem bigint,@chiPhiSanh bigint , @chiPhiPSDichVu bigint, @chiPhiPSDatMon bigint, @chiPhiPSDiKem bigint;
	
	SELECT @chiPhiHopDongDichVu = ( SELECT  SUM( distinct hddv.ChiPhi) AS ChiPhi  FROM HopDongDichVu hddv WHERE hddv.MaHD = @MaHD) 
	SELECT @chiPhiDatMon = ( SELECT SUM(ChiPhi) FROM DichVuDatMon WHERE MaHD = @MaHD)
	SELECT @chiPhiDiKem = ( SELECT ChiPhi FROM HopDongDichVuDiKem WHERE MaHD =@MaHD )
	SELECT @chiPhiSanh = ( (SELECT (GiaThueSanh + ( GiaBan * hd.SoLuongBan )) AS ChiPhi FROM Sanh s INNER JOIN HopDong hd ON s.MaSanh = hd.Sanh WHERE MaHD = @MaHD) )

	IF @chiPhiHopDongDichVu IS NULL SET @chiPhiHopDongDichVu = 0
	IF @chiPhiDatMon IS NULL SET @chiPhiDatMon = 0
	IF @chiPhiDiKem IS NULL SET @chiPhiDiKem = 0
	IF @chiPhiSanh IS NULL SET @chiPhiSanh = 0

	SELECT @ChiPhi = @chiPhiHopDongDichVu + @chiPhiDatMon + @chiPhiDiKem + @chiPhiSanh

	PRINT @ChiPhi

	SELECT @chiPhiPSDichVu = ( SELECT SUM ( a.ChiPhiPhatSinh )
							FROM ( SELECT SUM ( distinct ChiPhiPhatSinh)  AS ChiPhiPhatSinh FROM HopDongDichVu hddv INNER JOIN ChiTietDichVu ct ON hddv.MaHD = ct.MaHD
							WHERE hddv.MaHD = @MaHD GROUP BY ct.MaCSVC ) a )
	SELECT @chiPhiPSDatMon = ( SELECT SUM( ChiPhiPhatSinh * SoLuong )  FROM ChiTietDatMon WHERE MaHD = @MaHD )
	SELECT @chiPhiPSDiKem = ( SELECT SUM(ct.ChiPhiPhatSinh * ct.SoLuong) FROM ChiTietDichVuDiKem ct WHERE ct.MaHD = @MaHD )

	IF @chiPhiPSDichVu IS NULL SET @chiPhiPSDichVu = 0
	IF @chiPhiPSDatMon IS NULL SET @chiPhiPSDatMon = 0
	IF @chiPhiPSDiKem IS NULL SET @chiPhiPSDiKem = 0

	SELECT @ChiPhiPhatSinh = @chiPhiPSDichVu + @chiPhiPSDatMon + @chiPhiPSDiKem
	
	PRINT @ChiPhiPhatSinh

	SELECT @ChiPhi AS ChiPhi,@ChiPhiPhatSinh AS ChiPhiPhatSinh
END
GO


GO
CREATE PROCEDURE tinhTienVoiSanh( @MaHD varchar(50), @MaSanh varchar(50) ,@SoLuongBan int)
AS
BEGIN
	DECLARE @ChiPhi bigint,@ChiPhiPhatSinh bigint;
	DECLARE @chiPhiHopDongDichVu bigint, @chiPhiDatMon bigint, @chiPhiDiKem bigint,@chiPhiSanh bigint , @chiPhiPSDichVu bigint, @chiPhiPSDatMon bigint, @chiPhiPSDiKem bigint;
	
	SELECT @chiPhiHopDongDichVu = ( SELECT  SUM( distinct hddv.ChiPhi) AS ChiPhi  FROM HopDongDichVu hddv WHERE hddv.MaHD = @MaHD) 
	SELECT @chiPhiDatMon = ( SELECT SUM(ChiPhi) FROM DichVuDatMon WHERE MaHD = @MaHD)
	SELECT @chiPhiDiKem = ( SELECT ChiPhi FROM HopDongDichVuDiKem WHERE MaHD =@MaHD )
	SELECT @chiPhiSanh =  ( (SELECT GiaThueSanh FROM Sanh WHERE MaSanh = @MaSanh) + ((SELECT GiaBan FROM Sanh WHERE MaSanh = @MaSanh) * @SoLuongBan)  )


	IF @chiPhiHopDongDichVu IS NULL SET @chiPhiHopDongDichVu = 0
	IF @chiPhiDatMon IS NULL SET @chiPhiDatMon = 0
	IF @chiPhiDiKem IS NULL SET @chiPhiDiKem = 0
	IF @chiPhiSanh IS NULL SET @chiPhiSanh = 0

	SELECT @ChiPhi = @chiPhiHopDongDichVu + @chiPhiDatMon + @chiPhiDiKem + @chiPhiSanh

	PRINT @ChiPhi

	SELECT @chiPhiPSDichVu = ( SELECT SUM ( a.ChiPhiPhatSinh )
							FROM ( SELECT SUM ( distinct ChiPhiPhatSinh)  AS ChiPhiPhatSinh FROM HopDongDichVu hddv INNER JOIN ChiTietDichVu ct ON hddv.MaHD = ct.MaHD
							WHERE hddv.MaHD = @MaHD GROUP BY ct.MaCSVC ) a )
	SELECT @chiPhiPSDatMon = ( SELECT SUM( ChiPhiPhatSinh * SoLuong )  FROM ChiTietDatMon WHERE MaHD = @MaHD )
	SELECT @chiPhiPSDiKem = ( SELECT SUM(ct.ChiPhiPhatSinh * ct.SoLuong) FROM ChiTietDichVuDiKem ct WHERE ct.MaHD = @MaHD )

	IF @chiPhiPSDichVu IS NULL SET @chiPhiPSDichVu = 0
	IF @chiPhiPSDatMon IS NULL SET @chiPhiPSDatMon = 0
	IF @chiPhiPSDiKem IS NULL SET @chiPhiPSDiKem = 0

	SELECT @ChiPhiPhatSinh = @chiPhiPSDichVu + @chiPhiPSDatMon + @chiPhiPSDiKem
	
	PRINT @ChiPhiPhatSinh

	SELECT @ChiPhi AS ChiPhi,@ChiPhiPhatSinh AS ChiPhiPhatSinh
END
GO


-- thống kê doanh thu theo năm
GO
CREATE PROC thongKeDoanhThuNam 
AS
BEGIN
	SELECT SUM(IIF(hd.TrangThai = 0,TienCoc,hd.TongTien + hdd.TienCoc )) AS TongDoanhThu ,
	IIF(hd.TrangThai = 0, YEAR(hd.NgayLap),YEAR(NgayLapLan2)) AS Nam ,
	MAX (  IIF(hd.TrangThai = 0,TienCoc, hd.TongTien+ hdd.TienCoc )         ) AS DoanhThuCaoNhat,
	MIN ( IIF(hd.TrangThai = 0,TienCoc,hd.TongTien + hdd.TienCoc) ) AS DoanhThuThapNhat,
	COUNT(hd.MaHD) AS SoLuongHopDong
	FROM HopDong hdd INNER JOIN HoaDon hd ON hdd.MaHD = hd.MaHD 
	GROUP BY  IIF(hd.TrangThai = 0, YEAR(hd.NgayLap),YEAR(NgayLapLan2))
	ORDER BY Nam ASC
END
GO



GO
CREATE PROC thongKeDoanhThuThang @Nam int
AS
BEGIN
	--DECLARE		
	SELECT SUM(IIF(hd.TrangThai = 0,TienCoc,hd.TongTien + hdd.TienCoc)) AS TongDoanhThu ,
	IIF(hd.TrangThai = 0, MONTH(hd.NgayLap),MONTH(NgayLapLan2)) AS Thang ,
	MAX (  IIF(hd.TrangThai = 0,TienCoc,hd.TongTien + hdd.TienCoc ) ) AS DoanhThuCaoNhat,
	MIN ( IIF(hd.TrangThai = 0,TienCoc,hd.TongTien+ hdd.TienCoc ) ) AS DoanhThuThapNhat,
	COUNT(hd.MaHD) AS SoLuongHopDong
	FROM HopDong hdd INNER JOIN HoaDon hd ON hdd.MaHD = hd.MaHD 
	WHERE IIF(hd.TrangThai = 0, YEAR(hd.NgayLap),YEAR(NgayLapLan2)) = @Nam
	GROUP BY  IIF(hd.TrangThai = 0, MONTH(hd.NgayLap),MONTH(NgayLapLan2))
	ORDER BY Thang
	
END
GO

GO
CREATE PROC thongKeDoanhThuNgay 
AS 
BEGIN
	SELECT SUM(IIF(hd.TrangThai = 0,TienCoc,hd.TongTien + hdd.TienCoc )) AS TongDoanhThu ,
	IIF(hd.TrangThai = 0, hd.NgayLap,NgayLapLan2) AS Ngay ,
	MAX (  IIF(hd.TrangThai = 0,TienCoc, hd.TongTien+ hdd.TienCoc )         ) AS DoanhThuCaoNhat,
	MIN ( IIF(hd.TrangThai = 0,TienCoc,hd.TongTien + hdd.TienCoc) ) AS DoanhThuThapNhat,
	COUNT(hd.MaHD) AS SoLuongHopDong
	FROM HopDong hdd INNER JOIN HoaDon hd ON hdd.MaHD = hd.MaHD 
	GROUP BY  IIF(hd.TrangThai = 0, hd.NgayLap,NgayLapLan2)
	ORDER BY Ngay ASC
END
GO

GO
CREATE PROC thongKeDoanhThuNgayTop @top int, @Nam int 
AS 
BEGIN
	SELECT Top ( @top  )SUM(IIF(hd.TrangThai = 0,TienCoc,hd.TongTien + hdd.TienCoc )) AS TongDoanhThu ,
	IIF(hd.TrangThai = 0, hd.NgayLap,NgayLapLan2) AS Ngay ,
	MAX (  IIF(hd.TrangThai = 0,TienCoc, hd.TongTien+ hdd.TienCoc )         ) AS DoanhThuCaoNhat,
	MIN ( IIF(hd.TrangThai = 0,TienCoc,hd.TongTien + hdd.TienCoc) ) AS DoanhThuThapNhat,
	COUNT(hd.MaHD) AS SoLuongHopDong
	FROM HopDong hdd INNER JOIN HoaDon hd ON hdd.MaHD = hd.MaHD 
		WHERE IIF(hd.TrangThai = 0, YEAR(hd.NgayLap),YEAR(NgayLapLan2)) = @Nam
	GROUP BY  IIF(hd.TrangThai = 0, hd.NgayLap,NgayLapLan2)
	ORDER BY TongDoanhThu DESC
END
GO


CREATE PROC thongKeDoanhThuNgayTopSL @top int, @nam int 
AS 
BEGIN
	SELECT Top ( @top  )SUM(IIF(hd.TrangThai = 0,TienCoc,hd.TongTien + hdd.TienCoc )) AS TongDoanhThu ,
	IIF(hd.TrangThai = 0, hd.NgayLap,NgayLapLan2) AS Ngay ,
	MAX (  IIF(hd.TrangThai = 0,TienCoc, hd.TongTien+ hdd.TienCoc )         ) AS DoanhThuCaoNhat,
	MIN ( IIF(hd.TrangThai = 0,TienCoc,hd.TongTien + hdd.TienCoc) ) AS DoanhThuThapNhat,
	COUNT(hd.MaHD) AS SoLuongHopDong
	FROM HopDong hdd INNER JOIN HoaDon hd ON hdd.MaHD = hd.MaHD 
		WHERE IIF(hd.TrangThai = 0, YEAR(hd.NgayLap),YEAR(NgayLapLan2)) = @Nam
	GROUP BY  IIF(hd.TrangThai = 0, hd.NgayLap,NgayLapLan2)
	ORDER BY SoLuongHopDong DESC
END


GO



GO
CREATE FUNCTION formatMoney (@Tien bigint)
RETURNS VARCHAR(255)
AS
BEGIN
RETURN SUBSTRING(FORMAT(@Tien, 'c', 'vi-VN'),0, LEN(FORMAT(@Tien, 'c', 'vi-VN'))-4)
END

-- ChiTietDichVuDAO

-- INSERT vào HopDongDichVu

--ChiPhi sẽ đc tính bằng các Chi Phi trong chi tiết trên code
INSERT HopDongDichVu (MaHD, MaDV,MaGoi ,ChiPhi, GhiChu) VALUES (N'HD003', N'TTBANTIEC',NULL ,1200000, NULL)


-- UPDATE HopDongDichVu 
UPDATE HopDongDichVu
SET MaGoi = NULL, ChiPhi = 13000000,  GhiChu = N'test nè'
WHERE MaHD = 'HD003' AND MaDV = 'TTBANTIEC'

-- DELETE HopDongDichVu
DELETE HopDongDichVu WHERE MaHD = 'HD20221130004' AND MaDV = 'TTSANKHAU'
DELETE ChiTietDichVu WHERE MaHD = 'HD20221130004' AND MaDV = 'TTSANKHAU'
	
-- CHECK đã lưu về dịch vụ hay chưa
SELECT * FROM HopDongDichVu WHERE MaHD = 'HD003' AND MaDV = 'TTSANKHAU'

-- SELECT Lấy thông tin trang trí bàn ghế

SELECT MaHD,MaDV,MaGoi,ChiPhi, 

(SELECT SUM(ChiPhiPhatSinh) AS ChiPhiPhatSinh FROM HopDongDichVu hddv 
INNER JOIN ChiTietDichVu ct ON hddv.MaHD = ct.MaHD
WHERE ct.MaHD = 'HD20220101001' AND  hddv.MaDV = 'TTCONG' AND ct.MaDV = 'TTCONG'   ) AS ChiPhiPhatSinh ,

GhiChu FROM HopDongDichVu hddv  
WHERE hddv.MaHD = 'HD20220101001' AND hddv.MaDV = 'TTCONG'




-- ChiTietDichVu

-- INSERT ChiTietDichVu
INSERT ChiTietDichVu (MaHD,MaDV, MaCSVC,ChiPhi ,ChiPhiPhatSinh, GhiChu) VALUES (N'HD003', N'TTBANTIEC', N'AOGHEDO',500000 ,10000, NULL)
INSERT ChiTietDichVu (MaHD,MaDV, MaCSVC,ChiPhi ,ChiPhiPhatSinh, GhiChu) VALUES (N'HD003', N'TTBANTIEC', N'HOAHONGXANH',1000000 ,120000, NULL)
INSERT ChiTietDichVu (MaHD,MaDV, MaCSVC,ChiPhi ,ChiPhiPhatSinh, GhiChu) VALUES (N'HD003', N'TTBANTIEC', N'TRAIBANTRANG',500000 ,10000, NULL)


-- SELECT Lấy thông tin chi tiết trang trí trong dịch vụ ko tu custom	

SELECT hddv.MaHD,csvc.MaCSVC,csvc.TenCSVC, hddv.MaDV,csvc.GiaThue AS ChiPhi,ct.ChiPhiPhatSinh,ct.GhiChu FROM HopDongDichVu hddv 
INNER JOIN ChiTietDichVu ct ON hddv.MaHD = ct.MaHD
INNER JOIN CoSoVatChat csvc ON csvc.MaCSVC = ct.MaCSVC 
INNER JOIN DanhMucCon dmc ON dmc.MaDMC = csvc.MaDMC
WHERE ct.MaHD = 'HD20220101001' AND  hddv.MaDV = 'TTCONG' AND ct.MaDV = 'TTCONG' AND csvc.MaDMC = 'BANGTEN'

-- SELECT lấy thông tin chi tiết  trang trí tự custom
SELECT hddv.MaHD,csvc.MaCSVC,csvc.TenCSVC, hddv.MaDV,ct.ChiPhi,ct.ChiPhiPhatSinh,ct.GhiChu FROM HopDongDichVu hddv 
INNER JOIN ChiTietDichVu ct ON hddv.MaHD = ct.MaHD
INNER JOIN CoSoVatChat csvc ON csvc.MaCSVC = ct.MaCSVC 
INNER JOIN DanhMucCon dmc ON dmc.MaDMC = csvc.MaDMC
WHERE ct.MaHD = 'HD20220101001' AND  hddv.MaDV = 'TTCONG' AND ct.MaDV = 'TTCONG' AND csvc.MaDMC = 'BANGTEN'

-- SELECT Lấy tất cả thông tin chi tiết trang trí trong dịch vụ	 không tự chỉnh sữa giá

SELECT hddv.MaHD,csvc.MaCSVC,csvc.TenCSVC, hddv.MaDV,ct.ChiPhi,ct.ChiPhiPhatSinh,ct.GhiChu FROM HopDongDichVu hddv 
INNER JOIN ChiTietDichVu ct ON hddv.MaHD = ct.MaHD
INNER JOIN CoSoVatChat csvc ON csvc.MaCSVC = ct.MaCSVC 
WHERE ct.MaHD = 'HD20220101001' AND  hddv.MaDV = 'TTSANKHAU' AND ct.MaDV = 'TTSANKHAU' 

--

-- SELECT ChiPhi tu CoSoVatChat	
SELECT GiaThue FROM CoSoVatChat WHERE MaCSVC = 'HOAHONGDO'



--UPDATE ChiTietDichVu
UPDATE ctdv SET MaCSVC = 'HOAHONGXANH', ChiPhi = 1000000, ChiPhiPhatSinh = 200000, GhiChu = N'Thêm một bó '
FROM ChiTietDichVu ctdv INNER JOIN CoSoVatChat csvc ON ctdv.MaCSVC = csvc.MaCSVC
WHERE MaHD = 'HD003' AND MaDV = 'TTBANTIEC' AND csvc.MaDMC = 'HOACHUDAO'

-- lấy tổng chi phí phát sinh 

SELECT SUM(ChiPhiPhatSinh) AS ChiPhiPhatSinh FROM HopDongDichVu hddv 
INNER JOIN ChiTietDichVu ct ON hddv.MaHD = ct.MaHD
WHERE ct.MaHD = 'HD003' AND  hddv.MaDV = 'TTBANTIEC' AND ct.MaDV = 'TTBANTIEC' 




-- DatMonDAO

--SELECT Thông tin dịch vụ đặt món 
SELECT MaHD,MaTD,ChiPhi, 
( SELECT SUM(ChiPhiPhatSinh * SoLuong)  FROM ChiTietDatMon WHERE MaHD = 'HD20220101001' AND  MaTD = 'TD001' ) AS ChiPhiPhatSinh,
GhiChu FROM DichVuDatMon WHERE MaHD = 'HD20220101001' AND MaTD = 'TD001'

SELECT MaHD,MaTD,ChiPhi, 
( SELECT SUM(ChiPhiPhatSinh)  FROM ChiTietDatMon WHERE MaHD = 'HD20220101001') AS ChiPhiPhatSinh,
GhiChu FROM DichVuDatMon WHERE MaHD = 'HD20220101001'



AND MaTD = 'TD001'


-- INSERT Thông tin dịch vụ đặt món
INSERT DichVuDatMon (MaHD,MaTD,ChiPhi, GhiChu) VALUES (N'HD20220101001','TD001', 32000000, NULL)

--UPDATE Thông tin dịch vụ đặt món
UPDATE DichVuDatMon
SET MaTD = 'TD001', ChiPhi = 34000000, GhiChu = NULL
WHERE MaHD = 'HD20220101001' AND MaTD = 'TD001'

DELETE DichVuDatMon WHERE MaHD = 'HD20220101001' AND MaTD = 'TD001'


-- lấy tổng chi phí phát sinh
SELECT SUM(ChiPhiPhatSinh) AS ChiPhiPhatSinh FROM ChiTietDatMon WHERE MaHD = 'HD003'

SELECT * FROM ThucDon

SELECT * FROM KhachHang ORDER BY MaKH DESC

--ChiTietDatMon

--Lấy tất cả danh sách món ăn trong hợp đồng
		SELECT MaHD,MaTD,ct.MaMA,TenMA,MaPL, ma.GiaTien AS ChiPhi,ct.ChiPhiPhatSinh,ThuTu,ct.SoLuong,GhiChu,HinhAnh FROM ChiTietDatMon ct
		INNER JOIN MonAn ma ON ct.MaMA = ma.MaMA 
		WHERE MaHD = 'HD20220101001' AND MaTD = 'TD001'	

		SELECT MaHD,MaTD,ct.MaMA,TenMA,MaPL, ma.GiaTien AS ChiPhi,ct.ChiPhiPhatSinh,ThuTu,ct.SoLuong,GhiChu,HinhAnh FROM ChiTietDatMon ct
		INNER JOIN MonAn ma ON ct.MaMA = ma.MaMA 
		WHERE MaHD = 'HD20220101001' AND MaPL = 'NUOC'


-- SELECT danh sách thực đơn chính
SELECT Top 1 MaHD,MaTD,ct.MaMA,TenMA,MaPL, ma.GiaTien AS ChiPhi,ct.ChiPhiPhatSinh,ThuTu,ct.SoLuong,GhiChu,HinhAnh FROM ChiTietDatMon ct
		INNER JOIN MonAn ma ON ct.MaMA = ma.MaMA 
		WHERE MaHD = 'HD20220101001' ORDER BY SoLuong DESC 


-- SELECT danh sách thực đơn phụ
SELECT Top 1 MaHD,MaTD,ct.MaMA,TenMA,MaPL, ma.GiaTien AS ChiPhi,ct.ChiPhiPhatSinh,ThuTu,ct.SoLuong,GhiChu,HinhAnh FROM ChiTietDatMon ct
		INNER JOIN MonAn ma ON ct.MaMA = ma.MaMA 
		WHERE MaHD = 'HD20220101001' AND SoLuong != -1 ORDER BY SoLuong ASC 


-- INSERT vào chi tiết đặt món
INSERT ChiTietDatMon (MaHD, MaMA,ChiPhiPhatSinh ,ThuTu, SoLuong,GhiChu,MaTD) VALUES (N'HD20220101001', N'GABOXOI',1000,10, 1, '','TD001')
INSERT ChiTietDatMon (MaHD, MaMA,ChiPhiPhatSinh ,ThuTu,SoLuong, GhiChu) VALUES (N'HD20220802001', N'BOLAGU',0, 2, 10,NULL)
INSERT ChiTietDatMon (MaHD, MaMA,ChiPhiPhatSinh ,ThuTu,SoLuong, GhiChu) VALUES (N'HD20220802001', N'BOLUCLAC',0, 3,10, NULL)

--DELETE tất cả chi tiết đặt món và thêm lại từ đầu

DELETE ChiTietDatMon WHERE MaHD = 'HD20220101001'  AND MaTD = 'TD001'

DELETE DichVuDatMon WHERE MaHD = 'HD20220802001'


-- lấy danh sách các món ăn không có trong thực đơn hợp đồng
SELECT * FROM MonAn WHERE MaMA NOT IN( SELECT MaMA FROM ChiTietDatMon WHERE MaHD = 'HD20220101001'  )

-- lấy danh sách các món không có trong thực đơn 
SELECT * FROM MonAn WHERE MaMA NOT IN( SELECT MaMA FROM ChiTietThucDon WHERE MaTD = 'TD001'  )


--lấy danh sách các món có trong thực đơn
SELECT MaTD,ct.MaMA,TenMA,ma.GiaTien,ThuTu,GhiChu FROM ChiTietThucDon ct
INNER JOIN MonAn ma ON ct.MaMA = ma.MaMA 
WHERE MaTD = 'TD001'


--HopDongDichVuDiKemDAO

--INSERT HopDongDichVuDiKem
INSERT HopDongDichVuDiKem( MaHD,ChiPhi,ChiPhiPhatSinh,GhiChu ) VALUES( 'HD20220105001',1250000,0,NULL )

--UPDATE HopDongDichVuDiKem 
UPDATE HopDongDichVuDiKem 
SET ChiPhi = 1200000,ChiPhiPhatSinh = null, GhiChu = null
WHERE MaHD = 'HD003'

DELETE HopDongDichVuDiKem WHERE MaHD = 'HD20220105001'

--SELECT Thông tin HopDongDichVuDiKem 
	SELECT MaHD,ChiPhi,GhiChu,( SELECT SUM(ChiPhiPhatSinh)  FROM ChiTietDichVuDiKem WHERE MaHD = 'HD20220105001' ) AS ChiPhiPhatSinh FROM HopDongDichVuDiKem 
	WHERE MaHD = 'HD20221129001'


---ChiTietDichVuDiKemDAO


-- INSERT dịch vụ đi kèm
INSERT ChiTietDichVuDiKem (MaHD, MaDV, GhiChu, ChiPhiPhatSinh, ChiPhi,SoLuong) VALUES (N'HD20220105001', N'BANHKEM', N'3 tầng', 120000, NULL,1)
INSERT ChiTietDichVuDiKem (MaHD, MaDV, GhiChu, ChiPhiPhatSinh, ChiPhi, SoLuong) VALUES (N'HD20220105001', N'PHAOKIMTUYEN', N'54 cái', 479999, 0,1)
-- DELETE tất cả dịch vụ đi kèm trong hợp đồng
DELETE ChiTietDichVuDiKem WHERE MaHD = 'HD20220105001'


-- lấy danh sách các dịch vụ đi kèm
SELECT MaHD,dv.MaDV,dv.TenDV,dv.Gia AS ChiPhi,GhiChu,ChiPhiPhatSinh,SoLuong  FROM ChiTietDichVuDiKem ct
INNER JOIN DichVuDiKem  dv ON ct.MaDV = dv.MaDV
WHERE MaHD = 'HD20220105001' AND ct.MaDV = 'PHAOKIEMTUYEN'

SELECT * FROM DichVuDiKem



-- tinh toan cac khoan chi phi trong dichvu di kem
SELECT ChiPhi FROM HopDongDichVuDiKem WHERE MaHD = 'HD20220105001'

SELECT SUM(ct.ChiPhiPhatSinh) FROM HopDongDichVuDiKem dv INNER JOIN ChiTietDichVuDiKem ct ON dv.MaHD = ct.MaHD WHERE dv.MaHD = 'HD20220105001'

-- lấy danh sách các dịch vụ không có trong hợp đồng


-- GoiDichVuDAO

-- lấy thông tin gói dịch vụ
SELECT * FROM GoiDichVu WHERE MaDV = 'TTBANTIEC'

-- lấy thông tin chi tiết các vật không tự custom trong gói dịch vụ 
SELECT  ct.MaCSVC,csvc.TenCSVC, goi.MaDV,csvc.GiaThue AS ChiPhi,ct.GhiChu FROM ChiTietGoiDichVu  ct
INNER JOIN CoSoVatChat csvc ON ct.MaCSVC = csvc.MaCSVC
INNER JOIN GoiDichVu goi ON goi.MaGoi = ct.MaGoi
INNER JOIN DanhMucCon dmc ON dmc.MaDMC = csvc.MaDMC
WHERE ct.MaGoi = 'TTBANGHE1' AND csvc.MaDMC = 'TRAIBAN'


-- lấy thông tin chi tiết các vật tự custom trong gói dịch vụ 
SELECT  ct.MaCSVC,csvc.TenCSVC, goi.MaDV,ct.ChiPhi,ct.GhiChu FROM ChiTietGoiDichVu  ct
INNER JOIN CoSoVatChat csvc ON ct.MaCSVC = csvc.MaCSVC
INNER JOIN GoiDichVu goi ON goi.MaGoi = ct.MaGoi
INNER JOIN DanhMucCon dmc ON dmc.MaDMC = csvc.MaDMC
WHERE ct.MaGoi = 'TTBANGHE1' AND csvc.MaDMC = 'TRAIBAN'


--HoaDonDAO

INSERT ChiPhiPhatSinh (MaHD,MaPLDV,ChiPhi,LyDo) VALUES ('HD001','TTBANTIEC',10000,'làm cháy một cái áo gối')
 INSERT ChiPhiPhatSinh (MaHD,MaPLDV,ChiPhi,LyDo) VALUES ('HD001','TTSANKHAU',14000,'làm vỡ một cái bóng đèn')

 DELETE ChiPhiPhatSinh WHERE MaHD = 'HD001'

SELECT * FROM ChiPhiPhatSinh WHERE MaHD = 'HD003' AND MaPLDV = 'TTBANTIEC'


SELECT ChiPhi FROM HopDongDichVu WHERE MaHD = 'HD003' AND MaDV = 'TTBANTIEC'


SELECT ChiPhi FROM HopDongDichVuDiKem WHERE MaHD = 'HD001'

SELECT SUM(ChiPhiPhatSinh) AS ChiPhiPhatSinh FROM ChiTietDichVu WHERE MaHD = 'HD001'
SELECT SUM(ChiPhiPhatSinh) FROM ChiTietDatMon WHERE MaHD = 'HD001'
SELECT SUM(ChiPhiPhatSinh) FROM ChiTietDichVuDiKem WHERE MaHD = 'HD001';








ALTER PROC tinhHoaDon ( @MaHD varchar(5) )
AS
BEGIN
	
	
	
	-- tổng hợp chi phí trực tiếp trong select
	SELECT MaHoaDon,hd.MaHD,hd.NgayLap,nv.HoTen AS NguoiLap,hd.TienCoc,hd.TongTien,hd.TrangThai , 
	( SELECT ChiPhi FROM HopDongDichVu WHERE MaHD = @MaHD AND MaDV = 'TTBANTIEC' ) AS TrangTriBanTiec,
	( SELECT ChiPhi FROM HopDongDichVu WHERE MaHD = @MaHD AND MaDV = 'TTCONG' ) AS TrangTriCong,
	( SELECT ChiPhi FROM HopDongDichVu WHERE MaHD = @MaHD AND MaDV = 'TTSANKHAU' ) AS TrangTriSanKhau,
	( SELECT ChiPhi FROM HopDongDichVu WHERE MaHD = @MaHD AND MaDV = 'NGHETHUAT' ) AS NgheThuat,
	( SELECT ChiPhi FROM DichVuDatMon WHERE MaHD = @MaHD ) AS DatMon,
	(SELECT ChiPhi FROM HopDongDichVuDiKem WHERE MaHD = @MaHD) AS DiKem,PhatSinh
	
FROM HoaDon hd INNER JOIN HopDong hdd ON hd.MaHD = hdd.MaHD
INNER JOIN NhanVien nv  ON nv.MaNV = hd.MaNV
WHERE hd.MaHD = @MaHD
END

EXEC tinhHoaDon @maHD = 'HD002'


--SELECT tất cả hóa đơn

SELECT MaHoaDon,hd.MaHD,hd.NgayLap,hd.NgayLapLan2,nv.MaNV,HoTen,hdd.TienCoc,hd.MaNLLan2, ( SELECT HoTen FROM NhanVien WHERE MaNV = hd.MaNLLan2 ) AS HoTenNguoiLap2,
( SELECT SUM(ChiPhi) FROM ChiPhiPhatSinh WHERE hd.MaHD = hdd.MaHD ) AS ChiPhiPhatSinh, hdd.TongTien,hd.TrangThai
FROM HoaDon hd INNER JOIN HopDong hdd ON hd.MaHD = hdd.MaHD
INNER JOIN NhanVien nv ON hd.MaNV = nv.MaNV


--
INSERT HoaDon ( MaHD,NgayLap,MaNV,TrangThai ) VALUES ( 'HD002','1/1/2022','NV101',0 )

UPDATE HoaDon SET TrangThai = 1,NgayLapLan2 ='11/30/2022',MaNLLan2 = 'NV002'  WHERE MaHD = 'HD20221130003'


-- UPDATE 



-- cách lấy vật chất chưa được sử dụng

SELECT distinct csvc.MaCSVC,TenCSVC,MaDMC,SoLuong ,GiaThue,
(
SELECT  SUM(SoLuongBan) AS SoLuongBan FROM  CoSoVatChat csvc INNER JOIN ChiTietDichVu ctdv ON csvc.MaCSVC = ctdv.MaCSVC
INNER JOIN HopDong hd ON hd.MaHD = ctdv.MaHD
WHERE csvc.MaDMC = 'AOGHE' AND NgayToChuc = '1/10/2022'
GROUP BY csvc.MaCSVC
) AS SoLuongBan

FROM CoSoVatChat csvc INNER JOIN ChiTietDichVu ctdv ON csvc.MaCSVC = ctdv.MaCSVC
INNER JOIN HopDong hd ON hd.MaHD = ctdv.MaHD
WHERE csvc.MaDMC = 'AOGHE' AND NgayToChuc = '1/10/2022'






SELECT distinct csvc.MaCSVC,TenCSVC,MaDMC,SoLuong ,GiaThue,
(
SELECT distinct  SUM(SoLuongBan) AS SoLuongBan FROM  CoSoVatChat csvc LEFT JOIN ChiTietDichVu ctdv ON csvc.MaCSVC = ctdv.MaCSVC
INNER JOIN HopDong hd ON hd.MaHD = ctdv.MaHD
WHERE csvc.MaDMC = 'CONG' 
GROUP BY csvc.MaCSVC
) AS SoLuongBan

FROM CoSoVatChat csvc LEFT JOIN ChiTietDichVu ctdv ON csvc.MaCSVC = ctdv.MaCSVC
INNER JOIN HopDong hd ON hd.MaHD = ctdv.MaHD
WHERE csvc.MaDMC = 'CONG' 


-- lấy số lượng bàn đã sử dụng ở cơ sở vật chất trong ngày nào đó
SELECT distinct  SUM(SoLuongBan) AS SoLuongBan FROM  CoSoVatChat csvc LEFT JOIN ChiTietDichVu ctdv ON csvc.MaCSVC = ctdv.MaCSVC
INNER JOIN HopDong hd ON hd.MaHD = ctdv.MaHD
WHERE csvc.MaCSVC = 'TRAIBANTRANG' AND NgayToChuc = '1/10/2022'
GROUP BY csvc.MaCSVC



-- lấy số lượng vật chất sử dụng trong ngày nào đó
SELECT COUNT(*) AS SoLuongSuDung  FROM  CoSoVatChat csvc LEFT JOIN ChiTietDichVu ctdv ON csvc.MaCSVC = ctdv.MaCSVC
INNER JOIN HopDong hd ON hd.MaHD = ctdv.MaHD
WHERE csvc.MaCSVC = 'THAMDO' AND NgayToChuc = '1/10/2022'
GROUP BY csvc.MaCSVC

--kiểm tra xem nhân viên có bị trùng lịch làm việc không
SELECT * FROM NhanVien nv INNER JOIN ChiTietPhanCong ct ON nv.MaNV = ct.MaNV
WHERE NgayPC = '1/1/2022'  AND ct.MaNV = 'NV001'
AND (  ( CAST('10:00:00' AS Time) BETWEEN GioBatDau AND GioKetThuc ) OR ( CAST('17:00:00' AS Time) BETWEEN GioBatDau AND GioKetThuc )   )



-- SELECT HopDong 
SELECT hd.MaHD,hd.MaNL,hd.MaND,nv.HoTen AS NguoiLap,SoLuongBan,s.TenSanh,hd.NgayLap,hd.NgayDuyet,kh.MaKH,kh.HoTen,kh.SoDienThoai,hd.NgayToChuc,hd.ThoiGianBatDau,hd.ThoiGianKetThuc,tt.MaTT,tt.TenTT 
,hd.The,hd.TienCoc,hd.TongTien,
( SELECT HoTen FROM NhanVien WHERE MaNV = hd.MaND ) AS NguoiDuyet
FROM HopDong hd INNER JOIN KhachHang kh ON hd.MaKH = kh.MaKH
INNER JOIN TrangThaiHopDong tt ON hd.TrangThai = tt.MaTT
INNER JOIN NhanVien nv ON nv.MaNV = hd.MaNL
INNER JOIN Sanh s ON s.MaSanh = hd.Sanh




SELECT hd.MaHD,hd.MaNL,hd.MaND,nv.HoTen AS NguoiLap ,SoLuongBan,s.TenSanh,hd.NgayLap,kh.HoTen,kh.SoDienThoai,hd.NgayToChuc,hd.ThoiGianBatDau,hd.ThoiGianKetThuc,tt.MaTT,tt.TenTT 

FROM HopDong hd INNER JOIN KhachHang kh ON hd.MaKH = kh.MaKH
INNER JOIN TrangThaiHopDong tt ON hd.TrangThai = tt.MaTT
INNER JOIN NhanVien nv ON nv.MaNV = hd.MaNL
INNER JOIN Sanh s ON s.MaSanh = hd.Sanh
WHERE MaHD = 'HD001'


-- tính toán các khoản chi phí trong hợp đồng dịch vụ
SELECT  SUM( distinct hddv.ChiPhi) AS ChiPhi  FROM HopDongDichVu hddv 
WHERE hddv.MaHD = 'HD20220101001'

SELECT SUM ( a.ChiPhiPhatSinh ) AS ChiPhiPhatSinh
FROM ( SELECT SUM ( distinct ChiPhiPhatSinh)  AS ChiPhiPhatSinh FROM HopDongDichVu hddv INNER JOIN ChiTietDichVu ct ON hddv.MaHD = ct.MaHD
WHERE hddv.MaHD = 'HD20220101001'
GROUP BY ct.MaCSVC ) a

SELECT * FROM HopDongDichVu hddv INNER JOIN ChiTietDichVu ct ON hddv.MaHD = ct.MaHD
WHERE hddv.MaHD = 'HD20220101001'


--tinh toan cac khoan chi phi trong dat mon
SELECT SUM(ChiPhi) FROM DichVuDatMon WHERE MaHD = 'HD20220101001' AND MaTD = 'TD001'

SELECT SUM( ChiPhiPhatSinh * SoLuong )  FROM ChiTietDatMon WHERE MaHD = 'HD20220101001'  AND MaTD = 'TD002'

-- tinh toan cac khoan chi phi trong dichvu di kem
SELECT ChiPhi FROM HopDongDichVuDiKem WHERE MaHD = 'HD20220105001'

SELECT SUM(ct.ChiPhiPhatSinh) FROM HopDongDichVuDiKem dv INNER JOIN ChiTietDichVuDiKem ct ON dv.MaHD = ct.MaHD WHERE dv.MaHD = 'HD20220105001'


SELECT * FROM HopDongDichVu hddv 
INNER JOIN DichVuDatMon dvdm ON hddv.MaHD = dvdm.MaHD
INNER JOIN ChiTietDichVuDiKem dvdk ON hddv.MaHD = dvdk.MaHD
WHERE hddv.MaHD = 'HD001'


-- tinh tien trong sanh
SELECT (GiaThueSanh + ( GiaBan * hd.SoLuongBan )) AS ChiPhi FROM Sanh s INNER JOIN HopDong hd ON s.MaSanh = hd.Sanh WHERE MaHD = 'HD20220101001'


( (SELECT (GiaThueSanh + ( GiaBan * hd.SoLuongBan )) AS ChiPhi FROM Sanh s INNER JOIN HopDong hd ON s.MaSanh = hd.Sanh WHERE s.MaSanh = 'SANH03' ) )

SELECT GiaThueSanh FROM Sanh WHERE MaSanh = 'SANH03'

SELECT GiaBan FROM Sanh WHERE MaSanh = 'SANH03'

SELECT SoLuongBan  FROM  HopDong hd  WHERE MaHD = 'HD20220511001'

SELECT ( (SELECT GiaThueSanh FROM Sanh WHERE MaSanh = 'SANH03') + ((SELECT GiaBan FROM Sanh WHERE MaSanh = 'SANH03') * (SELECT SoLuongBan  FROM  HopDong hd  WHERE MaHD = 'HD20220511001'))  )

ALTER PROCEDURE tinhTien( @MaHD varchar(50) )
AS
BEGIN
	DECLARE @ChiPhi bigint,@ChiPhiPhatSinh bigint;
	DECLARE @chiPhiHopDongDichVu bigint, @chiPhiDatMon bigint, @chiPhiDiKem bigint,@chiPhiSanh bigint , @chiPhiPSDichVu bigint, @chiPhiPSDatMon bigint, @chiPhiPSDiKem bigint;
	
	SELECT @chiPhiHopDongDichVu = ( SELECT  SUM( distinct hddv.ChiPhi) AS ChiPhi  FROM HopDongDichVu hddv WHERE hddv.MaHD = 'HD20221129001') 
	SELECT @chiPhiDatMon = ( SELECT SUM(ChiPhi) FROM DichVuDatMon WHERE MaHD = @MaHD)
	SELECT @chiPhiDiKem = ( SELECT ChiPhi FROM HopDongDichVuDiKem WHERE MaHD =@MaHD )
	SELECT @chiPhiSanh = ( (SELECT (GiaThueSanh + ( GiaBan * hd.SoLuongBan )) AS ChiPhi FROM Sanh s INNER JOIN HopDong hd ON s.MaSanh = hd.Sanh WHERE MaHD = 'HD20221129001') )

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
	SELECT @chiPhiPSDiKem = ( SELECT SUM(ct.ChiPhiPhatSinh) FROM HopDongDichVuDiKem dv INNER JOIN ChiTietDichVuDiKem ct ON dv.MaHD = ct.MaHD WHERE dv.MaHD = @MaHD )

	IF @chiPhiPSDichVu IS NULL SET @chiPhiPSDichVu = 0
	IF @chiPhiPSDatMon IS NULL SET @chiPhiPSDatMon = 0
	IF @chiPhiPSDiKem IS NULL SET @chiPhiPSDiKem = 0

	SELECT @ChiPhiPhatSinh = @chiPhiPSDichVu + @chiPhiPSDatMon + @chiPhiPSDiKem
	
	PRINT @ChiPhiPhatSinh

	SELECT @ChiPhi AS ChiPhi,@ChiPhiPhatSinh AS ChiPhiPhatSinh
END

GO
ALTER PROCEDURE tinhTienVoiSanh( @MaHD varchar(50), @MaSanh varchar(50) ,@SoLuongBan int)
AS
BEGIN
	DECLARE @ChiPhi bigint,@ChiPhiPhatSinh bigint;
	DECLARE @chiPhiHopDongDichVu bigint, @chiPhiDatMon bigint, @chiPhiDiKem bigint,@chiPhiSanh bigint , @chiPhiPSDichVu bigint, @chiPhiPSDatMon bigint, @chiPhiPSDiKem bigint;
	
	SELECT @chiPhiHopDongDichVu = ( SELECT  SUM( distinct hddv.ChiPhi) AS ChiPhi  FROM HopDongDichVu hddv WHERE hddv.MaHD = 'HD20221129001') 
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
	SELECT @chiPhiPSDiKem = ( SELECT SUM(ct.ChiPhiPhatSinh) FROM HopDongDichVuDiKem dv INNER JOIN ChiTietDichVuDiKem ct ON dv.MaHD = ct.MaHD WHERE dv.MaHD = @MaHD )

	IF @chiPhiPSDichVu IS NULL SET @chiPhiPSDichVu = 0
	IF @chiPhiPSDatMon IS NULL SET @chiPhiPSDatMon = 0
	IF @chiPhiPSDiKem IS NULL SET @chiPhiPSDiKem = 0

	SELECT @ChiPhiPhatSinh = @chiPhiPSDichVu + @chiPhiPSDatMon + @chiPhiPSDiKem
	
	PRINT @ChiPhiPhatSinh

	SELECT @ChiPhi AS ChiPhi,@ChiPhiPhatSinh AS ChiPhiPhatSinh
END


EXEC tinhTien @MaHD = 'HD20221201001'

EXEC tinhTienVoiSanh @MaHD = 'HD20220901001' , @MaSanh = 'SANH03', @SoLuongBan = 20

GO
CREATE PROCEDURE tinhChiPhiPhatSinh(@MaHD varchar(50))
AS
BEGIN
	DECLARE @ChiPhi bigint,@ChiPhiDichVu bigint , @ChiPhiDatMon bigint, @ChiPhiDiKem bigint;
	SELECT @ChiPhiDichVu = ( SELECT SUM(ChiPhi) AS ChiPhi FROM  ChiPhiPhatSinh )
	SELECT @ChiPhiDatMon = ( SELECT SUM( ma.GiaTien * SoLuong )  FROM ChiTietDatMon ct INNER JOIN MonAn ma ON ct.MaMA = ma.MaMA
							 WHERE MaHD = @MaHD AND MaPL = 'NUOC')
    SELECT @ChiPhiDiKem = (SELECT SUM(ChiPhi) FROM ChiTietDichVuDiKem WHERE MaHD = @MaHD AND MaDV = 'PHAOKIMTUYEN')

	IF @ChiPhiDatMon IS NULL SET @ChiPhiDatMon = 0
	IF @ChiPhiDichVu IS NULL SET @ChiPhiDichVu = 0
	IF @ChiPhiDiKem IS NULL SET @ChiPhiDiKem = 0

	SELECT @ChiPhi = @ChiPhiDatMon + @ChiPhiDichVu + @ChiPhiDiKem
	PRINT @ChiPhi

	SELECT @ChiPhi AS ChiPhi
END

EXEC tinhChiPhiPhatSinh @MaHD =  'HD20221129001' 

SELECT SUM( ma.GiaTien * SoLuong )  FROM ChiTietDatMon ct INNER JOIN MonAn ma ON ct.MaMA = ma.MaMA
WHERE MaHD = 'HD20221129001'  AND MaPL = 'NUOC'

SELECT * FROM ChiTietDichVuDiKem WHERE MaHD = 'HD20221129001' AND MaDV = 'PHAOKIMTUYEN'

SELECT * FROM ChiPhiPhatSinh WHERE MaHD = 'HD20220101001' AND MaDV ='TTCONG'




SELECT hd.MaHD,hd.MaNL,hd.MaND,nv.HoTen AS NguoiLap,SoLuongBan,s.TenSanh,hd.NgayLap,hd.NgayDuyet,kh.MaKH,kh.HoTen,kh.SoDienThoai,hd.NgayToChuc,hd.ThoiGianBatDau,hd.ThoiGianKetThuc,tt.MaTT,tt.TenTT 
            ,hd.The,hd.TienCoc,hd.TongTien,
            ( SELECT HoTen FROM NhanVien WHERE MaNV = hd.MaND ) AS NguoiDuyet
            FROM HopDong hd INNER JOIN KhachHang kh ON hd.MaHD = kh.MaHD
            INNER JOIN TrangThaiHopDong tt ON hd.TrangThai = tt.MaTT
            INNER JOIN NhanVien nv ON nv.MaNV = hd.MaNL
            INNER JOIN Sanh s ON s.MaSanh = hd.Sanh 




--Kiểm tra sảnh có được đặt hay chưa
SELECT * FROM HopDong hd INNER JOIN Sanh s  ON hd.Sanh = s.MaSanh
WHERE MaSanh = 'SANH03' AND NgayToChuc = '2022-12-22' AND (  ( CAST('13:00:00' AS Time)   BETWEEN ThoiGianBatDau AND ThoiGianKetThuc ) OR  (  ( CAST('16:00:00' AS Time)   BETWEEN ThoiGianBatDau AND ThoiGianKetThuc )   )   )



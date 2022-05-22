﻿
CREATE DATABASE SCHOOL
go
USE SCHOOL
GO
-- TAI KHOAN
CREATE TABLE TAIKHOAN(
	TAIKHOAN VARCHAR(255) NOT NULL PRIMARY KEY,
	MATKHAU VARCHAR(256) NOT NULL,
	ROLE VARCHAR(10),
	TRANGTHAI VARCHAR(10)
)

INSERT INTO TAIKHOAN VALUES
('admin', '$2a$12$O7zJkruHOIKWrZhp8XKiq.eOW1mZPclUE0L3x6Ic8CwVI25Njd52O', 'ADMIN', 'ACTIVE')

INSERT INTO TAIKHOAN VALUES
('hs1', '$2a$12$O7zJkruHOIKWrZhp8XKiq.eOW1mZPclUE0L3x6Ic8CwVI25Njd52O', 'HOCSINH', 'ACTIVE')

INSERT INTO TAIKHOAN VALUES
('hs2', '$2a$12$hHRbETzIDXxoCmaCr2yR1emowvrvHJwSTo10SnfXtOezWSDqdkS66', 'HOCSINH', 'ACTIVE')

SELECT TOP 1 PERCENT *  FROM THONGBAO 
SELECT * FROM THONGBAO, LOAITHONGBAO WHERE THONGBAO.IDLOAITHONGBAO = LOAITHONGBAO.IDLOAITHONGBAO ORDER BY (SELECT NULL) OFFSET 0 ROWS FETCH NEXT 10 ROWS ONLY
SELECT * FROM THONGBAO LIMIT 1
SELECT * 
FROM THONGBAO 
WHERE THONGBAO.TIEUDE LIKE '%S%'
ORDER BY (SELECT NULL) OFFSET 0 ROWS FETCH NEXT 10 ROWS ONLY;

-- LOAI THONG BAO
CREATE TABLE LOAITHONGBAO(
	IDLOAITHONGBAO INT IDENTITY(1,1) PRIMARY KEY,
	TEN NVARCHAR(100)
)
INSERT INTO LOAITHONGBAO VALUES
(N'Học Phí'),
(N'Lịch Nghỉ'),
(N'Hoạt Động')


-- THONG BAO
CREATE TABLE THONGBAO(
	IDTHONGBAO INT IDENTITY(1,1) PRIMARY KEY,
	TIEUDE NVARCHAR(100),
	NOIDUNG NVARCHAR(500),
	NGAYDANG DATE,
	IDLOAITHONGBAO INT,
)

CREATE TABLE NAMHOC(
	IDNAMHOC INT IDENTITY(1,1) PRIMARY KEY,
	TENNAMHOC NVARCHAR(50),
	NGAYBATDAU DATE,
	NGAYKETTHUC DATE,
	TRANGTHAI NVARCHAR(50)
)

CREATE TABLE HOCKY(
	IDHOCKY  INT IDENTITY(1,1) PRIMARY KEY,
	IDNAMHOC VARCHAR(10) NOT NULL,
	TENHOCKY NVARCHAR(50),
	NGAYBATDAU DATE,
	NGAYKETTHUC DATE,
	TRANGTHAI NVARCHAR(50)
)

-- CODE NGÀY 25 / 4 

CREATE TABLE PHONGHOC(
	IDPHONGHOC INT IDENTITY(1,1) NOT NULL PRIMARY KEY,
	TENPHONGHOC NVARCHAR(50),
	TRANGTHAI INT DEFAULT 0
)

CREATE TABLE LOP(
	IDLOP INT IDENTITY(1,1) NOT NULL PRIMARY KEY,
	TENLOP NVARCHAR(50),
	KHOI INT,
	IDPHONGHOC INT,
	IDNAMHOC INT
)

CREATE TABLE GIAOVIEN(
	IDGV VARCHAR(10) NOT NULL PRIMARY KEY,
	TENGV NVARCHAR(50),
	CMND VARCHAR(20),
	NGAYSINH DATE,
	GIOITINH NVARCHAR(10),
	QUEQUAN NVARCHAR(50),
	DANTOC NVARCHAR(50),
	HOKHAU NVARCHAR(100),
	SDT VARCHAR(12),
	EMAIL VARCHAR(50),
	LINKAVATAR VARCHAR(50),
	TRANGTHAI VARCHAR(50)
)
CREATE TABLE GVCN (
	USERNAMEGVCN VARCHAR(10) NOT NULL,
	IDLOP INT NOT NULL
)
-- 
CREATE TABLE KHOI(
	IDKHOI INT PRIMARY KEY,
	TENKHOI VARCHAR(10) NOT NULL,
)
INSERT INTO KHOI VALUES
(10, '10'),
(11, '11'),
(12, '12')


-- MON HOC
CREATE TABLE MONHOC(
	IDMONHOC INT IDENTITY(1,1) PRIMARY KEY,
	TENMONHOC NVARCHAR(50)
)
INSERT INTO MONHOC VALUES
(N'Math'),
(N'Literature'),
(N'History'),
(N'Physics'),
(N'Chemistry'),
(N'English'),
(N'Biology')

-- Giao Vien Mon Hoc
CREATE TABLE GV_MONHOC(
	IDGV VARCHAR(10) NOT NULL,
	IDMONHOC INT NOT NULL,
	IDLOP INT NOT NULL
)

SELECT * FROM GV_MONHOC 
LEFT JOIN LOP ON GV_MONHOC.IDLOP = LOP.IDLOP 
LEFT JOIN NAMHOC ON LOP.IDNAMHOC = NAMHOC.IDNAMHOC 
LEFT JOIN MONHOC ON GV_MONHOC.IDMONHOC = MONHOC.IDMONHOC
WHERE GV_MONHOC.IDGV = 'gv1'

CREATE TABLE HOCSINH(
	IDHS VARCHAR(10) NOT NULL PRIMARY KEY,
	TENHS NVARCHAR(50),
	NGAYSINH DATE,
	GIOITINH NVARCHAR(10),
	QUEQUAN NVARCHAR(50),
	DANTOC NVARCHAR(50),
	HOKHAU NVARCHAR(100),
	SDTPHUHUYNH VARCHAR(12),
	LINKAVATAR VARCHAR(50),
	TRANGTHAI VARCHAR(50)
)
insert into HOCSINH values('hs3', N'Bảo', '05-05-2000', N'Nam', N'Gia Lai', N'Kinh', N'GL', '0375723290', '', 'ACTIVE')

-- 5/4

CREATE TABLE BUOI(
	IDBUOI INT IDENTITY(1,1) PRIMARY KEY,
	TENBUOI NVARCHAR(50)
)
INSERT INTO BUOI VALUES
(N'Morning'),
(N'Afternoon')


CREATE TABLE TIET(
	IDTIET INT IDENTITY(1,1) PRIMARY KEY,
	TENTIET NVARCHAR(50),
	IDBUOI INT,
)
SELECT * FROM THU
INSERT INTO TIET VALUES
('Shift 1', 1),
('Shift 2', 1),
('Shift 3', 1),
('Shift 4', 1),
('Shift 5', 1),
('Shift 1', 2),
('Shift 2', 2),
('Shift 3', 2),
('Shift 4', 2),
('Shift 5', 2)

CREATE TABLE THU(
	IDTHU INT IDENTITY(1,1) PRIMARY KEY,
	TENTHU NVARCHAR(50)
)

INSERT INTO THU VALUES
('Mon'),
('Tue'),
('Wed'),
('Thu'),
('Fri'),
('Sat'),
('Sun')

-- TKB
CREATE TABLE TKB(
	IDMONHOC INT NOT NULL,
	IDLOP INT NOT NULL,
	IDTIET INT NOT NULL,
	IDTHU INT NOT NULL,
	IDHOCKY INT NOT NULL,
)

CREATE TABLE HOCSINH_LOPHOC(
	IDLOP INT NOT NULL,
	IDHS VARCHAR(10) NOT NULL,
)
CREATE TABLE GV_MONHOC(
	IDGV VARCHAR(10) NOT NULL,
	IDMONHOC INT NOT NULL,
	IDLOP INT NOT NULL
)
SELECT * FROM GIAOVIEN WHERE IDGV = 'gv1'
SELECT * FROM TKB 
LEFT JOIN LOP ON TKB.IDLOP = LOP.IDLOP 
LEFT JOIN MONHOC ON MONHOC.IDMONHOC = TKB.IDMONHOC
LEFT JOIN GV_MONHOC ON GV_MONHOC.IDLOP = LOP.IDLOP 
WHERE GV_MONHOC.IDGV = 'gv1' AND TKB.IDHOCKY = 8

SELECT * FROM HOCSINH_LOPHOC LEFT JOIN LOP ON LOP.IDLOP = HOCSINH_LOPHOC.IDLOP WHERE LOP.IDNAMHOC = 1 AND HOCSINH_LOPHOC.IDHS ='hs1'
SELECT * FROM HOCSINH_LOPHOC LEFT JOIN LOP ON HOCSINH_LOPHOC.IDLOP = LOP.IDLOP WHERE LOP.IDNAMHOC = 3 AND HOCSINH_LOPHOC.IDHS = 'hs1'

SELECT * FROM LOP WHERE IDLOP = 1

SELECT * FROM HOCSINH 
LEFT JOIN HOCSINH_LOPHOC ON HOCSINH.IDHS = HOCSINH_LOPHOC.IDHS 
LEFT JOIN LOP ON LOP.IDLOP = HOCSINH_LOPHOC.IDLOP 
WHERE HOCSINH_LOPHOC.IDLOP = 2

SELECT * FROM HOCSINH_LOPHOC
SELECT * FROM HOCSINH
SELECT * FROM LOP
SELECT * FROM TKB
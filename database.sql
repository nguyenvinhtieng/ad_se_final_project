﻿CREATE DATABASE SCHOOL
go
USE SCHOOL

-- TAI KHOAN
CREATE TABLE TAIKHOAN(
	TAIKHOAN VARCHAR(255) NOT NULL PRIMARY KEY,
	MATKHAU VARCHAR(256) NOT NULL,
	ROLE VARCHAR(10),
	TRANGTHAI VARCHAR(10)
)
SELECT * FROM TAIKHOAN WHERE ROLE <> 'ADMIN'
INSERT INTO TAIKHOAN VALUES
('x', 'b', 'a', 'ACTIVE')

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

INSERT INTO THONGBAO VALUES
(N'Title 1', N'Content 1', '4-4-2020', 1),
(N'Title 2', N'Content 2', '4-4-2020', 2),
(N'Title 3', N'Content 3', '4-4-2020', 3)

SELECT * FROM THONGBAO, LOAITHONGBAO WHERE THONGBAO.IDLOAITHONGBAO = LOAITHONGBAO.IDLOAITHONGBAO
SELECT * FROM LOAITHONGBAO 
SELECT * FROM NAMHOC 
CREATE TABLE NAMHOC(
	IDNAMHOC INT IDENTITY(1,1) PRIMARY KEY,
	TENNAMHOC NVARCHAR(50),
	NGAYBATDAU DATE,
	NGAYKETTHUC DATE,
	TRANGTHAI NVARCHAR(50)
)
SELECT * FROM HOCKY, NAMHOC WHERE HOCKY.IDNAMHOC = NAMHOC.IDNAMHOC
CREATE TABLE HOCKY(
	IDHOCKY  INT IDENTITY(1,1) PRIMARY KEY,
	IDNAMHOC VARCHAR(10) NOT NULL,
	TENHOCKY NVARCHAR(50),
	NGAYBATDAU DATE,
	NGAYKETTHUC DATE,
	TRANGTHAI NVARCHAR(50)
)
INSERT INTO NAMHOC VALUES
('2021 - 2022', '4-4-2020', '5-5-2020', 'END'),
('2022 - 2023', '4-4-2020', '5-5-2020', 'END')

INSERT INTO HOCKY VALUES
(1, 'HK1' ,'4-4-2020', '5-5-2020', 'END'),
(1, 'HK2' ,'4-4-2020', '5-5-2020', 'END'),
(2, 'HK1' ,'4-4-2020', '5-5-2020', 'END'),
(2, 'HK2' ,'4-4-2020', '5-5-2020', 'END')

-- CODE NGÀY 25 / 4 

CREATE TABLE PHONGHOC(
	IDPHONGHOC INT IDENTITY(1,1) NOT NULL PRIMARY KEY,
	TENPHONGHOC NVARCHAR(50),
	TRANGTHAI INT DEFAULT 0
)

INSERT INTO PHONGHOC(TENPHONGHOC) VALUES
(N'R0101'),
(N'R0102'),
(N'R0103'),
(N'R0201'),
(N'R0202'),
(N'R0203')

SELECT * FROM PHONGHOC LEFT JOIN LOP ON PHONGHOC.TRANGTHAI = LOP.IDLOP
UPDATE PHONGHOC SET TRANGTHAI = 0 WHERE IDPHONGHOC = 2
SELECT * FROM LOP LEFT JOIN PHONGHOC ON LOP.IDPHONGHOC = PHONGHOC.IDPHONGHOC
SELECT * FROM LOP, PHONGHOC WHERE LOP.IDPHONGHOC = PHONGHOC.IDPHONGHOC
SELECT * FROM LOP WHERE IDLOP = 1
SELECT * FROM PHONGHOC WHERE IDPHONGHOC = 1
SELECT * FROM LOP WHERE IDLOP = 1
CREATE TABLE LOP(
	IDLOP INT IDENTITY(1,1) NOT NULL PRIMARY KEY,
	TENLOP NVARCHAR(50),
	KHOI INT,
	IDPHONGHOC INT,
	NAMVAOTRUONG INT
)
SELECT * FROM LOP
INSERT INTO LOP VALUES
(N'10A1', 10, 1, 2019),
(N'11A1', 11, 2, 2018),
(N'121A1', 12, 3, 2017)

CREATE TABLE HOCSINH(
	MAHS VARCHAR(10) NOT NULL,
	TENHS NVARCHAR(50),
	NGAYSINH DATE,
	GIOITINH NVARCHAR(10),
	QUEQUAN NVARCHAR(50),
	DANTOC NVARCHAR(50),
	HOKHAU NVARCHAR(100),
	SDTPHUHUYNH VARCHAR(12),
	LINKAVATAR VARCHAR(50),
	TRANGTHAI VARCHAR(10),
	IDLOPHOC INT,
)

INSERT INTO HOCSINH VALUES
(N'HS01', N'Nguyen Van A', N'02-02-2020', N'NAM', N'TP HCM', N'Kinh', N'Tp HCM', N'0123456','123', 'ACTIVE' , 1)

SELECT * FROM HOCSINH, LOP WHERE HOCSINH.IDLOPHOC = LOP.IDLOP